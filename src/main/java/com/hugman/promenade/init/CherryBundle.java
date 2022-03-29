package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.dawn.api.creator.ParticleCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.creator.bundle.block.WoodBundle;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.PinkCherryOakSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.WhiteCherryOakSaplingGenerator;
import com.hugman.promenade.object.trade_offers.SellSaplingFactory;
import com.hugman.promenade.object.world.gen.feature.BoulderFeatureConfig;
import com.hugman.promenade.util.BiomeUtil;
import com.hugman.promenade.util.BlockBuilders;
import com.hugman.promenade.util.PFeatureRegistrer;
import com.hugman.promenade.util.TreeUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;

import java.util.List;

public class CherryBundle extends PromenadeBundle {
	/*----------*/
	/*  BLOCKS  */
	/*----------*/
	public static final WoodBundle CHERRY_OAK_WOOD = creator(new WoodBundle.Builder("cherry_oak", MapColor.DULL_RED, MapColor.DULL_PINK, MapColor.DARK_DULL_PINK, false).build());
	public static final PlantBundle PINK_CHERRY_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("pink_cherry_oak_sapling").provider(s -> new SaplingBlock(new PinkCherryOakSaplingGenerator(), s))));
	public static final Block PINK_CHERRY_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("pink_cherry_oak_leaves").settings(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.AZALEA_LEAVES).nonOpaque().allowsSpawning(DefaultBlockSettings::canSpawnOnLeaves).suffocates(DefaultBlockSettings::never).blockVision(DefaultBlockSettings::never)).build());
	public static final Block PINK_CHERRY_OAK_LEAF_PILE = add(BlockBuilders.leafPile("pink_cherry_oak_leaf_pile", BlockSoundGroup.AZALEA_LEAVES).build());
	public static final PlantBundle WHITE_CHERRY_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("white_cherry_oak_sapling").provider(s -> new SaplingBlock(new WhiteCherryOakSaplingGenerator(), s))));
	public static final Block WHITE_CHERRY_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("white_cherry_oak_leaves").settings(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.AZALEA_LEAVES).nonOpaque().allowsSpawning(DefaultBlockSettings::canSpawnOnLeaves).suffocates(DefaultBlockSettings::never).blockVision(DefaultBlockSettings::never)).build());
	public static final Block WHITE_CHERRY_OAK_LEAF_PILE = add(BlockBuilders.leafPile("white_cherry_oak_leaf_pile", BlockSoundGroup.AZALEA_LEAVES).build());
	public static final DefaultParticleType PINK_CHERRY_BLOSSOM = add(new ParticleCreator<>("pink_cherry_blossom", FabricParticleTypes.simple()));
	public static final DefaultParticleType WHITE_CHERRY_BLOSSOM = add(new ParticleCreator<>("white_cherry_blossom", FabricParticleTypes.simple()));

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
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_CHERRY_OAK = PFeatureRegistrer.config("tree/pink_cherry_oak/normal", Feature.TREE, buildNormalCherryOak(PINK_CHERRY_OAK_LEAVES).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_CHERRY_OAK_BEES_005 = PFeatureRegistrer.config("tree/pink_cherry_oak/bees_005", Feature.TREE, buildNormalCherryOak(PINK_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_PINK_CHERRY_OAK = PFeatureRegistrer.config("tree/pink_cherry_oak/fancy", Feature.TREE, buildFancyCherryOak(PINK_CHERRY_OAK_LEAVES).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_PINK_CHERRY_OAK_BEES_005 = PFeatureRegistrer.config("tree/pink_cherry_oak/fancy_bees_005", Feature.TREE, buildFancyCherryOak(PINK_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build());

			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WHITE_CHERRY_OAK = PFeatureRegistrer.config("tree/white_cherry_oak/normal", Feature.TREE, buildNormalCherryOak(WHITE_CHERRY_OAK_LEAVES).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WHITE_CHERRY_OAK_BEES_005 = PFeatureRegistrer.config("tree/white_cherry_oak/bees_005", Feature.TREE, buildNormalCherryOak(WHITE_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_WHITE_CHERRY_OAK = PFeatureRegistrer.config("tree/white_cherry_oak/fancy", Feature.TREE, buildFancyCherryOak(WHITE_CHERRY_OAK_LEAVES).build());
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_WHITE_CHERRY_OAK_BEES_005 = PFeatureRegistrer.config("tree/white_cherry_oak/fancy_bees_005", Feature.TREE, buildFancyCherryOak(WHITE_CHERRY_OAK_LEAVES).decorators(List.of(TreeUtil.BEES_005)).build());

			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PINK_CHERRY_OAK_LEAF_PILE_PATCH = PFeatureRegistrer.config("patch/leaf_pile/pink_cherry_oak", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(PINK_CHERRY_OAK_LEAF_PILE), 32));
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WHITE_CHERRY_OAK_LEAF_PILE_PATCH = PFeatureRegistrer.config("patch/leaf_pile/white_cherry_oak", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(WHITE_CHERRY_OAK_LEAF_PILE), 32));

			/*---------*/
			/*  OTHER  */
			/*---------*/
			public static final RegistryEntry<ConfiguredFeature<BoulderFeatureConfig, ?>> CUTE_LITTLE_ROCK = PFeatureRegistrer.config("cute_little_rock", CommonBundle.BOULDER, new BoulderFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.STONE.getDefaultState(), 80).add(Blocks.CALCITE.getDefaultState(), 20)), List.of(Blocks.GRASS_BLOCK.getDefaultState()), UniformIntProvider.create(3, 4)));

			public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> GRAVEL_POOL = PFeatureRegistrer.config("water_pool/gravel", Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(Blocks.WATER.getDefaultState()), BlockStateProvider.of(Blocks.GRAVEL.getDefaultState())));
			public static final RegistryEntry<PlacedFeature> GRAVEL_WATER_POOL_BAMBOO = PFeatureRegistrer.place("water_pool/gravel/decoration/bamboo", VegetationConfiguredFeatures.BAMBOO_SOME_PODZOL);
			public static final RegistryEntry<PlacedFeature> GRAVEL_WATER_POOL_WATERLILY = PFeatureRegistrer.place("water_pool/gravel/decoration/waterlily", VegetationConfiguredFeatures.PATCH_WATERLILY);
			public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> GRAVEL_WATER_POOL_DECORATION_C = PFeatureRegistrer.config("water_pool/gravel/decoration", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(
					List.of(new RandomFeatureEntry(GRAVEL_WATER_POOL_BAMBOO, 0.4f)),
					GRAVEL_WATER_POOL_WATERLILY));
			public static final RegistryEntry<PlacedFeature> GRAVEL_WATER_POOL_DECORATION = PFeatureRegistrer.place("water_pool/gravel/decoration", GRAVEL_WATER_POOL_DECORATION_C);
			public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> DECORATED_GRAVEL_WATER_POOL = PFeatureRegistrer.config("water_pool/gravel/decorated", Feature.WATERLOGGED_VEGETATION_PATCH, buildDecoratedPool());

			private static VegetationPatchFeatureConfig buildDecoratedPool() {
				return new VegetationPatchFeatureConfig(
						BlockTags.LUSH_GROUND_REPLACEABLE, BlockStateProvider.of(Blocks.GRAVEL),
						GRAVEL_WATER_POOL_DECORATION,
						VerticalSurfaceType.FLOOR, ConstantIntProvider.create(3), 0.8f, 5, 0.15f, UniformIntProvider.create(4, 7), 0.7f);
			}

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
				return ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block), List.of(), tries);
			}
		}

		public static class Placed {
			/*---------*/
			/*  TREES  */
			/*---------*/
			public static final RegistryEntry<PlacedFeature> PINK_CHERRY_OAK = PFeatureRegistrer.place("tree/pink_cherry_oak/normal", Configured.PINK_CHERRY_OAK, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(PINK_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> PINK_CHERRY_OAK_BEES_005 = PFeatureRegistrer.place("tree/pink_cherry_oak/bees_002", Configured.PINK_CHERRY_OAK_BEES_005, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(PINK_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_PINK_CHERRY_OAK = PFeatureRegistrer.place("tree/pink_cherry_oak/fancy", Configured.FANCY_PINK_CHERRY_OAK, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(PINK_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_PINK_CHERRY_OAK_BEES_005 = PFeatureRegistrer.place("tree/pink_cherry_oak/fancy_bees_005", Configured.FANCY_PINK_CHERRY_OAK_BEES_005, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(PINK_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));

			public static final RegistryEntry<PlacedFeature> WHITE_CHERRY_OAK = PFeatureRegistrer.place("tree/white_cherry_oak/normal", Configured.WHITE_CHERRY_OAK, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(WHITE_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> WHITE_CHERRY_OAK_BEES_005 = PFeatureRegistrer.place("tree/white_cherry_oak/bees_005", Configured.WHITE_CHERRY_OAK_BEES_005, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(WHITE_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_WHITE_CHERRY_OAK = PFeatureRegistrer.place("tree/white_cherry_oak/fancy", Configured.FANCY_WHITE_CHERRY_OAK, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(WHITE_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));
			public static final RegistryEntry<PlacedFeature> FANCY_WHITE_CHERRY_OAK_BEES_005 = PFeatureRegistrer.place("tree/white_cherry_oak/fancy_bees_005", Configured.FANCY_WHITE_CHERRY_OAK_BEES_005, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(WHITE_CHERRY_OAK_SAPLING.getPlant().getDefaultState(), BlockPos.ORIGIN)));

			public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> PINK_CHERRY_OAK_FOREST_TREES_C = PFeatureRegistrer.config("trees/pink_cherry_oak_forest", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(Placed.WHITE_CHERRY_OAK, 0.2f), new RandomFeatureEntry(Placed.FANCY_PINK_CHERRY_OAK, 0.1f)), Placed.PINK_CHERRY_OAK));
			public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> WHITE_CHERRY_OAK_FOREST_TREES_C = PFeatureRegistrer.config("trees/white_cherry_oak_forest", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(Placed.PINK_CHERRY_OAK, 0.2f), new RandomFeatureEntry(Placed.FANCY_WHITE_CHERRY_OAK, 0.1f)), Placed.WHITE_CHERRY_OAK));

			public static final RegistryEntry<PlacedFeature> PINK_CHERRY_OAK_FOREST_TREES = PFeatureRegistrer.place("trees/pink_cherry_oak_forest", PINK_CHERRY_OAK_FOREST_TREES_C, VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(2, 0.1f, 1)));
			public static final RegistryEntry<PlacedFeature> WHITE_CHERRY_OAK_FOREST_TREES = PFeatureRegistrer.place("trees/white_cherry_oak_forest", WHITE_CHERRY_OAK_FOREST_TREES_C, VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(2, 0.1f, 1)));

			/*--------------*/
			/*  LEAF PILES  */
			/*--------------*/
			public static final RegistryEntry<PlacedFeature> PINK_CHERRY_OAK_LEAF_PILE_PATCH = PFeatureRegistrer.place("patch/leaf_pile/pink_cherry_oak/forest", Configured.PINK_CHERRY_OAK_LEAF_PILE_PATCH, VegetationPlacedFeatures.modifiers(3));
			public static final RegistryEntry<PlacedFeature> WHITE_CHERRY_OAK_LEAF_PILE_PATCH = PFeatureRegistrer.place("patch/leaf_pile/white_cherry_oak/forest", Configured.WHITE_CHERRY_OAK_LEAF_PILE_PATCH, VegetationPlacedFeatures.modifiers(3));

			/*---------*/
			/*  OTHER  */
			/*---------*/
			public static final RegistryEntry<PlacedFeature> CUTE_LITTLE_ROCKS = PFeatureRegistrer.place("cute_little_rocks", Configured.CUTE_LITTLE_ROCK, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

			public static final RegistryEntry<PlacedFeature> GRAVEL_WATER_POOLS = PFeatureRegistrer.place("water_pools/gravel", Configured.GRAVEL_POOL, RarityFilterPlacementModifier.of(7), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
			public static final RegistryEntry<PlacedFeature> DECORATED_GRAVEL_WATER_POOLS = PFeatureRegistrer.place("water_pools/gravel_decorated", Configured.DECORATED_GRAVEL_WATER_POOL, RarityFilterPlacementModifier.of(10), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
		}
	}

	public static class Biomes {
		public static final BiomeCreator PINK_CHERRY_OAK_FOREST  = creator(new BiomeCreator("pink_cherry_oak_forest",  createCherryOakForest(true)));
		public static final BiomeCreator WHITE_CHERRY_OAK_FOREST = creator(new BiomeCreator("white_cherry_oak_forest", createCherryOakForest(false)));

		private static Biome createCherryOakForest(boolean isPink) {
			GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();

			BiomeUtil.addBasicFeatures(genBuilder);
			genBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Features.Placed.CUTE_LITTLE_ROCKS);
			genBuilder.feature(GenerationStep.Feature.LAKES, Features.Placed.GRAVEL_WATER_POOLS);
			genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.DECORATED_GRAVEL_WATER_POOLS);
			DefaultBiomeFeatures.addForestFlowers(genBuilder);
			DefaultBiomeFeatures.addDefaultOres(genBuilder);
			DefaultBiomeFeatures.addDefaultDisks(genBuilder);

			genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BAMBOO_LIGHT);
			DefaultBiomeFeatures.addDefaultFlowers(genBuilder);
			DefaultBiomeFeatures.addForestGrass(genBuilder);
			genBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);
			DefaultBiomeFeatures.addDefaultMushrooms(genBuilder);
			DefaultBiomeFeatures.addDefaultVegetation(genBuilder);

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
			SpawnSettings.Builder spawnBuilder = BiomeUtil.composeForestSpawn();
			spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 16, 1, 3));
			spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PANDA, 2, 4, 5));

			BiomeEffects effects = BiomeUtil.genericEffectBuilder(0.6F)
					.waterColor(6459391)
					.waterFogColor(2170954)
					.particleConfig(new BiomeParticleConfig(isPink ? PINK_CHERRY_BLOSSOM : WHITE_CHERRY_BLOSSOM, 0.001F))
					.moodSound(BiomeMoodSound.CAVE)
					.build();

			return BiomeUtil.createBiome(Biome.Category.FOREST, Biome.Precipitation.RAIN, 0.6F, 0.4F, spawnBuilder.build(), genBuilder.build(), effects);
		}
	}
}
