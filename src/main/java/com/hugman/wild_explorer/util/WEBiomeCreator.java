package com.hugman.wild_explorer.util;

import com.google.common.collect.ImmutableList;
import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import com.hugman.wild_explorer.init.world.WEConfiguredSurfaceBuilders;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

public class WEBiomeCreator {
	private static int getSkyColor(float temperature) {
		float g = temperature / 3.0F;
		g = MathHelper.clamp(g, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - g * 0.05F, 0.5F + g * 0.1F, 1.0F);
	}

	public static Biome createPumpkinPastures() {
		GenerationSettings.Builder generationBuilder = createForestGenerationSettings();
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PUMPKIN_PASTURES_TREES);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_AUTUMN_BIRCH_LEAF_PILE_NORMAL);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_AUTUMN_OAK_LEAF_PILE_NORMAL);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_YELLOW_MUSHROOM_NORMAL);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_ORANGE_MUSHROOM_NORMAL);
		BiomeEffects.Builder effectBuilder = new BiomeEffects.Builder();
		effectBuilder.fogColor(12638463);
		effectBuilder.skyColor(getSkyColor(0.8F));
		effectBuilder.waterColor(155336);
		effectBuilder.waterFogColor(541);
		effectBuilder.moodSound(BiomeMoodSound.CAVE);
		effectBuilder.foliageColor(15232304);
		effectBuilder.grassColor(15443554);
		return createForest(generationBuilder.build(), effectBuilder.build(), 0.8F, 0.9F);
	}

	public static Biome createCherryOakForest(boolean isPink) {
		GenerationSettings.Builder generationBuilder = createForestGenerationSettings();
		if(isPink) {
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PINK_CHERRY_OAK_FOREST_TREES);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_PINK_CHERRY_OAK_LEAF_PILE_NORMAL);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_PINK_MUSHROOM_NORMAL);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_MAGENTA_MUSHROOM_NORMAL);
		}
		else {
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.WHITE_CHERRY_OAK_FOREST_TREES);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_WHITE_CHERRY_OAK_LEAF_PILE_NORMAL);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_WHITE_MUSHROOM_NORMAL);
			generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_LIGHT_GRAY_MUSHROOM_NORMAL);
		}
		BiomeEffects.Builder effectBuilder = new BiomeEffects.Builder();
		effectBuilder.fogColor(12638463);
		effectBuilder.skyColor(getSkyColor(0.6F));
		effectBuilder.waterColor(6459391);
		effectBuilder.waterFogColor(2170954);
		effectBuilder.moodSound(BiomeMoodSound.CAVE);
		effectBuilder.foliageColor(15768259);
		return createForest(generationBuilder.build(), effectBuilder.build(), 0.6F, 0.4F);
	}

	public static Biome createForest(GenerationSettings generationBuilder, float temperature, float downfall, int waterColor, int waterFogColor) {
		BiomeEffects.Builder effectBuilder = new BiomeEffects.Builder();
		effectBuilder.fogColor(12638463);
		effectBuilder.skyColor(getSkyColor(temperature));
		effectBuilder.waterColor(waterColor);
		effectBuilder.waterFogColor(waterFogColor);
		effectBuilder.moodSound(BiomeMoodSound.CAVE);
		return createForest(generationBuilder, effectBuilder.build(), temperature, downfall);
	}

	public static Biome createForest(GenerationSettings generationBuilder, BiomeEffects effectBuilder, float temperature, float downfall) {
		SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
		DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
		spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
		Biome.Builder builder = new Biome.Builder();
		builder.depth(0.1F);
		builder.scale(0.2F);
		builder.temperature(temperature);
		builder.downfall(downfall);
		builder.precipitation(Biome.Precipitation.RAIN);
		builder.category(Biome.Category.FOREST);
		builder.effects(effectBuilder);
		builder.spawnSettings(spawnBuilder.build());
		builder.generationSettings(generationBuilder);
		return builder.build();
	}

	private static GenerationSettings.Builder createForestGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		builder.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
		builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
		DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
		DefaultBiomeFeatures.addLandCarvers(builder);
		DefaultBiomeFeatures.addDefaultLakes(builder);
		DefaultBiomeFeatures.addDungeons(builder);
		DefaultBiomeFeatures.addForestFlowers(builder);
		DefaultBiomeFeatures.addMineables(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addForestGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addSprings(builder);
		DefaultBiomeFeatures.addFrozenTopLayer(builder);
		return builder;
	}

	public static Biome createTallCrimsonForest() {
		SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 2, 4));
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 9, 3, 4));
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 3, 4));
		spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2));
		GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
		generationBuilder.surfaceBuilder(ConfiguredSurfaceBuilders.CRIMSON_FOREST);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER);
		generationBuilder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.FORTRESS);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
		DefaultBiomeFeatures.addDefaultMushrooms(generationBuilder);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WEEPING_VINES);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.TALL_CRIMSON_FUNGI);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CRIMSON_FOREST_VEGETATION);
		DefaultBiomeFeatures.addNetherMineables(generationBuilder);
		BiomeEffects.Builder effectBuilder = new BiomeEffects.Builder();
		effectBuilder.waterColor(4159204);
		effectBuilder.waterFogColor(329011);
		effectBuilder.fogColor(3343107);
		effectBuilder.skyColor(getSkyColor(2.0F));
		effectBuilder.particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F));
		effectBuilder.loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP);
		effectBuilder.moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D));
		effectBuilder.additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D));
		effectBuilder.music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST));
		Biome.Builder builder = new Biome.Builder();
		builder.precipitation(Biome.Precipitation.NONE);
		builder.category(Biome.Category.NETHER);
		builder.depth(0.2F);
		builder.scale(0.4F);
		builder.temperature(2.0F);
		builder.downfall(0.0F);
		builder.scale(0.2F);
		builder.effects(effectBuilder.build());
		builder.spawnSettings(spawnBuilder.build());
		builder.generationSettings(generationBuilder.build());
		return builder.build();
	}

	public static Biome createTallWarpedForest() {
		SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4));
		spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2));
		spawnBuilder.spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
		GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
		generationBuilder.surfaceBuilder(ConfiguredSurfaceBuilders.WARPED_FOREST);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.FORTRESS);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER);
		generationBuilder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
		DefaultBiomeFeatures.addDefaultMushrooms(generationBuilder);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.TALL_WARPED_FUNGI);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WARPED_FOREST_VEGETATION);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.NETHER_SPROUTS);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.TWISTING_VINES);
		DefaultBiomeFeatures.addNetherMineables(generationBuilder);
		BiomeEffects.Builder effectBuilder = new BiomeEffects.Builder();
		effectBuilder.waterColor(4159204);
		effectBuilder.waterFogColor(329011);
		effectBuilder.fogColor(1705242);
		effectBuilder.skyColor(getSkyColor(2.0F));
		effectBuilder.particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F));
		effectBuilder.loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP);
		effectBuilder.moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D));
		effectBuilder.additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D));
		effectBuilder.music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST));
		Biome.Builder builder = new Biome.Builder();
		builder.precipitation(Biome.Precipitation.NONE);
		builder.category(Biome.Category.NETHER);
		builder.depth(0.2F);
		builder.scale(0.4F);
		builder.temperature(2.0F);
		builder.downfall(0.0F);
		builder.effects(effectBuilder.build());
		builder.spawnSettings(spawnBuilder.build());
		builder.generationSettings(generationBuilder.build());
		return builder.build();
	}

	public static Biome createGallery(GenerationSettings.Builder generationBuilder) {
		SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4));
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 100, 4, 4));
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 4, 4));
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4));
		spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 4, 4));
		spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2));
		generationBuilder.surfaceBuilder(ConfiguredSurfaceBuilders.NETHER);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.FORTRESS);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT);
		generationBuilder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
		DefaultBiomeFeatures.addDefaultMushrooms(generationBuilder);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED);
		DefaultBiomeFeatures.addNetherMineables(generationBuilder);
		BiomeEffects.Builder effectBuilder = new BiomeEffects.Builder();
		effectBuilder.waterColor(4159204);
		effectBuilder.waterFogColor(329011);
		effectBuilder.fogColor(3344392);
		effectBuilder.skyColor(getSkyColor(2.0F));
		effectBuilder.loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP);
		effectBuilder.moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D));
		effectBuilder.additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D));
		effectBuilder.music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES));
		Biome.Builder settings = new Biome.Builder();
		settings.precipitation(Biome.Precipitation.NONE);
		settings.category(Biome.Category.NETHER);
		settings.depth(0.1F);
		settings.scale(0.2F);
		settings.temperature(2.0F);
		settings.downfall(0.0F);
		settings.effects(effectBuilder.build());
		settings.spawnSettings(spawnBuilder.build());
		settings.generationSettings(generationBuilder.build());
		return settings.build();
	}

	public static GenerationSettings.Builder createTritanopianGalleryGenerationSettings() {
		GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_PINK_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_CYAN_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_PINK, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_PINK_FLAT, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN_FLAT)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_PINK_UPSIDE, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN_UPSIDE)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_PINK_UPSIDE_FLAT, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		return generationBuilder;
	}

	public static GenerationSettings.Builder createAchromatopsianGalleryGenerationSettings() {
		GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_WHITE_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_LIGHT_GRAY_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_GRAY_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_BLACK_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_GRAY, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BLACK)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_GRAY_FLAT, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BLACK_FLAT)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_WHITE_UPSIDE, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(() -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_WHITE_UPSIDE_FLAT, () -> WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE_FLAT)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		return generationBuilder;
	}

	public static GenerationSettings.Builder createProtanopianGalleryGenerationSettings() {
		GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_YELLOW_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_CYAN_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, WEConfiguredFeatures.PATCH_BLUE_MUSHROOM_NETHER);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BROWN_FLAT.withChance(0.25F), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_YELLOW.withChance(0.25F), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN.withChance(0.25F)), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BLUE)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BROWN_UPSIDE_FLAT.withChance(0.25F), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE.withChance(0.25F), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN_UPSIDE.withChance(0.25F)), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BLUE_UPSIDE)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_YELLOW_FLAT.withChance(1F / 3F), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN_FLAT.withChance(1F / 3F)), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BLUE_FLAT)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE_FLAT.withChance(1F / 3F), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT.withChance(1F / 3F)), WEConfiguredFeatures.HUGE_NETHER_MUSHROOM_BLUE_UPSIDE_FLAT)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));
		return generationBuilder;
	}

	private static Biome createEndBiome(float depth, float scale, GenerationSettings.Builder generationBuilder, SpawnSettings.Builder spawnSettings) {
		BiomeEffects.Builder effects = new BiomeEffects.Builder();
		effects.waterColor(4159204);
		effects.waterFogColor(329011);
		effects.fogColor(10518688);
		effects.skyColor(0);
		effects.moodSound(BiomeMoodSound.CAVE);
		Biome.Builder settings = new Biome.Builder();
		settings.precipitation(Biome.Precipitation.NONE);
		settings.category(Biome.Category.THEEND);
		settings.depth(depth);
		settings.scale(scale);
		settings.temperature(0.5F);
		settings.downfall(0.5F);
		settings.effects(effects.build());
		settings.spawnSettings(spawnSettings.build());
		settings.generationSettings(generationBuilder.build());
		return settings.build();
	}

	private static Biome createDarkAmaranthForest(float depth, float scale, GenerationSettings.Builder generationBuilder) {
		generationBuilder.surfaceBuilder(WEConfiguredSurfaceBuilders.AMARANTH_DYLIUM);
		generationBuilder.structureFeature(ConfiguredStructureFeatures.END_CITY);
		generationBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.END_GATEWAY);
		generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.AMARANTH_FOREST_VEGETATION);
		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addEndMobs(spawnSettings);
		spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 10, 4, 4));
		spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CAVE_SPIDER, 10, 4, 4));
		return createEndBiome(depth, scale, generationBuilder, spawnSettings);
	}

	public static Biome createNormalDarkAmaranthForest() {
		return createDarkAmaranthForest(0.1F, 0.2F, new GenerationSettings.Builder().feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.AMARANTH_FUNGI));
	}

	public static Biome createTallDarkAmaranthForest() {
		return createDarkAmaranthForest(0.2F, 0.4F, new GenerationSettings.Builder().feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.TALL_AMARANTH_FUNGI));
	}
}
