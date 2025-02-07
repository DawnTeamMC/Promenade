package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.state.DuckEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BabyModelTransformer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.util.math.MathHelper;

import java.util.Set;

@Environment(EnvType.CLIENT)
public class DuckEntityModel extends EntityModel<DuckEntityRenderState> {
    public static final ModelTransformer BABY_TRANSFORMER = new BabyModelTransformer(Set.of(EntityModelPartNames.HEAD, EntityModelPartNames.BEAK));

    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public DuckEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.rightLeg = root.getChild(EntityModelPartNames.RIGHT_LEG);
        this.leftLeg = root.getChild(EntityModelPartNames.LEFT_LEG);
        this.rightWing = root.getChild(EntityModelPartNames.RIGHT_WING);
        this.leftWing = root.getChild(EntityModelPartNames.LEFT_WING);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        var head = root.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -10.0F, -2.0F, 4.0F, 10.0F, 3.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        head.addChild(EntityModelPartNames.BEAK, ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, -8.0F, -5.0F, 4.0F, 2.0F, 3.0F), ModelTransform.NONE);
        root.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
        root.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), ModelTransform.pivot(-2.0F, 19.0F, 1.0F));
        root.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F), ModelTransform.pivot(1.0F, 19.0F, 1.0F));
        root.addChild(EntityModelPartNames.RIGHT_WING, ModelPartBuilder.create().uv(24, 17).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(-4.0F, 13.0F, 0.0F));
        root.addChild(EntityModelPartNames.LEFT_WING, ModelPartBuilder.create().uv(24, 17).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(4.0F, 13.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    public void setAngles(DuckEntityRenderState state) {
        super.setAngles(state);

        // Head
        this.head.pitch = state.pitch * (float) (Math.PI / 180.0);
        this.head.yaw = state.yawDegrees * (float) (Math.PI / 180.0);

        // Legs
        float g = state.limbAmplitudeMultiplier;
        float h = state.limbFrequency;
        this.rightLeg.pitch = MathHelper.cos(h * 0.6662F) * 1.4F * g;
        this.leftLeg.pitch = MathHelper.cos(h * 0.6662F + (float) Math.PI) * 1.4F * g;

        // Wings
        float f = (MathHelper.sin(state.flapProgress) + 1.0F) * state.maxWingDeviation;
        this.rightWing.roll = f;
        this.leftWing.roll = -f;
    }
}