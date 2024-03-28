package fr.hugman.promenade.content;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;

public class PromenadeBiomeKeys {
    public static final RegistryKey<Biome> CARNELIAN_TREEWAY = of("carnelian_treeway");

    private static RegistryKey<Biome> of(String path) {
        return RegistryKey.of(RegistryKeys.BIOME, Promenade.id(path));
    }
}