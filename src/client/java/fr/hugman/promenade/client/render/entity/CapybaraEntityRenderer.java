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
package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.CapybaraEntityModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.state.CapybaraEntityRenderState;
import fr.hugman.promenade.entity.CapybaraEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraEntityRenderer<E extends CapybaraEntity> extends AgeableMobEntityRenderer<E, CapybaraEntityRenderState, CapybaraEntityModel> {
	public CapybaraEntityRenderer(EntityRendererFactory.Context context) {
		super(
				context,
				new CapybaraEntityModel(context.getPart(PromenadeEntityModelLayers.CAPYBARA)),
				new CapybaraEntityModel(context.getPart(PromenadeEntityModelLayers.CAPYBARA_BABY)),
				0.5f
		);
	}

	@Override
	public CapybaraEntityRenderState createRenderState() {
		return new CapybaraEntityRenderState();
	}

	@Override
	public Identifier getTexture(CapybaraEntityRenderState state) {
		if (state.variant == null) {
			return MissingSprite.getMissingSpriteId();
		}
		if (state.closedEyes) {
			return state.variant.closedEyesTexture().texturePath();
		}
		return state.largeEyes ? state.variant.largeEyesTexture().texturePath() : state.variant.smallEyesTexture().texturePath();
	}

	@Override
	public void updateRenderState(E capybara, CapybaraEntityRenderState state, float f) {
		super.updateRenderState(capybara, state, f);
		state.earWiggleAnimState.copyFrom(capybara.earWiggleAnimState);
		state.fallToSleepAnimState.copyFrom(capybara.fallToSleepAnimState);
		state.sleepingAnimState.copyFrom(capybara.sleepingAnimState);
		state.wakeUpAnimState.copyFrom(capybara.wakeUpAnimState);
		state.fartAnimState.copyFrom(capybara.fartAnimState);

		state.variant = capybara.getVariant().value();
		state.closedEyes = capybara.hasClosedEyes();
		state.largeEyes = capybara.hasLargeEyes();
		state.earWiggleSpeed = capybara.getEarWiggleSpeed();
		state.canAngleHead = capybara.canAngleHead();
	}
}
