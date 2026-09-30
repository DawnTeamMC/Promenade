package fr.hugman.promenade.world.item.trading;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class PromenadeVillagerTrades {
    /**
     * @return the trades that wandering traders may offer among their common ones
     */
    public static List<VillagerTrades.ItemListing> wanderingTraderCommonTrades() {
        return List.of(
                sapling(PromenadeBlocks.VERMILION_MAPLE_SAPLING),
                sapling(PromenadeBlocks.FULVOUS_MAPLE_SAPLING),
                sapling(PromenadeBlocks.MIKADO_MAPLE_SAPLING),
                sapling(PromenadeBlocks.SAP_MAPLE_SAPLING),

                sapling(PromenadeBlocks.BLUSH_SAKURA_SAPLING),
                sapling(PromenadeBlocks.COTTON_SAKURA_SAPLING),

                sapling(PromenadeBlocks.PALM_SAPLING)
        );
    }

    /**
     * Same trade as the vanilla saplings sold by wandering traders.
     */
    private static VillagerTrades.ItemListing sapling(ItemLike sapling) {
        return (trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 5), new ItemStack(sapling), 8, 1, 0.05F);
    }
}
