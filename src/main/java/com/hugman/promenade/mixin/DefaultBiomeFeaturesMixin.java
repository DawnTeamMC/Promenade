package com.hugman.promenade.mixin;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.PromenadeEntities;
import com.hugman.promenade.init.world.PromenadeConfiguredFeatures;
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
	private static final PromenadeConfig.FeaturesCategory FEATURES_CONFIG = Promenade.CONFIG.features;
	private static final PromenadeConfig.CreaturesCategory CREATURES_CONFIG = Promenade.CONFIG.creatures;

	@Inject(method = "addMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;Z)V", at = @At(value = "TAIL"))
	private static void WE_addMineables(GenerationSettings.Builder builder, boolean bl, CallbackInfo ci) {
		if(FEATURES_CONFIG.igneous_rock_patches) {
			builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, PromenadeConfiguredFeatures.ORE_BLUNITE);
			builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, PromenadeConfiguredFeatures.ORE_CARBONITE);
		}
	}

	@Inject(method = "addDesertVegetation", at = @At(value = "TAIL"))
	private static void WE_addPalmTrees(GenerationSettings.Builder builder, CallbackInfo info) {
		if(FEATURES_CONFIG.palm_trees) builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadeConfiguredFeatures.PALM_TREES);
	}

	@Inject(method = "addSweetBerryBushes", at = @At(value = "TAIL"))
	private static void WE_addBlueberryBushes(GenerationSettings.Builder builder, CallbackInfo info) {
		if(FEATURES_CONFIG.blueberry_bushes) builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_SPARSE);
	}

	@Inject(method = "addSweetBerryBushesSnowy", at = @At(value = "TAIL"))
	private static void WE_addBlueberryBushesSnowy(GenerationSettings.Builder builder, CallbackInfo info) {
		if(FEATURES_CONFIG.blueberry_bushes) builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PromenadeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_DECORATED);
	}

	@Inject(method = "addFarmAnimals", at = @At(value = "TAIL"))
	private static void WE_addDuck(SpawnSettings.Builder builder, CallbackInfo info) {
		if(CREATURES_CONFIG.ducks) builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(PromenadeEntities.DUCK, 10, 4, 4));
	}
}
