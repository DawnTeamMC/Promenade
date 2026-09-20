package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.BerryBushBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.data.provider.builders.PromenadeFeatureConfigs;
import fr.hugman.promenade.util.NoiseScale;
import fr.hugman.promenade.world.gen.feature.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusFeature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.RandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.WaterloggedVegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.OverlayFeature;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.OffsetPlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PromenadeConfiguredFeatureProvider extends FabricDynamicRegistryProvider {
    private static final PlacementModifier[] NETHER_FOREST_BONEMEAL_SPREAD = new PlacementModifier[]{
            CountPlacement.of(9), OffsetPlacement.ofTriangle(2, 0), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
    };

    public PromenadeConfiguredFeatureProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.FEATURE));
    }

    @Override
    public String getName() {
        return "Configured Features";
    }

    public static void register(BootstrapContext<Feature> registerable) {
        final var blocks = registerable.lookup(Registries.BLOCK);
        final var configured = registerable.lookup(Registries.FEATURE);
        final var placed = registerable.lookup(Registries.PLACED_FEATURE);
        final var soilBeneathTree = registerable.lookup(Registries.BLOCK_STATE_PROVIDER).getOrThrow(BlockStateProviders.SOIL_BENEATH_TREE);

        // Ores
        var isBaseStoneOverworld = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);

        of(registerable, PromenadeConfiguredFeatures.PACKED_ICE_ORE, new OreFeature(isBaseStoneOverworld, Blocks.PACKED_ICE.defaultBlockState(), 50));
        of(registerable, PromenadeConfiguredFeatures.BLUE_ICE_ORE, new OreFeature(isBaseStoneOverworld, Blocks.PACKED_ICE.defaultBlockState(), 10));
        of(registerable, PromenadeConfiguredFeatures.ASPHALT_ORE, new OreFeature(isBaseStoneOverworld, PromenadeBlocks.ASPHALT.defaultBlockState(), 48));
        of(registerable, PromenadeConfiguredFeatures.BLUNITE_ORE, new OreFeature(isBaseStoneOverworld, PromenadeBlocks.BLUNITE.defaultBlockState(), 48));

        // Trees
        var beehive005 = new BeehiveDecorator(0.05F);
        var beehive002 = new BeehiveDecorator(0.02F);

        of(registerable, PromenadeConfiguredFeatures.BLUSH_SAKURA, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, false, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, true, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, false, soilBeneathTree).decorators(List.of(beehive005)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES, PromenadeFeatureConfigs.sakura(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, true, soilBeneathTree).decorators(List.of(beehive005)).build());

        of(registerable, PromenadeConfiguredFeatures.COTTON_SAKURA, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, false, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, true, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.COTTON_SAKURA_BEES, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, false, soilBeneathTree).decorators(List.of(beehive005)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES, PromenadeFeatureConfigs.sakura(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, true, soilBeneathTree).decorators(List.of(beehive005)).build());

        of(registerable, PromenadeConfiguredFeatures.SAP_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, false, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, true, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.SAP_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, false, soilBeneathTree).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.SAP_MAPLE_LEAVES, true, soilBeneathTree).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.VERMILION_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, false, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, true, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, false, soilBeneathTree).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.VERMILION_MAPLE_LEAVES, true, soilBeneathTree).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.FULVOUS_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, false, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, true, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, false, soilBeneathTree).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, true, soilBeneathTree).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.MIKADO_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, false, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, true, soilBeneathTree).build());
        of(registerable, PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, false, soilBeneathTree).decorators(List.of(beehive002)).build());
        of(registerable, PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES, PromenadeFeatureConfigs.maple(PromenadeBlocks.MIKADO_MAPLE_LEAVES, true, soilBeneathTree).decorators(List.of(beehive002)).build());

        of(registerable, PromenadeConfiguredFeatures.PALM, PromenadeFeatureConfigs.palm().build());

        of(registerable, PromenadeConfiguredFeatures.SNOWY_MEGA_SPRUCE, PromenadeFeatureConfigs.snowyMegaSpruce(PromenadeBlocks.SNOWY_SPRUCE_LEAVES, soilBeneathTree).build());

        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FUNGUS, PromenadeFeatureConfigs.hugeDarkAmaranthFungus(false));
        of(registerable, PromenadeConfiguredFeatures.PLANTED_DARK_AMARANTH_FUNGUS, PromenadeFeatureConfigs.hugeDarkAmaranthFungus(true));

        // Vegetation
        of(registerable, PromenadeConfiguredFeatures.WATER_POOL_GRAVEL_DECORATED,
                new WaterloggedVegetationPatchFeature(
                        blocks.getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
                        BlockStateProvider.holderOf(Blocks.GRAVEL),
                        PlacementUtils.inlinePlaced(new RandomSelectorFeature(List.of(new WeightedPlacedFeature(PlacementUtils.inlinePlaced(configured.getOrThrow(VegetationFeatures.BAMBOO_SOME_PODZOL)), 0.4F)), PlacementUtils.inlinePlaced(configured.getOrThrow(VegetationFeatures.WATERLILY)))), //hello there
                        CaveSurface.FLOOR,
                        ConstantInt.of(3),
                        0.8F,
                        5,
                        0.15F,
                        UniformInt.of(4, 7),
                        0.7F
                ));

        of(registerable, PromenadeConfiguredFeatures.CUTE_LITTLE_ROCK, new BoulderFeature(
                Holder.direct(new WeightedStateProvider(
                        WeightedList.<BlockState>builder()
                                .add(Blocks.STONE.defaultBlockState(), 80)
                                .add(Blocks.CALCITE.defaultBlockState(), 20)
                )),
                BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK),
                UniformInt.of(3, 4)
        ));

        of(registerable, PromenadeConfiguredFeatures.BLUEBERRY_BUSH, new SimpleBlockFeature(BlockStateProvider.holderOf(PromenadeBlocks.BLUEBERRY_BUSH.defaultBlockState().setValue(BerryBushBlock.AGE, 3))));

        WeightedStateProvider darkAmaranthVegetation = new WeightedStateProvider(
                WeightedList.<BlockState>builder()
                        .add(PromenadeBlocks.DARK_AMARANTH_ROOTS.defaultBlockState(), 87)
                        .add(PromenadeBlocks.DARK_AMARANTH_FUNGUS.defaultBlockState(), 11)
                        .add(Blocks.WARPED_FUNGUS.defaultBlockState(), 1)
                        .add(Blocks.WARPED_ROOTS.defaultBlockState(), 1)
        );

        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_VEGETATION, new SimpleBlockFeature(darkAmaranthVegetation));
        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION,
                new OverlayFeature(HolderSet.direct(PlacementUtils.inlinePlaced(new SimpleBlockFeature(darkAmaranthVegetation), NETHER_FOREST_BONEMEAL_SPREAD))));

        var horizontalDirections = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);
        of(registerable, PromenadeConfiguredFeatures.COILED_VINES, new CoiledVinesFeature(8, 4, 8, horizontalDirections));

        of(registerable, PromenadeConfiguredFeatures.FALLEN_VERMILION_MAPLE_LEAVES, new SimpleBlockFeature(Holder.direct(new WeightedStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES, 1, 3)))));
        of(registerable, PromenadeConfiguredFeatures.FALLEN_FULVOUS_MAPLE_LEAVES, new SimpleBlockFeature(Holder.direct(new WeightedStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES, 1, 3)))));
        of(registerable, PromenadeConfiguredFeatures.FALLEN_MIKADO_MAPLE_LEAVES, new SimpleBlockFeature(Holder.direct(new WeightedStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES, 1, 3)))));

        // Grouped features
        of(registerable, PromenadeConfiguredFeatures.BLUSH_SAKURA_GROVE_TREE,
                new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_BLUSH_SAKURA), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.COTTON_SAKURA), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.BLUSH_SAKURA))
        );
        of(registerable, PromenadeConfiguredFeatures.COTTON_SAKURA_GROVE_TREE,
                new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_COTTON_SAKURA), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.BLUSH_SAKURA), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.COTTON_SAKURA))
        );

        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_SAP_TREE,
                new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_SAP_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_SAP_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.SAP_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_VERMILION_TREE,
                new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_VERMILION_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_VERMILION_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.VERMILION_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FULVOUS_TREE,
                new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.FULVOUS_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_MIKADO_TREE,
                new RandomSelectorFeature(List.of(
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_MIKADO_MAPLE_BEES), 0.1f),
                        new WeightedPlacedFeature(placed.getOrThrow(PromenadePlacedFeatures.FANCY_MIKADO_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.MIKADO_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_TREE,
                new NoisePickedFeature(NoiseScale.of(200.0f), List.of(
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_SAP_TREE)), -1.0f, 1.0f),
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_VERMILION_TREE)), 0.2f, 0.95f),
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FULVOUS_TREE)), -0.5f, 0.5f),
                        new NoisePickedFeatureEntry(PlacementUtils.inlinePlaced(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_MIKADO_TREE)), -0.95f, -0.2f)
                ))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES,
                new NoisePickedFeature(NoiseScale.of(200.0f), List.of(
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

    private static void of(BootstrapContext<Feature> registry, ResourceKey<Feature> key, Feature feature) {
        registry.register(key, feature);
    }
}