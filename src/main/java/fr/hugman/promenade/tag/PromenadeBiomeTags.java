package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class PromenadeBiomeTags {
    public static final TagKey<Biome> SAKURA_GROVES = of("sakura_groves");

    public static final TagKey<Biome> HAS_PALMS = of("has_palms");
    public static final TagKey<Biome> HAS_DARK_FOREST_WITCH_HUTS = of("has_structure/dark_forest_witch_huts");

    public static final TagKey<Biome> CAN_FREEZE_DURING_SNOWFALL = of("can_freeze_during_snowfall");

    public static final TagKey<Biome> SPAWNS_CAPYBARAS = of("spawns_capybaras");
    public static final TagKey<Biome> SPAWNS_SUNKEN = of("spawns_sunken");
    public static final TagKey<Biome> SPAWNS_MALLARD_DUCKS = of("spawns_mallard_ducks");
    public static final TagKey<Biome> SPAWNS_PEKIN_DUCKS = of("spawns_pekin_ducks");

	public static final TagKey<Biome> PRIMARY_WOOD_TYPE_SAKURA = ofConventional("primary_wood_type/sakura");
	public static final TagKey<Biome> PRIMARY_WOOD_TYPE_PALM = ofConventional("primary_wood_type/palm");
	public static final TagKey<Biome> PRIMARY_WOOD_TYPE_MAPLE = ofConventional("primary_wood_type/maple");
	public static final TagKey<Biome> PRIMARY_WOOD_TYPE_DARK_AMARANTH = ofConventional("primary_wood_type/dark_amaranth");

    private static TagKey<Biome> of(String path) {
        return TagKey.of(RegistryKeys.BIOME, Promenade.id(path));
    }

	private static TagKey<Biome> ofConventional(String path) {
		return TagKey.of(RegistryKeys.BIOME, Identifier.of(TagUtil.C_TAG_NAMESPACE, path));
	}
}
