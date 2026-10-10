package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.entity.variant.CapybaraVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import org.jetbrains.annotations.Nullable;

public class CapybaraRenderState extends LivingEntityRenderState {
    public final AnimationState earWiggleAnimationState = new AnimationState();
    public final AnimationState fallToSleepAnimationState = new AnimationState();
    public final AnimationState sleepingAnimationState = new AnimationState();
    public final AnimationState wakeUpAnimationState = new AnimationState();
    public final AnimationState fartAnimationState = new AnimationState();

    @Nullable
    public CapybaraVariant variant;
    public boolean sleeping = false;
    public boolean surprised = false;
    public float earWiggleSpeed = 1.0f;

    public boolean canAngleHead = true;
}
