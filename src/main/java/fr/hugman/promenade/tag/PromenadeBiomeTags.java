package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class PromenadeBiomeTags {
    public static final TagKey<Biome> SAKURA_GROVES = of("sakura_groves");
    public static final TagKey<Biome> DARK_AMARANTH_FORESTS = of("dark_amaranth_forests");

    public static final TagKey<Biome> HAS_PALMS = of("has_palms");
    public static final TagKey<Biome> HAS_DARK_FOREST_WITCH_HUT = of("has_structure/dark_forest_witch_hut");

    public static final TagKey<Biome> CAN_FREEZE_DURING_SNOWFALL = of("can_freeze_during_snowfall");

    public static final TagKey<Biome> SPAWNS_CAPYBARAS = of("spawns_capybaras");
    public static final TagKey<Biome> SPAWNS_SUNKEN = of("spawns_sunken");
    public static final TagKey<Biome> SPAWNS_MALLARD_DUCKS = of("spawns_mallard_ducks");
    public static final TagKey<Biome> SPAWNS_PEKIN_DUCKS = of("spawns_pekin_ducks");

    private static TagKey<Biome> of(String path) {
        return TagKey.of(RegistryKeys.BIOME, Promenade.id(path));
    }
}
