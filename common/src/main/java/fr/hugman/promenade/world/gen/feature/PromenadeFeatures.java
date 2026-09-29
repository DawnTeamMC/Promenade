package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;

public class PromenadeFeatures {
    public static final MapCodec<TallHugeFungusFeature> TALL_HUGE_FUNGUS = of("tall_huge_fungus", TallHugeFungusFeature.CODEC);
    public static final MapCodec<CoiledVinesFeature> COILED_VINES = of("coiled_vines", CoiledVinesFeature.CODEC);
    public static final MapCodec<NoisePickedFeature> NOISE_PICKED = of("noised_picked", NoisePickedFeature.CODEC);
    public static final MapCodec<BoulderFeature> BOULDER = of("boulder", BoulderFeature.CODEC);
    public static final MapCodec<FreezeTopLayerFeature> FREEZE_TOP_LAYER = of("freeze_top_layer", FreezeTopLayerFeature.CODEC);

    private static <F extends Feature> MapCodec<F> of(String path, MapCodec<F> codec) {
        return Registry.register(BuiltInRegistries.FEATURE_TYPE, Promenade.id(path), codec);
    }

}
