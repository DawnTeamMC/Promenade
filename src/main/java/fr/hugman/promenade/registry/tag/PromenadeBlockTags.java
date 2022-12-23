package fr.hugman.promenade.registry.tag;

import fr.hugman.promenade.PromenadeFactory;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

public class PromenadeBlockTags {
	public static final TagKey<Block> CARPETED_GRASS_BLOCKS = PromenadeFactory.blockTag("carpeted_grass_blocks");

	public static final TagKey<Block> CHERRY_OAK_LOGS = PromenadeFactory.blockTag("cherry_oak_logs");
	public static final TagKey<Block> MAPLE_LOGS = PromenadeFactory.blockTag("maple_logs");
	public static final TagKey<Block> PALM_LOGS = PromenadeFactory.blockTag("palm_logs");
	public static final TagKey<Block> DARK_AMARANTH_STEMS = PromenadeFactory.blockTag("dark_amaranth_stems");

	public static final TagKey<Block> LEAVES = PromenadeFactory.blockTag("leaves");
	public static final TagKey<Block> LEAF_PILES = PromenadeFactory.blockTag("leaf_piles");
	public static final TagKey<Block> FLOWER_PILES = PromenadeFactory.blockTag("flower_piles");

	public static final TagKey<Block> CAN_SPREAD_BLACK_DYLIUM = PromenadeFactory.blockTag("can_spread_black_dylium");
	public static final TagKey<Block> DARK_AMARANTH_FUNGUS_PLACEABLE_ON = PromenadeFactory.blockTag("placeable_on/dark_amaranth_fungus");
	public static final TagKey<Block> DARK_AMARANTH_FUNGUS_GROWABLE_ON = PromenadeFactory.blockTag("growable_on/dark_amaranth_fungus");
	public static final TagKey<Block> DARK_AMARANTH_ROOTS_PLACEABLE_ON = PromenadeFactory.blockTag("placeable_on/dark_amaranth_roots");
	public static final TagKey<Block> OBSIDIAN_SPIKE_PLACEABLE_ON = PromenadeFactory.blockTag("placeable_on/obsidian_spike");
}
