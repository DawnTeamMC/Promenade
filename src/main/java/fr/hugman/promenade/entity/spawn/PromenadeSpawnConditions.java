package fr.hugman.promenade.entity.spawn;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeSpawnConditions {
    public static void register() {
        of("chance", ChanceSpawnCondition.CODEC);
    }

    public static MapCodec<? extends SpawnCondition> of(String name, MapCodec<? extends SpawnCondition> codec) {
        return Registry.register(Registries.SPAWN_CONDITION_TYPE, Promenade.id(name), codec);
    }
}
