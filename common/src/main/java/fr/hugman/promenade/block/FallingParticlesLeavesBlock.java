package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Leaves that let particles fall below them. Newer versions of Minecraft ship this class; 1.21.1 only has cherry leaves.
 */
public abstract class FallingParticlesLeavesBlock extends LeavesBlock {
    protected final float leafParticleChance;

    public FallingParticlesLeavesBlock(float leafParticleChance, Properties settings) {
        super(settings);
        this.leafParticleChance = leafParticleChance;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);
        BlockPos below = pos.below();
        BlockState belowState = world.getBlockState(below);
        if (random.nextFloat() < this.leafParticleChance && !isFaceFull(belowState.getCollisionShape(world, below), Direction.UP)) {
            this.spawnFallingLeavesParticle(world, pos, random);
        }
    }

    protected abstract void spawnFallingLeavesParticle(Level world, BlockPos pos, RandomSource random);
}
