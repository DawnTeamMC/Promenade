package fr.hugman.promenade.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class DecoratedLeavesBlock extends LeavesBlock {
    public static final MapCodec<DecoratedLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.INT.fieldOf("bound").forGetter(block -> block.bound),
                    createSettingsCodec())
            .apply(instance, DecoratedLeavesBlock::new));

    private final int bound;
    private final ParticleEffect particle;

    public DecoratedLeavesBlock(Settings settings, int bound, ParticleEffect particle) {
        super(settings);
        this.bound = bound;
        this.particle = particle;
    }

    @Override
    public MapCodec<DecoratedLeavesBlock> getCodec() {
        return CODEC;
    }

    public DecoratedLeavesBlock(int bound, Settings settings) {
        this(settings, bound, ParticleTypes.CHERRY_LEAVES);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(this.bound) != 0) {
            return;
        }
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        if (isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
            return;
        }
        ParticleUtil.spawnParticle(world, pos, random, this.particle);
    }
}
