package com.hugman.newdawn.builder;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemBuilder {
	protected final Item item;

	protected ItemBuilder(Item item) {
		this.item = item;
	}

	public static ItemBuilder of(Identifier id, Item item) {
		Registry.register(Registry.ITEM, id, item);
		return new ItemBuilder(item);
	}

	public static ItemBuilder of(Identifier id, ItemGroup group) {
		return of(id, new Item(new Item.Settings().group(group)));
	}

	/**
	 * @see AbstractFurnaceBlockEntity#createFuelTimeMap() Vanilla fuel times
	 */
	public ItemBuilder fuelTime(int i) {
		FuelRegistry.INSTANCE.add(this.item, i);
		return this;
	}

	/**
	 * @see ComposterBlock#registerDefaultCompostableItems() Vanilla composting chances
	 */
	public ItemBuilder compostingChance(float i) {
		CompostingChanceRegistry.INSTANCE.add(this.item, i);
		return this;
	}

	public Item get() {
		return this.item;
	}
}
