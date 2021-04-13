package com.hugman.wild_explorer.init.client;

import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.init.WEEntities;
import com.hugman.wild_explorer.object.entity.model.DuckEntityModel;
import com.hugman.wild_explorer.object.entity.render.DuckEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class WEEntityModels {
	public static final EntityModelLayer DUCK = new EntityModelLayer(WildExplorer.MOD_DATA.id("duck"), "main");

	public static void init() {
		EntityModelLayerRegistry.registerModelLayer(DUCK, () -> DuckEntityModel.createModelData());
		registerEntityRenders();
	}

	private static void registerEntityRenders() {
		EntityRendererRegistry.INSTANCE.register(WEEntities.DUCK, DuckEntityRenderer::new);
	}
}
