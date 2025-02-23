package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;

import java.util.Collections;
import java.util.function.Predicate;

public class PromenadeItemGroupAdditions {
    public static void appendItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(e -> e.addAfter(Blocks.ANDESITE, PromenadeBlocks.BLUNITE, PromenadeBlocks.ASPHALT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(e -> e.addAfter(Blocks.POLISHED_ANDESITE_SLAB,
                PromenadeBlocks.BLUNITE, PromenadeBlocks.BLUNITE_SLAB, PromenadeBlocks.BLUNITE_STAIRS, PromenadeBlocks.BLUNITE_WALL,
                PromenadeBlocks.POLISHED_BLUNITE, PromenadeBlocks.POLISHED_BLUNITE_SLAB, PromenadeBlocks.POLISHED_BLUNITE_STAIRS,
                PromenadeBlocks.ASPHALT, PromenadeBlocks.ASPHALT_SLAB, PromenadeBlocks.ASPHALT_STAIRS, PromenadeBlocks.ASPHALT_WALL,
                PromenadeBlocks.POLISHED_ASPHALT, PromenadeBlocks.POLISHED_ASPHALT_SLAB, PromenadeBlocks.POLISHED_ASPHALT_STAIRS
        ));


        // VANILLA PILES
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(e -> {
            e.addAfter(Blocks.FLOWERING_AZALEA_LEAVES,
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

            e.addAfter(Blocks.LILY_OF_THE_VALLEY,
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
            e.addAfter(Blocks.WITHER_ROSE, PromenadeBlocks.WITHER_ROSE_PILE);
        });

        // VANILLA SNOWY LEAVES
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(e -> {
            e.addAfter(Blocks.OAK_LEAVES, PromenadeBlocks.SNOWY_OAK_LEAVES);
            e.addAfter(Blocks.SPRUCE_LEAVES, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
            e.addAfter(Blocks.BIRCH_LEAVES, PromenadeBlocks.SNOWY_BIRCH_LEAVES);
            e.addAfter(Blocks.JUNGLE_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES);
            e.addAfter(Blocks.ACACIA_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES);
            e.addAfter(Blocks.CHERRY_LEAVES, PromenadeBlocks.SNOWY_CHERRY_LEAVES);
            e.addAfter(Blocks.DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES);
            e.addAfter(Blocks.PALE_OAK_LEAVES, PromenadeBlocks.SNOWY_PALE_OAK_LEAVES);
            e.addAfter(Blocks.MANGROVE_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES);
            e.addAfter(Blocks.AZALEA_LEAVES, PromenadeBlocks.SNOWY_AZALEA_LEAVES);
            e.addAfter(Blocks.FLOWERING_AZALEA_LEAVES, PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES);
        });

        // SAKURA
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(e -> e.addAfter(Blocks.BIRCH_BUTTON,
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
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(e -> {
            e.addAfter(Blocks.BIRCH_LOG, PromenadeBlocks.SAKURA_LOG);
            e.addAfter(PromenadeBlocks.SNOWY_BIRCH_LEAVES,
                    PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS,
                    PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS
            );
            e.addAfter(Blocks.BIRCH_SAPLING, PromenadeBlocks.BLUSH_SAKURA_SAPLING, PromenadeBlocks.COTTON_SAKURA_SAPLING);
            e.addAfter(PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE);
        });

        // MAPLE
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(e ->
                e.addAfter(PromenadeBlocks.SAKURA_BUTTON,
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
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(e -> {
            e.addAfter(PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.MAPLE_LOG);
            e.addAfter(PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS,
                    PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES,
                    PromenadeBlocks.VERMILION_MAPLE_LEAVES, PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES,
                    PromenadeBlocks.FULVOUS_MAPLE_LEAVES, PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES,
                    PromenadeBlocks.MIKADO_MAPLE_LEAVES, PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES
            );
            e.addAfter(PromenadeBlocks.COTTON_SAKURA_SAPLING, PromenadeBlocks.SAP_MAPLE_SAPLING, PromenadeBlocks.VERMILION_MAPLE_SAPLING, PromenadeBlocks.FULVOUS_MAPLE_SAPLING, PromenadeBlocks.MIKADO_MAPLE_SAPLING);
            e.addAfter(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE);
        });

        // PALM
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(e -> {
            e.addAfter(Blocks.ACACIA_BUTTON,
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
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(e -> {
            e.addAfter(Blocks.ACACIA_LOG, PromenadeBlocks.PALM_LOG);
            e.addAfter(PromenadeBlocks.SNOWY_ACACIA_LEAVES, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES);
            e.addAfter(Blocks.ACACIA_SAPLING, PromenadeBlocks.PALM_SAPLING);
            e.addAfter(PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.PALM_LEAF_PILE);
        });

        // AMARANTH
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(e -> e.addAfter(Blocks.WARPED_BUTTON,
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

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(e -> {
            e.addAfter(Blocks.WARPED_NYLIUM, PromenadeBlocks.DARK_AMARANTH_NYLIUM);
            e.addAfter(Blocks.WARPED_STEM, PromenadeBlocks.DARK_AMARANTH_STEM);
            e.addAfter(Blocks.WARPED_WART_BLOCK, PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
            e.addAfter(Blocks.WARPED_FUNGUS, PromenadeBlocks.DARK_AMARANTH_FUNGUS);
            e.addAfter(Blocks.WARPED_ROOTS, PromenadeBlocks.DARK_AMARANTH_ROOTS);
            e.addAfter(Blocks.TWISTING_VINES, PromenadeBlocks.COILED_VINES);
            e.addAfter(Blocks.SHROOMLIGHT, PromenadeBlocks.SOUL_SHROOMLIGHT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(e -> {
            e.addAfter(Blocks.SHROOMLIGHT, PromenadeBlocks.SOUL_SHROOMLIGHT);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(e -> e.addAfter(Blocks.CUT_RED_SANDSTONE_SLAB, PromenadeBlocks.MOAI));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(e -> e.addAfter(Blocks.BIRCH_HANGING_SIGN, PromenadeItems.SAKURA_SIGN, PromenadeItems.SAKURA_HANGING_SIGN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(e -> e.addAfter(Items.BIRCH_CHEST_BOAT, PromenadeItems.SAKURA_BOAT, PromenadeItems.SAKURA_CHEST_BOAT));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(e -> e.addAfter(PromenadeItems.SAKURA_HANGING_SIGN, PromenadeItems.MAPLE_SIGN, PromenadeItems.MAPLE_HANGING_SIGN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(e -> e.addAfter(PromenadeItems.SAKURA_CHEST_BOAT, PromenadeItems.MAPLE_BOAT, PromenadeItems.MAPLE_CHEST_BOAT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(e -> e.addAfter(Items.HONEY_BOTTLE, PromenadeItems.MAPLE_SYRUP_BOTTLE));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(e -> e.addAfter(Blocks.ACACIA_HANGING_SIGN, PromenadeItems.PALM_SIGN, PromenadeItems.PALM_HANGING_SIGN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(e -> e.addAfter(Items.ACACIA_CHEST_BOAT, PromenadeItems.PALM_BOAT, PromenadeItems.PALM_CHEST_BOAT));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(e -> e.addAfter(Blocks.WARPED_HANGING_SIGN, PromenadeItems.DARK_AMARANTH_SIGN, PromenadeItems.DARK_AMARANTH_HANGING_SIGN));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(e -> {
            e.addAfter(Items.SWEET_BERRIES, PromenadeItems.BLUEBERRIES);
            e.addAfter(Items.ENCHANTED_GOLDEN_APPLE, PromenadeItems.BANANA);
            //TODO apricot and mango
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(e -> e.addAfter(Items.COOKED_CHICKEN, PromenadeItems.DUCK, PromenadeItems.COOKED_DUCK));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(e -> e.addAfter(Items.FLOWER_BANNER_PATTERN, PromenadeItems.BOVINE_BANNER_PATTERN));

        appendSpawnEgg(PromenadeItems.CAPYBARA_SPAWN_EGG);
        appendSpawnEgg(PromenadeItems.DUCK_SPAWN_EGG);
        appendSpawnEgg(PromenadeItems.LUSH_CREEPER_SPAWN_EGG);
        appendSpawnEgg(PromenadeItems.SUNKEN_SPAWN_EGG);
    }

    public static void appendSpawnEgg(Item spawnEgg) {
        var itemGroup = Registries.ITEM_GROUP.get(ItemGroups.SPAWN_EGGS);
        String path = Registries.ITEM.getId(spawnEgg).getPath();

        if (itemGroup == null) {
            return;
        }

        Predicate<ItemStack> predicate = stack1 -> {
            String path1 = Registries.ITEM.getId(stack1.getItem()).getPath();
            for (ItemStack stack2 : itemGroup.getDisplayStacks()) {
                String path2 = Registries.ITEM.getId(stack2.getItem()).getPath();
                if (path1.matches(".*_spawn_egg") && path2.matches(".*_spawn_egg")) {
                    // check if path is lexicographically between path1 and path2
                    if (path.compareTo(path1) > 0 && path.compareTo(path2) < 0) {
                        return true;
                    }
                }
            }
            return false;
        };
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(e -> e.addAfter(predicate, Collections.singleton(new ItemStack(spawnEgg)), ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS));
    }
}
