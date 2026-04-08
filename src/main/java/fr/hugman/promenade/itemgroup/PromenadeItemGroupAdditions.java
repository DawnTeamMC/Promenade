package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class PromenadeItemGroupAdditions {
    public static void appendItemGroups() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> e.insertAfter(Blocks.ANDESITE, PromenadeBlocks.BLUNITE, PromenadeBlocks.ASPHALT));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(e -> e.insertAfter(Blocks.POLISHED_ANDESITE_SLAB,
                PromenadeBlocks.BLUNITE, PromenadeBlocks.BLUNITE_SLAB, PromenadeBlocks.BLUNITE_STAIRS, PromenadeBlocks.BLUNITE_WALL,
                PromenadeBlocks.POLISHED_BLUNITE, PromenadeBlocks.POLISHED_BLUNITE_SLAB, PromenadeBlocks.POLISHED_BLUNITE_STAIRS,
                PromenadeBlocks.ASPHALT, PromenadeBlocks.ASPHALT_SLAB, PromenadeBlocks.ASPHALT_STAIRS, PromenadeBlocks.ASPHALT_WALL,
                PromenadeBlocks.POLISHED_ASPHALT, PromenadeBlocks.POLISHED_ASPHALT_SLAB, PromenadeBlocks.POLISHED_ASPHALT_STAIRS
        ));


        // VANILLA PILES
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> {
            e.insertAfter(PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES,
                    PromenadeBlocks.OAK_LEAF_PILE,
                    PromenadeBlocks.SPRUCE_LEAF_PILE,
                    PromenadeBlocks.BIRCH_LEAF_PILE,
                    PromenadeBlocks.JUNGLE_LEAF_PILE,
                    PromenadeBlocks.ACACIA_LEAF_PILE,
                    PromenadeBlocks.CHERRY_LEAF_PILE,
                    PromenadeBlocks.DARK_OAK_LEAF_PILE,
                    PromenadeBlocks.PALE_OAK_LEAF_PILE,
                    PromenadeBlocks.MANGROVE_LEAF_PILE,
                    PromenadeBlocks.AZALEA_LEAF_PILE,
                    PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE
            );

            e.insertAfter(Blocks.LILY_OF_THE_VALLEY,
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
                    PromenadeBlocks.LILY_OF_THE_VALLEY_PILE);
            e.insertAfter(Blocks.WITHER_ROSE, PromenadeBlocks.WITHER_ROSE_PILE);
        });

        // VANILLA SNOWY LEAVES

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> {
            e.insertAfter(Blocks.OAK_LEAVES, PromenadeBlocks.SNOWY_OAK_LEAVES);
            e.insertAfter(Blocks.SPRUCE_LEAVES, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
            e.insertAfter(Blocks.BIRCH_LEAVES, PromenadeBlocks.SNOWY_BIRCH_LEAVES);
            e.insertAfter(Blocks.JUNGLE_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES);
            e.insertAfter(Blocks.ACACIA_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES);
            e.insertAfter(Blocks.CHERRY_LEAVES, PromenadeBlocks.SNOWY_CHERRY_LEAVES);
            e.insertAfter(Blocks.DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES);
            e.insertAfter(Blocks.PALE_OAK_LEAVES, PromenadeBlocks.SNOWY_PALE_OAK_LEAVES);
            e.insertAfter(Blocks.MANGROVE_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES);
            e.insertAfter(Blocks.AZALEA_LEAVES, PromenadeBlocks.SNOWY_AZALEA_LEAVES);
            e.insertAfter(Blocks.FLOWERING_AZALEA_LEAVES, PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES);
        });

        // SAKURA
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(e -> e.insertAfter(Blocks.BIRCH_BUTTON,
                PromenadeBlocks.SAKURA_LOG,
                PromenadeBlocks.SAKURA_WOOD,
                PromenadeBlocks.STRIPPED_SAKURA_LOG,
                PromenadeBlocks.STRIPPED_SAKURA_WOOD,
                PromenadeBlocks.SAKURA_PLANKS,
                PromenadeBlocks.SAKURA_STAIRS,
                PromenadeBlocks.SAKURA_SLAB,
                PromenadeBlocks.SAKURA_FENCE,
                PromenadeBlocks.SAKURA_FENCE_GATE,
                PromenadeBlocks.SAKURA_DOOR,
                PromenadeBlocks.SAKURA_TRAPDOOR,
                PromenadeBlocks.SAKURA_PRESSURE_PLATE,
                PromenadeBlocks.SAKURA_BUTTON
        ));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> {
            e.insertAfter(Blocks.BIRCH_LOG, PromenadeBlocks.SAKURA_LOG);
            e.insertAfter(PromenadeBlocks.SNOWY_BIRCH_LEAVES,
                    PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS,
                    PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS
            );
            e.insertAfter(Blocks.BIRCH_SAPLING, PromenadeBlocks.BLUSH_SAKURA_SAPLING, PromenadeBlocks.COTTON_SAKURA_SAPLING);
            e.insertAfter(PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE);
        });

        // MAPLE
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(e ->
                e.insertAfter(PromenadeBlocks.SAKURA_BUTTON,
                        PromenadeBlocks.MAPLE_LOG,
                        PromenadeBlocks.MAPLE_WOOD,
                        PromenadeBlocks.STRIPPED_MAPLE_LOG,
                        PromenadeBlocks.STRIPPED_MAPLE_WOOD,
                        PromenadeBlocks.MAPLE_PLANKS,
                        PromenadeBlocks.MAPLE_STAIRS,
                        PromenadeBlocks.MAPLE_SLAB,
                        PromenadeBlocks.MAPLE_FENCE,
                        PromenadeBlocks.MAPLE_FENCE_GATE,
                        PromenadeBlocks.MAPLE_DOOR,
                        PromenadeBlocks.MAPLE_TRAPDOOR,
                        PromenadeBlocks.MAPLE_PRESSURE_PLATE,
                        PromenadeBlocks.MAPLE_BUTTON));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> {
            e.insertAfter(PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.MAPLE_LOG);
            e.insertAfter(PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS,
                    PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES,
                    PromenadeBlocks.VERMILION_MAPLE_LEAVES, PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES,
                    PromenadeBlocks.FULVOUS_MAPLE_LEAVES, PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES,
                    PromenadeBlocks.MIKADO_MAPLE_LEAVES, PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES
            );
            e.insertAfter(PromenadeBlocks.COTTON_SAKURA_SAPLING, PromenadeBlocks.SAP_MAPLE_SAPLING, PromenadeBlocks.VERMILION_MAPLE_SAPLING, PromenadeBlocks.FULVOUS_MAPLE_SAPLING, PromenadeBlocks.MIKADO_MAPLE_SAPLING);
            e.insertAfter(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE);
            e.insertAfter(Blocks.FLOWERING_AZALEA_LEAVES,
                    PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES,
                    PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES,
                    PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES,
                    PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES
            );
        });

        // PALM
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(e -> {
            e.insertAfter(Blocks.ACACIA_BUTTON,
                    PromenadeBlocks.PALM_LOG,
                    PromenadeBlocks.PALM_WOOD,
                    PromenadeBlocks.STRIPPED_PALM_LOG,
                    PromenadeBlocks.STRIPPED_PALM_WOOD,
                    PromenadeBlocks.PALM_PLANKS,
                    PromenadeBlocks.PALM_STAIRS,
                    PromenadeBlocks.PALM_SLAB,
                    PromenadeBlocks.PALM_FENCE,
                    PromenadeBlocks.PALM_FENCE_GATE,
                    PromenadeBlocks.PALM_DOOR,
                    PromenadeBlocks.PALM_TRAPDOOR,
                    PromenadeBlocks.PALM_PRESSURE_PLATE,
                    PromenadeBlocks.PALM_BUTTON);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> {
            e.insertAfter(Blocks.ACACIA_LOG, PromenadeBlocks.PALM_LOG);
            e.insertAfter(PromenadeBlocks.SNOWY_ACACIA_LEAVES, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES);
            e.insertAfter(Blocks.ACACIA_SAPLING, PromenadeBlocks.PALM_SAPLING);
            e.insertAfter(PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.PALM_LEAF_PILE);
        });

        // AMARANTH
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(e -> e.insertAfter(Blocks.WARPED_BUTTON,
                PromenadeBlocks.DARK_AMARANTH_STEM,
                PromenadeBlocks.DARK_AMARANTH_HYPHAE,
                PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM,
                PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE,
                PromenadeBlocks.DARK_AMARANTH_PLANKS,
                PromenadeBlocks.DARK_AMARANTH_STAIRS,
                PromenadeBlocks.DARK_AMARANTH_SLAB,
                PromenadeBlocks.DARK_AMARANTH_FENCE,
                PromenadeBlocks.DARK_AMARANTH_FENCE_GATE,
                PromenadeBlocks.DARK_AMARANTH_DOOR,
                PromenadeBlocks.DARK_AMARANTH_TRAPDOOR,
                PromenadeBlocks.DARK_AMARANTH_PRESSURE_PLATE,
                PromenadeBlocks.DARK_AMARANTH_BUTTON));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(e -> {
            e.insertAfter(Blocks.WARPED_NYLIUM, PromenadeBlocks.DARK_AMARANTH_NYLIUM);
            e.insertAfter(Blocks.WARPED_STEM, PromenadeBlocks.DARK_AMARANTH_STEM);
            e.insertAfter(Blocks.WARPED_WART_BLOCK, PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
            e.insertAfter(Blocks.WARPED_FUNGUS, PromenadeBlocks.DARK_AMARANTH_FUNGUS);
            e.insertAfter(Blocks.WARPED_ROOTS, PromenadeBlocks.DARK_AMARANTH_ROOTS);
            e.insertAfter(Blocks.TWISTING_VINES, PromenadeBlocks.COILED_VINES);
            e.insertAfter(Blocks.SHROOMLIGHT, PromenadeBlocks.SOUL_SHROOMLIGHT);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> {
            e.insertAfter(Blocks.SHROOMLIGHT, PromenadeBlocks.SOUL_SHROOMLIGHT);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(e -> e.insertAfter(Blocks.CUT_RED_SANDSTONE_SLAB, PromenadeBlocks.MOAI));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(Blocks.BIRCH_HANGING_SIGN, PromenadeItems.SAKURA_SIGN, PromenadeItems.SAKURA_HANGING_SIGN));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(Blocks.BIRCH_SHELF, PromenadeBlocks.SAKURA_SHELF));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(e -> e.insertAfter(Items.BIRCH_CHEST_BOAT, PromenadeItems.SAKURA_BOAT, PromenadeItems.SAKURA_CHEST_BOAT));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(PromenadeItems.SAKURA_HANGING_SIGN, PromenadeItems.MAPLE_SIGN, PromenadeItems.MAPLE_HANGING_SIGN));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(PromenadeBlocks.SAKURA_SHELF, PromenadeBlocks.MAPLE_SHELF));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(e -> e.insertAfter(PromenadeItems.SAKURA_CHEST_BOAT, PromenadeItems.MAPLE_BOAT, PromenadeItems.MAPLE_CHEST_BOAT));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(e -> e.insertAfter(Items.HONEY_BOTTLE, PromenadeItems.MAPLE_SYRUP_BOTTLE));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(Blocks.ACACIA_HANGING_SIGN, PromenadeItems.PALM_SIGN, PromenadeItems.PALM_HANGING_SIGN));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(Blocks.ACACIA_SHELF, PromenadeBlocks.PALM_SHELF));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(e -> e.insertAfter(Items.ACACIA_CHEST_BOAT, PromenadeItems.PALM_BOAT, PromenadeItems.PALM_CHEST_BOAT));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(Blocks.WARPED_HANGING_SIGN, PromenadeItems.DARK_AMARANTH_SIGN, PromenadeItems.DARK_AMARANTH_HANGING_SIGN));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(e -> e.insertAfter(Blocks.WARPED_SHELF, PromenadeBlocks.DARK_AMARANTH_SHELF));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(e -> {
            e.insertAfter(Items.SWEET_BERRIES, PromenadeItems.BLUEBERRIES);
            e.insertAfter(Items.ENCHANTED_GOLDEN_APPLE, PromenadeItems.BANANA);
            //TODO apricot and mango
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(e -> e.insertAfter(Items.COOKED_CHICKEN, PromenadeItems.DUCK, PromenadeItems.COOKED_DUCK));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(e -> e.insertAfter(Items.FLOWER_BANNER_PATTERN, PromenadeItems.BOVINE_BANNER_PATTERN));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(e -> e.insertAfter(Items.CHICKEN_SPAWN_EGG, PromenadeItems.DUCK_SPAWN_EGG));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(e -> e.insertAfter(Items.PIG_SPAWN_EGG, PromenadeItems.CAPYBARA_SPAWN_EGG));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(e -> e.insertAfter(Items.CREEPER_SPAWN_EGG, PromenadeItems.LUSH_CREEPER_SPAWN_EGG));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(e -> e.insertAfter(Items.DROWNED_SPAWN_EGG, PromenadeItems.SUNKEN_SPAWN_EGG));
    }
}
