/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
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
