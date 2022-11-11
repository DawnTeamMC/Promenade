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

	private ItemBuilder(Item item) {
		this.item = item;
	}

	public static ItemBuilder of(Identifier id, Item item) {
		Registry.register(Registry.ITEM, id, item);
		return new ItemBuilder(item);
	}

	/**
	 * @see AbstractFurnaceBlockEntity#createFuelTimeMap() Vanilla fuel times
	 */
	public ItemBuilder fuelTime(int i) {
		if(i > 0) FuelRegistry.INSTANCE.add(this.item, i);
		return this;
	}

	/**
	 * @see ComposterBlock#registerDefaultCompostableItems() Vanilla composting chances
	 */
	public ItemBuilder compostingChance(float i) {
		if(i > 0.0f) CompostingChanceRegistry.INSTANCE.add(this.item, i);
		return this;
	}

	public Item get() {
		return this.item;
	}
}
