package fr.hugman.promenade.block;

import fr.hugman.promenade.state.property.PromenadeBlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Optional;

/**
 * Block that dispenses particles on redstone input.
 * The block can face any of the 6 directions, and the particles will be dispensed on the front face.
 * The front face is a 9x9 grid of toggleable outputs.
 *
 * @author Hugman
 */
public class SparklerBlock extends FacingBlock {
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
    public static final List<BooleanProperty> SLOT_OPEN_PROPERTIES = List.of(
            PromenadeBlockProperties.SLOT_0_OPEN,
            PromenadeBlockProperties.SLOT_1_OPEN,
            PromenadeBlockProperties.SLOT_2_OPEN,
            PromenadeBlockProperties.SLOT_3_OPEN,
            PromenadeBlockProperties.SLOT_4_OPEN,
            PromenadeBlockProperties.SLOT_5_OPEN,
            PromenadeBlockProperties.SLOT_6_OPEN,
            PromenadeBlockProperties.SLOT_7_OPEN,
            PromenadeBlockProperties.SLOT_8_OPEN
    );

    public SparklerBlock(Settings settings) {
        super(settings);
        BlockState state = this.stateManager.getDefaultState().with(FACING, Direction.SOUTH).with(TRIGGERED, false);
        for (BooleanProperty property : SLOT_OPEN_PROPERTIES) {
            state = state.with(property, true);
        }
        this.setDefaultState(state);
    }

    // ==========
    //   STATES
    // ==========

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TRIGGERED);
        SLOT_OPEN_PROPERTIES.forEach(builder::add);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    // ================
    //   INTERACTIONS
    // ================

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Optional<Vec2f> optPos = getHitPos(hit, state.get(FACING));
        if (optPos.isEmpty()) {
            return ActionResult.PASS;
        }
        int i = getSlotForHitPos(optPos.get());
        toggleSlot(world, pos, state, player, i);
        return ActionResult.success(world.isClient);
    }

    private static Optional<Vec2f> getHitPos(BlockHitResult hit, Direction facing) {
        Direction direction = hit.getSide();
        if (facing != direction) {
            return Optional.empty();
        }
        BlockPos blockPos = hit.getBlockPos().offset(direction);
        Vec3d vec3d = hit.getPos().subtract(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        double d = vec3d.getX();
        double e = vec3d.getY();
        double f = vec3d.getZ();
        return switch (direction) {
            case NORTH -> Optional.of(new Vec2f((float) (1.0 - d), (float) e));
            case SOUTH -> Optional.of(new Vec2f((float) d, (float) e));
            case WEST -> Optional.of(new Vec2f((float) f, (float) e));
            case EAST -> Optional.of(new Vec2f((float) (1.0 - f), (float) e));
            case DOWN -> Optional.of(new Vec2f((float) (1.0 - d), (float) (1.0 - f)));
            case UP -> Optional.of(new Vec2f((float) (1.0 - d), (float) f));
        };
    }

    private static int getSlotForHitPos(Vec2f hitPos) {
        int x = MathHelper.floor(hitPos.x * 3);
        int y = MathHelper.floor((1 - hitPos.y) * 3);
        return x + y * 3;
    }

    private static void toggleSlot(World world, BlockPos pos, BlockState state, PlayerEntity player, int slot) {
        if (world.isClient) {
            return;
        }
        var property = SLOT_OPEN_PROPERTIES.get(slot);
        var isOpen = state.get(property);
        SoundEvent soundEvent = isOpen ? SoundEvents.BLOCK_CHISELED_BOOKSHELF_INSERT_ENCHANTED : SoundEvents.BLOCK_CHISELED_BOOKSHELF_PICKUP_ENCHANTED;
        world.setBlockState(pos, state.cycle(property));
        world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    // =======================
    //   REDSTONE REACTIVITY
    // =======================

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        boolean isBeingTriggered = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up());
        boolean isTriggeredAlready = state.get(TRIGGERED);
        boolean isFaceCovered = world.getBlockState(pos.offset(state.get(FACING))).isSolidBlock(world, pos.offset(state.get(FACING)));

        if (isBeingTriggered && !isTriggeredAlready && !isFaceCovered) {
            world.scheduleBlockTick(pos, this, 4);
            world.setBlockState(pos, state.with(TRIGGERED, true), Block.NO_REDRAW);
            return;
        }

        if (!isBeingTriggered && isTriggeredAlready) {
            world.setBlockState(pos, state.with(TRIGGERED, false), Block.NO_REDRAW);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        int count = 0;
        for (BooleanProperty property : SLOT_OPEN_PROPERTIES) {
            if (state.get(property)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.sparkle(world, pos);
    }

    /**
     * Dispenses particles from the front face of the block.
     */
    protected void sparkle(ServerWorld world, BlockPos pos) {
        var state = world.getBlockState(pos);
        var direction = state.get(FACING);
        var sparklerPos = pos.offset(direction);
        var sparklerState = world.getBlockState(sparklerPos);

        //TODO: the block behind the sparkler will define the type of particle to dispense
        // for now we will have a generic particle
        var particle = ParticleTypes.SNOWFLAKE;


    }
}
