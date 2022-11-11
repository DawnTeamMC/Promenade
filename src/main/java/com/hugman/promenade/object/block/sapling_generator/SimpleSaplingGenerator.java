package com.hugman.promenade.object.block.sapling_generator;

import com.hugman.dawn.api.object.block.sapling_generator.DynamicSaplingGenerator;
import com.hugman.newdawn.DawnFactory;
import com.hugman.promenade.Promenade;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
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
		this.regular = DawnFactory.configuredFeatureKey(Promenade.id("tree/" + typeName + "/regular"));
		this.bees = DawnFactory.configuredFeatureKey(Promenade.id("tree/" + typeName + "/bees_" + beeChance));
		this.fancy = DawnFactory.configuredFeatureKey(Promenade.id("tree/" + typeName + "/fancy"));
		this.fancyBees = DawnFactory.configuredFeatureKey(Promenade.id("tree/" + typeName + "/fancy_bees_" + beeChance));
	}

	@Nullable
	@Override
	protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(ServerWorld world, Random random, boolean bees) {
		return pickTreeEntryWithFancyBees(world, random, bees, this.regular, this.bees, this.fancy, this.fancyBees);
	}

	@Nullable
	public static RegistryEntry<? extends ConfiguredFeature<?, ?>> pickTreeEntryWithFancyBees(ServerWorld world, Random random, boolean bees, RegistryKey<ConfiguredFeature<?, ?>> keyNormal, RegistryKey<ConfiguredFeature<?, ?>> keyNormalBees, RegistryKey<ConfiguredFeature<?, ?>> keyFancy, RegistryKey<ConfiguredFeature<?, ?>> keyFancyBees) {
		Registry<ConfiguredFeature<?, ?>> registry = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY);
		RegistryEntry<ConfiguredFeature<?, ?>> normal = registry.getEntry(keyNormal).orElse(null);
		RegistryEntry<ConfiguredFeature<?, ?>> normalBees = registry.getEntry(keyNormalBees).orElse(normal);
		RegistryEntry<ConfiguredFeature<?, ?>> fancy = registry.getEntry(keyFancy).orElse(normal);
		RegistryEntry<ConfiguredFeature<?, ?>> fancyBees = registry.getEntry(keyFancyBees).orElse(fancy);

		return random.nextInt(10) == 0 ? bees ? fancyBees : fancy : bees ? normalBees : normal;
	}
}