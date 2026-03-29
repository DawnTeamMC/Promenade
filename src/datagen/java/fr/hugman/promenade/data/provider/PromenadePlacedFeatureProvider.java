package fr.hugman.promenade.data.provider;

import com.google.common.collect.ImmutableList;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadeFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatures;
import fr.hugman.promenade.world.gen.placement_modifier.NoiseIntervalCountPlacementModifier;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PromenadePlacedFeatureProvider extends FabricDynamicRegistryProvider {
    private static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilter.forMaxDepth(0);

    public PromenadePlacedFeatureProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return "Placed Features";
    }

    public static void register(BootstrapContext<PlacedFeature> registerable) {
        final var configured = registerable.lookup(Registries.CONFIGURED_FEATURE);

        // Ores
        var asphalt = configured.getOrThrow(PromenadeConfiguredFeatures.ASPHALT_ORE);
        of(registerable, PromenadePlacedFeatures.ORE_ASPHALT_UPPER, asphalt, modifiersWithRarity(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));
        of(registerable, PromenadePlacedFeatures.ORE_ASPHALT_LOWER, asphalt, modifiersWithCount(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));
        var blunite = configured.getOrThrow(PromenadeConfiguredFeatures.BLUNITE_ORE);
        of(registerable, PromenadePlacedFeatures.ORE_BLUNITE_UPPER, blunite, modifiersWithRarity(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));
        of(registerable, PromenadePlacedFeatures.ORE_BLUNITE_LOWER, blunite, modifiersWithCount(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));

        of(registerable, PromenadePlacedFeatures.PACKED_ICE_ORE,
                configured.getOrThrow(PromenadeConfiguredFeatures.PACKED_ICE_ORE),
                modifiersWithCount(17, HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100), 60))));
        of(registerable, PromenadePlacedFeatures.BLUE_ICE_ORE,
                configured.getOrThrow(PromenadeConfiguredFeatures.BLUE_ICE_ORE),
                modifiersWithCount(4, HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100), 20))));

        // Trees
        of(registerable, PromenadePlacedFeatures.BLUSH_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.BLUSH_SAKURA), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_BLUSH_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.BLUSH_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_BLUSH_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.BLUSH_SAKURA_SAPLING));

        of(registerable, PromenadePlacedFeatures.COTTON_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.COTTON_SAKURA), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.COTTON_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_COTTON_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.COTTON_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.COTTON_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.COTTON_SAKURA_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.COTTON_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_COTTON_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.COTTON_SAKURA_SAPLING));

        of(registerable, PromenadePlacedFeatures.SAP_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.SAP_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.SAP_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_SAP_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.SAP_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.SAP_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.SAP_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.SAP_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_SAP_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.SAP_MAPLE_SAPLING));

        of(registerable, PromenadePlacedFeatures.VERMILION_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.VERMILION_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_VERMILION_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.VERMILION_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_VERMILION_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.VERMILION_MAPLE_SAPLING));

        of(registerable, PromenadePlacedFeatures.FULVOUS_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FULVOUS_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FULVOUS_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));

        of(registerable, PromenadePlacedFeatures.MIKADO_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.MIKADO_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_MIKADO_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.MIKADO_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_MIKADO_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES), PlacementUtils.filteredByBlockSurvival(PromenadeBlocks.MIKADO_MAPLE_SAPLING));


        // Vegetation
        var blueberryBush = configured.getOrThrow(PromenadeConfiguredFeatures.BLUEBERRY_BUSH);
        of(registerable, PromenadePlacedFeatures.BLUEBERRY_BUSH_COMMON_PATCH, blueberryBush,
                RarityFilter.onAverageOnceEvery(32),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(96),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.GRASS_BLOCK))
                )
        );
        of(registerable, PromenadePlacedFeatures.BLUEBERRY_BUSH_RARE_PATCH, blueberryBush,
                RarityFilter.onAverageOnceEvery(384),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(96),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.GRASS_BLOCK))
                )
        );

        of(registerable, PromenadePlacedFeatures.SAKURA_GROVE_BAMBOO, configured.getOrThrow(VegetationFeatures.BAMBOO_SOME_PODZOL),
                NoiseBasedCountPlacement.of(2, 50.0, 0.1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                CountPlacement.of(25),
                RandomOffsetPlacement.ofTriangle(7, 3),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
        );

        of(registerable, PromenadePlacedFeatures.CUTE_LITTLE_ROCKS, configured.getOrThrow(PromenadeConfiguredFeatures.CUTE_LITTLE_ROCK), count(2, PlacementUtils.HEIGHTMAP));

        of(registerable, PromenadePlacedFeatures.WATER_POOLS_GRAVEL_DECORATED, configured.getOrThrow(PromenadeConfiguredFeatures.WATER_POOL_GRAVEL_DECORATED), rare(10, PlacementUtils.HEIGHTMAP));

        of(registerable, PromenadePlacedFeatures.FREEZE_TOP_LAYER, Holder.direct(new ConfiguredFeature<>(PromenadeFeatures.FREEZE_TOP_LAYER, FeatureConfiguration.NONE)), BiomeFilter.biome());

        of(registerable, PromenadePlacedFeatures.DARK_AMARANTH_FOREST_VEGETATION, configured.getOrThrow(PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_VEGETATION), netherCount(6));

        of(registerable, PromenadePlacedFeatures.FALLEN_VERMILION_MAPLE_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.FALLEN_VERMILION_MAPLE_LEAVES));
        of(registerable, PromenadePlacedFeatures.FALLEN_FULVOUS_MAPLE_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.FALLEN_FULVOUS_MAPLE_LEAVES));
        of(registerable, PromenadePlacedFeatures.FALLEN_MIKADO_MAPLE_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.FALLEN_MIKADO_MAPLE_LEAVES));

        of(registerable, PromenadePlacedFeatures.COILED_VINES, configured.getOrThrow(PromenadeConfiguredFeatures.COILED_VINES), CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());

        // Tree groups
        of(registerable, PromenadePlacedFeatures.BLUSH_SAKURA_GROVE_TREES,
                configured.getOrThrow(PromenadeConfiguredFeatures.BLUSH_SAKURA_GROVE_TREE),
                treeModifiers(PlacementUtils.countExtra(2, 0.1F, 1)));
        of(registerable, PromenadePlacedFeatures.COTTON_SAKURA_GROVE_TREES,
                configured.getOrThrow(PromenadeConfiguredFeatures.COTTON_SAKURA_GROVE_TREE),
                treeModifiers(PlacementUtils.countExtra(2, 0.1F, 1)));

        of(registerable, PromenadePlacedFeatures.CARNELIAN_TREEWAY_TREES, configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_TREE), treeModifiers(PlacementUtils.countExtra(10, 0.1F, 1)));
        of(registerable, PromenadePlacedFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES),
                Util.copyAndAdd(
                    worldSurfaceSquaredWithCount(20),
                    CountPlacement.of(32),
                    RandomOffsetPlacement.ofTriangle(7, 3),
                    BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
        ));

        of(registerable, PromenadePlacedFeatures.GLACARIAN_TAIGA_TREES, configured.getOrThrow(PromenadeConfiguredFeatures.SNOWY_MEGA_SPRUCE), treeModifiersWithWouldSurvive(PlacementUtils.countExtra(14, 0.1F, 4), Blocks.SPRUCE_SAPLING));

        of(registerable, PromenadePlacedFeatures.PALMS, configured.getOrThrow(PromenadeConfiguredFeatures.PALM), treeModifiersWithWouldSurvive(PlacementUtils.countExtra(0, 0.1F, 1), PromenadeBlocks.PALM_SAPLING));

        of(registerable, PromenadePlacedFeatures.DARK_AMARANTH_FUNGI, configured.getOrThrow(PromenadeConfiguredFeatures.DARK_AMARANTH_FUNGUS), netherCount(8));

    }

    public static List<PlacementModifier> worldSurfaceSquaredWithCount(final int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

    public static List<PlacementModifier> rare(int chance) {
        return rare(chance, PlacementUtils.HEIGHTMAP_WORLD_SURFACE);
    }

    public static List<PlacementModifier> rare(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }

    public static List<PlacementModifier> count(int count) {
        return count(count, PlacementUtils.HEIGHTMAP_WORLD_SURFACE);
    }

    public static List<PlacementModifier> count(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

    public static List<PlacementModifier> netherCount(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome());
    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }

    private static ImmutableList.Builder<PlacementModifier> treeModifiersBuilder(PlacementModifier countModifier) {
        return ImmutableList.<PlacementModifier>builder()
                .add(countModifier)
                .add(InSquarePlacement.spread())
                .add(NOT_IN_SURFACE_WATER_MODIFIER)
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treeModifiersWithNoiseInterval(PlacementModifier countModifier, float noiseMin, float noiseMax) {
        return ImmutableList.<PlacementModifier>builder()
                .add(countModifier)
                .add(InSquarePlacement.spread())
                .add(NoiseIntervalCountPlacementModifier.of(noiseMin, noiseMax, 1, 0))
                .add(NOT_IN_SURFACE_WATER_MODIFIER)
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BiomeFilter.biome())
                .build();
    }

    public static List<PlacementModifier> treeModifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
        return treeModifiersBuilder(modifier).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(block.defaultBlockState(), BlockPos.ZERO))).build();
    }

    public static List<PlacementModifier> treeModifiers(PlacementModifier modifier) {
        return treeModifiersBuilder(modifier).build();
    }

    public static void of(
            BootstrapContext<PlacedFeature> featureRegisterable,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> feature,
            List<PlacementModifier> modifiers
    ) {
        PlacementUtils.register(featureRegisterable, key, feature, modifiers);
    }

    public static void of(
            BootstrapContext<PlacedFeature> featureRegisterable,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> feature,
            PlacementModifier... modifiers
    ) {
        PlacementUtils.register(featureRegisterable, key, feature, modifiers);
    }
}