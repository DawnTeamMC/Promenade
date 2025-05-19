package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class TintedParticleSnowyLeavesBlock extends SnowyLeavesBlock {
    public static final MapCodec<TintedParticleSnowyLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.rangedInclusiveFloat(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter(tintedParticleLeavesBlock -> tintedParticleLeavesBlock.leafParticleChance),
            createSettingsCodec()
    ).apply(instance, TintedParticleSnowyLeavesBlock::new));

    public TintedParticleSnowyLeavesBlock(float f, Settings settings) {
        super(f, settings);
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        EntityEffectParticleEffect entityEffectParticleEffect = EntityEffectParticleEffect.create(ParticleTypes.TINTED_LEAVES, world.getBlockColor(pos));
        ParticleUtil.spawnParticle(world, pos, random, entityEffectParticleEffect);
    }

    @Override
    public MapCodec<? extends TintedParticleSnowyLeavesBlock> getCodec() {
        return CODEC;
    }
}
