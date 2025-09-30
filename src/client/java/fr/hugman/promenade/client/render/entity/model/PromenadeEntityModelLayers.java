package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.Promenade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.*;

@Environment(EnvType.CLIENT)
public class PromenadeEntityModelLayers {
    public static final EntityModelLayer SAKURA_BOAT = ofMain("boat/sakura");
    public static final EntityModelLayer SAKURA_CHEST_BOAT = ofMain("chest_boat/sakura");
    public static final EntityModelLayer MAPLE_BOAT = ofMain("boat/maple");
    public static final EntityModelLayer MAPLE_CHEST_BOAT = ofMain("chest_boat/maple");
    public static final EntityModelLayer PALM_BOAT = ofMain("boat/palm");
    public static final EntityModelLayer PALM_CHEST_BOAT = ofMain("chest_boat/palm");

    public static final EntityModelLayer CAPYBARA = ofMain("capybara");
    public static final EntityModelLayer CAPYBARA_BABY = ofMain("capybara_baby");

    public static final EntityModelLayer DUCK = ofMain("duck");
    public static final EntityModelLayer DUCK_BABY = ofMain("duck_baby");

    public static final EntityModelLayer LUSH_CREEPER = ofMain("lush_creeper");
    public static final EntityModelLayer LUSH_CREEPER_OUTER = of("lush_creeper", "outer");

    public static final EntityModelLayer SUNKEN = ofMain("sunken");
    public static final EquipmentModelData<EntityModelLayer> SUNKEN_EQUIPMENT = equipment("stray");


    private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
    private static final Dilation HAT_DILATION = new Dilation(0.5F);
    private static final TexturedModelData INNER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(HAT_DILATION, 0.0F), 64, 32);
    private static final TexturedModelData OUTER_ARMOR_MODEL_DATA = TexturedModelData.of(BipedEntityModel.getModelData(ARMOR_DILATION, 0.0F), 64, 32);

    public static void register() {
        EquipmentModelData<TexturedModelData> equipmentModelData = BipedEntityModel.createEquipmentModelData(HAT_DILATION, ARMOR_DILATION).map((data) -> TexturedModelData.of(data, 64, 32));

        var capybaraModelData = CapybaraEntityModel.getTexturedModelData();
        EntityModelLayerRegistry.registerModelLayer(CAPYBARA, () -> capybaraModelData);
        EntityModelLayerRegistry.registerModelLayer(CAPYBARA_BABY, () -> capybaraModelData.transform(CapybaraEntityModel.BABY_TRANSFORMER));

        var duckModelData = DuckEntityModel.getTexturedModelData();

        EntityModelLayerRegistry.registerModelLayer(DUCK, () -> duckModelData);
        EntityModelLayerRegistry.registerModelLayer(DUCK_BABY, () -> duckModelData.transform(DuckEntityModel.BABY_TRANSFORMER));

        EntityModelLayerRegistry.registerModelLayer(LUSH_CREEPER, () -> CreeperEntityModel.getTexturedModelData(Dilation.NONE));
        EntityModelLayerRegistry.registerModelLayer(LUSH_CREEPER_OUTER, () -> CreeperEntityModel.getTexturedModelData(new Dilation(0.25f)));

        EntityModelLayerRegistry.registerModelLayer(SUNKEN, SunkenEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.head(), equipmentModelData::head);
        EntityModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.chest(), equipmentModelData::chest);
        EntityModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.legs(), equipmentModelData::legs);
        EntityModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.feet(), equipmentModelData::feet);

        TexturedModelData boatModel = BoatEntityModel.getTexturedModelData();
        TexturedModelData chestBoatModel = BoatEntityModel.getChestTexturedModelData();
        EntityModelLayerRegistry.registerModelLayer(SAKURA_BOAT, () -> boatModel);
        EntityModelLayerRegistry.registerModelLayer(SAKURA_CHEST_BOAT, () -> chestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(MAPLE_BOAT, () -> boatModel);
        EntityModelLayerRegistry.registerModelLayer(MAPLE_CHEST_BOAT, () -> chestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(PALM_BOAT, () -> boatModel);
        EntityModelLayerRegistry.registerModelLayer(PALM_CHEST_BOAT, () -> chestBoatModel);
    }

    private static EntityModelLayer of(String name, String layer) {
        return new EntityModelLayer(Promenade.id(name), layer);
    }

    private static EntityModelLayer ofMain(String name) {
        return of(name, "main");
    }

    private static EquipmentModelData<EntityModelLayer> equipment(String id) {
        return new EquipmentModelData(of(id, "helmet"), of(id, "chestplate"), of(id, "leggings"), of(id, "boots"));
    }
}
