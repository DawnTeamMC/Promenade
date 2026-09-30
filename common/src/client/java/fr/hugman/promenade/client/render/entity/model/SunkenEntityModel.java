package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.entity.Sunken;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.EquipmentSlot;

public class SunkenEntityModel extends SkeletonModel<Sunken> {
    public SunkenEntityModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = modelData.getRoot();
        // same limbs as SkeletonModel#createBodyLayer
        root.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        root.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
        // the coral on the head; in 1.21.1 the hat follows the head without being its child
        root.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -16.0F, -4.0F, 16.0F, 16.0F, 0.0F), PartPose.ZERO);
        return LayerDefinition.create(modelData, 64, 32);
    }

    @Override
    public void setupAnim(Sunken sunken, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setupAnim(sunken, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.hat.visible = sunken.getItemBySlot(EquipmentSlot.HEAD).isEmpty();
    }
}
