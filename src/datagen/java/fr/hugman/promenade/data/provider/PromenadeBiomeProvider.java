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
import net.minecraft.sound.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;
import org.jetbrains.annotations.Nullable;

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
        generation.feature(GenerationStep.Feature.LAKES, PromenadePlacedFeatures.WATER_POOLS_GRAVEL);
        generation.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PromenadePlacedFeatures.CUTE_LITTLE_ROCKS);

        DefaultBiomeFeatures.addForestFlowers(generation);

        DefaultBiomeFeatures.addDefaultOres(generation);
        DefaultBiomeFeatures.addDefaultDisks(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.SAKURA_GROVE_BAMBOO);

        DefaultBiomeFeatures.addDefaultFlowers(generation);
        DefaultBiomeFeatures.addForestGrass(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);

        DefaultBiomeFeatures.addDefaultMushrooms(generation);
        DefaultBiomeFeatures.addDefaultVegetation(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, trees);

        SpawnSettings.Builder spawns = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addFarmAnimals(spawns);
        DefaultBiomeFeatures.addBatsAndMonsters(spawns);
        spawns.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        spawns.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 16, 1, 3));
        spawns.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PANDA, 2, 4, 5));

        return createBiome(
                true,
                0.6F,
                0.4F,
                6459391,
                2170954,
                6484135,
                null,
                spawns,
                generation,
                MusicType.createIngameMusic(PromenadeSoundEvents.MUSIC_OVERWORLD_SAKURA_GROVES)
        );
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
        DefaultBiomeFeatures.addDefaultVegetation(generation);

        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_VERMILION_TREES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_FULVOUS_TREES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_MIKADO_TREES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_SAP_TREES);

        SpawnSettings.Builder spawns = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addFarmAnimals(spawns);
        DefaultBiomeFeatures.addBatsAndMonsters(spawns);
        spawns.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        spawns.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 7, 2, 3));

        return createBiome(
                true,
                1.2F,
                0.9F,
                155336,
                541,
                9090320,
                10931465,
                spawns,
                generation,
                null
        );
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

        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        spawns.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(PromenadeEntityTypes.DUCK, 4, 4, 4))
                .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4))
                .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4));
        DefaultBiomeFeatures.addBatsAndMonsters(spawns);

        return createBiome(
                true,
                -0.7F,
                0.8f,
                1724346,
                197394,
                null,
                null,
                spawns,
                generation,
                null
        );
    }

    public static Biome createDarkAmaranthForest(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        SpawnSettings spawnSettings = new SpawnSettings.Builder()
                .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4))
                .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2))
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
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.TWISTING_VINES); //TODO: do we keep or remove or whatever
        DefaultBiomeFeatures.addNetherMineables(lookupBackedBuilder);
        return new Biome.Builder()
                .precipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .effects(
                        new BiomeEffects.Builder()
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .fogColor(524562)
                                .skyColor(OverworldBiomeCreator.getSkyColor(2.0F))
                                .particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F)) //TODO
                                .loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP) //TODO
                                .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0)) //TODO
                                .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111)) //TODO
                                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)) //TODO
                                .build()
                )
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

    private static Biome createBiome(
            boolean precipitation,
            float temperature,
            float downfall,
            SpawnSettings.Builder spawnSettings,
            GenerationSettings.LookupBackedBuilder generationSettings,
            @Nullable MusicSound music
    ) {
        return createBiome(precipitation, temperature, downfall, 4159204, 329011, null, null, spawnSettings, generationSettings, music);
    }

    private static Biome createBiome(
            boolean precipitation,
            float temperature,
            float downfall,
            int waterColor,
            int waterFogColor,
            @Nullable Integer grassColor,
            @Nullable Integer foliageColor,
            SpawnSettings.Builder spawnSettings,
            GenerationSettings.LookupBackedBuilder generationSettings,
            @Nullable MusicSound music
    ) {
        BiomeEffects.Builder builder = new BiomeEffects.Builder()
                .waterColor(waterColor)
                .waterFogColor(waterFogColor)
                .fogColor(12638463)
                .skyColor(OverworldBiomeCreator.getSkyColor(temperature))
                .moodSound(BiomeMoodSound.CAVE)
                .music(music);
        if (grassColor != null) {
            builder.grassColor(grassColor);
        }

        if (foliageColor != null) {
            builder.foliageColor(foliageColor);
        }

        return new Biome.Builder()
                .precipitation(precipitation)
                .temperature(temperature)
                .downfall(downfall)
                .effects(builder.build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}