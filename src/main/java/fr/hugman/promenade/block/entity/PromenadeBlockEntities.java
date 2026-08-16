package fr.hugman.promenade.block.entity;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;

public class PromenadeBlockEntities {
    public static void addBlocksToVanillaBlockEntityTypes() {
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.SAKURA_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.SAKURA_WALL_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.SAKURA_HANGING_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.MAPLE_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.MAPLE_WALL_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.MAPLE_HANGING_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.PALM_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.PALM_WALL_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.PALM_HANGING_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_SIGN);
        BlockEntityTypes.SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_WALL_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);
        BlockEntityTypes.HANGING_SIGN.addValidBlock(PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);

        BlockEntityTypes.SHELF.addValidBlock(PromenadeBlocks.SAKURA_SHELF);
        BlockEntityTypes.SHELF.addValidBlock(PromenadeBlocks.MAPLE_SHELF);
        BlockEntityTypes.SHELF.addValidBlock(PromenadeBlocks.PALM_SHELF);
        BlockEntityTypes.SHELF.addValidBlock(PromenadeBlocks.DARK_AMARANTH_SHELF);
    }
}
