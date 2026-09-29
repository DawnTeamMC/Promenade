package fr.hugman.promenade.entity.spawn;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.variant.SpawnCondition;

public class PromenadeSpawnConditions {
    public static void register() {
        of("chance", ChanceSpawnCondition.CODEC);
    }

    public static MapCodec<? extends SpawnCondition> of(String name, MapCodec<? extends SpawnCondition> codec) {
        return Registry.register(BuiltInRegistries.SPAWN_CONDITION_TYPE, Promenade.id(name), codec);
    }
}
