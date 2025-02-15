package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;

public record NoisePickedFeatureConfig(
        List<NoisePickedFeatureEntry> entries
) implements FeatureConfig {
    public static final Codec<NoisePickedFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            NoisePickedFeatureEntry.CODEC.listOf().fieldOf("entries").forGetter(NoisePickedFeatureConfig::entries)
    ).apply(instance, NoisePickedFeatureConfig::new));
}
