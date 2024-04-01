package fr.hugman.promenade;

import fr.hugman.dawn.client.ClientRegistrar;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.client.render.entity.CapybaraRenderer;
import fr.hugman.promenade.client.render.entity.DuckRenderer;
import fr.hugman.promenade.client.render.entity.LushCreeperRenderer;
import fr.hugman.promenade.client.render.entity.SunkenSkeletonRenderer;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.particle.FallingLeafParticle;
import fr.hugman.promenade.particle.PromenadeParticleTypes;
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
	@Override
	public void onInitializeClient() {
		PromenadeEntityModelLayers.init();
		PromenadeClient.registerRenderLayers();
		PromenadeClient.registerBlockColors();
		PromenadeClient.registerItemColors();
		PromenadeClient.registerEntityRenderers();

		ParticleFactoryRegistry.getInstance().register(SakuraContent.BLUSH_SAKURA_BLOSSOM, FallingLeafParticle.BlossomFactory::new);
		ParticleFactoryRegistry.getInstance().register(SakuraContent.COTTON_SAKURA_BLOSSOM, FallingLeafParticle.BlossomFactory::new);

		ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.MIKADO_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);
		ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.FULVOUS_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);
		ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.VERMILION_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);

		ClientRegistrar.add(SakuraContent.SAKURA_BOAT_TYPE);
		ClientRegistrar.add(MapleContent.MAPLE_BOAT_TYPE);
		ClientRegistrar.add(TropicalContent.PALM_BOAT_TYPE);
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

		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.BLUSH_SAKURA_BLOSSOMS, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.BLUSH_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.POTTED_BLUSH_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.BLUSH_SAKURA_BLOSSOM_PILE, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.COTTON_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.POTTED_COTTON_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.COTTON_SAKURA_BLOSSOMS, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(SakuraContent.COTTON_SAKURA_BLOSSOM_PILE, RenderLayer.getCutoutMipped());


		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.MAPLE_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.MAPLE_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.VERMILION_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.VERMILION_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.FULVOUS_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.MIKADO_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.MIKADO_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.SAP_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.SAP_MAPLE_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, RenderLayer.getCutoutMipped());


		BlockRenderLayerMap.INSTANCE.putBlock(TropicalContent.PALM_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TropicalContent.PALM_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(TropicalContent.PALM_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TropicalContent.POTTED_PALM_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TropicalContent.PALM_LEAVES, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(TropicalContent.PALM_HANGING_LEAVES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TropicalContent.PALM_LEAF_PILE, RenderLayer.getCutout());


		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_TRAPDOOR, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_ROOTS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.DARK_AMARANTH_FUNGUS, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(AmaranthContent.POTTED_DARK_AMARANTH_FUNGUS, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlock(FoodContent.BLUEBERRY_BUSH, RenderLayer.getCutout());
	}

	private static final int SAP_MAPLE_COLOR = 10931465; // 'foliage_color' in 'carnelian_treeway.json'
	private static final int PALM_COLOR = 8237614;

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> PALM_COLOR, TropicalContent.PALM_LEAVES, TropicalContent.PALM_HANGING_LEAVES, TropicalContent.PALM_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE, VanillaPilesContent.MANGROVE_LEAF_PILE, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getSpruceColor(), VanillaPilesContent.SPRUCE_LEAF_PILE, GlaglaglaContent.SNOWY_SPRUCE_LEAVES);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getBirchColor(), VanillaPilesContent.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if(world == null || pos == null) {
				return GrassColors.getColor(0.5, 1.0);
			}
			return BiomeColors.getGrassColor(world, pos);
		}, PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> PALM_COLOR, TropicalContent.PALM_LEAVES, TropicalContent.PALM_HANGING_LEAVES, TropicalContent.PALM_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			Block block = ((BlockItem) stack.getItem()).getBlock();
			BlockState blockState = block.getDefaultState();
			return ColorProviderRegistry.BLOCK.get(block).getColor(blockState, null, null, tintIndex);
		}, VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.SPRUCE_LEAF_PILE, VanillaPilesContent.BIRCH_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE, PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK, GlaglaglaContent.SNOWY_SPRUCE_LEAVES);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getMangroveColor(), VanillaPilesContent.MANGROVE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> SAP_MAPLE_COLOR, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE);
	}

	public static void registerEntityRenderers() {
		EntityRendererRegistry.register(AnimalContent.CAPYBARA, CapybaraRenderer::new);
		EntityRendererRegistry.register(AnimalContent.DUCK, DuckRenderer::new);
		EntityRendererRegistry.register(MonsterContent.LUSH_CREEPER, LushCreeperRenderer::new);
		EntityRendererRegistry.register(MonsterContent.SUNKEN_SKELETON, SunkenSkeletonRenderer::new);
	}
}
