package fr.hugman.promenade.block;

import fr.hugman.promenade.block.sparkler.SparklerBehavior;
import fr.hugman.promenade.state.property.PromenadeBlockProperties;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
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
    public static final BooleanProperty POWERED = Properties.POWERED;
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
    public static final List<SparklerBehavior> DEFAULT_BEHAVIORS = List.of(
            new SparklerBehavior(ParticleTypes.SCRAPE, 20.0D, Blocks.OXIDIZED_COPPER, Blocks.OXIDIZED_CUT_COPPER),
            new SparklerBehavior(ParticleTypes.END_ROD, 0.25D, Blocks.END_ROD),
            new SparklerBehavior(ParticleTypes.SNEEZE, 0.15D, Blocks.SLIME_BLOCK)
    );

    private final List<SparklerBehavior> behaviors;

    public SparklerBlock(List<SparklerBehavior> behaviors, Settings settings) {
        super(settings);
        this.behaviors = behaviors;

        BlockState state = this.stateManager.getDefaultState()
                .with(FACING, Direction.SOUTH)
                .with(POWERED, false);
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
        builder.add(FACING, POWERED);
        SLOT_OPEN_PROPERTIES.forEach(builder::add);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerLookDirection().getOpposite())
                .with(POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
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
        Vec2f hitPos = optPos.get();
        int slotX = MathHelper.floor(hitPos.x * 3);
        int slotY = MathHelper.floor((1 - hitPos.y) * 3);
        toggleSlot(world, pos, state, player, slotX + slotY * 3);
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
        if (world.isClient) {
            return;
        }
        boolean bl = state.get(POWERED);
        if (bl != world.isReceivingRedstonePower(pos)) {
            if (bl) {
                world.scheduleBlockTick(pos, this, 4);
            } else {
                world.setBlockState(pos, state.cycle(POWERED), Block.NOTIFY_LISTENERS);
            }
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
        if (state.get(POWERED) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(POWERED), Block.NOTIFY_LISTENERS);
        }
    }

    // ===========
    //   DISPLAY
    // ===========

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(POWERED)) {
            return;
        }
        sparkle(world, state, pos);
    }

    /**
     * Dispenses particles from the front face of the block.
     */
    @Environment(EnvType.CLIENT)
    protected void sparkle(World world, BlockState state, BlockPos pos) {
        var direction = state.get(FACING);

        if (!world.getBlockState(pos.offset(direction)).isAir()) {
            return;
        }

        SparklerBehavior behavior = null;
        for (SparklerBehavior b : behaviors) {
            if (b.blocks().contains(world.getBlockState(pos.offset(direction.getOpposite())).getBlock().getRegistryEntry())) {
                behavior = b;
                break;
            }
        }
        if (behavior == null) {
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (state.get(SLOT_OPEN_PROPERTIES.get(i))) {
                var slotPos = getSlotPos(direction, pos, i);
                var slotVelocity = getSlotVelocity(pos.toCenterPos(), slotPos, behavior.strength());
                world.addParticle(behavior.particle(),
                        slotPos.getX(), slotPos.getY(), slotPos.getZ(),
                        slotVelocity.getX(), slotVelocity.getY(), slotVelocity.getZ()
                );
            }
        }
    }

    private static final double SLOT_PADDING = 3 / 16D;
    private static final double SLOT_OFFSET = 5 / 16D;
    private static final double FACE_OFFSET = 0.1D;

    protected Vec3d getSlotPos(Direction direction, BlockPos pos, int slot) {
        var slotX = slot % 3;
        var slotY = slot / 3;

        var x = SLOT_PADDING + slotX * SLOT_OFFSET;
        var y = SLOT_PADDING + slotY * SLOT_OFFSET;

        return switch (direction) {
            case NORTH -> new Vec3d(pos.getX() + (1 - x), pos.getY() + (1 - y), pos.getZ() - FACE_OFFSET);
            case SOUTH -> new Vec3d(pos.getX() + x, pos.getY() + (1 - y), pos.getZ() + 1 + FACE_OFFSET);
            case WEST -> new Vec3d(pos.getX() - FACE_OFFSET, pos.getY() + (1 - y), pos.getZ() + x);
            case EAST -> new Vec3d(pos.getX() + 1 + FACE_OFFSET, pos.getY() + (1 - y), pos.getZ() + (1 - x));
            case DOWN -> new Vec3d(pos.getX() + (1 - x), pos.getY() - FACE_OFFSET, pos.getZ() + y);
            case UP -> new Vec3d(pos.getX() + (1 - x), pos.getY() + 1 + FACE_OFFSET, pos.getZ() + (1 - y));
        };
    }

    protected Vec3d getSlotVelocity(Vec3d center, Vec3d slotPos, double strength) {
        var velocity = slotPos.subtract(center);
        velocity = velocity.multiply(strength);

        return velocity;
    }
}
