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

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import fr.hugman.promenade.Promenade;

public class PromenadePaintingVariants {
	public static final RegistryKey<PaintingVariant> OPTIMISM = of("optimism");
	public static final RegistryKey<PaintingVariant> NURTURE = of("nurture");

	public static RegistryKey<PaintingVariant> of(String path) {
		return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, Promenade.id(path));
	}
}
