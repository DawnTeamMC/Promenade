package com.hugman.promenade.init.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.BlockBundle;
import com.hugman.promenade.init.MushroomBundle.Features.Configured.*;
import com.hugman.promenade.init.MushroomBundle;
import com.hugman.promenade.init.PromenadeBundle;
import com.hugman.promenade.init.world.PromenadeFeatures;
import com.hugman.promenade.object.world.gen.feature.config.BoulderFeatureConfig;
import com.hugman.promenade.object.world.gen.feature.config.SpikeFeatureConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;

import java.util.Arrays;
import java.util.List;

import static com.hugman.promenade.init.MushroomBundle.Features.Configured.*;


public class PromenadeConfiguredFeatures extends PromenadeBundle {
	public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH = add(new ConfiguredFeatureCreator<>("patch_blueberry_bush", Configs.patch(States.BLUEBERRY_BUSH, 64, ImmutableSet.of(States.GRASS_BLOCK.getBlock()))));
	public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_SPARSE = add(new ConfiguredFeatureCreator<>("patch_blueberry_bush_sparse", PATCH_BLUEBERRY_BUSH.decorate(Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE)));
	public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_DECORATED = add(new ConfiguredFeatureCreator<>("patch_blueberry_bush_decorated", PATCH_BLUEBERRY_BUSH.decorate(Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).applyChance(12)));

	public static final ConfiguredFeature<TreeFeatureConfig, ?> PALM = add(new ConfiguredFeatureCreator<>("palm", Feature.TREE.configure((new TreeFeatureConfig.Builder(BlockStateProvider.of(States.PALM_LOG), new ForkingTrunkPlacer(16, 2, 2), BlockStateProvider.of(States.PALM_LEAVES), BlockStateProvider.of(States.PALM_SAPLING), new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build())));
	public static final ConfiguredFeature<?, ?> PALM_TREES = add(new ConfiguredFeatureCreator<>("palm_trees", PALM.decorate(Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.12F, 1)))));


