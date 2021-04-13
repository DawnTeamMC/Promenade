package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.StructureFeatureCreator;
import com.hugman.wild_explorer.init.WEBundle;
import com.hugman.wild_explorer.object.world.gen.feature.structure.WitchHutFeature;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class WEStructureFeatures extends WEBundle {
	public static final StructureFeatureCreator<DefaultFeatureConfig, WitchHutFeature> WITCH_HUT = creator(new StructureFeatureCreator<>("witch_hut", new WitchHutFeature(DefaultFeatureConfig.CODEC), GenerationStep.Feature.SURFACE_STRUCTURES, 32, 8, 14353620, false));

	public static void init() {
	}
}
