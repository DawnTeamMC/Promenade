package fr.hugman.promenade.world.biome;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Predicate;

/**
 * Biome predicates for biome modifications, that only look at the biome itself so that every loader can evaluate them.
 */
public final class PromenadeBiomeSelectors {
    public static Predicate<Holder<Biome>> is(ResourceKey<Biome> key) {
        return biome -> biome.is(key);
    }

    public static Predicate<Holder<Biome>> tag(TagKey<Biome> tag) {
        return biome -> biome.is(tag);
    }

    /**
     * Matches biomes where {@code type} naturally spawns, in any category.
     */
    public static Predicate<Holder<Biome>> spawns(EntityType<?> type) {
        return biome -> {
            var spawnSettings = biome.value().getMobSettings();
            for (MobCategory category : MobCategory.values()) {
                for (var spawner : spawnSettings.getMobs(category).unwrap()) {
                    if (spawner.type == type) {
                        return true;
                    }
                }
            }
            return false;
        };
    }

    public static Predicate<Holder<Biome>> hasFeature(ResourceKey<ConfiguredFeature<?, ?>> feature) {
        return biome -> biome.value().getGenerationSettings().features().stream()
                .flatMap(HolderSet::stream)
                .anyMatch(placed -> placed.value().feature().is(feature));
    }

    public static Predicate<Holder<Biome>> hasPlacedFeature(ResourceKey<PlacedFeature> feature) {
        return biome -> biome.value().getGenerationSettings().features().stream()
                .flatMap(HolderSet::stream)
                .anyMatch(placed -> placed.is(feature));
    }
}
