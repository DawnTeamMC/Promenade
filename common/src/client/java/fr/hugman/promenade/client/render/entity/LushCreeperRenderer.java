package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.LushCreeper;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LushCreeperRenderer extends MobRenderer<LushCreeper, CreeperModel<LushCreeper>> {
    private static final ResourceLocation TEXTURE = Promenade.id("textures/entity/lush_creeper/base.png");
    private static final ResourceLocation OVERLAY_TEXTURE = Promenade.id("textures/entity/lush_creeper/overlay.png");

    public LushCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel<>(context.bakeLayer(PromenadeEntityModelLayers.LUSH_CREEPER)), 0.5F);
        this.addLayer(new CreeperOverlayRenderer<>(this, context.getModelSet(), PromenadeEntityModelLayers.LUSH_CREEPER_OUTER, OVERLAY_TEXTURE));
    }

    @Override
    protected void scale(LushCreeper creeper, PoseStack matrixStack, float tickDelta) {
        float f = creeper.getSwelling(tickDelta);
        float g = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float h = (1.0F + f * 0.4F) * g;
        float i = (1.0F + f * 0.1F) / g;
        matrixStack.scale(h, i, h);
    }

    @Override
    protected float getWhiteOverlayProgress(LushCreeper creeper, float tickDelta) {
        float f = creeper.getSwelling(tickDelta);
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(LushCreeper creeper) {
        return TEXTURE;
    }
}
