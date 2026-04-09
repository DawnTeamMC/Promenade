package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

public class PromenadeEntityRenderers {
    public static void register() {
        EntityRenderers.register(PromenadeEntityTypes.CAPYBARA, CapybaraEntityRenderer::new);
        EntityRenderers.register(PromenadeEntityTypes.DUCK, DuckEntityRenderer::new);
        EntityRenderers.register(PromenadeEntityTypes.LUSH_CREEPER, LushCreeperRenderer::new);
        EntityRenderers.register(PromenadeEntityTypes.SUNKEN, SunkenEntityRenderer::new);

        registerBoat(PromenadeEntityTypes.SAKURA_BOAT, PromenadeEntityModelLayers.SAKURA_BOAT);
        registerBoat(PromenadeEntityTypes.SAKURA_CHEST_BOAT, PromenadeEntityModelLayers.SAKURA_CHEST_BOAT);
        registerBoat(PromenadeEntityTypes.MAPLE_BOAT, PromenadeEntityModelLayers.MAPLE_BOAT);
        registerBoat(PromenadeEntityTypes.MAPLE_CHEST_BOAT, PromenadeEntityModelLayers.MAPLE_CHEST_BOAT);
        registerBoat(PromenadeEntityTypes.PALM_BOAT, PromenadeEntityModelLayers.PALM_BOAT);
        registerBoat(PromenadeEntityTypes.PALM_CHEST_BOAT, PromenadeEntityModelLayers.PALM_CHEST_BOAT);
    }

    private static void registerBoat(EntityType<? extends AbstractBoat> type, ModelLayerLocation modelId) {
        EntityRenderers.register(type, context -> new BoatRenderer(context, modelId));
    }
}
