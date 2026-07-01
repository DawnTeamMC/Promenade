package fr.hugman.promenade.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.references.PromenadeBlockItemIds.*;
import static fr.hugman.promenade.references.PromenadeBlockIds.*;
import static fr.hugman.promenade.tag.PromenadeBlockTags.*;

public class PromenadeBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public PromenadeBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Promenade
        builder(SAKURA_LOGS).add(SAKURA_LOG, STRIPPED_SAKURA_LOG, SAKURA_WOOD, STRIPPED_SAKURA_WOOD);
        builder(MAPLE_LOGS).add(MAPLE_LOG, STRIPPED_MAPLE_LOG, MAPLE_WOOD, STRIPPED_MAPLE_WOOD);
        builder(PALM_LOGS).add(PALM_LOG, STRIPPED_PALM_LOG, PALM_WOOD, STRIPPED_PALM_WOOD);
        builder(DARK_AMARANTH_STEMS).add(DARK_AMARANTH_STEM, STRIPPED_DARK_AMARANTH_STEM, DARK_AMARANTH_HYPHAE, STRIPPED_DARK_AMARANTH_HYPHAE);

        builder(SNOWY_LEAVES).add(
                SNOWY_OAK_LEAVES,
                SNOWY_SPRUCE_LEAVES,
                SNOWY_BIRCH_LEAVES,
                SNOWY_JUNGLE_LEAVES,
                SNOWY_ACACIA_LEAVES,
                SNOWY_CHERRY_LEAVES,
                SNOWY_DARK_OAK_LEAVES,
                SNOWY_PALE_OAK_LEAVES,
                SNOWY_MANGROVE_LEAVES,
                SNOWY_AZALEA_LEAVES,
                SNOWY_FLOWERING_AZALEA_LEAVES,
                SNOWY_BLUSH_SAKURA_BLOSSOMS,
                SNOWY_COTTON_SAKURA_BLOSSOMS,
                SNOWY_SAP_MAPLE_LEAVES,
                SNOWY_VERMILION_MAPLE_LEAVES,
                SNOWY_FULVOUS_MAPLE_LEAVES,
                SNOWY_MIKADO_MAPLE_LEAVES,
                SNOWY_PALM_LEAVES
        );

        builder(FALLEN_LEAVES).add(
                FALLEN_SAP_MAPLE_LEAVES,
                FALLEN_VERMILION_MAPLE_LEAVES,
                FALLEN_FULVOUS_MAPLE_LEAVES,
                FALLEN_MIKADO_MAPLE_LEAVES
        );
        builder(LEAF_PILES).add(
                OAK_LEAF_PILE,
                SPRUCE_LEAF_PILE,
                BIRCH_LEAF_PILE,
                JUNGLE_LEAF_PILE,
                ACACIA_LEAF_PILE,
                CHERRY_LEAF_PILE,
                DARK_OAK_LEAF_PILE,
                PALE_OAK_LEAF_PILE,
                MANGROVE_LEAF_PILE,
                AZALEA_LEAF_PILE,
                FLOWERING_AZALEA_LEAF_PILE,
                VERMILION_MAPLE_LEAF_PILE,
                FULVOUS_MAPLE_LEAF_PILE,
                MIKADO_MAPLE_LEAF_PILE,
                SAP_MAPLE_LEAF_PILE,
                BLUSH_SAKURA_BLOSSOM_PILE,
                COTTON_SAKURA_BLOSSOM_PILE,
                PALM_LEAF_PILE
        );

        builder(FLOWER_PILES).add(
                DANDELION_PILE,
                POPPY_PILE,
                BLUE_ORCHID_PILE,
                ALLIUM_PILE,
                AZURE_BLUET_PILE,
                RED_TULIP_PILE,
                ORANGE_TULIP_PILE,
                WHITE_TULIP_PILE,
                PINK_TULIP_PILE,
                OXEYE_DAISY_PILE,
                CORNFLOWER_PILE,
                LILY_OF_THE_VALLEY_PILE,
                WITHER_ROSE_PILE
        );

        builder(DARK_AMARANTH_FUNGUS_GROWABLE_ON).add(DARK_AMARANTH_NYLIUM);
        builder(DARK_AMARANTH_FUNGUS_PLACEABLE_ON)
                .add(BlockItemIds.MYCELIUM)
                .add(BlockItemIds.SOUL_SOIL)
                .add(BlockItemIds.DIRT)
                .add(BlockItemIds.FARMLAND)
                .addTag(DARK_AMARANTH_FUNGUS_GROWABLE_ON)
                .forceAddTag(BlockTags.NYLIUM);
        builder(DARK_AMARANTH_ROOTS_PLACEABLE_ON)
                .add(BlockItemIds.SOUL_SOIL)
                .add(BlockItemIds.DIRT)
                .add(BlockItemIds.FARMLAND)
                .forceAddTag(BlockTags.NYLIUM);

        // Vanilla
        builder(BlockTags.BASE_STONE_OVERWORLD).add(ASPHALT, BLUNITE);
        builder(BlockTags.STONE_ORE_REPLACEABLES).add(ASPHALT, BLUNITE);


        builder(BlockTags.SLABS).add(ASPHALT_SLAB, BLUNITE_SLAB, POLISHED_ASPHALT_SLAB, POLISHED_BLUNITE_SLAB);
        builder(BlockTags.STAIRS).add(ASPHALT_STAIRS, BLUNITE_STAIRS, POLISHED_ASPHALT_STAIRS, POLISHED_BLUNITE_STAIRS);
        builder(BlockTags.WALLS).add(ASPHALT_WALL, BLUNITE_WALL);

        builder(BlockTags.LOGS).addTag(DARK_AMARANTH_STEMS);
        builder(BlockItemTags.LOGS_THAT_BURN.block()).addTag(SAKURA_LOGS).addTag(MAPLE_LOGS).addTag(PALM_LOGS);
        builder(BlockTags.OVERWORLD_NATURAL_LOGS).add(SAKURA_LOG, MAPLE_LOG, PALM_LOG);
        builder(BlockTags.PLANKS).add(SAKURA_PLANKS, MAPLE_PLANKS, PALM_PLANKS, DARK_AMARANTH_PLANKS);
        builder(BlockTags.WOODEN_BUTTONS).add(SAKURA_BUTTON, MAPLE_BUTTON, PALM_BUTTON, DARK_AMARANTH_BUTTON);
        builder(BlockTags.WOODEN_DOORS).add(SAKURA_DOOR, MAPLE_DOOR, PALM_DOOR, DARK_AMARANTH_DOOR);
        builder(BlockTags.WOODEN_FENCES).add(SAKURA_FENCE, MAPLE_FENCE, PALM_FENCE, DARK_AMARANTH_FENCE);
        builder(BlockTags.WOODEN_PRESSURE_PLATES).add(SAKURA_PRESSURE_PLATE, MAPLE_PRESSURE_PLATE, PALM_PRESSURE_PLATE, DARK_AMARANTH_PRESSURE_PLATE);
        builder(BlockTags.WOODEN_SLABS).add(SAKURA_SLAB, MAPLE_SLAB, PALM_SLAB, DARK_AMARANTH_SLAB);
        builder(BlockTags.WOODEN_STAIRS).add(SAKURA_STAIRS, MAPLE_STAIRS, PALM_STAIRS, DARK_AMARANTH_STAIRS);
        builder(BlockTags.WOODEN_TRAPDOORS).add(SAKURA_TRAPDOOR, MAPLE_TRAPDOOR, PALM_TRAPDOOR, DARK_AMARANTH_TRAPDOOR);
        builder(BlockTags.FENCE_GATES).add(SAKURA_FENCE_GATE, MAPLE_FENCE_GATE, PALM_FENCE_GATE, DARK_AMARANTH_FENCE_GATE);
        builder(BlockTags.STANDING_SIGNS).add(SAKURA_SIGN, MAPLE_SIGN, PALM_SIGN, DARK_AMARANTH_SIGN);
        builder(BlockTags.WALL_SIGNS).add(SAKURA_WALL_SIGN, MAPLE_WALL_SIGN, PALM_WALL_SIGN, DARK_AMARANTH_WALL_SIGN);
        builder(BlockTags.CEILING_HANGING_SIGNS).add(SAKURA_HANGING_SIGN, MAPLE_HANGING_SIGN, PALM_HANGING_SIGN, DARK_AMARANTH_HANGING_SIGN);
        builder(BlockTags.WALL_HANGING_SIGNS).add(SAKURA_WALL_HANGING_SIGN, MAPLE_WALL_HANGING_SIGN, PALM_WALL_HANGING_SIGN, DARK_AMARANTH_WALL_HANGING_SIGN);
        builder(BlockTags.WOODEN_SHELVES).add(SAKURA_SHELF, MAPLE_SHELF, PALM_SHELF, DARK_AMARANTH_SHELF);

        builder(BlockTags.LEAVES).add(
                VERMILION_MAPLE_LEAVES,
                FULVOUS_MAPLE_LEAVES,
                MIKADO_MAPLE_LEAVES,
                SAP_MAPLE_LEAVES,
                BLUSH_SAKURA_BLOSSOMS,
                COTTON_SAKURA_BLOSSOMS,
                PALM_LEAVES
        ).addTag(SNOWY_LEAVES);
        builder(BlockItemTags.SAPLINGS.block()).add(
                BLUSH_SAKURA_SAPLING, COTTON_SAKURA_SAPLING,
                SAP_MAPLE_SAPLING, VERMILION_MAPLE_SAPLING, FULVOUS_MAPLE_SAPLING, MIKADO_MAPLE_SAPLING,
                PALM_SAPLING);

        builder(BlockTags.BEE_ATTRACTIVE).add(AZALEA_LEAF_PILE, FLOWERING_AZALEA_LEAF_PILE);
        builder(BlockTags.FLOWERS).add(AZALEA_LEAF_PILE, FLOWERING_AZALEA_LEAF_PILE);
        builder(BlockTags.FLOWER_POTS).add(
                POTTED_BLUSH_SAKURA_SAPLING, POTTED_COTTON_SAKURA_SAPLING,
                POTTED_SAP_MAPLE_SAPLING, POTTED_VERMILION_MAPLE_SAPLING, POTTED_FULVOUS_MAPLE_SAPLING, POTTED_MIKADO_MAPLE_SAPLING,
                POTTED_PALM_SAPLING,
                POTTED_DARK_AMARANTH_FUNGUS, POTTED_DARK_AMARANTH_ROOTS
        );

        builder(BlockTags.REPLACEABLE_BY_TREES)
                .addTag(FALLEN_LEAVES)
                .addTag(LEAF_PILES)
                .addTag(FLOWER_PILES)
                .add(DARK_AMARANTH_ROOTS);
        builder(BlockTags.BEE_GROWABLES).add(BLUEBERRY_BUSH);
        builder(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT).add(DARK_AMARANTH_NYLIUM);
        builder(BlockTags.HUGE_BROWN_MUSHROOM_CAN_PLACE_ON).add(DARK_AMARANTH_NYLIUM);
        builder(BlockTags.HUGE_RED_MUSHROOM_CAN_PLACE_ON).add(DARK_AMARANTH_NYLIUM);

        builder(BlockTags.NYLIUM).add(DARK_AMARANTH_NYLIUM);
        builder(BlockTags.WART_BLOCKS).add(DARK_AMARANTH_WART_BLOCK);
        builder(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(DARK_AMARANTH_NYLIUM, DARK_AMARANTH_WART_BLOCK, SOUL_SHROOMLIGHT);

        builder(BlockTags.ENDERMAN_HOLDABLE).add(DARK_AMARANTH_FUNGUS, DARK_AMARANTH_NYLIUM, DARK_AMARANTH_ROOTS);

        builder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).addTag(FALLEN_LEAVES);
        builder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS).add(DARK_AMARANTH_ROOTS);

        builder(BlockTags.CLIMBABLE).add(COILED_VINES).add(COILED_VINES_PLANT);
        builder(BlockTags.CAN_GLIDE_THROUGH).add(COILED_VINES).add(COILED_VINES_PLANT);
        builder(BlockTags.REPLACEABLE).addTag(FALLEN_LEAVES);

        builder(BlockTags.SWORD_EFFICIENT)
                .add(DARK_AMARANTH_ROOTS, COILED_VINES)
                .add(COILED_VINES_PLANT)
                .addTag(LEAF_PILES)
                .addTag(FLOWER_PILES);
        builder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BLUNITE,
                BLUNITE_STAIRS,
                BLUNITE_SLAB,
                ASPHALT,
                ASPHALT_STAIRS,
                ASPHALT_SLAB,
                POLISHED_BLUNITE,
                POLISHED_BLUNITE_STAIRS,
                POLISHED_BLUNITE_SLAB,
                POLISHED_ASPHALT,
                POLISHED_ASPHALT_STAIRS,
                POLISHED_ASPHALT_SLAB,
                MOAI,
                DARK_AMARANTH_NYLIUM
        );
        builder(BlockTags.MINEABLE_WITH_AXE).add(DARK_AMARANTH_FUNGUS, COILED_VINES).add(COILED_VINES_PLANT);
        builder(BlockTags.MINEABLE_WITH_HOE)
                .add(
                        VERMILION_MAPLE_LEAVES,
                        FULVOUS_MAPLE_LEAVES,
                        MIKADO_MAPLE_LEAVES,
                        SAP_MAPLE_LEAVES,
                        BLUSH_SAKURA_BLOSSOMS,
                        COTTON_SAKURA_BLOSSOMS,
                        PALM_LEAVES
                )
                .addTag(SNOWY_LEAVES)
                .addTag(FLOWER_PILES)
                .addTag(LEAF_PILES)
                .add(DARK_AMARANTH_WART_BLOCK, SOUL_SHROOMLIGHT);


        // Conventional
        builder(ConventionalBlockTags.STONES).add(ASPHALT, BLUNITE);
        builder(ConventionalBlockTags.FENCES).add(SAKURA_FENCE, MAPLE_FENCE, PALM_FENCE, DARK_AMARANTH_FENCE);
        builder(ConventionalBlockTags.WOODEN_FENCES).add(SAKURA_FENCE, MAPLE_FENCE, PALM_FENCE, DARK_AMARANTH_FENCE);
        builder(ConventionalBlockTags.FENCE_GATES).add(SAKURA_FENCE_GATE, MAPLE_FENCE_GATE, PALM_FENCE_GATE, DARK_AMARANTH_FENCE_GATE);
        builder(ConventionalBlockTags.WOODEN_FENCE_GATES).add(SAKURA_FENCE_GATE, MAPLE_FENCE_GATE, PALM_FENCE_GATE, DARK_AMARANTH_FENCE_GATE);
        builder(ConventionalBlockTags.STRIPPED_LOGS).add(STRIPPED_SAKURA_LOG, STRIPPED_MAPLE_LOG, STRIPPED_PALM_LOG, STRIPPED_DARK_AMARANTH_STEM);
        builder(ConventionalBlockTags.STRIPPED_WOODS).add(STRIPPED_SAKURA_WOOD, STRIPPED_MAPLE_WOOD, STRIPPED_PALM_WOOD, STRIPPED_DARK_AMARANTH_HYPHAE);

        builder(IGNEOUS_ROCKS)
                .add(BlockItemIds.ANDESITE, BlockItemIds.DIORITE, BlockItemIds.GRANITE, BlockItemIds.TUFF, BLUNITE);

    }
}