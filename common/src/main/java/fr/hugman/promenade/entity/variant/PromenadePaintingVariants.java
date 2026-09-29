package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

public class PromenadePaintingVariants {
    public static final ResourceKey<PaintingVariant> OPTIMISM = of("optimism");
    public static final ResourceKey<PaintingVariant> NURTURE = of("nurture");

    public static ResourceKey<PaintingVariant> of(String path) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, Promenade.id(path));
    }
}
