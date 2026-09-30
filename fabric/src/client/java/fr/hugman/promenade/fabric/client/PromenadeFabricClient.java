package fr.hugman.promenade.fabric.client;

import fr.hugman.promenade.client.color.block.PromenadeBlockColors;
import fr.hugman.promenade.client.particle.PromenadeParticles;
import fr.hugman.promenade.client.render.block.PromenadeBlockRenderLayers;
import fr.hugman.promenade.client.render.entity.PromenadeEntityRenderers;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class PromenadeFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PromenadeEntityModelLayers.register((layer, definition) -> EntityModelLayerRegistry.registerModelLayer(layer, definition::get));
        PromenadeBlockRenderLayers.register(BlockRenderLayerMap.INSTANCE::putBlocks);
        PromenadeBlockColors.register(ColorProviderRegistry.BLOCK::register);
        PromenadeBlockColors.registerItems(ColorProviderRegistry.ITEM::register);
        PromenadeEntityRenderers.register(EntityRendererRegistry::register);
        PromenadeParticles.register((type, provider) -> ParticleFactoryRegistry.getInstance().register(type, provider::apply));
    }
}
