package fr.hugman.promenade.block.entity;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;

public class PromenadeBlockEntities {
    public static void addBlocksToVanillaBlockEntityTypes() {
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.SAKURA_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.SAKURA_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.SAKURA_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.MAPLE_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.MAPLE_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.MAPLE_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.PALM_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.PALM_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.PALM_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.DARK_AMARANTH_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);

        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SHELF, PromenadeBlocks.SAKURA_SHELF);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SHELF, PromenadeBlocks.MAPLE_SHELF);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SHELF, PromenadeBlocks.PALM_SHELF);
        PromenadePlatform.INSTANCE.addSupportedBlocks(BlockEntityTypes.SHELF, PromenadeBlocks.DARK_AMARANTH_SHELF);
    }
}
