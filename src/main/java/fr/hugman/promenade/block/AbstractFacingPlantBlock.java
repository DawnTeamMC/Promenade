package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import java.util.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public abstract class AbstractFacingPlantBlock extends AbstractFacingPlantPartBlock implements Fertilizable {
    protected AbstractFacingPlantBlock(Settings settings, VoxelShape[] outlineShapes, boolean bl) {
        super(settings, outlineShapes, bl);
    }

    @Override
    protected abstract MapCodec<? extends AbstractFacingPlantBlock> getCodec();

    protected BlockState copyState(BlockState from, BlockState to) {
        return to.with(FACING, from.get(FACING));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {
        var facing = state.get(FACING);
        if (direction == facing.getOpposite() && !state.canPlaceAt(world, pos)) {
            tickView.scheduleBlockTick(pos, this, 1);
        }

        AbstractFacingPlantStemBlock stem = this.getStem();
        if (direction == facing && !neighborState.isOf(this) && !neighborState.isOf(stem)) {
            return this.copyState(state, stem.getRandomGrowthState(random));
        } else {
            if (this.tickWater) {
                tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(this.getStem());
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        var facing = state.get(FACING);
        Optional<BlockPos> optional = this.getStemHeadPos(world, pos, state.getBlock(), facing);
        return optional.isPresent() && this.getStem().canGrowAt(world.getBlockState(optional.get().offset(facing)));
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        Optional<BlockPos> optional = this.getStemHeadPos(world, pos, state.getBlock(), state.get(FACING));
        if (optional.isPresent()) {
            BlockState blockState = world.getBlockState(optional.get());
            ((AbstractFacingPlantStemBlock) blockState.getBlock()).grow(world, random, optional.get(), blockState);
        }
    }

    private Optional<BlockPos> getStemHeadPos(BlockView world, BlockPos pos, Block block, Direction direction) {
        return BlockLocating.findColumnEnd(world, pos, block, direction, this.getStem());
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        boolean bl = super.canReplace(state, context);
        return (!bl || !context.getStack().isOf(this.getStem().asItem())) && bl;
    }

    @Override
    protected Block getPlant() {
        return this;
    }
}
