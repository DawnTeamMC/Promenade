package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.block.property.PromenadeBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class MoaiBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<MoaiBlock> CODEC = simpleCodec(MoaiBlock::new);
    public static final EnumProperty<MoaiType> TYPE = PromenadeBlockProperties.MOAI_TYPE;

    public MoaiBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, MoaiType.SINGLE).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TYPE, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var world = context.getLevel();
        boolean sneaking = context.isSecondaryUseActive(); // stupid yarn name
        var direction = context.getHorizontalDirection().getOpposite();
        var hitSide = context.getClickedFace();

        var posBelow = context.getClickedPos().below();
        var posAbove = context.getClickedPos().above();

        boolean mergeWithBelow = isSingleMoai(direction, world.getBlockState(posBelow));
        boolean mergeWithAbove = isSingleMoai(direction, world.getBlockState(posAbove));

        if (sneaking) {
            mergeWithBelow = false;
            mergeWithAbove = false;
        }

        if (mergeWithBelow && mergeWithAbove) {
            if (hitSide == Direction.DOWN) {
                mergeWithBelow = false;
            } else if (hitSide == Direction.UP) {
                mergeWithAbove = false;
            }
        }

        if (mergeWithBelow) {
            return this.defaultBlockState().setValue(TYPE, MoaiType.TOP).setValue(FACING, direction);
        } else if (mergeWithAbove) {
            return this.defaultBlockState().setValue(TYPE, MoaiType.BOTTOM).setValue(FACING, direction);
        } else {
            return this.defaultBlockState().setValue(TYPE, MoaiType.SINGLE).setValue(FACING, direction);
        }
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.setPlacedBy(world, pos, state, placer, itemStack);
        var type = state.getValue(TYPE);
        var direction = state.getValue(FACING);
        var posBelow = pos.below();
        var posAbove = pos.above();

        if (isSingleMoai(direction, world.getBlockState(posBelow)) && type == MoaiType.TOP) {
            world.setBlock(posBelow, world.getBlockState(posBelow).setValue(TYPE, MoaiType.BOTTOM), Block.UPDATE_ALL);
        } else if (isSingleMoai(direction, world.getBlockState(posAbove)) && type == MoaiType.BOTTOM) {
            world.setBlock(posAbove, world.getBlockState(posAbove).setValue(TYPE, MoaiType.TOP), Block.UPDATE_ALL);
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        var type = state.getValue(TYPE);
        if ((type == MoaiType.TOP && direction == Direction.DOWN) || (type == MoaiType.BOTTOM && direction == Direction.UP)) {
            if (!(neighborState.is(this) && neighborState.getValue(TYPE) == (type == MoaiType.TOP ? MoaiType.BOTTOM : MoaiType.TOP))) {
                return state.setValue(TYPE, MoaiType.SINGLE);
            }
        }
        return state;
    }

    public boolean isSingleMoai(Direction direction, BlockState state) {
        return state.is(this) && state.getValue(TYPE) == MoaiType.SINGLE && state.getValue(FACING) == direction;
    }
}
