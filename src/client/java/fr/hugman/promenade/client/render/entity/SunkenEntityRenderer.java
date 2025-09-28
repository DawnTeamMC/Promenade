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

import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.SunkenEntityModel;
import fr.hugman.promenade.client.render.entity.state.SunkenEntityRenderState;
import fr.hugman.promenade.entity.SunkenEntity;

@Environment(EnvType.CLIENT)
public class SunkenEntityRenderer extends AbstractSkeletonEntityRenderer<SunkenEntity, SunkenEntityRenderState> {
	public SunkenEntityRenderer(EntityRendererFactory.Context context) {
		super(
				context,
				PromenadeEntityModelLayers.SUNKEN_EQUIPMENT,
				new SunkenEntityModel(context.getPart(PromenadeEntityModelLayers.SUNKEN))
		);
	}

	@Override
	public SunkenEntityRenderState createRenderState() {
		return new SunkenEntityRenderState();
	}

	@Override
	protected void setupTransforms(SunkenEntityRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
		float h = state.leaningPitch;
		float i = state.pitch;

		super.setupTransforms(state, matrices, bodyYaw, baseHeight);
		if (h > 0.0F) {
			float jx = state.touchingWater ? - 90.0F - i : - 90.0F;
			float k = MathHelper.lerp(h, 0.0F, jx);
			matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(k));
			if (state.isSwimming) {
				matrices.translate(0.0F, - 1.0F, 0.3F);
			}
		}
	}

	@Override
	public Identifier getTexture(SunkenEntityRenderState state) {
		if (state.variant == null) {
			return MissingSprite.getMissingSpriteId();
		}
		return state.variant.texture().texturePath();
	}

	@Override
	public void updateRenderState(SunkenEntity sunken, SunkenEntityRenderState state, float f) {
		super.updateRenderState(sunken, state, f);
		state.variant = sunken.getVariant().value();
	}
}
