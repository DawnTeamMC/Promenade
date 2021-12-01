package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.init.world.feature.PromenadeConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class WhiteCherryOakSaplingGenerator extends SaplingGenerator {
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean beeHive) {
		return random.nextInt(10) == 0 ? beeHive ? PromenadeConfiguredFeatures.FANCY_WHITE_CHERRY_OAK_BEES_005 : PromenadeConfiguredFeatures.FANCY_WHITE_CHERRY_OAK : beeHive ? PromenadeConfiguredFeatures.WHITE_CHERRY_OAK_BEES_005 : PromenadeConfiguredFeatures.WHITE_CHERRY_OAK;
	}
}