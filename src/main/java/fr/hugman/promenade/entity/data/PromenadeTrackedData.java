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
package fr.hugman.promenade.entity.data;

import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.entry.RegistryEntry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.CapybaraEntity;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.SunkenVariant;

public class PromenadeTrackedData {
	public static final TrackedDataHandler<RegistryEntry<DuckVariant>> DUCK_VARIANT = of("duck_variant", DuckVariant.ENTRY_PACKET_CODEC);
	public static final TrackedDataHandler<RegistryEntry<CapybaraVariant>> CAPYBARA_VARIANT = of("capybara_variant", CapybaraVariant.ENTRY_PACKET_CODEC);
	public static final TrackedDataHandler<RegistryEntry<SunkenVariant>> SUNKEN_VARIANT = of("sunken_variant", SunkenVariant.ENTRY_PACKET_CODEC);

	public static final TrackedDataHandler<CapybaraEntity.State> CAPYBARA_STATE = of("capybara_state", CapybaraEntity.State.PACKET_CODEC);

	public static <T> TrackedDataHandler<T> of(String name, PacketCodec<? super RegistryByteBuf, T> codec) {
		var handler = TrackedDataHandler.create(codec);
		FabricTrackedDataRegistry.register(Promenade.id(name), handler);
		return handler;
	}
}
