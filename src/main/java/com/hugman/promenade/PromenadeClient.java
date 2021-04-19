package com.hugman.promenade;

import com.hugman.promenade.init.PromenadeEntities;
import com.hugman.promenade.init.client.PromenadeColorMaps;
import com.hugman.promenade.object.entity.render.DuckEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class PromenadeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PromenadeColorMaps.registerColors();
		registerEntityRenders();
	}

	private void registerEntityRenders() {
		EntityRendererRegistry.INSTANCE.register(PromenadeEntities.DUCK, (dispatcher, context) -> new DuckEntityRenderer(dispatcher));
	}
}
