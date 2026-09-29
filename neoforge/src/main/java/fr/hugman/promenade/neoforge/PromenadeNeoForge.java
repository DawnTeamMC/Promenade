package fr.hugman.promenade.neoforge;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.platform.PromenadePlatform;
import fr.hugman.promenade.registry.PromenadeRegistries;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.NewDatapackRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Promenade.MOD_ID)
public class PromenadeNeoForge {
    private static boolean registered = false;

    public PromenadeNeoForge(IEventBus modBus) {
        ((NeoForgePromenadePlatform) PromenadePlatform.INSTANCE).subscribe(modBus);

        modBus.addListener(NewDatapackRegistryEvent.class, event -> PromenadeRegistries.register(event::worldRegistry));
        modBus.addListener(RegisterEvent.class, PromenadeNeoForge::register);
    }

    /**
     * NeoForge keeps every registry open for the whole registration phase, so all of Promenade's content is registered
     * at once, as soon as the first registry is ready, just like on Fabric.
     */
    private static void register(RegisterEvent event) {
        if (registered) {
            return;
        }
        registered = true;

        Promenade.init();
        Registry.register(NeoForgeRegistries.BIOME_MODIFIER_SERIALIZERS, Promenade.id("platform"), PromenadeBiomeModifier.CODEC);
    }
}
