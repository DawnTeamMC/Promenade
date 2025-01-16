package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.Promenade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraEntityRenderState extends LivingEntityRenderState {
    private static final Identifier DEFAULT_TEXTURE = Promenade.id("textures/entity/capybara/brown/small_eyes.png");

    public final AnimationState earWiggleAnimState = new AnimationState();
    public final AnimationState fallToSleepAnimState = new AnimationState();
    public final AnimationState sleepingAnimState = new AnimationState();
    public final AnimationState wakeUpAnimState = new AnimationState();
    public final AnimationState fartAnimState = new AnimationState();

    public Identifier texture = DEFAULT_TEXTURE;
    public float earWiggleSpeed = 1.0f;

    public boolean canAngleHead = true;
}
