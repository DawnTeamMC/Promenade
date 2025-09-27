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
package fr.hugman.promenade.client.particle;

import fr.hugman.promenade.particle.PromenadeParticleTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.LeavesParticle;

@Environment(EnvType.CLIENT)
public class PromenadeParticleFactories {
	public static void register() {
		var instance = ParticleFactoryRegistry.getInstance();

		instance.register(PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM, LeavesParticle.CherryLeavesFactory::new);
		instance.register(PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM, LeavesParticle.CherryLeavesFactory::new);

		instance.register(PromenadeParticleTypes.MIKADO_MAPLE_LEAF, PromenadeLeavesParticleFactories.MapleLeavesFactory::new);
		instance.register(PromenadeParticleTypes.FULVOUS_MAPLE_LEAF, PromenadeLeavesParticleFactories.MapleLeavesFactory::new);
		instance.register(PromenadeParticleTypes.VERMILION_MAPLE_LEAF, PromenadeLeavesParticleFactories.MapleLeavesFactory::new);
	}
}
