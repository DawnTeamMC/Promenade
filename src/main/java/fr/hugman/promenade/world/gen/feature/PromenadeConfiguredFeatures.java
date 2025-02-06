package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class PromenadeConfiguredFeatures {
    // Ores
    public static final RegistryKey<ConfiguredFeature<?, ?>> PACKED_ICE_ORE = of("ore/packed_ice");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUE_ICE_ORE = of("ore/blue_ice");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ASPHALT_ORE = of("ore/asphalt");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUNITE_ORE = of("ore/blunite");

    // Trees
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUSH_SAKURA = of("tree/sakura/blush/regular");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_BLUSH_SAKURA = of("tree/sakura/blush/fancy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUSH_SAKURA_BEES = of("tree/sakura/blush/bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_BLUSH_SAKURA_BEES = of("tree/sakura/blush/fancy_bees");

    public static final RegistryKey<ConfiguredFeature<?, ?>> COTTON_SAKURA = of("tree/sakura/cotton/regular");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_COTTON_SAKURA = of("tree/sakura/cotton/fancy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COTTON_SAKURA_BEES = of("tree/sakura/cotton/bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_COTTON_SAKURA_BEES = of("tree/sakura/cotton/fancy_bees");

    public static final RegistryKey<ConfiguredFeature<?, ?>> SAP_MAPLE = of("tree/maple/sap/regular");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_SAP_MAPLE = of("tree/maple/sap/fancy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAP_MAPLE_BEES = of("tree/maple/sap/bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_SAP_MAPLE_BEES = of("tree/maple/sap/fancy_bees");

    public static final RegistryKey<ConfiguredFeature<?, ?>> VERMILION_MAPLE = of("tree/maple/vermilion/regular");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_VERMILION_MAPLE = of("tree/maple/vermilion/fancy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VERMILION_MAPLE_BEES = of("tree/maple/vermilion/bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_VERMILION_MAPLE_BEES = of("tree/maple/vermilion/fancy_bees");

    public static final RegistryKey<ConfiguredFeature<?, ?>> FULVOUS_MAPLE = of("tree/maple/fulvous/regular");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_FULVOUS_MAPLE = of("tree/maple/fulvous/fancy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FULVOUS_MAPLE_BEES = of("tree/maple/fulvous/bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_FULVOUS_MAPLE_BEES = of("tree/maple/fulvous/fancy_bees");

    public static final RegistryKey<ConfiguredFeature<?, ?>> MIKADO_MAPLE = of("tree/maple/mikado/regular");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_MIKADO_MAPLE = of("tree/maple/mikado/fancy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIKADO_MAPLE_BEES = of("tree/maple/mikado/bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_MIKADO_MAPLE_BEES = of("tree/maple/mikado/fancy_bees");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PALM = of("tree/palm");

    public static final RegistryKey<ConfiguredFeature<?, ?>> SNOWY_MEGA_SPRUCE = of("tree/snowy_mega_spruce");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DARK_AMARANTH_FUNGUS = of("tree/dark_amaranth_fungus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PLANTED_DARK_AMARANTH_FUNGUS = of("tree/dark_amaranth_fungus_planted");

    // Randomized trees
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUSH_SAKURA_GROVE_TREE = of("tree/blush_sakura_grove");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COTTON_SAKURA_GROVE_TREE = of("tree/cotton_sakura_grove");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_SAP_TREE = of("tree/carnelian_treeway/sap");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_VERMILION_TREE = of("tree/carnelian_treeway/vermilion");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_FULVOUS_TREE = of("tree/carnelian_treeway/fulvous");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CARNELIAN_TREEWAY_MIKADO_TREE = of("tree/carnelian_treeway/mikado");

    // Vegetation
    public static final RegistryKey<ConfiguredFeature<?, ?>> WATER_POOL_GRAVEL = of("water_pool_gravel");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WATER_POOL_GRAVEL_DECORATED = of("water_pool_gravel_decorated");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CUTE_LITTLE_ROCK = of("cute_little_rock");

    public static final RegistryKey<ConfiguredFeature<?, ?>> BAMBOO_PATCH = of("bamboo_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUEBERRY_BUSH_PATCH = of("blueberry_bush_patch");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DARK_AMARANTH_FOREST_VEGETATION = of("dark_amaranth_forest_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION = of("dark_amaranth_forest_vegetation/bonemeal");

    public static final RegistryKey<ConfiguredFeature<?, ?>> COILED_VINES = of("coiled_vines");

    private static RegistryKey<ConfiguredFeature<?, ?>> of(String path) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Promenade.id(path));
    }
}
