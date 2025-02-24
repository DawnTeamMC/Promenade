package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class SnowyLeavesBlock extends LeavesBlock {
    public static final MapCodec<SnowyLeavesBlock> CODEC = createCodec(SnowyLeavesBlock::new);
    public static final BooleanProperty BOTTOM = Properties.BOTTOM;

    public SnowyLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(BOTTOM, false));
    }

    @Override
    public MapCodec<? extends SnowyLeavesBlock> getCodec() {
        return CODEC;
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
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        BlockState stateBelow = world.getBlockState(pos.down());
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random).with(BOTTOM, !isSnow(stateBelow));
    }

    public static boolean isSnow(BlockState state) {
        return state.getBlock() instanceof SnowyLeavesBlock;
    }
}
