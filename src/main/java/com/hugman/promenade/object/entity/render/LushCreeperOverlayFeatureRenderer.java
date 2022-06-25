/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package com.hugman.promenade.object.entity.render;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.client.PromenadeEntityModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LushCreeperOverlayFeatureRenderer<T extends CreeperEntity>
extends FeatureRenderer<T, CreeperEntityModel<T>> {
    private static final Identifier TEXTURE = Promenade.MOD_DATA.id("textures/entity/lush_creeper/overlay.png");
    private final CreeperEntityModel<T> model;

    public LushCreeperOverlayFeatureRenderer(FeatureRendererContext<T, CreeperEntityModel<T>> context, EntityModelLoader loader) {
        super(context);
        this.model = new CreeperEntityModel<>(loader.getModelPart(PromenadeEntityModelLayers.LUSH_CREEPER_OUTER));
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T creeperEntity, float f, float g, float h, float j, float k, float l) {
        LushCreeperOverlayFeatureRenderer.render(this.getContextModel(), this.model, TEXTURE, matrixStack, vertexConsumerProvider, i, creeperEntity, f, g, j, k, l, h, 1.0f, 1.0f, 1.0f);
    }
}

