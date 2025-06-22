package fr.hugman.promenade.trade;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;

public class PromenadeTrades {
    public static void appendVillagerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(builder -> builder.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                TradeFactory.sapling(PromenadeBlocks.VERMILION_MAPLE_SAPLING),
                TradeFactory.sapling(PromenadeBlocks.FULVOUS_MAPLE_SAPLING),
                TradeFactory.sapling(PromenadeBlocks.MIKADO_MAPLE_SAPLING),
                TradeFactory.sapling(PromenadeBlocks.SAP_MAPLE_SAPLING),

                TradeFactory.sapling(PromenadeBlocks.BLUSH_SAKURA_SAPLING),
                TradeFactory.sapling(PromenadeBlocks.COTTON_SAKURA_SAPLING),

                TradeFactory.sapling(PromenadeBlocks.PALM_SAPLING)
        ));
    }
}
