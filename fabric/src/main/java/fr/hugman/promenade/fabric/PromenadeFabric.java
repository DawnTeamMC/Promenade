package fr.hugman.promenade.fabric;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.PromenadeStrippables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;

public class PromenadeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PromenadeRegistries.register(DynamicRegistries::registerSynced);

        Promenade.init();

        PromenadeStrippables.register(BlockTransformerHelper::registerStripping);
    }
}
