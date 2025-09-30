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
package fr.hugman.promenade.data.provider;

import java.util.concurrent.CompletableFuture;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import fr.hugman.promenade.banner.PromenadeBannerPatternTags;
import fr.hugman.promenade.banner.PromenadeBannerPatterns;

public class PromenadeBannerPatternTagProvider extends FabricTagProvider<BannerPattern> {
	public PromenadeBannerPatternTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, RegistryKeys.BANNER_PATTERN, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		// Promenade
		builder(PromenadeBannerPatternTags.BOVINE_PATTERN_ITEM).add(PromenadeBannerPatterns.BOVINE);
	}
}