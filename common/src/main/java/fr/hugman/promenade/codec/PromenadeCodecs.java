package fr.hugman.promenade.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.function.Function;

public class PromenadeCodecs {
    public static final Codec<Float> SAMPLED_NOISE_VALUE = rangedInclusiveFloat(-1.0F, 1.0F);

    private static Codec<Float> rangedInclusiveFloat(float minInclusive, float maxInclusive, Function<Float, String> messageFactory) {
        return Codec.FLOAT.validate(
                value -> value.compareTo(minInclusive) >= 0 && value.compareTo(maxInclusive) <= 0
                        ? DataResult.success(value)
                        : DataResult.error(() -> messageFactory.apply(value))
        );
    }

    public static Codec<Float> rangedInclusiveFloat(float minInclusive, float maxInclusive) {
        return rangedInclusiveFloat(minInclusive, maxInclusive, value -> "Value must be within range [" + minInclusive + ";" + maxInclusive + "]: " + value);
    }
}

