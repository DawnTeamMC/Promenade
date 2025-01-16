package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.world.biome.PromenadeBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import java.util.function.Predicate;

public class PromenadePlacedFeatures {
    public static void appendWorldGen() {
        if (PromenadeConfig.get().worldFeatures().igneousRockPatches()) {
            Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasFeature(OreConfiguredFeatures.ORE_ANDESITE) && c.hasFeature(OreConfiguredFeatures.ORE_DIORITE) && c.hasFeature(OreConfiguredFeatures.ORE_GRANITE);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, PromenadePlacedFeatureKeys.ORE_BLUNITE_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, PromenadePlacedFeatureKeys.ORE_BLUNITE_LOWER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, PromenadePlacedFeatureKeys.ORE_ASPHALT_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, PromenadePlacedFeatureKeys.ORE_ASPHALT_LOWER);
        }
        if (PromenadeConfig.get().worldFeatures().palms()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(PromenadeBiomeTags.HAS_PALMS), GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatureKeys.PALMS);
        }

        if (PromenadeConfig.get().worldFeatures().blueberryBushes()) {
            BiomeModifications.addFeature(c -> c.hasPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_COMMON), GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatureKeys.PATCH_BLUEBERRY_BUSH_COMMON);
            BiomeModifications.addFeature(c -> c.hasPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_RARE), GenerationStep.Feature.VEGETAL_DECORATION, PromenadePlacedFeatureKeys.PATCH_BLUEBERRY_BUSH_RARE);
        }
    }
}
