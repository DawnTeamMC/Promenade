package fr.hugman.promenade.registry;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.CapybaraVariant;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class PromenadeRegistryKeys {
    public static final RegistryKey<Registry<CapybaraVariant>> CAPYBARA_VARIANT = RegistryKey.ofRegistry(Promenade.id("capybara_variant"));
}
