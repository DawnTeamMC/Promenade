/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
package fr.hugman.promenade.codec;

import java.util.function.Function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

public class PromenadeCodecs {
	public static final Codec<Float> SAMPLED_NOISE_VALUE = rangedInclusiveFloat(- 1.0F, 1.0F);

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
