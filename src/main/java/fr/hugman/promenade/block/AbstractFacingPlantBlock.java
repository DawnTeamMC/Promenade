package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractFacingPlantBlock extends AbstractFacingPlantPartBlock implements BonemealableBlock {
    protected AbstractFacingPlantBlock(Properties settings, VoxelShape[] outlineShapes, boolean bl) {
        super(settings, outlineShapes, bl);
    }

    @Override
    protected abstract MapCodec<? extends AbstractFacingPlantBlock> codec();

    protected BlockState copyState(BlockState from, BlockState to) {
        return to.setValue(FACING, from.getValue(FACING));
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader world,
            ScheduledTickAccess tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            RandomSource random
    ) {
        var facing = state.getValue(FACING);
        if (direction == facing.getOpposite() && !state.canSurvive(world, pos)) {
            tickView.scheduleTick(pos, this, 1);
        }

        AbstractFacingPlantStemBlock stem = this.getStem();
        if (direction == facing && !((neighborState.is(this) || neighborState.is(stem)) && neighborState.getValue(FACING) == facing)) {
            return this.copyState(state, stem.getRandomGrowthState(random));
        } else {
            if (this.tickWater) {
                tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
            }

            return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        }
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(this.getStem());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        var facing = state.getValue(FACING);
        Optional<BlockPos> optional = this.getStemHeadPos(world, pos, state.getBlock(), facing);
        return optional.isPresent() && this.getStem().canGrowAt(world.getBlockState(optional.get().relative(facing)));
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        Optional<BlockPos> optional = this.getStemHeadPos(world, pos, state.getBlock(), state.getValue(FACING));
        if (optional.isPresent()) {
            BlockState blockState = world.getBlockState(optional.get());
            ((AbstractFacingPlantStemBlock) blockState.getBlock()).performBonemeal(world, random, optional.get(), blockState);
        }
    }

    private Optional<BlockPos> getStemHeadPos(BlockGetter world, BlockPos pos, Block block, Direction direction) {
        return BlockUtil.getTopConnectedBlock(world, pos, block, direction, this.getStem());
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        boolean bl = super.canBeReplaced(state, context);
        return (!bl || !context.getItemInHand().is(this.getStem().asItem())) && bl;
    }

    @Override
    protected Block getPlant() {
        return this;
    }
}
