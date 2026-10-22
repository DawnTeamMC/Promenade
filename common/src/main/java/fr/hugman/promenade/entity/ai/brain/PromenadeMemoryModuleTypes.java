package fr.hugman.promenade.entity.ai.brain;

import fr.hugman.promenade.platform.PromenadePlatform;
import com.mojang.serialization.Codec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import java.util.Optional;

public class PromenadeMemoryModuleTypes {
    public static final MemoryModuleType<Unit> FART_COOLDOWN = register("fart_cooldown", Codec.unit(Unit.INSTANCE));

    private static <U> MemoryModuleType<U> register(String id, Codec<U> codec) {
        return PromenadePlatform.INSTANCE.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Promenade.id(id), new MemoryModuleType<>(Optional.of(codec)));
    }

    private static <U> MemoryModuleType<U> register(String id) {
        return PromenadePlatform.INSTANCE.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Promenade.id(id), new MemoryModuleType<>(Optional.empty()));
    }


}
