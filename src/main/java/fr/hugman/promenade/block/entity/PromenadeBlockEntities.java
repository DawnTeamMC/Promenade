package fr.hugman.promenade.block.entity;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.block.entity.BlockEntityType;

public class PromenadeBlockEntities {
    public static void addBlocksToVanillaBlockEntityTypes() {
        BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.PALM_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.PALM_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.PALM_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.PALM_WALL_HANGING_SIGN);
    }
}
