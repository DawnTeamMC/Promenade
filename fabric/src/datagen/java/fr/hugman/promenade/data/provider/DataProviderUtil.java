package fr.hugman.promenade.data.provider;

import net.minecraft.core.HolderSet;
import fr.hugman.promenade.entity.spawn.BiomeCheck;
import fr.hugman.promenade.entity.spawn.SpawnPrioritySelectors;
import net.minecraft.world.level.biome.Biome;

public final class DataProviderUtil {
    public static SpawnPrioritySelectors createSpawnConditions(HolderSet<Biome> requiredBiomes) {
        return SpawnPrioritySelectors.single(new BiomeCheck(requiredBiomes), 1);
    }
}
