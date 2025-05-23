package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.CapybaraEntityModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.state.CapybaraEntityRenderState;
import fr.hugman.promenade.entity.CapybaraEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CapybaraEntityRenderer<E extends CapybaraEntity> extends AgeableMobEntityRenderer<E, CapybaraEntityRenderState, CapybaraEntityModel> {
    public CapybaraEntityRenderer(EntityRendererFactory.Context context) {
        super(context,
                new CapybaraEntityModel(context.getPart(PromenadeEntityModelLayers.CAPYBARA)),
                new CapybaraEntityModel(context.getPart(PromenadeEntityModelLayers.CAPYBARA_BABY)),
                0.5f);
    }

    @Override
    public CapybaraEntityRenderState createRenderState() {
        return new CapybaraEntityRenderState();
    }

    @Override
    public Identifier getTexture(CapybaraEntityRenderState state) {
        return state.texture;
    }

    @Override
    public void updateRenderState(E capybara, CapybaraEntityRenderState state, float f) {
        super.updateRenderState(capybara, state, f);
        state.earWiggleAnimState.copyFrom(capybara.earWiggleAnimState);
        state.fallToSleepAnimState.copyFrom(capybara.fallToSleepAnimState);
        state.sleepingAnimState.copyFrom(capybara.sleepingAnimState);
        state.wakeUpAnimState.copyFrom(capybara.wakeUpAnimState);
        state.fartAnimState.copyFrom(capybara.fartAnimState);

        state.texture = capybara.getTexture();
        state.earWiggleSpeed = capybara.getEarWiggleSpeed();
        state.canAngleHead = capybara.canAngleHead();
    }
}
