package fr.hugman.promenade.world;

import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class PromenadeSaplingGenerators {
    public static final TreeGrower BLUSH_SAKURA = fancyGrower("sakura/blush",
            PromenadeConfiguredFeatures.BLUSH_SAKURA, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA,
            PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES
    );
    public static final TreeGrower COTTON_SAKURA = fancyGrower("sakura/cotton",
            PromenadeConfiguredFeatures.COTTON_SAKURA, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA,
            PromenadeConfiguredFeatures.COTTON_SAKURA_BEES, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES
    );

    public static final TreeGrower SAP_MAPLE = fancyGrower("maple/sap",
            PromenadeConfiguredFeatures.SAP_MAPLE, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE,
            PromenadeConfiguredFeatures.SAP_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES
    );
    public static final TreeGrower VERMILION_MAPLE = fancyGrower("maple/vermilion",
            PromenadeConfiguredFeatures.VERMILION_MAPLE, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE,
            PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES
    );
    public static final TreeGrower FULVOUS_MAPLE = fancyGrower("maple/fulvous",
            PromenadeConfiguredFeatures.FULVOUS_MAPLE, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE,
            PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES
    );
    public static final TreeGrower MIKADO_MAPLE = fancyGrower("maple/mikado",
            PromenadeConfiguredFeatures.MIKADO_MAPLE, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE,
            PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES
    );

    public static final TreeGrower PALM = new TreeGrower("palm",
            Optional.empty(), Optional.of(PromenadeConfiguredFeatures.PALM), Optional.empty()
    );

    /**
     * Creates a tree grower that grows a fancy variant 10% of the time, with bee-nest variants used when flowers are nearby.
     */
    private static TreeGrower fancyGrower(
            String name,
            ResourceKey<ConfiguredFeature<?, ?>> tree,
            ResourceKey<ConfiguredFeature<?, ?>> fancyTree,
            ResourceKey<ConfiguredFeature<?, ?>> beeTree,
            ResourceKey<ConfiguredFeature<?, ?>> fancyBeeTree
    ) {
        return new TreeGrower(name, 0.1F, Optional.empty(), Optional.empty(),
                Optional.of(tree), Optional.of(fancyTree),
                Optional.of(beeTree), Optional.of(fancyBeeTree)
        );
    }
}
