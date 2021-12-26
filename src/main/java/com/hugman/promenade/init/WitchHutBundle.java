package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.ConfiguredStructureFeatureCreator;
import com.hugman.dawn.api.creator.StructureFeatureCreator;
import com.hugman.dawn.api.creator.StructurePieceCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.world.gen.feature.structure.WitchHutFeature;
import com.hugman.promenade.object.world.gen.feature.structure.generator.WitchHutGenerator;
import com.hugman.promenade.util.GenUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;

public class WitchHutBundle extends PromenadeBundle {
	public static final StructurePieceType WITCH_HUT_PIECE = add(new StructurePieceCreator("dfh", WitchHutGenerator.MainPiece::new));
	public static final StructureFeatureCreator<DefaultFeatureConfig, WitchHutFeature> WITCH_HUT = creator(new StructureFeatureCreator<>("witch_hut", new WitchHutFeature(DefaultFeatureConfig.CODEC), GenerationStep.Feature.SURFACE_STRUCTURES, 32, 8, 14353620, false));
	public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> DARK_FOREST_WITCH_HUT = add(new ConfiguredStructureFeatureCreator<>("dark_forest_witch_hut", WITCH_HUT.getStructure().configure(DefaultFeatureConfig.DEFAULT)));

	public static void addToGen() {
		if(Promenade.CONFIG.world_features.witch_huts) {
			BiomeModifications.addStructure(biomeSelectionContext -> biomeSelectionContext.hasBuiltInFeature(VegetationConfiguredFeatures.DARK_FOREST_VEGETATION), GenUtil.getKey(WitchHutBundle.DARK_FOREST_WITCH_HUT));
		}
	}
}