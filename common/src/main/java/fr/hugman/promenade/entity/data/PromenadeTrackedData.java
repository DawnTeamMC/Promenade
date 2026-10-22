package fr.hugman.promenade.entity.data;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.Capybara;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import fr.hugman.promenade.platform.PromenadePlatform;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;

public class PromenadeTrackedData {
    public static final EntityDataSerializer<ResourceKey<DuckVariant>> DUCK_VARIANT = register("duck_variant", resourceKey(PromenadeRegistryKeys.DUCK_VARIANT));
    public static final EntityDataSerializer<ResourceKey<CapybaraVariant>> CAPYBARA_VARIANT = register("capybara_variant", resourceKey(PromenadeRegistryKeys.CAPYBARA_VARIANT));
    public static final EntityDataSerializer<ResourceKey<SunkenVariant>> SUNKEN_VARIANT = register("sunken_variant", resourceKey(PromenadeRegistryKeys.SUNKEN_VARIANT));

    public static final EntityDataSerializer<Capybara.State> CAPYBARA_STATE = register("capybara_state", EntityDataSerializer.simpleEnum(Capybara.State.class));

    /**
     * 1.20.1 packets have no access to registries: variants are synced by key, and each side looks them up in its own registries.
     */
    private static <T> EntityDataSerializer<ResourceKey<T>> resourceKey(ResourceKey<? extends Registry<T>> registryKey) {
        return EntityDataSerializer.simple(FriendlyByteBuf::writeResourceKey, buf -> buf.readResourceKey(registryKey));
    }

    private static <T> EntityDataSerializer<T> register(String name, EntityDataSerializer<T> serializer) {
        PromenadePlatform.INSTANCE.registerEntityDataSerializer(Promenade.id(name), serializer);
        return serializer;
    }
}
