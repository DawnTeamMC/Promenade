package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.capybara.AdultCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.capybara.BabyCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.duck.AdultDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.BabyDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.DuckModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;

public class PromenadeEntityModelLayers {
    public static final ModelLayerLocation SAKURA_BOAT = ofMain("boat/sakura");
    public static final ModelLayerLocation SAKURA_CHEST_BOAT = ofMain("chest_boat/sakura");
    public static final ModelLayerLocation MAPLE_BOAT = ofMain("boat/maple");
    public static final ModelLayerLocation MAPLE_CHEST_BOAT = ofMain("chest_boat/maple");
    public static final ModelLayerLocation PALM_BOAT = ofMain("boat/palm");
    public static final ModelLayerLocation PALM_CHEST_BOAT = ofMain("chest_boat/palm");

    public static final ModelLayerLocation CAPYBARA = ofMain("capybara");
    public static final ModelLayerLocation CAPYBARA_BABY = ofMain("capybara_baby");

    public static final ModelLayerLocation DUCK = ofMain("duck");
    public static final ModelLayerLocation DUCK_BABY = ofMain("duck_baby");

    public static final ModelLayerLocation LUSH_CREEPER = ofMain("lush_creeper");
    public static final ModelLayerLocation LUSH_CREEPER_OUTER = of("lush_creeper", "outer");

    public static final ModelLayerLocation SUNKEN = ofMain("sunken");
    public static final ArmorModelSet<ModelLayerLocation> SUNKEN_EQUIPMENT = equipment("stray");


    private static final CubeDeformation ARMOR_DILATION = new CubeDeformation(1.0F);
    private static final CubeDeformation HAT_DILATION = new CubeDeformation(0.5F);
    private static final LayerDefinition INNER_ARMOR_MODEL_DATA = LayerDefinition.create(HumanoidModel.createMesh(HAT_DILATION, 0.0F), 64, 32);
    private static final LayerDefinition OUTER_ARMOR_MODEL_DATA = LayerDefinition.create(HumanoidModel.createMesh(ARMOR_DILATION, 0.0F), 64, 32);

    public static void register() {
        ArmorModelSet<LayerDefinition> equipmentModelData = HumanoidModel.createArmorMeshSet(HAT_DILATION, ARMOR_DILATION).map((data) -> LayerDefinition.create(data, 64, 32));

        ModelLayerRegistry.registerModelLayer(CAPYBARA, AdultCapybaraModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(CAPYBARA_BABY, BabyCapybaraModel::getTexturedModelData);

        ModelLayerRegistry.registerModelLayer(DUCK, AdultDuckModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(DUCK_BABY, BabyDuckModel::getTexturedModelData);

        ModelLayerRegistry.registerModelLayer(LUSH_CREEPER, () -> CreeperModel.createBodyLayer(CubeDeformation.NONE));
        ModelLayerRegistry.registerModelLayer(LUSH_CREEPER_OUTER, () -> CreeperModel.createBodyLayer(new CubeDeformation(0.25f)));

        ModelLayerRegistry.registerModelLayer(SUNKEN, SunkenEntityModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.head(), equipmentModelData::head);
        ModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.chest(), equipmentModelData::chest);
        ModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.legs(), equipmentModelData::legs);
        ModelLayerRegistry.registerModelLayer(SUNKEN_EQUIPMENT.feet(), equipmentModelData::feet);

        LayerDefinition boatModel = BoatModel.createBoatModel();
        LayerDefinition chestBoatModel = BoatModel.createChestBoatModel();
        ModelLayerRegistry.registerModelLayer(SAKURA_BOAT, () -> boatModel);
        ModelLayerRegistry.registerModelLayer(SAKURA_CHEST_BOAT, () -> chestBoatModel);
        ModelLayerRegistry.registerModelLayer(MAPLE_BOAT, () -> boatModel);
        ModelLayerRegistry.registerModelLayer(MAPLE_CHEST_BOAT, () -> chestBoatModel);
        ModelLayerRegistry.registerModelLayer(PALM_BOAT, () -> boatModel);
        ModelLayerRegistry.registerModelLayer(PALM_CHEST_BOAT, () -> chestBoatModel);
    }

    private static ModelLayerLocation of(String name, String layer) {
        return new ModelLayerLocation(Promenade.id(name), layer);
    }

    private static ModelLayerLocation ofMain(String name) {
        return of(name, "main");
    }

    private static ArmorModelSet<ModelLayerLocation> equipment(String id) {
        return new ArmorModelSet(of(id, "helmet"), of(id, "chestplate"), of(id, "leggings"), of(id, "boots"));
    }
}
