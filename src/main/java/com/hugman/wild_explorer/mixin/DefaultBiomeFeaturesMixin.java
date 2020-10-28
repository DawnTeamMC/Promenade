package com.hugman.wild_explorer.mixin;

import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.config.WEConfig;
import com.hugman.wild_explorer.init.WEEntities;
import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
	private static final WEConfig.FeaturesCategory FEATURES_CONFIG = WildExplorer.CONFIG.features;
	private static final WEConfig.CreaturesCategory CREATURES_CONFIG = WildExplorer.CONFIG.creatures;

	@Inject(method = "addMineables", at = @At(value = "TAIL"))
	private static void WE_addMineables(GenerationSettings.Builder builder, CallbackInfo info) {
		if(FEATURES_CONFIG.blunite_patch) builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WEConfiguredFeatures.ORE_BLUNITE);
		if(FEATURES_CONFIG.carbonite_patch) builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WEConfiguredFeatures.ORE_CARBONITE);
	}

	@Inject(method = "addDesertVegetation", at = @At(value = "TAIL"))
	private static void WE_addPalmTrees(GenerationSettings.Builder builder, CallbackInfo info) {
		if(FEATURES_CONFIG.palm_tree) builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PALM_TREES);
	}

	@Inject(method = "addSweetBerryBushes", at = @At(value = "TAIL"))
	private static void WE_addBlueberryBushes(GenerationSettings.Builder builder, CallbackInfo info) {
		if(FEATURES_CONFIG.blueberry_bush) builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_BLUEBERRY_BUSH_SPARSE);
	}

	@Inject(method = "addSweetBerryBushesSnowy", at = @At(value = "TAIL"))
	private static void WE_addBlueberryBushesSnowy(GenerationSettings.Builder builder, CallbackInfo info) {
		if(FEATURES_CONFIG.blueberry_bush) builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PATCH_BLUEBERRY_BUSH_DECORATED);
	}

	@Inject(method = "addFarmAnimals", at = @At(value = "TAIL"))
	private static void WE_addDuck(SpawnSettings.Builder builder, CallbackInfo info) {
		if(CREATURES_CONFIG.duck) builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(WEEntities.DUCK, 10, 4, 4));
	}
}
