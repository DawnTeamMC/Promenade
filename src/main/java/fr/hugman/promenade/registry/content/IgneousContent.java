package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class IgneousContent {
    public static final RegistryKey<PlacedFeature> ORE_BLUNITE_UPPER = DawnFactory.placedFeature(Promenade.id("ore/blunite/upper"));
    public static final RegistryKey<PlacedFeature> ORE_BLUNITE_LOWER = DawnFactory.placedFeature(Promenade.id("ore/blunite/lower"));
    public static final RegistryKey<PlacedFeature> ORE_ASPHALT_UPPER = DawnFactory.placedFeature(Promenade.id("ore/asphalt/upper"));
    public static final RegistryKey<PlacedFeature> ORE_ASPHALT_LOWER = DawnFactory.placedFeature(Promenade.id("ore/asphalt/lower"));

    public static void register(Registrar r) {
        if (Promenade.CONFIG.world_features.igneous_rock_patches) {
            Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasFeature(OreConfiguredFeatures.ORE_ANDESITE) && c.hasFeature(OreConfiguredFeatures.ORE_DIORITE) && c.hasFeature(OreConfiguredFeatures.ORE_GRANITE);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_LOWER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_ASPHALT_UPPER);
            BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_ASPHALT_LOWER);
        }
    }
}
