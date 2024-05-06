package fr.hugman.promenade.entity.decoration.painting;

import fr.hugman.promenade.Promenade;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadePaintingVariantKeys {
    public static final RegistryKey<PaintingVariant> OPTIMISM = of("optimism");
    public static final RegistryKey<PaintingVariant> NURTURE = of("nurture");


    private static RegistryKey<PaintingVariant> of(String path) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, Promenade.id(path));
    }
}
