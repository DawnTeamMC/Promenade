package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.item.trading.PromenadeVillagerTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class PromenadeVillagerTradeProvider extends FabricDynamicRegistryProvider {
    public PromenadeVillagerTradeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        final var wrapper = provider.lookupOrThrow(Registries.VILLAGER_TRADE);
        entries.add(wrapper, PromenadeVillagerTrades.WANDERING_TRADER_VERMILION_MAPLE_SAPLING);
        entries.add(wrapper, PromenadeVillagerTrades.WANDERING_TRADER_FULVOUS_MAPLE_SAPLING);
        entries.add(wrapper, PromenadeVillagerTrades.WANDERING_TRADER_MIKADO_MAPLE_SAPLING);
        entries.add(wrapper, PromenadeVillagerTrades.WANDERING_TRADER_SAP_MAPLE_SAPLING);
        entries.add(wrapper, PromenadeVillagerTrades.WANDERING_TRADER_BLUSH_SAKURA_SAPLING);
        entries.add(wrapper, PromenadeVillagerTrades.WANDERING_TRADER_COTTON_SAKURA_SAPLING);
        entries.add(wrapper, PromenadeVillagerTrades.WANDERING_TRADER_PALM_SAPLING);
    }

    @Override
    public String getName() {
        return "Villager Trades";
    }

    public static void register(BootstrapContext<VillagerTrade> context) {
        context.register(PromenadeVillagerTrades.WANDERING_TRADER_VERMILION_MAPLE_SAPLING, wanderingTraderSapling(PromenadeBlocks.VERMILION_MAPLE_SAPLING));
        context.register(PromenadeVillagerTrades.WANDERING_TRADER_FULVOUS_MAPLE_SAPLING, wanderingTraderSapling(PromenadeBlocks.FULVOUS_MAPLE_SAPLING));
        context.register(PromenadeVillagerTrades.WANDERING_TRADER_MIKADO_MAPLE_SAPLING, wanderingTraderSapling(PromenadeBlocks.MIKADO_MAPLE_SAPLING));
        context.register(PromenadeVillagerTrades.WANDERING_TRADER_SAP_MAPLE_SAPLING, wanderingTraderSapling(PromenadeBlocks.SAP_MAPLE_SAPLING));

        context.register(PromenadeVillagerTrades.WANDERING_TRADER_BLUSH_SAKURA_SAPLING, wanderingTraderSapling(PromenadeBlocks.BLUSH_SAKURA_SAPLING));
        context.register(PromenadeVillagerTrades.WANDERING_TRADER_COTTON_SAKURA_SAPLING, wanderingTraderSapling(PromenadeBlocks.COTTON_SAKURA_SAPLING));

        context.register(PromenadeVillagerTrades.WANDERING_TRADER_PALM_SAPLING, wanderingTraderSapling(PromenadeBlocks.PALM_SAPLING));
    }


    private static VillagerTrade wanderingTraderSapling(Block sapling) {
        return new VillagerTrade(new TradeCost(Items.EMERALD, 5), new ItemStackTemplate(sapling.asItem()), 8, 1, 0.05F, Optional.empty(), List.of());
    }
}