package fr.hugman.promenade.platform;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

/**
 * Registers a data-driven registry whose entries are synced to clients.
 */
public interface DynamicRegistrar {
    <T> void register(ResourceKey<Registry<T>> key, Codec<T> codec, Codec<T> networkCodec);
}
