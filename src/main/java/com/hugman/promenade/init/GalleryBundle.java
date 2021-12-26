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
			NetherBiomes.addNetherBiome(Biomes.TRITANOPIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(Biomes.ACHROMATOPSIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.2F, 0.0F, 0.6F, 0.0F));
			NetherBiomes.addNetherBiome(Biomes.PROTANOPIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F, 0.0F));
		}
	}

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<?, ?> TRITANOPIAN_GALLERY_GENERATION_1 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/normal", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK::withPlacement, HUGE_NETHER_MUSHROOM_CYAN::withPlacement))));
			public static final ConfiguredFeature<?, ?> TRITANOPIAN_GALLERY_GENERATION_2 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_CYAN_FLAT::withPlacement))));
			public static final ConfiguredFeature<?, ?> TRITANOPIAN_GALLERY_GENERATION_3 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/ceiling", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK_UPSIDE::withPlacement, HUGE_NETHER_MUSHROOM_CYAN_UPSIDE::withPlacement))));
			public static final ConfiguredFeature<?, ?> TRITANOPIAN_GALLERY_GENERATION_4 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/tritanopian/ceiling_flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_PINK_UPSIDE_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT::withPlacement))));

			public static final ConfiguredFeature<?, ?> ACHROMATOPSIAN_GALLERY_GENERATION_1 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/normal", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_GRAY::withPlacement, HUGE_NETHER_MUSHROOM_BLACK::withPlacement))));
			public static final ConfiguredFeature<?, ?> ACHROMATOPSIAN_GALLERY_GENERATION_2 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_GRAY_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_BLACK_FLAT::withPlacement))));
			public static final ConfiguredFeature<?, ?> ACHROMATOPSIAN_GALLERY_GENERATION_3 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/ceiling", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_WHITE_UPSIDE::withPlacement, HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE::withPlacement))));
			public static final ConfiguredFeature<?, ?> ACHROMATOPSIAN_GALLERY_GENERATION_4 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/achromatopsian/ceiling_flat", Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(HUGE_NETHER_MUSHROOM_WHITE_UPSIDE_FLAT::withPlacement, HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE_FLAT::withPlacement))));

			public static final ConfiguredFeature<?, ?> PROTANOPIAN_GALLERY_GENERATION_1 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/normal", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_BROWN_FLAT.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN.withPlacement(), 0.25F)), HUGE_NETHER_MUSHROOM_BLUE.withPlacement()))));
			public static final ConfiguredFeature<?, ?> PROTANOPIAN_GALLERY_GENERATION_2 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/flat", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_BROWN_UPSIDE_FLAT.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE.withPlacement(), 0.25F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN_UPSIDE.withPlacement(), 0.25F)), HUGE_NETHER_MUSHROOM_BLUE_UPSIDE.withPlacement()))));
			public static final ConfiguredFeature<?, ?> PROTANOPIAN_GALLERY_GENERATION_3 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/ceiling", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW_FLAT.withPlacement(), 1F / 3F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN_FLAT.withPlacement(), 1F / 3F)), HUGE_NETHER_MUSHROOM_BLUE_FLAT.withPlacement()))));
			public static final ConfiguredFeature<?, ?> PROTANOPIAN_GALLERY_GENERATION_4 = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom/protanopian/ceiling_flat", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE_FLAT.withPlacement(), 1F / 3F), new RandomFeatureEntry(HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT.withPlacement(), 1F / 3F)), HUGE_NETHER_MUSHROOM_BLUE_UPSIDE_FLAT.withPlacement()))));
		}

		public static class Placed {
			private static final List<PlacementModifier> PLACEMENT_MODIFIERS = List.of(CountPlacementModifier.of(16), SquarePlacementModifier.of(), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

			public static final PlacedFeature TRITANOPIAN_GALLERY_GENERATION_1 = add(new PlacedFeatureCreator("huge_nether_mushrooms/pink_cyan/normal", Configured.TRITANOPIAN_GALLERY_GENERATION_1.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature TRITANOPIAN_GALLERY_GENERATION_2 = add(new PlacedFeatureCreator("huge_nether_mushrooms/pink_cyan/flat", Configured.TRITANOPIAN_GALLERY_GENERATION_2.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature TRITANOPIAN_GALLERY_GENERATION_3 = add(new PlacedFeatureCreator("huge_nether_mushrooms/pink_cyan/ceiling", Configured.TRITANOPIAN_GALLERY_GENERATION_3.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature TRITANOPIAN_GALLERY_GENERATION_4 = add(new PlacedFeatureCreator("huge_nether_mushrooms/pink_cyan/ceiling_flat", Configured.TRITANOPIAN_GALLERY_GENERATION_4.withPlacement(PLACEMENT_MODIFIERS)));

			public static final PlacedFeature ACHROMATOPSIAN_GALLERY_GENERATION_1 = add(new PlacedFeatureCreator("huge_nether_mushrooms/achromatopsian/normal", Configured.ACHROMATOPSIAN_GALLERY_GENERATION_1.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature ACHROMATOPSIAN_GALLERY_GENERATION_2 = add(new PlacedFeatureCreator("huge_nether_mushrooms/achromatopsian/flat", Configured.ACHROMATOPSIAN_GALLERY_GENERATION_2.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature ACHROMATOPSIAN_GALLERY_GENERATION_3 = add(new PlacedFeatureCreator("huge_nether_mushrooms/achromatopsian/ceiling", Configured.ACHROMATOPSIAN_GALLERY_GENERATION_3.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature ACHROMATOPSIAN_GALLERY_GENERATION_4 = add(new PlacedFeatureCreator("huge_nether_mushrooms/achromatopsian/ceiling_flat", Configured.ACHROMATOPSIAN_GALLERY_GENERATION_4.withPlacement(PLACEMENT_MODIFIERS)));

			public static final PlacedFeature PROTANOPIAN_GALLERY_GENERATION_1 = add(new PlacedFeatureCreator("huge_nether_mushrooms/protanopian/normal", Configured.PROTANOPIAN_GALLERY_GENERATION_1.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature PROTANOPIAN_GALLERY_GENERATION_2 = add(new PlacedFeatureCreator("huge_nether_mushrooms/protanopian/flat", Configured.PROTANOPIAN_GALLERY_GENERATION_2.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature PROTANOPIAN_GALLERY_GENERATION_3 = add(new PlacedFeatureCreator("huge_nether_mushrooms/protanopian/ceiling", Configured.PROTANOPIAN_GALLERY_GENERATION_3.withPlacement(PLACEMENT_MODIFIERS)));
			public static final PlacedFeature PROTANOPIAN_GALLERY_GENERATION_4 = add(new PlacedFeatureCreator("huge_nether_mushrooms/protanopian/ceiling_flat", Configured.PROTANOPIAN_GALLERY_GENERATION_4.withPlacement(PLACEMENT_MODIFIERS)));
		}
	}

	public static class Biomes {
		public static final BiomeCreator TRITANOPIAN_GALLERY = creator(new BiomeCreator("tritanopian_gallery", createGallery(composeTritanopianGalleryGenerationSettings())));
		public static final BiomeCreator ACHROMATOPSIAN_GALLERY = creator(new BiomeCreator("achromatopsian_gallery", createGallery(composeAchromatopsianGalleryGenerationSettings())));
		public static final BiomeCreator PROTANOPIAN_GALLERY = creator(new BiomeCreator("protanopian_gallery", createGallery(composeProtanopianGalleryGenerationSettings())));

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
				genBuilder.surfaceBuilder(ConfiguredSurfaceBuilders.NETHER);
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

		public static GenerationSettings.Builder composeTritanopianGalleryGenerationSettings() {
			GenerationSettings.Builder builder = new GenerationSettings.Builder();

			builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
			addVegetalDecoration(builder);
			addTritanopianHugeMushrooms(builder);
			addUndergroundDecoration(builder);
			addTritanopianMushrooms(builder);
			DefaultBiomeFeatures.addNetherMineables(builder);

			return builder;
		}

		public static GenerationSettings.Builder composeAchromatopsianGalleryGenerationSettings() {
			GenerationSettings.Builder builder = new GenerationSettings.Builder();

			builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
			addVegetalDecoration(builder);
			addAchromatopsianHugeMushrooms(builder);
			addUndergroundDecoration(builder);
			addAchromatopsianMushrooms(builder);
			DefaultBiomeFeatures.addNetherMineables(builder);

			return builder;
		}

		public static GenerationSettings.Builder composeProtanopianGalleryGenerationSettings() {
			GenerationSettings.Builder builder = new GenerationSettings.Builder();

			builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
			addVegetalDecoration(builder);
			addProtanopianHugeMushrooms(builder);
			addUndergroundDecoration(builder, true);
			addProtanopianMushrooms(builder);
			DefaultBiomeFeatures.addNetherMineables(builder);

			return builder;
		}

		private static void addTritanopianMushrooms(GenerationSettings.Builder builder) {
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.PINK_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.CYAN_MUSHROOM_NETHER_PATCH);
		}

		private static void addTritanopianHugeMushrooms(GenerationSettings.Builder builder) {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.TRITANOPIAN_GALLERY_GENERATION_1);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.TRITANOPIAN_GALLERY_GENERATION_2);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.TRITANOPIAN_GALLERY_GENERATION_3);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.TRITANOPIAN_GALLERY_GENERATION_4);
		}

		private static void addAchromatopsianMushrooms(GenerationSettings.Builder builder) {
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.WHITE_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.LIGHT_GRAY_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.GRAY_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.BLACK_MUSHROOM_NETHER_PATCH);
		}

		private static void addAchromatopsianHugeMushrooms(GenerationSettings.Builder builder) {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.ACHROMATOPSIAN_GALLERY_GENERATION_1);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.ACHROMATOPSIAN_GALLERY_GENERATION_2);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.ACHROMATOPSIAN_GALLERY_GENERATION_3);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.ACHROMATOPSIAN_GALLERY_GENERATION_4);
		}

		private static void addProtanopianMushrooms(GenerationSettings.Builder builder) {
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.YELLOW_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.CYAN_MUSHROOM_NETHER_PATCH);
			builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MushroomBundle.Features.Placed.BLUE_MUSHROOM_NETHER_PATCH);
		}

		private static void addProtanopianHugeMushrooms(GenerationSettings.Builder builder) {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PROTANOPIAN_GALLERY_GENERATION_1);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PROTANOPIAN_GALLERY_GENERATION_2);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PROTANOPIAN_GALLERY_GENERATION_3);
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PROTANOPIAN_GALLERY_GENERATION_4);
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

		private static void addVegetalDecoration(GenerationSettings.Builder builder) {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.SPRING_OPEN);
		}
	}
}