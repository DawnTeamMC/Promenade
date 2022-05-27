package com.hugman.promenade.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.OptionalInt;

public final class TreeUtil {
	public static final BeehiveTreeDecorator BEES_0002 = new BeehiveTreeDecorator(0.002f);
	public static final BeehiveTreeDecorator BEES_002 = new BeehiveTreeDecorator(0.02f);
	public static final BeehiveTreeDecorator BEES_005 = new BeehiveTreeDecorator(0.05f);
	public static final BeehiveTreeDecorator BEES = new BeehiveTreeDecorator(1.0f);

	public static TreeFeatureConfig.Builder buildNormal(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
		return build(log, leaves, new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), new BlobFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), 3), new TwoLayersFeatureSize(1, 0, 1));
	}

	public static TreeFeatureConfig.Builder buildFancy(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
		return build(log, leaves, new LargeOakTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), new LargeOakFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)));
	}

	public static TreeFeatureConfig.Builder build(Block log, Block leaves, TrunkPlacer trunk, FoliagePlacer foliage, FeatureSize size) {
		return new TreeFeatureConfig.Builder(BlockStateProvider.of(log), trunk, BlockStateProvider.of(leaves), foliage, size);
	}
}
