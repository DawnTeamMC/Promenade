package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class SunkenVariants {
    public static final RegistryKey<SunkenVariant> BUBBLE = of("bubble");

    private static RegistryKey<SunkenVariant> of(String path) {
        return of(Promenade.id(path));
    }

    public static RegistryKey<SunkenVariant> of(Identifier id) {
        return RegistryKey.of(PromenadeRegistryKeys.SUNKEN_VARIANT, id);
    }
}
