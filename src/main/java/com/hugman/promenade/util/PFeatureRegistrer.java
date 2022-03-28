package com.hugman.promenade.util;

import com.hugman.dawn.api.util.FeatureRegistrer;
import com.hugman.promenade.Promenade;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public final class PFeatureRegistrer {
	public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> config(String id, F feature, FC config) {
		return FeatureRegistrer.config(Promenade.MOD_DATA.id(id), feature, config);
	}

	public static RegistryEntry<PlacedFeature> place(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
		return FeatureRegistrer.place(Promenade.MOD_DATA.id(id), feature, modifiers);
	}

	public static RegistryEntry<PlacedFeature> place(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return place(id, feature, List.of(modifiers));
	}
}
