package com.hugman.wild_explorer.object.block.sapling_generator;

import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class WhiteCherryOakSaplingGenerator extends SaplingGenerator {
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean beeHive) {
		return random.nextInt(10) == 0 ? beeHive ? WEConfiguredFeatures.FANCY_WHITE_CHERRY_OAK_BEES_005 : WEConfiguredFeatures.FANCY_WHITE_CHERRY_OAK : beeHive ? WEConfiguredFeatures.WHITE_CHERRY_OAK_BEES_005 : WEConfiguredFeatures.WHITE_CHERRY_OAK;
	}
}