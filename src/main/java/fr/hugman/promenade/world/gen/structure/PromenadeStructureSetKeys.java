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
import net.minecraft.structure.StructureSet;

public class PromenadeStructureSetKeys {
	public static final RegistryKey<StructureSet> WITCH_HUTS = of("witch_huts");

	private static RegistryKey<StructureSet> of(String id) {
		return RegistryKey.of(RegistryKeys.STRUCTURE_SET, Promenade.id(id));
	}
}
