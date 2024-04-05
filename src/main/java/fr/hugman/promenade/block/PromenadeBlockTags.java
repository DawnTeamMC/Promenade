package fr.hugman.promenade.block;

import fr.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class PromenadeBlockTags {
    public static final TagKey<Block> CARPETED_GRASS_BLOCKS = of("carpeted_grass_blocks");

    public static final TagKey<Block> CHERRY_OAK_LOGS = of("cherry_oak_logs");
    public static final TagKey<Block> MAPLE_LOGS = of("maple_logs");
    public static final TagKey<Block> PALM_LOGS = of("palm_logs");
    public static final TagKey<Block> DARK_AMARANTH_STEMS = of("dark_amaranth_stems");

    public static final TagKey<Block> LEAVES = of("leaves");
    public static final TagKey<Block> LEAF_PILES = of("leaf_piles");
    public static final TagKey<Block> FLOWER_PILES = of("flower_piles");

    public static final TagKey<Block> CAN_SPREAD_BLACK_DYLIUM = of("can_spread_black_dylium");
    public static final TagKey<Block> DARK_AMARANTH_FUNGUS_PLACEABLE_ON = of("placeable_on/dark_amaranth_fungus");
    public static final TagKey<Block> DARK_AMARANTH_FUNGUS_GROWABLE_ON = of("growable_on/dark_amaranth_fungus");
    public static final TagKey<Block> DARK_AMARANTH_ROOTS_PLACEABLE_ON = of("placeable_on/dark_amaranth_roots");
    public static final TagKey<Block> OBSIDIAN_SPIKE_PLACEABLE_ON = of("placeable_on/obsidian_spike");

    public static TagKey<Block> of(String path) {
        return TagKey.of(RegistryKeys.BLOCK, Promenade.id(path));
    }
}
