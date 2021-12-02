package com.hugman.promenade.util;

import com.google.common.collect.ImmutableList;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class GenUtil {
	public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
		return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
	}

	public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModfier) {
		return modifiers(CountPlacementModifier.of(count), heightModfier);
	}

	public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
		return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
	}

	public static List<PlacementModifier> vegetationModifiersWithChance(int chance, @Nullable PlacementModifier modifier) {
		ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
		if (modifier != null) {
			builder.add(modifier);
		}
		if (chance != 0) {
			builder.add(RarityFilterPlacementModifier.of(chance));
		}
		builder.add(SquarePlacementModifier.of());
		builder.add(PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);
		builder.add(BiomePlacementModifier.of());
		return builder.build();
	}

	public static RegistryKey<PlacedFeature> getKey(PlacedFeature f) {
		return BuiltinRegistries.PLACED_FEATURE.getKey(f).orElseThrow();
	}

	public static RegistryKey<ConfiguredStructureFeature<?, ?>> getKey(ConfiguredStructureFeature<?, ?> f) {
		return BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getKey(f).orElseThrow();
	}
}
