package fr.hugman.promenade.world.gen.tree.trunk;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class PromenadeTrunkPlacerTypes {
    public static final TrunkPlacerType<LeapingTrunkPlacer> LEAPING = register("leaping", LeapingTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String path, MapCodec<P> codec) {
        return Registry.register(Registries.TRUNK_PLACER_TYPE, Promenade.id(path), new TrunkPlacerType<>(codec));
    }

}
