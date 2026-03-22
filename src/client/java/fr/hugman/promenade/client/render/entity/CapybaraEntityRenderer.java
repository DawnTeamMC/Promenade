package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.CapybaraEntityModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.state.CapybaraEntityRenderState;
import fr.hugman.promenade.entity.CapybaraEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraEntityRenderer<E extends CapybaraEntity> extends AgeableMobRenderer<E, CapybaraEntityRenderState, CapybaraEntityModel> {
    public CapybaraEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new CapybaraEntityModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA)),
                new CapybaraEntityModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA_BABY)),
                0.5f
        );
    }

    @Override
    public CapybaraEntityRenderState createRenderState() {
        return new CapybaraEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(CapybaraEntityRenderState state) {
        if (state.variant == null) {
            return MissingTextureAtlasSprite.getLocation();
        }
        if (state.closedEyes) {
            return state.variant.closedEyesTexture().texturePath();
        }
        return state.largeEyes ? state.variant.largeEyesTexture().texturePath() : state.variant.smallEyesTexture().texturePath();
    }

    @Override
    public void extractRenderState(E capybara, CapybaraEntityRenderState state, float f) {
        super.extractRenderState(capybara, state, f);
        state.earWiggleAnimState.copyFrom(capybara.earWiggleAnimState);
        state.fallToSleepAnimState.copyFrom(capybara.fallToSleepAnimState);
        state.sleepingAnimState.copyFrom(capybara.sleepingAnimState);
        state.wakeUpAnimState.copyFrom(capybara.wakeUpAnimState);
        state.fartAnimState.copyFrom(capybara.fartAnimState);

        state.variant = capybara.getVariant().value();
        state.closedEyes = capybara.hasClosedEyes();
        state.largeEyes = capybara.hasLargeEyes();
        state.earWiggleSpeed = capybara.getEarWiggleSpeed();
        state.canAngleHead = capybara.canAngleHead();
    }
}
