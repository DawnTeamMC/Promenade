package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.LevelAccessor;

public class UntintedParticleSnowyExtendedLeavesBlock extends ExtendedLeavesBlock {
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    protected final ParticleOptions leafParticleEffect;

    public UntintedParticleSnowyExtendedLeavesBlock(float leafParticleChance, ParticleOptions leafParticleEffect, Properties settings) {
        super(leafParticleChance, settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BOTTOM, false));
        this.leafParticleEffect = leafParticleEffect;
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
        return state.getBlock() instanceof UntintedParticleSnowyExtendedLeavesBlock;
    }

    @Override
    protected void spawnLeafParticle(Level world, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(world, pos, random, this.leafParticleEffect);
    }
}
