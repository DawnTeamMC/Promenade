package fr.hugman.promenade.registry;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.snowy.SnowyBlockTransformation;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class PromenadeRegistryKeys {
    public static final RegistryKey<Registry<SnowyBlockTransformation>> SNOWY_BLOCK_TRANSFORMATION = RegistryKey.ofRegistry(Promenade.id("snowy_block_transformation"));

    public static final RegistryKey<Registry<DuckVariant>> DUCK_VARIANT = RegistryKey.ofRegistry(Promenade.id("duck_variant"));
    public static final RegistryKey<Registry<CapybaraVariant>> CAPYBARA_VARIANT = RegistryKey.ofRegistry(Promenade.id("capybara_variant"));
    public static final RegistryKey<Registry<SunkenVariant>> SUNKEN_VARIANT = RegistryKey.ofRegistry(Promenade.id("sunken_variant"));
}
