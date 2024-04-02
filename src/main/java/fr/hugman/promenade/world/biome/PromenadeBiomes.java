package fr.hugman.promenade.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import fr.hugman.promenade.Promenade;
import net.minecraft.world.biome.BiomeKeys;

public class PromenadeBiomes {
    public static void appendWorldGen() {
        if (Promenade.CONFIG.biomes.carnelian_treeway_weight <= 0) {
            return;
        }
        BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, PromenadeBiomeKeys.CARNELIAN_TREEWAY, Promenade.CONFIG.biomes.carnelian_treeway_weight / 100.0D);


        if (Promenade.CONFIG.biomes.sakura_groves_weight <= 0) {
            return;
        }
        double sakuraWeight = Promenade.CONFIG.biomes.sakura_groves_weight / 100.0D;
        BiomePlacement.replaceOverworld(BiomeKeys.FOREST, PromenadeBiomeKeys.BLUSH_SAKURA_GROVE, sakuraWeight);
        BiomePlacement.replaceOverworld(BiomeKeys.BIRCH_FOREST, PromenadeBiomeKeys.COTTON_SAKURA_GROVE, sakuraWeight);
    }
}
