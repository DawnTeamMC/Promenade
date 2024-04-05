package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.SunkenSkeletonModel;
import fr.hugman.promenade.entity.SunkenEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class SunkenSkeletonRenderer extends BipedEntityRenderer<SunkenEntity, SunkenSkeletonModel> {
    public SunkenSkeletonRenderer(EntityRendererFactory.Context context) {
        this(context, PromenadeEntityModelLayers.SUNKEN, PromenadeEntityModelLayers.SUNKEN_INNER_ARMOR, PromenadeEntityModelLayers.SUNKEN_OUTER_ARMOR);
    }

    public SunkenSkeletonRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new SunkenSkeletonModel(ctx.getPart(layer)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, new SunkenSkeletonModel(ctx.getPart(legArmorLayer)), new SunkenSkeletonModel(ctx.getPart(bodyArmorLayer)), ctx.getModelManager()));
    }

    @Override
    protected void setupTransforms(SunkenEntity sunkenEntity, MatrixStack matrixStack, float animationProgress, float bodyYaw, float tickDelta, float scale) {
        float i = sunkenEntity.getLeaningPitch(tickDelta);
        float n;
        float k;
        if (sunkenEntity.isFallFlying()) {
            super.setupTransforms(sunkenEntity, matrixStack, animationProgress, bodyYaw, tickDelta, scale);
            n = (float) sunkenEntity.getFallFlyingTicks() + tickDelta;
            k = MathHelper.clamp(n * n / 100.0F, 0.0F, 1.0F);
            if (!sunkenEntity.isUsingRiptide()) {
                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(k * (-90.0F - sunkenEntity.getPitch())));
            }

            Vec3d vec3d = sunkenEntity.getRotationVec(tickDelta);
            Vec3d vec3d2 = sunkenEntity.getVelocity();
            double d = vec3d2.horizontalLengthSquared();
            double e = vec3d.horizontalLengthSquared();
            if (d > 0.0D && e > 0.0D) {
                double l = (vec3d2.x * vec3d.x + vec3d2.z * vec3d.z) / Math.sqrt(d * e);
                double m = vec3d2.x * vec3d.z - vec3d2.z * vec3d.x;
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotation((float) (Math.signum(m) * Math.acos(l))));
            }
        } else if (i > 0.0F) {
            super.setupTransforms(sunkenEntity, matrixStack, animationProgress, bodyYaw, tickDelta, scale);
            n = sunkenEntity.isTouchingWater() ? -90.0F - sunkenEntity.getPitch() : -90.0F;
            k = MathHelper.lerp(i, 0.0F, n);
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(k));
            if (sunkenEntity.isInSwimmingPose()) {
                matrixStack.translate(0.0D, -1.0D, 0.30000001192092896D);
            }
        } else {
            super.setupTransforms(sunkenEntity, matrixStack, animationProgress, bodyYaw, tickDelta, scale);
        }
    }

    @Override
    public Identifier getTexture(SunkenEntity entity) {
        return Promenade.id("textures/entity/sunken/" + entity.getVariant().getName() + ".png");
    }
}
