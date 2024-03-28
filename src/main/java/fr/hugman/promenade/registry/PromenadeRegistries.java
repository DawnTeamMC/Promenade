package fr.hugman.promenade.registry;

import fr.hugman.promenade.entity.CapybaraVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeRegistries {
    public static final Registry<CapybaraVariant> CAPYBARA_VARIANT = Registries.create(PromenadeRegistryKeys.CAPYBARA_VARIANT, CapybaraVariant::registerAndGetDefault);
}
