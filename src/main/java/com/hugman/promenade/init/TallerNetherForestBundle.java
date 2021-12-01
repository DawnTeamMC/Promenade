package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.world.PromenadeFeatures;
import com.hugman.promenade.object.world.gen.biome.BiomeUtil;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountMultilayerPlacementModifier;
import net.minecraft.world.gen.feature.*;

public class TallerNetherForestBundle extends PromenadeBundle {
	public static void addToGen() {
		if(Promenade.CONFIG.biomes.tall_nether_forests) {
			NetherBiomes.addNetherBiome(Biomes.TALL_CRIMSON_FOREST.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.4F, 0.0F, 0.8F, 0.0F, 0.4F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(Biomes.TALL_WARPED_FOREST.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.5F, 0.8F, 0.0F, 0.0F, 0.0F, 0.375F));
		}
	}

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<HugeFungusFeatureConfig, ?> TALL_CRIMSON_FUNGUS = add(new ConfiguredFeatureCreator<>("tall_crimson_fungus", PromenadeFeatures.TALL_HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(Blocks.CRIMSON_NYLIUM.getDefaultState(), Blocks.CRIMSON_STEM.getDefaultState(), Blocks.NETHER_WART_BLOCK.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), false))));
			public static final ConfiguredFeature<HugeFungusFeatureConfig, ?> TALL_WARPED_FUNGUS = add(new ConfiguredFeatureCreator<>("tall_warped_fungus", PromenadeFeatures.TALL_HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(Blocks.WARPED_NYLIUM.getDefaultState(), Blocks.WARPED_STEM.getDefaultState(), Blocks.WARPED_WART_BLOCK.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), false))));
		}

		public static class Placed {
			public static final PlacedFeature TALL_CRIMSON_FUNGI = PlacedFeatures.register("tall_crimson_fungi", Configured.TALL_CRIMSON_FUNGUS.withPlacement(CountMultilayerPlacementModifier.of(8), BiomePlacementModifier.of()));
			public static final PlacedFeature TALL_WARPED_FUNGI = PlacedFeatures.register("tall_warped_fungi", Configured.TALL_WARPED_FUNGUS.withPlacement(CountMultilayerPlacementModifier.of(8), BiomePlacementModifier.of()));
		}
	}

	public static class Biomes {
		public static final BiomeCreator TALL_CRIMSON_FOREST = creator(new BiomeCreator("tall_crimson_forest", createTallCrimsonForest()));
		public static final BiomeCreator TALL_WARPED_FOREST = creator(new BiomeCreator("tall_warped_forest", createTallWarpedForest()));

		public static Biome createTallCrimsonForest() {
			SpawnSettings spawnSettings = new SpawnSettings.Builder()
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 2, 4))
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 9, 3, 4))
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 3, 4))
					.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2))
					.build();
			GenerationSettings.Builder genBuilder = new GenerationSettings.Builder()
					.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.WEEPING_VINES)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.TALL_CRIMSON_FUNGI)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.CRIMSON_FOREST_VEGETATION);
			DefaultBiomeFeatures.addDefaultMushrooms(genBuilder);
			DefaultBiomeFeatures.addNetherMineables(genBuilder);
			BiomeEffects effects = BiomeUtil.genericEffectBuilder(2.0F)
					.fogColor(0x330303)
					.particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025f))
					.loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
					.moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0))
					.additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111))
					.music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST))
					.build();
			/* TODO
					.surfaceBuilder(ConfiguredSurfaceBuilders.CRIMSON_FOREST)
					.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER)
					.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE)
					.structureFeature(ConfiguredStructureFeatures.FORTRESS)
					.structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT)
			*/
			return new Biome.Builder()
					.precipitation(Biome.Precipitation.NONE)
					.category(Biome.Category.NETHER)
					.temperature(2.0f).downfall(0.0f)
					.effects(effects)
					.spawnSettings(spawnSettings)
					.generationSettings(genBuilder.build())
					.build();
		}

		public static Biome createTallWarpedForest() {
			SpawnSettings spawnSettings = new SpawnSettings.Builder()
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4))
					.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2))
					.spawnCost(EntityType.ENDERMAN, 1.0, 0.12)
					.build();
			GenerationSettings.Builder genBuilder = new GenerationSettings.Builder()
					.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
					.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.TALL_WARPED_FUNGI)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.WARPED_FOREST_VEGETATION)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.NETHER_SPROUTS)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.TWISTING_VINES);
			DefaultBiomeFeatures.addDefaultMushrooms(genBuilder);
			DefaultBiomeFeatures.addNetherMineables(genBuilder);
			BiomeEffects effects = BiomeUtil.genericEffectBuilder(2.0F)
					.fogColor(1705242)
					.particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428f))
					.loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
					.moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0))
					.additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111))
					.music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST))
					.build();
			return new Biome.Builder()
					.precipitation(Biome.Precipitation.NONE)
					.category(Biome.Category.NETHER)
					.temperature(2.0f)
					.downfall(0.0f)
					.effects(effects)
					.spawnSettings(spawnSettings)
					.generationSettings(genBuilder.build())
					.build();
		}
	}
}