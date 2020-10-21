package com.hugman.wild_explorer.mixin;

import com.hugman.wild_explorer.WildExplorer;
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
	@Inject(method = "addDesertVegetation", at = @At(value = "TAIL"), cancellable = true)
	private static void WE_addDesertVegetation(GenerationSettings.Builder builder, CallbackInfo info) {
		//builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WEConfiguredFeatures.PALM_TREES);
	}

	@Inject(method = "addFarmAnimals", at = @At(value = "TAIL"), cancellable = true)
	private static void WE_addFarmAnimals(SpawnSettings.Builder builder, CallbackInfo info) {
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(WEEntities.DUCK, 10, 4, 4));
	}
}
