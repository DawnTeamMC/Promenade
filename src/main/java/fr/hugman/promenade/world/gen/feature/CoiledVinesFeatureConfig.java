package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record CoiledVinesFeatureConfig(int spreadWidth, int spreadHeight, int maxHeight) implements FeatureConfig {
    public static final Codec<CoiledVinesFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codecs.POSITIVE_INT.fieldOf("spread_width").forGetter(CoiledVinesFeatureConfig::spreadWidth),
                            Codecs.POSITIVE_INT.fieldOf("spread_height").forGetter(CoiledVinesFeatureConfig::spreadHeight),
                            Codecs.POSITIVE_INT.fieldOf("max_height").forGetter(CoiledVinesFeatureConfig::maxHeight)
                    )
                    .apply(instance, CoiledVinesFeatureConfig::new)
    );
}
