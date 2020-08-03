package com.hugman.wild_explorer.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
	// TODO : fix these
	@Inject(method = "addMineables", at = @At(value = "TAIL"), cancellable = true)
	private static void mubble_addMineables(GenerationSettings.Builder builder, CallbackInfo info) {
		//builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MubbleConfiguredFeatures.ORE_BLUNITE);
		//builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MubbleConfiguredFeatures.ORE_CARBONITE);
	}

	@Inject(method = "addDefaultOres", at = @At(value = "TAIL"), cancellable = true)
	private static void mubble_addDefaultOres(GenerationSettings.Builder builder, CallbackInfo info) {
		//builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MubbleConfiguredFeatures.ORE_VANADIUM);
	}

	@Inject(method = "addDesertVegetation", at = @At(value = "TAIL"), cancellable = true)
	private static void mubble_addDesertVegetation(GenerationSettings.Builder builder, CallbackInfo info) {
		//builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MubbleConfiguredFeatures.PALM_TREES);
	}

	@Inject(method = "addFarmAnimals", at = @At(value = "TAIL"), cancellable = true)
	private static void mubble_addFarmAnimals(SpawnSettings.Builder builder, CallbackInfo info) {
		//builder.spawners(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(MubbleEntities.DUCK, 10, 4, 4));
	}

	@Inject(method = "addPlainsMobs", at = @At(value = "TAIL"), cancellable = true)
	private static void mubble_addPlainsMobs(SpawnSettings.Builder builder, CallbackInfo info) {
		//builder.spawners(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(MubbleEntities.TOAD, 10, 4, 4));
	}
}
