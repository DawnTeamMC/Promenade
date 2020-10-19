package com.hugman.wild_explorer;

import com.hugman.wild_explorer.init.WEEntities;
import com.hugman.wild_explorer.init.client.WEColorMaps;
import com.hugman.wild_explorer.object.entity.render.DuckEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class WildExplorerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		WEColorMaps.registerColors();
		registerEntityRenders();
	}

	private void registerEntityRenders() {
		EntityRendererRegistry.INSTANCE.register(WEEntities.DUCK, (dispatcher, context) -> new DuckEntityRenderer(dispatcher));
	}
}
