package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.util.NoiseScale;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record NoisePickedFeatureConfig(
        NoiseScale noiseScale,
        List<NoisePickedFeatureEntry> entries
) implements FeatureConfiguration {
    public static final Codec<NoisePickedFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            NoiseScale.CODEC.fieldOf("noise").forGetter(NoisePickedFeatureConfig::noiseScale),
            NoisePickedFeatureEntry.CODEC.listOf().fieldOf("entries").forGetter(NoisePickedFeatureConfig::entries)
    ).apply(instance, NoisePickedFeatureConfig::new));

    @Override
    public Stream<ConfiguredFeature<?, ?>> getFeatures() {
        return entries.stream().flatMap(entry -> entry.feature().value().getFeatures());
    }
}
