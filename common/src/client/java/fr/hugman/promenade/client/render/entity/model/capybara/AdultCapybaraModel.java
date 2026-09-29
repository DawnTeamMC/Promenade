package fr.hugman.promenade.client.render.entity.model.capybara;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class AdultCapybaraModel extends CapybaraModel {
    public AdultCapybaraModel(ModelPart part) {
        super(part);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild(PartNames.ROOT, CubeListBuilder.create(), PartPose.offset(0.5F, 14.5F, 0.5F));

        root.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, CubeListBuilder.create().texOffs(31, 0).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), PartPose.offset(1.5F, 2.5F, 5.5F));
        root.addOrReplaceChild(PartNames.LEFT_HIND_LEG, CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), PartPose.offset(-2.5F, 2.5F, 5.5F));
        root.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, CubeListBuilder.create().texOffs(0, 36).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), PartPose.offset(1.5F, 4.5F, -4.5F));
        root.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, CubeListBuilder.create().texOffs(12, 36).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), PartPose.offset(-2.5F, 4.5F, -4.5F));

        root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -7.5F, 8.0F, 8.0F, 15.0F), PartPose.offsetAndRotation(-0.5F, 0.5F, 0.0F, 0.0873F, 0.0F, 0.0F));
        PartDefinition head = root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
                        .texOffs(20, 23).addBox(-3.0F, 1.0F, -5.0F, 6.0F, 1.0F, 5.0F)
                        .texOffs(0, 23).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 5.0F, 8.0F),
                PartPose.offset(-0.5F, -2.5F, -5.5F));
        PartDefinition jaw = head.addOrReplaceChild(PartNames.JAW, CubeListBuilder.create().texOffs(28, 29).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 3.0F), PartPose.offset(0.0F, 1.0F, -5.0F));

        head.addOrReplaceChild(PartNames.LEFT_EAR, CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(2.0F, -3.0F, -1.0F, -0.2182F, 0.2182F, 0.0F));
        head.addOrReplaceChild(PartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(6, 10).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-2.0F, -3.0F, -1.0F, -0.2182F, -0.2182F, 0.0F));
        head.addOrReplaceChild(UPPER_TEETH, CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -0.25F, 0.0F, 2.0F, 1.0F, 0.0F), PartPose.offset(0.0F, 1.0F, -7.0F));
        jaw.addOrReplaceChild(LOWER_TEETH, CubeListBuilder.create().texOffs(9, 1).addBox(-1.0F, -0.75F, 0.0F, 2.0F, 1.0F, 0.0F), PartPose.offset(0.0F, 0.0F, -2.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }
}