package fr.hugman.promenade.registry.tag;

import fr.hugman.promenade.PromenadeFactory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class PromenadeBiomeTags {
	public static final TagKey<Biome> CAPYBARA_SPAWN = PromenadeFactory.biomeTag("spawns/capybara");
	public static final TagKey<Biome> PEKIN_DUCK_SPAWN = PromenadeFactory.biomeTag("spawns/duck/pekin");
	public static final TagKey<Biome> MALLARD_DUCK_SPAWN = PromenadeFactory.biomeTag("spawns/duck/mallard");
	public static final TagKey<Biome> SUNKEN_SKELETON_SPAWN = PromenadeFactory.biomeTag("spawns/sunken_skeleton");
	public static final TagKey<Biome> HAS_PALMS = PromenadeFactory.biomeTag("has_palms");
}
