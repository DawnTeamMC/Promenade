package fr.hugman.promenade.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class StarFragmentBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape DEFAULT_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 5, 5, 16, 11, 11),
            Block.createCuboidShape(5, 0, 5, 11, 16, 11),
            Block.createCuboidShape(5, 5, 0, 11, 11, 16)
    );
    private static final VoxelShape DEFAULT_SNEAKING_OUTLINE_SHAPE = VoxelShapes.fullCube();

    private final VoxelShape shape;
    private final VoxelShape sneakingOutlineShape;

    public StarFragmentBlock(Settings settings, VoxelShape shape, VoxelShape sneakingOutlineShape) {
        super(settings);
        this.shape = shape;
        this.sneakingOutlineShape = sneakingOutlineShape;
    }

    public static StarFragmentBlock of(MapColor mapColor) {
        return new StarFragmentBlock(
                Settings.create()
                        .item()
                        .mapColor(mapColor)
                        .sounds(BlockSoundGroup.LARGE_AMETHYST_BUD)
                        .luminance(state -> 15)
                        .pistonBehavior(PistonBehavior.DESTROY),
                DEFAULT_SHAPE,
                DEFAULT_SNEAKING_OUTLINE_SHAPE
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return context.isDescending() ? this.sneakingOutlineShape : this.shape;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.shape;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }


    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(WATERLOGGED, fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
