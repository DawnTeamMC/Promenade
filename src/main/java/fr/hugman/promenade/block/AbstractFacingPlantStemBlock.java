package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractFacingPlantStemBlock extends AbstractFacingPlantPartBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_25;
    public static final int MAX_AGE = 25;
    private final double growthChance;

    public AbstractFacingPlantStemBlock(
            BlockBehaviour.Properties settings,
            VoxelShape[] outlineShapes,
            boolean tickWater,
            double growthChance
    ) {
        super(settings, outlineShapes, tickWater);
        this.growthChance = growthChance;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    @Override
    protected abstract MapCodec<? extends AbstractFacingPlantStemBlock> codec();

    @Override
    public BlockState getRandomGrowthState(RandomSource random) {
        return this.defaultBlockState().setValue(AGE, Integer.valueOf(random.nextInt(MAX_AGE)));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) < 25 && random.nextDouble() < this.growthChance) {
            BlockPos blockPos = pos.relative(state.getValue(FACING));
            if (this.canGrowAt(world.getBlockState(blockPos))) {
                world.setBlockAndUpdate(blockPos, this.age(state, world.random));
            }
        }
    }

    protected BlockState age(BlockState state, RandomSource random) {
        return state.cycle(AGE);
    }

    public BlockState withMaxAge(BlockState state) {
        return state.setValue(AGE, Integer.valueOf(MAX_AGE));
    }

    public boolean hasMaxAge(BlockState state) {
        return state.getValue(AGE) == MAX_AGE;
    }

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

        if (direction != facing || !((neighborState.is(this) || neighborState.is(this.getPlant())) && neighborState.getValue(FACING) == facing)) {
            if (this.tickWater) {
                tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
            }

            return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        } else {
            return this.copyState(state, this.getPlant().defaultBlockState());
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return this.canGrowAt(world.getBlockState(pos.relative(state.getValue(FACING))));
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        var facing = state.getValue(FACING);
        var blockPos = pos.relative(facing);
        var i = Math.min(state.getValue(AGE) + 1, MAX_AGE);
        var j = this.getGrowthLength(random);

        for (int k = 0; k < j && this.canGrowAt(world.getBlockState(blockPos)); k++) {
            world.setBlockAndUpdate(blockPos, state.setValue(AGE, Integer.valueOf(i)).setValue(FACING, facing));
            blockPos = blockPos.relative(facing);
            i = Math.min(i + 1, MAX_AGE);
        }
    }

    protected abstract int getGrowthLength(RandomSource random);

    protected abstract boolean canGrowAt(BlockState state);

    @Override
    protected AbstractFacingPlantStemBlock getStem() {
        return this;
    }
}
