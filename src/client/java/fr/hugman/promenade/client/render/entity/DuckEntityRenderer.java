package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.duck.AdultDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.BabyDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.DuckModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.state.DuckEntityRenderState;
import fr.hugman.promenade.entity.DuckEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class DuckEntityRenderer extends AgeableMobRenderer<DuckEntity, DuckEntityRenderState, DuckModel> {
    public DuckEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new AdultDuckModel(context.bakeLayer(PromenadeEntityModelLayers.DUCK)),
                new BabyDuckModel(context.bakeLayer(PromenadeEntityModelLayers.DUCK_BABY)),
                0.3F
        );
    }

    @Override
    public DuckEntityRenderState createRenderState() {
        return new DuckEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(DuckEntityRenderState state) {
        if (state.variant == null) {
            return MissingTextureAtlasSprite.getLocation();
        }
        if (state.isBaby) {
            return state.variant.babyTexture().texturePath();
        }
        return state.variant.texture().texturePath();
    }

    @Override
    public void extractRenderState(DuckEntity duck, DuckEntityRenderState state, float f) {
        super.extractRenderState(duck, state, f);
        state.flapProgress = Mth.lerp(f, duck.prevFlapProgress, duck.flapProgress);
        state.maxWingDeviation = Mth.lerp(f, duck.prevMaxWingDeviation, duck.maxWingDeviation);
        state.variant = duck.getVariant().value();
    }
}