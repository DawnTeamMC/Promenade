package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.util.BiomeUtil;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

import static com.hugman.promenade.init.MushroomBundle.Features.Configured.*;

public class GalleryBundle extends PromenadeBundle {
	public static void addToGen() {
		if(Promenade.CONFIG.biomes.nether_galleries) {
			NetherBiomes.addNetherBiome(Biomes.TRITANOPIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(Biomes.ACHROMATOPSIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, -0.4F, 0.0F, 0.1F, 0.0F));
			NetherBiomes.addNetherBiome(Biomes.PROTANOPIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2F, 0.0F));
		}
	}

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<?, ?> NORMAL_TRITANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/normal", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK::withPlacement, HUGE_NETHER_MUSHROOM_CYAN::withPlacement))));
			public static final ConfiguredFeature<?, ?> FLAT_TRITANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_CYAN_FLAT::withPlacement))));
			public static final ConfiguredFeature<?, ?> CEILING_TRITANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/ceiling", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK_UPSIDE::withPlacement, HUGE_NETHER_MUSHROOM_CYAN_UPSIDE::withPlacement))));
			public static final ConfiguredFeature<?, ?> CEILING_FLAT_TRITANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/ceiling_flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK_UPSIDE_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT::withPlacement))));

			public static final ConfiguredFeature<?, ?> NORMAL_ACHROMATOPSIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/normal", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_GRAY::withPlacement, HUGE_NETHER_MUSHROOM_BLACK::withPlacement))));
			public static final ConfiguredFeature<?, ?> FLAT_ACHROMATOPSIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_GRAY_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_BLACK_FLAT::withPlacement))));
			public static final ConfiguredFeature<?, ?> CEILING_ACHROMATOPSIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/ceiling", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_WHITE_UPSIDE::withPlacement, HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE::withPlacement))));
			public static final ConfiguredFeature<?, ?> CEILING_FLAT_ACHROMATOPSIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/ceiling_flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_WHITE_UPSIDE_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE_FLAT::withPlacement))));

			public static final ConfiguredFeature<?, ?> NORMAL_PROTANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/normal", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_BROWN_FLAT.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN.withPlacement(), 0.25F)), HUGE_NETHER_MUSHROOM_BLUE.withPlacement()))));
			public static final ConfiguredFeature<?, ?> FLAT_PROTANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/flat", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_BROWN_UPSIDE_FLAT.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN_UPSIDE.withPlacement(), 0.25F)), HUGE_NETHER_MUSHROOM_BLUE_UPSIDE.withPlacement()))));
			public static final ConfiguredFeature<?, ?> CEILING_PROTANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/ceiling", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW_FLAT.withPlacement(), 1F / 3F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN_FLAT.withPlacement(), 1F / 3F)), HUGE_NETHER_MUSHROOM_BLUE_FLAT.withPlacement()))));
			public static final ConfiguredFeature<?, ?> CEILING_FLAT_PROTANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/ceiling_flat", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE_FLAT.withPlacement(), 1F / 3F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT.withPlacement(), 1F / 3F)), HUGE_NETHER_MUSHROOM_BLUE_UPSIDE_FLAT.withPlacement()))));

			public static final ConfiguredFeature<?, ?> TRITANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(NORMAL_TRITANOPIAN_HUGE_MUSHROOM.withPlacement(), 0.25F), new RandomFeatureEntry(FLAT_TRITANOPIAN_HUGE_MUSHROOM.withPlacement(), 0.25F), new RandomFeatureEntry(CEILING_TRITANOPIAN_HUGE_MUSHROOM.withPlacement(), 0.25F)), CEILING_FLAT_TRITANOPIAN_HUGE_MUSHROOM.withPlacement()))));
			public static final ConfiguredFeature<?, ?> ACHROMATOPSIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(NORMAL_ACHROMATOPSIAN_HUGE_MUSHROOM.withPlacement(), 0.25F), new RandomFeatureEntry(FLAT_ACHROMATOPSIAN_HUGE_MUSHROOM.withPlacement(), 0.25F), new RandomFeatureEntry(CEILING_ACHROMATOPSIAN_HUGE_MUSHROOM.withPlacement(), 0.25F)), CEILING_FLAT_ACHROMATOPSIAN_HUGE_MUSHROOM.withPlacement()))));
			public static final ConfiguredFeature<?, ?> PROTANOPIAN_HUGE_MUSHROOM = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(NORMAL_PROTANOPIAN_HUGE_MUSHROOM.withPlacement(), 0.25F), new RandomFeatureEntry(FLAT_PROTANOPIAN_HUGE_MUSHROOM.withPlacement(), 0.25F), new RandomFeatureEntry(CEILING_PROTANOPIAN_HUGE_MUSHROOM.withPlacement(), 0.25F)), CEILING_FLAT_PROTANOPIAN_HUGE_MUSHROOM.withPlacement()))));
		}

		public static class Placed {
			private static final List<PlacementModifier> PLACEMENT_MODIFIERS = List.of(CountPlacementModifier.of(16), SquarePlacementModifier.of(), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

			public static final PlacedFeature TRITANOPIAN_HUGE_MUSHROOMS = add(new PlacedFeatureCreator("huge_nether_mushrooms/tritanopian", Configured.TRITANOPIAN_HUGE_MUSHROOM.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature ACHROMATOPSIAN_HUGE_MUSHROOMS = add(new PlacedFeatureCreator("huge_nether_mushrooms/achromatopsian", Configured.ACHROMATOPSIAN_HUGE_MUSHROOM.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature PROTANOPIAN_HUGE_MUSHROOMS = add(new PlacedFeatureCreator("huge_nether_mushrooms/protanopian", Configured.PROTANOPIAN_HUGE_MUSHROOM.withPlacement(PLACEMENT_MODIFIERS)));
		}
	}

	public static class Biomes {
		public static final BiomeCreator TRITANOPIAN_GALLERY = creator(new BiomeCreator("tritanopian_gallery", createGallery(composeTritanopianSettings())));
		public static final BiomeCreator ACHROMATOPSIAN_GALLERY = creator(new BiomeCreator("achromatopsian_gallery", createGallery(composeAchromatopsianSettings())));
		public static final BiomeCreator PROTANOPIAN_GALLERY = creator(new BiomeCreator("protanopian_gallery", createGallery(composeProtanopianSettings())));

		public static Biome createGallery(GenerationSettings.Builder genBuilder) {
			SpawnSettings spawnSettings = new SpawnSettings.Builder()
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4))
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 100, 4, 4))
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 4, 4))
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4))
					.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 4, 4))
					.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2))
					.build();
			/* TODO
				genBuilder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER);
				genBuilder.structureFeature(ConfiguredStructureFeatures.FORTRESS);
				genBuilder.structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT);
			*/
			BiomeEffects effects = BiomeUtil.genericEffectBuilder(2.0F)
					.fogColor(0x330808)
					.loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
					.moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0))
					.additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
					.music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES))
					.build();
			return new Biome.Builder()
					.precipitation(Biome.Precipitation.NONE)
					.category(Biome.Category.NETHER)
					.temperature(2.0F)
					.downfall(0.0F)
					.effects(effects)
					.spawnSettings(spawnSettings)
					.generationSettings(genBuilder.build())
					.build();
		}

		public static GenerationSettings.Builder composeTritanopianSettings() {
			GenerationSettings.Builder builder = new GenerationSettings.Builder();

			builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.SPRING_OPEN);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.TRITANOPIAN_HUGE_MUSHROOMS);
			addUndergroundDecoration(builder);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.PINK_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.CYAN_MUSHROOM_NETHER_PATCH);
			DefaultBiomeFeatures.addNetherMineables(builder);

			return builder;
		}

		public static GenerationSettings.Builder composeAchromatopsianSettings() {
			GenerationSettings.Builder builder = new GenerationSettings.Builder();

			builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.SPRING_OPEN);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.ACHROMATOPSIAN_HUGE_MUSHROOMS);
			addUndergroundDecoration(builder);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.WHITE_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.LIGHT_GRAY_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.GRAY_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.BLACK_MUSHROOM_NETHER_PATCH);
			DefaultBiomeFeatures.addNetherMineables(builder);

			return builder;
		}

		public static GenerationSettings.Builder composeProtanopianSettings() {
			GenerationSettings.Builder builder = new GenerationSettings.Builder();

			builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.SPRING_OPEN);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PROTANOPIAN_HUGE_MUSHROOMS);
			addUndergroundDecoration(builder, true);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.YELLOW_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.CYAN_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.BLUE_MUSHROOM_NETHER_PATCH);
			DefaultBiomeFeatures.addNetherMineables(builder);

			return builder;
		}

		private static void addUndergroundDecoration(GenerationSettings.Builder builder) {
			addUndergroundDecoration(builder, false);
		}

		private static void addUndergroundDecoration(GenerationSettings.Builder builder, boolean hasBrownMushrooms) {
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE);
			if(hasBrownMushrooms) builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED);
		}
	}
}