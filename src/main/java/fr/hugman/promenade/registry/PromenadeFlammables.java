package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class PromenadeFlammables {
    public static void register() {
        var fire = FlammableBlockRegistry.getDefaultInstance();

        fire.add(PromenadeBlocks.OAK_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.SPRUCE_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.BIRCH_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.JUNGLE_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.ACACIA_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.CHERRY_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.DARK_OAK_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.PALE_OAK_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.MANGROVE_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.AZALEA_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE, 30, 60);

        fire.add(PromenadeBlocks.DANDELION_PILE, 60, 100);
        fire.add(PromenadeBlocks.POPPY_PILE, 60, 100);
        fire.add(PromenadeBlocks.BLUE_ORCHID_PILE, 60, 100);
        fire.add(PromenadeBlocks.ALLIUM_PILE, 60, 100);
        fire.add(PromenadeBlocks.AZURE_BLUET_PILE, 60, 100);
        fire.add(PromenadeBlocks.RED_TULIP_PILE, 60, 100);
        fire.add(PromenadeBlocks.ORANGE_TULIP_PILE, 60, 100);
        fire.add(PromenadeBlocks.WHITE_TULIP_PILE, 60, 100);
        fire.add(PromenadeBlocks.PINK_TULIP_PILE, 60, 100);
        fire.add(PromenadeBlocks.OXEYE_DAISY_PILE, 60, 100);
        fire.add(PromenadeBlocks.CORNFLOWER_PILE, 60, 100);
        fire.add(PromenadeBlocks.LILY_OF_THE_VALLEY_PILE, 60, 100);
        fire.add(PromenadeBlocks.WITHER_ROSE_PILE, 60, 100);

        fire.add(PromenadeBlocks.SNOWY_OAK_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_SPRUCE_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_BIRCH_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_JUNGLE_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_ACACIA_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_CHERRY_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_PALE_OAK_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_MANGROVE_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_AZALEA_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES, 30, 60);

        fire.add(PromenadeBlocks.SAKURA_LOG, 5, 5);
        fire.add(PromenadeBlocks.STRIPPED_SAKURA_LOG, 5, 5);
        fire.add(PromenadeBlocks.SAKURA_WOOD, 5, 5);
        fire.add(PromenadeBlocks.STRIPPED_SAKURA_WOOD, 5, 5);
        fire.add(PromenadeBlocks.SAKURA_PLANKS, 5, 20);
        fire.add(PromenadeBlocks.SAKURA_STAIRS, 5, 20);
        fire.add(PromenadeBlocks.SAKURA_SLAB, 5, 20);
        fire.add(PromenadeBlocks.SAKURA_FENCE, 5, 20);
        fire.add(PromenadeBlocks.SAKURA_FENCE_GATE, 5, 20);
        fire.add(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, 30, 60);
        fire.add(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, 30, 60);
        fire.add(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, 30, 60);
        fire.add(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, 30, 60);

        fire.add(PromenadeBlocks.MAPLE_LOG, 5, 5);
        fire.add(PromenadeBlocks.STRIPPED_MAPLE_LOG, 5, 5);
        fire.add(PromenadeBlocks.MAPLE_WOOD, 5, 5);
        fire.add(PromenadeBlocks.STRIPPED_MAPLE_WOOD, 5, 5);
        fire.add(PromenadeBlocks.MAPLE_PLANKS, 5, 20);
        fire.add(PromenadeBlocks.MAPLE_STAIRS, 5, 20);
        fire.add(PromenadeBlocks.MAPLE_SLAB, 5, 20);
        fire.add(PromenadeBlocks.MAPLE_FENCE, 5, 20);
        fire.add(PromenadeBlocks.MAPLE_FENCE_GATE, 5, 20);
        fire.add(PromenadeBlocks.SAP_MAPLE_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.VERMILION_MAPLE_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, 30, 60);
        fire.add(PromenadeBlocks.MIKADO_MAPLE_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, 30, 60);

        fire.add(PromenadeBlocks.PALM_LOG, 5, 5);
        fire.add(PromenadeBlocks.STRIPPED_PALM_LOG, 5, 5);
        fire.add(PromenadeBlocks.PALM_WOOD, 5, 5);
        fire.add(PromenadeBlocks.STRIPPED_PALM_WOOD, 5, 5);
        fire.add(PromenadeBlocks.PALM_PLANKS, 5, 20);
        fire.add(PromenadeBlocks.PALM_STAIRS, 5, 20);
        fire.add(PromenadeBlocks.PALM_SLAB, 5, 20);
        fire.add(PromenadeBlocks.PALM_FENCE, 5, 20);
        fire.add(PromenadeBlocks.PALM_FENCE_GATE, 5, 20);
        fire.add(PromenadeBlocks.PALM_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.PALM_HANGING_LEAVES, 30, 60);
        fire.add(PromenadeBlocks.PALM_LEAF_PILE, 30, 60);

        fire.add(PromenadeBlocks.BLUEBERRY_BUSH, 60, 100);
    }
}
