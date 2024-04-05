package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LushCreeperOverlayRenderer<T extends CreeperEntity> extends FeatureRenderer<T, CreeperEntityModel<T>> {
    private static final Identifier TEXTURE = Promenade.id("textures/entity/lush_creeper/overlay.png");
    private final CreeperEntityModel<T> model;

    public LushCreeperOverlayRenderer(FeatureRendererContext<T, CreeperEntityModel<T>> context, EntityModelLoader loader) {
        super(context);
        this.model = new CreeperEntityModel<>(loader.getModelPart(PromenadeEntityModelLayers.LUSH_CREEPER_OUTER));
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T creeperEntity, float f, float g, float h, float j, float k, float l) {
        LushCreeperOverlayRenderer.render(this.getContextModel(), this.model, TEXTURE, matrixStack, vertexConsumerProvider, i, creeperEntity, f, g, j, k, l, h, 1.0f, 1.0f, 1.0f);
    }
}

