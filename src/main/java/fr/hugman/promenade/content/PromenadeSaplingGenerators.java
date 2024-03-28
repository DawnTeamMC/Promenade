package fr.hugman.promenade.content;

import fr.hugman.promenade.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

import java.util.Optional;

public class PromenadeSaplingGenerators {
    public static final SaplingGenerator PALM_SAPLING_GENERATOR = new SaplingGenerator("palm", Optional.empty(), Optional.of(PromenadeConfiguredFeatures.PALM), Optional.empty());

    public static final SaplingGenerator BLUSH_SAKURA_SAPLING_GENERATOR = new SaplingGenerator("sakura/blush", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.BLUSH_SAKURA), Optional.of(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA),
            Optional.of(PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES)
    );
    public static final SaplingGenerator COTTON_SAKURA_SAPLING_GENERATOR = new SaplingGenerator("sakura/cotton", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.COTTON_SAKURA), Optional.of(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA),
            Optional.of(PromenadeConfiguredFeatures.COTTON_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES)
    );

    public static final SaplingGenerator SAP_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/sap", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.SAP_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.SAP_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES)
    );
    public static final SaplingGenerator VERMILION_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.VERMILION_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES)
    );
    public static final SaplingGenerator FULVOUS_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/fulvous", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.FULVOUS_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES)
    );
    public static final SaplingGenerator MIKADO_MAPLE_SAPLING_GENERATOR = new SaplingGenerator("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.MIKADO_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES)
    );
}
