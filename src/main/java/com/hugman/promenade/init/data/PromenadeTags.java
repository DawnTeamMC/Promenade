package com.hugman.promenade.init.data;

import com.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class PromenadeTags {
	public static class Blocks {
		public static final TagKey<Block> POTTED_MUSHROOMS = register("potted_mushrooms");
		public static final TagKey<Block> DARK_AMARANTH_FUNGUS_GROWABLE_ON = register("growable_on/dark_amaranth_fungus");
		public static final TagKey<Block> DARK_AMARANTH_FUNGUS_PLACEABLE_ON = register("placeable_on/dark_amaranth_fungus");
		public static final TagKey<Block> DARK_AMARANTH_ROOTS_PLACEABLE_ON = register("placeable_on/dark_amaranth_roots");

		private static TagKey<Block> register(String name) {
			return TagKey.of(Registry.BLOCK_KEY, Promenade.MOD_DATA.id(name));
		}
	}

	public static class Biomes {
		public static final TagKey<Biome> PEKIN_DUCK_SPAWN = register("spawns/duck/pekin");
		public static final TagKey<Biome> MALLARD_DUCK_SPAWN = register("spawns/duck/mallard");
		public static final TagKey<Biome> SUNKEN_SKELETON_SPAWN = register("spawns/sunken_skeleton");
		public static final TagKey<Biome> HAS_PALMS = register("has_palms");

		private static TagKey<Biome> register(String name) {
			return TagKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id(name));
		}
	}
}
