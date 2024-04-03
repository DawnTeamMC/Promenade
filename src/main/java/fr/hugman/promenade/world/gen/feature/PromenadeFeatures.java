package fr.hugman.promenade.world.gen.feature;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

public class PromenadeFeatures {
    public static final Feature<HugeFungusFeatureConfig> TALL_HUGE_FUNGUS = register("tall_huge_fungus", new TallHugeFungusFeature(HugeFungusFeatureConfig.CODEC));
    public static final Feature<BoulderFeatureConfig> BOULDER = register("boulder", new BoulderFeature(BoulderFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String path, F feature) {
        return Registry.register(Registries.FEATURE, Promenade.id(path), feature);
    }

}
