package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public record CapybaraVariant(int spawnWeight) {
    public static final RegistryKey<CapybaraVariant> BROWN = of("brown");
    public static final RegistryKey<CapybaraVariant> ALBINO = of("albino");

    public static RegistryKey<CapybaraVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static RegistryKey<CapybaraVariant> of(Identifier id) {
        return RegistryKey.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, id);
    }

    public static CapybaraVariant registerAndGetDefault(Registry<CapybaraVariant> registry) {
        register(registry, ALBINO, new CapybaraVariant(1));
        return register(registry, BROWN, new CapybaraVariant(49));
    }

    public static CapybaraVariant register(Registry<CapybaraVariant> registry, RegistryKey<CapybaraVariant> registryKey, CapybaraVariant variant) {
        return Registry.register(registry, registryKey, variant);
    }

    public static RegistryEntry<CapybaraVariant> getRandom(Random random) {
        var entrySet = PromenadeRegistries.CAPYBARA_VARIANT.streamEntries().toList();

        // get the total weight of all variants
        int totalWeight = 0;
        for (var variant : entrySet) {
            totalWeight += variant.value().spawnWeight();
        }
        // choose a random variant
        int randomWeight = random.nextInt(totalWeight);
        for (var variant : entrySet) {
            randomWeight -= variant.value().spawnWeight();
            if (randomWeight <= 0) {
                return variant;
            }
        }
        // should never happen
        return PromenadeRegistries.CAPYBARA_VARIANT.entryOf(getDefault());
    }

    public static RegistryKey<CapybaraVariant> getDefault() {
        return BROWN;
    }

}
