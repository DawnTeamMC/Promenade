package com.hugman.wild_explorer.object.entity.render;

import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.init.client.WEEntityModels;
import com.hugman.wild_explorer.object.entity.DuckEntity;
import com.hugman.wild_explorer.object.entity.model.DuckEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class DuckEntityRenderer extends MobEntityRenderer<DuckEntity, DuckEntityModel<DuckEntity>> {
	public DuckEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new DuckEntityModel<>(context.getPart(WEEntityModels.DUCK)), 0.3F);
	}

	@Override
	public Identifier getTexture(DuckEntity entity) {
		if(entity.isBaby()) return WildExplorer.MOD_DATA.id("textures/entity/duck/duckling.png");
		return WildExplorer.MOD_DATA.id("textures/entity/duck/" + entity.getVariant().getName() + ".png");
	}

	protected float getAnimationProgress(DuckEntity entity, float p_77044_2_) {
		float f = MathHelper.lerp(p_77044_2_, entity.oFlap, entity.wingRotation);
		float f1 = MathHelper.lerp(p_77044_2_, entity.oFlapSpeed, entity.destPos);
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}