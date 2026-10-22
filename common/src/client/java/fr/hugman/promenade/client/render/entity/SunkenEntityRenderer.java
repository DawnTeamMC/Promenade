package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.SunkenEntityModel;
import fr.hugman.promenade.entity.Sunken;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Mirrors vanilla's {@link net.minecraft.client.renderer.entity.SkeletonRenderer}, which only renders the vanilla
 * skeleton model in 1.20.1.
 */
public class SunkenEntityRenderer extends HumanoidMobRenderer<Sunken, SunkenEntityModel> {
    public SunkenEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SunkenEntityModel(context.bakeLayer(PromenadeEntityModelLayers.SUNKEN)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new SkeletonModel<>(context.bakeLayer(ModelLayers.STRAY_INNER_ARMOR)),
                new SkeletonModel<>(context.bakeLayer(ModelLayers.STRAY_OUTER_ARMOR)),
                context.getModelManager()));
    }

    @Override
    protected void setupRotations(Sunken sunken, PoseStack poseStack, float animationProgress, float bodyYaw, float tickDelta) {
        float h = sunken.getSwimAmount(tickDelta);
        float i = sunken.getViewXRot(tickDelta);

        super.setupRotations(sunken, poseStack, animationProgress, bodyYaw, tickDelta);
        if (h > 0.0F) {
            float jx = sunken.isInWater() ? -90.0F - i : -90.0F;
            float k = Mth.lerp(h, 0.0F, jx);
            poseStack.mulPose(Axis.XP.rotationDegrees(k));
            if (sunken.isVisuallySwimming()) {
                poseStack.translate(0.0F, -1.0F, 0.3F);
            }
        }
    }

    @Override
    protected boolean isShaking(Sunken sunken) {
        return sunken.isShaking();
    }

    @Override
    public ResourceLocation getTextureLocation(Sunken sunken) {
        return sunken.getVariant().value().texture().texturePath();
    }
}
