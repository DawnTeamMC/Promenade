package fr.hugman.promenade.entity.decoration.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadePaintingVariants {
    public static void register() {
        Registry.register(Registries.PAINTING_VARIANT, PromenadePaintingVariantKeys.RADICAL, new PaintingVariant(32, 32));
        Registry.register(Registries.PAINTING_VARIANT, PromenadePaintingVariantKeys.NURTURE, new PaintingVariant(32, 48));
    }
}
