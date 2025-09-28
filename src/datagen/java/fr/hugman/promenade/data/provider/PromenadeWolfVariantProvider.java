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

import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.AssetInfo.TextureAssetInfo;
import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import fr.hugman.promenade.entity.variant.PromenadeWolfVariants;
import fr.hugman.promenade.tag.PromenadeBiomeTags;

public class PromenadeWolfVariantProvider extends FabricDynamicRegistryProvider {
	public PromenadeWolfVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
		var wrapper = registries.getOrThrow(RegistryKeys.WOLF_VARIANT);
		entries.add(wrapper, PromenadeWolfVariants.SHIBA_INU);
	}

	@Override
	public String getName() {
		return "Wolf Variants";
	}

	public static void register(Registerable<WolfVariant> registry) {
		of(registry, PromenadeWolfVariants.SHIBA_INU, PromenadeBiomeTags.SAKURA_GROVES);
	}

	private static void of(Registerable<WolfVariant> registry, RegistryKey<WolfVariant> key, TagKey<Biome> biomeTag) {
		of(registry, key, DataProviderUtil.createSpawnConditions(registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag)));
	}

	private static void of(Registerable<WolfVariant> registry, RegistryKey<WolfVariant> key, SpawnConditionSelectors spawnConditions) {
		var baseId = key.getValue().withPrefixedPath("entity/wolf/");
		registry.register(key, new WolfVariant(
				new WolfVariant.WolfAssetInfo(
						new TextureAssetInfo(baseId.withSuffixedPath("/wild")),
						new TextureAssetInfo(baseId.withSuffixedPath("/tame")),
						new TextureAssetInfo(baseId.withSuffixedPath("/angry"))
				),
				spawnConditions
		));
	}
}