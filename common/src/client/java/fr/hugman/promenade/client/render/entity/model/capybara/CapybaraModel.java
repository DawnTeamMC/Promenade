package fr.hugman.promenade.client.render.entity.model.capybara;

import fr.hugman.promenade.client.render.entity.animation.CapybaraAnimations;
import fr.hugman.promenade.entity.Capybara;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;

public abstract class CapybaraModel extends HierarchicalModel<Capybara> {
    public static final String LOWER_TEETH = "lower_teeth";
    public static final String UPPER_TEETH = "upper_teeth";

    private final ModelPart root;
    private final ModelPart head;

    public CapybaraModel(ModelPart part) {
        this.root = part.getChild(PartNames.ROOT);
        this.head = this.root.getChild(PartNames.HEAD);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Capybara capybara, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        // Head
        if (capybara.canAngleHead()) {
            this.head.xRot = headPitch * (float) (Math.PI / 180.0);
            this.head.yRot = headYaw * (float) (Math.PI / 180.0);
        }

        // Dynamic animations
        this.animateWalk(CapybaraAnimations.WALKING, limbAngle, limbDistance, 4.0F, 2.5F);

        // Custom animations
        this.animate(capybara.earWiggleAnimState, CapybaraAnimations.EAR_WIGGLE, animationProgress, capybara.getEarWiggleSpeed());
        this.animate(capybara.fallToSleepAnimState, CapybaraAnimations.FALL_TO_SLEEP, animationProgress);
        this.animate(capybara.sleepingAnimState, CapybaraAnimations.SLEEP, animationProgress);
        this.animate(capybara.wakeUpAnimState, CapybaraAnimations.WAKE_UP, animationProgress);
        this.animate(capybara.fartAnimState, CapybaraAnimations.FART, animationProgress);
    }
}
