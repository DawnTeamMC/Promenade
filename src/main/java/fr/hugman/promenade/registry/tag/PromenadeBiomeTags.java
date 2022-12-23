package fr.hugman.promenade.registry.tag;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.promenade.Promenade;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class PromenadeBiomeTags {
	public static final TagKey<Biome> CAPYBARA_SPAWN = DawnFactory.biomeTag(Promenade.id("spawns/capybara"));
	public static final TagKey<Biome> PEKIN_DUCK_SPAWN = DawnFactory.biomeTag(Promenade.id("spawns/duck/pekin"));
	public static final TagKey<Biome> MALLARD_DUCK_SPAWN = DawnFactory.biomeTag(Promenade.id("spawns/duck/mallard"));
	public static final TagKey<Biome> SUNKEN_SKELETON_SPAWN = DawnFactory.biomeTag(Promenade.id("spawns/sunken_skeleton"));
	public static final TagKey<Biome> HAS_PALMS = DawnFactory.biomeTag(Promenade.id("has_palms"));
}
