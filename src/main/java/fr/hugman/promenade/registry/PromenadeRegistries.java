package fr.hugman.promenade.registry;

import fr.hugman.promenade.entity.CapybaraVariant;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class PromenadeRegistries {
    public static void register() {
        DynamicRegistries.registerSynced(PromenadeRegistryKeys.CAPYBARA_VARIANT, CapybaraVariant.CODEC);
    }
}
