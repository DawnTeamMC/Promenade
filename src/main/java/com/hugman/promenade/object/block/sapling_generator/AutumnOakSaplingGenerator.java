package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.init.AutumnBundle;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class AutumnOakSaplingGenerator extends SaplingGenerator {
	@Override
	protected RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeFeature(Random random, boolean beeHive) {
		return random.nextInt(10) == 0 ? beeHive ? AutumnBundle.Features.Configured.FANCY_AUTUMN_OAK_BEES_002 : AutumnBundle.Features.Configured.FANCY_AUTUMN_OAK : beeHive ? AutumnBundle.Features.Configured.AUTUMN_BIRCH_BEES_002 : AutumnBundle.Features.Configured.AUTUMN_BIRCH;
	}
}