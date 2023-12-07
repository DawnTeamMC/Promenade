package fr.hugman.promenade.block;

import fr.hugman.promenade.state.property.PromenadeBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

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
            state = state.with(property, false);
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
        var side = hit.getSide();
        if(side == state.get(FACING)) {
            if(world.isClient) {
                return ActionResult.SUCCESS;
            }
            // for now we will just toggle all the slots
            for (BooleanProperty property : SLOT_OPEN_PROPERTIES) {
                state = state.cycle(property);
            }
            world.playSound(null, pos, SoundEvents.BLOCK_CHISELED_BOOKSHELF_INSERT, SoundCategory.BLOCKS, 1.0f, 1.0f); //TODO: change sound
            world.setBlockState(pos, state);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
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
    }
}
