package fr.hugman.promenade.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

//TODO: codec
public class StarBitsBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<Direction> FACING = Properties.FACING;

    private final VoxelShape upShape;
    private final VoxelShape downShape;
    private final VoxelShape eastShape;
    private final VoxelShape westShape;
    private final VoxelShape southShape;
    private final VoxelShape northShape;

    public StarBitsBlock(Settings settings, VoxelShape upShape, VoxelShape downShape, VoxelShape eastShape, VoxelShape westShape, VoxelShape southShape, VoxelShape northShape) {
        super(settings);

        this.upShape = upShape;
        this.downShape = downShape;
        this.eastShape = eastShape;
        this.westShape = westShape;
        this.southShape = southShape;
        this.northShape = northShape;
    }

    // TODO: move this to a separate class
    public StarBitsBlock(Settings settings) {
        this(settings,
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),
                Block.createCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 1.0, 16.0, 16.0),
                Block.createCuboidShape(15.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
                Block.createCuboidShape(0.0, 0.0, 15.0, 16.0, 16.0, 16.0)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return switch (direction) {
            case NORTH -> this.northShape;
            case SOUTH -> this.southShape;
            case EAST -> this.eastShape;
            case WEST -> this.westShape;
            case DOWN -> this.downShape;
            default -> this.upShape;
        };
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState placeOn = world.getBlockState(blockPos);
        return placeOn.isIn(BlockTags.LEAVES) || SideShapeType.FULL.matches(placeOn, world, pos, direction);
    }


    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == state.get(FACING).getOpposite() && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }


    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        return this.getDefaultState().with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER).with(FACING, ctx.getSide());
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }
}
