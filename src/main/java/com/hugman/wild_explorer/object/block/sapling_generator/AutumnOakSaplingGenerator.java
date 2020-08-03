package com.hugman.wild_explorer.object.block.sapling_generator;

import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class AutumnOakSaplingGenerator extends SaplingGenerator {
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean beeHive) {
		return random.nextInt(10) == 0 ? beeHive ? WEConfiguredFeatures.FANCY_AUTUMN_BIRCH_BEES_002 : WEConfiguredFeatures.FANCY_AUTUMN_BIRCH : beeHive ? WEConfiguredFeatures.AUTUMN_OAK_BEES_002 : WEConfiguredFeatures.AUTUMN_OAK;
	}
}