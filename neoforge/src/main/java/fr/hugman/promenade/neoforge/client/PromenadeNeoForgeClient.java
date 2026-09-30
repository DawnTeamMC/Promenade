package fr.hugman.promenade.neoforge.client;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.client.color.block.PromenadeBlockColors;
import fr.hugman.promenade.client.config.PromenadeConfigScreen;
import fr.hugman.promenade.client.particle.PromenadeParticles;
import fr.hugman.promenade.client.render.block.PromenadeBlockRenderLayers;
import fr.hugman.promenade.client.render.entity.PromenadeEntityRenderers;
import fr.hugman.promenade.client.render.entity.model.PromenadeEntityModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Promenade.MOD_ID, dist = Dist.CLIENT)
public class PromenadeNeoForgeClient {
    public PromenadeNeoForgeClient(IEventBus modBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (mod, parent) -> new PromenadeConfigScreen(parent));

        modBus.addListener(FMLClientSetupEvent.class, event -> PromenadeBlockRenderLayers.register(PromenadeNeoForgeClient::setRenderLayer));
        modBus.addListener(EntityRenderersEvent.RegisterLayerDefinitions.class, event -> PromenadeEntityModelLayers.register(event::registerLayerDefinition));
        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> PromenadeBlockColors.register(event::register));
        modBus.addListener(RegisterColorHandlersEvent.Item.class, event -> PromenadeBlockColors.registerItems(event::register));
        modBus.addListener(EntityRenderersEvent.RegisterRenderers.class, event -> PromenadeEntityRenderers.register(event::registerEntityRenderer));
        modBus.addListener(RegisterParticleProvidersEvent.class, event -> PromenadeParticles.register((type, provider) -> event.registerSpriteSet(type, provider::apply)));
    }

    // Deprecated in favour of "render_type" in the model JSON, which Fabric ignores: this keeps one list for both loaders
    @SuppressWarnings("deprecation")
    private static void setRenderLayer(RenderType renderType, Block... blocks) {
        for (Block block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block, renderType);
        }
    }
}
