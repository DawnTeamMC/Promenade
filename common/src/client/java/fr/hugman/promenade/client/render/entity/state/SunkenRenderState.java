package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import org.jetbrains.annotations.Nullable;

public class SunkenRenderState extends SkeletonRenderState {
    @Nullable
    public SunkenVariant variant;
}
