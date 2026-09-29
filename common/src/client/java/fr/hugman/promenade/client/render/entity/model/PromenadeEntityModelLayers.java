package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.capybara.AdultCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.capybara.BabyCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.duck.AdultDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.BabyDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.DuckModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

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

    public static void register(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registrar) {
        ArmorModelSet<LayerDefinition> equipmentModelData = HumanoidModel.createArmorMeshSet(HAT_DILATION, ARMOR_DILATION).map((data) -> LayerDefinition.create(data, 64, 32));

        registrar.accept(CAPYBARA, AdultCapybaraModel::getTexturedModelData);
        registrar.accept(CAPYBARA_BABY, BabyCapybaraModel::getTexturedModelData);

        registrar.accept(DUCK, AdultDuckModel::getTexturedModelData);
        registrar.accept(DUCK_BABY, BabyDuckModel::getTexturedModelData);

        registrar.accept(LUSH_CREEPER, () -> CreeperModel.createBodyLayer(CubeDeformation.NONE));
        registrar.accept(LUSH_CREEPER_OUTER, () -> CreeperModel.createBodyLayer(new CubeDeformation(0.25f)));

        registrar.accept(SUNKEN, SunkenEntityModel::getTexturedModelData);
        registrar.accept(SUNKEN_EQUIPMENT.head(), equipmentModelData::head);
        registrar.accept(SUNKEN_EQUIPMENT.chest(), equipmentModelData::chest);
        registrar.accept(SUNKEN_EQUIPMENT.legs(), equipmentModelData::legs);
        registrar.accept(SUNKEN_EQUIPMENT.feet(), equipmentModelData::feet);

        LayerDefinition boatModel = BoatModel.createBoatModel();
        LayerDefinition chestBoatModel = BoatModel.createChestBoatModel();
        registrar.accept(SAKURA_BOAT, () -> boatModel);
        registrar.accept(SAKURA_CHEST_BOAT, () -> chestBoatModel);
        registrar.accept(MAPLE_BOAT, () -> boatModel);
        registrar.accept(MAPLE_CHEST_BOAT, () -> chestBoatModel);
        registrar.accept(PALM_BOAT, () -> boatModel);
        registrar.accept(PALM_CHEST_BOAT, () -> chestBoatModel);
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
