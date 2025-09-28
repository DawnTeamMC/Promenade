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
package fr.hugman.promenade.entity.spawn;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.util.dynamic.Codecs;

public record ChanceSpawnCondition(float chance) implements SpawnCondition {
	public static final MapCodec<ChanceSpawnCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codecs.rangedInclusiveFloat(0.0F, 1.0F).fieldOf("chance").forGetter(ChanceSpawnCondition::chance)
	).apply(instance, ChanceSpawnCondition::new));

	public boolean test(SpawnContext spawnContext) {
		return spawnContext.world().getRandom().nextFloat() < this.chance;
	}

	@Override
	public MapCodec<ChanceSpawnCondition> getCodec() {
		return CODEC;
	}
}
