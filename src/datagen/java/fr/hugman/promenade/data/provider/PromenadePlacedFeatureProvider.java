package fr.hugman.promenade.data.provider;

import com.google.common.collect.ImmutableList;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadeFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatures;
import fr.hugman.promenade.world.gen.placement_modifier.NoiseIntervalCountPlacementModifier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.heightprovider.TrapezoidHeightProvider;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PromenadePlacedFeatureProvider extends FabricDynamicRegistryProvider {
    private static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);

    public PromenadePlacedFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return "Placed Features";
    }

    public static void register(Registerable<PlacedFeature> registerable) {
        final var configured = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Ores
        var asphalt = configured.getOrThrow(PromenadeConfiguredFeatures.ASPHALT_ORE);
        of(registerable, PromenadePlacedFeatures.ORE_ASPHALT_UPPER, asphalt, modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))));
        of(registerable, PromenadePlacedFeatures.ORE_ASPHALT_LOWER, asphalt, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));
        var blunite = configured.getOrThrow(PromenadeConfiguredFeatures.BLUNITE_ORE);
        of(registerable, PromenadePlacedFeatures.ORE_BLUNITE_UPPER, blunite, modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))));
        of(registerable, PromenadePlacedFeatures.ORE_BLUNITE_LOWER, blunite, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));

        of(registerable, PromenadePlacedFeatures.PACKED_ICE_ORE,
                configured.getOrThrow(PromenadeConfiguredFeatures.PACKED_ICE_ORE),
                modifiersWithCount(17, HeightRangePlacementModifier.of(TrapezoidHeightProvider.create(YOffset.fixed(0), YOffset.fixed(100), 60))));
        of(registerable, PromenadePlacedFeatures.BLUE_ICE_ORE,
                configured.getOrThrow(PromenadeConfiguredFeatures.BLUE_ICE_ORE),
                modifiersWithCount(4, HeightRangePlacementModifier.of(TrapezoidHeightProvider.create(YOffset.fixed(0), YOffset.fixed(100), 20))));

        // Trees
        of(registerable, PromenadePlacedFeatures.BLUSH_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.BLUSH_SAKURA), PlacedFeatures.wouldSurvive(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_BLUSH_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA), PlacedFeatures.wouldSurvive(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.BLUSH_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.BLUSH_SAKURA_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_BLUSH_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_BLUSH_SAKURA_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.BLUSH_SAKURA_SAPLING));

        of(registerable, PromenadePlacedFeatures.COTTON_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.COTTON_SAKURA), PlacedFeatures.wouldSurvive(PromenadeBlocks.COTTON_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_COTTON_SAKURA, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA), PlacedFeatures.wouldSurvive(PromenadeBlocks.COTTON_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.COTTON_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.COTTON_SAKURA_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.COTTON_SAKURA_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_COTTON_SAKURA_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_COTTON_SAKURA_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.COTTON_SAKURA_SAPLING));

        of(registerable, PromenadePlacedFeatures.SAP_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.SAP_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.SAP_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_SAP_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.SAP_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.SAP_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.SAP_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.SAP_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_SAP_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_SAP_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.SAP_MAPLE_SAPLING));

        of(registerable, PromenadePlacedFeatures.VERMILION_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.VERMILION_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_VERMILION_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.VERMILION_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.VERMILION_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_VERMILION_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_VERMILION_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.VERMILION_MAPLE_SAPLING));

        of(registerable, PromenadePlacedFeatures.FULVOUS_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FULVOUS_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FULVOUS_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FULVOUS_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_FULVOUS_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_FULVOUS_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));

        of(registerable, PromenadePlacedFeatures.MIKADO_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.MIKADO_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_MIKADO_MAPLE, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE), PlacedFeatures.wouldSurvive(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.MIKADO_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.MIKADO_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
        of(registerable, PromenadePlacedFeatures.FANCY_MIKADO_MAPLE_BEES, configured.getOrThrow(PromenadeConfiguredFeatures.FANCY_MIKADO_MAPLE_BEES), PlacedFeatures.wouldSurvive(PromenadeBlocks.MIKADO_MAPLE_SAPLING));


        // Vegetation
        var blueberryBush = configured.getOrThrow(PromenadeConfiguredFeatures.BLUEBERRY_BUSH_PATCH);
        of(registerable, PromenadePlacedFeatures.BLUEBERRY_BUSH_COMMON_PATCH, blueberryBush, rare(32));
        of(registerable, PromenadePlacedFeatures.BLUEBERRY_BUSH_RARE_PATCH, blueberryBush, rare(384));

        of(registerable, PromenadePlacedFeatures.SAKURA_GROVE_BAMBOO, configured.getOrThrow(PromenadeConfiguredFeatures.BAMBOO_PATCH), modifiers(NoiseBasedCountPlacementModifier.of(2, 50.0d, 0.1d), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

        of(registerable, PromenadePlacedFeatures.CUTE_LITTLE_ROCKS, configured.getOrThrow(PromenadeConfiguredFeatures.CUTE_LITTLE_ROCK), count(2, PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP));

        of(registerable, PromenadePlacedFeatures.WATER_POOLS_GRAVEL, configured.getOrThrow(PromenadeConfiguredFeatures.WATER_POOL_GRAVEL), rare(7));
        of(registerable, PromenadePlacedFeatures.WATER_POOLS_GRAVEL_DECORATED, configured.getOrThrow(PromenadeConfiguredFeatures.WATER_POOL_GRAVEL_DECORATED), rare(10, PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP));

        of(registerable, PromenadePlacedFeatures.FREEZE_TOP_LAYER, RegistryEntry.of(new ConfiguredFeature<>(PromenadeFeatures.FREEZE_TOP_LAYER, FeatureConfig.DEFAULT)), BiomePlacementModifier.of());

        of(registerable, PromenadePlacedFeatures.DARK_AMARANTH_FOREST_VEGETATION, configured.getOrThrow(PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_VEGETATION), netherCount(6));

        of(registerable, PromenadePlacedFeatures.FALLEN_VERMILION_MAPLE_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.FALLEN_VERMILION_MAPLE_LEAVES));
        of(registerable, PromenadePlacedFeatures.FALLEN_FULVOUS_MAPLE_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.FALLEN_FULVOUS_MAPLE_LEAVES));
        of(registerable, PromenadePlacedFeatures.FALLEN_MIKADO_MAPLE_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.FALLEN_MIKADO_MAPLE_LEAVES));

        of(registerable, PromenadePlacedFeatures.COILED_VINES, configured.getOrThrow(PromenadeConfiguredFeatures.COILED_VINES), CountPlacementModifier.of(10), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());

        // Tree groups
        of(registerable, PromenadePlacedFeatures.BLUSH_SAKURA_GROVE_TREES,
                configured.getOrThrow(PromenadeConfiguredFeatures.BLUSH_SAKURA_GROVE_TREE),
                treeModifiers(PlacedFeatures.createCountExtraModifier(2, 0.1F, 1)));
        of(registerable, PromenadePlacedFeatures.COTTON_SAKURA_GROVE_TREES,
                configured.getOrThrow(PromenadeConfiguredFeatures.COTTON_SAKURA_GROVE_TREE),
                treeModifiers(PlacedFeatures.createCountExtraModifier(2, 0.1F, 1)));

        of(registerable, PromenadePlacedFeatures.CARNELIAN_TREEWAY_TREES, configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_TREE), treeModifiers(PlacedFeatures.createCountExtraModifier(10, 0.1F, 1)));
        of(registerable, PromenadePlacedFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES, configured.getOrThrow(PromenadeConfiguredFeatures.CARNELIAN_TREEWAY_FALLEN_LEAVES), count(25));

        of(registerable, PromenadePlacedFeatures.GLACARIAN_TAIGA_TREES, configured.getOrThrow(PromenadeConfiguredFeatures.SNOWY_MEGA_SPRUCE), treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(14, 0.1F, 4), Blocks.SPRUCE_SAPLING));

        of(registerable, PromenadePlacedFeatures.PALMS, configured.getOrThrow(PromenadeConfiguredFeatures.PALM), treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.1F, 1), PromenadeBlocks.PALM_SAPLING));

        of(registerable, PromenadePlacedFeatures.DARK_AMARANTH_FUNGI, configured.getOrThrow(PromenadeConfiguredFeatures.DARK_AMARANTH_FUNGUS), netherCount(8));

    }

    public static List<PlacementModifier> rare(int chance) {
        return rare(chance, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP);
    }

    public static List<PlacementModifier> rare(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

    public static List<PlacementModifier> count(int count) {
        return count(count, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP);
    }

    public static List<PlacementModifier> count(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> netherCount(int count) {
        return List.of(CountMultilayerPlacementModifier.of(count), BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

    private static ImmutableList.Builder<PlacementModifier> treeModifiersBuilder(PlacementModifier countModifier) {
        return ImmutableList.<PlacementModifier>builder()
                .add(countModifier)
                .add(SquarePlacementModifier.of())
                .add(NOT_IN_SURFACE_WATER_MODIFIER)
                .add(PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP)
                .add(BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> treeModifiersWithNoiseInterval(PlacementModifier countModifier, float noiseMin, float noiseMax) {
        return ImmutableList.<PlacementModifier>builder()
                .add(countModifier)
                .add(SquarePlacementModifier.of())
                .add(NoiseIntervalCountPlacementModifier.of(noiseMin, noiseMax, 1, 0))
                .add(NOT_IN_SURFACE_WATER_MODIFIER)
                .add(PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP)
                .add(BiomePlacementModifier.of())
                .build();
    }

    public static List<PlacementModifier> treeModifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
        return treeModifiersBuilder(modifier).add(BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN))).build();
    }

    public static List<PlacementModifier> treeModifiers(PlacementModifier modifier) {
        return treeModifiersBuilder(modifier).build();
    }

    public static void of(
            Registerable<PlacedFeature> featureRegisterable,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> feature,
            List<PlacementModifier> modifiers
    ) {
        PlacedFeatures.register(featureRegisterable, key, feature, modifiers);
    }

    public static void of(
            Registerable<PlacedFeature> featureRegisterable,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> feature,
            PlacementModifier... modifiers
    ) {
        PlacedFeatures.register(featureRegisterable, key, feature, modifiers);
    }
}