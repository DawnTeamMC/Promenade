package fr.hugman.promenade.client.render.entity.state;

import fr.hugman.promenade.entity.variant.SunkenVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SunkenRenderState extends SkeletonRenderState {
    @Nullable
    public SunkenVariant variant;
}
