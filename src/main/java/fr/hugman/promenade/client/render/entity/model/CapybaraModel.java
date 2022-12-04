package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.animation.CapybaraAnimations;
import fr.hugman.promenade.entity.CapybaraEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class CapybaraModel<E extends CapybaraEntity> extends SinglePartEntityModel<E> {
	private static final String LOWER_TEETH = "lower_teeth";
	private static final String UPPER_TEETH = "upper_teeth";

	private static final float BABY_SCALE = 0.6f;
	private static final float BABY_Y_OFFSET = 1.0f;

	private final ModelPart root;
	private final ModelPart head;

	public CapybaraModel(ModelPart part) {
		this.root = part.getChild(EntityModelPartNames.ROOT);
		this.head = this.root.getChild(EntityModelPartNames.HEAD);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create(), ModelTransform.pivot(0.5F, 14.5F, 0.5F));

		root.addChild(EntityModelPartNames.RIGHT_HIND_LEG, ModelPartBuilder.create().uv(31, 0).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), ModelTransform.pivot(1.5F, 2.5F, 5.5F));
		root.addChild(EntityModelPartNames.LEFT_HIND_LEG, ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), ModelTransform.pivot(-2.5F, 2.5F, 5.5F));
		root.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create().uv(0, 36).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), ModelTransform.pivot(1.5F, 4.5F, -4.5F));
		root.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create().uv(12, 36).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), ModelTransform.pivot(-2.5F, 4.5F, -4.5F));

		root.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -7.5F, 8.0F, 8.0F, 15.0F), ModelTransform.pivot(-0.5F, 0.5F, 0.0F));
		ModelPartData head = root.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
						.uv(20, 23).cuboid(-3.0F, 1.0F, -5.0F, 6.0F, 1.0F, 5.0F)
						.uv(0, 23).cuboid(-3.0F, -4.0F, -8.0F, 6.0F, 5.0F, 8.0F),
				ModelTransform.of(-0.5F, -2.5F, -6.5F, -0.0436F, 0.0F, 0.0F));
		ModelPartData jaw = head.addChild(EntityModelPartNames.JAW, ModelPartBuilder.create().uv(28, 29).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 3.0F), ModelTransform.pivot(0.0F, 1.0F, -5.0F));

		head.addChild(EntityModelPartNames.LEFT_EAR, ModelPartBuilder.create().uv(0, 10).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), ModelTransform.of(2.0F, -3.0F, -1.0F, -0.2F, 0.2F, 0.0F));
		head.addChild(EntityModelPartNames.RIGHT_EAR, ModelPartBuilder.create().uv(6, 10).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), ModelTransform.of(-2.0F, -3.0F, -1.0F, -0.2F, -0.2F, 0.0F));
		head.addChild(UPPER_TEETH, ModelPartBuilder.create().uv(9, 0).cuboid(-1.0F, -0.25F, 0.0F, 2.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F, 1.0F, -7.0F));
		jaw.addChild(LOWER_TEETH, ModelPartBuilder.create().uv(9, 1).cuboid(-1.0F, -0.75F, 0.0F, 2.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		return TexturedModelData.of(modelData, 64, 64);
	}

	public void setAngles(E capybara, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);
		this.updateVisibleParts(capybara);
		this.updateAnimations(capybara, animationProgress);
	}

	public void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	private void updateVisibleParts(E entity) {
		//TODO: add haircuts
	}

	@Override
	public ModelPart getPart() {
		return this.root;
	}

	private void updateAnimations(E capybara, float progress) {
		float v = (float) capybara.getVelocity().horizontalLengthSquared();
		float speed = MathHelper.clamp(v * 400.0F, 0.3F, 2.0F);

		this.updateAnimation(capybara.walkingAnimationState, CapybaraAnimations.WALKING, progress, speed * 2);
		this.updateAnimation(capybara.earWiggleAnimState, CapybaraAnimations.EAR_WIGGLE, progress, 1.0F);
		this.updateAnimation(capybara.fallOverAnimState, CapybaraAnimations.FALL_OVER, progress, 1.0F);
		this.updateAnimation(capybara.sleepingAnimState, CapybaraAnimations.SLEEP, progress, 1.0F);
		this.updateAnimation(capybara.fartAnimState, CapybaraAnimations.FART, progress, 1.0F);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		if(this.child) {
			matrices.push();
			matrices.scale(BABY_SCALE, BABY_SCALE, BABY_SCALE);
			matrices.translate(0.0F, BABY_Y_OFFSET, 0.0F);
			this.getPart().render(matrices, vertices, light, overlay, red, green, blue, alpha);
			matrices.pop();
		}
		else {
			this.getPart().render(matrices, vertices, light, overlay, red, green, blue, alpha);
		}
	}
}