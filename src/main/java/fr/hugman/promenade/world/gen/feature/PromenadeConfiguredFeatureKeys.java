package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class PromenadeConfiguredFeatureKeys {
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALM = of("tree/palm");

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

    public static final RegistryKey<ConfiguredFeature<?, ?>> PLANTED_AMARANTH_FUNGUS = of("amaranth_fungus/planted");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION = of("dark_amaranth_forest_vegetation/bonemeal");


    private static RegistryKey<ConfiguredFeature<?, ?>> of(String path) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Promenade.id(path));
    }
}
