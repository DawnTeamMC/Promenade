package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.FeatureCreator;
import com.hugman.dawn.api.creator.FoliagePlacerTypeCreator;
import com.hugman.dawn.api.creator.TrunkPlacerTypeCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.world.gen.feature.*;
import com.hugman.promenade.object.world.gen.placement_modifier.NoiseIntervalCountPlacementModifier;
import com.hugman.promenade.object.world.gen.tree.foliage.PalmFoliagePlacer;
import com.hugman.promenade.object.world.gen.tree.trunk.LeapingTrunkPlacer;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class CommonBundle extends PromenadeBundle {
	public static final TrunkPlacerType<LeapingTrunkPlacer> LEAPING_TRUNK_PLACER = add(new TrunkPlacerTypeCreator<>("leaping", LeapingTrunkPlacer.CODEC));
	public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = add(new FoliagePlacerTypeCreator<>("palm", PalmFoliagePlacer.CODEC));
	public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGUS = add(new FeatureCreator<>("tall_huge_fungus", new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC)));
	public static final Feature<BoulderFeatureConfig> BOULDER = add(new FeatureCreator<>("boulder", new BoulderFeature(BoulderFeatureConfig.CODEC)));

	public static final PlacementModifierType<NoiseIntervalCountPlacementModifier> NOISE_INTERVAL_COUNT = register("noise_interval_count", NoiseIntervalCountPlacementModifier.MODIFIER_CODEC);

	private static <P extends PlacementModifier> PlacementModifierType<P> register(String id, Codec<P> codec) {
		return Registry.register(Registry.PLACEMENT_MODIFIER_TYPE, Promenade.MOD_DATA.id(id), () -> codec);
	}
}
