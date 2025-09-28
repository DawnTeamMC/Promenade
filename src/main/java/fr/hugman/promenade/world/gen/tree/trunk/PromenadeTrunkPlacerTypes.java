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
package fr.hugman.promenade.world.gen.tree.trunk;

import com.mojang.serialization.MapCodec;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import fr.hugman.promenade.Promenade;

public class PromenadeTrunkPlacerTypes {
	public static final TrunkPlacerType<LeapingTrunkPlacer> LEAPING = register("leaping", LeapingTrunkPlacer.CODEC);
	public static final TrunkPlacerType<BranchingStraightTrunkPlacer> BRANCHING_STRAIGHT = register("branching_straight", BranchingStraightTrunkPlacer.CODEC);

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String path, MapCodec<P> codec) {
		return Registry.register(Registries.TRUNK_PLACER_TYPE, Promenade.id(path), new TrunkPlacerType<>(codec));
	}

}
