package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class TintedParticleSnowyLeavesBlock extends SnowyLeavesBlock {
    public TintedParticleSnowyLeavesBlock(float f, Properties settings) {
        super(f, settings);
    }

    @Override
    protected void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random) {
        // 1.21.1 has no tinted leaf particles, like vanilla leaves
    }

}
