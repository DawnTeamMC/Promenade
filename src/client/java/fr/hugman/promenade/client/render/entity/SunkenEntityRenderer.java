package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.SunkenEntityModel;
import fr.hugman.promenade.client.render.entity.state.SunkenEntityRenderState;
import fr.hugman.promenade.entity.SunkenEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class SunkenEntityRenderer extends AbstractSkeletonEntityRenderer<SunkenEntity, SunkenEntityRenderState> {
    public SunkenEntityRenderer(EntityRendererFactory.Context context) {
        super(context, PromenadeEntityModelLayers.SUNKEN_INNER_ARMOR, PromenadeEntityModelLayers.SUNKEN_OUTER_ARMOR, new SunkenEntityModel(context.getPart(PromenadeEntityModelLayers.SUNKEN)));
    }

    @Override
    public SunkenEntityRenderState createRenderState() {
        return new SunkenEntityRenderState();
    }

    @Override
    protected void setupTransforms(SunkenEntityRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
        float h = state.leaningPitch;
        float i = state.pitch;

        if (h > 0.0F) {
            super.setupTransforms(state, matrices, bodyYaw, baseHeight);
            float jx = state.touchingWater ? -90.0F - i : -90.0F;
            float k = MathHelper.lerp(h, 0.0F, jx);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(k));
            if (state.isSwimming) {
                matrices.translate(0.0F, -1.0F, 0.3F);
            }
        } else {
            super.setupTransforms(state, matrices, bodyYaw, baseHeight);
        }
    }

    @Override
    public Identifier getTexture(SunkenEntityRenderState state) {
        return state.texture;
    }
}
