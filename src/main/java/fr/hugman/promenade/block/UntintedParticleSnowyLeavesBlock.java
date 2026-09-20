package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class UntintedParticleSnowyLeavesBlock extends SnowyLeavesBlock {
    protected final ParticleOptions leafParticleEffect;

    public UntintedParticleSnowyLeavesBlock(float leafParticleChance, ParticleOptions leafParticleEffect, Properties settings) {
        super(leafParticleChance, settings);
        this.leafParticleEffect = leafParticleEffect;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(world, pos, random, this.leafParticleEffect);
    }

}
