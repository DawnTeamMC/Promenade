package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.promenade.init.data.PromenadeFoods;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBundle extends PromenadeBundle {
	public static final Item BLUEBERRIES = add(new ItemCreator.Builder("blueberries", settings -> new AliasedBlockItem(BlockBundle.BLUEBERRY_BUSH, settings), new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.BLUEBERRIES)).compostingChance(0.30F).build());
	public static final Item BANANA = add(new ItemCreator.Builder("banana", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.BANANA)).compostingChance(0.65F).build());
	public static final Item APRICOT = add(new ItemCreator.Builder("apricot", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.APRICOT)).compostingChance(0.65F).build());
	public static final Item MANGO = add(new ItemCreator.Builder("mango", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.MANGO)).compostingChance(0.65F).build());
	public static final Item DUCK = add(new ItemCreator.Builder("duck", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.DUCK)).build());
	public static final Item COOKED_DUCK = add(new ItemCreator.Builder("cooked_duck", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.COOKED_DUCK)).build());

	public static void init() {
	}
}
