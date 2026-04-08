package fr.hugman.promenade.world;

import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import java.util.Optional;
import net.minecraft.world.level.block.grower.TreeGrower;

public class PromenadeSaplingGenerators {
    public static final TreeGrower BLUSH_SAKURA = new TreeGrower("sakura/blush", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.BLUSH_SAKURA), Optional.of(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA),
            Optional.of(PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES)
    );
    public static final TreeGrower COTTON_SAKURA = new TreeGrower("sakura/cotton", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.COTTON_SAKURA), Optional.of(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA),
            Optional.of(PromenadeConfiguredFeatures.COTTON_SAKURA_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES)
    );

    public static final TreeGrower SAP_MAPLE = new TreeGrower("maple/sap", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.SAP_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.SAP_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES)
    );
    public static final TreeGrower VERMILION_MAPLE = new TreeGrower("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.VERMILION_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES)
    );
    public static final TreeGrower FULVOUS_MAPLE = new TreeGrower("maple/fulvous", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.FULVOUS_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES)
    );
    public static final TreeGrower MIKADO_MAPLE = new TreeGrower("maple/vermilion", 0.1F, Optional.empty(), Optional.empty(),
            Optional.of(PromenadeConfiguredFeatures.MIKADO_MAPLE), Optional.of(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE),
            Optional.of(PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES), Optional.of(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES)
    );

    public static final TreeGrower PALM = new TreeGrower("palm", Optional.empty(), Optional.of(PromenadeConfiguredFeatures.PALM), Optional.empty());
}
