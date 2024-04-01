package fr.hugman.promenade.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import fr.hugman.promenade.Promenade;
import net.minecraft.world.biome.BiomeKeys;

public class PromenadeBiomes {
    public static void appendWorldGen() {
        if (Promenade.CONFIG.biomes.carnelian_treeway_weight <= 0) {
            return;
        }
        double weight = Promenade.CONFIG.biomes.carnelian_treeway_weight / 100.0D;
        BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, PromenadeBiomeKeys.CARNELIAN_TREEWAY, weight);
    }
}
