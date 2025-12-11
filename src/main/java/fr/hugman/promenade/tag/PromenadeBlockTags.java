package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class PromenadeBlockTags {
    public static final TagKey<Block> SAKURA_LOGS = of("sakura_logs");
    public static final TagKey<Block> MAPLE_LOGS = of("maple_logs");
    public static final TagKey<Block> PALM_LOGS = of("palm_logs");
    public static final TagKey<Block> DARK_AMARANTH_STEMS = of("dark_amaranth_stems");

    public static final TagKey<Block> SNOWY_LEAVES = of("snowy_leaves");

    public static final TagKey<Block> FALLEN_LEAVES = of("fallen_leaves");
    public static final TagKey<Block> LEAF_PILES = of("leaf_piles");
    public static final TagKey<Block> FLOWER_PILES = of("flower_piles");

    public static final TagKey<Block> DARK_AMARANTH_FUNGUS_PLACEABLE_ON = of("dark_amaranth_fungus_placeable_on");
    public static final TagKey<Block> DARK_AMARANTH_FUNGUS_GROWABLE_ON = of("dark_amaranth_fungus_growable_on");
    public static final TagKey<Block> DARK_AMARANTH_ROOTS_PLACEABLE_ON = of("dark_amaranth_roots_placeable_on");

	public static final TagKey<Block> IGNEOUS_ROCKS = ofConventional("igneous_rocks");

    public static TagKey<Block> of(String path) {
        return TagKey.of(RegistryKeys.BLOCK, Promenade.id(path));
    }

	private static TagKey<Block> ofConventional(String path) {
		return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TagUtil.C_TAG_NAMESPACE, path));
	}
}
