package fr.hugman.promenade.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class SnowyBlocksMap {
    // TODO: Make this data-driven or at least registrable
    @Nullable
    public static Block get(Block block) {
        if (block == Blocks.OAK_LEAVES) return PromenadeBlocks.SNOWY_OAK_LEAVES;
        if (block == Blocks.SPRUCE_LEAVES) return PromenadeBlocks.SNOWY_SPRUCE_LEAVES;
        if (block == Blocks.BIRCH_LEAVES) return PromenadeBlocks.SNOWY_BIRCH_LEAVES;
        if (block == Blocks.JUNGLE_LEAVES) return PromenadeBlocks.SNOWY_JUNGLE_LEAVES;
        if (block == Blocks.ACACIA_LEAVES) return PromenadeBlocks.SNOWY_ACACIA_LEAVES;
        if (block == Blocks.CHERRY_LEAVES) return PromenadeBlocks.SNOWY_CHERRY_LEAVES;
        if (block == Blocks.DARK_OAK_LEAVES) return PromenadeBlocks.SNOWY_DARK_OAK_LEAVES;
        if (block == Blocks.PALE_OAK_LEAVES) return PromenadeBlocks.SNOWY_PALE_OAK_LEAVES;
        if (block == Blocks.MANGROVE_LEAVES) return PromenadeBlocks.SNOWY_MANGROVE_LEAVES;
        if (block == Blocks.AZALEA_LEAVES) return PromenadeBlocks.SNOWY_AZALEA_LEAVES;
        if (block == Blocks.FLOWERING_AZALEA_LEAVES) return PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES;
        if (block == PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS) return PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS;
        if (block == PromenadeBlocks.COTTON_SAKURA_BLOSSOMS) return PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS;
        if (block == PromenadeBlocks.SAP_MAPLE_LEAVES) return PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES;
        if (block == PromenadeBlocks.VERMILION_MAPLE_LEAVES) return PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES;
        if (block == PromenadeBlocks.FULVOUS_MAPLE_LEAVES) return PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES;
        if (block == PromenadeBlocks.MIKADO_MAPLE_LEAVES) return PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES;
        if (block == PromenadeBlocks.PALM_LEAVES) return PromenadeBlocks.SNOWY_PALM_LEAVES;
        return null;
    }

    @Nullable
    public static boolean hasKey(Block block) {
        if (block == Blocks.OAK_LEAVES) return true;
        if (block == Blocks.SPRUCE_LEAVES) return true;
        if (block == Blocks.BIRCH_LEAVES) return true;
        if (block == Blocks.JUNGLE_LEAVES) return true;
        if (block == Blocks.ACACIA_LEAVES) return true;
        if (block == Blocks.CHERRY_LEAVES) return true;
        if (block == Blocks.DARK_OAK_LEAVES) return true;
        if (block == Blocks.PALE_OAK_LEAVES) return true;
        if (block == Blocks.MANGROVE_LEAVES) return true;
        if (block == Blocks.AZALEA_LEAVES) return true;
        if (block == Blocks.FLOWERING_AZALEA_LEAVES) return true;
        if (block == PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS) return true;
        if (block == PromenadeBlocks.COTTON_SAKURA_BLOSSOMS) return true;
        if (block == PromenadeBlocks.SAP_MAPLE_LEAVES) return true;
        if (block == PromenadeBlocks.VERMILION_MAPLE_LEAVES) return true;
        if (block == PromenadeBlocks.FULVOUS_MAPLE_LEAVES) return true;
        if (block == PromenadeBlocks.MIKADO_MAPLE_LEAVES) return true;
        if (block == PromenadeBlocks.PALM_LEAVES) return true;
        return false;
    }

    public static boolean hasValue(Block block) {
        if(block == PromenadeBlocks.SNOWY_OAK_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_SPRUCE_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_BIRCH_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_JUNGLE_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_ACACIA_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_CHERRY_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_DARK_OAK_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_PALE_OAK_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_MANGROVE_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_AZALEA_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS) return true;
        if(block == PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS) return true;
        if(block == PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES) return true;
        if(block == PromenadeBlocks.SNOWY_PALM_LEAVES) return true;
        return false;
    }
}
