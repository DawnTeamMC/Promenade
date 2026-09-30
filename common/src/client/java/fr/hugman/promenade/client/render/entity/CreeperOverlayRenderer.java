package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;

public class CreeperOverlayRenderer<T extends Creeper, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final CreeperModel<T> model;
    private final ResourceLocation texture;

    public CreeperOverlayRenderer(RenderLayerParent<T, M> context, EntityModelSet loader, ModelLayerLocation layer, ResourceLocation texture) {
        super(context);
        this.model = new CreeperModel<>(loader.bakeLayer(layer));
        this.texture = texture;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffers, int light, T creeper, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, this.texture, poseStack, buffers, light, creeper, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, -1);
    }
}
