package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class PromenadeConfiguredFeatures {
    // Ores
    public static final ResourceKey<ConfiguredFeature<?, ?>> PACKED_ICE_ORE = of("ore/packed_ice");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ICE_ORE = of("ore/blue_ice");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPHALT_ORE = of("ore/asphalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUNITE_ORE = of("ore/blunite");

    // Trees
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUSH_SAKURA = of("tree/sakura/blush/regular");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_BLUSH_SAKURA = of("tree/sakura/blush/fancy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUSH_SAKURA_BEES = of("tree/sakura/blush/bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_BLUSH_SAKURA_BEES = of("tree/sakura/blush/fancy_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> COTTON_SAKURA = of("tree/sakura/cotton/regular");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_COTTON_SAKURA = of("tree/sakura/cotton/fancy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COTTON_SAKURA_BEES = of("tree/sakura/cotton/bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_COTTON_SAKURA_BEES = of("tree/sakura/cotton/fancy_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SAP_MAPLE = of("tree/maple/sap/regular");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_SAP_MAPLE = of("tree/maple/sap/fancy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAP_MAPLE_BEES = of("tree/maple/sap/bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_SAP_MAPLE_BEES = of("tree/maple/sap/fancy_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> VERMILION_MAPLE = of("tree/maple/vermilion/regular");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_VERMILION_MAPLE = of("tree/maple/vermilion/fancy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERMILION_MAPLE_BEES = of("tree/maple/vermilion/bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_VERMILION_MAPLE_BEES = of("tree/maple/vermilion/fancy_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FULVOUS_MAPLE = of("tree/maple/fulvous/regular");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_FULVOUS_MAPLE = of("tree/maple/fulvous/fancy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FULVOUS_MAPLE_BEES = of("tree/maple/fulvous/bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_FULVOUS_MAPLE_BEES = of("tree/maple/fulvous/fancy_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MIKADO_MAPLE = of("tree/maple/mikado/regular");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_MIKADO_MAPLE = of("tree/maple/mikado/fancy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIKADO_MAPLE_BEES = of("tree/maple/mikado/bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_MIKADO_MAPLE_BEES = of("tree/maple/mikado/fancy_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM = of("tree/palm");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWY_MEGA_SPRUCE = of("tree/snowy_mega_spruce");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_AMARANTH_FUNGUS = of("tree/dark_amaranth_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLANTED_DARK_AMARANTH_FUNGUS = of("tree/dark_amaranth_fungus_planted");

    // Randomized trees
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUSH_SAKURA_GROVE_TREE = of("tree/blush_sakura_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COTTON_SAKURA_GROVE_TREE = of("tree/cotton_sakura_grove");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_SAP_TREE = of("tree/carnelian_treeway/sap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_VERMILION_TREE = of("tree/carnelian_treeway/vermilion");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_FULVOUS_TREE = of("tree/carnelian_treeway/fulvous");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_MIKADO_TREE = of("tree/carnelian_treeway/mikado");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_TREE = of("tree/carnelian_treeway");

    // Vegetation
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_POOL_GRAVEL_DECORATED = of("water_pool_gravel_decorated");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CUTE_LITTLE_ROCK = of("cute_little_rock");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH = of("blueberry_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_AMARANTH_FOREST_VEGETATION = of("dark_amaranth_forest_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION = of("dark_amaranth_forest_vegetation/bonemeal");

    public static final ResourceKey<ConfiguredFeature<?, ?>> COILED_VINES = of("coiled_vines");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_VERMILION_MAPLE_LEAVES = of("fallen_vermilion_maple_leaves");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_FULVOUS_MAPLE_LEAVES = of("fallen_fulvous_maple_leaves");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_MIKADO_MAPLE_LEAVES = of("fallen_mikado_maple_leaves");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_FALLEN_LEAVES = of("carnelian_treeway/fallen_leaves");

    private static ResourceKey<ConfiguredFeature<?, ?>> of(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Promenade.id(path));
    }
}
