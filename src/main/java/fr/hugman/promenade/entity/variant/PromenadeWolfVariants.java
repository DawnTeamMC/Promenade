package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.wolf.WolfVariant;

public class PromenadeWolfVariants {
    public static final ResourceKey<WolfVariant> SHIBA_INU = of("shiba_inu");

    public static ResourceKey<WolfVariant> of(String path) {
        return ResourceKey.create(Registries.WOLF_VARIANT, Promenade.id(path));
    }
}
