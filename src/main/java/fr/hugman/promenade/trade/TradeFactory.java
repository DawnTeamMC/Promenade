package fr.hugman.promenade.trade;

import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;

public class TradeFactory {
    public static VillagerTrades.ItemListing sapling(ItemLike sapling) {
		return (world, entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(sapling), 8, 1, 0.05f);
    }
}
