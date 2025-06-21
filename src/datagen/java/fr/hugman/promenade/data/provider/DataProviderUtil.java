package fr.hugman.promenade.data.provider;

import net.minecraft.entity.spawn.BiomeSpawnCondition;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;

public final class DataProviderUtil {
    public static SpawnConditionSelectors createSpawnConditions(RegistryEntryList<Biome> requiredBiomes) {
        return SpawnConditionSelectors.createSingle(new BiomeSpawnCondition(requiredBiomes), 1);
    }
}
