package fr.hugman.promenade.client.color.block;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeFoliageColors;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public final class PromenadeBlockColors {
    private static final BlockColor FOLIAGE = (state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor();

    public static void register(Registrar registrar) {
        registrar.register(FOLIAGE,
                PromenadeBlocks.SNOWY_OAK_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES,
                PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.MANGROVE_LEAF_PILE,
                PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES
        );
        registrar.register(constant(FoliageColor.getEvergreenColor()), PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        registrar.register(constant(FoliageColor.getBirchColor()), PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.SNOWY_BIRCH_LEAVES);
        registrar.register(constant(PromenadeFoliageColors.PALM), PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
    }

    /**
     * Item tints are set in code on 1.21.1, rather than in item model definitions.
     */
    public static void registerItems(ItemRegistrar registrar) {
        registrar.register(constantItem(FoliageColor.getDefaultColor()),
                PromenadeBlocks.SNOWY_OAK_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES,
                PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.MANGROVE_LEAF_PILE
        );
        registrar.register(constantItem(PromenadeFoliageColors.SAP_MAPLE),
                PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES
        );
        registrar.register(constantItem(FoliageColor.getEvergreenColor()), PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
        registrar.register(constantItem(FoliageColor.getBirchColor()), PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.SNOWY_BIRCH_LEAVES);
        registrar.register(constantItem(PromenadeFoliageColors.PALM), PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
    }

    private static BlockColor constant(int color) {
        return (state, world, pos, tintIndex) -> color;
    }

    private static ItemColor constantItem(int color) {
        int opaqueColor = FastColor.ARGB32.opaque(color);
        return (stack, tintIndex) -> opaqueColor;
    }

    @FunctionalInterface
    public interface Registrar {
        void register(BlockColor color, Block... blocks);
    }

    @FunctionalInterface
    public interface ItemRegistrar {
        void register(ItemColor color, ItemLike... items);
    }
}
