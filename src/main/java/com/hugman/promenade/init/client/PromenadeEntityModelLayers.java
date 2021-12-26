package com.hugman.promenade.init.client;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.entity.model.DuckEntityModel;
import com.hugman.promenade.object.entity.model.SunkenSkeletonModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class PromenadeEntityModelLayers {
	public static final EntityModelLayer DUCK = createModelLayer("duck");

	public static final EntityModelLayer LUSH_CREEPER = createModelLayer("lush_creeper");
	public static final EntityModelLayer LUSH_CREEPER_OUTER = createModelLayer("lush_creeper", "outer");

	public static final EntityModelLayer SUNKEN_SKELETON = createModelLayer("sunken_skeleton");
	public static final EntityModelLayer SUNKEN_SKELETON_INNER_ARMOR = createModelLayerInnerArmor("sunken_skeleton");
	public static final EntityModelLayer SUNKEN_SKELETON_OUTER_ARMOR = createModelLayerOuterArmor("sunken_skeleton");

	private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
	private static final Dilation HAT_DILATION = new Dilation(0.5F);
	private static final TexturedModelData INNER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(HAT_DILATION, 0.0F), 64, 32);
	private static final TexturedModelData OUTER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(ARMOR_DILATION, 0.0F), 64, 32);

	public static void init() {
		EntityModelLayerRegistry.registerModelLayer(DUCK, DuckEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(LUSH_CREEPER, () -> CreeperEntityModel.getTexturedModelData(Dilation.NONE));
		EntityModelLayerRegistry.registerModelLayer(LUSH_CREEPER_OUTER, () -> CreeperEntityModel.getTexturedModelData(new Dilation(0.25f)));
		EntityModelLayerRegistry.registerModelLayer(SUNKEN_SKELETON, SunkenSkeletonModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SUNKEN_SKELETON_INNER_ARMOR, () -> INNER_ARMOR_MODEL_DATA);
		EntityModelLayerRegistry.registerModelLayer(SUNKEN_SKELETON_OUTER_ARMOR, () -> OUTER_ARMOR_MODEL_DATA);
	}

	private static EntityModelLayer createModelLayer(String name) {
		return new EntityModelLayer(Promenade.MOD_DATA.id(name), "main");
	}

	private static EntityModelLayer createModelLayer(String name, String layer) {
		return new EntityModelLayer(Promenade.MOD_DATA.id(name), layer);
	}

	private static EntityModelLayer createModelLayerInnerArmor(String name) {
		return createModelLayer(name, "inner_armor");
	}

	private static EntityModelLayer createModelLayerOuterArmor(String name) {
		return createModelLayer(name, "outer_armor");
	}
}
