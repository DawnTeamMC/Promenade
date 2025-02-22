package fr.hugman.promenade.world;

import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class PromenadeSaplingGenerators {
    public static final SaplingGenerator BLUSH_SAKURA = new SaplingGenerator("sakura/blush", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.BLUSH_SAKURA), Optional.of(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA),
            Optional.of(PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES)
    );
    public static final SaplingGenerator COTTON_SAKURA = new SaplingGenerator("sakura/cotton", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.COTTON_SAKURA), Optional.of(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA),
            Optional.of(PromenadeConfiguredFeatures.COTTON_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES)
    );

    public static final SaplingGenerator SAP_MAPLE = new SaplingGenerator("maple/sap", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.SAP_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.SAP_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES)
    );
    public static final SaplingGenerator VERMILION_MAPLE = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.VERMILION_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES)
    );
    public static final SaplingGenerator FULVOUS_MAPLE = new SaplingGenerator("maple/fulvous", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.FULVOUS_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES)
    );
    public static final SaplingGenerator MIKADO_MAPLE = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.MIKADO_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES)
    );

    public static final SaplingGenerator PALM = new SaplingGenerator("palm", Optional.empty(), Optional.of(PromenadeConfiguredFeatures.PALM), Optional.empty());

    public static final SaplingGenerator AURORAL_CYPRESS = new SaplingGenerator("auroral", Optional.empty(), Optional.of(PromenadeConfiguredFeatures.AURORAL_CYPRESS), Optional.empty());
}
