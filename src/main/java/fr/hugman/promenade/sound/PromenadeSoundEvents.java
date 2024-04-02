package fr.hugman.promenade.sound;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

public class PromenadeSoundEvents {
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_OVERWORLD_SAKURA_GROVES = ofRef("music.overworld.sakura_groves");

    private static RegistryEntry.Reference<SoundEvent> ofRef(String path) {
        var id = Promenade.id(path);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
