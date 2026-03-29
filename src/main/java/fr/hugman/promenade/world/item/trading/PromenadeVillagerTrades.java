package fr.hugman.promenade.world.item.trading;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.VillagerTrade;

public class PromenadeVillagerTrades {
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_VERMILION_MAPLE_SAPLING = resourceKey("wandering_trader/vermilion_maple_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_FULVOUS_MAPLE_SAPLING = resourceKey("wandering_trader/fulvous_maple_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_MIKADO_MAPLE_SAPLING = resourceKey("wandering_trader/mikado_maple_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_SAP_MAPLE_SAPLING = resourceKey("wandering_trader/sap_maple_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_BLUSH_SAKURA_SAPLING = resourceKey("wandering_trader/blush_sakura_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_COTTON_SAKURA_SAPLING = resourceKey("wandering_trader/cotton_sakura_sapling");
    public static final ResourceKey<VillagerTrade> WANDERING_TRADER_PALM_SAPLING = resourceKey("wandering_trader/palm_sapling");

    private static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Promenade.id(path));
    }
}
