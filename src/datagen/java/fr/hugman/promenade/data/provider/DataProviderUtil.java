package fr.hugman.promenade.data.provider;

import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.level.biome.Biome;

public final class DataProviderUtil {
    public static SpawnPrioritySelectors createSpawnConditions(HolderSet<Biome> requiredBiomes) {
        return SpawnPrioritySelectors.single(new BiomeCheck(requiredBiomes), 1);
    }
}
