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
package fr.hugman.promenade.world.gen.placement_modifier;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class PromenadePlacementModifierTypes {
	public static final PlacementModifierType<NoiseIntervalCountPlacementModifier> NOISE_INTERVAL_COUNT = register("noise_interval_count", NoiseIntervalCountPlacementModifier.MODIFIER_CODEC);

	private static <P extends PlacementModifier> PlacementModifierType<P> register(String path, MapCodec<P> codec) {
		return Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, Promenade.id(path), () -> codec);
	}
}
