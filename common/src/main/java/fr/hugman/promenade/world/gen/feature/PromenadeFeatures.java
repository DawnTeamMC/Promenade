package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PromenadeFeatures {
    public static final Feature<HugeFungusConfiguration> TALL_HUGE_FUNGUS = of("tall_huge_fungus", new TallHugeFungusFeature(HugeFungusConfiguration.CODEC));
    public static final Feature<CoiledVinesFeatureConfig> COILED_VINES = of("coiled_vines", new CoiledVinesFeature(CoiledVinesFeatureConfig.CODEC));
    public static final Feature<NoisePickedFeatureConfig> NOISE_PICKED = of("noised_picked", new NoisePickedFeature(NoisePickedFeatureConfig.CODEC));
    public static final Feature<BoulderFeatureConfig> BOULDER = of("boulder", new BoulderFeature(BoulderFeatureConfig.CODEC));
    public static final FreezeTopLayerFeature FREEZE_TOP_LAYER = of("freeze_top_layer", new FreezeTopLayerFeature(NoneFeatureConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F of(String path, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Promenade.id(path), feature);
    }

}
