package fr.hugman.promenade.registry.tag;

import fr.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class PromenadeTags {
	public static class Blocks {
		public static final TagKey<Block> DARK_AMARANTH_FUNGUS_GROWABLE_ON = register("growable_on/dark_amaranth_fungus");
		public static final TagKey<Block> DARK_AMARANTH_FUNGUS_PLACEABLE_ON = register("placeable_on/dark_amaranth_fungus");
		public static final TagKey<Block> DARK_AMARANTH_ROOTS_PLACEABLE_ON = register("placeable_on/dark_amaranth_roots");
		public static final TagKey<Block> CAN_SPREAD_BLACK_DYLIUM = register("can_spread_black_dylium");

		private static TagKey<Block> register(String name) {
			return TagKey.of(RegistryKeys.BLOCK, Promenade.id(name));
		}
	}

	public static class Items {
		public static final TagKey<Item> BREEDING_CAPYBARA = register("breeding/capybara");
		public static final TagKey<Item> BREEDING_DUCK = register("breeding/duck");

		private static TagKey<Item> register(String name) {
			return TagKey.of(RegistryKeys.ITEM, Promenade.id(name));
		}
	}


	public static class Biomes {
		public static final TagKey<Biome> CAPYBARA_SPAWN = register("spawns/capybara");
		public static final TagKey<Biome> PEKIN_DUCK_SPAWN = register("spawns/duck/pekin");
		public static final TagKey<Biome> MALLARD_DUCK_SPAWN = register("spawns/duck/mallard");
		public static final TagKey<Biome> SUNKEN_SKELETON_SPAWN = register("spawns/sunken_skeleton");
		public static final TagKey<Biome> HAS_PALMS = register("has_palms");

		private static TagKey<Biome> register(String name) {
			return TagKey.of(RegistryKeys.BIOME, Promenade.id(name));
		}
	}
}
