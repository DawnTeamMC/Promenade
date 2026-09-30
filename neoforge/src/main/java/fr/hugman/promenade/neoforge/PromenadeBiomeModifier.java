package fr.hugman.promenade.neoforge;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

/**
 * Applies the spawns and features that Promenade adds to biomes (see {@link fr.hugman.promenade.platform.PromenadePlatform#addSpawn}).
 * Enabled by {@code data/promenade/neoforge/biome_modifier/platform.json}.
 *
 * @param placedFeatures looked up while the modifier is decoded, as NeoForge 21.1 gives no registries when applying it
 */
public record PromenadeBiomeModifier(HolderGetter<PlacedFeature> placedFeatures) implements BiomeModifier {
    public static final MapCodec<PromenadeBiomeModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RegistryOps.retrieveGetter(Registries.PLACED_FEATURE)
    ).apply(instance, PromenadeBiomeModifier::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) {
            return;
        }
        var platform = (NeoForgePromenadePlatform) PromenadePlatform.INSTANCE;

        var spawnSettings = builder.getMobSpawnSettings();
        for (var spawn : platform.spawns) {
            if (spawn.biomes().test(biome)) {
                spawnSettings.addSpawn(spawn.category(), new MobSpawnSettings.SpawnerData(spawn.type(), spawn.weight(), spawn.minGroupSize(), spawn.maxGroupSize()));
            }
        }

        var generationSettings = builder.getGenerationSettings();
        for (var feature : platform.features) {
            if (feature.biomes().test(biome)) {
                generationSettings.addFeature(feature.step(), this.placedFeatures.getOrThrow(feature.feature()));
            }
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
