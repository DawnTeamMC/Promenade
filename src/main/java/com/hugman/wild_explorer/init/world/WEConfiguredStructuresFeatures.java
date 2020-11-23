package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.ConfiguredStructureFeatureCreator;
import com.hugman.wild_explorer.init.WEPack;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class WEConfiguredStructuresFeatures extends WEPack {
	public static final ConfiguredStructureFeature<?, ?> ELDER_VILLAGE = register(new ConfiguredStructureFeatureCreator.Builder<>("elder_village", WEStructureFeatures.ELDER_VILLAGE.configure(DefaultFeatureConfig.DEFAULT)));

	public static void init() {

	}

	public static void addToGen() {
		BiomeModifications.addStructure(BiomeSelectors.foundInTheEnd(), getId(WEConfiguredStructuresFeatures.ELDER_VILLAGE));
	}

	public static RegistryKey<ConfiguredStructureFeature<?, ?>> getId(ConfiguredStructureFeature<?, ?> structureFeature) {
		return RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(structureFeature));
	}
}
