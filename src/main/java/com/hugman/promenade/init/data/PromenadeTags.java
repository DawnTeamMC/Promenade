package com.hugman.promenade.init.data;

import com.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class PromenadeTags {
	public static class Blocks {
		public static final Tag<Block> POTTED_MUSHROOMS = register("potted_mushrooms");

		private static Tag<Block> register(Identifier id) {
			return TagFactory.BLOCK.create(id);
		}

		private static Tag<Block> register(String name) {
			return register(Promenade.MOD_DATA.id(name));
		}

		private static Tag<Block> register(String namespace, String path) {
			return register(new Identifier(namespace, path));
		}
	}
}
