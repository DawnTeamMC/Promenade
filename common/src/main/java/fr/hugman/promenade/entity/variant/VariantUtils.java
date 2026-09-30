package fr.hugman.promenade.entity.variant;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

/**
 * Helpers for mobs whose variant is a data-driven registry entry. Mirrors the class of the same name in newer versions
 * of Minecraft, and saves variants the same way.
 */
public final class VariantUtils {
    public static final String TAG_VARIANT = "variant";

    public static <T> Holder<T> getDefaultOrAny(RegistryAccess registries, ResourceKey<T> defaultKey) {
        Registry<T> registry = registries.registryOrThrow(ResourceKey.<T>createRegistryKey(defaultKey.registry()));
        return registry.getHolder(defaultKey).<Holder<T>>map(holder -> holder)
                .or(() -> registry.getAny().map(holder -> holder))
                .orElseThrow();
    }

    public static <T> void writeVariant(CompoundTag tag, Holder<T> variant) {
        variant.unwrapKey().ifPresent(key -> tag.putString(TAG_VARIANT, key.location().toString()));
    }

    public static <T> Optional<Holder.Reference<T>> readVariant(CompoundTag tag, RegistryAccess registries, ResourceKey<? extends Registry<T>> registryKey) {
        Registry<T> registry = registries.registryOrThrow(registryKey);
        return Optional.ofNullable(ResourceLocation.tryParse(tag.getString(TAG_VARIANT)))
                .flatMap(registry::getHolder);
    }
}
