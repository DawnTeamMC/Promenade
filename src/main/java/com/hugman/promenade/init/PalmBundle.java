package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.bundle.block.OverworldWoodBundle;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.PalmSaplingGenerator;
import com.hugman.promenade.object.trade_offers.SellSaplingFactory;
import com.hugman.promenade.object.world.gen.tree.foliage.PalmFoliagePlacer;
import com.hugman.promenade.object.world.gen.tree.trunk.LeapingTrunkPlacer;
import com.hugman.promenade.util.BlockBuilders;
import com.hugman.promenade.util.PFeatureRegistrer;
import com.hugman.promenade.util.TreeUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class PalmBundle extends PromenadeBundle {
	public static final OverworldWoodBundle PALM_WOOD = creator(new OverworldWoodBundle.Builder("palm", new PalmSaplingGenerator(), MapColor.ORANGE, MapColor.TERRACOTTA_CYAN).saplingSoil(blockState -> blockState.isIn(BlockTags.SAND)).build());
	public static final Block PALM_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("palm_leaf_pile").build());

	public static void addWanderingSales() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(new SellSaplingFactory(PALM_WOOD.getSapling()));
		});
	}

	public static class Features {
		public static class Configured {
			public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PALM = PFeatureRegistrer.config("tree/palm", Feature.TREE, createPalm().build());

			private static TreeFeatureConfig.Builder createPalm() {
				return TreeUtil.build(PALM_WOOD.getLog(), PALM_WOOD.getLeaves(), new LeapingTrunkPlacer(6, 5, 2, BiasedToBottomIntProvider.create(3, 10), UniformIntProvider.create(-1, 0), 0.45F, 2), new PalmFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().dirtProvider(BlockStateProvider.of(Blocks.SAND));
			}
		}

		public static class Placed {
			public static final RegistryEntry<PlacedFeature> PALMS = PFeatureRegistrer.place("tree/palms", Configured.PALM, VegetationPlacedFeatures.modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.1f, 1), PALM_WOOD.getSapling()));
		}
	}

	public static void addToGen() {
		if (Promenade.CONFIG.world_features.palms) {
			BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PALMS.getKey().orElseThrow());
		}
	}
}
