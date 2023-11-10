package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.gen.feature.BoulderFeature;
import fr.hugman.promenade.gen.feature.BoulderFeatureConfig;
import fr.hugman.promenade.gen.feature.FreezeTopLayerFeature;
import fr.hugman.promenade.gen.feature.TallHugeFungusFeature;
import fr.hugman.promenade.gen.placement_modifier.NoiseIntervalCountPlacementModifier;
import fr.hugman.promenade.gen.tree.foliage.OvalFoliagePlacer;
import fr.hugman.promenade.gen.tree.foliage.PalmFoliagePlacer;
import fr.hugman.promenade.gen.tree.trunk.LeapingTrunkPlacer;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class CommonContent {
	public static final TrunkPlacerType<LeapingTrunkPlacer> LEAPING_TRUNK_PLACER = DawnFactory.trunkPlacer(LeapingTrunkPlacer.CODEC);
	public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = DawnFactory.foliagePlacer(PalmFoliagePlacer.CODEC);
	public static final FoliagePlacerType<OvalFoliagePlacer> OVAL_FOLIAGE_PLACER = DawnFactory.foliagePlacer(OvalFoliagePlacer.CODEC);

	public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGUS = new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC);
	public static final Feature<BoulderFeatureConfig> BOULDER = new BoulderFeature(BoulderFeatureConfig.CODEC);

	public static final PlacementModifierType<NoiseIntervalCountPlacementModifier> NOISE_INTERVAL_COUNT = () -> NoiseIntervalCountPlacementModifier.MODIFIER_CODEC;

	public static void register(Registrar r) {
		r.add("leaping", LEAPING_TRUNK_PLACER);
		r.add("palm", PALM_FOLIAGE_PLACER);
		r.add("tall_huge_fungus", TALL_HUGE_FUNGUS);
		r.add("boulder", BOULDER);
		r.add("oval", OVAL_FOLIAGE_PLACER);

		r.add("noise_interval_count", NOISE_INTERVAL_COUNT);
	}
}
