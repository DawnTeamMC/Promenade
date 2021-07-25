package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.init.world.PromenadeConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class AutumnBirchSaplingGenerator extends SaplingGenerator {
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
		return random.nextInt(10) == 0 ? bees ? PromenadeConfiguredFeatures.FANCY_AUTUMN_BIRCH_BEES_002 : PromenadeConfiguredFeatures.FANCY_AUTUMN_BIRCH : bees ? PromenadeConfiguredFeatures.AUTUMN_BIRCH_BEES_002 : PromenadeConfiguredFeatures.AUTUMN_BIRCH;
	}
}