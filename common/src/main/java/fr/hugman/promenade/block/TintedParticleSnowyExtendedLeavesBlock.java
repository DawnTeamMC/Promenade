package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.LevelAccessor;

public class TintedParticleSnowyExtendedLeavesBlock extends ExtendedLeavesBlock {
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public TintedParticleSnowyExtendedLeavesBlock(float leafParticleChance, Properties settings) {
        super(leafParticleChance, settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BOTTOM, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BOTTOM);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) return null;
        BlockState stateBelow = context.getLevel().getBlockState(context.getClickedPos().below());
        return state.setValue(BOTTOM, !isSnow(stateBelow));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        BlockState stateBelow = world.getBlockState(pos.below());
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos).setValue(BOTTOM, !isSnow(stateBelow));
    }

    public static boolean isSnow(BlockState state) {
        return state.getBlock() instanceof TintedParticleSnowyExtendedLeavesBlock;
    }

    @Override
    protected void spawnLeafParticle(Level world, BlockPos pos, RandomSource random) {
        // 1.21.1 has no tinted leaf particles, like vanilla leaves
    }
}
