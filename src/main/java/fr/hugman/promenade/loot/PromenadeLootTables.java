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
package fr.hugman.promenade.loot;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import fr.hugman.promenade.Promenade;

public class PromenadeLootTables {
	public static final RegistryKey<LootTable> TUBE_SUNKEN = of("entities/sunken/tube");
	public static final RegistryKey<LootTable> BRAIN_SUNKEN = of("entities/sunken/brain");
	public static final RegistryKey<LootTable> BUBBLE_SUNKEN = of("entities/sunken/bubble");
	public static final RegistryKey<LootTable> FIRE_SUNKEN = of("entities/sunken/fire");
	public static final RegistryKey<LootTable> HORN_SUNKEN = of("entities/sunken/horn");

	public static final RegistryKey<LootTable> WITCH_HUT_CHEST = of("chests/witch_hut");


	private static RegistryKey<LootTable> of(String path) {
		return RegistryKey.of(RegistryKeys.LOOT_TABLE, Promenade.id(path));
	}
}
