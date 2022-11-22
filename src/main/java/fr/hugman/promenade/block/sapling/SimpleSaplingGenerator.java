package fr.hugman.promenade.block.sapling;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.promenade.Promenade;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class SimpleSaplingGenerator extends SaplingGenerator {
	public final RegistryKey<ConfiguredFeature<?, ?>> regular;
	public final RegistryKey<ConfiguredFeature<?, ?>> bees;
	public final RegistryKey<ConfiguredFeature<?, ?>> fancy;
	public final RegistryKey<ConfiguredFeature<?, ?>> fancyBees;

	public SimpleSaplingGenerator(String typeName, String beeChance) {
		this.regular = DawnFactory.configuredFeature(Promenade.id("tree/" + typeName + "/regular"));
		this.bees = DawnFactory.configuredFeature(Promenade.id("tree/" + typeName + "/bees_" + beeChance));
		this.fancy = DawnFactory.configuredFeature(Promenade.id("tree/" + typeName + "/fancy"));
		this.fancyBees = DawnFactory.configuredFeature(Promenade.id("tree/" + typeName + "/fancy_bees_" + beeChance));
	}

	@Nullable
	@Override
	protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return (random.nextInt(10) == 0) ? (bees ? this.fancyBees : this.fancy) :  (bees ? this.bees : this.regular);
	}
}