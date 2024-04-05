package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.entity.SunkenEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Arm;

@Environment(EnvType.CLIENT)
public class SunkenSkeletonModel extends SkeletonEntityModel<SunkenEntity> {
    public SunkenSkeletonModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -16.0F, -4.0F, 16.0F, 16.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void animateModel(SunkenEntity mobEntity, float f, float g, float h) {
        super.animateModel(mobEntity, f, g, h);

        this.hat.visible = mobEntity.getEquippedStack(EquipmentSlot.HEAD).isEmpty();

        this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
        this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
        ArmPose armPose = switch (mobEntity.getState()) {
            default -> ArmPose.EMPTY;
            case BOW_HOLD -> ArmPose.BOW_AND_ARROW;
            case CROSSBOW_CHARGE -> ArmPose.CROSSBOW_CHARGE;
            case CROSSBOW_HOLD -> ArmPose.CROSSBOW_HOLD;
        };
        if (mobEntity.getMainArm() == Arm.RIGHT) {
            this.rightArmPose = armPose;
        } else {
            this.leftArmPose = armPose;
        }
    }
}