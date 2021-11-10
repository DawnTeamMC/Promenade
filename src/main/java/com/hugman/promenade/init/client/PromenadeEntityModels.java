package com.hugman.promenade.init.client;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.PromenadeEntities;
import com.hugman.promenade.object.entity.model.DuckEntityModel;
import com.hugman.promenade.object.entity.render.DuckEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class PromenadeEntityModels {
	public static final EntityModelLayer DUCK = new EntityModelLayer(Promenade.MOD_DATA.id("duck"), "main");

	public static void init() {
		registerModelLayers();
		registerEntityRenders();
	}

	private static void registerModelLayers() {
		EntityModelLayerRegistry.registerModelLayer(DUCK, DuckEntityModel::createModelData);
	}

	private static void registerEntityRenders() {
		EntityRendererRegistry.register(PromenadeEntities.DUCK, DuckEntityRenderer::new);
		EntityRendererRegistry.register(PromenadeEntities.SUNKEN_SKELETON, SkeletonEntityRenderer::new);
	}
}
