package fr.hugman.promenade.world.gen.tree.foliage;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PromenadeFoliagePlacerTypes {
    public static final FoliagePlacerType<PalmFoliagePlacer> PALM = register("palm", PalmFoliagePlacer.CODEC);
    public static final FoliagePlacerType<MapleFoliagePlacer> MAPLE = register("maple", MapleFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String path, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Promenade.id(path), new FoliagePlacerType<>(codec));
    }

}
