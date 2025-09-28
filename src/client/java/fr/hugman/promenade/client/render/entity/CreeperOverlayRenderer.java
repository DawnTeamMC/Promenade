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

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CreeperOverlayRenderer<S extends CreeperEntityRenderState, M extends EntityModel<S>> extends FeatureRenderer<S, M> {
	private final CreeperEntityModel model;
	private final Identifier texture;

	public CreeperOverlayRenderer(FeatureRendererContext<S, M> context, LoadedEntityModels loader, EntityModelLayer layer, Identifier texture) {
		super(context);
		this.model = new CreeperEntityModel(loader.getModelPart(layer));
		this.texture = texture;
	}

	@Override
	public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, S state, float limbAngle, float limbDistance) {
		render(this.model, this.texture, matrices, queue, light, state, - 1, 1);
	}
}
