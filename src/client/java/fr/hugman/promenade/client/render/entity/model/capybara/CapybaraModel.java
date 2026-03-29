package fr.hugman.promenade.client.render.entity.model.capybara;

import fr.hugman.promenade.client.render.entity.animation.CapybaraAnimations;
import fr.hugman.promenade.client.render.entity.state.CapybaraEntityRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public abstract class CapybaraModel extends EntityModel<CapybaraEntityRenderState> {
    public static final String LOWER_TEETH = "lower_teeth";
    public static final String UPPER_TEETH = "upper_teeth";

    private final ModelPart head;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation earWiggleAnimation;
    private final KeyframeAnimation fallToSleepAnimation;
    private final KeyframeAnimation sleepingAnimation;
    private final KeyframeAnimation wakeUpAnimation;
    private final KeyframeAnimation fartAnimation;

    public CapybaraModel(ModelPart part) {
        super(part.getChild(PartNames.ROOT));
        this.head = this.root.getChild(PartNames.HEAD);
        this.walkingAnimation = CapybaraAnimations.WALKING.bake(this.root);
        this.earWiggleAnimation = CapybaraAnimations.EAR_WIGGLE.bake(this.root);
        this.fallToSleepAnimation = CapybaraAnimations.FALL_TO_SLEEP.bake(this.root);
        this.sleepingAnimation = CapybaraAnimations.SLEEP.bake(this.root);
        this.wakeUpAnimation = CapybaraAnimations.WAKE_UP.bake(this.root);
        this.fartAnimation = CapybaraAnimations.FART.bake(this.root);
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