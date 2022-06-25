package com.hugman.promenade.util;

import com.hugman.promenade.Promenade;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.jetbrains.annotations.Nullable;

public final class WorldGenUtil
{
	public static RegistryKey<Biome> biomeKey(String name) {
		return RegistryKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id(name));
	}

	public static RegistryKey<PlacedFeature> placedFeatureKey(String name) {
		return RegistryKey.of(Registry.PLACED_FEATURE_KEY, Promenade.MOD_DATA.id(name));
	}

	public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String name) {
		return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, Promenade.MOD_DATA.id(name));
	}

	@Nullable
	public static RegistryEntry<? extends ConfiguredFeature<?, ?>> treeEntryWithFancyBees(ServerWorld world, Random random, boolean bees, RegistryKey<ConfiguredFeature<?, ?>> keyNormal, RegistryKey<ConfiguredFeature<?, ?>> keyNormalBees, RegistryKey<ConfiguredFeature<?, ?>> keyFancy, RegistryKey<ConfiguredFeature<?, ?>> keyFancyBees) {
		Registry<ConfiguredFeature<?, ?>> registry = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY);
		RegistryEntry<ConfiguredFeature<?, ?>> normal = registry.getEntry(keyNormal).orElse(null);
		RegistryEntry<ConfiguredFeature<?, ?>> normalBees = registry.getEntry(keyNormalBees).orElse(normal);
		RegistryEntry<ConfiguredFeature<?, ?>> fancy = registry.getEntry(keyFancy).orElse(normal);
		RegistryEntry<ConfiguredFeature<?, ?>> fancyBees = registry.getEntry(keyFancyBees).orElse(fancy);

		return random.nextInt(10) == 0 ? bees ? fancyBees : fancy : bees ? normalBees : normal;
	}
}
