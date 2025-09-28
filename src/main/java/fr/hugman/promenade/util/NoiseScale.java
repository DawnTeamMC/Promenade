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
package fr.hugman.promenade.util;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.dynamic.Codecs;

public record NoiseScale(float x, float z) {
	private static final Codec<NoiseScale> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codecs.POSITIVE_FLOAT.fieldOf("x").forGetter(NoiseScale::x),
			Codecs.POSITIVE_FLOAT.fieldOf("z").forGetter(NoiseScale::z)
	).apply(instance, NoiseScale::new));

	public static final Codec<NoiseScale> CODEC = Codec.either(Codecs.POSITIVE_FLOAT, DIRECT_CODEC).xmap(
			either -> either.map(value -> new NoiseScale(value, value), scale -> scale),
			scale -> scale.x == scale.z ? Either.left(scale.x) : Either.right(scale)
	);

	public static NoiseScale of(float value) {
		return new NoiseScale(value, value);
	}
}