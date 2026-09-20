package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;

public class PromenadeConfiguredFeatures {
    // Ores
    public static final ResourceKey<Feature> PACKED_ICE_ORE = of("ore/packed_ice");
    public static final ResourceKey<Feature> BLUE_ICE_ORE = of("ore/blue_ice");

    public static final ResourceKey<Feature> ASPHALT_ORE = of("ore/asphalt");
    public static final ResourceKey<Feature> BLUNITE_ORE = of("ore/blunite");

    // Trees
    public static final ResourceKey<Feature> BLUSH_SAKURA = of("tree/sakura/blush/regular");
    public static final ResourceKey<Feature> FANCY_BLUSH_SAKURA = of("tree/sakura/blush/fancy");
    public static final ResourceKey<Feature> BLUSH_SAKURA_BEES = of("tree/sakura/blush/bees");
    public static final ResourceKey<Feature> FANCY_BLUSH_SAKURA_BEES = of("tree/sakura/blush/fancy_bees");

    public static final ResourceKey<Feature> COTTON_SAKURA = of("tree/sakura/cotton/regular");
    public static final ResourceKey<Feature> FANCY_COTTON_SAKURA = of("tree/sakura/cotton/fancy");
    public static final ResourceKey<Feature> COTTON_SAKURA_BEES = of("tree/sakura/cotton/bees");
    public static final ResourceKey<Feature> FANCY_COTTON_SAKURA_BEES = of("tree/sakura/cotton/fancy_bees");

    public static final ResourceKey<Feature> SAP_MAPLE = of("tree/maple/sap/regular");
    public static final ResourceKey<Feature> FANCY_SAP_MAPLE = of("tree/maple/sap/fancy");
    public static final ResourceKey<Feature> SAP_MAPLE_BEES = of("tree/maple/sap/bees");
    public static final ResourceKey<Feature> FANCY_SAP_MAPLE_BEES = of("tree/maple/sap/fancy_bees");

    public static final ResourceKey<Feature> VERMILION_MAPLE = of("tree/maple/vermilion/regular");
    public static final ResourceKey<Feature> FANCY_VERMILION_MAPLE = of("tree/maple/vermilion/fancy");
    public static final ResourceKey<Feature> VERMILION_MAPLE_BEES = of("tree/maple/vermilion/bees");
    public static final ResourceKey<Feature> FANCY_VERMILION_MAPLE_BEES = of("tree/maple/vermilion/fancy_bees");

    public static final ResourceKey<Feature> FULVOUS_MAPLE = of("tree/maple/fulvous/regular");
    public static final ResourceKey<Feature> FANCY_FULVOUS_MAPLE = of("tree/maple/fulvous/fancy");
    public static final ResourceKey<Feature> FULVOUS_MAPLE_BEES = of("tree/maple/fulvous/bees");
    public static final ResourceKey<Feature> FANCY_FULVOUS_MAPLE_BEES = of("tree/maple/fulvous/fancy_bees");

    public static final ResourceKey<Feature> MIKADO_MAPLE = of("tree/maple/mikado/regular");
    public static final ResourceKey<Feature> FANCY_MIKADO_MAPLE = of("tree/maple/mikado/fancy");
    public static final ResourceKey<Feature> MIKADO_MAPLE_BEES = of("tree/maple/mikado/bees");
    public static final ResourceKey<Feature> FANCY_MIKADO_MAPLE_BEES = of("tree/maple/mikado/fancy_bees");

    public static final ResourceKey<Feature> PALM = of("tree/palm");

    public static final ResourceKey<Feature> SNOWY_MEGA_SPRUCE = of("tree/snowy_mega_spruce");

    public static final ResourceKey<Feature> DARK_AMARANTH_FUNGUS = of("tree/dark_amaranth_fungus");
    public static final ResourceKey<Feature> PLANTED_DARK_AMARANTH_FUNGUS = of("tree/dark_amaranth_fungus_planted");

    // Randomized trees
    public static final ResourceKey<Feature> BLUSH_SAKURA_GROVE_TREE = of("tree/blush_sakura_grove");
    public static final ResourceKey<Feature> COTTON_SAKURA_GROVE_TREE = of("tree/cotton_sakura_grove");

    public static final ResourceKey<Feature> CARNELIAN_TREEWAY_SAP_TREE = of("tree/carnelian_treeway/sap");
    public static final ResourceKey<Feature> CARNELIAN_TREEWAY_VERMILION_TREE = of("tree/carnelian_treeway/vermilion");
    public static final ResourceKey<Feature> CARNELIAN_TREEWAY_FULVOUS_TREE = of("tree/carnelian_treeway/fulvous");
    public static final ResourceKey<Feature> CARNELIAN_TREEWAY_MIKADO_TREE = of("tree/carnelian_treeway/mikado");

    public static final ResourceKey<Feature> CARNELIAN_TREEWAY_TREE = of("tree/carnelian_treeway");

    // Vegetation
    public static final ResourceKey<Feature> WATER_POOL_GRAVEL_DECORATED = of("water_pool_gravel_decorated");

    public static final ResourceKey<Feature> CUTE_LITTLE_ROCK = of("cute_little_rock");

    public static final ResourceKey<Feature> BLUEBERRY_BUSH = of("blueberry_bush");

    public static final ResourceKey<Feature> DARK_AMARANTH_FOREST_VEGETATION = of("dark_amaranth_forest_vegetation");
    public static final ResourceKey<Feature> DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION = of("dark_amaranth_forest_vegetation/bonemeal");

    public static final ResourceKey<Feature> COILED_VINES = of("coiled_vines");

    public static final ResourceKey<Feature> FALLEN_VERMILION_MAPLE_LEAVES = of("fallen_vermilion_maple_leaves");
    public static final ResourceKey<Feature> FALLEN_FULVOUS_MAPLE_LEAVES = of("fallen_fulvous_maple_leaves");
    public static final ResourceKey<Feature> FALLEN_MIKADO_MAPLE_LEAVES = of("fallen_mikado_maple_leaves");
    public static final ResourceKey<Feature> CARNELIAN_TREEWAY_FALLEN_LEAVES = of("carnelian_treeway/fallen_leaves");

    private static ResourceKey<Feature> of(String path) {
        return ResourceKey.create(Registries.FEATURE, Promenade.id(path));
    }
}
