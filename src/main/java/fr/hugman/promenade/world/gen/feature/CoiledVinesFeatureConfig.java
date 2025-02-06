package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;

public record CoiledVinesFeatureConfig(
        int spreadWidth,
        int spreadHeight,
        int maxLength,
        List<Direction> directions
) implements FeatureConfig {
    public static final Codec<CoiledVinesFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(

                    Codecs.POSITIVE_INT.fieldOf("spread_width").forGetter(CoiledVinesFeatureConfig::spreadWidth),
                    Codecs.POSITIVE_INT.fieldOf("spread_height").forGetter(CoiledVinesFeatureConfig::spreadHeight),
                    Codecs.POSITIVE_INT.fieldOf("max_length").forGetter(CoiledVinesFeatureConfig::maxLength),
                    Codecs.nonEmptyList(Direction.CODEC.listOf()).optionalFieldOf("directions", List.of(Direction.values())).forGetter(CoiledVinesFeatureConfig::directions)
            ).apply(instance, CoiledVinesFeatureConfig::new)
    );
}
