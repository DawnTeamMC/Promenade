package com.hugman.wild_explorer.init.world;

import com.hugman.dawn.api.creator.FeatureCreator;
import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.init.WEPack;
import com.hugman.wild_explorer.object.world.gen.feature.EndBoulderFeature;
import com.hugman.wild_explorer.object.world.gen.feature.HugeNetherMushroomFeature;
import com.hugman.wild_explorer.object.world.gen.feature.HugeNetherMushroomFeatureConfig;
import com.hugman.wild_explorer.object.world.gen.feature.TallHugeFungusFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

public class WEFeatures extends WEPack {
	public static void init() {
	}

	public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGI = register(new FeatureCreator.Builder<>("tall_huge_fungus", new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC)));
	public static final Feature<HugeNetherMushroomFeatureConfig> HUGE_NETHER_MUSHROOM = register(new FeatureCreator.Builder<>("huge_nether_mushroom", new HugeNetherMushroomFeature(HugeNetherMushroomFeatureConfig.CODEC)));
	public static final Feature<SingleStateFeatureConfig> ENDER_BOULDER = register(new FeatureCreator.Builder<>("ender_boulder", new EndBoulderFeature(SingleStateFeatureConfig.CODEC)));
}
