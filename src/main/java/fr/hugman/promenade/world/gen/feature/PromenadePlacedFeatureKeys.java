package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.world.biome.PromenadeBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class PromenadePlacedFeatureKeys {
    public static final RegistryKey<PlacedFeature> ORE_BLUNITE_UPPER = of("ore/blunite/upper");
    public static final RegistryKey<PlacedFeature> ORE_BLUNITE_LOWER = of("ore/blunite/lower");
    public static final RegistryKey<PlacedFeature> ORE_ASPHALT_UPPER = of("ore/asphalt/upper");
    public static final RegistryKey<PlacedFeature> ORE_ASPHALT_LOWER = of("ore/asphalt/lower");

    public static final RegistryKey<PlacedFeature> PALMS = of("trees/palms");

    private static RegistryKey<PlacedFeature> of(String path) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Promenade.id(path));
    }

    public static void appendWorldGen() {
        if (Promenade.CONFIG.world_features.igneous_rock_patches) {
            Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasFeature(OreConfiguredFeatures.ORE_ANDESITE) && c.hasFeature(OreConfiguredFeatures.ORE_DIORITE) && c.hasFeature(OreConfiguredFeatures.ORE_GRANITE);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_LOWER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_ASPHALT_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_ASPHALT_LOWER);
        }
        if (Promenade.CONFIG.world_features.palms) {
            BiomeModifications.addFeature(BiomeSelectors.tag(PromenadeBiomeTags.HAS_PALMS), GenerationStep.Feature.VEGETAL_DECORATION, PALMS);
        }
    }
}
