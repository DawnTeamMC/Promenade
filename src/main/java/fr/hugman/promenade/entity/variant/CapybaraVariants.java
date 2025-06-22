package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class CapybaraVariants {
    public static final RegistryKey<CapybaraVariant> BROWN = of("brown");
    public static final RegistryKey<CapybaraVariant> ALBINO = of("albino");

    public static final RegistryKey<CapybaraVariant> DEFAULT = BROWN;

    public static RegistryKey<CapybaraVariant> of(String path) {
        return RegistryKey.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, Promenade.id(path));
    }

    public static Optional<RegistryEntry.Reference<CapybaraVariant>> select(Random random, DynamicRegistryManager registries, SpawnContext context) {
        return VariantSelectorProvider.select(registries.getOrThrow(PromenadeRegistryKeys.CAPYBARA_VARIANT).streamEntries(), RegistryEntry::value, random, context);
    }
}
