package fr.hugman.promenade.world;

import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;

public class PromenadeSaplingGenerators {
    public static final AbstractTreeGrower BLUSH_SAKURA = fancyGrower(
            PromenadeConfiguredFeatures.BLUSH_SAKURA, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA,
            PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES
    );
    public static final AbstractTreeGrower COTTON_SAKURA = fancyGrower(
            PromenadeConfiguredFeatures.COTTON_SAKURA, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA,
            PromenadeConfiguredFeatures.COTTON_SAKURA_BEES, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES
    );

    public static final AbstractTreeGrower SAP_MAPLE = fancyGrower(
            PromenadeConfiguredFeatures.SAP_MAPLE, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE,
            PromenadeConfiguredFeatures.SAP_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES
    );
    public static final AbstractTreeGrower VERMILION_MAPLE = fancyGrower(
            PromenadeConfiguredFeatures.VERMILION_MAPLE, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE,
            PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES
    );
    public static final AbstractTreeGrower FULVOUS_MAPLE = fancyGrower(
            PromenadeConfiguredFeatures.FULVOUS_MAPLE, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE,
            PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES
    );
    public static final AbstractTreeGrower MIKADO_MAPLE = fancyGrower(
            PromenadeConfiguredFeatures.MIKADO_MAPLE, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE,
            PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES
    );

    public static final AbstractTreeGrower PALM = new AbstractTreeGrower() {
        @Override
        protected @NotNull ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
            return PromenadeConfiguredFeatures.PALM;
        }
    };

    /**
     * Creates a tree grower that grows a fancy variant 10% of the time, with bee-nest variants used when flowers are nearby.
     */
    private static AbstractTreeGrower fancyGrower(
            ResourceKey<ConfiguredFeature<?, ?>> tree,
            ResourceKey<ConfiguredFeature<?, ?>> fancyTree,
            ResourceKey<ConfiguredFeature<?, ?>> beeTree,
            ResourceKey<ConfiguredFeature<?, ?>> fancyBeeTree
    ) {
        return new AbstractTreeGrower() {
            @Override
            protected @NotNull ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
                if (random.nextFloat() < 0.1F) {
                    return hasFlowers ? fancyBeeTree : fancyTree;
                }
                return hasFlowers ? beeTree : tree;
            }
        };
    }
}
