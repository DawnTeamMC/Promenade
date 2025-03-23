package fr.hugman.promenade.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

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
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CreeperEntityRenderState state, float limbAngle, float limbDistance) {
        render(this.model, this.texture, matrices, vertexConsumers, light, state, -1);
    }
}

