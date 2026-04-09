package fr.hugman.promenade.registry;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.snowy.SnowyBlockTransformation;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class PromenadeRegistryKeys {
    public static final ResourceKey<Registry<SnowyBlockTransformation>> SNOWY_BLOCK_TRANSFORMATION = ResourceKey.createRegistryKey(Promenade.id("snowy_block_transformation"));

    public static final ResourceKey<Registry<DuckVariant>> DUCK_VARIANT = ResourceKey.createRegistryKey(Promenade.id("duck_variant"));
    public static final ResourceKey<Registry<CapybaraVariant>> CAPYBARA_VARIANT = ResourceKey.createRegistryKey(Promenade.id("capybara_variant"));
    public static final ResourceKey<Registry<SunkenVariant>> SUNKEN_VARIANT = ResourceKey.createRegistryKey(Promenade.id("sunken_variant"));
}
