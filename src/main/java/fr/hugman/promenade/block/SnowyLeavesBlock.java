package fr.hugman.promenade.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

//TODO add Codec
public class SnowyLeavesBlock extends LeavesBlock {
    public static final BooleanProperty BOTTOM = Properties.BOTTOM;

    public SnowyLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BOTTOM, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BOTTOM);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = super.getPlacementState(context);
        if (state == null) return null;
        BlockState stateBelow = context.getWorld().getBlockState(context.getBlockPos().down());
        return state.with(BOTTOM, !isSnow(stateBelow));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState stateBelow = world.getBlockState(pos.down());
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(BOTTOM, !isSnow(stateBelow));
    }

    public static boolean isSnow(BlockState state) {
        return state.getBlock() instanceof SnowyLeavesBlock;
    }
}
