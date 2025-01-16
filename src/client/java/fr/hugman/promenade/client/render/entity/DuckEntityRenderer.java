package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.DuckEntityModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.state.DuckEntityRenderState;
import fr.hugman.promenade.entity.DuckEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class DuckEntityRenderer extends AgeableMobEntityRenderer<DuckEntity, DuckEntityRenderState, DuckEntityModel> {
    public DuckEntityRenderer(EntityRendererFactory.Context context) {
        super(
                context,
                new DuckEntityModel(context.getPart(PromenadeEntityModelLayers.DUCK)),
                new DuckEntityModel(context.getPart(PromenadeEntityModelLayers.DUCK_BABY)),
                0.3F
        );
    }

    @Override
    public DuckEntityRenderState createRenderState() {
        return new DuckEntityRenderState();
    }

    @Override
    public Identifier getTexture(DuckEntityRenderState state) {
        return state.texture;
    }

    public void updateRenderState(DuckEntity duck, DuckEntityRenderState state, float f) {
        super.updateRenderState(duck, state, f);
        state.flapProgress = MathHelper.lerp(f, duck.prevFlapProgress, duck.flapProgress);
        state.maxWingDeviation = MathHelper.lerp(f, duck.prevMaxWingDeviation, duck.maxWingDeviation);
        state.texture = duck.getTexture();
    }
}