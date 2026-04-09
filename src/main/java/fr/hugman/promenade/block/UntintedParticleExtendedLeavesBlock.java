package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class UntintedParticleExtendedLeavesBlock extends ExtendedLeavesBlock {
    public static final MapCodec<UntintedParticleExtendedLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            ExtraCodecs.floatRange(0.0F, 1.0F)
                                    .fieldOf("leaf_particle_chance")
                                    .forGetter(untintedParticleLeavesBlock -> untintedParticleLeavesBlock.leafParticleChance),
                            ParticleTypes.CODEC.fieldOf("leaf_particle").forGetter(untintedParticleLeavesBlock -> untintedParticleLeavesBlock.leafParticleEffect),
                            propertiesCodec()
                    )
                    .apply(instance, UntintedParticleExtendedLeavesBlock::new)
    );
    protected final ParticleOptions leafParticleEffect;

    public UntintedParticleExtendedLeavesBlock(float leafParticleChance, ParticleOptions leafParticleEffect, Properties settings) {
        super(leafParticleChance, settings);
        this.leafParticleEffect = leafParticleEffect;
    }

    @Override
    protected void spawnLeafParticle(Level world, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(world, pos, random, this.leafParticleEffect);
    }

    @Override
    public MapCodec<UntintedParticleExtendedLeavesBlock> codec() {
        return CODEC;
    }
}
