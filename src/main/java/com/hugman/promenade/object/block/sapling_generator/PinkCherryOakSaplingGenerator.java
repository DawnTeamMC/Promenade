package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.init.CherryBundle;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class PinkCherryOakSaplingGenerator extends SaplingGenerator {
	@Override
	protected RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeFeature(Random random, boolean beeHive) {
		return random.nextInt(10) == 0 ? beeHive ? CherryBundle.Features.Configured.FANCY_PINK_CHERRY_OAK_BEES_005 : CherryBundle.Features.Configured.FANCY_PINK_CHERRY_OAK : beeHive ? CherryBundle.Features.Configured.PINK_CHERRY_OAK_BEES_005 : CherryBundle.Features.Configured.PINK_CHERRY_OAK;
	}
}