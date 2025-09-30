package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class TintedParticleExtendedLeavesBlock extends ExtendedLeavesBlock {
    public static final MapCodec<TintedParticleExtendedLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.rangedInclusiveFloat(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter(tintedParticleLeavesBlock -> tintedParticleLeavesBlock.leafParticleChance),
            createSettingsCodec()
    ).apply(instance, TintedParticleExtendedLeavesBlock::new));

    public TintedParticleExtendedLeavesBlock(float f, Settings settings) {
        super(f, settings);
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        ParticleUtil.spawnParticle(world, pos, random, TintedParticleEffect.create(ParticleTypes.TINTED_LEAVES, world.getBlockColor(pos)));
    }

    @Override
    public MapCodec<? extends TintedParticleExtendedLeavesBlock> getCodec() {
        return CODEC;
    }
}
