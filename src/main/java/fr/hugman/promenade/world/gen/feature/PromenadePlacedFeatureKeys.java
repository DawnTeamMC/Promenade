package fr.hugman.promenade.world.gen.feature;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;

public class PromenadePlacedFeatureKeys {
    public static final RegistryKey<PlacedFeature> ORE_BLUNITE_UPPER = of("ore/blunite/upper");
    public static final RegistryKey<PlacedFeature> ORE_BLUNITE_LOWER = of("ore/blunite/lower");
    public static final RegistryKey<PlacedFeature> ORE_ASPHALT_UPPER = of("ore/asphalt/upper");
    public static final RegistryKey<PlacedFeature> ORE_ASPHALT_LOWER = of("ore/asphalt/lower");

    public static final RegistryKey<PlacedFeature> PALMS = of("trees/palms");

    public static final RegistryKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_COMMON = DawnFactory.placedFeature(Promenade.id("patch/blueberry_bush/common"));
    public static final RegistryKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_RARE = DawnFactory.placedFeature(Promenade.id("patch/blueberry_bush/rare"));

    private static RegistryKey<PlacedFeature> of(String path) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Promenade.id(path));
    }
}
