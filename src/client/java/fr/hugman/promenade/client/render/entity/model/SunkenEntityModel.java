package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.client.render.entity.state.SunkenEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;

@Environment(EnvType.CLIENT)
public class SunkenEntityModel extends SkeletonEntityModel<SunkenEntityRenderState> {
    public SunkenEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        //FIXME arms and legs are flipped
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
        modelData.getRoot().getChild(EntityModelPartNames.HEAD)
                .addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -16.0F, -4.0F, 16.0F, 16.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void setAngles(SunkenEntityRenderState state) {
        super.setAngles(state);
        this.hat.visible = state.equippedHeadStack.isEmpty();
    }
}