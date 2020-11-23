package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.StructureFeatureCreator;
import com.hugman.wild_explorer.init.WEPack;
import com.hugman.wild_explorer.object.world.gen.feature.structure.ElderVillageFeature;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class WEStructureFeatures extends WEPack {
	public static final StructureFeature<DefaultFeatureConfig> ELDER_VILLAGE = register(new StructureFeatureCreator.Builder<>("elder_village", new ElderVillageFeature(DefaultFeatureConfig.CODEC), GenerationStep.Feature.SURFACE_STRUCTURES, 15, 8, 14357921).adjustsSurface());

	public static void init() {
	}
}
