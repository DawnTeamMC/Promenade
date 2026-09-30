package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class PromenadeBiomeProvider extends FabricDynamicRegistryProvider {
    // Vanilla's defaults, that 1.21.1 biomes must all set
    private static final int NORMAL_WATER_COLOR = 4159204;
    private static final int NORMAL_WATER_FOG_COLOR = 329011;
    private static final int OVERWORLD_FOG_COLOR = 12638463;

    public PromenadeBiomeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
    }

    @Override
    public String getName() {
        return "Biomes";
    }

    public static void register(BootstrapContext<Biome> registerable) {
        final var features = registerable.lookup(Registries.PLACED_FEATURE);
        final var carvers = registerable.lookup(Registries.CONFIGURED_CARVER);

        registerable.register(PromenadeBiomes.BLUSH_SAKURA_GROVE, createSakuraGroves(features, carvers, PromenadePlacedFeatures.BLUSH_SAKURA_GROVE_TREES));
        registerable.register(PromenadeBiomes.COTTON_SAKURA_GROVE, createSakuraGroves(features, carvers, PromenadePlacedFeatures.COTTON_SAKURA_GROVE_TREES));

        registerable.register(PromenadeBiomes.CARNELIAN_TREEWAY, createCarnelianTreeway(features, carvers));
        registerable.register(PromenadeBiomes.GLACARIAN_TAIGA, createGlacarianTaiga(features, carvers));

        registerable.register(PromenadeBiomes.DARK_AMARANTH_FOREST, createDarkAmaranthForest(features, carvers));
    }

    public static Biome createSakuraGroves(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers, ResourceKey<PlacedFeature> trees) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        addBasicFeatures(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.WATER_POOLS_GRAVEL_DECORATED);
        generation.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, PromenadePlacedFeatures.CUTE_LITTLE_ROCKS);

        BiomeDefaultFeatures.addForestFlowers(generation);

        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.SAKURA_GROVE_BAMBOO);

        BiomeDefaultFeatures.addDefaultFlowers(generation);
        BiomeDefaultFeatures.addForestGrass(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);

        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, trees);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawns);
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 16, 1, 3));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 2, 4, 5));

        return biome(0.6F, 0.4F)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .specialEffects(overworldEffects(0.6F)
                        .waterColor(6459391)
                        .grassColorOverride(6484135)
                        .backgroundMusic(Musics.createGameMusic(PromenadeSoundEvents.MUSIC_OVERWORLD_SAKURA_GROVES))
                        .build())
                .build();
    }

    public static Biome createCarnelianTreeway(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        addBasicFeatures(generation);

        BiomeDefaultFeatures.addForestFlowers(generation);

        BiomeDefaultFeatures.addDefaultOres(generation);
        BiomeDefaultFeatures.addDefaultSoftDisks(generation);

        BiomeDefaultFeatures.addDefaultFlowers(generation);
        BiomeDefaultFeatures.addForestGrass(generation);

        BiomeDefaultFeatures.addDefaultMushrooms(generation);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_TREES);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawns);
        BiomeDefaultFeatures.commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 7, 2, 3));

        return biome(1.2F, 0.9F)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .specialEffects(overworldEffects(1.2F)
                        .waterColor(155336)
                        .waterFogColor(541)
                        .grassColorOverride(9090320)
                        .foliageColorOverride(10931465)
                        .build())
                .build();
    }

    public static Biome createGlacarianTaiga(HolderGetter<PlacedFeature> featureLookup, HolderGetter<ConfiguredWorldCarver<?>> carverLookup) {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(featureLookup, carverLookup);

        generation.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        generation.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        generation.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
        generation.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND);

        BiomeDefaultFeatures.addDefaultCrystalFormations(generation);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generation);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIRT);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRAVEL);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRANITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_TUFF);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.GLOW_LICHEN);

        generation.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_UPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_UPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_MIDDLE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_SMALL);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_MEDIUM);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_LARGE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIAMOND_BURIED);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS_BURIED);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PromenadePlacedFeatures.PACKED_ICE_ORE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PromenadePlacedFeatures.BLUE_ICE_ORE);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);

        BiomeDefaultFeatures.addInfestedStone(generation);

        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.GLACARIAN_TAIGA_TREES);
        generation.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, PromenadePlacedFeatures.FREEZE_TOP_LAYER);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(PromenadeEntityTypes.DUCK, 4, 4, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        BiomeDefaultFeatures.commonSpawns(spawns);

        return biome(-0.7F, 0.8f)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .specialEffects(overworldEffects(-0.7F)
                        .waterColor(1724346)
                        .waterFogColor(197394)
                        .build())
                .build();
    }

    public static Biome createDarkAmaranthForest(HolderGetter<PlacedFeature> featureLookup, HolderGetter<ConfiguredWorldCarver<?>> carverLookup) {
        MobSpawnSettings spawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2))
                .addMobCharge(EntityType.ENDERMAN, 1.0, 0.12)
                .build();
        BiomeGenerationSettings.Builder lookupBackedBuilder = new BiomeGenerationSettings.Builder(featureLookup, carverLookup)
                .addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA);
        BiomeDefaultFeatures.addDefaultMushrooms(lookupBackedBuilder);
        lookupBackedBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_SOUL_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.DARK_AMARANTH_FUNGI)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.DARK_AMARANTH_FOREST_VEGETATION)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NetherPlacements.NETHER_SPROUTS)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.COILED_VINES);
        BiomeDefaultFeatures.addNetherDefaultOres(lookupBackedBuilder);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(NORMAL_WATER_COLOR)
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .fogColor(524562)
                        .skyColor(calculateSkyColor(2.0F))
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.WARPED_SPORE, 0.01428F)) //TODO
                        .ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0))
                        .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111))
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_WARPED_FOREST))
                        .build())
                .mobSpawnSettings(spawnSettings)
                .generationSettings(lookupBackedBuilder.build())
                .build();
    }

    private static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
    }

    public static Biome.BiomeBuilder biome(float temperature, float downfall) {
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(downfall);
    }

    /**
     * The effects every overworld biome has in vanilla.
     */
    private static BiomeSpecialEffects.Builder overworldEffects(float temperature) {
        return new BiomeSpecialEffects.Builder()
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .fogColor(OVERWORLD_FOG_COLOR)
                .skyColor(calculateSkyColor(temperature))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
    }

    // mirrors OverworldBiomes#calculateSkyColor
    private static int calculateSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = Mth.clamp(f, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
}
