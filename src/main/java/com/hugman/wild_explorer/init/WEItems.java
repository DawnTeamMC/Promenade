package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.wild_explorer.init.data.WEFoods;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class WEItems extends WEPack {
	public static void init() {
	}

	public static final Item BLUEBERRIES = register(new ItemCreator.Builder("blueberries", new AliasedBlockItem(WEBlocks.BLUEBERRY_BUSH, new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.BLUEBERRIES))).compostingChance(0.30F));
	public static final Item BANANA = register(new ItemCreator.Builder("banana", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.BANANA))).compostingChance(0.65F));
	public static final Item APRICOT = register(new ItemCreator.Builder("apricot", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.APRICOT))).compostingChance(0.65F));
	public static final Item MANGO = register(new ItemCreator.Builder("mango", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.MANGO))).compostingChance(0.65F));
	public static final Item DUCK = register(new ItemCreator.Builder("duck", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.DUCK))));
	public static final Item COOKED_DUCK = register(new ItemCreator.Builder("cooked_duck", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.COOKED_DUCK))));
}
