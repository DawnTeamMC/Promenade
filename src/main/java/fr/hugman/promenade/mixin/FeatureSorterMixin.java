package fr.hugman.promenade.mixin;

import fr.hugman.promenade.util.FeatureCycleDetector;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Function;

@Mixin(PlacedFeatureIndexer.class)
public abstract class FeatureSorterMixin
{
    @Inject(method = "collectIndexedFeatures", at = @At("HEAD"), cancellable = true)
    private static <T> void buildFeaturesPerStepWithAdvancedCycleDetection(
            List<T> biomes,
            Function<T, List<RegistryEntryList<PlacedFeature>>> biomesToPlacedFeaturesList,
            boolean listInvolvedBiomesOnFailure,
            CallbackInfoReturnable<List<PlacedFeatureIndexer.IndexedFeatures>> cir
    )
    {
        cir.setReturnValue(FeatureCycleDetector.buildFeaturesPerStep(cast(biomes), cast(biomesToPlacedFeaturesList)));
    }

    @Unique
    @SuppressWarnings("unchecked")
    private static <T> T cast(Object o)
    {
        return (T) o;
    }
}