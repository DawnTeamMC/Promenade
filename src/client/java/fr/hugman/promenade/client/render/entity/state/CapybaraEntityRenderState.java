package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class CapybaraEntityRenderState extends LivingEntityRenderState {
    public final AnimationState earWiggleAnimState = new AnimationState();
    public final AnimationState fallToSleepAnimState = new AnimationState();
    public final AnimationState sleepingAnimState = new AnimationState();
    public final AnimationState wakeUpAnimState = new AnimationState();
    public final AnimationState fartAnimState = new AnimationState();

    @Nullable
    public CapybaraVariant variant;
    public boolean closedEyes = false;
    public boolean largeEyes = false;
    public float earWiggleSpeed = 1.0f;

    public boolean canAngleHead = true;
}
