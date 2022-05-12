package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.promenade.util.WorldGenUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PinkCherryOakSaplingGenerator extends DynamicSaplingGenerator
{
	public static final RegistryKey<ConfiguredFeature<?, ?>> NORMAL = WorldGenUtil.configuredFeatureKey("tree/pink_cherry_oak/normal");
	public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY = WorldGenUtil.configuredFeatureKey("tree/pink_cherry_oak/fancy");
	public static final RegistryKey<ConfiguredFeature<?, ?>> BEES = WorldGenUtil.configuredFeatureKey("tree/pink_cherry_oak/bees_005");
	public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_BEES = WorldGenUtil.configuredFeatureKey("tree/pink_cherry_oak/fancy_bees_005");

	@Nullable
	@Override
	protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(ServerWorld world, Random random, boolean bees) {
		return WorldGenUtil.treeEntryWithFancyBees(world, random, bees, NORMAL, BEES, FANCY, FANCY_BEES);
	}
}