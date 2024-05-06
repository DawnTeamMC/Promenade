package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.DuckModel;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.DuckEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class DuckRenderer extends MobEntityRenderer<DuckEntity, DuckModel<DuckEntity>> {
    public DuckRenderer(EntityRendererFactory.Context context) {
        super(context, new DuckModel<>(context.getPart(PromenadeEntityModelLayers.DUCK)), 0.3F);
    }

    @Override
    public Identifier getTexture(DuckEntity entity) {
        return entity.getTexture();
    }

    protected float getAnimationProgress(DuckEntity entity, float tickDelta) {
        float f = MathHelper.lerp(tickDelta, entity.oFlap, entity.wingRotation);
        float f1 = MathHelper.lerp(tickDelta, entity.oFlapSpeed, entity.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}