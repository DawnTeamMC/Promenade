package fr.hugman.promenade.block.entity;

import com.google.common.collect.ImmutableSet;
import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PromenadeBlockEntities {
    public static void addBlocksToVanillaBlockEntityTypes() {
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.SAKURA_SIGN);
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.SAKURA_WALL_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.SAKURA_HANGING_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.MAPLE_SIGN);
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.MAPLE_WALL_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.MAPLE_HANGING_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.PALM_SIGN);
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.PALM_WALL_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.PALM_HANGING_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.DARK_AMARANTH_SIGN);
        addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);
        addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);
    }

    /**
     * Neither loader can add blocks to an existing block entity type in 1.20.1: its set of blocks is widened by the
     * access transformer and the access widener, and replaced here.
     */
    private static void addSupportedBlocks(BlockEntityType<?> type, Block... blocks) {
        type.validBlocks = ImmutableSet.<Block>builder().addAll(type.validBlocks).add(blocks).build();
    }
}
