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
package fr.hugman.promenade.world.gen.feature;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.FeatureConfig;

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
