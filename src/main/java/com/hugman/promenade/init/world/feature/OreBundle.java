package com.hugman.promenade.init.world.feature;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.MTBlockBundle;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.PromenadeBundle;
import com.hugman.promenade.util.GenUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class OreBundle extends PromenadeBundle {
	public static final MTBlockBundle BLUNITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("blunite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).sounds(BlockSoundGroup.TUFF)), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB, DefaultBlockTemplates.WALL));
	public static final MTBlockBundle CARBONITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("carbonite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.BASALT)), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB, DefaultBlockTemplates.WALL));
	public static final MTBlockBundle POLISHED_BLUNITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("polished_blunite").settings(FabricBlockSettings.copyOf(BLUNITE.getBlock(DefaultBlockTemplates.CUBE)).sounds(BlockSoundGroup.TUFF)), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB));
	public static final MTBlockBundle POLISHED_CARBONITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("polished_carbonite").settings(FabricBlockSettings.copyOf(CARBONITE.getBlock(DefaultBlockTemplates.CUBE))), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB));

	public class Features {
		public class Configured {
			public static final ConfiguredFeature<?, ?> BLUNITE_ORE = add(new ConfiguredFeatureCreator<>("ore/blunite", Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, BLUNITE.getBlock(DefaultBlockTemplates.CUBE).getDefaultState(), 64))));
			public static final ConfiguredFeature<?, ?> CARBONITE_ORE = add(new ConfiguredFeatureCreator<>("ore/_carbonite", Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, CARBONITE.getBlock(DefaultBlockTemplates.CUBE).getDefaultState(), 64))));
		}

		public class Placed {
			public static final PlacedFeature ORE_BLUNITE_UPPER =add(new PlacedFeatureCreator("ore/blunite/upper", Configured.BLUNITE_ORE.withPlacement(GenUtil.modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))))));
			public static final PlacedFeature ORE_BLUNITE_LOWER =add(new PlacedFeatureCreator("ore/blunite/lower", Configured.BLUNITE_ORE.withPlacement(GenUtil.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))))));

			public static final PlacedFeature ORE_CARBONITE_UPPER =add(new PlacedFeatureCreator("ore/carbonite/upper", Configured.CARBONITE_ORE.withPlacement(GenUtil.modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))))));
			public static final PlacedFeature ORE_CARBONITE_LOWER =add(new PlacedFeatureCreator("ore/carbonite/lower", Configured.CARBONITE_ORE.withPlacement(GenUtil.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))))));
		}
	}

	public static void addToGen() {
		if(Promenade.CONFIG.features.igneous_rock_patches) {
			Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasBuiltInFeature(OreConfiguredFeatures.ORE_ANDESITE) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_DIORITE) && c.hasBuiltInFeature(OreConfiguredFeatures.ORE_GRANITE);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, key(Features.Placed.ORE_BLUNITE_UPPER));
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, key(Features.Placed.ORE_BLUNITE_LOWER));
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, key(Features.Placed.ORE_CARBONITE_UPPER));
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, key(Features.Placed.ORE_CARBONITE_LOWER));
		}
	}

	public static RegistryKey<PlacedFeature> key(PlacedFeature placedFeature) {
		return BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature).orElseThrow();
	}
}
