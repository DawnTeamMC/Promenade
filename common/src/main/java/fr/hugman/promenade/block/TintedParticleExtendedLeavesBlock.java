package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class TintedParticleExtendedLeavesBlock extends ExtendedLeavesBlock {
    public TintedParticleExtendedLeavesBlock(float f, Properties settings) {
        super(f, settings);
    }

    @Override
    protected void spawnLeafParticle(Level world, BlockPos pos, RandomSource random) {
        // 1.21.1 has no tinted leaf particles, like vanilla leaves
    }

}
