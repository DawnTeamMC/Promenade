package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadePaintingVariants {
    public static final RegistryKey<PaintingVariant> OPTIMISM = of("optimism");
    public static final RegistryKey<PaintingVariant> NURTURE = of("nurture");

    public static RegistryKey<PaintingVariant> of(String path) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, Promenade.id(path));
    }
}
