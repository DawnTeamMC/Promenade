package fr.hugman.promenade.forge.client;

import fr.hugman.promenade.block.type.PromenadeWoodTypes;
import fr.hugman.promenade.client.color.block.PromenadeBlockColors;
import fr.hugman.promenade.client.config.PromenadeConfigScreen;
import fr.hugman.promenade.client.particle.PromenadeParticles;
import fr.hugman.promenade.client.render.block.PromenadeBlockRenderLayers;
import fr.hugman.promenade.client.render.entity.PromenadeEntityRenderers;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.stream.Stream;

/**
 * Only loaded on the client, by {@link fr.hugman.promenade.forge.PromenadeForge}: Forge 1.20.1 has no client-only mod entrypoints.
 */
public class PromenadeForgeClient {
    public static void init(IEventBus modBus) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> new PromenadeConfigScreen(parent)));

        modBus.addListener((FMLClientSetupEvent event) -> event.enqueueWork(() -> {
            PromenadeBlockRenderLayers.register(PromenadeForgeClient::setRenderLayer);
            // Sign textures are looked up by wood type, from a map that may be built before the mod's wood types exist
            Stream.of(PromenadeWoodTypes.SAKURA, PromenadeWoodTypes.MAPLE, PromenadeWoodTypes.PALM, PromenadeWoodTypes.DARK_AMARANTH).forEach(Sheets::addWoodType);
        }));
        modBus.addListener((EntityRenderersEvent.RegisterLayerDefinitions event) -> PromenadeEntityModelLayers.register(event::registerLayerDefinition));
        modBus.addListener((RegisterColorHandlersEvent.Block event) -> PromenadeBlockColors.register(event::register));
        modBus.addListener((RegisterColorHandlersEvent.Item event) -> PromenadeBlockColors.registerItems(event::register));
        modBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> PromenadeEntityRenderers.register(event::registerEntityRenderer));
        modBus.addListener((RegisterParticleProvidersEvent event) -> PromenadeParticles.register((type, provider) -> event.registerSpriteSet(type, provider::apply)));
    }

    // Deprecated in favour of "render_type" in the model JSON, which Fabric ignores: this keeps one list for both loaders
    @SuppressWarnings("deprecation")
    private static void setRenderLayer(RenderType renderType, Block... blocks) {
        for (Block block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block, renderType);
        }
    }
}
