package fr.hugman.promenade.block.entity;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PromenadeBlockEntities {
    public static void addBlocksToVanillaBlockEntityTypes() {
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.SAKURA_SIGN);
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.SAKURA_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.SAKURA_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.MAPLE_SIGN);
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.MAPLE_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.MAPLE_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.PALM_SIGN);
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.PALM_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.PALM_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_SIGN);
        BlockEntityType.SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);

        BlockEntityType.SHELF.addValidBlock(PromenadeBlocks.SAKURA_SHELF);
        BlockEntityType.SHELF.addValidBlock(PromenadeBlocks.MAPLE_SHELF);
        BlockEntityType.SHELF.addValidBlock(PromenadeBlocks.PALM_SHELF);
        BlockEntityType.SHELF.addValidBlock(PromenadeBlocks.DARK_AMARANTH_SHELF);
    }
}
