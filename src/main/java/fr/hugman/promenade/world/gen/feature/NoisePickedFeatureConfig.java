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
import java.util.stream.Stream;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;

import fr.hugman.promenade.util.NoiseScale;

public record NoisePickedFeatureConfig(
		NoiseScale noiseScale,
		List<NoisePickedFeatureEntry> entries
) implements FeatureConfig {
	public static final Codec<NoisePickedFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			NoiseScale.CODEC.fieldOf("noise").forGetter(NoisePickedFeatureConfig::noiseScale),
			NoisePickedFeatureEntry.CODEC.listOf().fieldOf("entries").forGetter(NoisePickedFeatureConfig::entries)
	).apply(instance, NoisePickedFeatureConfig::new));

	@Override
	public Stream<ConfiguredFeature<?, ?>> getDecoratedFeatures() {
		return entries.stream().flatMap(entry -> entry.feature().value().getDecoratedFeatures());
	}
}
