package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.Promenade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class PromenadeEntityModelLayers {
    public static final EntityModelLayer SAKURA_BOAT = createModelLayer("boat/sakura");
    public static final EntityModelLayer SAKURA_CHEST_BOAT = createModelLayer("chest_boat/sakura");
    public static final EntityModelLayer MAPLE_BOAT = createModelLayer("boat/maple");
    public static final EntityModelLayer MAPLE_CHEST_BOAT = createModelLayer("chest_boat/maple");
    public static final EntityModelLayer PALM_BOAT = createModelLayer("boat/palm");
    public static final EntityModelLayer PALM_CHEST_BOAT = createModelLayer("chest_boat/palm");

    public static final EntityModelLayer CAPYBARA = createModelLayer("capybara");
    public static final EntityModelLayer CAPYBARA_BABY = createModelLayer("capybara_baby");

    public static final EntityModelLayer DUCK = createModelLayer("duck");
    public static final EntityModelLayer DUCK_BABY = createModelLayer("duck_baby");

    public static final EntityModelLayer LUSH_CREEPER = createModelLayer("lush_creeper");
    public static final EntityModelLayer LUSH_CREEPER_OUTER = createModelLayer("lush_creeper", "outer");

    public static final EntityModelLayer SUNKEN = createModelLayer("sunken");
    public static final EntityModelLayer SUNKEN_INNER_ARMOR = createModelLayerInnerArmor("sunken");
    public static final EntityModelLayer SUNKEN_OUTER_ARMOR = createModelLayerOuterArmor("sunken");

    private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
    private static final Dilation HAT_DILATION = new Dilation(0.5F);
    private static final TexturedModelData INNER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(HAT_DILATION, 0.0F), 64, 32);
    private static final TexturedModelData OUTER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(ARMOR_DILATION, 0.0F), 64, 32);

    public static void register() {
        var capybaraModelData = CapybaraEntityModel.getTexturedModelData();
        EntityModelLayerRegistry.registerModelLayer(CAPYBARA, () -> capybaraModelData);
        EntityModelLayerRegistry.registerModelLayer(CAPYBARA_BABY, () -> capybaraModelData.transform(CapybaraEntityModel.BABY_TRANSFORMER));

        var duckModelData = DuckEntityModel.getTexturedModelData();

        EntityModelLayerRegistry.registerModelLayer(DUCK, () -> duckModelData);
        EntityModelLayerRegistry.registerModelLayer(DUCK_BABY, () -> duckModelData.transform(DuckEntityModel.BABY_TRANSFORMER));

        EntityModelLayerRegistry.registerModelLayer(LUSH_CREEPER, () -> CreeperEntityModel.getTexturedModelData(Dilation.NONE));
        EntityModelLayerRegistry.registerModelLayer(LUSH_CREEPER_OUTER, () -> CreeperEntityModel.getTexturedModelData(new Dilation(0.25f)));

        EntityModelLayerRegistry.registerModelLayer(SUNKEN, SunkenEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SUNKEN_INNER_ARMOR, () -> INNER_ARMOR_MODEL_DATA);
        EntityModelLayerRegistry.registerModelLayer(SUNKEN_OUTER_ARMOR, () -> OUTER_ARMOR_MODEL_DATA);

        TexturedModelData boatModel = BoatEntityModel.getTexturedModelData();
        TexturedModelData chestBoatModel = BoatEntityModel.getChestTexturedModelData();
        EntityModelLayerRegistry.registerModelLayer(SAKURA_BOAT, () -> boatModel);
        EntityModelLayerRegistry.registerModelLayer(SAKURA_CHEST_BOAT, () -> chestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(MAPLE_BOAT, () -> boatModel);
        EntityModelLayerRegistry.registerModelLayer(MAPLE_CHEST_BOAT, () -> chestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(PALM_BOAT, () -> boatModel);
        EntityModelLayerRegistry.registerModelLayer(PALM_CHEST_BOAT, () -> chestBoatModel);
    }

    private static EntityModelLayer createModelLayer(String name) {
        return new EntityModelLayer(Promenade.id(name), "main");
    }

    private static EntityModelLayer createModelLayer(String name, String layer) {
        return new EntityModelLayer(Promenade.id(name), layer);
    }

    private static EntityModelLayer createModelLayerInnerArmor(String name) {
        return createModelLayer(name, "inner_armor");
    }

    private static EntityModelLayer createModelLayerOuterArmor(String name) {
        return createModelLayer(name, "outer_armor");
    }
}
