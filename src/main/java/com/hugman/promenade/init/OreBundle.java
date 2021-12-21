package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.BSSBundle;
import com.hugman.dawn.api.creator.bundle.block.BSSWBundle;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.util.GenUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class OreBundle extends PromenadeBundle {
	public static final BSSWBundle BLUNITE = creator(new BSSWBundle(new BlockCreator.Builder().name("blunite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).sounds(BlockSoundGroup.TUFF))));
	public static final BSSWBundle CARBONITE = creator(new BSSWBundle(new BlockCreator.Builder().name("carbonite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.BASALT))));
	public static final BSSBundle POLISHED_BLUNITE = creator(new BSSBundle(new BlockCreator.Builder().name("polished_blunite").settings(FabricBlockSettings.copyOf(BLUNITE.getBlock()))));
	public static final BSSBundle POLISHED_CARBONITE = creator(new BSSBundle(new BlockCreator.Builder().name("polished_carbonite").settings(FabricBlockSettings.copyOf(CARBONITE.getBlock()))));

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<?, ?> BLUNITE_ORE = add(new ConfiguredFeatureCreator<>("ore/blunite", Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, BLUNITE.getBlock().getDefaultState(), 64))));
			public static final ConfiguredFeature<?, ?> CARBONITE_ORE = add(new ConfiguredFeatureCreator<>("ore/carbonite", Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, CARBONITE.getBlock().getDefaultState(), 64))));
		}

		public static class Placed {
			public static final PlacedFeature ORE_BLUNITE_UPPER = add(new PlacedFeatureCreator("ore/blunite/upper", Configured.BLUNITE_ORE.withPlacement(GenUtil.modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))))));
			public static final PlacedFeature ORE_BLUNITE_LOWER = add(new PlacedFeatureCreator("ore/blunite/lower", Configured.BLUNITE_ORE.withPlacement(GenUtil.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))))));

			public static final PlacedFeature ORE_CARBONITE_UPPER = add(new PlacedFeatureCreator("ore/carbonite/upper", Configured.CARBONITE_ORE.withPlacement(GenUtil.modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))))));
			public static final PlacedFeature ORE_CARBONITE_LOWER = add(new PlacedFeatureCreator("ore/carbonite/lower", Configured.CARBONITE_ORE.withPlacement(GenUtil.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))))));
		}
	}

	public static void addToGen() {
		if(Promenade.CONFIG.features.igneous_rock_patches) {
			Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasBuiltInFeature(OreConfiguredFeatures.ORE_ANDESITE) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_DIORITE) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_GRANITE);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, GenUtil.getKey(Features.Placed.ORE_BLUNITE_UPPER));
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, GenUtil.getKey(Features.Placed.ORE_BLUNITE_LOWER));
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, GenUtil.getKey(Features.Placed.ORE_CARBONITE_UPPER));
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, GenUtil.getKey(Features.Placed.ORE_CARBONITE_LOWER));
		}
	}
}
