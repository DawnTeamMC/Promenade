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
package fr.hugman.promenade.world.gen.tree.foliage;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class PromenadeFoliagePlacerTypes {
	public static final FoliagePlacerType<PalmFoliagePlacer> PALM = register("palm", PalmFoliagePlacer.CODEC);
	public static final FoliagePlacerType<MapleFoliagePlacer> MAPLE = register("maple", MapleFoliagePlacer.CODEC);

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String path, MapCodec<P> codec) {
		return Registry.register(Registries.FOLIAGE_PLACER_TYPE, Promenade.id(path), new FoliagePlacerType<>(codec));
	}

}
