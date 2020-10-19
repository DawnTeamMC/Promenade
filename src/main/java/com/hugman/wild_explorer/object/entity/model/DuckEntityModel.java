package com.hugman.wild_explorer.object.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class DuckEntityModel<T extends LivingEntity> extends AnimalModel<T> {
	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart beak;

	public DuckEntityModel() {
		head = new ModelPart(this, 0, 0);
		head.addCuboid(-2.0F, -10.0F, -2.0F, 4.0F, 10.0F, 3.0F, 0.0F);
		head.setPivot(0.0F, 15.0F, -4.0F);
		beak = new ModelPart(this, 14, 0);
		beak.addCuboid(-2.0F, -8.0F, -5.0F, 4.0F, 2.0F, 3.0F, 0.0F);
		torso = new ModelPart(this, 0, 13);
		torso.addCuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F);
		torso.setPivot(0.0F, 16.0F, 0.0F);
		rightLeg = new ModelPart(this, 28, 0);
		rightLeg.addCuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
		rightLeg.setPivot(-2.0F, 19.0F, 1.0F);
		leftLeg = new ModelPart(this, 28, 0);
		leftLeg.addCuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
		leftLeg.setPivot(1.0F, 19.0F, 1.0F);
		rightWing = new ModelPart(this, 24, 17);
		rightWing.addCuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F);
		rightWing.setPivot(-4.0F, 13.0F, 0.0F);
		leftWing = new ModelPart(this, 24, 17);
		leftWing.addCuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F);
		leftWing.setPivot(4.0F, 13.0F, 0.0F);
	}

	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(head, beak);
	}

	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(torso, rightLeg, leftLeg, rightWing, leftWing);
	}

	public void setAngles(T entity, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		head.pitch = p_225597_6_ * ((float) Math.PI / 180F);
		head.yaw = p_225597_5_ * ((float) Math.PI / 180F);
		beak.copyPositionAndRotation(head);
		torso.pitch = ((float) Math.PI / 2F);
		rightLeg.pitch = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
		leftLeg.pitch = MathHelper.cos(p_225597_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_225597_3_;
		rightWing.roll = p_225597_4_;
		leftWing.roll = -p_225597_4_;
	}
}