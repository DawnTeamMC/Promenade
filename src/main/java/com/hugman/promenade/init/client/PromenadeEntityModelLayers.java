package com.hugman.promenade.init.client;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.entity.model.DuckEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;

@Environment(EnvType.CLIENT)
public class PromenadeEntityModelLayers {
	public static final EntityModelLayer DUCK = createModelLayer("duck");

	public static final EntityModelLayer SUNKEN_SKELETON = createModelLayer("sunken_skeleton");
	public static final EntityModelLayer SUNKEN_SKELETON_INNER_ARMOR = createModelLayerInnerArmor(SUNKEN_SKELETON);
	public static final EntityModelLayer SUNKEN_SKELETON_OUTER_ARMOR = createModelLayerOuterArmor(SUNKEN_SKELETON);

	private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
	private static final Dilation HAT_DILATION = new Dilation(0.5F);
	private static final TexturedModelData INNER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(HAT_DILATION, 0.0F), 64, 32);
	private static final TexturedModelData OUTER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(ARMOR_DILATION, 0.0F), 64, 32);

	public static void init() {
		EntityModelLayerRegistry.registerModelLayer(DUCK, DuckEntityModel::createModelData);
		EntityModelLayerRegistry.registerModelLayer(SUNKEN_SKELETON, SkeletonEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SUNKEN_SKELETON_INNER_ARMOR, () -> INNER_ARMOR_MODEL_DATA);
		EntityModelLayerRegistry.registerModelLayer(SUNKEN_SKELETON_OUTER_ARMOR, () -> OUTER_ARMOR_MODEL_DATA);
	}

	private static EntityModelLayer createModelLayer(String name) {
		return new EntityModelLayer(Promenade.MOD_DATA.id(name), "main");
	}

	private static EntityModelLayer createModelLayer(String name, String layer) {
		return new EntityModelLayer(Promenade.MOD_DATA.id(name), layer);
	}

	private static EntityModelLayer createModelLayer(EntityModelLayer entityModelLayer, String layer) {
		return createModelLayer(entityModelLayer.getName() + layer, layer);
	}

	private static EntityModelLayer createModelLayerInnerArmor(EntityModelLayer entityModelLayer) {
		return createModelLayer(entityModelLayer, "inner_armor");
	}

	private static EntityModelLayer createModelLayerOuterArmor(EntityModelLayer entityModelLayer) {
		return createModelLayer(entityModelLayer, "outer_armor");
	}
}
