package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.capybara.BabyCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.capybara.AdultCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.capybara.CapybaraModel;
import fr.hugman.promenade.client.render.entity.state.CapybaraEntityRenderState;
import fr.hugman.promenade.entity.CapybaraEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraEntityRenderer<E extends CapybaraEntity> extends AgeableMobRenderer<E, CapybaraEntityRenderState, CapybaraModel> {
    public CapybaraEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new AdultCapybaraModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA)),
                new BabyCapybaraModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA_BABY)),
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
        var textureInfo = state.isBaby ? state.variant.babyInfo() : state.variant.adultInfo();
        if (state.closedEyes) {
            return textureInfo.sleeping().texturePath();
        }
        return state.largeEyes ? textureInfo.farting().texturePath() : textureInfo.normal().texturePath();
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
