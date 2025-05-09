package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.snowy.SnowyBlockTransformation;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class PromenadeRegistries {
    public static void register() {
        DynamicRegistries.registerSynced(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION, SnowyBlockTransformation.CODEC);

        DynamicRegistries.registerSynced(PromenadeRegistryKeys.DUCK_VARIANT, DuckVariant.CODEC);
        DynamicRegistries.registerSynced(PromenadeRegistryKeys.CAPYBARA_VARIANT, CapybaraVariant.CODEC, CapybaraVariant.NETWORK_CODEC);
        DynamicRegistries.registerSynced(PromenadeRegistryKeys.SUNKEN_VARIANT, SunkenVariant.CODEC);
    }
}
