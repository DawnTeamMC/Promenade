package fr.hugman.promenade.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class DuckModel<T extends LivingEntity> extends AnimalModel<T> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart beak;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public DuckModel(ModelPart root) {
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.beak = root.getChild(EntityModelPartNames.BEAK);
        this.body = root.getChild(EntityModelPartNames.BODY);
        this.rightLeg = root.getChild(EntityModelPartNames.RIGHT_LEG);
        this.leftLeg = root.getChild(EntityModelPartNames.LEFT_LEG);
        this.rightWing = root.getChild(EntityModelPartNames.RIGHT_WING);
        this.leftWing = root.getChild(EntityModelPartNames.LEFT_WING);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -10.0F, -2.0F, 4.0F, 10.0F, 3.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        modelPartData.addChild(EntityModelPartNames.BEAK, ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, -8.0F, -5.0F, 4.0F, 2.0F, 3.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), ModelTransform.pivot(-2.0F, 19.0F, 1.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), ModelTransform.pivot(1.0F, 19.0F, 1.0F));
        modelPartData.addChild(EntityModelPartNames.RIGHT_WING, ModelPartBuilder.create().uv(24, 17).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(-4.0F, 13.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_WING, ModelPartBuilder.create().uv(24, 17).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(4.0F, 13.0F, 0.0F));
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