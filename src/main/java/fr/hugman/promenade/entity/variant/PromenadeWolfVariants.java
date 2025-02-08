package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeWolfVariants {
    public static final RegistryKey<WolfVariant> SHIBA_INU = of("shiba_inu");

    public static RegistryKey<WolfVariant> of(String path) {
        return RegistryKey.of(RegistryKeys.WOLF_VARIANT, Promenade.id(path));
    }
}
