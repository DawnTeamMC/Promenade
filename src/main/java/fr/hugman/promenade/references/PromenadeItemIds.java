package fr.hugman.promenade.references;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PromenadeItemIds {
    public static final ResourceKey<Item> SAKURA_BOAT = createKey("sakura_boat");
    public static final ResourceKey<Item> SAKURA_CHEST_BOAT = createKey("sakura_chest_boat");

    public static final ResourceKey<Item> MAPLE_BOAT = createKey("maple_boat");
    public static final ResourceKey<Item> MAPLE_CHEST_BOAT = createKey("maple_chest_boat");
    public static final ResourceKey<Item> MAPLE_SYRUP_BOTTLE = createKey("maple_syrup_bottle");

    public static final ResourceKey<Item> PALM_BOAT = createKey("palm_boat");
    public static final ResourceKey<Item> PALM_CHEST_BOAT = createKey("palm_chest_boat");

    public static final ResourceKey<Item> BLUEBERRIES = createKey("blueberries");

    public static final ResourceKey<Item> BANANA = createKey("banana");
    public static final ResourceKey<Item> APRICOT = createKey("apricot");
    public static final ResourceKey<Item> MANGO = createKey("mango");

    public static final ResourceKey<Item> DUCK = createKey("duck");
    public static final ResourceKey<Item> COOKED_DUCK = createKey("cooked_duck");

    public static final ResourceKey<Item> BOVINE_BANNER_PATTERN = createKey("bovine_banner_pattern");

    public static final ResourceKey<Item> CAPYBARA_SPAWN_EGG = createKey("capybara_spawn_egg");
    public static final ResourceKey<Item> DUCK_SPAWN_EGG = createKey("duck_spawn_egg");
    public static final ResourceKey<Item> LUSH_CREEPER_SPAWN_EGG = createKey("lush_creeper_spawn_egg");
    public static final ResourceKey<Item> SUNKEN_SPAWN_EGG = createKey("sunken_spawn_egg");

    private static ResourceKey<Item> createKey(String path) {
        return ResourceKey.create(Registries.ITEM, Promenade.id(path));
    }
}
