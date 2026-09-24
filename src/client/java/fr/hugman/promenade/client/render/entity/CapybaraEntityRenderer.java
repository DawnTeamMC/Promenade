package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.capybara.BabyCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.capybara.AdultCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.capybara.CapybaraModel;
import fr.hugman.promenade.client.render.entity.state.CapybaraRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.hugman.promenade.entity.Capybara;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;

public class CapybaraEntityRenderer<E extends Capybara> extends AgeableMobRenderer<E, CapybaraRenderState, CapybaraModel> {
    public CapybaraEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new AdultCapybaraModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA)),
                new BabyCapybaraModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA_BABY)),
                0.5f
        );
    }

    @Override
    public CapybaraRenderState createRenderState() {
        return new CapybaraRenderState();
    }

    @Override
    public Identifier getTextureLocation(CapybaraRenderState state) {
        if (state.variant == null) {
            return MissingTextureAtlasSprite.getLocation();
        }
        var textureInfo = state.isBaby ? state.variant.babyInfo() : state.variant.adultInfo();
        if (state.sleeping) {
            return textureInfo.sleeping().texturePath();
        }
        return state.surprised ? textureInfo.surprised().texturePath() : textureInfo.normal().texturePath();
    }

    @Override
    public void extractRenderState(E capybara, CapybaraRenderState state, float f) {
        super.extractRenderState(capybara, state, f);
        state.earWiggleAnimationState.copyFrom(capybara.earWiggleAnimState);
        state.fallToSleepAnimationState.copyFrom(capybara.fallToSleepAnimState);
        state.sleepingAnimationState.copyFrom(capybara.sleepingAnimState);
        state.wakeUpAnimationState.copyFrom(capybara.wakeUpAnimState);
        state.fartAnimationState.copyFrom(capybara.fartAnimState);

        state.variant = capybara.getVariant().value();
        state.sleeping = capybara.isVisuallySleeping();
        state.surprised = capybara.isSurprised();
        state.earWiggleSpeed = capybara.getEarWiggleSpeed();
        state.canAngleHead = capybara.canAngleHead();
        state.floatingInWater = capybara.isFloatingInWater();
        state.divePitch = capybara.getDivePitch(f);
    }

    @Override
    protected void setupRotations(CapybaraRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
        super.setupRotations(state, poseStack, bodyRot, entityScale);
        if (state.divePitch != 0.0f) {
            poseStack.rotateAround(Axis.XP.rotationDegrees(state.divePitch), 0.0f, state.boundingBoxHeight / 2.0f / entityScale, 0.0f);
        }
    }
}
