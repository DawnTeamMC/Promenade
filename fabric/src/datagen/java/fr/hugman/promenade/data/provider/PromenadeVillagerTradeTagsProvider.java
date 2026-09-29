package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.world.item.trading.PromenadeVillagerTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class PromenadeVillagerTradeTagsProvider extends FabricTagsProvider<VillagerTrade> {

    public PromenadeVillagerTradeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(VillagerTradeTags.WANDERING_TRADER_COMMON)
                .add(PromenadeVillagerTrades.WANDERING_TRADER_VERMILION_MAPLE_SAPLING)
                .add(PromenadeVillagerTrades.WANDERING_TRADER_FULVOUS_MAPLE_SAPLING)
                .add(PromenadeVillagerTrades.WANDERING_TRADER_MIKADO_MAPLE_SAPLING)
                .add(PromenadeVillagerTrades.WANDERING_TRADER_SAP_MAPLE_SAPLING)
                .add(PromenadeVillagerTrades.WANDERING_TRADER_BLUSH_SAKURA_SAPLING)
                .add(PromenadeVillagerTrades.WANDERING_TRADER_COTTON_SAKURA_SAPLING)
                .add(PromenadeVillagerTrades.WANDERING_TRADER_PALM_SAPLING);
    }
}