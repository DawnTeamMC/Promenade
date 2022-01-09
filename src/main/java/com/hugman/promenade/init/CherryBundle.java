package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.creator.bundle.block.WoodBundle;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.PinkCherryOakSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.WhiteCherryOakSaplingGenerator;
import com.hugman.promenade.object.trade_offers.SellSaplingFactory;
import com.hugman.promenade.util.BiomeUtil;
import com.hugman.promenade.util.BlockBuilders;
import com.hugman.promenade.util.TreeUtil;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;

import java.util.List;

public class CherryBundle extends PromenadeBundle {
	/*----------*/
	/*  BLOCKS  */
	/*----------*/
	public static final WoodBundle CHERRY_OAK_WOOD = creator(new WoodBundle.Builder("cherry_oak", MapColor.DULL_RED, MapColor.DULL_PINK, MapColor.DARK_DULL_PINK, false).build());
	public static final PlantBundle PINK_CHERRY_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("pink_cherry_oak_sapling").provider(s -> new SaplingBlock(new PinkCherryOakSaplingGenerator(), s))));
	public static final Block PINK_CHERRY_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("pink_cherry_oak_leaves").build());
	public static final Block PINK_CHERRY_OAK_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("pink_cherry_oak_leaf_pile").build());
	public static final PlantBundle WHITE_CHERRY_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("white_cherry_oak_sapling").provider(s -> new SaplingBlock(new WhiteCherryOakSaplingGenerator(), s))));
	public static final Block WHITE_CHERRY_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("white_cherry_oak_leaves").build());
	public static final Block WHITE_CHERRY_OAK_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("white_cherry_oak_leaf_pile").build());

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.cherry_oak_forests_weight > 0) {
			//TODO
			//OverworldBiomes.addContinentalBiome(PINK_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D)
			//OverworldBiomes.addContinentalBiome(WHITE_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D);
		}
	}

	public static void addWanderingSales() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(new SellSaplingFactory(PINK_CHERRY_OAK_SAPLING.getPlant()));
			factories.add(new SellSaplingFactory(WHITE_CHERRY_OAK_SAPLING.getPlant()));
		});
	}

	public static class Features {
		public static class Configured {
			/*---------*/
			/*  TREES  */
			/*---------*/
			public static final ConfiguredFeature<TreeFeatureConfig, ?> PINK_CHERRY_OAK = add(new ConfiguredFeatureCreator<>("tree/pink_cherry_oak/normal", Feature.TREE.configure(buildNormalCherryOak(PINK_CHERRY_OAK_LEAVES).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> PINK_CHERRY_OAK_BEES_005 = add(new ConfiguredFeatureCreator<>("tree/pink_cherry_oak/bees_005", Feature.TREE.configure(buildNormalCherryOak(PINK_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_PINK_CHERRY_OAK = add(new ConfiguredFeatureCreator<>("tree/pink_cherry_oak/fancy", Feature.TREE.configure(buildFancyCherryOak(PINK_CHERRY_OAK_LEAVES).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_PINK_CHERRY_OAK_BEES_005 = add(new ConfiguredFeatureCreator<>("tree/pink_cherry_oak/fancy_bees_005", Feature.TREE.configure(buildFancyCherryOak(PINK_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build())));

			public static final ConfiguredFeature<TreeFeatureConfig, ?> WHITE_CHERRY_OAK = add(new ConfiguredFeatureCreator<>("tree/white_cherry_oak/normal", Feature.TREE.configure(buildNormalCherryOak(WHITE_CHERRY_OAK_LEAVES).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> WHITE_CHERRY_OAK_BEES_005 = add(new ConfiguredFeatureCreator<>("tree/white_cherry_oak/bees_005", Feature.TREE.configure(buildNormalCherryOak(WHITE_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_WHITE_CHERRY_OAK = add(new ConfiguredFeatureCreator<>("tree/white_cherry_oak/fancy", Feature.TREE.configure(buildFancyCherryOak(WHITE_CHERRY_OAK_LEAVES).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_WHITE_CHERRY_OAK_BEES_005 = add(new ConfiguredFeatureCreator<>("tree/white_cherry_oak/fancy_bees_005", Feature.TREE.configure(buildFancyCherryOak(WHITE_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build())));

			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final ConfiguredFeature<?, ?> PINK_CHERRY_OAK_LEAF_PILE_PATCH = add(new ConfiguredFeatureCreator<>("patch/leaf_pile/pink_cherry_oak", Feature.RANDOM_PATCH.configure(createRandomPatchFeatureConfig(BlockStateProvider.of(PINK_CHERRY_OAK_LEAF_PILE), 32))));
			public static final ConfiguredFeature<?, ?> WHITE_CHERRY_OAK_LEAF_PILE_PATCH = add(new ConfiguredFeatureCreator<>("patch/leaf_pile/white_cherry_oak", Feature.RANDOM_PATCH.configure(createRandomPatchFeatureConfig(BlockStateProvider.of(WHITE_CHERRY_OAK_LEAF_PILE), 32))));

			private static TreeFeatureConfig.Builder buildNormalCherryOak(Block leaves) {
				return buildCherryOak(leaves, 3, 2, 1).ignoreVines();
			}

			private static TreeFeatureConfig.Builder buildFancyCherryOak(Block leaves) {
				return buildCherryOak(leaves, 5, 3, 2).ignoreVines();
			}

			private static TreeFeatureConfig.Builder buildCherryOak(Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight) {
				return TreeUtil.build(CherryBundle.CHERRY_OAK_WOOD.getLog(), leaves, new ForkingTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), new AcaciaFoliagePlacer(UniformIntProvider.create(1, 2), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1));
			}

			private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
				return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
			}
		}

		public static class Placed {
			/*---------*/
			/*  TREES  */
			/*---------*/
			public static final PlacedFeature PINK_CHERRY_OAK = add(new PlacedFeatureCreator("tree/pink_cherry_oak/normal", Configured.PINK_CHERRY_OAK.withWouldSurviveFilter(PINK_CHERRY_OAK_SAPLING.getPlant())));
			public static final PlacedFeature PINK_CHERRY_OAK_BEES_005 = add(new PlacedFeatureCreator("tree/pink_cherry_oak/bees_002", Configured.PINK_CHERRY_OAK_BEES_005.withWouldSurviveFilter(PINK_CHERRY_OAK_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_PINK_CHERRY_OAK = add(new PlacedFeatureCreator("tree/pink_cherry_oak/fancy", Configured.FANCY_PINK_CHERRY_OAK.withWouldSurviveFilter(PINK_CHERRY_OAK_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_PINK_CHERRY_OAK_BEES_005 = add(new PlacedFeatureCreator("tree/pink_cherry_oak/fancy_bees_005", Configured.FANCY_PINK_CHERRY_OAK_BEES_005.withWouldSurviveFilter(PINK_CHERRY_OAK_SAPLING.getPlant())));

			public static final PlacedFeature WHITE_CHERRY_OAK = add(new PlacedFeatureCreator("tree/white_cherry_oak/normal", Configured.WHITE_CHERRY_OAK.withWouldSurviveFilter(WHITE_CHERRY_OAK_SAPLING.getPlant())));
			public static final PlacedFeature WHITE_CHERRY_OAK_BEES_005 = add(new PlacedFeatureCreator("tree/white_cherry_oak/bees_005", Configured.WHITE_CHERRY_OAK_BEES_005.withWouldSurviveFilter(WHITE_CHERRY_OAK_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_WHITE_CHERRY_OAK = add(new PlacedFeatureCreator("tree/white_cherry_oak/fancy", Configured.FANCY_WHITE_CHERRY_OAK.withWouldSurviveFilter(WHITE_CHERRY_OAK_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_WHITE_CHERRY_OAK_BEES_005 = add(new PlacedFeatureCreator("tree/white_cherry_oak/fancy_bees_005", Configured.FANCY_WHITE_CHERRY_OAK_BEES_005.withWouldSurviveFilter(WHITE_CHERRY_OAK_SAPLING.getPlant())));

			public static final ConfiguredFeature<RandomFeatureConfig, ?> PINK_CHERRY_OAK_FOREST_TREES_C = add(new ConfiguredFeatureCreator<>("trees/pink_cherry_oak_forest", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(Placed.WHITE_CHERRY_OAK, 0.2f), new RandomFeatureEntry(Placed.FANCY_PINK_CHERRY_OAK, 0.1f)), Placed.PINK_CHERRY_OAK))));
			public static final ConfiguredFeature<RandomFeatureConfig, ?> WHITE_CHERRY_OAK_FOREST_TREES_C = add(new ConfiguredFeatureCreator<>("trees/white_cherry_oak_forest", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(Placed.PINK_CHERRY_OAK, 0.2f), new RandomFeatureEntry(Placed.FANCY_WHITE_CHERRY_OAK, 0.1f)), Placed.WHITE_CHERRY_OAK))));

			public static final PlacedFeature PINK_CHERRY_OAK_FOREST_TREES = PlacedFeatures.register("trees/pink_cherry_oak_forest", PINK_CHERRY_OAK_FOREST_TREES_C.withPlacement(VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(10, 0.1f, 1))));
			public static final PlacedFeature WHITE_CHERRY_OAK_FOREST_TREES = PlacedFeatures.register("trees/white_cherry_oak_forest", WHITE_CHERRY_OAK_FOREST_TREES_C.withPlacement(VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(10, 0.1f, 1))));

			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final PlacedFeature PINK_CHERRY_OAK_LEAF_PILE_PATCH = add(new PlacedFeatureCreator("patch/leaf_pile/pink_cherry_oak/forest", Configured.PINK_CHERRY_OAK_LEAF_PILE_PATCH.withPlacement(VegetationPlacedFeatures.modifiers(5))));
			public static final PlacedFeature WHITE_CHERRY_OAK_LEAF_PILE_PATCH = add(new PlacedFeatureCreator("patch/leaf_pile/white_cherry_oak/forest", Configured.WHITE_CHERRY_OAK_LEAF_PILE_PATCH.withPlacement(VegetationPlacedFeatures.modifiers(5))));
		}
	}

	public static class Biomes {
		public static final BiomeCreator PINK_CHERRY_OAK_FOREST = creator(new BiomeCreator("pink_cherry_oak_forest", createCherryOakForest(true)));
		public static final BiomeCreator WHITE_CHERRY_OAK_FOREST = creator(new BiomeCreator("white_cherry_oak_forest", createCherryOakForest(false)));

		private static Biome createCherryOakForest(boolean isPink) {
			GenerationSettings.Builder genBuilder = BiomeUtil.composeForestGenerationSettings();
			if(isPink) {
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PINK_CHERRY_OAK_FOREST_TREES);
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PINK_CHERRY_OAK_LEAF_PILE_PATCH);
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MushroomBundle.Features.Placed.PINK_MUSHROOM_NORMAL_PATCH);
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MushroomBundle.Features.Placed.MAGENTA_MUSHROOM_NORMAL_PATCH);
			}
			else {
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.WHITE_CHERRY_OAK_FOREST_TREES);
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.WHITE_CHERRY_OAK_LEAF_PILE_PATCH);
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MushroomBundle.Features.Placed.WHITE_MUSHROOM_NORMAL_PATCH);
				genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MushroomBundle.Features.Placed.LIGHT_GRAY_MUSHROOM_NORMAL_PATCH);
			}
			BiomeEffects effects = BiomeUtil.genericEffectBuilder(0.6F)
					.waterColor(6459391)
					.waterFogColor(2170954)
					.moodSound(BiomeMoodSound.CAVE)
					.foliageColor(15768259)
					.build();
			return BiomeUtil.createForest(genBuilder.build(), effects, 0.6F, 0.4F);
		}
	}
}
