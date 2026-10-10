package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class TintedParticleSnowyLeavesBlock extends SnowyLeavesBlock {
    public TintedParticleSnowyLeavesBlock(float f, Properties settings) {
        super(f, settings);
    }

    @Override
    protected void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(world, pos, random, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, world.getClientLeafTintColor(pos)));
    }

}
