package com.hugman.promenade.object.entity.render;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.client.PromenadeEntityModelLayers;
import com.hugman.promenade.object.entity.SunkenSkeletonEntity;
import com.hugman.promenade.object.entity.model.SunkenSkeletonModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class SunkenSkeletonEntityRenderer extends BipedEntityRenderer<SunkenSkeletonEntity, SunkenSkeletonModel> {
	public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context context) {
		this(context, PromenadeEntityModelLayers.SUNKEN_SKELETON, PromenadeEntityModelLayers.SUNKEN_SKELETON_INNER_ARMOR, PromenadeEntityModelLayers.SUNKEN_SKELETON_OUTER_ARMOR);
	}

	public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, new SunkenSkeletonModel(ctx.getPart(layer)), 0.5F);
		this.addFeature(new ArmorFeatureRenderer<>(this, new SunkenSkeletonModel(ctx.getPart(legArmorLayer)), new SunkenSkeletonModel(ctx.getPart(bodyArmorLayer))));
	}

	@Override
	protected void setupTransforms(SunkenSkeletonEntity sunkenSkeletonEntity, MatrixStack matrixStack, float f, float g, float h) {
		float i = sunkenSkeletonEntity.getLeaningPitch(h);
		float n;
		float k;
		if(sunkenSkeletonEntity.isFallFlying()) {
			super.setupTransforms(sunkenSkeletonEntity, matrixStack, f, g, h);
			n = (float) sunkenSkeletonEntity.getRoll() + h;
			k = MathHelper.clamp(n * n / 100.0F, 0.0F, 1.0F);
			if(!sunkenSkeletonEntity.isUsingRiptide()) {
				matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(k * (-90.0F - sunkenSkeletonEntity.getPitch())));
			}

			Vec3d vec3d = sunkenSkeletonEntity.getRotationVec(h);
			Vec3d vec3d2 = sunkenSkeletonEntity.getVelocity();
			double d = vec3d2.horizontalLengthSquared();
			double e = vec3d.horizontalLengthSquared();
			if(d > 0.0D && e > 0.0D) {
				double l = (vec3d2.x * vec3d.x + vec3d2.z * vec3d.z) / Math.sqrt(d * e);
				double m = vec3d2.x * vec3d.z - vec3d2.z * vec3d.x;
				matrixStack.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion((float) (Math.signum(m) * Math.acos(l))));
			}
		}
		else if(i > 0.0F) {
			super.setupTransforms(sunkenSkeletonEntity, matrixStack, f, g, h);
			n = sunkenSkeletonEntity.isTouchingWater() ? -90.0F - sunkenSkeletonEntity.getPitch() : -90.0F;
			k = MathHelper.lerp(i, 0.0F, n);
			matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(k));
			if(sunkenSkeletonEntity.isInSwimmingPose()) {
				matrixStack.translate(0.0D, -1.0D, 0.30000001192092896D);
			}
		}
		else {
			super.setupTransforms(sunkenSkeletonEntity, matrixStack, f, g, h);
		}
	}

	@Override
	public Identifier getTexture(SunkenSkeletonEntity entity) {
		return Promenade.MOD_DATA.id("textures/entity/sunken_skeleton/" + entity.getVariant().getName() + ".png");
	}
}
