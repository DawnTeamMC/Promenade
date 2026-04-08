package fr.hugman.promenade.client.render.entity.model.duck;

import fr.hugman.promenade.client.render.entity.state.DuckRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class AdultDuckModel extends DuckModel {
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public AdultDuckModel(ModelPart root) {
        super(root);
        ModelPart body = root.getChild(PartNames.BODY);
        this.rightWing = body.getChild(PartNames.RIGHT_WING);
        this.leftWing = body.getChild(PartNames.LEFT_WING);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition root = modelData.getRoot();

        PartDefinition body = root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(8, 9).addBox(-3.0F, -1.5F, -5.0F, 6.0F, 3.0F, 10.0F), PartPose.offset(0.0F, 19.5F, 0.0F));

        body.addOrReplaceChild(PartNames.LEFT_WING, CubeListBuilder.create().texOffs(30, 8).addBox(0.0F, 0.0F, -4.0F, 1.0F, 3.0F, 8.0F), PartPose.offset(3.0F, -1.5F, 0.0F));
        body.addOrReplaceChild(PartNames.RIGHT_WING, CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 3.0F, 8.0F), PartPose.offset(-3.0F, -1.5F, 0.0F));
        body.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(12, 22).addBox(-1.5F, 0.0F, -3.5F, 3.0F, 3.0F, 3.0F), PartPose.offset(1.5F, 1.5F, 1.0F));
        body.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -0.5F, 3.0F, 3.0F, 3.0F), PartPose.offset(-1.0F, 1.5F, -2.0F));
        body.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
            .texOffs(24, 3).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 2.0F, 3.0F)
            .texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F)
            .texOffs(16, 2).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -1.5F, -2.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(DuckRenderState state) {
        super.setupAnim(state);

        // Wings
        float f = (Mth.sin(state.flapProgress) + 1.0F) * state.maxWingDeviation;
        this.rightWing.zRot = f;
        this.leftWing.zRot = -f;
    }
}