package fr.hugman.promenade.block;

import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatureKeys;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class PromenadeSaplingGenerators {

    public static final SaplingGenerator BLUSH_SAKURA_SAPLING_GENERATOR = new SaplingGenerator("sakura/blush", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.BLUSH_SAKURA), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_BLUSH_SAKURA),
            Optional.of(PromenadeConfiguredFeatureKeys.BLUSH_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_BLUSH_SAKURA_BEES)
    );
    public static final SaplingGenerator COTTON_SAKURA_SAPLING_GENERATOR = new SaplingGenerator("sakura/cotton", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.COTTON_SAKURA), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_COTTON_SAKURA),
            Optional.of(PromenadeConfiguredFeatureKeys.COTTON_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_COTTON_SAKURA_BEES)
    );

    public static final SaplingGenerator SAP_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/sap", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.SAP_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_SAP_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.SAP_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_SAP_MAPLE_BEES)
    );
    public static final SaplingGenerator VERMILION_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.VERMILION_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_VERMILION_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.VERMILION_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_VERMILION_MAPLE_BEES)
    );
    public static final SaplingGenerator FULVOUS_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/fulvous", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.FULVOUS_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_FULVOUS_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.FULVOUS_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_FULVOUS_MAPLE_BEES)
    );
    public static final SaplingGenerator MIKADO_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatureKeys.MIKADO_MAPLE), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_MIKADO_MAPLE),
            Optional.of(PromenadeConfiguredFeatureKeys.MIKADO_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatureKeys.FANCY_MIKADO_MAPLE_BEES)
    );

    public static final SaplingGenerator PALM_SAPLING_GENERATOR = new SaplingGenerator("palm", Optional.empty(), Optional.of(PromenadeConfiguredFeatureKeys.PALM), Optional.empty());

    public static final SaplingGenerator DUSK_CYPRESS_SAPLING_GENERATOR = new SaplingGenerator("dusk", Optional.empty(), Optional.of(PromenadeConfiguredFeatureKeys.DUSK_CYPRESS), Optional.empty());
}
