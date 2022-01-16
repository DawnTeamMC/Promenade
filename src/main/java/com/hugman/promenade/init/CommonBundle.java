package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.FeatureCreator;
import com.hugman.promenade.creator.TrunkPlacerTypeCreator;
import com.hugman.promenade.object.world.gen.feature.BoulderFeature;
import com.hugman.promenade.object.world.gen.feature.HugeMushroomFeature;
import com.hugman.promenade.object.world.gen.feature.SpikeFeature;
import com.hugman.promenade.object.world.gen.feature.TallHugeFungusFeature;
import com.hugman.promenade.object.world.gen.feature.config.BoulderFeatureConfig;
import com.hugman.promenade.object.world.gen.feature.config.HugeMushroomFeatureConfig;
import com.hugman.promenade.object.world.gen.feature.config.SpikeFeatureConfig;
import com.hugman.promenade.object.world.gen.tree.LeapingTrunkPlacer;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class CommonBundle extends PromenadeBundle {
	public static final TrunkPlacerType<LeapingTrunkPlacer> LEAPING_TRUNK_PLACER = add(new TrunkPlacerTypeCreator<>("leaping_trunk_placer", LeapingTrunkPlacer.CODEC));
	public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGUS = add(new FeatureCreator<>("tall_huge_fungus", new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC)));
	public static final Feature<HugeMushroomFeatureConfig> HUGE_MUSHROOM = add(new FeatureCreator<>("huge_mushroom", new HugeMushroomFeature(HugeMushroomFeatureConfig.CODEC)));
	public static final Feature<SpikeFeatureConfig> SPIKE = add(new FeatureCreator<>("spike", new SpikeFeature(SpikeFeatureConfig.CODEC)));
	public static final Feature<BoulderFeatureConfig> BOULDER = add(new FeatureCreator<>("boulder", new BoulderFeature(BoulderFeatureConfig.CODEC)));
}
