package fr.hugman.promenade.trade;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;

public class PromenadeTrades {
    public static void appendVillagerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(TradeFactory.sapling(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
            factories.add(TradeFactory.sapling(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
            factories.add(TradeFactory.sapling(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
            factories.add(TradeFactory.sapling(PromenadeBlocks.SAP_MAPLE_SAPLING));
        });
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(TradeFactory.sapling(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
            factories.add(TradeFactory.sapling(PromenadeBlocks.COTTON_SAKURA_SAPLING));
        });
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(TradeFactory.sapling(PromenadeBlocks.PALM_SAPLING)));
    }
}
