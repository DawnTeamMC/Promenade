package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.duck.AdultDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.BabyDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.DuckModel;
import fr.hugman.promenade.entity.Duck;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DuckEntityRenderer extends MobRenderer<Duck, DuckModel> {
    private final DuckModel adultModel;
    private final DuckModel babyModel;

    public DuckEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultDuckModel(context.bakeLayer(PromenadeEntityModelLayers.DUCK)), 0.3F);
        this.adultModel = this.model;
        this.babyModel = new BabyDuckModel(context.bakeLayer(PromenadeEntityModelLayers.DUCK_BABY));
    }

    @Override
    public void render(Duck duck, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource buffers, int light) {
        // Babies have their own model
        this.model = duck.isBaby() ? this.babyModel : this.adultModel;
        super.render(duck, yaw, tickDelta, poseStack, buffers, light);
    }

    @Override
    public ResourceLocation getTextureLocation(Duck duck) {
        var variant = duck.getVariant().value();
        return duck.isBaby() ? variant.babyTexture().texturePath() : variant.texture().texturePath();
    }

    /**
     * The model uses this value to open the wings, like vanilla chickens.
     */
    @Override
    protected float getBob(Duck duck, float tickDelta) {
        float flapProgress = Mth.lerp(tickDelta, duck.prevFlapProgress, duck.flapProgress);
        float maxWingDeviation = Mth.lerp(tickDelta, duck.prevMaxWingDeviation, duck.maxWingDeviation);
        return (Mth.sin(flapProgress) + 1.0F) * maxWingDeviation;
    }
}
