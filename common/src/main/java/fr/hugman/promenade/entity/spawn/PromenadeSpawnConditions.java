package fr.hugman.promenade.entity.spawn;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.resources.ResourceLocation;

/**
 * The types of {@link SpawnCondition}. The vanilla ones keep their newer ids, so that variant files stay compatible.
 */
public class PromenadeSpawnConditions {
    private static final BiMap<ResourceLocation, MapCodec<? extends SpawnCondition>> TYPES = HashBiMap.create();

    public static final Codec<MapCodec<? extends SpawnCondition>> TYPE_CODEC = ResourceLocation.CODEC.flatXmap(
            id -> {
                MapCodec<? extends SpawnCondition> codec = TYPES.get(id);
                return codec != null ? DataResult.success(codec) : DataResult.error(() -> "Unknown spawn condition type: " + id);
            },
            codec -> {
                ResourceLocation id = TYPES.inverse().get(codec);
                return id != null ? DataResult.success(id) : DataResult.error(() -> "Unregistered spawn condition type: " + codec);
            }
    );

    public static void register() {
        of(ResourceLocation.withDefaultNamespace("structure"), StructureCheck.CODEC);
        of(ResourceLocation.withDefaultNamespace("moon_brightness"), MoonBrightnessCheck.CODEC);
        of(ResourceLocation.withDefaultNamespace("biome"), BiomeCheck.CODEC);
        of(Promenade.id("chance"), ChanceSpawnCondition.CODEC);
    }

    public static MapCodec<? extends SpawnCondition> of(ResourceLocation id, MapCodec<? extends SpawnCondition> codec) {
        TYPES.put(id, codec);
        return codec;
    }
}
