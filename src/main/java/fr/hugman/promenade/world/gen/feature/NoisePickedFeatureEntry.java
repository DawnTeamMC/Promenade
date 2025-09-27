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
import fr.hugman.promenade.codec.PromenadeCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.PlacedFeature;

public record NoisePickedFeatureEntry(
		RegistryEntry<PlacedFeature> feature,
		float min,
		float max
) {
	public static final Codec<NoisePickedFeatureEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			PlacedFeature.REGISTRY_CODEC.fieldOf("feature").forGetter(NoisePickedFeatureEntry::feature),
			PromenadeCodecs.SAMPLED_NOISE_VALUE.fieldOf("min").forGetter(NoisePickedFeatureEntry::min),
			PromenadeCodecs.SAMPLED_NOISE_VALUE.fieldOf("max").forGetter(NoisePickedFeatureEntry::max)
	).apply(instance, NoisePickedFeatureEntry::new));

	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos) {
		return this.feature.value().generateUnregistered(world, chunkGenerator, random, pos);
	}
}
