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

import fr.hugman.promenade.banner.PromenadeBannerPatterns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class PromenadeBannerPatternProvider extends FabricDynamicRegistryProvider {
	public PromenadeBannerPatternProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
		entries.addAll(registries.getOrThrow(RegistryKeys.BANNER_PATTERN));
	}

	@Override
	public String getName() {
		return "Banner Patterns";
	}

	public static void register(Registerable<BannerPattern> registerable) {
		of(registerable, PromenadeBannerPatterns.BOVINE);
	}

	public static void of(Registerable<BannerPattern> registerable, RegistryKey<BannerPattern> key) {
		registerable.register(key, new BannerPattern(key.getValue(), "block.promenade.banner." + key.getValue().getPath()));
	}

}