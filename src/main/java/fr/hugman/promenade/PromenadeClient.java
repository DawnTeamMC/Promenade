package fr.hugman.promenade;

import fr.hugman.dawn.client.ClientRegistrar;
import fr.hugman.promenade.client.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.CapybaraRenderer;
import fr.hugman.promenade.client.render.entity.DuckRenderer;
import fr.hugman.promenade.client.render.entity.LushCreeperRenderer;
import fr.hugman.promenade.client.render.entity.SunkenSkeletonRenderer;
import fr.hugman.promenade.particle.FloatingParticle;
import fr.hugman.promenade.registry.content.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;

@Environment(EnvType.CLIENT)
public class PromenadeClient implements ClientModInitializer {
	private static final int SAP_MAPLE_COLOR = 10931465; // 'foliage_color' in 'carnelian_treeway.json'

	@Override
	public void onInitializeClient() {
		PromenadeEntityModelLayers.init();
		PromenadeClient.registerRenderLayers();
		PromenadeClient.registerBlockColors();
		PromenadeClient.registerItemColors();
		PromenadeClient.registerEntityRenderers();

		ParticleFactoryRegistry.getInstance().register(SakuraContent.BLUSH_SAKURA_BLOSSOM, FloatingParticle.BlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(SakuraContent.COTTON_SAKURA_BLOSSOM, FloatingParticle.BlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(MapleContent.MAPLE_LEAF, FloatingParticle.MapleLeafFactory::new);

		ClientRegistrar.add(SakuraContent.SAKURA_SIGNS);
		ClientRegistrar.add(SakuraContent.SAKURA_BOAT_TYPE);

		ClientRegistrar.add(MapleContent.MAPLE_SIGNS);
		ClientRegistrar.add(MapleContent.MAPLE_BOAT_TYPE);

		ClientRegistrar.add(PalmContent.PALM_SIGNS);
		ClientRegistrar.add(PalmContent.PALM_BOAT_TYPE);

		ClientRegistrar.add(AmaranthContent.DARK_AMARANTH_SIGNS);
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


		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.SAKURA_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.SAKURA_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.BLUSH_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.POTTED_BLUSH_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.BLUSH_SAKURA_BLOSSOM_PILE, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.COTTON_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.POTTED_COTTON_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.COTTON_SAKURA_BLOSSOM_PILE, RenderLayer.getCutoutMipped());


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


		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.POTTED_PALM_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PalmContent.PALM_LEAF_PILE, RenderLayer.getCutout());


		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_ROOTS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_FUNGUS, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.POTTED_DARK_AMARANTH_FUNGUS, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(FoodContent.BLUEBERRY_BUSH, RenderLayer.getCutout());
	}

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), PalmContent.PALM_LEAVES, PalmContent.PALM_LEAF_PILE, VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE, VanillaPilesContent.MANGROVE_LEAF_PILE, MapleContent.SAP_MAPLE_LEAVES, MapleContent.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getSpruceColor(), VanillaPilesContent.SPRUCE_LEAF_PILE, GlaglaglaContent.SNOWY_SPRUCE_LEAVES);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getBirchColor(), VanillaPilesContent.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if(world == null || pos == null) {
				return GrassColors.getColor(0.5, 1.0);
			}
			return BiomeColors.getGrassColor(world, pos);
		}, MapleContent.VERMILION_CARPETED_GRASS_BLOCK, MapleContent.FULVOUS_CARPETED_GRASS_BLOCK, MapleContent.MIKADO_CARPETED_GRASS_BLOCK);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getColor(1.0D, 0.0D), PalmContent.PALM_LEAVES, PalmContent.PALM_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			Block block = ((BlockItem) stack.getItem()).getBlock();
			BlockState blockState = block.getDefaultState();
			return ColorProviderRegistry.BLOCK.get(block).getColor(blockState, null, null, tintIndex);
		}, VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.SPRUCE_LEAF_PILE, VanillaPilesContent.BIRCH_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE, MapleContent.VERMILION_CARPETED_GRASS_BLOCK, MapleContent.FULVOUS_CARPETED_GRASS_BLOCK, MapleContent.MIKADO_CARPETED_GRASS_BLOCK, GlaglaglaContent.SNOWY_SPRUCE_LEAVES);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getMangroveColor(), VanillaPilesContent.MANGROVE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> SAP_MAPLE_COLOR, MapleContent.SAP_MAPLE_LEAVES, MapleContent.SAP_MAPLE_LEAF_PILE);
	}

	public static void registerEntityRenderers() {
		EntityRendererRegistry.register(AnimalContent.CAPYBARA, CapybaraRenderer::new);
		EntityRendererRegistry.register(AnimalContent.DUCK, DuckRenderer::new);
		EntityRendererRegistry.register(MonsterContent.LUSH_CREEPER, LushCreeperRenderer::new);
		EntityRendererRegistry.register(MonsterContent.SUNKEN_SKELETON, SunkenSkeletonRenderer::new);
	}
}
