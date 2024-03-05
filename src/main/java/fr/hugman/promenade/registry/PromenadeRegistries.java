package fr.hugman.promenade.registry;

import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.entity.CapybaraVariants;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeRegistries {
    public static final Registry<CapybaraVariant> CAPYBARA_VARIANT = Registries.create(PromenadeRegistryKeys.CAPYBARA_VARIANT, registry -> CapybaraVariants.getDefault());
}
