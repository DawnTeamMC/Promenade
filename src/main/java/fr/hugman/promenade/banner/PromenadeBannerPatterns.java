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
package fr.hugman.promenade.banner;

import fr.hugman.promenade.Promenade;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeBannerPatterns {
	public static final RegistryKey<BannerPattern> BOVINE = of("bovine");

	private static RegistryKey<BannerPattern> of(String path) {
		return RegistryKey.of(RegistryKeys.BANNER_PATTERN, Promenade.id(path));
	}
}
