package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.dawn.api.object.block.sapling_generator.DynamicSaplingGenerator;
import com.hugman.promenade.util.WorldGenUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class SimpleSaplingGenerator extends DynamicSaplingGenerator {
	public final RegistryKey<ConfiguredFeature<?, ?>> regular;
	public final RegistryKey<ConfiguredFeature<?, ?>> bees;
	public final RegistryKey<ConfiguredFeature<?, ?>> fancy;
	public final RegistryKey<ConfiguredFeature<?, ?>> fancyBees;

	public SimpleSaplingGenerator(String typeName, String beeChance) {
		this.regular = WorldGenUtil.configuredFeatureKey("tree/" + typeName + "/regular");
		this.bees = WorldGenUtil.configuredFeatureKey("tree/" + typeName + "/bees_" + beeChance);
		this.fancy = WorldGenUtil.configuredFeatureKey("tree/" + typeName + "/fancy");
		this.fancyBees = WorldGenUtil.configuredFeatureKey("tree/" + typeName + "/fancy_bees_" + beeChance);
	}

	@Nullable
	@Override
	protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(ServerWorld world, Random random, boolean bees) {
		return WorldGenUtil.getTreeEntryWithFancyBees(world, random, bees, this.regular, this.bees, this.fancy, this.fancyBees);
	}
}