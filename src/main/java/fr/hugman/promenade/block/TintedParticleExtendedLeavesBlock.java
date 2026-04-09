package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class TintedParticleExtendedLeavesBlock extends ExtendedLeavesBlock {
    public static final MapCodec<TintedParticleExtendedLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ExtraCodecs.floatRange(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter(tintedParticleLeavesBlock -> tintedParticleLeavesBlock.leafParticleChance),
            propertiesCodec()
    ).apply(instance, TintedParticleExtendedLeavesBlock::new));

    public TintedParticleExtendedLeavesBlock(float f, Properties settings) {
        super(f, settings);
    }

    @Override
    protected void spawnLeafParticle(Level world, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(world, pos, random, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, world.getClientLeafTintColor(pos)));
    }

    @Override
    public MapCodec<? extends TintedParticleExtendedLeavesBlock> codec() {
        return CODEC;
    }
}
