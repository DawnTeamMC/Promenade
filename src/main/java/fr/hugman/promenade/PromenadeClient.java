package fr.hugman.promenade;

import fr.hugman.promenade.client.PromenadeEntityModelLayers;
import fr.hugman.promenade.content.*;
import fr.hugman.promenade.entity.render.DuckEntityRenderer;
import fr.hugman.promenade.entity.render.LushCreeperEntityRenderer;
import fr.hugman.promenade.entity.render.SunkenSkeletonEntityRenderer;
import fr.hugman.promenade.particle.FloatingParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;

@Environment(EnvType.CLIENT)
public class PromenadeClient implements ClientModInitializer {
	private static final int SAP_MAPLE_COLOR = 10931465;

	@Override
	public void onInitializeClient() {
		PromenadeEntityModelLayers.init();
		PromenadeClient.registerRenderLayers();
		PromenadeClient.registerBlockColors();
		PromenadeClient.registerItemColors();
		PromenadeClient.registerEntityRenderers();

		ParticleFactoryRegistry.getInstance().register(CherryContent.PINK_CHERRY_BLOSSOM, FloatingParticle.CherryBlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(CherryContent.WHITE_CHERRY_BLOSSOM, FloatingParticle.CherryBlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(MapleContent.MAPLE_LEAF, FloatingParticle.MapleLeafFactory::new);
	}

	public static void registerRenderLayers() {
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.OAK_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.SPRUCE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.BIRCH_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.JUNGLE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.ACACIA_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.DARK_OAK_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.MANGROVE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.AZALEA_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.FLOWERING_AZALEA_LEAF_PILE, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.DANDELION_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.POPPY_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.BLUE_ORCHID_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.ALLIUM_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.AZURE_BLUET_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.RED_TULIP_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.ORANGE_TULIP_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.WHITE_TULIP_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.PINK_TULIP_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.OXEYE_DAISY_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.CORNFLOWER_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.LILY_OF_THE_VALLEY_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(VanillaPilesContent.WITHER_ROSE_PILE, RenderLayer.getCutoutMipped());



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

		BlockRenderLayerMap.INSTANCE.putBlock(FoodContent.BLUEBERRY_BUSH, RenderLayer.getCutout());
	}

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor(), PalmContent.PALM_LEAVES, PalmContent.PALM_LEAF_PILE, VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE, VanillaPilesContent.MANGROVE_LEAF_PILE, MapleContent.SAP_MAPLE_LEAVES, MapleContent.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getSpruceColor(), VanillaPilesContent.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getBirchColor(), VanillaPilesContent.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if(world == null || pos == null) {
				return GrassColors.getColor(0.5, 1.0);
			}
			return BiomeColors.getGrassColor(world, pos);
		}, MapleContent.VERMILION_CARPETED_GRASS_BLOCK, MapleContent.FULVOUS_CARPETED_GRASS_BLOCK, MapleContent.MIKADO_CARPETED_GRASS_BLOCK);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((stack, layer) -> GrassColors.getColor(0.5D, 1.0D), PalmContent.PALM_LEAVES, PalmContent.PALM_LEAF_PILE, VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.SPRUCE_LEAF_PILE, VanillaPilesContent.BIRCH_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> FoliageColors.getMangroveColor(), VanillaPilesContent.MANGROVE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> SAP_MAPLE_COLOR, MapleContent.SAP_MAPLE_LEAVES, MapleContent.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleContent.VERMILION_CARPETED_GRASS_BLOCK).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleContent.VERMILION_CARPETED_GRASS_BLOCK);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleContent.FULVOUS_CARPETED_GRASS_BLOCK).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleContent.FULVOUS_CARPETED_GRASS_BLOCK);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleContent.MIKADO_CARPETED_GRASS_BLOCK).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleContent.MIKADO_CARPETED_GRASS_BLOCK);
	}

	public static void registerEntityRenderers() {
		EntityRendererRegistry.register(AnimalContent.DUCK, DuckEntityRenderer::new);
		EntityRendererRegistry.register(MonsterContent.LUSH_CREEPER, LushCreeperEntityRenderer::new);
		EntityRendererRegistry.register(MonsterContent.SUNKEN_SKELETON, SunkenSkeletonEntityRenderer::new);
	}
}
