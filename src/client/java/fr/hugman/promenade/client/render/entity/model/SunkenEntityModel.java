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
package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.state.SunkenEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;

@Environment(EnvType.CLIENT)
public class SunkenEntityModel extends SkeletonEntityModel<SunkenEntityRenderState> {
	public SunkenEntityModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
		ModelPartData root = modelData.getRoot();
		SkeletonEntityModel.addLimbs(root);
		modelData.getRoot().getChild(EntityModelPartNames.HEAD)
				.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(- 4.0F, - 16.0F, - 4.0F, 16.0F, 16.0F, 0.0F), ModelTransform.origin(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void setAngles(SunkenEntityRenderState state) {
		super.setAngles(state);
		this.hat.visible = state.equippedHeadStack.isEmpty();
	}
}