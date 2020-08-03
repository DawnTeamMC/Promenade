package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.wild_explorer.init.data.WEFood;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class WEItemPack extends WEPack {
	public static final Item BLUEBERRIES = register(new ItemCreator.Builder("blueberries", new AliasedBlockItem(WEBlockPack.BLUEBERRY_BUSH, new Item.Settings().group(ItemGroup.FOOD).food(WEFood.BLUEBERRIES))).setCompostingChance(0.30F));
	public static final Item BANANA = register(new ItemCreator.Builder("banana", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFood.BANANA))).setCompostingChance(0.65F));
	public static final Item APRICOT = register(new ItemCreator.Builder("apricot", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFood.APRICOT))).setCompostingChance(0.65F));
	public static final Item MANGO = register(new ItemCreator.Builder("mango", new Item(new Item.Settings().group(ItemGroup.FOOD).food(WEFood.MANGO))).setCompostingChance(0.65F));
}
