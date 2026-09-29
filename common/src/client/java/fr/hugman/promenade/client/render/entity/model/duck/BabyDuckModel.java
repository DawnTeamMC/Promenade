package fr.hugman.promenade.client.render.entity.model.duck;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BabyDuckModel extends DuckModel {
    public BabyDuckModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition root = modelData.getRoot();

        PartDefinition body = root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 4).addBox(-1.5F, 0.5F, 0.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, -2.0F));

        body.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(4, 10).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.5F, 2.0F));
        body.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.5F, 2.0F));

        body.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
                        .texOffs(8, 10).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.5F, 1.0F));

        return LayerDefinition.create(modelData, 16, 16);
    }
}