package fr.hugman.promenade.client.color;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeFoliageColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.world.biome.FoliageColors;
import net.minecraft.world.biome.GrassColors;

@Environment(EnvType.CLIENT)
public final class PromenadeBlockColors {
    public static void register() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> PromenadeFoliageColors.PALM, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.DEFAULT, PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.MANGROVE_LEAF_PILE, PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.SPRUCE, PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.BIRCH, PromenadeBlocks.BIRCH_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return GrassColors.getColor(0.5, 1.0);
            }
            return BiomeColors.getGrassColor(world, pos);
        }, PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK, PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK);
    }
}
