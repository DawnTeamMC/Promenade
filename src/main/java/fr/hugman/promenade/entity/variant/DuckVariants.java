package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class DuckVariants {
    public static final RegistryKey<DuckVariant> PEKIN = of("pekin");
    public static final RegistryKey<DuckVariant> MALLARD = of("mallard");

    public static final RegistryKey<DuckVariant> DEFAULT = PEKIN;

    private static RegistryKey<DuckVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static RegistryKey<DuckVariant> of(Identifier id) {
        return RegistryKey.of(PromenadeRegistryKeys.DUCK_VARIANT, id);
    }

    public static Optional<RegistryEntry.Reference<DuckVariant>> select(Random random, DynamicRegistryManager registries, SpawnContext context) {
        return VariantSelectorProvider.select(registries.getOrThrow(PromenadeRegistryKeys.DUCK_VARIANT).streamEntries(), RegistryEntry::value, random, context);
    }
}
