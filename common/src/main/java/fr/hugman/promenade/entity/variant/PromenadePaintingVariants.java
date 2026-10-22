package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.platform.PromenadePlatform;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

/**
 * Painting variants are not data-driven in 1.20.1: they are registered with the rest of the content.
 * Their title and author are translated as {@code painting.<namespace>.<path>.title} and {@code .author}.
 */
public class PromenadePaintingVariants {
    public static final ResourceKey<PaintingVariant> OPTIMISM = register("optimism", 2, 2);
    public static final ResourceKey<PaintingVariant> NURTURE = register("nurture", 2, 2);

    /**
     * @param width  the width of the painting, in blocks
     * @param height the height of the painting, in blocks
     */
    private static ResourceKey<PaintingVariant> register(String path, int width, int height) {
        var key = ResourceKey.create(Registries.PAINTING_VARIANT, Promenade.id(path));
        PromenadePlatform.INSTANCE.register(BuiltInRegistries.PAINTING_VARIANT, key, new PaintingVariant(width * 16, height * 16));
        return key;
    }
}
