package com.hugman.promenade.object.sell;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.Random;

public class SellSaplingFactory implements TradeOffers.Factory {
	private final ItemStack sapling;

	public SellSaplingFactory(ItemConvertible sapling) {
		this.sapling = new ItemStack(sapling);
	}

	public TradeOffer create(Entity entity, Random random) {
		return new TradeOffer(new ItemStack(Items.EMERALD, 5), this.sapling, 8, 1, 0.05f);
	}
}
