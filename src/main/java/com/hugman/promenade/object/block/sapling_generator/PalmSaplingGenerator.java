package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.util.WorldGenUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class PalmSaplingGenerator extends DynamicSaplingGenerator
{
	public static final RegistryKey<ConfiguredFeature<?, ?>> PALM = WorldGenUtil.configuredFeatureKey("tree/palm");

	@Nullable
	@Override
	protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(ServerWorld world, Random random, boolean bees) {
		return world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY).getEntry(PALM).orElse(null);
	}
}