package fr.hugman.promenade.neoforge;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

/**
 * Applies the spawns and features that Promenade adds to biomes (see {@link fr.hugman.promenade.platform.PromenadePlatform#addSpawn}).
 * Enabled by {@code data/promenade/neoforge/biome_modifier/platform.json}.
 */
public record PromenadeBiomeModifier() implements BiomeModifier {
    public static final MapCodec<PromenadeBiomeModifier> CODEC = MapCodec.unit(PromenadeBiomeModifier::new);

    @Override
    public void modify(RegistryAccess registries, Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) {
            return;
        }
        var platform = (NeoForgePromenadePlatform) PromenadePlatform.INSTANCE;

        var spawnSettings = builder.getMobSpawnSettings();
        for (var spawn : platform.spawns) {
            if (spawn.biomes().test(biome)) {
                spawnSettings.addSpawn(spawn.type(), spawn.category(), spawn.weight(), UniformInt.of(spawn.minGroupSize(), spawn.maxGroupSize()));
            }
        }

        var placedFeatures = registries.lookupOrThrow(Registries.PLACED_FEATURE);
        var generationSettings = builder.getGenerationSettings();
        for (var feature : platform.features) {
            if (feature.biomes().test(biome)) {
                generationSettings.addFeature(feature.step(), placedFeatures.getOrThrow(feature.feature()));
            }
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
