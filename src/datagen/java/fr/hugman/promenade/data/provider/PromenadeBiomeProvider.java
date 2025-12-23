package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.*;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class PromenadeBiomeProvider extends FabricDynamicRegistryProvider {
    public PromenadeBiomeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.BIOME));
    }

    @Override
    public String getName() {
        return "Biomes";
    }

    public static void register(Registerable<Biome> registerable) {
        final var features = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        final var carvers = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);

        registerable.register(PromenadeBiomes.BLUSH_SAKURA_GROVE, createSakuraGroves(features, carvers, PromenadePlacedFeatures.BLUSH_SAKURA_GROVE_TREES));
        registerable.register(PromenadeBiomes.COTTON_SAKURA_GROVE, createSakuraGroves(features, carvers, PromenadePlacedFeatures.COTTON_SAKURA_GROVE_TREES));

        registerable.register(PromenadeBiomes.CARNELIAN_TREEWAY, createCarnelianTreeway(features, carvers));
        registerable.register(PromenadeBiomes.GLACARIAN_TAIGA, createGlacarianTaiga(features, carvers));

        registerable.register(PromenadeBiomes.DARK_AMARANTH_FOREST, createDarkAmaranthForest(features, carvers));
    }

    public static Biome createSakuraGroves(RegistryEntryLookup<PlacedFeature> features, RegistryEntryLookup<ConfiguredCarver<?>> carvers, RegistryKey<PlacedFeature> trees) {
        GenerationSettings.LookupBackedBuilder generation = new GenerationSettings.LookupBackedBuilder(features, carvers);

        addBasicFeatures(generation);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.WATER_POOLS_GRAVEL_DECORATED);
        generation.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PromenadePlacedFeatures.CUTE_LITTLE_ROCKS);

        DefaultBiomeFeatures.addForestFlowers(generation);

        DefaultBiomeFeatures.addDefaultOres(generation);
        DefaultBiomeFeatures.addDefaultDisks(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.SAKURA_GROVE_BAMBOO);

        DefaultBiomeFeatures.addDefaultFlowers(generation);
        DefaultBiomeFeatures.addForestGrass(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);

        DefaultBiomeFeatures.addDefaultMushrooms(generation);
        DefaultBiomeFeatures.addDefaultVegetation(generation, true);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, trees);

        SpawnSettings.Builder spawns = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addFarmAnimals(spawns);
		DefaultBiomeFeatures.addCaveAndMonsters(spawns);
        spawns.spawn(SpawnGroup.CREATURE, 5, new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4));
        spawns.spawn(SpawnGroup.CREATURE, 16, new SpawnSettings.SpawnEntry(EntityType.FOX, 1, 3));
        spawns.spawn(SpawnGroup.CREATURE, 2, new SpawnSettings.SpawnEntry(EntityType.PANDA, 4, 5));

		return biome(0.6F, 0.4F)
				.spawnSettings(spawns.build())
				.generationSettings(generation.build())
				.effects(new BiomeEffects.Builder()
						.waterColor(6459391)
						.grassColor(6484135)
						.build())
				.setEnvironmentAttribute(EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO, new BackgroundMusic(MusicType.createIngameMusic(PromenadeSoundEvents.MUSIC_OVERWORLD_SAKURA_GROVES)))
				.build();
    }

    public static Biome createCarnelianTreeway(RegistryEntryLookup<PlacedFeature> features, RegistryEntryLookup<ConfiguredCarver<?>> carvers) {
        GenerationSettings.LookupBackedBuilder generation = new GenerationSettings.LookupBackedBuilder(features, carvers);

        addBasicFeatures(generation);

        DefaultBiomeFeatures.addForestFlowers(generation);

        DefaultBiomeFeatures.addDefaultOres(generation);
        DefaultBiomeFeatures.addDefaultDisks(generation);

        DefaultBiomeFeatures.addDefaultFlowers(generation);
        DefaultBiomeFeatures.addForestGrass(generation);

        DefaultBiomeFeatures.addDefaultMushrooms(generation);
        DefaultBiomeFeatures.addDefaultVegetation(generation, true);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_TREES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES);

        SpawnSettings.Builder spawns = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addFarmAnimals(spawns);
		DefaultBiomeFeatures.addCaveAndMonsters(spawns);
        spawns.spawn(SpawnGroup.CREATURE, 5, new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4));
        spawns.spawn(SpawnGroup.CREATURE, 7, new SpawnSettings.SpawnEntry(EntityType.FOX, 2, 3));

		return biome(1.2F, 0.9F)
				.spawnSettings(spawns.build())
				.generationSettings(generation.build())
				.effects(new BiomeEffects.Builder()
						.waterColor(155336)
						.grassColor(9090320)
						.foliageColor(10931465)
						.build())
				.setEnvironmentAttribute(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, 541)
				.build();
    }

    public static Biome createGlacarianTaiga(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        GenerationSettings.LookupBackedBuilder generation = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup);

        generation.carver(ConfiguredCarvers.CAVE);
        generation.carver(ConfiguredCarvers.CAVE_EXTRA_UNDERGROUND);
        generation.carver(ConfiguredCarvers.CANYON);
        generation.feature(GenerationStep.Feature.LAKES, MiscPlacedFeatures.LAKE_LAVA_UNDERGROUND);

        DefaultBiomeFeatures.addAmethystGeodes(generation);
        DefaultBiomeFeatures.addDungeons(generation);

        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIRT);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRAVEL);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRANITE_LOWER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIORITE_LOWER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_ANDESITE_LOWER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_TUFF);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, UndergroundPlacedFeatures.GLOW_LICHEN);

        generation.feature(GenerationStep.Feature.FLUID_SPRINGS, MiscPlacedFeatures.SPRING_WATER);

        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COAL_UPPER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COAL_LOWER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_UPPER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_MIDDLE);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_SMALL);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GOLD);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GOLD_LOWER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_REDSTONE);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_REDSTONE_LOWER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIAMOND);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIAMOND_MEDIUM);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIAMOND_LARGE);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIAMOND_BURIED);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_LAPIS);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_LAPIS_BURIED);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COPPER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PromenadePlacedFeatures.PACKED_ICE_ORE);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PromenadePlacedFeatures.BLUE_ICE_ORE);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, MiscPlacedFeatures.DISK_CLAY);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, MiscPlacedFeatures.DISK_GRAVEL);

        DefaultBiomeFeatures.addInfestedStone(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.GLACARIAN_TAIGA_TREES);
        generation.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, PromenadePlacedFeatures.FREEZE_TOP_LAYER);

        SpawnSettings.Builder spawns = new SpawnSettings.Builder()
                .spawn(SpawnGroup.CREATURE, 4, new SpawnSettings.SpawnEntry(PromenadeEntityTypes.DUCK, 4, 4))
                .spawn(SpawnGroup.CREATURE, 8, new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4))
                .spawn(SpawnGroup.CREATURE, 8, new SpawnSettings.SpawnEntry(EntityType.FOX, 2, 4));
		DefaultBiomeFeatures.addCaveAndMonsters(spawns);

		return biome(- 0.7F, 0.8f)
				.spawnSettings(spawns.build())
				.generationSettings(generation.build())
				.effects(new BiomeEffects.Builder()
						.waterColor(1724346)
						.build())
				.setEnvironmentAttribute(EnvironmentAttributes.FOG_COLOR_VISUAL, 12638463)
				.setEnvironmentAttribute(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, 197394)
				.build();
    }

    public static Biome createDarkAmaranthForest(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        SpawnSettings spawnSettings = new SpawnSettings.Builder()
                .spawn(SpawnGroup.MONSTER, 1, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 4, 4))
                .spawn(SpawnGroup.CREATURE, 60, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 1, 2))
                .spawnCost(EntityType.ENDERMAN, 1.0, 0.12)
                .build();
        GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup)
                .carver(ConfiguredCarvers.NETHER_CAVE)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(lookupBackedBuilder);
        lookupBackedBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.DARK_AMARANTH_FUNGI)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.DARK_AMARANTH_FOREST_VEGETATION)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.NETHER_SPROUTS)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.COILED_VINES);
        DefaultBiomeFeatures.addNetherMineables(lookupBackedBuilder);
        return new Biome.Builder()
                .precipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
				.effects(new BiomeEffects.Builder().waterColor(4159204).build())
				.setEnvironmentAttribute(EnvironmentAttributes.AMBIENT_PARTICLES_VISUAL, List.of(new AmbientParticle(ParticleTypes.WARPED_SPORE, 0.01428F))) //TODO
				.setEnvironmentAttribute(EnvironmentAttributes.FOG_COLOR_VISUAL, 524562)
				.setEnvironmentAttribute(EnvironmentAttributes.AMBIENT_SOUNDS_AUDIO, new AmbientSounds(
						Optional.of(SoundEvents.AMBIENT_WARPED_FOREST_LOOP),
						Optional.of(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0)),
						List.of(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111))
				))
				.setEnvironmentAttribute(EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO, new BackgroundMusic(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)))
                .spawnSettings(spawnSettings)
                .generationSettings(lookupBackedBuilder.build())
                .build();
    }

    private static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

	public static Biome.Builder biome(float temperature, float downfall) {
		return (new Biome.Builder()).precipitation(true).temperature(temperature).downfall(downfall).setEnvironmentAttribute(EnvironmentAttributes.SKY_COLOR_VISUAL, OverworldBiomeCreator.getSkyColor(temperature)).effects((new BiomeEffects.Builder()).waterColor(4159204).build());
	}
}