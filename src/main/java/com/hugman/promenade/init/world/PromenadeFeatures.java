package com.hugman.promenade.init.world;

import com.hugman.dawn.api.creator.FeatureCreator;
import com.hugman.promenade.init.PromenadePack;
import com.hugman.promenade.object.world.gen.feature.BoulderFeature;
import com.hugman.promenade.object.world.gen.feature.HugeMushroomFeature;
import com.hugman.promenade.object.world.gen.feature.SpikeFeature;
import com.hugman.promenade.object.world.gen.feature.TallHugeFungusFeature;
import com.hugman.promenade.object.world.gen.feature.config.BoulderFeatureConfig;
import com.hugman.promenade.object.world.gen.feature.config.HugeMushroomFeatureConfig;
import com.hugman.promenade.object.world.gen.feature.config.SpikeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

public class PromenadeFeatures extends PromenadePack {
	public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGUS = register(new FeatureCreator.Builder<>("tall_huge_fungus", new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC)));
	public static final Feature<HugeMushroomFeatureConfig> HUGE_MUSHROOM = register(new FeatureCreator.Builder<>("huge_mushroom", new HugeMushroomFeature(HugeMushroomFeatureConfig.CODEC)));
	public static final Feature<SpikeFeatureConfig> SPIKE = register(new FeatureCreator.Builder<>("spike", new SpikeFeature(SpikeFeatureConfig.CODEC)));
	public static final Feature<BoulderFeatureConfig> BOULDER = register(new FeatureCreator.Builder<>("boulder", new BoulderFeature(BoulderFeatureConfig.CODEC)));

	public static void init() {
	}
}
