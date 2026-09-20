package fr.hugman.promenade.world;

import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;

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
            WeightedList.of(PromenadeConfiguredFeatures.PALM), WeightedList.of(), WeightedList.of(), PromenadeConfiguredFeatures.PALM
    );

    /**
     * Creates a tree grower that grows a fancy variant 10% of the time, with bee-nest variants used when flowers are nearby.
     */
    private static TreeGrower fancyGrower(
            String name,
            ResourceKey<Feature> tree,
            ResourceKey<Feature> fancyTree,
            ResourceKey<Feature> beeTree,
            ResourceKey<Feature> fancyBeeTree
    ) {
        return new TreeGrower(name,
                WeightedList.of(new Weighted<>(tree, 9), new Weighted<>(fancyTree, 1)),
                WeightedList.of(),
                WeightedList.of(new Weighted<>(beeTree, 9), new Weighted<>(fancyBeeTree, 1)),
                tree
        );
    }
}
