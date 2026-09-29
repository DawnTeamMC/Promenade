package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.snowy.SnowyBlockTransformation;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import fr.hugman.promenade.platform.DynamicRegistrar;

public class PromenadeRegistries {
    public static void register(DynamicRegistrar registrar) {
        registrar.register(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION, SnowyBlockTransformation.CODEC, SnowyBlockTransformation.CODEC);

        registrar.register(PromenadeRegistryKeys.DUCK_VARIANT, DuckVariant.CODEC, DuckVariant.NETWORK_CODEC);
        registrar.register(PromenadeRegistryKeys.CAPYBARA_VARIANT, CapybaraVariant.CODEC, CapybaraVariant.NETWORK_CODEC);
        registrar.register(PromenadeRegistryKeys.SUNKEN_VARIANT, SunkenVariant.CODEC, SunkenVariant.NETWORK_CODEC);
    }
}
