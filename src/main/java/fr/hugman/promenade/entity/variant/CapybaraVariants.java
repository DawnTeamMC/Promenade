package fr.hugman.promenade.entity.variant;

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
    public static final RegistryKey<CapybaraVariant> DEFAULT = BROWN;


    private static RegistryKey<CapybaraVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static RegistryKey<CapybaraVariant> of(Identifier id) {
        return RegistryKey.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, id);
    }

    public static RegistryEntry<CapybaraVariant> getRandom(DynamicRegistryManager dynamicRegistryManager, Random random) {
        Registry<CapybaraVariant> registry = dynamicRegistryManager.getOrThrow(PromenadeRegistryKeys.CAPYBARA_VARIANT);
        List<RegistryEntry<CapybaraVariant>> entries = registry.streamEntries().collect(Collectors.toList());

        int totalWeight = entries.stream().mapToInt(entry -> entry.value().spawnWeight()).sum();
        int randomWeight = random.nextInt(totalWeight);

        for (RegistryEntry<CapybaraVariant> entry : entries) {
            randomWeight -= entry.value().spawnWeight();
            if (randomWeight <= 0) {
                return entry;
            }
        }

        return registry.getOptional(DEFAULT).or(registry::getDefaultEntry).orElseThrow();
    }
}
