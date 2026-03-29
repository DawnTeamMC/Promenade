package fr.hugman.promenade.client.color;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeFoliageColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.world.level.FoliageColor;

import java.util.List;

@Environment(EnvType.CLIENT)
public final class PromenadeBlockColors {
    public static void register() {
        BlockColorRegistry.register(List.of(BlockTintSources.foliage()),
                PromenadeBlocks.SNOWY_OAK_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES,
                PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.MANGROVE_LEAF_PILE,
                PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES,
                PromenadeBlocks.SNOWY_OAK_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES
        );
        BlockColorRegistry.register(List.of(BlockTintSources.constant(FoliageColor.FOLIAGE_EVERGREEN)), PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        BlockColorRegistry.register(List.of(BlockTintSources.constant(FoliageColor.FOLIAGE_BIRCH)), PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.SNOWY_BIRCH_LEAVES);
        BlockColorRegistry.register(List.of(BlockTintSources.constant(PromenadeFoliageColors.PALM)), PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
    }
}
