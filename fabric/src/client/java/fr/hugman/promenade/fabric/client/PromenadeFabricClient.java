package fr.hugman.promenade.fabric.client;

import fr.hugman.promenade.client.color.block.PromenadeBlockColors;
import fr.hugman.promenade.client.particle.PromenadeParticles;
import fr.hugman.promenade.client.render.entity.PromenadeEntityRenderers;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class PromenadeFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PromenadeEntityModelLayers.register((layer, definition) -> ModelLayerRegistry.registerModelLayer(layer, definition::get));
        PromenadeBlockColors.register(BlockColorRegistry::register);
        PromenadeEntityRenderers.register(EntityRenderers::register);
        PromenadeParticles.register((type, provider) -> ParticleProviderRegistry.getInstance().register(type, provider::apply));
    }
}
