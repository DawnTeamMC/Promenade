package fr.hugman.promenade.client.render.entity.model.duck;

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

abstract public class DuckModel extends EntityModel<DuckEntityRenderState> {
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public DuckModel(ModelPart root) {
        super(root);
        ModelPart body = root.getChild(PartNames.BODY);
        this.head = body.getChild(PartNames.HEAD);
        this.rightLeg = body.getChild(PartNames.RIGHT_LEG);
        this.leftLeg = body.getChild(PartNames.LEFT_LEG);
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
    }
}