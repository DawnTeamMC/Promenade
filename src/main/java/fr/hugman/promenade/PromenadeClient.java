package fr.hugman.promenade;

import fr.hugman.dawn.client.ClientRegistrar;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.boat.PromenadeBoatTypes;
import fr.hugman.promenade.client.render.entity.CapybaraRenderer;
import fr.hugman.promenade.client.render.entity.DuckRenderer;
import fr.hugman.promenade.client.render.entity.LushCreeperRenderer;
import fr.hugman.promenade.client.render.entity.SunkenSkeletonRenderer;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.particle.FallingLeafParticle;
import fr.hugman.promenade.particle.PromenadeParticleTypes;
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

        ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM, FallingLeafParticle.BlossomFactory::new);
        ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM, FallingLeafParticle.BlossomFactory::new);

        ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.MIKADO_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);
        ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.FULVOUS_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);
        ParticleFactoryRegistry.getInstance().register(PromenadeParticleTypes.VERMILION_MAPLE_LEAF, FallingLeafParticle.MapleLeafFactory::new);

        ClientRegistrar.add(PromenadeBoatTypes.SAKURA);
        ClientRegistrar.add(PromenadeBoatTypes.MAPLE);
        ClientRegistrar.add(PromenadeBoatTypes.PALM);
    }

    public static void registerRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.OAK_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.SPRUCE_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.BIRCH_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.JUNGLE_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.ACACIA_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.DARK_OAK_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.MANGROVE_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.AZALEA_LEAF_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.DANDELION_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POPPY_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.BLUE_ORCHID_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.ALLIUM_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.AZURE_BLUET_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.RED_TULIP_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.ORANGE_TULIP_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.WHITE_TULIP_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.PINK_TULIP_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.OXEYE_DAISY_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.CORNFLOWER_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.LILY_OF_THE_VALLEY_PILE, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.WITHER_ROSE_PILE, RenderLayer.getCutoutMipped());


        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.SAKURA_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.SAKURA_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.BLUSH_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.COTTON_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, RenderLayer.getCutoutMipped());


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


        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.PALM_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.PALM_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.PALM_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_PALM_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.PALM_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.PALM_HANGING_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.PALM_LEAF_PILE, RenderLayer.getCutout());


        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.DARK_AMARANTH_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.DARK_AMARANTH_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.DARK_AMARANTH_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.DARK_AMARANTH_FUNGUS, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(PromenadeBlocks.BLUEBERRY_BUSH, RenderLayer.getCutout());
    }

    private static final int SAP_MAPLE_COLOR = 10931465; // 'foliage_color' in 'carnelian_treeway.json'
    private static final int PALM_COLOR = 8237614;

    private static void registerBlockColors() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> PALM_COLOR, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.MANGROVE_LEAF_PILE, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getSpruceColor(), PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getBirchColor(), PromenadeBlocks.BIRCH_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return GrassColors.getColor(0.5, 1.0);
            }
            return BiomeColors.getGrassColor(world, pos);
        }, PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK);
    }

    private static void registerItemColors() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> PALM_COLOR, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            BlockState blockState = block.getDefaultState();
            return ColorProviderRegistry.BLOCK.get(block).getColor(blockState, null, null, tintIndex);
        }, PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getMangroveColor(), PromenadeBlocks.MANGROVE_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> SAP_MAPLE_COLOR, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE);
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(PromenadeEntityTypes.CAPYBARA, CapybaraRenderer::new);
        EntityRendererRegistry.register(PromenadeEntityTypes.DUCK, DuckRenderer::new);
        EntityRendererRegistry.register(PromenadeEntityTypes.LUSH_CREEPER, LushCreeperRenderer::new);
        EntityRendererRegistry.register(PromenadeEntityTypes.SUNKEN, SunkenSkeletonRenderer::new);
    }
}
