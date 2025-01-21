package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFacingPlantPartBlock extends FacingBlock {
    public static final EnumProperty<Direction> FACING = Properties.FACING;

    protected final VoxelShape outlineShape;
    protected final boolean tickWater;

    public AbstractFacingPlantPartBlock(
            Settings settings,
            VoxelShape outlineShape,
            boolean tickWater
    ) {
        super(settings);
        this.outlineShape = outlineShape;
        this.tickWater = tickWater;
    }

    @Override
    protected abstract MapCodec<? extends AbstractFacingPlantPartBlock> getCodec();

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var placementDirections = ctx.getPlacementDirections();
        for (var direction : placementDirections) {
            var state = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction));
            if (state.isOf(this.getStem()) || state.isOf(this.getPlant())) {
                var newState = this.getPlant().getDefaultState();
                if (newState.contains(FACING)) {
                    newState = newState.with(FACING, direction);
                }
                return newState;
            }
        }
        var newState = this.getRandomGrowthState(ctx.getWorld().random);
        if (newState.contains(FACING)) {
            newState = newState.with(FACING, placementDirections[0]);
        }
        return newState;
    }

    public BlockState getRandomGrowthState(Random random) {
        return this.getDefaultState();
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        var facing = state.get(FACING);
        var blockPos = pos.offset(facing.getOpposite());
        var blockState = world.getBlockState(blockPos);
        return this.canAttachTo(blockState) &&
                (blockState.isOf(this.getStem()) ||
                        blockState.isOf(this.getPlant()) ||
                        blockState.isSideSolidFullSquare(world, blockPos, facing));
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    protected boolean canAttachTo(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.outlineShape;
    }

    protected abstract AbstractFacingPlantStemBlock getStem();

    protected abstract Block getPlant();
}
