package com.hugman.promenade.object.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PlantPileBlock extends PlantBlock
{
	protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

	public PlantPileBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !stateIn.canPlaceAt(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockPos blockpos = pos.offset(Direction.DOWN);
		BlockState blockState = world.getBlockState(blockpos);
		return Block.isFaceFullSquare(blockState.getCollisionShape(world, blockpos), Direction.UP);
	}
}