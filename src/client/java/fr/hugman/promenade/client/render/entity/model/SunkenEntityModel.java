package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.state.SunkenRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;

public class SunkenEntityModel extends SkeletonModel<SunkenRenderState> {
    public SunkenEntityModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = modelData.getRoot();
        SkeletonModel.createDefaultSkeletonMesh(root);
        modelData.getRoot().getChild(PartNames.HEAD)
                .addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -16.0F, -4.0F, 16.0F, 16.0F, 0.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 32);
    }

    @Override
    public void setupAnim(SunkenRenderState state) {
        super.setupAnim(state);
        this.hat.visible = state.headEquipment.isEmpty();
    }
}