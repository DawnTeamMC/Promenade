package com.hugman.promenade.object.entity.render;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.client.PromenadeEntityModelLayers;
import com.hugman.promenade.object.entity.DuckEntity;
import com.hugman.promenade.object.entity.SunkenSkeletonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SunkenSkeletonEntityRenderer extends BipedEntityRenderer<SunkenSkeletonEntity, SkeletonEntityModel<SunkenSkeletonEntity>> {
	public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context context) {
		this(context, PromenadeEntityModelLayers.SUNKEN_SKELETON, PromenadeEntityModelLayers.SUNKEN_SKELETON_INNER_ARMOR, PromenadeEntityModelLayers.SUNKEN_SKELETON_OUTER_ARMOR);
	}

	public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, new SkeletonEntityModel<>(ctx.getPart(layer)), 0.5F);
		this.addFeature(new ArmorFeatureRenderer<>(this, new SkeletonEntityModel<>(ctx.getPart(legArmorLayer)), new SkeletonEntityModel<>(ctx.getPart(bodyArmorLayer))));
	}

	@Override
	public Identifier getTexture(SunkenSkeletonEntity entity) {
		return Promenade.MOD_DATA.id("textures/entity/sunken_skeleton/" + entity.getVariant().getName() + ".png");
	}
}
