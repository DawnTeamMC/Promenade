package com.hugman.promenade.object.entity.render;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.client.PromenadeEntityModelLayers;
import com.hugman.promenade.object.entity.LushCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class LushCreeperEntityRenderer extends MobEntityRenderer<LushCreeperEntity, CreeperEntityModel<LushCreeperEntity>> {
	private static final Identifier TEXTURE = Promenade.MOD_DATA.id("textures/entity/lush_creeper/base.png");

	public LushCreeperEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new CreeperEntityModel<>(context.getPart(PromenadeEntityModelLayers.LUSH_CREEPER)), 0.5F);
		this.addFeature(new LushCreeperOverlayFeatureRenderer<>(this, context.getModelLoader()));
	}

	@Override
	protected void scale(LushCreeperEntity creeperEntity, MatrixStack matrixStack, float f) {
		float g = creeperEntity.getClientFuseTime(f);
		float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
		g = MathHelper.clamp(g, 0.0F, 1.0F);
		g *= g;
		g *= g;
		float i = (1.0F + g * 0.4F) * h;
		float j = (1.0F + g * 0.1F) / h;
		matrixStack.scale(i, j, i);
	}

	@Override
	protected float getAnimationCounter(LushCreeperEntity creeperEntity, float f) {
		float g = creeperEntity.getClientFuseTime(f);
		return (int)(g * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(g, 0.5F, 1.0F);
	}

	@Override
	public Identifier getTexture(LushCreeperEntity entity) {
		return TEXTURE;
	}
}
