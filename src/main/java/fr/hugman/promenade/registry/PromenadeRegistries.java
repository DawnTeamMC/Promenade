package fr.hugman.promenade.registry;

import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.entity.DuckVariant;
import fr.hugman.promenade.entity.SunkenVariant;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class PromenadeRegistries {
    public static void register() {
        DynamicRegistries.registerSynced(PromenadeRegistryKeys.DUCK_VARIANT, DuckVariant.CODEC);
        DynamicRegistries.registerSynced(PromenadeRegistryKeys.CAPYBARA_VARIANT, CapybaraVariant.CODEC);
        DynamicRegistries.registerSynced(PromenadeRegistryKeys.SUNKEN_VARIANT, SunkenVariant.CODEC);
    }
}
