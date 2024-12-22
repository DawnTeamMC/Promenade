package fr.hugman.promenade.world.biome;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class PromenadeBiomeTags {
    public static final TagKey<Biome> CAPYBARA_SPAWN = of("spawns/capybara");
    public static final TagKey<Biome> PEKIN_DUCK_SPAWN = of("spawns/duck/pekin");
    public static final TagKey<Biome> MALLARD_DUCK_SPAWN = of("spawns/duck/mallard");
    public static final TagKey<Biome> SUNKEN_SPAWN = of("spawns/sunken");
    public static final TagKey<Biome> HAS_PALMS = of("has_palms");
    public static final TagKey<Biome> CAN_FREEZE_DURING_SNOWFALL = of("can_freeze_during_snowfall");

    private static TagKey<Biome> of(String path) {
        return TagKey.of(RegistryKeys.BIOME, Promenade.id(path));
    }
}
