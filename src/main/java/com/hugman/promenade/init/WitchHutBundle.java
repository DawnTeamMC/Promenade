package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.StructureFeatureCreator;
import com.hugman.dawn.api.creator.StructurePieceCreator;
import com.hugman.promenade.object.world.gen.feature.structure.WitchHutFeature;
import com.hugman.promenade.object.world.gen.feature.structure.generator.WitchHutGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class WitchHutBundle extends PromenadeBundle {
	public static final StructurePieceType WITCH_HUT_PIECE = add(new StructurePieceCreator("dfh", WitchHutGenerator.MainPiece::new));
	public static final StructureFeature<DefaultFeatureConfig> WITCH_HUT = add(new StructureFeatureCreator<>("witch_hut", new WitchHutFeature(DefaultFeatureConfig.CODEC), GenerationStep.Feature.SURFACE_STRUCTURES));
}