package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.CapybaraVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

//TODO: a generic class for other devs
public class PromenadeCapybaraVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeCapybaraVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(PromenadeRegistryKeys.CAPYBARA_VARIANT));
    }

    @Override
    public String getName() {
        return "Capybara Variants";
    }

    public static void register(Registerable<CapybaraVariant> registerable) {
        of(registerable, CapybaraVariants.BROWN, 49);
        of(registerable, CapybaraVariants.ALBINO, 1);
    }

    private static void of(Registerable<CapybaraVariant> registry, RegistryKey<CapybaraVariant> key, int spawnWeight) {
        var baseId = key.getValue().withPrefixedPath("entity/capybara/");
        registry.register(key, new CapybaraVariant(
                baseId.withSuffixedPath("/small_eyes"),
                baseId.withSuffixedPath("/large_eyes"),
                baseId.withSuffixedPath("/closed_eyes"),
                spawnWeight
        ));
    }
}