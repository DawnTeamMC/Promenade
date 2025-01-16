package fr.hugman.promenade.client.color;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.biome.FoliageColors;
import net.minecraft.world.biome.GrassColors;

@Environment(EnvType.CLIENT)
public final class PromenadeColors {
    private static final int SAP_MAPLE_COLOR = 10931465; // 'foliage_color' in 'carnelian_treeway.json'
    private static final int PALM_COLOR = 8237614;

    public static void register() {
        registerBlockColors();
        registerItemColors();
    }


    public static void registerBlockColors() {
        /*
        TODO
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> PALM_COLOR, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.MANGROVE_LEAF_PILE, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getSpruceColor(), PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.getBirchColor(), PromenadeBlocks.BIRCH_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return GrassColors.getColor(0.5, 1.0);
            }
            return BiomeColors.getGrassColor(world, pos);
        }, PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK);

         */
    }

    public static void registerItemColors() {
        /* TODO: i believe this in the resource pack now
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> PALM_COLOR, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            BlockState blockState = block.getDefaultState();
            return ColorProviderRegistry.BLOCK.get(block).getColor(blockState, null, null, tintIndex);
        }, PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getMangroveColor(), PromenadeBlocks.MANGROVE_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> SAP_MAPLE_COLOR, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE);

         */
    }
}
