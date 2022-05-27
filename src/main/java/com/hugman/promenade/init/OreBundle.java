package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.block.BSSBundle;
import com.hugman.dawn.api.creator.bundle.block.BSSWBundle;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class OreBundle extends PromenadeBundle
{
	public static final BSSWBundle BLUNITE = creator(new BSSWBundle(new BlockCreator.Builder().name("blunite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).sounds(BlockSoundGroup.TUFF))));
	public static final BSSWBundle CARBONITE = creator(new BSSWBundle(new BlockCreator.Builder().name("carbonite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.BASALT))));
	public static final BSSBundle POLISHED_BLUNITE = creator(new BSSBundle(new BlockCreator.Builder().name("polished_blunite").settings(FabricBlockSettings.copyOf(BLUNITE.getBlock()))));
	public static final BSSBundle POLISHED_CARBONITE = creator(new BSSBundle(new BlockCreator.Builder().name("polished_carbonite").settings(FabricBlockSettings.copyOf(CARBONITE.getBlock()))));

	public static final RegistryKey<PlacedFeature> ORE_BLUNITE_UPPER = WorldGenUtil.placedFeatureKey("ore/blunite/upper");
	public static final RegistryKey<PlacedFeature> ORE_BLUNITE_LOWER = WorldGenUtil.placedFeatureKey("ore/blunite/lower");
	public static final RegistryKey<PlacedFeature> ORE_CARBONITE_UPPER = WorldGenUtil.placedFeatureKey("ore/carbonite/upper");
	public static final RegistryKey<PlacedFeature> ORE_CARBONITE_LOWER = WorldGenUtil.placedFeatureKey("ore/carbonite/lower");

	public static void addToGen() {
		if(Promenade.CONFIG.world_features.igneous_rock_patches) {
			Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasBuiltInFeature(OreConfiguredFeatures.ORE_ANDESITE.value()) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_DIORITE.value()) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_GRANITE.value());
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_UPPER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_LOWER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_CARBONITE_UPPER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_CARBONITE_LOWER);
		}
	}
}
