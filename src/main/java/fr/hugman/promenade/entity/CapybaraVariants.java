package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.List;
import java.util.stream.Collectors;

public class CapybaraVariants {
    public static final RegistryKey<CapybaraVariant> BROWN = of("brown");

    private static RegistryKey<CapybaraVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static RegistryKey<CapybaraVariant> of(Identifier id) {
        return RegistryKey.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, id);
    }

    public static RegistryEntry<CapybaraVariant> getRandom(DynamicRegistryManager dynamicRegistryManager, Random random) {
        Registry<CapybaraVariant> registry = dynamicRegistryManager.get(PromenadeRegistryKeys.CAPYBARA_VARIANT);
        List<RegistryEntry<CapybaraVariant>> entries = registry.streamEntries().collect(Collectors.toList());

        int totalWeight = entries.stream().mapToInt(entry -> entry.value().spawnWeight()).sum();
        int randomWeight = random.nextInt(totalWeight);

        for (RegistryEntry<CapybaraVariant> entry : entries) {
            randomWeight -= entry.value().spawnWeight();
            if (randomWeight <= 0) {
                return entry;
            }
        }

        // Fallback to the first entry if something goes wrong
        // TODO: if no entries are present, throw an exception
        return entries.get(0);
    }
}
