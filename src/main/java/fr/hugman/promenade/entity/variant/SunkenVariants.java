package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public class SunkenVariants {
    public static final RegistryKey<SunkenVariant> BUBBLE = of("bubble");
    public static final RegistryKey<SunkenVariant> FIRE = of("fire");
    public static final RegistryKey<SunkenVariant> HORN = of("horn");
    //TODO: add other coral variants

    public static final RegistryKey<SunkenVariant> DEFAULT = BUBBLE;

    private static RegistryKey<SunkenVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static RegistryKey<SunkenVariant> of(Identifier id) {
        return RegistryKey.of(PromenadeRegistryKeys.SUNKEN_VARIANT, id);
    }

    public static RegistryEntry<SunkenVariant> getRandom(DynamicRegistryManager dynamicRegistryManager, Random random) {
        Registry<SunkenVariant> registry = dynamicRegistryManager.getOrThrow(PromenadeRegistryKeys.SUNKEN_VARIANT);

        return registry.getRandom(random)
                .or(() -> registry.getOptional(DEFAULT))
                .or(registry::getDefaultEntry)
                .orElseThrow();
    }
}
