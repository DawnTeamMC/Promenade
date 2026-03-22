package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.client.render.entity.model.SunkenEntityModel;
import fr.hugman.promenade.client.render.entity.state.SunkenEntityRenderState;
import fr.hugman.promenade.entity.SunkenEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class SunkenEntityRenderer extends AbstractSkeletonRenderer<SunkenEntity, SunkenEntityRenderState> {
    public SunkenEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                PromenadeEntityModelLayers.SUNKEN_EQUIPMENT,
                new SunkenEntityModel(context.bakeLayer(PromenadeEntityModelLayers.SUNKEN))
        );
    }

    @Override
    public SunkenEntityRenderState createRenderState() {
        return new SunkenEntityRenderState();
    }

    @Override
    protected void setupRotations(SunkenEntityRenderState state, PoseStack poseStack, float bodyYaw, float baseHeight) {
        float h = state.swimAmount;
        float i = state.xRot;

        super.setupRotations(state, poseStack, bodyYaw, baseHeight);
        if (h > 0.0F) {
            float jx = state.isInWater ? -90.0F - i : -90.0F;
            float k = Mth.lerp(h, 0.0F, jx);
            poseStack.mulPose(Axis.XP.rotationDegrees(k));
            if (state.isVisuallySwimming) {
                poseStack.translate(0.0F, -1.0F, 0.3F);
            }
        }
    }

    @Override
    public Identifier getTextureLocation(SunkenEntityRenderState state) {
        if (state.variant == null) {
            return MissingTextureAtlasSprite.getLocation();
        }
        return state.variant.texture().texturePath();
    }

    @Override
    public void extractRenderState(SunkenEntity sunken, SunkenEntityRenderState state, float f) {
        super.extractRenderState(sunken, state, f);
        state.variant = sunken.getVariant().value();
    }
}
