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
package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.snowy.SnowyBlockTransformation;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class PromenadeRegistries {
	public static void register() {
		DynamicRegistries.registerSynced(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION, SnowyBlockTransformation.CODEC);

		DynamicRegistries.registerSynced(PromenadeRegistryKeys.DUCK_VARIANT, DuckVariant.CODEC, DuckVariant.NETWORK_CODEC);
		DynamicRegistries.registerSynced(PromenadeRegistryKeys.CAPYBARA_VARIANT, CapybaraVariant.CODEC, CapybaraVariant.NETWORK_CODEC);
		DynamicRegistries.registerSynced(PromenadeRegistryKeys.SUNKEN_VARIANT, SunkenVariant.CODEC, SunkenVariant.NETWORK_CODEC);
	}
}
