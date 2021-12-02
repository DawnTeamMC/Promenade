package com.hugman.promenade.init.client;

import com.hugman.promenade.init.AnimalBundle;
import com.hugman.promenade.init.MonsterBundle;
import com.hugman.promenade.object.entity.render.DuckEntityRenderer;
import com.hugman.promenade.object.entity.render.SunkenSkeletonEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class PromenadeEntityRenders {
	public static void init() {
		EntityRendererRegistry.register(AnimalBundle.DUCK, DuckEntityRenderer::new);
		EntityRendererRegistry.register(MonsterBundle.SUNKEN_SKELETON, SunkenSkeletonEntityRenderer::new);
	}
}
