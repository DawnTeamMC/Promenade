package fr.hugman.promenade.world.gen.tree.trunk;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class PromenadeTrunkPlacerTypes {
    public static final TrunkPlacerType<LeapingTrunkPlacer> LEAPING = register("leaping", LeapingTrunkPlacer.CODEC);
    public static final TrunkPlacerType<BranchingStraightTrunkPlacer> BRANCHING_STRAIGHT = register("branching_straight", BranchingStraightTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String path, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Promenade.id(path), new TrunkPlacerType<>(codec));
    }

}
