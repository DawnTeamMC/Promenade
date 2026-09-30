package fr.hugman.promenade.item;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.banner.PromenadeBannerPatternTags;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.component.PromenadeFoodComponents;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.helper.ItemFactory;
import fr.hugman.promenade.registry.PromenadeCompostables;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PromenadeItems {
    public static final Item SAKURA_SIGN = register(PromenadeBlocks.SAKURA_SIGN, ItemFactory.sign(PromenadeBlocks.SAKURA_WALL_SIGN), ItemSettings.sign());
    public static final Item SAKURA_HANGING_SIGN = register(PromenadeBlocks.SAKURA_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.SAKURA_WALL_HANGING_SIGN), ItemSettings.hangingSign());
    public static final Item SAKURA_BOAT = register("sakura_boat", ItemFactory.boat(() -> PromenadeEntityTypes.SAKURA_BOAT, false), ItemSettings.boat());
    public static final Item SAKURA_CHEST_BOAT = register("sakura_chest_boat", ItemFactory.boat(() -> PromenadeEntityTypes.SAKURA_CHEST_BOAT, true), ItemSettings.boat());

    public static final Item MAPLE_SIGN = register(PromenadeBlocks.MAPLE_SIGN, ItemFactory.sign(PromenadeBlocks.MAPLE_WALL_SIGN), ItemSettings.sign());
    public static final Item MAPLE_HANGING_SIGN = register(PromenadeBlocks.MAPLE_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.MAPLE_WALL_HANGING_SIGN), ItemSettings.hangingSign());
    public static final Item MAPLE_BOAT = register("maple_boat", ItemFactory.boat(() -> PromenadeEntityTypes.MAPLE_BOAT, false), ItemSettings.boat());
    public static final Item MAPLE_CHEST_BOAT = register("maple_chest_boat", ItemFactory.boat(() -> PromenadeEntityTypes.MAPLE_CHEST_BOAT, true), ItemSettings.boat());
    public static final Item MAPLE_SYRUP_BOTTLE = register("maple_syrup_bottle", HoneyBottleItem::new, ItemSettings.stackableDrink(PromenadeFoodComponents.MAPLE_SYRUP_BOTTLE));

    public static final Item PALM_SIGN = register(PromenadeBlocks.PALM_SIGN, ItemFactory.sign(PromenadeBlocks.PALM_WALL_SIGN), ItemSettings.sign());
    public static final Item PALM_HANGING_SIGN = register(PromenadeBlocks.PALM_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.PALM_WALL_HANGING_SIGN), ItemSettings.hangingSign());
    public static final Item PALM_BOAT = register("palm_boat", ItemFactory.boat(() -> PromenadeEntityTypes.PALM_BOAT, false), ItemSettings.boat());
    public static final Item PALM_CHEST_BOAT = register("palm_chest_boat", ItemFactory.boat(() -> PromenadeEntityTypes.PALM_CHEST_BOAT, true), ItemSettings.boat());

    public static final Item DARK_AMARANTH_SIGN = register(PromenadeBlocks.DARK_AMARANTH_SIGN, ItemFactory.sign(PromenadeBlocks.DARK_AMARANTH_WALL_SIGN), ItemSettings.nonFlammableSign());
    public static final Item DARK_AMARANTH_HANGING_SIGN = register(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN, ItemFactory.hangingSign(PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN), ItemSettings.nonFlammableSign());

    public static final Item BLUEBERRIES = compostable(register(PromenadeItemKeys.BLUEBERRIES, ItemFactory.uniqueNameBlock(PromenadeBlocks.BLUEBERRY_BUSH), new Item.Properties().food(PromenadeFoodComponents.BLUEBERRIES)), PromenadeCompostables.LOW);

    public static final Item BANANA = compostable(register("banana", new Item.Properties().food(PromenadeFoodComponents.BANANA)), PromenadeCompostables.MEDIUM);
    public static final Item APRICOT = compostable(register("apricot", new Item.Properties().food(PromenadeFoodComponents.APRICOT)), PromenadeCompostables.MEDIUM);
    public static final Item MANGO = compostable(register("mango", new Item.Properties().food(PromenadeFoodComponents.MANGO)), PromenadeCompostables.MEDIUM);

    public static final Item DUCK = register("duck", new Item.Properties().food(PromenadeFoodComponents.RAW_DUCK));
    public static final Item COOKED_DUCK = register("cooked_duck", new Item.Properties().food(PromenadeFoodComponents.COOKED_DUCK));

    public static final Item BOVINE_BANNER_PATTERN = register("bovine_banner_pattern", settings -> new BannerPatternItem(PromenadeBannerPatternTags.BOVINE_PATTERN_ITEM, settings), ItemSettings.max1());

    // Spawn eggs are tinted templates in 1.21.1
    public static final Item CAPYBARA_SPAWN_EGG = register("capybara_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.CAPYBARA, 0xa0704e, 0x433930));
    public static final Item DUCK_SPAWN_EGG = register("duck_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.DUCK, 10592673, 15904341));
    public static final Item LUSH_CREEPER_SPAWN_EGG = register("lush_creeper_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.LUSH_CREEPER, 4347181, 4262661));
    public static final Item SUNKEN_SPAWN_EGG = register("sunken_spawn_egg", ItemFactory.spawnEgg(PromenadeEntityTypes.SUNKEN, 12233882, 6191682));

    private static ResourceKey<Item> keyOf(String path) {
        return ResourceKey.create(Registries.ITEM, Promenade.id(path));
    }

    private static ResourceKey<Item> keyOf(ResourceKey<Block> blockKey) {
        return ResourceKey.create(Registries.ITEM, blockKey.location());
    }

    private static Item compostable(Item item, float chance) {
        PromenadeCompostables.add(item, chance);
        return item;
    }

    public static <O extends Item> O register(Block block, BiFunction<Block, Item.Properties, O> factory, Item.Properties settings) {
        return register(keyOf(block.builtInRegistryHolder().key()), itemSettings -> factory.apply(block, itemSettings), settings);
    }

    public static <O extends Item> O register(ResourceKey<Item> key, Function<Item.Properties, O> factory, Item.Properties settings) {
        O item = factory.apply(settings);
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static <O extends Item> O register(String id, Function<Item.Properties, O> factory, Item.Properties settings) {
        return register(keyOf(id), factory, settings);
    }

    public static <O extends Item> O register(String id, Function<Item.Properties, O> factory) {
        return register(keyOf(id), factory, new Item.Properties());
    }

    public static Item register(String id, Item.Properties settings) {
        return register(keyOf(id), Item::new, settings);
    }
}
