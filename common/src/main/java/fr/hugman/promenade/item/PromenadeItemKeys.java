package fr.hugman.promenade.item;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class PromenadeItemKeys {
    public static final ResourceKey<Item> BLUEBERRIES = of("blueberries");

    private static ResourceKey<Item> of(String path) {
        return ResourceKey.create(Registries.ITEM, Promenade.id(path));
    }
}
