package fr.hugman.promenade.forge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

/**
 * Applies the spawns and features that Promenade adds to biomes (see {@link fr.hugman.promenade.platform.PromenadePlatform#addSpawn}).
 * Enabled by {@code data/promenade/forge/biome_modifier/platform.json}.
 *
 * @param placedFeatures looked up while the modifier is decoded, as Forge gives no registries when applying it
 */
public record PromenadeBiomeModifier(HolderGetter<PlacedFeature> placedFeatures) implements BiomeModifier {
    public static final Codec<PromenadeBiomeModifier> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryOps.retrieveGetter(Registries.PLACED_FEATURE)
    ).apply(instance, PromenadeBiomeModifier::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) {
            return;
        }
        var platform = (ForgePromenadePlatform) PromenadePlatform.INSTANCE;

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
    public Codec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
