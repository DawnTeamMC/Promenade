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
package fr.hugman.promenade.particle;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeParticleTypes {
	public static final SimpleParticleType BLUSH_SAKURA_BLOSSOM = register("blush_sakura_blossom", FabricParticleTypes.simple());
	public static final SimpleParticleType COTTON_SAKURA_BLOSSOM = register("cotton_sakura_blossom", FabricParticleTypes.simple());

	public static final SimpleParticleType VERMILION_MAPLE_LEAF = register("vermilion_maple_leaf", FabricParticleTypes.simple());
	public static final SimpleParticleType FULVOUS_MAPLE_LEAF = register("fulvous_maple_leaf", FabricParticleTypes.simple());
	public static final SimpleParticleType MIKADO_MAPLE_LEAF = register("mikado_maple_leaf", FabricParticleTypes.simple());

	public static <B extends ParticleType<?>> B register(String path, B particleType) {
		return Registry.register(Registries.PARTICLE_TYPE, Promenade.id(path), particleType);
	}
}
