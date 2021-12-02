package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import com.hugman.promenade.object.block.sapling_generator.AutumnBirchSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.AutumnOakSaplingGenerator;
import com.hugman.promenade.util.BiomeUtil;
import com.hugman.promenade.util.BlockSettingsUtil;
import com.hugman.promenade.util.BlockTemplateUtil;
import com.hugman.promenade.util.TreeUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class AutumnBundle extends PromenadeBundle {
	/*----------*/
	/*  BLOCKS  */
	/*----------*/
	public static final PlantBundle AUTUMN_OAK_SAPLING = bundle(new PlantBundle(new BlockCreator.Builder("autumn_oak_sapling", settings -> new SaplingBlock(new AutumnOakSaplingGenerator(), settings), DefaultBlockSettings.SAPLING).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final Block AUTUMN_OAK_LEAVES = add(new BlockCreator.Builder("autumn_oak", DefaultBlockTemplates.LEAVES, DefaultBlockSettings.LEAVES).flammability(30, 60).build());
	public static final Block AUTUMN_OAK_LEAF_PILE = add(new BlockCreator.Builder("autumn_oak", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());
	public static final PlantBundle AUTUMN_BIRCH_SAPLING = bundle(new PlantBundle(new BlockCreator.Builder("autumn_birch_sapling", settings -> new SaplingBlock(new AutumnBirchSaplingGenerator(), settings), DefaultBlockSettings.SAPLING).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final Block AUTUMN_BIRCH_LEAVES = add(new BlockCreator.Builder("autumn_birch", DefaultBlockTemplates.LEAVES, DefaultBlockSettings.LEAVES).flammability(30, 60).build());
	public static final Block AUTUMN_BIRCH_LEAF_PILE = add(new BlockCreator.Builder("autumn_birch", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());

	public static void addToGen() {
		/**
		 * TODO: add when API is here LOL
		 *
		 * if(Promenade.CONFIG.biomes.pumpkin_pastures)
		 * 			OverworldBiomes.addContinentalBiome(AutumnBundle.Biomes.PUMPKIN_PASTURES.getRegistryKey(), OverworldClimate.COOL, Promenade.CONFIG.biomes.pumpkin_pastures_weight / 10.0D);
		 */
	}

	public static class Features {
		public static class Configured {
			/*---------*/
			/*  TREES  */
			/*---------*/
			public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_OAK = add(new ConfiguredFeatureCreator<>("tree/autumn_oak/normal", Feature.TREE.configure(autumnOakTree().build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_OAK_BEES_002 = add(new ConfiguredFeatureCreator<>("tree/autumn_oak/bees_002", Feature.TREE.configure(autumnOakTree().decorators(List.of(TreeUtil.BEES_002)).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_OAK = add(new ConfiguredFeatureCreator<>("tree/autumn_oak/fancy", Feature.TREE.configure(fancyAutumnOakTree().build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_OAK_BEES_002 = add(new ConfiguredFeatureCreator<>("tree/autumn_oak/fancy_bees_002", Feature.TREE.configure(fancyAutumnOakTree().decorators(List.of(TreeUtil.BEES_002)).build())));

			public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_BIRCH = add(new ConfiguredFeatureCreator<>("tree/autumn_birch/normal", Feature.TREE.configure(autumnBirchTree().build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> AUTUMN_BIRCH_BEES_002 = add(new ConfiguredFeatureCreator<>("tree/autumn_birch/bees_002", Feature.TREE.configure(autumnBirchTree().decorators(List.of(TreeUtil.BEES_002)).build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_BIRCH = add(new ConfiguredFeatureCreator<>("tree/autumn_birch/fancy", Feature.TREE.configure(fancyAutumnBirchTree().build())));
			public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_AUTUMN_BIRCH_BEES_002 = add(new ConfiguredFeatureCreator<>("tree/autumn_birch/fancy_bees_002", Feature.TREE.configure(fancyAutumnBirchTree().decorators(List.of(TreeUtil.BEES_002)).build())));

			public static final ConfiguredFeature<RandomFeatureConfig, ?> PUMPKIN_PASTURES_TREES = add(new ConfiguredFeatureCreator<>("trees/pumpkin_pastures", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(Placed.AUTUMN_OAK_BEES_002, 0.2f), new RandomFeatureEntry(Placed.FANCY_AUTUMN_BIRCH_BEES_002, 0.1f)), Placed.AUTUMN_BIRCH_BEES_002))));

			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final ConfiguredFeature<?, ?> AUTUMN_OAK_LEAF_PILE_PATCH = add(new ConfiguredFeatureCreator<>("patch/leaf_pile/autumn_oak", Feature.RANDOM_PATCH.configure(createRandomPatchFeatureConfig(BlockStateProvider.of(AUTUMN_OAK_LEAF_PILE), 32))));
			public static final ConfiguredFeature<?, ?> AUTUMN_BIRCH_LEAF_PILE_PATCH = add(new ConfiguredFeatureCreator<>("patch/leaf_pile/autumn_birch", Feature.RANDOM_PATCH.configure(createRandomPatchFeatureConfig(BlockStateProvider.of(AUTUMN_BIRCH_LEAF_PILE), 32))));

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
				return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
			}
		}

		public static class Placed {
			/*---------*/
			/*  TREES  */
			/*---------*/
			public static final PlacedFeature AUTUMN_OAK = add(new PlacedFeatureCreator("tree/autumn_oak/normal", Configured.AUTUMN_OAK.withWouldSurviveFilter(AUTUMN_OAK_SAPLING.getPlant())));
			public static final PlacedFeature AUTUMN_OAK_BEES_002 = add(new PlacedFeatureCreator("tree/autumn_oak/bees_002", Configured.AUTUMN_OAK_BEES_002.withWouldSurviveFilter(AUTUMN_OAK_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_AUTUMN_OAK = add(new PlacedFeatureCreator("tree/autumn_oak/fancy", Configured.FANCY_AUTUMN_OAK.withWouldSurviveFilter(AUTUMN_OAK_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_AUTUMN_OAK_BEES_002 = add(new PlacedFeatureCreator("tree/autumn_oak/fancy_bees_002", Configured.FANCY_AUTUMN_OAK_BEES_002.withWouldSurviveFilter(AUTUMN_OAK_SAPLING.getPlant())));

			public static final PlacedFeature AUTUMN_BIRCH = add(new PlacedFeatureCreator("tree/autumn_birch/normal", Configured.AUTUMN_BIRCH.withWouldSurviveFilter(AUTUMN_BIRCH_SAPLING.getPlant())));
			public static final PlacedFeature AUTUMN_BIRCH_BEES_002 = add(new PlacedFeatureCreator("tree/autumn_birch/bees_002", Configured.AUTUMN_BIRCH_BEES_002.withWouldSurviveFilter(AUTUMN_BIRCH_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_AUTUMN_BIRCH = add(new PlacedFeatureCreator("tree/autumn_birch/fancy", Configured.FANCY_AUTUMN_BIRCH.withWouldSurviveFilter(AUTUMN_BIRCH_SAPLING.getPlant())));
			public static final PlacedFeature FANCY_AUTUMN_BIRCH_BEES_002 = add(new PlacedFeatureCreator("tree/autumn_birch/fancy_bees_002", Configured.FANCY_AUTUMN_BIRCH_BEES_002.withWouldSurviveFilter(AUTUMN_BIRCH_SAPLING.getPlant())));

			public static final PlacedFeature PUMPKIN_PASTURES_TREES = add(new PlacedFeatureCreator("trees/pumpkin_pastures", Configured.PUMPKIN_PASTURES_TREES.withPlacement(VegetationPlacedFeatures.modifiers(net.minecraft.world.gen.feature.PlacedFeatures.createCountExtraModifier(10, 0.1f, 1)))));

			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final PlacedFeature AUTUMN_OAK_LEAF_PILE_FOREST_PATCH = add(new PlacedFeatureCreator("patch/leaf_pile/autumn_oak/forest", Configured.AUTUMN_OAK_LEAF_PILE_PATCH.withPlacement(VegetationPlacedFeatures.modifiers(5))));
			public static final PlacedFeature AUTUMN_BIRCH_LEAF_PILE_FOREST_PATCH = add(new PlacedFeatureCreator("patch/leaf_pile/autumn_birch/forest", Configured.AUTUMN_BIRCH_LEAF_PILE_PATCH.withPlacement(VegetationPlacedFeatures.modifiers(5))));
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
			return BiomeUtil.createForest(genSettings, effects, 0.8F, 0.9F);
		}
	}
}
