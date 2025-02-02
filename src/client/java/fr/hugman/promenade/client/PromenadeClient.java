package fr.hugman.promenade.client;

import fr.hugman.promenade.client.color.PromenadeBlockColors;
import fr.hugman.promenade.client.particle.PromenadeParticleFactories;
import fr.hugman.promenade.client.render.block.PromenadeBlockRenderLayers;
import fr.hugman.promenade.client.render.entity.PromenadeEntityRenderers;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import net.fabricmc.api.ClientModInitializer;

public class PromenadeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PromenadeEntityModelLayers.register();
        PromenadeBlockRenderLayers.register();
        PromenadeBlockColors.register();
        PromenadeEntityRenderers.register();
        PromenadeParticleFactories.register();
    }
}