package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import java.util.function.Predicate;

public class PromenadePlacedFeatures {
    // Ores
    public static final ResourceKey<PlacedFeature> ORE_ASPHALT_UPPER = of("ore/asphalt/upper");
    public static final ResourceKey<PlacedFeature> ORE_ASPHALT_LOWER = of("ore/asphalt/lower");
    public static final ResourceKey<PlacedFeature> ORE_BLUNITE_UPPER = of("ore/blunite/upper");
    public static final ResourceKey<PlacedFeature> ORE_BLUNITE_LOWER = of("ore/blunite/lower");

    public static final ResourceKey<PlacedFeature> PACKED_ICE_ORE = of("ore/packed_ice");
    public static final ResourceKey<PlacedFeature> BLUE_ICE_ORE = of("ore/blue_ice");

    // Trees
    public static final ResourceKey<PlacedFeature> BLUSH_SAKURA = of("tree/blush_sakura");
    public static final ResourceKey<PlacedFeature> FANCY_BLUSH_SAKURA = of("tree/fancy_blush_sakura");
    public static final ResourceKey<PlacedFeature> BLUSH_SAKURA_BEES = of("tree/blush_sakura_bees");
    public static final ResourceKey<PlacedFeature> FANCY_BLUSH_SAKURA_BEES = of("tree/fancy_blush_sakura_bees");

    public static final ResourceKey<PlacedFeature> COTTON_SAKURA = of("tree/cotton_sakura");
    public static final ResourceKey<PlacedFeature> FANCY_COTTON_SAKURA = of("tree/fancy_cotton_sakura");
    public static final ResourceKey<PlacedFeature> COTTON_SAKURA_BEES = of("tree/cotton_sakura_bees");
    public static final ResourceKey<PlacedFeature> FANCY_COTTON_SAKURA_BEES = of("tree/fancy_cotton_sakura_bees");

    public static final ResourceKey<PlacedFeature> SAP_MAPLE = of("tree/sap_maple");
    public static final ResourceKey<PlacedFeature> FANCY_SAP_MAPLE = of("tree/fancy_sap_maple");
    public static final ResourceKey<PlacedFeature> SAP_MAPLE_BEES = of("tree/sap_maple_bees");
    public static final ResourceKey<PlacedFeature> FANCY_SAP_MAPLE_BEES = of("tree/fancy_sap_maple_bees");

    public static final ResourceKey<PlacedFeature> VERMILION_MAPLE = of("tree/vermilion_maple");
    public static final ResourceKey<PlacedFeature> FANCY_VERMILION_MAPLE = of("tree/fancy_vermilion_maple");
    public static final ResourceKey<PlacedFeature> VERMILION_MAPLE_BEES = of("tree/vermilion_maple_bees");
    public static final ResourceKey<PlacedFeature> FANCY_VERMILION_MAPLE_BEES = of("tree/fancy_vermilion_maple_bees");

    public static final ResourceKey<PlacedFeature> FULVOUS_MAPLE = of("tree/fulvous_maple");
    public static final ResourceKey<PlacedFeature> FANCY_FULVOUS_MAPLE = of("tree/fancy_fulvous_maple");
    public static final ResourceKey<PlacedFeature> FULVOUS_MAPLE_BEES = of("tree/fulvous_maple_bees");
    public static final ResourceKey<PlacedFeature> FANCY_FULVOUS_MAPLE_BEES = of("tree/fancy_fulvous_maple_bees");

    public static final ResourceKey<PlacedFeature> MIKADO_MAPLE = of("tree/mikado_maple");
    public static final ResourceKey<PlacedFeature> FANCY_MIKADO_MAPLE = of("tree/fancy_mikado_maple");
    public static final ResourceKey<PlacedFeature> MIKADO_MAPLE_BEES = of("tree/mikado_maple_bees");
    public static final ResourceKey<PlacedFeature> FANCY_MIKADO_MAPLE_BEES = of("tree/fancy_mikado_maple_bees");

