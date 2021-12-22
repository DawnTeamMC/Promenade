package com.hugman.promenade.init.data;

import com.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.world.biome.Biome;

public class PromenadeTags {
	public static class Blocks {
		public static final Tag<Block> POTTED_MUSHROOMS = register("potted_mushrooms");

		private static Tag<Block> register(String name) {
			return TagFactory.BLOCK.create(Promenade.MOD_DATA.id(name));
		}
	}

	public static class Biomes {
		private static Tag<Biome> register(String name) {
			return TagFactory.BIOME.create(Promenade.MOD_DATA.id(name));
		}
	}
}
