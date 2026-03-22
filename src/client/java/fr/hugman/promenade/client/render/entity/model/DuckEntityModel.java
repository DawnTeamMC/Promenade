package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.state.DuckEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class DuckEntityModel extends EntityModel<DuckEntityRenderState> {
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(Set.of(PartNames.HEAD, PartNames.BEAK));

    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public DuckEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild(PartNames.HEAD);
        this.rightLeg = root.getChild(PartNames.RIGHT_LEG);
        this.leftLeg = root.getChild(PartNames.LEFT_LEG);
        this.rightWing = root.getChild(PartNames.RIGHT_WING);
        this.leftWing = root.getChild(PartNames.LEFT_WING);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition root = modelData.getRoot();

        var head = root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 10.0F, 3.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        head.addOrReplaceChild(PartNames.BEAK, CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -8.0F, -5.0F, 4.0F, 2.0F, 3.0F), PartPose.ZERO);
        root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F), PartPose.offset(0.0F, 16.0F, 0.0F));
        root.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), PartPose.offset(-2.0F, 19.0F, 1.0F));
        root.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), PartPose.offset(1.0F, 19.0F, 1.0F));
        root.addOrReplaceChild(PartNames.RIGHT_WING, CubeListBuilder.create().texOffs(24, 17).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(-4.0F, 13.0F, 0.0F));
        root.addOrReplaceChild(PartNames.LEFT_WING, CubeListBuilder.create().texOffs(24, 17).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(4.0F, 13.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 32);
    }

    @Override
    public void setupAnim(DuckEntityRenderState state) {
        super.setupAnim(state);

        // Head
        this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
        this.head.yRot = state.yRot * (float) (Math.PI / 180.0);

        // Legs
        float g = state.walkAnimationSpeed;
        float h = state.walkAnimationPos;
        this.rightLeg.xRot = Mth.cos(h * 0.6662F) * 1.4F * g;
        this.leftLeg.xRot = Mth.cos(h * 0.6662F + (float) Math.PI) * 1.4F * g;

        // Wings
        float f = (Mth.sin(state.flapProgress) + 1.0F) * state.maxWingDeviation;
        this.rightWing.zRot = f;
        this.leftWing.zRot = -f;
    }
}