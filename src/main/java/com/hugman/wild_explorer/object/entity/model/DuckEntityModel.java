package com.hugman.wild_explorer.object.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class DuckEntityModel<T extends LivingEntity> extends AnimalModel<T> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart beak;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public DuckEntityModel(ModelPart root) {
		this.head = root.getChild("head");
		this.beak = root.getChild("beak");
		this.body = root.getChild("body");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
		this.rightWing = root.getChild("right_wing");
		this.leftWing = root.getChild("left_wing");
	}

	public static TexturedModelData createModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -10.0F, -2.0F, 4.0F, 10.0F, 3.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		modelPartData.addChild("beak", ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, -8.0F, -5.0F, 4.0F, 2.0F, 3.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
		modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), ModelTransform.pivot(-2.0F, 19.0F, 1.0F));
		modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), ModelTransform.pivot(1.0F, 19.0F, 1.0F));
		modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(24, 17).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(-4.0F, 13.0F, 0.0F));
		modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(24, 17).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(4.0F, 13.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head, this.beak);
	}

	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.rightLeg, this.leftLeg, this.rightWing, this.leftWing);
	}

	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.pitch = headPitch * ((float) Math.PI / 180F);
		this.head.yaw = headYaw * ((float) Math.PI / 180F);
		this.beak.pitch = this.head.pitch;
		this.beak.yaw = this.head.yaw;
		this.body.pitch = ((float) Math.PI / 2F);
		this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
		this.rightWing.roll = animationProgress;
		this.leftWing.roll = -animationProgress;
	}
}