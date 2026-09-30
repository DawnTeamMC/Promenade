package fr.hugman.promenade.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;

/**
 * Renders one of the mod's boats. Mirrors vanilla's {@link net.minecraft.client.renderer.entity.BoatRenderer}, which
 * picks its model and texture from the vanilla boat types.
 */
public class PromenadeBoatRenderer extends EntityRenderer<Boat> {
    private final ResourceLocation texture;
    private final ListModel<Boat> model;

    public PromenadeBoatRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, boolean chest) {
        super(context);
        this.shadowRadius = 0.8F;
        // Layers are named "<boat or chest_boat>/<wood>", like the textures
        ResourceLocation id = layer.getModel();
        this.texture = id.withPath(path -> "textures/entity/" + path + ".png");
        ModelPart part = context.bakeLayer(layer);
        this.model = chest ? new ChestBoatModel(part) : new BoatModel(part);
    }

    @Override
    public void render(Boat boat, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource buffers, int light) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
        float hurtTime = (float) boat.getHurtTime() - tickDelta;
        float damage = boat.getDamage() - tickDelta;
        if (damage < 0.0F) {
            damage = 0.0F;
        }

        if (hurtTime > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(hurtTime) * hurtTime * damage / 10.0F * (float) boat.getHurtDir()));
        }

        float bubbleAngle = boat.getBubbleAngle(tickDelta);
        if (!Mth.equal(bubbleAngle, 0.0F)) {
            poseStack.mulPose(new Quaternionf().setAngleAxis(bubbleAngle * (float) (Math.PI / 180.0), 1.0F, 0.0F, 1.0F));
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        this.model.setupAnim(boat, tickDelta, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = buffers.getBuffer(this.model.renderType(this.texture));
        this.model.renderToBuffer(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
        if (!boat.isUnderWater() && this.model instanceof WaterPatchModel waterPatchModel) {
            VertexConsumer waterMask = buffers.getBuffer(RenderType.waterMask());
            waterPatchModel.waterPatch().render(poseStack, waterMask, light, OverlayTexture.NO_OVERLAY);
        }

        poseStack.popPose();
        super.render(boat, yaw, tickDelta, poseStack, buffers, light);
    }

    @Override
    public ResourceLocation getTextureLocation(Boat boat) {
        return this.texture;
    }
}
