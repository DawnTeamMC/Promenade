package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.BerryBushBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.data.provider.builders.PromenadeFeatureConfigs;
import fr.hugman.promenade.util.NoiseScale;
import fr.hugman.promenade.world.gen.feature.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeafLitterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PromenadeConfiguredFeatureProvider extends FabricDynamicRegistryProvider {
    public PromenadeConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
    }

    @Override
    public String getName() {
        return "Configured Features";
    }

    public static void register(BootstrapContext<ConfiguredFeature<?, ?>> registerable) {
        final var configured = registerable.lookup(Registries.CONFIGURED_FEATURE);
        final var placed = registerable.lookup(Registries.PLACED_FEATURE);

        // Ores
        var isBaseStoneOverworld = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);

        of(registerable, PromenadeConfiguredFeatures.PACKED_ICE_ORE, Feature.ORE, new OreConfiguration(isBaseStoneOverworld, Blocks.PACKED_ICE.defaultBlockState(), 50));
        of(registerable, PromenadeConfiguredFeatures.BLUE_ICE_ORE, Feature.ORE, new OreConfiguration(isBaseStoneOverworld, Blocks.PACKED_ICE.defaultBlockState(), 10));
        of(registerable, PromenadeConfiguredFeatures.ASPHALT_ORE, Feature.ORE, new OreConfiguration(isBaseStoneOverworld, PromenadeBlocks.ASPHALT.defaultBlockState(), 48));
        of(registerable, PromenadeConfiguredFeatures.BLUNITE_ORE, Feature.ORE, new OreConfiguration(isBaseStoneOverworld, PromenadeBlocks.BLUNITE.defaultBlockState(), 48));

        // Trees
        var beehive005 = new BeehiveDecorator(0.05F);
        var beehive002 = new BeehiveDecorator(0.02F);

        of(registerable, PromenadeConfiguredFeatures.BLUSH_SAKURA, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, false).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, true).build());
        of(registerable, PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, false).decorators(List.of(beehive005)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, true).decorators(List.of(beehive005)).build());

        of(registerable, PromenadeConfiguredFeatures.COTTON_SAKURA, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, false).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, true).build());
        of(registerable, PromenadeConfiguredFeatures.COTTON_SAKURA_BEES, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, false).decorators(List.of(beehive005)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES, Feature.TREE, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, true).decorators(List.of(beehive005)).build());

        of(registerable, PromenadeConfiguredFeatures.SAP_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, false).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, true).build());
        of(registerable, PromenadeConfiguredFeatures.SAP_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, false).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, true).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.VERMILION_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, false).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, true).build());
        of(registerable, PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, false).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, true).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.FULVOUS_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, false).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, true).build());
        of(registerable, PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, false).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, true).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.MIKADO_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, false).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, true).build());
        of(registerable, PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, false).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES, Feature.TREE, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, true).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.PALM, Feature.TREE, PromenadeFeatureConfigs.palm().build());

        of(registerable, PromenadeConfiguredFeatures.SNOWY_MEGA_SPRUCE, Feature.TREE, PromenadeFeatureConfigs.snowyMegaSpruce(PromenadeBlocks.SNOWY_SPRUCE_LEAVES).build());

        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FUNGUS, Feature.HUGE_FUNGUS, PromenadeFeatureConfigs.hugeDarkAmaranthFungus(false));
        of(registerable, PromenadeConfiguredFeatures.PLANTED_DARK_AMARANTH_FUNGUS, Feature.HUGE_FUNGUS, PromenadeFeatureConfigs.hugeDarkAmaranthFungus(true));

        // Vegetation
        of(registerable, PromenadeConfiguredFeatures.WATER_POOL_GRAVEL_DECORATED, Feature.WATERLOGGED_VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        BlockTags.LUSH_GROUND_REPLACEABLE,
                        BlockStateProvider.simple(Blocks.GRAVEL),
                        PlacementUtils.inlinePlaced(Holder.direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configured.getOrThrow(VegetationFeatures.BAMBOO_SOME_PODZOL)), 0.4F)), PlacementUtils.inlinePlaced(configured.getOrThrow(VegetationFeatures.PATCH_WATERLILY)))))), //hello there
                        CaveSurface.FLOOR,
                        ConstantInt.of(3),
                        0.8F,
                        5,
                        0.15F,
                        UniformInt.of(4, 7),
                        0.7F
                ));

        of(registerable, PromenadeConfiguredFeatures.CUTE_LITTLE_ROCK, PromenadeFeatures.BOULDER, new BoulderFeatureConfig(
                new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(Blocks.STONE.defaultBlockState(), 80)
                                .add(Blocks.CALCITE.defaultBlockState(), 20)
                ),
                BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK),
                UniformInt.of(3, 4)
        ));


        of(registerable, PromenadeConfiguredFeatures.BAMBOO_PATCH, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(25, 3, 3,
                        PlacementUtils.inlinePlaced(configured.getOrThrow(VegetationFeatures.BAMBOO_SOME_PODZOL), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE))
                ));
        of(registerable, PromenadeConfiguredFeatures.BLUEBERRY_BUSH_PATCH, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(PromenadeBlocks.BLUEBERRY_BUSH.defaultBlockState().setValue(BerryBushBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK)
                ));

        WeightedStateProvider darkAmaranthVegetation = new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(PromenadeBlocks.DARK_AMARANTH_ROOTS.defaultBlockState(), 87)
                        .add(PromenadeBlocks.DARK_AMARANTH_FUNGUS.defaultBlockState(), 11)
                        .add(Blocks.WARPED_FUNGUS.defaultBlockState(), 1)
                        .add(Blocks.WARPED_ROOTS.defaultBlockState(), 1)
        );

        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_VEGETATION, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationConfig(darkAmaranthVegetation, 8, 4));
        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationConfig(darkAmaranthVegetation, 3, 1));

        var horizontalDirections = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);
        of(registerable, PromenadeConfiguredFeatures.COILED_VINES, PromenadeFeatures.COILED_VINES, new CoiledVinesFeatureConfig(8, 4, 8, horizontalDirections));

        of(registerable, PromenadeConfiguredFeatures.FALLEN_VERMILION_MAPLE_LEAVES, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES, 1, 3)))
        ));
        of(registerable, PromenadeConfiguredFeatures.FALLEN_FULVOUS_MAPLE_LEAVES, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES, 1, 3)))
        ));
        of(registerable, PromenadeConfiguredFeatures.FALLEN_MIKADO_MAPLE_LEAVES, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES, 1, 3)))
        ));

        // Grouped features
        of(registerable, PromenadeConfiguredFeatures.BLUSH_SAKURA_GROVE_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_BLUSH_SAKURA), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.COTTON_SAKURA), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.BLUSH_SAKURA))
        );
        of(registerable, PromenadeConfiguredFeatures.COTTON_SAKURA_GROVE_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_COTTON_SAKURA), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.BLUSH_SAKURA), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.COTTON_SAKURA))
        );

        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_SAP_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_SAP_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_SAP_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.SAP_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_VERMILION_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_VERMILION_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_VERMILION_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.VERMILION_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FULVOUS_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.FULVOUS_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_MIKADO_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_MIKADO_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_MIKADO_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.MIKADO_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_TREE, PromenadeFeatures.NOISE_PICKED,
                new NoisePickedFeatureConfig(NoiseScale.of(200.0f), List.of(
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_SAP_TREE)), -1.0f, 1.0f),
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_VERMILION_TREE)), 0.2f, 0.95f),
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FULVOUS_TREE)), -0.5f, 0.5f),
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_MIKADO_TREE)), -0.95f, -0.2f)
                ))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES, PromenadeFeatures.NOISE_PICKED,
                new NoisePickedFeatureConfig(NoiseScale.of(200.0f), List.of(
                        new NoisePickedFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FALLEN_VERMILION_MAPLE_LEAVES), 0.2f, 0.95f),
                        new NoisePickedFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FALLEN_FULVOUS_MAPLE_LEAVES), -0.5f, 0.5f),
                        new NoisePickedFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FALLEN_MIKADO_MAPLE_LEAVES), -0.95f, -0.2f)
                ))
        );
    }

    public static WeightedList.Builder<BlockState> fallenLeaves(Block block, int min, int max) {
        return segmentedBlock(block, min, max, LeafLitterBlock.AMOUNT, LeafLitterBlock.FACING);
    }

    private static WeightedList.Builder<BlockState> segmentedBlock(Block block, int min, int max, IntegerProperty amountProperty, EnumProperty<Direction> facingProperty) {
        WeightedList.Builder<BlockState> builder = WeightedList.builder();

        for (int i = min; i <= max; ++i) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                builder.add(block.defaultBlockState().setValue(amountProperty, i).setValue(facingProperty, direction), 1);
            }
        }

        return builder;
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void of(BootstrapContext<ConfiguredFeature<?, ?>> registry, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        FeatureUtils.register(registry, key, feature, config);
    }
}