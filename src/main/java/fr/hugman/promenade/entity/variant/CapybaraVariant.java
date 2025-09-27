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

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
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

import java.util.List;

public record CapybaraVariant(
		TextureAssetInfo smallEyesTexture,
		TextureAssetInfo largeEyesTexture,
		TextureAssetInfo closedEyesTexture,
		SpawnConditionSelectors spawnConditions
) implements VariantSelectorProvider<SpawnContext, SpawnCondition> {
	public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			TextureAssetInfo.CODEC.fieldOf("small_eyes_texture").forGetter(CapybaraVariant::smallEyesTexture),
			TextureAssetInfo.CODEC.fieldOf("large_eyes_texture").forGetter(CapybaraVariant::largeEyesTexture),
			TextureAssetInfo.CODEC.fieldOf("closed_eyes_texture").forGetter(CapybaraVariant::closedEyesTexture),
			SpawnConditionSelectors.CODEC.fieldOf("spawn_conditions").forGetter(CapybaraVariant::spawnConditions)
	).apply(instance, CapybaraVariant::new));

	public static final Codec<CapybaraVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
			TextureAssetInfo.CODEC.fieldOf("small_eyes_texture").forGetter(CapybaraVariant::smallEyesTexture),
			TextureAssetInfo.CODEC.fieldOf("large_eyes_texture").forGetter(CapybaraVariant::largeEyesTexture),
			TextureAssetInfo.CODEC.fieldOf("closed_eyes_texture").forGetter(CapybaraVariant::closedEyesTexture)
	).apply(instance, CapybaraVariant::new));

	public static final Codec<RegistryEntry<CapybaraVariant>> ENTRY_CODEC = RegistryFixedCodec.of(PromenadeRegistryKeys.CAPYBARA_VARIANT);
	public static final PacketCodec<RegistryByteBuf, RegistryEntry<CapybaraVariant>> ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(PromenadeRegistryKeys.CAPYBARA_VARIANT);

	private CapybaraVariant(TextureAssetInfo smallEyesTexture, TextureAssetInfo largeEyesTexture, TextureAssetInfo closedEyesTexture) {
		this(smallEyesTexture, largeEyesTexture, closedEyesTexture, SpawnConditionSelectors.EMPTY);
	}

	@Override
	public List<Selector<SpawnContext, SpawnCondition>> getSelectors() {
		return this.spawnConditions.selectors();
	}
}
