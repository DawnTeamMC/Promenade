package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class TintedParticleSnowyExtendedLeavesBlock extends ExtendedLeavesBlock {
    public static final MapCodec<TintedParticleSnowyExtendedLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ExtraCodecs.floatRange(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter(untintedParticleLeavesBlock -> untintedParticleLeavesBlock.leafParticleChance),
            propertiesCodec()
    ).apply(instance, TintedParticleSnowyExtendedLeavesBlock::new));
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public TintedParticleSnowyExtendedLeavesBlock(float leafParticleChance, Properties settings) {
        super(leafParticleChance, settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BOTTOM, false));
    }

    @Override
    public MapCodec<? extends TintedParticleSnowyExtendedLeavesBlock> codec() {
        return CODEC;
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
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        BlockState stateBelow = world.getBlockState(pos.below());
        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random).setValue(BOTTOM, !isSnow(stateBelow));
    }

    public static boolean isSnow(BlockState state) {
        return state.getBlock() instanceof TintedParticleSnowyExtendedLeavesBlock;
    }

    @Override
    protected void spawnLeafParticle(Level world, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(world, pos, random, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, world.getClientLeafTintColor(pos)));
    }
}
