package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

public class PromenadeFeatures {
    public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGUS = of("tall_huge_fungus", new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC));
    public static final Feature<CoiledVinesFeatureConfig> COILED_VINES = of("coiled_vines", new CoiledVinesFeature(CoiledVinesFeatureConfig.CODEC));
    public static final Feature<BoulderFeatureConfig> BOULDER = of("boulder", new BoulderFeature(BoulderFeatureConfig.CODEC));
    public static final FreezeTopLayerFeature FREEZE_TOP_LAYER = of("freeze_top_layer", new FreezeTopLayerFeature(DefaultFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F of(String path, F feature) {
        return Registry.register(Registries.FEATURE, Promenade.id(path), feature);
    }

}
