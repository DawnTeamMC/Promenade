package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PromenadeItemGroupKeys {
    public static final ResourceKey<CreativeModeTab> PROMENADE = of("promenade");

    private static ResourceKey<CreativeModeTab> of(String path) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, Promenade.id(path));
    }
}
