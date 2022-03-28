package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.ConfiguredStructureFeatureCreator;
import com.hugman.dawn.api.creator.StructureFeatureCreator;
import com.hugman.dawn.api.creator.StructurePieceCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.object.world.gen.feature.structure.WitchHutFeature;
import com.hugman.promenade.object.world.gen.feature.structure.generator.WitchHutGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class WitchHutBundle extends PromenadeBundle {
	public static final StructurePieceType WITCH_HUT_PIECE = add(new StructurePieceCreator("dfh", WitchHutGenerator.MainPiece::new));
	public static final StructureFeature<DefaultFeatureConfig> WITCH_HUT = add(new StructureFeatureCreator<>("witch_hut", new WitchHutFeature(DefaultFeatureConfig.CODEC), GenerationStep.Feature.SURFACE_STRUCTURES));
	public static final ConfiguredStructureFeatureCreator<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> DARK_FOREST_WITCH_HUT = creator(new ConfiguredStructureFeatureCreator<>("dark_forest_witch_hut", WITCH_HUT.configure(DefaultFeatureConfig.INSTANCE, PromenadeTags.Biomes.HAS_WITCH_HUT)));

	public static void addToGen() {
		if(Promenade.CONFIG.world_features.witch_huts) {
			//TODO: fix this, see structure sets (https://gist.github.com/misode/45559d34627755ecaa52497daea83544)
			// BiomeModifications.addFeature(biomeSelectionContext -> biomeSelectionContext.hasBuiltInFeature(VegetationConfiguredFeatures.DARK_FOREST_VEGETATION.value()), WitchHutBundle.DARK_FOREST_WITCH_HUT.get().getKey().orElseThrow());

		}
	}
}