package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.OverworldWoodBundle;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.PalmSaplingGenerator;
import com.hugman.promenade.util.BlockSettingsUtil;
import com.hugman.promenade.util.BlockTemplateUtil;
import com.hugman.promenade.util.GenUtil;
import com.hugman.promenade.util.TreeUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;


public class PalmBundle extends PromenadeBundle {
	public static final OverworldWoodBundle PALM_WOOD = bundle(new OverworldWoodBundle.Builder("palm", new PalmSaplingGenerator(), MapColor.ORANGE, MapColor.TERRACOTTA_CYAN).saplingSoil(blockState -> blockState.isIn(BlockTags.SAND)).build());
	public static final Block PALM_LEAF_PILE = add(new BlockCreator.Builder("palm", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).compostingChance(0.3f).build());

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<TreeFeatureConfig, ?> PALM = add(new ConfiguredFeatureCreator<>("tree/palm", Feature.TREE.configure(createPalm().build())));

			private static TreeFeatureConfig.Builder createPalm() {
				return TreeUtil.build(PALM_WOOD.getLog(), PALM_WOOD.getLeaves(), new ForkingTrunkPlacer(16, 2, 2), new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines();
			}
 		}

		public static class Placed {
			public static final PlacedFeature PALM_TREES = add(new PlacedFeatureCreator("trees/palm", Configured.PALM.withWouldSurviveFilter(PALM_WOOD.getSapling())));
		}
	}

	public static void addToGen() {
		if(Promenade.CONFIG.features.palm_trees) {
			BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, GenUtil.getKey(Features.Placed.PALM_TREES));
		}
	}
}
