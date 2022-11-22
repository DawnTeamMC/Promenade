package fr.hugman.promenade.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.gen.feature.BoulderFeature;
import fr.hugman.promenade.gen.feature.BoulderFeatureConfig;
import fr.hugman.promenade.gen.feature.TallHugeFungusFeature;
import fr.hugman.promenade.gen.placement_modifier.NoiseIntervalCountPlacementModifier;
import fr.hugman.promenade.gen.tree.foliage.PalmFoliagePlacer;
import fr.hugman.promenade.gen.tree.trunk.LeapingTrunkPlacer;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class CommonContent {
	public static final TrunkPlacerType<LeapingTrunkPlacer> LEAPING_TRUNK_PLACER = DawnFactory.trunkPlacer(LeapingTrunkPlacer.CODEC);
	public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = DawnFactory.foliagePlacer(PalmFoliagePlacer.CODEC);
	public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGUS = new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC);
	public static final Feature<BoulderFeatureConfig> BOULDER = new BoulderFeature(BoulderFeatureConfig.CODEC);

	public static final PlacementModifierType<NoiseIntervalCountPlacementModifier> NOISE_INTERVAL_COUNT = () -> NoiseIntervalCountPlacementModifier.MODIFIER_CODEC;

	public static void init() {
		Registrar.add(Promenade.id("leaping"), LEAPING_TRUNK_PLACER);
		Registrar.add(Promenade.id("palm"), PALM_FOLIAGE_PLACER);
		Registrar.add(Promenade.id("tall_huge_fungus"), TALL_HUGE_FUNGUS);
		Registrar.add(Promenade.id("boulder"), BOULDER);

		Registrar.add(Promenade.id("noise_interval_count"), NOISE_INTERVAL_COUNT);
	}
}
