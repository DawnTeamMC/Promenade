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
package fr.hugman.promenade.world.gen.structure;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.structure.Structure;

public class PromenadeStructureKeys {
	public static final RegistryKey<Structure> DARK_FOREST_WITCH_HUT = of("dark_forest_witch_hut");

	private static RegistryKey<Structure> of(String id) {
		return RegistryKey.of(RegistryKeys.STRUCTURE, Promenade.id(id));
	}
}
