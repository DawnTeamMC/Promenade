package fr.hugman.promenade.entity.ai.brain;

import com.mojang.serialization.Codec;
import fr.hugman.promenade.Promenade;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

import java.util.Optional;

public class PromenadeMemoryModuleTypes {
    public static final MemoryModuleType<Unit> FART_COOLDOWN = register("fart_cooldown", Unit.CODEC);

    private static <U> MemoryModuleType<U> register(String id, Codec<U> codec) {
        return Registry.register(Registries.MEMORY_MODULE_TYPE, Promenade.id(id), new MemoryModuleType<>(Optional.of(codec)));
    }

    private static <U> MemoryModuleType<U> register(String id) {
        return Registry.register(Registries.MEMORY_MODULE_TYPE, Promenade.id(id), new MemoryModuleType<>(Optional.empty()));
    }


}
