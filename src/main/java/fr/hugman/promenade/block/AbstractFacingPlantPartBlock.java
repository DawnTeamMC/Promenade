package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFacingPlantPartBlock extends DirectionalBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    protected final VoxelShape[] outlineShapes;
    protected final boolean tickWater;

    public AbstractFacingPlantPartBlock(
            Properties settings,
            VoxelShape[] outlineShapes,
            boolean tickWater
    ) {
        super(settings);
        this.outlineShapes = outlineShapes;
        this.tickWater = tickWater;
    }

    @Override
    protected abstract MapCodec<? extends AbstractFacingPlantPartBlock> codec();

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        var placementDirections = ctx.getNearestLookingDirections();
        //TODO: make it prefer existing stems if not sneaking
        for (var direction : placementDirections) {
            var opposite = direction.getOpposite();
            var otherState = ctx.getLevel().getBlockState(ctx.getClickedPos().relative(opposite));
            var newState = !otherState.is(this.getStem()) && !otherState.is(this.getPlant())
                    ? this.getRandomGrowthState(ctx.getLevel().getRandom())
                    : this.getPlant().defaultBlockState();
            if (newState.hasProperty(FACING)) {
                newState = newState.setValue(FACING, opposite);
            }
            if (newState.canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
                return newState;
            }
        }
        return null;
    }

    public BlockState getRandomGrowthState(RandomSource random) {
        return this.defaultBlockState();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        var facing = state.getValue(FACING);
        var blockPos = pos.relative(facing.getOpposite());
        var blockState = world.getBlockState(blockPos);
        if (!this.canAttachTo(blockState)) return false;
        if (blockState.is(this.getStem()) || blockState.is(this.getPlant())) {
            if (blockState.hasProperty(FACING) && blockState.getValue(FACING) == facing) {
                return true;
            }
        }
        return blockState.isFaceSturdy(world, blockPos, facing);
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(world, pos)) {
            world.destroyBlock(pos, true);
        }
    }

    protected boolean canAttachTo(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.outlineShapes[state.getValue(FACING).get3DDataValue()];
    }

    protected abstract AbstractFacingPlantStemBlock getStem();

    protected abstract Block getPlant();
}
