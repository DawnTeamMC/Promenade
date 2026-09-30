package fr.hugman.promenade.block.entity;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PromenadeBlockEntities {
    public static void addBlocksToVanillaBlockEntityTypes() {
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.SAKURA_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.SAKURA_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.SAKURA_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.MAPLE_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.MAPLE_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.MAPLE_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.PALM_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.PALM_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.PALM_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.DARK_AMARANTH_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityType.HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);
    }
}
