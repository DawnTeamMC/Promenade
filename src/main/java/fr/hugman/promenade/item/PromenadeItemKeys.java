package fr.hugman.promenade.item;

import fr.hugman.promenade.Promenade;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeItemKeys {
    public static final RegistryKey<Item> BLUEBERRIES = of("blueberries");

    private static RegistryKey<Item> of(String path) {
        return RegistryKey.of(RegistryKeys.ITEM, Promenade.id(path));
    }
}
