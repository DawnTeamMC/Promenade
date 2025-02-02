package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.LushCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class LushCreeperRenderer extends MobEntityRenderer<LushCreeperEntity, CreeperEntityRenderState, CreeperEntityModel> {
    private static final Identifier TEXTURE = Promenade.id("textures/entity/lush_creeper/base.png");
    private static final Identifier OVERLAY_TEXTURE = Promenade.id("textures/entity/lush_creeper/overlay.png");

    public LushCreeperRenderer(EntityRendererFactory.Context context) {
        super(context, new CreeperEntityModel(context.getPart(PromenadeEntityModelLayers.LUSH_CREEPER)), 0.5F);
        this.addFeature(new CreeperOverlayRenderer<>(this, context.getEntityModels(), PromenadeEntityModelLayers.LUSH_CREEPER_OUTER, OVERLAY_TEXTURE));
    }

    @Override
    public CreeperEntityRenderState createRenderState() {
        return new CreeperEntityRenderState();
    }

    @Override
    protected void scale(CreeperEntityRenderState creeperEntityRenderState, MatrixStack matrixStack) {
        float f = creeperEntityRenderState.fuseTime;
        float g = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float h = (1.0F + f * 0.4F) * g;
        float i = (1.0F + f * 0.1F) / g;
        matrixStack.scale(h, i, h);
    }

    @Override
    protected float getAnimationCounter(CreeperEntityRenderState state) {
        float f = state.fuseTime;
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public Identifier getTexture(CreeperEntityRenderState state) {
        return TEXTURE;
    }

    public void updateRenderState(LushCreeperEntity lushCreeper, CreeperEntityRenderState creeperEntityRenderState, float f) {
        super.updateRenderState(lushCreeper, creeperEntityRenderState, f);
        creeperEntityRenderState.fuseTime = lushCreeper.getClientFuseTime(f);
        creeperEntityRenderState.charged = lushCreeper.isCharged();
    }
}
