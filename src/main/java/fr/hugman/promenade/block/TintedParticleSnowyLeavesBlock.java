/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
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
		ParticleUtil.spawnParticle(world, pos, random, TintedParticleEffect.create(ParticleTypes.TINTED_LEAVES, world.getBlockColor(pos)));
	}

	@Override
	public MapCodec<? extends TintedParticleSnowyLeavesBlock> getCodec() {
		return CODEC;
	}
}
