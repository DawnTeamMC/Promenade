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

    public static final RegistryKey<Item> PALM_SIGN = of("palm_sign");
    public static final RegistryKey<Item> PALM_HANGING_SIGN = of("palm_hanging_sign");
    public static final RegistryKey<Item> PALM_BOAT = of("palm_boat");
    public static final RegistryKey<Item> PALM_CHEST_BOAT = of("palm_chest_boat");

    public static final RegistryKey<Item> AURORAL_CYPRESS_SIGN = of("auroral_cypress_sign");
    public static final RegistryKey<Item> AURORAL_CYPRESS_HANGING_SIGN = of("auroral_cypress_hanging_sign");
    public static final RegistryKey<Item> AURORAL_CYPRESS_BOAT = of("auroral_cypress_boat");
    public static final RegistryKey<Item> AURORAL_CYPRESS_CHEST_BOAT = of("auroral_cypress_chest_boat");

    public static final RegistryKey<Item> DARK_AMARANTH_SIGN = of("dark_amaranth_sign");
    public static final RegistryKey<Item> DARK_AMARANTH_HANGING_SIGN = of("dark_amaranth_hanging_sign");

    public static final RegistryKey<Item> BLUEBERRIES = of("blueberries");

    public static final RegistryKey<Item> BANANA = of("banana");
    public static final RegistryKey<Item> APRICOT = of("apricot");
    public static final RegistryKey<Item> MANGO = of("mango");

    public static final RegistryKey<Item> DUCK = of("duck");
    public static final RegistryKey<Item> COOKED_DUCK = of("cooked_duck");

    public static final RegistryKey<Item> STAR_DUST = of("star_dust");

    public static final RegistryKey<Item> CAPYBARA_SPAWN_EGG = of("capybara_spawn_egg");
    public static final RegistryKey<Item> DUCK_SPAWN_EGG = of("duck_spawn_egg");
    public static final RegistryKey<Item> LUSH_CREEPER_SPAWN_EGG = of("lush_creeper_spawn_egg");
    public static final RegistryKey<Item> SUNKEN_SPAWN_EGG = of("sunken_spawn_egg");

    private static RegistryKey<Item> of(String path) {
        return RegistryKey.of(RegistryKeys.ITEM, Promenade.id(path));
    }
}
