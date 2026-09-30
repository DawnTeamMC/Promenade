package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record CoiledVinesFeatureConfig(
        int spreadWidth,
        int spreadHeight,
        int maxLength,
        List<Direction> directions
) implements FeatureConfiguration {
    public static final Codec<CoiledVinesFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(

                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(CoiledVinesFeatureConfig::spreadWidth),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(CoiledVinesFeatureConfig::spreadHeight),
                    ExtraCodecs.POSITIVE_INT.fieldOf("max_length").forGetter(CoiledVinesFeatureConfig::maxLength),
                    ExtraCodecs.nonEmptyList(Direction.CODEC.listOf()).optionalFieldOf("directions", List.of(Direction.values())).forGetter(CoiledVinesFeatureConfig::directions)
            ).apply(instance, CoiledVinesFeatureConfig::new)
    );
}
