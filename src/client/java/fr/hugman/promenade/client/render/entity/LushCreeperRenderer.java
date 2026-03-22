package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.LushCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class LushCreeperRenderer extends MobRenderer<LushCreeperEntity, CreeperRenderState, CreeperModel> {
    private static final Identifier TEXTURE = Promenade.id("textures/entity/lush_creeper/base.png");
    private static final Identifier OVERLAY_TEXTURE = Promenade.id("textures/entity/lush_creeper/overlay.png");

    public LushCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(PromenadeEntityModelLayers.LUSH_CREEPER)), 0.5F);
        this.addLayer(new CreeperOverlayRenderer<>(this, context.getModelSet(), PromenadeEntityModelLayers.LUSH_CREEPER_OUTER, OVERLAY_TEXTURE));
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    protected void scale(CreeperRenderState creeperEntityRenderState, PoseStack matrixStack) {
        float f = creeperEntityRenderState.swelling;
        float g = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float h = (1.0F + f * 0.4F) * g;
        float i = (1.0F + f * 0.1F) / g;
        matrixStack.scale(h, i, h);
    }

    @Override
    protected float getWhiteOverlayProgress(CreeperRenderState state) {
        float f = state.swelling;
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public Identifier getTextureLocation(CreeperRenderState state) {
        return TEXTURE;
    }

    public void updateRenderState(LushCreeperEntity lushCreeper, CreeperRenderState creeperEntityRenderState, float f) {
        super.extractRenderState(lushCreeper, creeperEntityRenderState, f);
        creeperEntityRenderState.swelling = lushCreeper.getSwelling(f);
        creeperEntityRenderState.isPowered = lushCreeper.isPowered();
    }
}
