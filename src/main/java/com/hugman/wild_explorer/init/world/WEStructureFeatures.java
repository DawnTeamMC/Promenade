package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.StructureFeatureCreator;
import com.hugman.wild_explorer.init.WEPack;
import com.hugman.wild_explorer.object.world.gen.feature.structure.WitchHutFeature;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class WEStructureFeatures extends WEPack {
	public static final StructureFeature<DefaultFeatureConfig> WITCH_HUT = register(new StructureFeatureCreator.Builder<>("witch_hut", new WitchHutFeature(DefaultFeatureConfig.CODEC), GenerationStep.Feature.SURFACE_STRUCTURES, 32, 8, 14353620));

	public static void init() {
	}
}
