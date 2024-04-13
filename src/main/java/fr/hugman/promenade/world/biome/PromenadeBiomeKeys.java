package fr.hugman.promenade.world.biome;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;

public class PromenadeBiomeKeys {
    public static final RegistryKey<Biome> BLUSH_SAKURA_GROVE = of("blush_sakura_grove");
    public static final RegistryKey<Biome> COTTON_SAKURA_GROVE = of("cotton_sakura_grove");
    public static final RegistryKey<Biome> CARNELIAN_TREEWAY = of("carnelian_treeway");
    public static final RegistryKey<Biome> GLACARIAN_TAIGA = of("glacarian_taiga");

    public static final RegistryKey<Biome> DARK_AMARANTH_FOREST = of("dark_amaranth_forest");
    public static final RegistryKey<Biome> TALL_DARK_AMARANTH_FOREST = of("tall_dark_amaranth_forest");

    public static final RegistryKey<Biome> AURORAL_CYPRESS_FOREST = of("auroral_cypress_forest");

    private static RegistryKey<Biome> of(String path) {
        return RegistryKey.of(RegistryKeys.BIOME, Promenade.id(path));
    }
}
