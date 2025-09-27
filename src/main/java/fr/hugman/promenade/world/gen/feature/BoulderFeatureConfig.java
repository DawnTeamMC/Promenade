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

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record BoulderFeatureConfig(
		BlockStateProvider stateProvider,
		BlockPredicate replaceableBlocks,
		IntProvider radius
) implements FeatureConfig {
	public static final Codec<BoulderFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> config.stateProvider),
			BlockPredicate.BASE_CODEC.fieldOf("replaceable").forGetter((config) -> config.replaceableBlocks),
			IntProvider.createValidatingCodec(1, 64).fieldOf("count").forGetter((config) -> config.radius)
	).apply(instance, BoulderFeatureConfig::new));
}