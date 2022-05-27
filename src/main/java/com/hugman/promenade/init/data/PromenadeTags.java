package com.hugman.promenade.init.data;

import com.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class PromenadeTags {
	public static class Blocks {
		public static final TagKey<Block> POTTED_MUSHROOMS = register("potted_mushrooms");

		private static TagKey<Block> register(String name) {
			return TagKey.of(Registry.BLOCK_KEY, Promenade.MOD_DATA.id(name));
		}
	}

	public static class Biomes {
		public static final TagKey<Biome> SUNKEN_SKELETON_SPAWN = register("spawn/sunken_skeleton");
		public static final TagKey<Biome> MALLARD_DUCK_SPAWN = register("spawn/duck/mallard");
		public static final TagKey<Biome> PEKIN_DUCK_SPAWN = register("spawn/duck/pekin");

		private static TagKey<Biome> register(String name) {
			return TagKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id(name));
		}
	}
}
