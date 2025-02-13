package fr.hugman.promenade.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SnowyLeavesMap {
    private static final Map<Block, Block> SNOWY_LEAVES = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.OAK_LEAVES, PromenadeBlocks.SNOWY_OAK_LEAVES)
            .put(Blocks.SPRUCE_LEAVES, PromenadeBlocks.SNOWY_SPRUCE_LEAVES)
            .put(Blocks.BIRCH_LEAVES, PromenadeBlocks.SNOWY_BIRCH_LEAVES)
            .put(Blocks.JUNGLE_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES)
            .put(Blocks.ACACIA_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES)
            .put(Blocks.CHERRY_LEAVES, PromenadeBlocks.SNOWY_CHERRY_LEAVES)
            .put(Blocks.DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES)
            .put(Blocks.PALE_OAK_LEAVES, PromenadeBlocks.SNOWY_PALE_OAK_LEAVES)
            .put(Blocks.MANGROVE_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES)
            .put(Blocks.AZALEA_LEAVES, PromenadeBlocks.SNOWY_AZALEA_LEAVES)
            .put(Blocks.FLOWERING_AZALEA_LEAVES, PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES)
            .build();

    @Nullable
    public static Block get(Block leaves) {
        return SNOWY_LEAVES.getOrDefault(leaves, null);
    }
}
