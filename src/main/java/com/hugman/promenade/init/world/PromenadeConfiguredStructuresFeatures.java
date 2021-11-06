package com.hugman.promenade.init.world;

import com.hugman.dawn.api.creator.ConfiguredStructureFeatureCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.PromenadeBundle;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class PromenadeConfiguredStructuresFeatures extends PromenadeBundle {
	public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> DARK_FOREST_WITCH_HUT = add(new ConfiguredStructureFeatureCreator<>("dark_forest_witch_hut", PromenadeStructureFeatures.WITCH_HUT.getStructure().configure(DefaultFeatureConfig.DEFAULT)));
	private static final PromenadeConfig.StructuresCategory CONFIG = Promenade.CONFIG.structures;

	public static void init() {

	}

	public static void addToGen() {
		if(CONFIG.witch_huts) {
			BiomeModifications.addStructure(biomeSelectionContext -> biomeSelectionContext.hasBuiltInFeature(ConfiguredFeatures.DARK_FOREST_VEGETATION_RED) || biomeSelectionContext.hasBuiltInFeature(ConfiguredFeatures.DARK_FOREST_VEGETATION_BROWN), key(PromenadeConfiguredStructuresFeatures.DARK_FOREST_WITCH_HUT));
		}
	}

	public static RegistryKey<ConfiguredStructureFeature<?, ?>> key(ConfiguredStructureFeature<?, ?> structureFeature) {
		return BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getKey(structureFeature).get();
	}
}
