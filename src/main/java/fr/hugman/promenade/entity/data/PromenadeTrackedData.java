package fr.hugman.promenade.entity.data;

import fr.hugman.promenade.entity.CapybaraEntity;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.entry.RegistryEntry;

public class PromenadeTrackedData {
    public static final TrackedDataHandler<RegistryEntry<DuckVariant>> DUCK_VARIANT = of(DuckVariant.ENTRY_PACKET_CODEC);
    public static final TrackedDataHandler<RegistryEntry<CapybaraVariant>> CAPYBARA_VARIANT = of(CapybaraVariant.ENTRY_PACKET_CODEC);
    public static final TrackedDataHandler<RegistryEntry<SunkenVariant>> SUNKEN_VARIANT = of(SunkenVariant.ENTRY_PACKET_CODEC);

    public static final TrackedDataHandler<CapybaraEntity.State> CAPYBARA_STATE = of(CapybaraEntity.State.PACKET_CODEC);

    public static <T> TrackedDataHandler<T> of(PacketCodec<? super RegistryByteBuf, T> codec) {
        var handler = TrackedDataHandler.create(codec);
        TrackedDataHandlerRegistry.register(handler);
        return handler;
    }
}
