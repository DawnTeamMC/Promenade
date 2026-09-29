package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.entity.variant.DuckVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.jetbrains.annotations.Nullable;

public class DuckRenderState extends LivingEntityRenderState {
    @Nullable
    public DuckVariant variant;

    public float flapProgress;
    public float maxWingDeviation;
}
