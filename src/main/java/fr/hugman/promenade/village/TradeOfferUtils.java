package fr.hugman.promenade.village;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;

public class TradeOfferUtils {
    public static TradeOffers.Factory sapling(ItemConvertible sapling) {
        return (entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 5), new ItemStack(sapling), 8, 1, 0.05f);
    }
}
