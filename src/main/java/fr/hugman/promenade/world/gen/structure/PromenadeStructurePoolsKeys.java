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
import net.minecraft.structure.pool.StructurePool;

public class PromenadeStructurePoolsKeys {
	public static final RegistryKey<StructurePool> DARK_FOREST_WITCH_HUTS = of("dark_forest_witch_huts");
	public static final RegistryKey<StructurePool> WITCH_HUT_INTERIORS = of("witch_huts_interiors");

	private static RegistryKey<StructurePool> of(String id) {
		return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, Promenade.id(id));
	}
}
