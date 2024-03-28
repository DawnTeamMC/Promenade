package fr.hugman.promenade.entity.data;

import fr.hugman.promenade.entity.CapybaraEntity;
import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;

public class PromenadeTrackedData {
    public static final TrackedDataHandler<RegistryEntry<CapybaraVariant>> CAPYBARA_VARIANT = TrackedDataHandler.create(PacketCodecs.registryEntry(PromenadeRegistryKeys.CAPYBARA_VARIANT));
    public static final TrackedDataHandler<CapybaraEntity.State> CAPYBARA_STATE = TrackedDataHandler.create(CapybaraEntity.State.PACKET_CODEC);

    public static void init() {
        TrackedDataHandlerRegistry.register(CAPYBARA_VARIANT);
        TrackedDataHandlerRegistry.register(CAPYBARA_STATE);
    }
}
