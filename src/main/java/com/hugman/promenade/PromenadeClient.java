package com.hugman.promenade;

import com.hugman.promenade.init.CherryBundle;
import com.hugman.promenade.init.MapleBundle;
import com.hugman.promenade.init.client.PromenadeColorMaps;
import com.hugman.promenade.init.client.PromenadeEntityModelLayers;
import com.hugman.promenade.init.client.PromenadeEntityRenders;
import com.hugman.promenade.object.particle.FloatingParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.texture.SpriteAtlasTexture;

@Environment(EnvType.CLIENT)
public class PromenadeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PromenadeColorMaps.registerColors();
		PromenadeEntityModelLayers.init();
		PromenadeEntityRenders.init();

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(Promenade.MOD_DATA.id("particle/cherry_blossom/pink/1"));
			registry.register(Promenade.MOD_DATA.id("particle/cherry_blossom/pink/2"));
			registry.register(Promenade.MOD_DATA.id("particle/cherry_blossom/white/1"));
			registry.register(Promenade.MOD_DATA.id("particle/cherry_blossom/white/2"));
			registry.register(Promenade.MOD_DATA.id("particle/maple_leaf/vermilion"));
			registry.register(Promenade.MOD_DATA.id("particle/maple_leaf/fulvous"));
			registry.register(Promenade.MOD_DATA.id("particle/maple_leaf/mikado"));
		}));
		ParticleFactoryRegistry.getInstance().register(CherryBundle.PINK_CHERRY_BLOSSOM, FloatingParticle.CherryBlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(CherryBundle.WHITE_CHERRY_BLOSSOM, FloatingParticle.CherryBlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(MapleBundle.MAPLE_LEAF, FloatingParticle.MapleLeafFactory::new);
	}
}
