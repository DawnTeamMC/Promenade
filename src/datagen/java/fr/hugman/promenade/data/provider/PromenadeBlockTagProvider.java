package fr.hugman.promenade.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.block.PromenadeBlocks.*;
import static fr.hugman.promenade.tag.PromenadeBlockTags.*;

public class PromenadeBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public PromenadeBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Promenade
        valueLookupBuilder(SAKURA_LOGS).add(SAKURA_LOG, STRIPPED_SAKURA_LOG, SAKURA_WOOD, STRIPPED_SAKURA_WOOD);
        valueLookupBuilder(MAPLE_LOGS).add(MAPLE_LOG, STRIPPED_MAPLE_LOG, MAPLE_WOOD, STRIPPED_MAPLE_WOOD);
        valueLookupBuilder(PALM_LOGS).add(PALM_LOG, STRIPPED_PALM_LOG, PALM_WOOD, STRIPPED_PALM_WOOD);
        valueLookupBuilder(DARK_AMARANTH_STEMS).add(DARK_AMARANTH_STEM, STRIPPED_DARK_AMARANTH_STEM, DARK_AMARANTH_HYPHAE, STRIPPED_DARK_AMARANTH_HYPHAE);

        valueLookupBuilder(SNOWY_LEAVES).add(
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

        valueLookupBuilder(FALLEN_LEAVES).add(
                FALLEN_SAP_MAPLE_LEAVES,
                FALLEN_VERMILION_MAPLE_LEAVES,
                FALLEN_FULVOUS_MAPLE_LEAVES,
                FALLEN_MIKADO_MAPLE_LEAVES
        );
        valueLookupBuilder(LEAF_PILES).add(
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

        valueLookupBuilder(FLOWER_PILES).add(
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

        valueLookupBuilder(DARK_AMARANTH_FUNGUS_GROWABLE_ON).add(DARK_AMARANTH_NYLIUM);
        valueLookupBuilder(DARK_AMARANTH_FUNGUS_PLACEABLE_ON)
                .addTag(DARK_AMARANTH_FUNGUS_GROWABLE_ON)
                .forceAddTag(BlockTags.NYLIUM)
                .add(Blocks.MYCELIUM)
                .add(Blocks.SOUL_SOIL)
                .add(Blocks.DIRT)
                .add(Blocks.FARMLAND);
        valueLookupBuilder(DARK_AMARANTH_ROOTS_PLACEABLE_ON)
                .forceAddTag(BlockTags.NYLIUM)
                .add(Blocks.SOUL_SOIL)
                .add(Blocks.DIRT)
                .add(Blocks.FARMLAND);

        // Vanilla
        valueLookupBuilder(BlockTags.BASE_STONE_OVERWORLD).add(ASPHALT, BLUNITE);
        valueLookupBuilder(BlockTags.STONE_ORE_REPLACEABLES).add(ASPHALT, BLUNITE);


        valueLookupBuilder(BlockTags.SLABS).add(ASPHALT_SLAB, BLUNITE_SLAB, POLISHED_ASPHALT_SLAB, POLISHED_BLUNITE_SLAB);
        valueLookupBuilder(BlockTags.STAIRS).add(ASPHALT_STAIRS, BLUNITE_STAIRS, POLISHED_ASPHALT_STAIRS, POLISHED_BLUNITE_STAIRS);
        valueLookupBuilder(BlockTags.WALLS).add(ASPHALT_WALL, BLUNITE_WALL);

        valueLookupBuilder(BlockTags.LOGS).addTag(DARK_AMARANTH_STEMS);
        valueLookupBuilder(BlockTags.LOGS_THAT_BURN).addTag(SAKURA_LOGS).addTag(MAPLE_LOGS).addTag(PALM_LOGS);
        valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add(SAKURA_LOG, MAPLE_LOG, PALM_LOG);
        valueLookupBuilder(BlockTags.PLANKS).add(SAKURA_PLANKS, MAPLE_PLANKS, PALM_PLANKS, DARK_AMARANTH_PLANKS);
        valueLookupBuilder(BlockTags.WOODEN_BUTTONS).add(SAKURA_BUTTON, MAPLE_BUTTON, PALM_BUTTON, DARK_AMARANTH_BUTTON);
        valueLookupBuilder(BlockTags.WOODEN_DOORS).add(SAKURA_DOOR, MAPLE_DOOR, PALM_DOOR, DARK_AMARANTH_DOOR);
        valueLookupBuilder(BlockTags.WOODEN_FENCES).add(SAKURA_FENCE, MAPLE_FENCE, PALM_FENCE, DARK_AMARANTH_FENCE);
        valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(SAKURA_PRESSURE_PLATE, MAPLE_PRESSURE_PLATE, PALM_PRESSURE_PLATE, DARK_AMARANTH_PRESSURE_PLATE);
        valueLookupBuilder(BlockTags.WOODEN_SLABS).add(SAKURA_SLAB, MAPLE_SLAB, PALM_SLAB, DARK_AMARANTH_SLAB);
        valueLookupBuilder(BlockTags.WOODEN_STAIRS).add(SAKURA_STAIRS, MAPLE_STAIRS, PALM_STAIRS, DARK_AMARANTH_STAIRS);
        valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add(SAKURA_TRAPDOOR, MAPLE_TRAPDOOR, PALM_TRAPDOOR, DARK_AMARANTH_TRAPDOOR);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(SAKURA_FENCE_GATE, MAPLE_FENCE_GATE, PALM_FENCE_GATE, DARK_AMARANTH_FENCE_GATE);
        valueLookupBuilder(BlockTags.STANDING_SIGNS).add(SAKURA_SIGN, MAPLE_SIGN, PALM_SIGN, DARK_AMARANTH_SIGN);
        valueLookupBuilder(BlockTags.WALL_SIGNS).add(SAKURA_WALL_SIGN, MAPLE_WALL_SIGN, PALM_WALL_SIGN, DARK_AMARANTH_WALL_SIGN);
        valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS).add(SAKURA_HANGING_SIGN, MAPLE_HANGING_SIGN, PALM_HANGING_SIGN, DARK_AMARANTH_HANGING_SIGN);
        valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS).add(SAKURA_WALL_HANGING_SIGN, MAPLE_WALL_HANGING_SIGN, PALM_WALL_HANGING_SIGN, DARK_AMARANTH_WALL_HANGING_SIGN);

        valueLookupBuilder(BlockTags.LEAVES).add(
                VERMILION_MAPLE_LEAVES,
                FULVOUS_MAPLE_LEAVES,
                MIKADO_MAPLE_LEAVES,
                SAP_MAPLE_LEAVES,
                BLUSH_SAKURA_BLOSSOMS,
                COTTON_SAKURA_BLOSSOMS,
                PALM_LEAVES
        ).addTag(SNOWY_LEAVES);
        valueLookupBuilder(BlockTags.SAPLINGS).add(
                BLUSH_SAKURA_SAPLING, COTTON_SAKURA_SAPLING,
                SAP_MAPLE_SAPLING, VERMILION_MAPLE_SAPLING, FULVOUS_MAPLE_SAPLING, MIKADO_MAPLE_SAPLING,
                PALM_SAPLING);

        valueLookupBuilder(BlockTags.BEE_ATTRACTIVE).add(AZALEA_LEAF_PILE, FLOWERING_AZALEA_LEAF_PILE);
        valueLookupBuilder(BlockTags.FLOWERS).add(AZALEA_LEAF_PILE, FLOWERING_AZALEA_LEAF_PILE);
        valueLookupBuilder(BlockTags.FLOWER_POTS).add(
                POTTED_BLUSH_SAKURA_SAPLING, POTTED_COTTON_SAKURA_SAPLING,
                POTTED_SAP_MAPLE_SAPLING, POTTED_VERMILION_MAPLE_SAPLING, POTTED_FULVOUS_MAPLE_SAPLING, POTTED_MIKADO_MAPLE_SAPLING,
                POTTED_PALM_SAPLING,
                POTTED_DARK_AMARANTH_FUNGUS, POTTED_DARK_AMARANTH_ROOTS
        );

        valueLookupBuilder(BlockTags.REPLACEABLE_BY_TREES)
                .addTag(FALLEN_LEAVES)
                .addTag(LEAF_PILES)
                .addTag(FLOWER_PILES)
                .add(DARK_AMARANTH_ROOTS);
        valueLookupBuilder(BlockTags.BEE_GROWABLES).add(BLUEBERRY_BUSH);
        valueLookupBuilder(BlockTags.MUSHROOM_GROW_BLOCK).add(DARK_AMARANTH_NYLIUM);

        valueLookupBuilder(BlockTags.NYLIUM).add(DARK_AMARANTH_NYLIUM);
        valueLookupBuilder(BlockTags.WART_BLOCKS).add(DARK_AMARANTH_WART_BLOCK);
        valueLookupBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(DARK_AMARANTH_NYLIUM, DARK_AMARANTH_WART_BLOCK, SOUL_SHROOMLIGHT);

        valueLookupBuilder(BlockTags.ENDERMAN_HOLDABLE).add(DARK_AMARANTH_FUNGUS, DARK_AMARANTH_NYLIUM, DARK_AMARANTH_ROOTS);

        valueLookupBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).addTag(FALLEN_LEAVES);
        valueLookupBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS).add(DARK_AMARANTH_ROOTS);

        valueLookupBuilder(BlockTags.CLIMBABLE).add(COILED_VINES, COILED_VINES_PLANT);
        valueLookupBuilder(BlockTags.REPLACEABLE).addTag(FALLEN_LEAVES);

        valueLookupBuilder(BlockTags.SWORD_EFFICIENT)
                .addTag(LEAF_PILES)
                .addTag(FLOWER_PILES)
                .add(DARK_AMARANTH_ROOTS, COILED_VINES, COILED_VINES_PLANT);
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(
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
        valueLookupBuilder(BlockTags.AXE_MINEABLE).add(DARK_AMARANTH_FUNGUS, COILED_VINES, COILED_VINES_PLANT);
        valueLookupBuilder(BlockTags.HOE_MINEABLE)
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
        valueLookupBuilder(ConventionalBlockTags.STONES).add(ASPHALT, BLUNITE);
        valueLookupBuilder(ConventionalBlockTags.FENCES).add(SAKURA_FENCE, MAPLE_FENCE, PALM_FENCE, DARK_AMARANTH_FENCE);
        valueLookupBuilder(ConventionalBlockTags.WOODEN_FENCES).add(SAKURA_FENCE, MAPLE_FENCE, PALM_FENCE, DARK_AMARANTH_FENCE);
        valueLookupBuilder(ConventionalBlockTags.FENCE_GATES).add(SAKURA_FENCE_GATE, MAPLE_FENCE_GATE, PALM_FENCE_GATE, DARK_AMARANTH_FENCE_GATE);
        valueLookupBuilder(ConventionalBlockTags.WOODEN_FENCE_GATES).add(SAKURA_FENCE_GATE, MAPLE_FENCE_GATE, PALM_FENCE_GATE, DARK_AMARANTH_FENCE_GATE);
        valueLookupBuilder(ConventionalBlockTags.STRIPPED_LOGS).add(STRIPPED_SAKURA_LOG, STRIPPED_MAPLE_LOG, STRIPPED_PALM_LOG, STRIPPED_DARK_AMARANTH_STEM);
        valueLookupBuilder(ConventionalBlockTags.STRIPPED_WOODS).add(STRIPPED_SAKURA_WOOD, STRIPPED_MAPLE_WOOD, STRIPPED_PALM_WOOD, STRIPPED_DARK_AMARANTH_HYPHAE);

    }
}