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

//TODO: add shears functionality
public abstract class AbstractFacingPlantPartBlock extends FacingBlock {
    public static final EnumProperty<Direction> FACING = Properties.FACING;

    protected final VoxelShape[] outlineShapes;
    protected final boolean tickWater;

    public AbstractFacingPlantPartBlock(
            Settings settings,
            VoxelShape[] outlineShapes,
            boolean tickWater
    ) {
        super(settings);
        this.outlineShapes = outlineShapes;
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
        //TODO: make it prefer existing stems if not sneaking
        //FIXME: the plant is being placed instead of the stem
        for (var direction : placementDirections) {
            var opposite = direction.getOpposite();
            var otherState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction));
            var newState = !otherState.isOf(this.getStem()) && !otherState.isOf(this.getPlant())
                    ? this.getRandomGrowthState(ctx.getWorld().random)
                    : this.getPlant().getDefaultState();
            if (newState.contains(FACING)) {
                newState = newState.with(FACING, opposite);
            }
            if (newState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return newState;
            }
        }
        return null;
    }

    public BlockState getRandomGrowthState(Random random) {
        return this.getDefaultState();
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        var facing = state.get(FACING);
        var blockPos = pos.offset(facing.getOpposite());
        var blockState = world.getBlockState(blockPos);
        if(!this.canAttachTo(blockState)) return false;
        if(blockState.isOf(this.getStem()) || blockState.isOf(this.getPlant())) {
            if(blockState.contains(FACING) && blockState.get(FACING) == facing) {
                return true;
            }
        }
        return blockState.isSideSolidFullSquare(world, blockPos, facing);
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
        return this.outlineShapes[state.get(FACING).getId()];
    }

    protected abstract AbstractFacingPlantStemBlock getStem();

    protected abstract Block getPlant();
}
