package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.Promenade;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeItemGroupKeys {
    public static final RegistryKey<ItemGroup> PROMENADE = of("promenade");

    private static RegistryKey<ItemGroup> of(String path) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Promenade.id(path));
    }
}
