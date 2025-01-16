package fr.hugman.promenade.client.render.entity;

import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractBoatEntity;

@Environment(EnvType.CLIENT)
public class PromenadeEntityRenderers {
    public static void register() {
        EntityRendererRegistry.register(PromenadeEntityTypes.CAPYBARA, CapybaraEntityRenderer::new);
        EntityRendererRegistry.register(PromenadeEntityTypes.DUCK, DuckEntityRenderer::new);
        EntityRendererRegistry.register(PromenadeEntityTypes.LUSH_CREEPER, LushCreeperRenderer::new);
        EntityRendererRegistry.register(PromenadeEntityTypes.SUNKEN, SunkenEntityRenderer::new);

        registerBoat(PromenadeEntityTypes.SAKURA_BOAT, PromenadeEntityModelLayers.SAKURA_BOAT);
        registerBoat(PromenadeEntityTypes.SAKURA_CHEST_BOAT, PromenadeEntityModelLayers.SAKURA_CHEST_BOAT);
        registerBoat(PromenadeEntityTypes.MAPLE_BOAT, PromenadeEntityModelLayers.MAPLE_BOAT);
        registerBoat(PromenadeEntityTypes.MAPLE_CHEST_BOAT, PromenadeEntityModelLayers.MAPLE_CHEST_BOAT);
        registerBoat(PromenadeEntityTypes.PALM_BOAT, PromenadeEntityModelLayers.PALM_BOAT);
        registerBoat(PromenadeEntityTypes.PALM_CHEST_BOAT, PromenadeEntityModelLayers.PALM_CHEST_BOAT);
    }

    private static void registerBoat(EntityType<? extends AbstractBoatEntity> type, EntityModelLayer modelLayer) {
        EntityRendererRegistry.register(type, context -> new BoatEntityRenderer(context, modelLayer));
    }
}
