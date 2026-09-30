package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.capybara.AdultCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.capybara.BabyCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.capybara.CapybaraModel;
import fr.hugman.promenade.entity.Capybara;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CapybaraEntityRenderer extends MobRenderer<Capybara, CapybaraModel> {
    private final CapybaraModel adultModel;
    private final CapybaraModel babyModel;

    public CapybaraEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultCapybaraModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA)), 0.5f);
        this.adultModel = this.model;
        this.babyModel = new BabyCapybaraModel(context.bakeLayer(PromenadeEntityModelLayers.CAPYBARA_BABY));
    }

    @Override
    public void render(Capybara capybara, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource buffers, int light) {
        // Babies have their own model
        this.model = capybara.isBaby() ? this.babyModel : this.adultModel;
        super.render(capybara, yaw, tickDelta, poseStack, buffers, light);
    }

    @Override
    public ResourceLocation getTextureLocation(Capybara capybara) {
        var variant = capybara.getVariant().value();
        var textureInfo = capybara.isBaby() ? variant.babyInfo() : variant.adultInfo();
        if (capybara.isVisuallySleeping()) {
            return textureInfo.sleeping().texturePath();
        }
        return capybara.isSurprised() ? textureInfo.surprised().texturePath() : textureInfo.normal().texturePath();
    }
}
