package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.AutumnBirchSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.AutumnOakSaplingGenerator;
import com.hugman.promenade.object.trade_offers.SellSaplingFactory;
import com.hugman.promenade.util.BiomeUtil;
import com.hugman.promenade.util.BlockBuilders;
import com.hugman.promenade.util.PFeatureRegistrer;
import com.hugman.promenade.util.TreeUtil;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class AutumnBundle extends PromenadeBundle {
	/*----------*/
	/*  BLOCKS  */
	/*----------*/
	public static final PlantBundle AUTUMN_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("autumn_oak_sapling").provider(settings -> new SaplingBlock(new AutumnOakSaplingGenerator(), settings))));
	public static final Block AUTUMN_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("autumn_oak_leaves").build());
	public static final Block AUTUMN_OAK_LEAF_PILE = add(BlockBuilders.leafPile("autumn_oak_leaf_pile").build());
	public static final PlantBundle AUTUMN_BIRCH_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("autumn_birch_sapling").provider(settings -> new SaplingBlock(new AutumnBirchSaplingGenerator(), settings))));
	public static final Block AUTUMN_BIRCH_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("autumn_birch_leaves").build());
	public static final Block AUTUMN_BIRCH_LEAF_PILE = add(BlockBuilders.leafPile("autumn_birch_leaf_pile").build());

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.pumpkin_pastures_weight > 0) {
			//TODO
			//OverworldBiomes.addContinentalBiome(AutumnBundle.Biomes.PUMPKIN_PASTURES.getRegistryKey(), OverworldClimate.COOL, Promenade.CONFIG.biomes.pumpkin_pastures_weight / 10.0D);
		}
	}

	public static void addWanderingSales() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(new SellSaplingFactory(AUTUMN_OAK_SAPLING.getPlant()));
			factories.add(new SellSaplingFactory(AUTUMN_BIRCH_SAPLING.getPlant()));
		});
	}

	public static class Features {
		public static class Configured {
			/*---------*/
			/*  TREES  */
			/*---------*/
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> AUTUMN_OAK = PFeatureRegistrer.config("tree/autumn_oak/normal", Feature.TREE, autumnOakTree().build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> AUTUMN_OAK_BEES_002 = PFeatureRegistrer.config("tree/autumn_oak/bees_002", Feature.TREE, autumnOakTree().decorators(List.of(TreeUtil.BEES_002)).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_AUTUMN_OAK = PFeatureRegistrer.config("tree/autumn_oak/fancy", Feature.TREE, fancyAutumnOakTree().build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_AUTUMN_OAK_BEES_002 = PFeatureRegistrer.config("tree/autumn_oak/fancy_bees_002", Feature.TREE, fancyAutumnOakTree().decorators(List.of(TreeUtil.BEES_002)).build());

			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> AUTUMN_BIRCH = PFeatureRegistrer.config("tree/autumn_birch/normal", Feature.TREE, autumnBirchTree().build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> AUTUMN_BIRCH_BEES_002 = PFeatureRegistrer.config("tree/autumn_birch/bees_002", Feature.TREE, autumnBirchTree().decorators(List.of(TreeUtil.BEES_002)).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_AUTUMN_BIRCH = PFeatureRegistrer.config("tree/autumn_birch/fancy", Feature.TREE, fancyAutumnBirchTree().build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_AUTUMN_BIRCH_BEES_002 = PFeatureRegistrer.config("tree/autumn_birch/fancy_bees_002", Feature.TREE, fancyAutumnBirchTree().decorators(List.of(TreeUtil.BEES_002)).build());


			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> AUTUMN_OAK_LEAF_PILE_PATCH = PFeatureRegistrer.config("patch/leaf_pile/autumn_oak", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(AUTUMN_OAK_LEAF_PILE), 32));
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> AUTUMN_BIRCH_LEAF_PILE_PATCH = PFeatureRegistrer.config("patch/leaf_pile/autumn_birch", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(AUTUMN_BIRCH_LEAF_PILE), 32));

			private static TreeFeatureConfig.Builder autumnOakTree() {
				return TreeUtil.buildNormal(Blocks.OAK_LOG, AUTUMN_OAK_LEAVES, 4, 2, 0, 2).ignoreVines();
			}

			private static TreeFeatureConfig.Builder fancyAutumnOakTree() {
				return TreeUtil.buildFancy(Blocks.OAK_LOG, AUTUMN_OAK_LEAVES, 3, 11, 0, 2).ignoreVines();
			}

			private static TreeFeatureConfig.Builder autumnBirchTree() {
				return TreeUtil.buildNormal(Blocks.BIRCH_LOG, AUTUMN_BIRCH_LEAVES, 5, 2, 0, 2).ignoreVines();
			}

			private static TreeFeatureConfig.Builder fancyAutumnBirchTree() {
				return TreeUtil.buildFancy(Blocks.BIRCH_LOG, AUTUMN_BIRCH_LEAVES, 4, 11, 0, 2).ignoreVines();
			}

			private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
				return ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block), List.of(), tries);
			}
		}

		public static class Placed {
			/*---------*/
			/*  TREES  */
			/*---------*/
			public static final RegistryEntry<PlacedFeature> AUTUMN_OAK = PFeatureRegistrer.place("tree/autumn_oak/normal", Configured.AUTUMN_OAK, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> AUTUMN_OAK_BEES_002 = PFeatureRegistrer.place("tree/autumn_oak/bees_002", Configured.AUTUMN_OAK_BEES_002, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_AUTUMN_OAK = PFeatureRegistrer.place("tree/autumn_oak/fancy", Configured.FANCY_AUTUMN_OAK, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_AUTUMN_OAK_BEES_002 = PFeatureRegistrer.place("tree/autumn_oak/fancy_bees_002", Configured.FANCY_AUTUMN_OAK_BEES_002, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));

			public static final RegistryEntry<PlacedFeature> AUTUMN_BIRCH = PFeatureRegistrer.place("tree/autumn_birch/normal", Configured.AUTUMN_BIRCH, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_BIRCH_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> AUTUMN_BIRCH_BEES_002 = PFeatureRegistrer.place("tree/autumn_birch/bees_002", Configured.AUTUMN_BIRCH_BEES_002, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_BIRCH_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_AUTUMN_BIRCH = PFeatureRegistrer.place("tree/autumn_birch/fancy", Configured.FANCY_AUTUMN_BIRCH, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_BIRCH_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_AUTUMN_BIRCH_BEES_002 = PFeatureRegistrer.place("tree/autumn_birch/fancy_bees_002", Configured.FANCY_AUTUMN_BIRCH_BEES_002, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(AUTUMN_BIRCH_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));

			public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> PUMPKIN_PASTURES_TREES_C = PFeatureRegistrer.config("trees/pumpkin_pastures", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(Placed.AUTUMN_OAK_BEES_002, 0.2f), new RandomFeatureEntry(Placed.FANCY_AUTUMN_BIRCH_BEES_002, 0.1f)), Placed.AUTUMN_BIRCH_BEES_002));
			public static final RegistryEntry<PlacedFeature> PUMPKIN_PASTURES_TREES = PFeatureRegistrer.place("trees/pumpkin_pastures", PUMPKIN_PASTURES_TREES_C, VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(10, 0.1f, 1)));

			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final RegistryEntry<PlacedFeature> AUTUMN_OAK_LEAF_PILE_FOREST_PATCH = PFeatureRegistrer.place("patch/leaf_pile/autumn_oak/forest", Configured.AUTUMN_OAK_LEAF_PILE_PATCH, VegetationPlacedFeatures.modifiers(5));
			public static final RegistryEntry<PlacedFeature> AUTUMN_BIRCH_LEAF_PILE_FOREST_PATCH = PFeatureRegistrer.place("patch/leaf_pile/autumn_birch/forest", Configured.AUTUMN_BIRCH_LEAF_PILE_PATCH, VegetationPlacedFeatures.modifiers(5));
		}
	}

	public static class Biomes {
		public static final BiomeCreator PUMPKIN_PASTURES = creator(new BiomeCreator("pumpkin_pastures", createPumpkinPastures()));

		private static Biome createPumpkinPastures() {
			GenerationSettings genSettings = BiomeUtil.composeForestGenerationSettings()
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PUMPKIN_PASTURES_TREES)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.AUTUMN_OAK_LEAF_PILE_FOREST_PATCH)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.AUTUMN_BIRCH_LEAF_PILE_FOREST_PATCH)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, MushroomBundle.Features.Placed.YELLOW_MUSHROOM_NORMAL_PATCH)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, MushroomBundle.Features.Placed.ORANGE_MUSHROOM_NORMAL_PATCH)
					.build();
			BiomeEffects effects = BiomeUtil.genericEffectBuilder(0.8F)
					.waterColor(155336)
					.waterFogColor(541)
					.moodSound(BiomeMoodSound.CAVE)
					.foliageColor(15232304)
					.grassColor(15443554)
					.build();
			return BiomeUtil.createBiome(Biome.Category.FOREST, Biome.Precipitation.RAIN, 0.8F, 0.9F, BiomeUtil.composeForestSpawn().build(), genSettings, effects);
		}
	}
}
