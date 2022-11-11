package com.hugman.promenade.client;

import com.hugman.promenade.content.AnimalContent;
import com.hugman.promenade.content.MonsterContent;
import com.hugman.promenade.object.entity.render.DuckEntityRenderer;
import com.hugman.promenade.object.entity.render.LushCreeperEntityRenderer;
import com.hugman.promenade.object.entity.render.SunkenSkeletonEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class PromenadeEntityRenders {
	public static void init() {
		EntityRendererRegistry.register(AnimalContent.DUCK, DuckEntityRenderer::new);
		EntityRendererRegistry.register(MonsterContent.LUSH_CREEPER, LushCreeperEntityRenderer::new);
		EntityRendererRegistry.register(MonsterContent.SUNKEN_SKELETON, SunkenSkeletonEntityRenderer::new);
	}
}
