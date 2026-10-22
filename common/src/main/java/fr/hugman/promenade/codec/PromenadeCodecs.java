package fr.hugman.promenade.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Function;

public class PromenadeCodecs {
    public static final Codec<Float> SAMPLED_NOISE_VALUE = rangedInclusiveFloat(-1.0F, 1.0F);
    /**
     * 1.20.1 bounds are only read from JSON: this reads them the same way, as a number or as {@code min} and {@code max}.
     */
    public static final Codec<MinMaxBounds.Doubles> DOUBLE_BOUNDS = ExtraCodecs.JSON.xmap(MinMaxBounds.Doubles::fromJson, MinMaxBounds::serializeToJson);

    private static Codec<Float> rangedInclusiveFloat(float minInclusive, float maxInclusive, Function<Float, String> messageFactory) {
        return ExtraCodecs.validate(Codec.FLOAT,
                value -> value.compareTo(minInclusive) >= 0 && value.compareTo(maxInclusive) <= 0
                        ? DataResult.success(value)
                        : DataResult.error(() -> messageFactory.apply(value))
        );
    }

    public static Codec<Float> rangedInclusiveFloat(float minInclusive, float maxInclusive) {
        return rangedInclusiveFloat(minInclusive, maxInclusive, value -> "Value must be within range [" + minInclusive + ";" + maxInclusive + "]: " + value);
    }
}

