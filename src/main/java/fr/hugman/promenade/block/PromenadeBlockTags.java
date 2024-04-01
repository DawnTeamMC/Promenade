package fr.hugman.promenade.block;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

public class PromenadeBlockTags {
    public static final TagKey<Block> CARPETED_GRASS_BLOCKS = DawnFactory.blockTag(Promenade.id("carpeted_grass_blocks"));

    public static final TagKey<Block> CHERRY_OAK_LOGS = DawnFactory.blockTag(Promenade.id("cherry_oak_logs"));
    public static final TagKey<Block> MAPLE_LOGS = DawnFactory.blockTag(Promenade.id("maple_logs"));
    public static final TagKey<Block> PALM_LOGS = DawnFactory.blockTag(Promenade.id("palm_logs"));
    public static final TagKey<Block> DARK_AMARANTH_STEMS = DawnFactory.blockTag(Promenade.id("dark_amaranth_stems"));

    public static final TagKey<Block> LEAVES = DawnFactory.blockTag(Promenade.id("leaves"));
    public static final TagKey<Block> LEAF_PILES = DawnFactory.blockTag(Promenade.id("leaf_piles"));
    public static final TagKey<Block> FLOWER_PILES = DawnFactory.blockTag(Promenade.id("flower_piles"));

    public static final TagKey<Block> CAN_SPREAD_BLACK_DYLIUM = DawnFactory.blockTag(Promenade.id("can_spread_black_dylium"));
    public static final TagKey<Block> DARK_AMARANTH_FUNGUS_PLACEABLE_ON = DawnFactory.blockTag(Promenade.id("placeable_on/dark_amaranth_fungus"));
    public static final TagKey<Block> DARK_AMARANTH_FUNGUS_GROWABLE_ON = DawnFactory.blockTag(Promenade.id("growable_on/dark_amaranth_fungus"));
    public static final TagKey<Block> DARK_AMARANTH_ROOTS_PLACEABLE_ON = DawnFactory.blockTag(Promenade.id("placeable_on/dark_amaranth_roots"));
    public static final TagKey<Block> OBSIDIAN_SPIKE_PLACEABLE_ON = DawnFactory.blockTag(Promenade.id("placeable_on/obsidian_spike"));
}
