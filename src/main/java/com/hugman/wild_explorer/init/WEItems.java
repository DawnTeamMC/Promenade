package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.wild_explorer.init.data.WEFoods;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class WEItems extends WEBundle {
	public static final Item BLUEBERRIES = add(new ItemCreator.Builder("blueberries", settings -> new AliasedBlockItem(WEBlocks.BLUEBERRY_BUSH, settings), new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.BLUEBERRIES)).compostingChance(0.30F).build());
	public static final Item BANANA = add(new ItemCreator.Builder("banana", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.BANANA)).compostingChance(0.65F).build());
	public static final Item APRICOT = add(new ItemCreator.Builder("apricot", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.APRICOT)).compostingChance(0.65F).build());
	public static final Item MANGO = add(new ItemCreator.Builder("mango", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.MANGO)).compostingChance(0.65F).build());
	public static final Item DUCK = add(new ItemCreator.Builder("duck", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.DUCK)).build());
	public static final Item COOKED_DUCK = add(new ItemCreator.Builder("cooked_duck", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(WEFoods.COOKED_DUCK)).build());

	public static void init() {
	}
}
