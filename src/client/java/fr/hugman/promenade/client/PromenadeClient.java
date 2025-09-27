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
package fr.hugman.promenade.client;

import fr.hugman.promenade.client.color.PromenadeBlockColors;
import fr.hugman.promenade.client.particle.PromenadeParticleFactories;
import fr.hugman.promenade.client.render.block.PromenadeBlockRenderLayers;
import fr.hugman.promenade.client.render.entity.PromenadeEntityRenderers;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import net.fabricmc.api.ClientModInitializer;

public class PromenadeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PromenadeEntityModelLayers.register();
		PromenadeBlockRenderLayers.register();
		PromenadeBlockColors.register();
		PromenadeEntityRenderers.register();
		PromenadeParticleFactories.register();
	}
}