    // Vegetation
    public static final ResourceKey<PlacedFeature> BLUEBERRY_BUSH_COMMON_PATCH = of("patch/blueberry_bush/common");
    public static final ResourceKey<PlacedFeature> BLUEBERRY_BUSH_RARE_PATCH = of("patch/blueberry_bush/rare");

    public static final ResourceKey<PlacedFeature> SAKURA_GROVE_BAMBOO = of("sakura_grove_bamboo");

    public static final ResourceKey<PlacedFeature> CUTE_LITTLE_ROCKS = of("cute_little_rocks");

    public static final ResourceKey<PlacedFeature> WATER_POOLS_GRAVEL_DECORATED = of("water_pools/gravel_decorated");

    public static final ResourceKey<PlacedFeature> FREEZE_TOP_LAYER = of("freeze_top_layer");

    public static final ResourceKey<PlacedFeature> DARK_AMARANTH_FOREST_VEGETATION = of("dark_amaranth_forest_vegetation");

    public static final ResourceKey<PlacedFeature> COILED_VINES = of("coiled_vines");

    public static final ResourceKey<PlacedFeature> FALLEN_VERMILION_MAPLE_LEAVES = of("patch/fallen_vermilion_maple_leaves");
    public static final ResourceKey<PlacedFeature> FALLEN_FULVOUS_MAPLE_LEAVES = of("patch/fallen_fulvous_maple_leaves");
    public static final ResourceKey<PlacedFeature> FALLEN_MIKADO_MAPLE_LEAVES = of("patch/fallen_mikado_maple_leaves");

    // Grouped features
    public static final ResourceKey<PlacedFeature> BLUSH_SAKURA_GROVE_TREES = of("trees/blush_sakura_grove");
    public static final ResourceKey<PlacedFeature> COTTON_SAKURA_GROVE_TREES = of("trees/cotton_sakura_grove");

    public static final ResourceKey<PlacedFeature> CARNELIAN_TREEWAY_TREES = of("trees/carnelian_treeway");
    public static final ResourceKey<PlacedFeature> CARNELIAN_TREEWAY_FALLEN_LEAVES = of("patch/carnelian_treeway_fallen_leaves");

    public static final ResourceKey<PlacedFeature> GLACARIAN_TAIGA_TREES = of("trees/glacarian_taiga");

    public static final ResourceKey<PlacedFeature> PALMS = of("trees/palms");

    public static final ResourceKey<PlacedFeature> DARK_AMARANTH_FUNGI = of("trees/dark_amaranth_fungi");


    private static ResourceKey<PlacedFeature> of(String path) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Promenade.id(path));
    }

    public static void appendWorldGen() {
        if (PromenadeConfig.get().worldFeatures().igneousRockPatches()) {
            Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasFeature(OreFeatures.ORE_ANDESITE) && c.hasFeature(OreFeatures.ORE_DIORITE) && c.hasFeature(OreFeatures.ORE_GRANITE);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Decoration.UNDERGROUND_ORES, PromenadePlacedFeatures.ORE_ASPHALT_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Decoration.UNDERGROUND_ORES, PromenadePlacedFeatures.ORE_ASPHALT_LOWER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Decoration.UNDERGROUND_ORES, PromenadePlacedFeatures.ORE_BLUNITE_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Decoration.UNDERGROUND_ORES, PromenadePlacedFeatures.ORE_BLUNITE_LOWER);
        }
        if (PromenadeConfig.get().worldFeatures().palms()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(PromenadeBiomeTags.HAS_PALMS), GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.PALMS);
        }

        if (PromenadeConfig.get().worldFeatures().blueberryBushes()) {
            BiomeModifications.addFeature(c -> c.hasPlacedFeature(VegetationPlacements.PATCH_BERRY_COMMON), GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.BLUEBERRY_BUSH_COMMON_PATCH);
            BiomeModifications.addFeature(c -> c.hasPlacedFeature(VegetationPlacements.PATCH_BERRY_RARE), GenerationStep.Decoration.VEGETAL_DECORATION, PromenadePlacedFeatures.BLUEBERRY_BUSH_RARE_PATCH);
        }
    }
}
