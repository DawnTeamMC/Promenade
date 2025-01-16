package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.animation.CapybaraAnimations;
import fr.hugman.promenade.client.render.entity.state.CapybaraEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BabyModelTransformer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ModelTransformer;

import java.util.Set;

@Environment(EnvType.CLIENT)
public class CapybaraEntityModel extends EntityModel<CapybaraEntityRenderState> {
    private static final String LOWER_TEETH = "lower_teeth";
    private static final String UPPER_TEETH = "upper_teeth";

    private final ModelPart head;

    public static final ModelTransformer BABY_TRANSFORMER = new BabyModelTransformer(false, 1.00F, 0.6F, Set.of(EntityModelPartNames.HEAD));

    public CapybaraEntityModel(ModelPart part) {
        super(part.getChild(EntityModelPartNames.ROOT));
        this.head = this.root.getChild(EntityModelPartNames.HEAD);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create(), ModelTransform.pivot(0.5F, 14.5F, 0.5F));

        root.addChild(EntityModelPartNames.RIGHT_HIND_LEG, ModelPartBuilder.create().uv(31, 0).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), ModelTransform.pivot(1.5F, 2.5F, 5.5F));
        root.addChild(EntityModelPartNames.LEFT_HIND_LEG, ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), ModelTransform.pivot(-2.5F, 2.5F, 5.5F));
        root.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create().uv(0, 36).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), ModelTransform.pivot(1.5F, 4.5F, -4.5F));
        root.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create().uv(12, 36).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), ModelTransform.pivot(-2.5F, 4.5F, -4.5F));

        root.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -7.5F, 8.0F, 8.0F, 15.0F), ModelTransform.pivot(-0.5F, 0.5F, 0.0F));
        ModelPartData head = root.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
                        .uv(20, 23).cuboid(-3.0F, 1.0F, -5.0F, 6.0F, 1.0F, 5.0F)
                        .uv(0, 23).cuboid(-3.0F, -4.0F, -8.0F, 6.0F, 5.0F, 8.0F),
                ModelTransform.of(-0.5F, -2.5F, -6.5F, -0.0436F, 0.0F, 0.0F));
        ModelPartData jaw = head.addChild(EntityModelPartNames.JAW, ModelPartBuilder.create().uv(28, 29).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 3.0F), ModelTransform.pivot(0.0F, 1.0F, -5.0F));

        head.addChild(EntityModelPartNames.LEFT_EAR, ModelPartBuilder.create().uv(0, 10).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), ModelTransform.of(2.0F, -3.0F, -1.0F, -0.2F, 0.2F, 0.0F));
        head.addChild(EntityModelPartNames.RIGHT_EAR, ModelPartBuilder.create().uv(6, 10).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), ModelTransform.of(-2.0F, -3.0F, -1.0F, -0.2F, -0.2F, 0.0F));
        head.addChild(UPPER_TEETH, ModelPartBuilder.create().uv(9, 0).cuboid(-1.0F, -0.25F, 0.0F, 2.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F, 1.0F, -7.0F));
        jaw.addChild(LOWER_TEETH, ModelPartBuilder.create().uv(9, 1).cuboid(-1.0F, -0.75F, 0.0F, 2.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(CapybaraEntityRenderState state) {
        super.setAngles(state);

        // Head
        if(state.canAngleHead) {
            this.head.pitch = state.pitch * (float) (Math.PI / 180.0);
            this.head.yaw = state.yawDegrees * (float) (Math.PI / 180.0);
        }

        // Dynamic animations
        this.animateWalking(CapybaraAnimations.WALKING, state.limbFrequency, state.limbAmplitudeMultiplier, 4.0F, 2.5F);

        // Custom animations
        this.animate(state.earWiggleAnimState, CapybaraAnimations.EAR_WIGGLE, state.age, state.earWiggleSpeed);
        this.animate(state.fallToSleepAnimState, CapybaraAnimations.FALL_TO_SLEEP, state.age);
        this.animate(state.sleepingAnimState, CapybaraAnimations.SLEEP, state.age);
        this.animate(state.wakeUpAnimState, CapybaraAnimations.WAKE_UP, state.age);
        this.animate(state.fartAnimState, CapybaraAnimations.FART, state.age);
    }
}