	public static final ConfiguredFeature<?, ?> AMARANTH_FUNGI = add(new ConfiguredFeatureCreator<>("amaranth_fungi", Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(States.AMARANTH_DYLIUM, States.DARK_AMARANTH_STEM, States.AMARANTH_WART_BLOCK, States.COBWEB, false)).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(2)))));
	public static final ConfiguredFeature<?, ?> TALL_AMARANTH_FUNGI = add(new ConfiguredFeatureCreator<>("tall_amaranth_fungi", PromenadeFeatures.TALL_HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(States.AMARANTH_DYLIUM, States.DARK_AMARANTH_STEM, States.AMARANTH_WART_BLOCK, States.COBWEB, false)).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(2)))));
	public static final ConfiguredFeature<HugeFungusFeatureConfig, ?> AMARANTH_FUNGI_PLANTED = add(new ConfiguredFeatureCreator<>("amaranth_fungi_planted", Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(States.AMARANTH_DYLIUM, States.DARK_AMARANTH_STEM, States.AMARANTH_WART_BLOCK, States.COBWEB, true))));
	public static final ConfiguredFeature<?, ?> AMARANTH_FOREST_VEGETATION = add(new ConfiguredFeatureCreator<>("amaranth_forest_vegetation", Feature.NETHER_FOREST_VEGETATION.configure(Configs.AMARANTH_ROOTS).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(4)))));
	public static final ConfiguredFeature<?, ?> OBSIDIAN_SPIKE = add(new ConfiguredFeatureCreator<>("obsidian_spike", PromenadeFeatures.SPIKE.configure(new SpikeFeatureConfig(Blocks.OBSIDIAN.getDefaultState(), Arrays.asList(BlockBundle.BLACK_DYLIUM.getDefaultState(), Blocks.END_STONE.getDefaultState()), 6, 22, 2, 3, 0, 360, 150, 30, -4).setDecoration(Blocks.CRYING_OBSIDIAN.getDefaultState(), 0.08f)).decorate(Decorators.SQUARE_HEIGHTMAP).repeatRandomly(12)));
	public static final ConfiguredFeature<?, ?> ENDER_BOULDER = add(new ConfiguredFeatureCreator<>("ender_boulder", PromenadeFeatures.BOULDER.configure(new BoulderFeatureConfig(Blocks.OBSIDIAN.getDefaultState(), Arrays.asList(Blocks.END_STONE.getDefaultState()))).decorate(Decorators.SQUARE_HEIGHTMAP).repeatRandomly(2)));

	public static final BlockPileFeatureConfig AMARANTH_ROOTS = new BlockPileFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(BlockBundle.DARK_AMARANTH_ROOTS.getDefaultState(), 80).add(BlockBundle.DARK_AMARANTH_WOOD.getFungus().getDefaultState(), 4)));
	private static final PromenadeConfig.FeaturesCategory FEATURES_CONFIG = Promenade.CONFIG.features;

	public static void init() {
	}

	public static void addToGen() {
		if(FEATURES_CONFIG.palm_trees) {
			BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, key(PALM_TREES));
		}
		if(FEATURES_CONFIG.blueberry_bushes) {
			BiomeModifications.addFeature(c -> c.hasBuiltInFeature(ConfiguredFeatures.PATCH_BERRY_SPARSE), GenerationStep.Feature.VEGETAL_DECORATION, key(PATCH_BLUEBERRY_BUSH_SPARSE));
			BiomeModifications.addFeature(c -> c.hasBuiltInFeature(ConfiguredFeatures.PATCH_BERRY_DECORATED), GenerationStep.Feature.VEGETAL_DECORATION, key(PATCH_BLUEBERRY_BUSH_DECORATED));
		}
	}

	public static RegistryKey<ConfiguredFeature<?, ?>> key(ConfiguredFeature<?, ?> configuredFeature) {
		return BuiltinRegistries.CONFIGURED_FEATURE.getKey(configuredFeature).get();
	}

	public static final class States {
		private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();

		private static final BlockState CHERRY_OAK_LOG = BlockBundle.CHERRY_OAK_WOOD.getLog().getDefaultState();
		private static final BlockState PINK_CHERRY_OAK_SAPLING = BlockBundle.PINK_CHERRY_OAK_SAPLING.getPlant().getDefaultState();
		private static final BlockState PINK_CHERRY_OAK_LEAVES = BlockBundle.PINK_CHERRY_OAK_LEAVES.getDefaultState();
		private static final BlockState PINK_CHERRY_OAK_LEAF_PILE = BlockBundle.PINK_CHERRY_OAK_LEAF_PILE.getDefaultState();
		private static final BlockState WHITE_CHERRY_OAK_SAPLING = BlockBundle.WHITE_CHERRY_OAK_SAPLING.getPlant().getDefaultState();
		private static final BlockState WHITE_CHERRY_OAK_LEAVES = BlockBundle.WHITE_CHERRY_OAK_LEAVES.getDefaultState();
		private static final BlockState WHITE_CHERRY_OAK_LEAF_PILE = BlockBundle.WHITE_CHERRY_OAK_LEAF_PILE.getDefaultState();

		private static final BlockState PALM_LOG = BlockBundle.PALM_WOOD.getLog().getDefaultState();
		private static final BlockState PALM_SAPLING = BlockBundle.PALM_WOOD.getSapling().getDefaultState();
		private static final BlockState PALM_LEAVES = BlockBundle.PALM_WOOD.getLeaves().getDefaultState();

		private static final BlockState BLUEBERRY_BUSH = BlockBundle.BLUEBERRY_BUSH.getDefaultState();

		private static final BlockState AMARANTH_DYLIUM = BlockBundle.BLACK_DYLIUM.getDefaultState();
		private static final BlockState DARK_AMARANTH_STEM = BlockBundle.DARK_AMARANTH_WOOD.getStem().getDefaultState();
		private static final BlockState AMARANTH_WART_BLOCK = BlockBundle.DARK_AMARANTH_WART_BLOCK.getDefaultState();
		private static final BlockState COBWEB = Blocks.COBWEB.getDefaultState();

		private static final BlockState BLUE_MUSHROOM = BlockBundle.BLUE_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState CYAN_MUSHROOM = BlockBundle.CYAN_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState YELLOW_MUSHROOM = BlockBundle.YELLOW_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState ORANGE_MUSHROOM = BlockBundle.ORANGE_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState PINK_MUSHROOM = BlockBundle.PINK_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState MAGENTA_MUSHROOM = BlockBundle.MAGENTA_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState WHITE_MUSHROOM = BlockBundle.WHITE_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState LIGHT_GRAY_MUSHROOM = BlockBundle.LIGHT_GRAY_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState GRAY_MUSHROOM = BlockBundle.GRAY_MUSHROOM.getPlant().getDefaultState();
		private static final BlockState BLACK_MUSHROOM = BlockBundle.BLACK_MUSHROOM.getPlant().getDefaultState();

		private static final BlockState BLUE_MUSHROOM_BLOCK = BlockBundle.BLUE_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState CYAN_MUSHROOM_BLOCK = BlockBundle.CYAN_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState YELLOW_MUSHROOM_BLOCK = BlockBundle.YELLOW_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState BROWN_MUSHROOM_BLOCK = Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState ORANGE_MUSHROOM_BLOCK = BlockBundle.ORANGE_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState PINK_MUSHROOM_BLOCK = BlockBundle.PINK_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState MAGENTA_MUSHROOM_BLOCK = BlockBundle.MAGENTA_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState WHITE_MUSHROOM_BLOCK = BlockBundle.WHITE_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState LIGHT_GRAY_MUSHROOM_BLOCK = BlockBundle.LIGHT_GRAY_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState GRAY_MUSHROOM_BLOCK = BlockBundle.GRAY_MUSHROOM_BLOCK.getDefaultState();
		private static final BlockState BLACK_MUSHROOM_BLOCK = BlockBundle.BLACK_MUSHROOM_BLOCK.getDefaultState();

		private static final BlockState SHROOMLIGHT = Blocks.SHROOMLIGHT.getDefaultState();
	}

	public static final class Decorators {
		private static final BeehiveTreeDecorator VERY_RARE_BEEHIVES_TREES = new BeehiveTreeDecorator(0.002F);
		private static final BeehiveTreeDecorator REGULAR_BEEHIVES_TREES = new BeehiveTreeDecorator(0.02F);
		private static final BeehiveTreeDecorator MORE_BEEHIVES_TREES = new BeehiveTreeDecorator(0.05F);
		private static final ConfiguredDecorator<HeightmapDecoratorConfig> HEIGHTMAP = Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING));
		private static final ConfiguredDecorator<HeightmapDecoratorConfig> HEIGHTMAP_SPREAD_DOUBLE = Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING));
		private static final RangeDecoratorConfig BOTTOM_TO_TOP = new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.getTop()));
		private static final ConfiguredDecorator<?> SQUARE_HEIGHTMAP = HEIGHTMAP.spreadHorizontally();
		private static final ConfiguredDecorator<?> SQUARE_HEIGHTMAP_SPREAD_DOUBLE = HEIGHTMAP_SPREAD_DOUBLE.spreadHorizontally();
	}
}
