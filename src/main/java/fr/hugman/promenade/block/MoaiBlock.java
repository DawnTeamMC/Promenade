package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.state.property.PromenadeBlockProperties;
import net.minecraft.block.BigDripleafBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class MoaiBlock extends HorizontalFacingBlock {
	public static final MapCodec<MoaiBlock> CODEC = createCodec(MoaiBlock::new);
	public static final EnumProperty<MoaiType> TYPE = PromenadeBlockProperties.MOAI_TYPE;

	public MoaiBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(TYPE, MoaiType.SINGLE).with(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(TYPE, FACING);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		var world = context.getWorld();
		boolean sneaking = context.shouldCancelInteraction(); // stupid yarn name
		var direction = context.getHorizontalPlayerFacing().getOpposite();
		var hitSide = context.getSide();

		var posBelow = context.getBlockPos().down();
		var posAbove = context.getBlockPos().up();

		boolean mergeWithBelow = isSingleMoai(direction, world.getBlockState(posBelow));
		boolean mergeWithAbove = isSingleMoai(direction, world.getBlockState(posAbove));

		if(sneaking) {
			mergeWithBelow = false;
			mergeWithAbove = false;
		}

		if(mergeWithBelow && mergeWithAbove) {
			if(hitSide == Direction.DOWN) {
				mergeWithBelow = false;
			}
			else if(hitSide == Direction.UP) {
				mergeWithAbove = false;
			}
		}

		if(mergeWithBelow) {
			return this.getDefaultState().with(TYPE, MoaiType.TOP).with(FACING, direction);
		}
		else if(mergeWithAbove) {
			return this.getDefaultState().with(TYPE, MoaiType.BOTTOM).with(FACING, direction);
		}
		else {
			return this.getDefaultState().with(TYPE, MoaiType.SINGLE).with(FACING, direction);
		}
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		var type = state.get(TYPE);
		var direction = state.get(FACING);
		var posBelow = pos.down();
		var posAbove = pos.up();

		if(isSingleMoai(direction, world.getBlockState(posBelow)) && type == MoaiType.TOP) {
			world.setBlockState(posBelow, world.getBlockState(posBelow).with(TYPE, MoaiType.BOTTOM), Block.NOTIFY_ALL);
		}
		else if(isSingleMoai(direction, world.getBlockState(posAbove)) && type == MoaiType.BOTTOM) {
			world.setBlockState(posAbove, world.getBlockState(posAbove).with(TYPE, MoaiType.TOP), Block.NOTIFY_ALL);
		}
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		var type = state.get(TYPE);
		if((type == MoaiType.TOP && direction == Direction.DOWN) || (type == MoaiType.BOTTOM && direction == Direction.UP)) {
			if(!(neighborState.isOf(this) && neighborState.get(TYPE) == (type == MoaiType.TOP ? MoaiType.BOTTOM : MoaiType.TOP))) {
				return state.with(TYPE, MoaiType.SINGLE);
			}
		}
		return state;
	}

	public boolean isSingleMoai(Direction direction, BlockState state) {
		return state.isOf(this) && state.get(TYPE) == MoaiType.SINGLE && state.get(FACING) == direction;
	}
}
