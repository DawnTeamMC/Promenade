package fr.hugman.promenade.fabric;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeCompostables;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.PromenadeStrippables;
import fr.hugman.promenade.world.item.trading.PromenadeVillagerTrades;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class PromenadeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PromenadeRegistries.register(DynamicRegistries::registerSynced);

        Promenade.init();

        PromenadeStrippables.register(StrippableBlockRegistry::register);
        PromenadeCompostables.all().forEach(CompostingChanceRegistry.INSTANCE::add);
        TradeOfferHelper.registerWanderingTraderOffers(1, trades -> trades.addAll(PromenadeVillagerTrades.wanderingTraderCommonTrades()));
    }
}
