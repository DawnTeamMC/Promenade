package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.animation.CapybaraAnimations;
import fr.hugman.promenade.client.render.entity.state.CapybaraEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class CapybaraEntityModel extends EntityModel<CapybaraEntityRenderState> {
    private static final String LOWER_TEETH = "lower_teeth";
    private static final String UPPER_TEETH = "upper_teeth";

    private final ModelPart head;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation earWiggleAnimation;
    private final KeyframeAnimation fallToSleepAnimation;
    private final KeyframeAnimation sleepingAnimation;
    private final KeyframeAnimation wakeUpAnimation;
    private final KeyframeAnimation fartAnimation;

    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false, 1.00F, 0.6F, Set.of(PartNames.HEAD));

    public CapybaraEntityModel(ModelPart part) {
        super(part.getChild(PartNames.ROOT));
        this.head = this.root.getChild(PartNames.HEAD);
        this.walkingAnimation = CapybaraAnimations.WALKING.bake(this.root);
        this.earWiggleAnimation = CapybaraAnimations.EAR_WIGGLE.bake(this.root);
        this.fallToSleepAnimation = CapybaraAnimations.FALL_TO_SLEEP.bake(this.root);
        this.sleepingAnimation = CapybaraAnimations.SLEEP.bake(this.root);
        this.wakeUpAnimation = CapybaraAnimations.WAKE_UP.bake(this.root);
        this.fartAnimation = CapybaraAnimations.FART.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild(PartNames.ROOT, CubeListBuilder.create(), PartPose.offset(0.5F, 14.5F, 0.5F));

        root.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, CubeListBuilder.create().texOffs(31, 0).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), PartPose.offset(1.5F, 2.5F, 5.5F));
        root.addOrReplaceChild(PartNames.LEFT_HIND_LEG, CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 7.0F, 3.0F), PartPose.offset(-2.5F, 2.5F, 5.5F));
        root.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, CubeListBuilder.create().texOffs(0, 36).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), PartPose.offset(1.5F, 4.5F, -4.5F));
        root.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, CubeListBuilder.create().texOffs(12, 36).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F), PartPose.offset(-2.5F, 4.5F, -4.5F));

        root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -7.5F, 8.0F, 8.0F, 15.0F), PartPose.offset(-0.5F, 0.5F, 0.0F));
        PartDefinition head = root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
                        .texOffs(20, 23).addBox(-3.0F, 1.0F, -5.0F, 6.0F, 1.0F, 5.0F)
                        .texOffs(0, 23).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 5.0F, 8.0F),
                PartPose.offsetAndRotation(-0.5F, -2.5F, -6.5F, -0.0436F, 0.0F, 0.0F));
        PartDefinition jaw = head.addOrReplaceChild(PartNames.JAW, CubeListBuilder.create().texOffs(28, 29).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 3.0F), PartPose.offset(0.0F, 1.0F, -5.0F));

        head.addOrReplaceChild(PartNames.LEFT_EAR, CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(2.0F, -3.0F, -1.0F, -0.2F, 0.2F, 0.0F));
        head.addOrReplaceChild(PartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(6, 10).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-2.0F, -3.0F, -1.0F, -0.2F, -0.2F, 0.0F));
        head.addOrReplaceChild(UPPER_TEETH, CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -0.25F, 0.0F, 2.0F, 1.0F, 0.0F), PartPose.offset(0.0F, 1.0F, -7.0F));
        jaw.addOrReplaceChild(LOWER_TEETH, CubeListBuilder.create().texOffs(9, 1).addBox(-1.0F, -0.75F, 0.0F, 2.0F, 1.0F, 0.0F), PartPose.offset(0.0F, 0.0F, -2.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(CapybaraEntityRenderState state) {
        super.setupAnim(state);

        // Head
        if (state.canAngleHead) {
            this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
            this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
        }

        // Dynamic animations
        this.walkingAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 4.0F, 2.5F);

        // Custom animations
        this.earWiggleAnimation.apply(state.earWiggleAnimState, state.ageInTicks, state.earWiggleSpeed);
        this.fallToSleepAnimation.apply(state.fallToSleepAnimState, state.ageInTicks);
        this.sleepingAnimation.apply(state.sleepingAnimState, state.ageInTicks);
        this.wakeUpAnimation.apply(state.wakeUpAnimState, state.ageInTicks);
        this.fartAnimation.apply(state.fartAnimState, state.ageInTicks);
    }
}