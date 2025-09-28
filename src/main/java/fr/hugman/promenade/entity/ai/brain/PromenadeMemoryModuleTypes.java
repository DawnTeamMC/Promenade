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
package fr.hugman.promenade.entity.ai.brain;

import java.util.Optional;

import com.mojang.serialization.Codec;

import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

import fr.hugman.promenade.Promenade;

public class PromenadeMemoryModuleTypes {
	public static final MemoryModuleType<Unit> FART_COOLDOWN = register("fart_cooldown", Unit.CODEC);

	private static <U> MemoryModuleType<U> register(String id, Codec<U> codec) {
		return Registry.register(Registries.MEMORY_MODULE_TYPE, Promenade.id(id), new MemoryModuleType<>(Optional.of(codec)));
	}

	private static <U> MemoryModuleType<U> register(String id) {
		return Registry.register(Registries.MEMORY_MODULE_TYPE, Promenade.id(id), new MemoryModuleType<>(Optional.empty()));
	}


}
