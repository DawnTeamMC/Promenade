package fr.hugman.promenade.world;

import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatureKeys;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class PromenadeSaplingGenerators {
    public static final SaplingGenerator PALM = new SaplingGenerator("palm", Optional.empty(), Optional.of(PromenadeConfiguredFeatureKeys.PALM), Optional.empty());

    public static final SaplingGenerator BLUSH_SAKURA = new SaplingGenerator("sakura/blush", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.BLUSH_SAKURA), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_BLUSH_SAKURA),
            Optional.of(PromenadeConfiguredFeatureKeys.BLUSH_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_BLUSH_SAKURA_BEES)
    );
    public static final SaplingGenerator COTTON_SAKURA = new SaplingGenerator("sakura/cotton", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.COTTON_SAKURA), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_COTTON_SAKURA),
            Optional.of(PromenadeConfiguredFeatureKeys.COTTON_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_COTTON_SAKURA_BEES)
    );

    public static final SaplingGenerator SAP_MAPLE = new SaplingGenerator("maple/sap", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.SAP_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_SAP_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.SAP_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_SAP_MAPLE_BEES)
    );
    public static final SaplingGenerator VERMILION_MAPLE = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.VERMILION_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_VERMILION_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.VERMILION_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_VERMILION_MAPLE_BEES)
    );
    public static final SaplingGenerator FULVOUS_MAPLE = new SaplingGenerator("maple/fulvous", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.FULVOUS_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_FULVOUS_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.FULVOUS_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_FULVOUS_MAPLE_BEES)
    );
    public static final SaplingGenerator MIKADO_MAPLE = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.MIKADO_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_MIKADO_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.MIKADO_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_MIKADO_MAPLE_BEES)
    );
}
