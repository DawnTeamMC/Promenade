package com.hugman.promenade.init.world;

import com.hugman.dawn.api.creator.StructureFeatureCreator;
import com.hugman.promenade.init.PromenadeBundle;
import com.hugman.promenade.object.world.gen.feature.structure.WitchHutFeature;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class PromenadeStructureFeatures extends PromenadeBundle {
	public static final StructureFeatureCreator<DefaultFeatureConfig, WitchHutFeature> WITCH_HUT = creator(new StructureFeatureCreator<>("witch_hut", new WitchHutFeature(DefaultFeatureConfig.CODEC), GenerationStep.Feature.SURFACE_STRUCTURES, 32, 8, 14353620, false));

	public static void init() {
	}
}
