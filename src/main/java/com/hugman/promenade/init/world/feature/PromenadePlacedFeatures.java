package com.hugman.promenade.init.world.feature;

import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.promenade.init.PromenadeBundle;
import com.hugman.promenade.util.GenUtil;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public class PromenadePlacedFeatures extends PromenadeBundle {
	public static final PlacedFeature ORE_BLUNITE_UPPER =add(new PlacedFeatureCreator("ore_blunite_upper", OreConfiguredFeatures.ORE_DIORITE.withPlacement(GenUtil.modifiersWithRarity(6, HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128))))));
	public static final PlacedFeature ORE_BLUNITE_LOWER =add(new PlacedFeatureCreator("ore_blunite_lower", OreConfiguredFeatures.ORE_DIORITE.withPlacement(GenUtil.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))))));
}
