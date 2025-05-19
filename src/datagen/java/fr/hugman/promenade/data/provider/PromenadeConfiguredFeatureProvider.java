package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.BerryBushBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.data.provider.builders.PromenadeFeatureConfigs;
import fr.hugman.promenade.util.NoiseScale;
import fr.hugman.promenade.world.gen.feature.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeafLitterBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PromenadeConfiguredFeatureProvider extends FabricDynamicRegistryProvider {
    public PromenadeConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.CONFIGURED_FEATURE));
    }

    @Override
    public String getName() {
        return "Configured Features";
    }

    public static void register(Registerable<ConfiguredFeature<?, ?>> registerable) {
        final var configured = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        final var placed = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        // Ores
        var isBaseStoneOverworld = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);

        of(registerable, PromenadeConfiguredFeatures.PACKED_ICE_ORE, Feature.ORE, new OreFeatureConfig(isBaseStoneOverworld, Blocks.PACKED_ICE.getDefaultState(), 50));
        of(registerable, PromenadeConfiguredFeatures.BLUE_ICE_ORE, Feature.ORE, new OreFeatureConfig(isBaseStoneOverworld, Blocks.PACKED_ICE.getDefaultState(), 10));
        of(registerable, PromenadeConfiguredFeatures.ASPHALT_ORE, Feature.ORE, new OreFeatureConfig(isBaseStoneOverworld, PromenadeBlocks.ASPHALT.getDefaultState(), 48));
        of(registerable, PromenadeConfiguredFeatures.BLUNITE_ORE, Feature.ORE, new OreFeatureConfig(isBaseStoneOverworld, PromenadeBlocks.BLUNITE.getDefaultState(), 48));

        // Trees
        var beehive005 = new BeehiveTreeDecorator(0.05F);
        var beehive002 = new BeehiveTreeDecorator(0.02F);

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
                new VegetationPatchFeatureConfig(
                        BlockTags.LUSH_GROUND_REPLACEABLE,
                        BlockStateProvider.of(Blocks.GRAVEL),
                        PlacedFeatures.createEntry(RegistryEntry.of(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(PlacedFeatures.createEntry(configured.getOrThrow(VegetationConfiguredFeatures.BAMBOO_SOME_PODZOL)), 0.4F)), PlacedFeatures.createEntry(configured.getOrThrow(VegetationConfiguredFeatures.PATCH_WATERLILY)))))), //hello there
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(3),
                        0.8F,
                        5,
                        0.15F,
                        UniformIntProvider.create(4, 7),
                        0.7F
                ));

        of(registerable, PromenadeConfiguredFeatures.CUTE_LITTLE_ROCK, PromenadeFeatures.BOULDER, new BoulderFeatureConfig(
                new WeightedBlockStateProvider(
                        Pool.<BlockState>builder()
                                .add(Blocks.STONE.getDefaultState(), 80)
                                .add(Blocks.CALCITE.getDefaultState(), 20)
                ),
                BlockPredicate.matchingBlocks(Blocks.GRASS_BLOCK),
                UniformIntProvider.create(3, 4)
        ));


        of(registerable, PromenadeConfiguredFeatures.BAMBOO_PATCH, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(25, 3, 3,
                        PlacedFeatures.createEntry(configured.getOrThrow(VegetationConfiguredFeatures.BAMBOO_SOME_PODZOL), BlockFilterPlacementModifier.of(BlockPredicate.IS_AIR))
                ));
        of(registerable, PromenadeConfiguredFeatures.BLUEBERRY_BUSH_PATCH, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(PromenadeBlocks.BLUEBERRY_BUSH.getDefaultState().with(BerryBushBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK)
                ));

        WeightedBlockStateProvider darkAmaranthVegetation = new WeightedBlockStateProvider(
                Pool.<BlockState>builder()
                        .add(PromenadeBlocks.DARK_AMARANTH_ROOTS.getDefaultState(), 87)
                        .add(PromenadeBlocks.DARK_AMARANTH_FUNGUS.getDefaultState(), 11)
                        .add(Blocks.WARPED_FUNGUS.getDefaultState(), 1)
                        .add(Blocks.WARPED_ROOTS.getDefaultState(), 1)
        );

        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_VEGETATION, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationFeatureConfig(darkAmaranthVegetation, 8, 4));
        of(registerable, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION, Feature.NETHER_FOREST_VEGETATION, new NetherForestVegetationFeatureConfig(darkAmaranthVegetation, 3, 1));

        var horizontalDirections = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);
        of(registerable, PromenadeConfiguredFeatures.COILED_VINES, PromenadeFeatures.COILED_VINES, new CoiledVinesFeatureConfig(8, 4, 8, horizontalDirections));

        of(registerable, PromenadeConfiguredFeatures.FALLEN_VERMILION_MAPLE_LEAVES, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES, 1, 3)))
        ));
        of(registerable, PromenadeConfiguredFeatures.FALLEN_FULVOUS_MAPLE_LEAVES, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES, 1, 3)))
        ));
        of(registerable, PromenadeConfiguredFeatures.FALLEN_MIKADO_MAPLE_LEAVES, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(fallenLeaves(PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES, 1, 3)))
        ));

        // Grouped features
        of(registerable, PromenadeConfiguredFeatures.BLUSH_SAKURA_GROVE_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_BLUSH_SAKURA), 0.1f),
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.COTTON_SAKURA), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.BLUSH_SAKURA))
        );
        of(registerable, PromenadeConfiguredFeatures.COTTON_SAKURA_GROVE_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_COTTON_SAKURA), 0.1f),
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.BLUSH_SAKURA), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.COTTON_SAKURA))
        );

        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_SAP_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_SAP_MAPLE_BEES), 0.1f),
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_SAP_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.SAP_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_VERMILION_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_VERMILION_MAPLE_BEES), 0.1f),
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_VERMILION_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.VERMILION_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FULVOUS_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE_BEES), 0.1f),
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.FULVOUS_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_MIKADO_TREE, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_MIKADO_MAPLE_BEES), 0.1f),
                        new RandomFeatureEntry(placed.getOrThrow(PromenadePlacedFeatures.FANCY_MIKADO_MAPLE), 0.2f)
                ), placed.getOrThrow(PromenadePlacedFeatures.MIKADO_MAPLE))
        );
        of(registerable, PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_TREE, PromenadeFeatures.NOISE_PICKED,
                new NoisePickedFeatureConfig(NoiseScale.of(200.0f), List.of(
                        new NoisePickedFeatureEntry(PlacedFeatures.createEntry(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_SAP_TREE)), -1.0f, 1.0f),
                        new NoisePickedFeatureEntry(PlacedFeatures.createEntry(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_VERMILION_TREE)), 0.2f, 0.95f),
                        new NoisePickedFeatureEntry(PlacedFeatures.createEntry(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FULVOUS_TREE)), -0.5f, 0.5f),
                        new NoisePickedFeatureEntry(PlacedFeatures.createEntry(configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_MIKADO_TREE)), -0.95f, -0.2f)
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

    public static Pool.Builder<BlockState> fallenLeaves(Block block, int min, int max) {
        return segmentedBlock(block, min, max, LeafLitterBlock.SEGMENT_AMOUNT, LeafLitterBlock.HORIZONTAL_FACING);
    }

    private static Pool.Builder<BlockState> segmentedBlock(Block block, int min, int max, IntProperty amountProperty, EnumProperty<Direction> facingProperty) {
        Pool.Builder<BlockState> builder = Pool.builder();

        for (int i = min; i <= max; ++i) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                builder.add(block.getDefaultState().with(amountProperty, i).with(facingProperty, direction), 1);
            }
        }

        return builder;
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void of(Registerable<ConfiguredFeature<?, ?>> registry, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        ConfiguredFeatures.register(registry, key, feature, config);
    }
}