package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.block.BSSBundle;
import com.hugman.dawn.api.creator.bundle.block.BSSWBundle;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.util.GenUtil;
import com.hugman.promenade.util.PFeatureRegistrer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

import java.util.function.Predicate;

public class OreBundle extends PromenadeBundle {
	public static final BSSWBundle BLUNITE = creator(new BSSWBundle(new BlockCreator.Builder().name("blunite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).sounds(BlockSoundGroup.TUFF))));
	public static final BSSWBundle CARBONITE = creator(new BSSWBundle(new BlockCreator.Builder().name("carbonite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.BASALT))));
	public static final BSSBundle POLISHED_BLUNITE = creator(new BSSBundle(new BlockCreator.Builder().name("polished_blunite").settings(FabricBlockSettings.copyOf(BLUNITE.getBlock()))));
	public static final BSSBundle POLISHED_CARBONITE = creator(new BSSBundle(new BlockCreator.Builder().name("polished_carbonite").settings(FabricBlockSettings.copyOf(CARBONITE.getBlock()))));

	public static class Features {
		public static class Configured {
			public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> BLUNITE_ORE = PFeatureRegistrer.config("ore/blunite", Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, BLUNITE.getBlock().getDefaultState(), 25));
			public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> CARBONITE_ORE = PFeatureRegistrer.config("ore/carbonite", Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, CARBONITE.getBlock().getDefaultState(), 25));
		}

		public static class Placed {
			public static final RegistryEntry<PlacedFeature> ORE_BLUNITE_UPPER = PFeatureRegistrer.place("ore/blunite/upper", Configured.BLUNITE_ORE, GenUtil.modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))));
			public static final RegistryEntry<PlacedFeature> ORE_BLUNITE_LOWER = PFeatureRegistrer.place("ore/blunite/lower", Configured.BLUNITE_ORE, GenUtil.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));

			public static final RegistryEntry<PlacedFeature> ORE_CARBONITE_UPPER = PFeatureRegistrer.place("ore/carbonite/upper", Configured.CARBONITE_ORE, GenUtil.modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))));
			public static final RegistryEntry<PlacedFeature> ORE_CARBONITE_LOWER = PFeatureRegistrer.place("ore/carbonite/lower", Configured.CARBONITE_ORE, GenUtil.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));
		}
	}

	public static void addToGen() {
		if(Promenade.CONFIG.world_features.igneous_rock_patches) {
			Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasBuiltInFeature(OreConfiguredFeatures.ORE_ANDESITE.value()) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_DIORITE.value()) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_GRANITE.value());
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, Features.Placed.ORE_BLUNITE_UPPER.getKey().orElseThrow());
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, Features.Placed.ORE_BLUNITE_LOWER.getKey().orElseThrow());
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, Features.Placed.ORE_CARBONITE_UPPER.getKey().orElseThrow());
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, Features.Placed.ORE_CARBONITE_LOWER.getKey().orElseThrow());
		}
	}
}
