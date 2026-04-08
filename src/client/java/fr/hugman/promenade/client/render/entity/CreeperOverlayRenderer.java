package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;

public class CreeperOverlayRenderer<S extends CreeperRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
    private final CreeperModel model;
    private final Identifier texture;

    public CreeperOverlayRenderer(RenderLayerParent<S, M> context, EntityModelSet loader, ModelLayerLocation layer, Identifier texture) {
        super(context);
        this.model = new CreeperModel(loader.bakeLayer(layer));
        this.texture = texture;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, S entityRenderState, float f, float g) {
        coloredCutoutModelCopyLayerRender(this.model, this.texture, poseStack, submitNodeCollector, i, entityRenderState, -1, 1);
    }
}

