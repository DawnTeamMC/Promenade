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
package fr.hugman.promenade.block.snowy;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;

public record SnowyBlockTransformation(RegistryEntryList<Block> baseBlocks, RegistryEntry<Block> snowyBlock) {
	public static final Codec<SnowyBlockTransformation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("base_blocks").forGetter(SnowyBlockTransformation::baseBlocks),
			Registries.BLOCK.getEntryCodec().fieldOf("snowy_block").forGetter(SnowyBlockTransformation::snowyBlock)
	).apply(instance, SnowyBlockTransformation::new));

	public SnowyBlockTransformation(RegistryEntry<Block> baseBlock, RegistryEntry<Block> snowyBlock) {
		this(RegistryEntryList.of(baseBlock), snowyBlock);
	}
}
