package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.init.PalmBundle;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class PalmSaplingGenerator extends SaplingGenerator {
	@Override
	protected RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeFeature(Random random, boolean beeHive) {
		return PalmBundle.Features.Configured.PALM;
	}
}