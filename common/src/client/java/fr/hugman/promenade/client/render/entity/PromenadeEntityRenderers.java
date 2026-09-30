package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;

public class PromenadeEntityRenderers {
    public static void register(Registrar registrar) {
        registrar.register(PromenadeEntityTypes.CAPYBARA, CapybaraEntityRenderer::new);
        registrar.register(PromenadeEntityTypes.DUCK, DuckEntityRenderer::new);
        registrar.register(PromenadeEntityTypes.LUSH_CREEPER, LushCreeperRenderer::new);
        registrar.register(PromenadeEntityTypes.SUNKEN, SunkenEntityRenderer::new);

        registerBoat(registrar, PromenadeEntityTypes.SAKURA_BOAT, PromenadeEntityModelLayers.SAKURA_BOAT, false);
        registerBoat(registrar, PromenadeEntityTypes.SAKURA_CHEST_BOAT, PromenadeEntityModelLayers.SAKURA_CHEST_BOAT, true);
        registerBoat(registrar, PromenadeEntityTypes.MAPLE_BOAT, PromenadeEntityModelLayers.MAPLE_BOAT, false);
        registerBoat(registrar, PromenadeEntityTypes.MAPLE_CHEST_BOAT, PromenadeEntityModelLayers.MAPLE_CHEST_BOAT, true);
        registerBoat(registrar, PromenadeEntityTypes.PALM_BOAT, PromenadeEntityModelLayers.PALM_BOAT, false);
        registerBoat(registrar, PromenadeEntityTypes.PALM_CHEST_BOAT, PromenadeEntityModelLayers.PALM_CHEST_BOAT, true);
    }

    private static void registerBoat(Registrar registrar, EntityType<? extends Boat> type, ModelLayerLocation modelId, boolean chest) {
        registrar.<Boat>register(type, context -> new PromenadeBoatRenderer(context, modelId, chest));
    }

    public interface Registrar {
        <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> provider);
    }
}
