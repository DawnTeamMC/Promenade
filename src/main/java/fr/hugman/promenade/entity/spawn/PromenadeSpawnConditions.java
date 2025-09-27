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
import fr.hugman.promenade.Promenade;
import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeSpawnConditions {
	public static void register() {
		of("chance", ChanceSpawnCondition.CODEC);
	}

	public static MapCodec<? extends SpawnCondition> of(String name, MapCodec<? extends SpawnCondition> codec) {
		return Registry.register(Registries.SPAWN_CONDITION_TYPE, Promenade.id(name), codec);
	}
}
