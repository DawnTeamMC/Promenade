package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.world.PromenadeConfiguredSurfaceBuilders;
import com.hugman.promenade.init.world.feature.PromenadeConfiguredFeatures;
import com.hugman.promenade.object.world.gen.biome.BiomeUtil;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

public class AmaranthBundle extends PromenadeBundle {
	public static void addToGen() {
		if(Promenade.CONFIG.biomes.dark_amaranth_forests) {
			TheEndBiomes.addHighlandsBiome(Biomes.TALL_DARK_AMARANTH_FOREST.getRegistryKey(), Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
			TheEndBiomes.addMidlandsBiome(Biomes.TALL_DARK_AMARANTH_FOREST.getRegistryKey(), Biomes.DARK_AMARANTH_FOREST.getRegistryKey(), Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
		}
	}

	public static class Features {
		public static class Configured {

		}

		public static class Placed {

		}
	}

	public static class Biomes {
		public static final BiomeCreator DARK_AMARANTH_FOREST = creator(new BiomeCreator("dark_amaranth_forest", createDarkAmaranthForest(false)));
		public static final BiomeCreator TALL_DARK_AMARANTH_FOREST = creator(new BiomeCreator("tall_dark_amaranth_forest", createDarkAmaranthForest(true)));

		public static Biome createDarkAmaranthForest(boolean isTall) {
			GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
			if(isTall) generationBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PromenadeConfiguredFeatures.OBSIDIAN_SPIKE);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, isTall ? PromenadeConfiguredFeatures.TALL_AMARANTH_FUNGI : PromenadeConfiguredFeatures.AMARANTH_FUNGI);
			generationBuilder.surfaceBuilder(PromenadeConfiguredSurfaceBuilders.AMARANTH_DYLIUM);
			generationBuilder.structureFeature(ConfiguredStructureFeatures.END_CITY);
			generationBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.END_GATEWAY);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadeConfiguredFeatures.AMARANTH_FOREST_VEGETATION);
			SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
			DefaultBiomeFeatures.addEndMobs(spawnSettings);
			spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 10, 3, 4));
			spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CAVE_SPIDER, 6, 1, 3));
			return BiomeUtil.createEndBiome(generationBuilder, spawnSettings);
		}
	}
}
