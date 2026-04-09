package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

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
        return TagKey.create(Registries.BLOCK, Promenade.id(path));
    }

	private static TagKey<Block> ofConventional(String path) {
		return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TagUtil.C_TAG_NAMESPACE, path));
	}
}
