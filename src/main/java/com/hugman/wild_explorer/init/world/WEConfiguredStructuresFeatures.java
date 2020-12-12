package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.ConfiguredStructureFeatureCreator;
import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.config.WEConfig;
import com.hugman.wild_explorer.init.WEPack;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class WEConfiguredStructuresFeatures extends WEPack {
	public static final ConfiguredStructureFeature<?, ?> DARK_FOREST_WITCH_HUT = register(new ConfiguredStructureFeatureCreator.Builder<>("dark_forest_witch_hut", WEStructureFeatures.WITCH_HUT.configure(DefaultFeatureConfig.DEFAULT)));
	private static final WEConfig.StructuresCategory CONFIG = WildExplorer.CONFIG.structures;

	public static void init() {

	}

	public static void addToGen() {
		if(CONFIG.witch_huts) {
			BiomeModifications.addStructure(biomeSelectionContext -> biomeSelectionContext.hasBuiltInFeature(ConfiguredFeatures.DARK_FOREST_VEGETATION_RED) || biomeSelectionContext.hasBuiltInFeature(ConfiguredFeatures.DARK_FOREST_VEGETATION_BROWN), getId(WEConfiguredStructuresFeatures.DARK_FOREST_WITCH_HUT));
		}
	}

	public static RegistryKey<ConfiguredStructureFeature<?, ?>> getId(ConfiguredStructureFeature<?, ?> structureFeature) {
		return RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(structureFeature));
	}
}
