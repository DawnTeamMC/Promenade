package fr.hugman.promenade.client.render.block;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

/**
 * 1.21.1 renders every block as solid unless told otherwise, rather than reading the layer from its textures.
 * Blocks extending {@link net.minecraft.world.level.block.LeavesBlock} are the exception: the game already switches them with the graphics setting.
 */
public final class PromenadeBlockRenderLayers {
    public static void register(Registrar registrar) {
        // Palm leaves are not LeavesBlocks, so they get the layer vanilla leaves use with fancy graphics
        registrar.register(RenderType.cutoutMipped(),
                PromenadeBlocks.PALM_LEAVES,
                PromenadeBlocks.SNOWY_PALM_LEAVES
        );

        registrar.register(RenderType.cutout(),
                PromenadeBlocks.OAK_LEAF_PILE,
                PromenadeBlocks.SPRUCE_LEAF_PILE,
                PromenadeBlocks.BIRCH_LEAF_PILE,
                PromenadeBlocks.JUNGLE_LEAF_PILE,
                PromenadeBlocks.ACACIA_LEAF_PILE,
                PromenadeBlocks.CHERRY_LEAF_PILE,
                PromenadeBlocks.DARK_OAK_LEAF_PILE,
                PromenadeBlocks.MANGROVE_LEAF_PILE,
                PromenadeBlocks.AZALEA_LEAF_PILE,
                PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE,
                PromenadeBlocks.DANDELION_PILE,
                PromenadeBlocks.POPPY_PILE,
                PromenadeBlocks.BLUE_ORCHID_PILE,
                PromenadeBlocks.ALLIUM_PILE,
                PromenadeBlocks.AZURE_BLUET_PILE,
                PromenadeBlocks.RED_TULIP_PILE,
                PromenadeBlocks.ORANGE_TULIP_PILE,
                PromenadeBlocks.WHITE_TULIP_PILE,
                PromenadeBlocks.PINK_TULIP_PILE,
                PromenadeBlocks.OXEYE_DAISY_PILE,
                PromenadeBlocks.CORNFLOWER_PILE,
                PromenadeBlocks.LILY_OF_THE_VALLEY_PILE,
                PromenadeBlocks.WITHER_ROSE_PILE,

                PromenadeBlocks.SAKURA_DOOR,
                PromenadeBlocks.SAKURA_TRAPDOOR,
                PromenadeBlocks.BLUSH_SAKURA_SAPLING,
                PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING,
                PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE,
                PromenadeBlocks.COTTON_SAKURA_SAPLING,
                PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING,
                PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE,

                PromenadeBlocks.MAPLE_DOOR,
                PromenadeBlocks.MAPLE_TRAPDOOR,
                PromenadeBlocks.SAP_MAPLE_SAPLING,
                PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING,
                PromenadeBlocks.SAP_MAPLE_LEAF_PILE,
                PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES,
                PromenadeBlocks.VERMILION_MAPLE_SAPLING,
                PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING,
                PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE,
                PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES,
                PromenadeBlocks.FULVOUS_MAPLE_SAPLING,
                PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING,
                PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE,
                PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES,
                PromenadeBlocks.MIKADO_MAPLE_SAPLING,
                PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING,
                PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE,
                PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES,

                PromenadeBlocks.PALM_DOOR,
                PromenadeBlocks.PALM_TRAPDOOR,
                PromenadeBlocks.PALM_SAPLING,
                PromenadeBlocks.POTTED_PALM_SAPLING,
                PromenadeBlocks.PALM_HANGING_LEAVES,
                PromenadeBlocks.PALM_LEAF_PILE,

                PromenadeBlocks.DARK_AMARANTH_DOOR,
                PromenadeBlocks.DARK_AMARANTH_TRAPDOOR,
                PromenadeBlocks.DARK_AMARANTH_ROOTS,
                PromenadeBlocks.POTTED_DARK_AMARANTH_ROOTS,
                PromenadeBlocks.DARK_AMARANTH_FUNGUS,
                PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS,

                PromenadeBlocks.COILED_VINES,
                PromenadeBlocks.COILED_VINES_PLANT,
                PromenadeBlocks.BLUEBERRY_BUSH
        );
    }

    @FunctionalInterface
    public interface Registrar {
        void register(RenderType renderType, Block... blocks);
    }
}
