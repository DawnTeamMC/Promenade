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
package fr.hugman.promenade.entity.variant;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.util.AssetInfo.TextureAssetInfo;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;

public record DuckVariant(
		TextureAssetInfo texture,
		TextureAssetInfo babyTexture,
		SpawnConditionSelectors spawnConditions
) implements VariantSelectorProvider<SpawnContext, SpawnCondition> {
	public static final TextureAssetInfo DEFAULT_DUCKLING_ASSET = new TextureAssetInfo(Promenade.id("entity/duck/duckling"));

	public static final Codec<DuckVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			TextureAssetInfo.MAP_CODEC.forGetter(DuckVariant::texture),
			TextureAssetInfo.CODEC.optionalFieldOf("baby_texture", DEFAULT_DUCKLING_ASSET).forGetter(DuckVariant::babyTexture),
			SpawnConditionSelectors.CODEC.fieldOf("spawn_conditions").forGetter(DuckVariant::spawnConditions)
	).apply(instance, DuckVariant::new));

	public static final Codec<DuckVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
			TextureAssetInfo.MAP_CODEC.forGetter(DuckVariant::texture),
			TextureAssetInfo.CODEC.optionalFieldOf("baby_texture", DEFAULT_DUCKLING_ASSET).forGetter(DuckVariant::babyTexture)
	).apply(instance, DuckVariant::new));

	public static final Codec<RegistryEntry<DuckVariant>> ENTRY_CODEC = RegistryFixedCodec.of(PromenadeRegistryKeys.DUCK_VARIANT);
	public static final PacketCodec<RegistryByteBuf, RegistryEntry<DuckVariant>> ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(PromenadeRegistryKeys.DUCK_VARIANT);

	public DuckVariant(TextureAssetInfo texture, TextureAssetInfo babyTexture) {
		this(texture, babyTexture, SpawnConditionSelectors.EMPTY);
	}

	@Override
	public List<Selector<SpawnContext, SpawnCondition>> getSelectors() {
		return this.spawnConditions.selectors();
	}
}
