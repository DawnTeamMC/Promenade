package fr.hugman.promenade.client;

import fr.hugman.promenade.client.color.PromenadeColors;
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
		PromenadeColors.register();
		PromenadeEntityRenderers.register();
		PromenadeParticleFactories.register();
	}
}