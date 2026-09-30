package fr.hugman.promenade.entity.spawn;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

/**
 * Where a mob is spawning, as seen by {@link SpawnCondition}s.
 * <p>
 * Newer versions of Minecraft pick mob variants with spawn conditions; this package brings the same system (and the
 * same data format) to 1.21.1.
 */
public record SpawnContext(BlockPos pos, ServerLevelAccessor level, Holder<Biome> biome) {
    public static SpawnContext create(ServerLevelAccessor level, BlockPos pos) {
        return new SpawnContext(pos, level, level.getBiome(pos));
    }
}
