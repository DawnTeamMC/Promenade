package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.init.world.PromenadeConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class AutumnOakSaplingGenerator extends SaplingGenerator {
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean beeHive) {
		return random.nextInt(10) == 0 ? beeHive ? PromenadeConfiguredFeatures.FANCY_AUTUMN_BIRCH_BEES_002 : PromenadeConfiguredFeatures.FANCY_AUTUMN_BIRCH : beeHive ? PromenadeConfiguredFeatures.AUTUMN_OAK_BEES_002 : PromenadeConfiguredFeatures.AUTUMN_OAK;
	}
}