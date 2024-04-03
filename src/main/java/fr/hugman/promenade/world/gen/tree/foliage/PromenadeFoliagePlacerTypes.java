package fr.hugman.promenade.world.gen.tree.foliage;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.world.gen.tree.trunk.LeapingTrunkPlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PromenadeFoliagePlacerTypes {
    public static final FoliagePlacerType<PalmFoliagePlacer> PALM = register("palm", PalmFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String path, MapCodec<P> codec) {
        return Registry.register(Registries.FOLIAGE_PLACER_TYPE, Promenade.id(path), new FoliagePlacerType<>(codec));
    }

}
