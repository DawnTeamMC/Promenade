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

import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import fr.hugman.promenade.client.render.entity.model.DuckEntityModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.state.DuckEntityRenderState;
import fr.hugman.promenade.entity.DuckEntity;

@Environment(EnvType.CLIENT)
public class DuckEntityRenderer extends AgeableMobEntityRenderer<DuckEntity, DuckEntityRenderState, DuckEntityModel> {
	public DuckEntityRenderer(EntityRendererFactory.Context context) {
		super(
				context,
				new DuckEntityModel(context.getPart(PromenadeEntityModelLayers.DUCK)),
				new DuckEntityModel(context.getPart(PromenadeEntityModelLayers.DUCK_BABY)),
				0.3F
		);
	}

	@Override
	public DuckEntityRenderState createRenderState() {
		return new DuckEntityRenderState();
	}

	@Override
	public Identifier getTexture(DuckEntityRenderState state) {
		if (state.variant == null) {
			return MissingSprite.getMissingSpriteId();
		}
		if (state.baby) {
			return state.variant.babyTexture().texturePath();
		}
		return state.variant.texture().texturePath();
	}

	public void updateRenderState(DuckEntity duck, DuckEntityRenderState state, float f) {
		super.updateRenderState(duck, state, f);
		state.flapProgress = MathHelper.lerp(f, duck.prevFlapProgress, duck.flapProgress);
		state.maxWingDeviation = MathHelper.lerp(f, duck.prevMaxWingDeviation, duck.maxWingDeviation);
		state.variant = duck.getVariant().value();
	}
}