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
package fr.hugman.promenade.entity.variant;

import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import fr.hugman.promenade.Promenade;

public class PromenadeWolfVariants {
	public static final RegistryKey<WolfVariant> SHIBA_INU = of("shiba_inu");

	public static RegistryKey<WolfVariant> of(String path) {
		return RegistryKey.of(RegistryKeys.WOLF_VARIANT, Promenade.id(path));
	}
}
