package com.hugman.promenade;

import com.hugman.promenade.client.PromenadeColorMaps;
import com.hugman.promenade.client.PromenadeEntityModelLayers;
import com.hugman.promenade.client.PromenadeEntityRenders;
import com.hugman.promenade.content.AmaranthContent;
import com.hugman.promenade.content.CherryContent;
import com.hugman.promenade.content.MapleContent;
import com.hugman.promenade.content.PalmContent;
import com.hugman.promenade.init.FoodBundle;
import com.hugman.promenade.object.particle.FloatingParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;

@Environment(EnvType.CLIENT)
public class PromenadeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PromenadeColorMaps.registerColors();
		PromenadeEntityModelLayers.init();
		PromenadeEntityRenders.init();
		registerRenderLayers();

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(Promenade.id("particle/cherry_blossom/pink/1"));
			registry.register(Promenade.id("particle/cherry_blossom/pink/2"));
			registry.register(Promenade.id("particle/cherry_blossom/white/1"));
			registry.register(Promenade.id("particle/cherry_blossom/white/2"));
			registry.register(Promenade.id("particle/maple_leaf/vermilion"));
			registry.register(Promenade.id("particle/maple_leaf/fulvous"));
			registry.register(Promenade.id("particle/maple_leaf/mikado"));
		}));
		ParticleFactoryRegistry.getInstance().register(CherryContent.PINK_CHERRY_BLOSSOM, FloatingParticle.CherryBlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(CherryContent.WHITE_CHERRY_BLOSSOM, FloatingParticle.CherryBlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(MapleContent.MAPLE_LEAF, FloatingParticle.MapleLeafFactory::new);
	}

	public void registerRenderLayers() {
		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.CHERRY_OAK_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.CHERRY_OAK_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.PINK_CHERRY_OAK_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.POTTED_PINK_CHERRY_OAK_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.PINK_CHERRY_OAK_LEAF_PILE, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.WHITE_CHERRY_OAK_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.POTTED_WHITE_CHERRY_OAK_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(CherryContent.WHITE_CHERRY_OAK_LEAF_PILE, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.POTTED_PALM_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_LEAF_PILE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.MAPLE_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.MAPLE_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.VERMILION_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.POTTED_VERMILION_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.VERMILION_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.VERMILION_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.VERMILION_CARPETED_GRASS_BLOCK, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.FULVOUS_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.POTTED_FULVOUS_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.FULVOUS_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.FULVOUS_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.FULVOUS_CARPETED_GRASS_BLOCK, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.MIKADO_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.POTTED_MIKADO_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.MIKADO_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.MIKADO_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.MIKADO_CARPETED_GRASS_BLOCK, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.SAP_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.POTTED_SAP_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.SAP_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(MapleContent.SAP_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_ROOTS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_FUNGUS, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(FoodBundle.BLUEBERRY_BUSH, RenderLayer.getCutout());

	}
}
