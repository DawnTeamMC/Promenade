package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class DuckVariants {
    public static final RegistryKey<DuckVariant> PEKIN = of("pekin");

    private static RegistryKey<DuckVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static RegistryKey<DuckVariant> of(Identifier id) {
        return RegistryKey.of(PromenadeRegistryKeys.DUCK_VARIANT, id);
    }

    public static RegistryEntry<DuckVariant> fromBiome(DynamicRegistryManager dynamicRegistryManager, RegistryEntry<Biome> biome) {
        Registry<DuckVariant> registry = dynamicRegistryManager.get(PromenadeRegistryKeys.DUCK_VARIANT);
        return registry.streamEntries()
                .filter(entry -> entry.value().getBiomes().contains(biome))
                .findFirst()
                .orElse(registry.entryOf(PEKIN));
    }
}
