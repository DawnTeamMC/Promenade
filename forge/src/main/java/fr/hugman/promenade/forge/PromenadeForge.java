package fr.hugman.promenade.forge;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.forge.client.PromenadeForgeClient;
import fr.hugman.promenade.platform.PromenadePlatform;
import fr.hugman.promenade.registry.PromenadeCompostables;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.PromenadeStrippables;
import fr.hugman.promenade.world.item.trading.PromenadeVillagerTrades;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DataPackRegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Map;

@Mod(Promenade.MOD_ID)
public class PromenadeForge {
    private static final Map<Block, Block> STRIPPABLES = new HashMap<>();
    private static boolean registered = false;

    public PromenadeForge() {
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ((ForgePromenadePlatform) PromenadePlatform.INSTANCE).subscribe(modBus);

        modBus.addListener((DataPackRegistryEvent.NewRegistry event) -> PromenadeRegistries.register(event::dataPackRegistry));
        modBus.addListener(PromenadeForge::register);
        // Forge 1.20.1 has no data maps: compostables are added to vanilla's map, and strippables are given by an event
        modBus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(() -> {
            PromenadeCompostables.all().forEach((item, chance) -> ComposterBlock.COMPOSTABLES.put(item.asItem(), chance.floatValue()));
            PromenadeStrippables.register(STRIPPABLES::put);
        }));
        MinecraftForge.EVENT_BUS.addListener(PromenadeForge::strip);
        MinecraftForge.EVENT_BUS.addListener((WandererTradesEvent event) -> event.getGenericTrades().addAll(PromenadeVillagerTrades.wanderingTraderCommonTrades()));

        if (FMLEnvironment.dist.isClient()) {
            PromenadeForgeClient.init(modBus);
        }
    }

    /**
     * Forge keeps every registry open until its own registration event, so all of Promenade's content is registered
     * at once, as soon as the first registry is ready, just like on Fabric.
     */
    private static void register(RegisterEvent event) {
        if (registered) {
            return;
        }
        registered = true;

        Promenade.init();
        ForgeRegistries.BIOME_MODIFIER_SERIALIZERS.get().register(Promenade.id("platform"), PromenadeBiomeModifier.CODEC);
    }

    private static void strip(BlockEvent.BlockToolModificationEvent event) {
        if (event.getToolAction() != ToolActions.AXE_STRIP) {
            return;
        }
        var state = event.getState();
        var stripped = STRIPPABLES.get(state.getBlock());
        if (stripped != null) {
            event.setFinalState(stripped.withPropertiesOf(state));
        }
    }
}
