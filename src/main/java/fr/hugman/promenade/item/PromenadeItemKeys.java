package fr.hugman.promenade.item;

import fr.hugman.promenade.Promenade;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeItemKeys {
    public static final RegistryKey<Item> SAKURA_SIGN = of("sakura_sign");
    public static final RegistryKey<Item> SAKURA_HANGING_SIGN = of("sakura_hanging_sign");
    public static final RegistryKey<Item> SAKURA_BOAT = of("sakura_boat");
    public static final RegistryKey<Item> SAKURA_CHEST_BOAT = of("sakura_chest_boat");

    public static final RegistryKey<Item> MAPLE_SIGN = of("maple_sign");
    public static final RegistryKey<Item> MAPLE_HANGING_SIGN = of("maple_hanging_sign");
    public static final RegistryKey<Item> MAPLE_BOAT = of("maple_boat");
    public static final RegistryKey<Item> MAPLE_CHEST_BOAT = of("maple_chest_boat");
    public static final RegistryKey<Item> MAPLE_SYRUP_BOTTLE = of("maple_syrup_bottle");

    private static RegistryKey<Item> of(String path) {
        return RegistryKey.of(RegistryKeys.ITEM, Promenade.id(path));
    }
}
