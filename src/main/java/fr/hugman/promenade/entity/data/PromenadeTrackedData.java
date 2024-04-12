package fr.hugman.promenade.entity.data;

import fr.hugman.promenade.entity.CapybaraEntity;
import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.entity.SunkenVariant;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;

public class PromenadeTrackedData {
    public static final TrackedDataHandler<RegistryEntry<CapybaraVariant>> CAPYBARA_VARIANT = of(PacketCodecs.registryEntry(PromenadeRegistryKeys.CAPYBARA_VARIANT));
    public static final TrackedDataHandler<RegistryEntry<SunkenVariant>> SUNKEN_VARIANT = of(PacketCodecs.registryEntry(PromenadeRegistryKeys.SUNKEN_VARIANT));
    public static final TrackedDataHandler<CapybaraEntity.State> CAPYBARA_STATE = of(CapybaraEntity.State.PACKET_CODEC);

    public static <T> TrackedDataHandler<T> of(PacketCodec<? super RegistryByteBuf, T> codec) {
        var handler = TrackedDataHandler.create(codec);
        TrackedDataHandlerRegistry.register(handler);
        return handler;
    }
}
