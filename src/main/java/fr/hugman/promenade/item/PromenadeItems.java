package fr.hugman.promenade.item;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.component.PromenadeConsumableComponents;
import fr.hugman.promenade.component.PromenadeFoodComponents;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.helper.ItemFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PromenadeItems {
    public static final Item SAKURA_SIGN = register(PromenadeBlocks.SAKURA_SIGN, ItemFactory.sign(PromenadeBlocks.SAKURA_WALL_SIGN), ItemSettings.max16());
    public static final Item SAKURA_HANGING_SIGN = register(PromenadeBlocks.SAKURA_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.SAKURA_WALL_HANGING_SIGN), ItemSettings.max16());
    public static final Item SAKURA_BOAT = register("sakura_boat", ItemFactory.boat(PromenadeEntityTypes.SAKURA_BOAT), ItemSettings.max1());
    public static final Item SAKURA_CHEST_BOAT = register("sakura_chest_boat", ItemFactory.boat(PromenadeEntityTypes.SAKURA_CHEST_BOAT), ItemSettings.max1());

    public static final Item MAPLE_SIGN = register(PromenadeBlocks.MAPLE_SIGN, ItemFactory.sign(PromenadeBlocks.MAPLE_WALL_SIGN), ItemSettings.max16());
    public static final Item MAPLE_HANGING_SIGN = register(PromenadeBlocks.MAPLE_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.MAPLE_WALL_HANGING_SIGN), ItemSettings.max16());
    public static final Item MAPLE_BOAT = register("maple_boat", ItemFactory.boat(PromenadeEntityTypes.MAPLE_BOAT), ItemSettings.max1());
    public static final Item MAPLE_CHEST_BOAT = register("maple_chest_boat", ItemFactory.boat(PromenadeEntityTypes.MAPLE_CHEST_BOAT), ItemSettings.max1());
    public static final Item MAPLE_SYRUP_BOTTLE = register("maple_syrup_bottle", ItemSettings.stackableDrink(PromenadeFoodComponents.MAPLE_SYRUP_BOTTLE, PromenadeConsumableComponents.MAPLE_SYRUP_BOTTLE));

    public static final Item PALM_SIGN = register(PromenadeBlocks.PALM_SIGN, ItemFactory.sign(PromenadeBlocks.PALM_WALL_SIGN), ItemSettings.max16());
    public static final Item PALM_HANGING_SIGN = register(PromenadeBlocks.PALM_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.PALM_WALL_HANGING_SIGN), ItemSettings.max16());
    public static final Item PALM_BOAT = register("palm_boat", ItemFactory.boat(PromenadeEntityTypes.PALM_BOAT), ItemSettings.max1());
    public static final Item PALM_CHEST_BOAT = register("palm_chest_boat", ItemFactory.boat(PromenadeEntityTypes.PALM_CHEST_BOAT), ItemSettings.max1());

    public static final Item DARK_AMARANTH_SIGN = register(PromenadeBlocks.DARK_AMARANTH_SIGN, ItemFactory.sign(PromenadeBlocks.DARK_AMARANTH_WALL_SIGN), ItemSettings.max16());
    public static final Item DARK_AMARANTH_HANGING_SIGN = register(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN), ItemSettings.max16());

    public static final Item BLUEBERRIES = register(PromenadeItemKeys.BLUEBERRIES, ItemFactory.uniqueNameBlock(PromenadeBlocks.BLUEBERRY_BUSH), new Item.Settings().food(PromenadeFoodComponents.BLUEBERRIES));

    public static final Item BANANA = register("banana", new Item.Settings().food(PromenadeFoodComponents.BANANA));
    public static final Item APRICOT = register("apricot", new Item.Settings().food(PromenadeFoodComponents.APRICOT));
    public static final Item MANGO = register("mango", new Item.Settings().food(PromenadeFoodComponents.MANGO));

    public static final Item DUCK = register("duck", new Item.Settings().food(PromenadeFoodComponents.RAW_DUCK, PromenadeConsumableComponents.RAW_DUCK));
    public static final Item COOKED_DUCK = register("cooked_duck", new Item.Settings().food(PromenadeFoodComponents.COOKED_DUCK));

    //TODO: dispenser behaviors
    public static final Item CAPYBARA_SPAWN_EGG = register("capybara_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.CAPYBARA));
    public static final Item DUCK_SPAWN_EGG = register("duck_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.DUCK));
    public static final Item LUSH_CREEPER_SPAWN_EGG = register("lush_creeper_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.LUSH_CREEPER));
    public static final Item SUNKEN_SPAWN_EGG = register("sunken_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.SUNKEN));

    private static RegistryKey<Item> keyOf(String path) {
        return RegistryKey.of(RegistryKeys.ITEM, Promenade.id(path));
    }

    private static RegistryKey<Item> keyOf(RegistryKey<Block> blockKey) {
        return RegistryKey.of(RegistryKeys.ITEM, blockKey.getValue());
    }

    public static <O extends Item> O register(Block block, BiFunction<Block, Item.Settings, O> factory, Item.Settings settings) {
        return register(keyOf(block.getRegistryEntry().registryKey()), itemSettings -> factory.apply(block, itemSettings), settings.useBlockPrefixedTranslationKey());
    }

    public static <O extends Item> O register(RegistryKey<Item> key, Function<Item.Settings, O> factory, Item.Settings settings) {
        O item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static <O extends Item> O register(String id, Function<Item.Settings, O> factory, Item.Settings settings) {
        return register(keyOf(id), factory, settings);
    }

    public static <O extends Item> O register(String id, Function<Item.Settings, O> factory) {
        return register(keyOf(id), factory, new Item.Settings());
    }

    public static Item register(String id, Item.Settings settings) {
        return register(keyOf(id), Item::new, settings);
    }
}
