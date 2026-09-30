package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.SunkenEntityModel;
import fr.hugman.promenade.entity.Sunken;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SunkenEntityRenderer extends SkeletonRenderer<Sunken> {
    public SunkenEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                ModelLayers.STRAY_INNER_ARMOR,
                ModelLayers.STRAY_OUTER_ARMOR,
                new SunkenEntityModel(context.bakeLayer(PromenadeEntityModelLayers.SUNKEN))
        );
    }

    @Override
    protected void setupRotations(Sunken sunken, PoseStack poseStack, float animationProgress, float bodyYaw, float tickDelta, float scale) {
        float h = sunken.getSwimAmount(tickDelta);
        float i = sunken.getViewXRot(tickDelta);

        super.setupRotations(sunken, poseStack, animationProgress, bodyYaw, tickDelta, scale);
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
    public ResourceLocation getTextureLocation(Sunken sunken) {
        return sunken.getVariant().value().texture().texturePath();
    }
}
