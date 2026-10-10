package fr.hugman.promenade.world.gen.placement_modifier;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class PromenadePlacementModifierTypes {
    public static final MapCodec<NoiseIntervalCountPlacementModifier> NOISE_INTERVAL_COUNT = register("noise_interval_count", NoiseIntervalCountPlacementModifier.MODIFIER_CODEC);

    private static <P extends PlacementModifier> MapCodec<P> register(String path, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, Promenade.id(path), codec);
    }
}
