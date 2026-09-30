package fr.hugman.promenade.client.render.entity.model;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.render.entity.model.capybara.AdultCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.capybara.BabyCapybaraModel;
import fr.hugman.promenade.client.render.entity.model.duck.AdultDuckModel;
import fr.hugman.promenade.client.render.entity.model.duck.BabyDuckModel;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

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

    // Sunkens wear armor with the stray's armor layers
    public static final ModelLayerLocation SUNKEN = ofMain("sunken");

    public static void register(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registrar) {
        registrar.accept(CAPYBARA, AdultCapybaraModel::getTexturedModelData);
        registrar.accept(CAPYBARA_BABY, BabyCapybaraModel::getTexturedModelData);

        registrar.accept(DUCK, AdultDuckModel::getTexturedModelData);
        registrar.accept(DUCK_BABY, BabyDuckModel::getTexturedModelData);

        registrar.accept(LUSH_CREEPER, () -> CreeperModel.createBodyLayer(CubeDeformation.NONE));
        registrar.accept(LUSH_CREEPER_OUTER, () -> CreeperModel.createBodyLayer(new CubeDeformation(0.25f)));

        registrar.accept(SUNKEN, SunkenEntityModel::getTexturedModelData);

        registrar.accept(SAKURA_BOAT, BoatModel::createBodyModel);
        registrar.accept(SAKURA_CHEST_BOAT, ChestBoatModel::createBodyModel);
        registrar.accept(MAPLE_BOAT, BoatModel::createBodyModel);
        registrar.accept(MAPLE_CHEST_BOAT, ChestBoatModel::createBodyModel);
        registrar.accept(PALM_BOAT, BoatModel::createBodyModel);
        registrar.accept(PALM_CHEST_BOAT, ChestBoatModel::createBodyModel);
    }

    private static ModelLayerLocation of(String name, String layer) {
        return new ModelLayerLocation(Promenade.id(name), layer);
    }

    private static ModelLayerLocation ofMain(String name) {
        return of(name, "main");
    }
}
