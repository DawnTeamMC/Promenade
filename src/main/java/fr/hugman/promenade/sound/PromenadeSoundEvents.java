package fr.hugman.promenade.sound;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class PromenadeSoundEvents {
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_OVERWORLD_SAKURA_GROVES = ofRef("music.overworld.sakura_groves");

    public static final SoundEvent BLOCK_SNOWY_LEAVES_BREAK = of("block.snowy_leaves.break");
    public static final SoundEvent BLOCK_SNOWY_LEAVES_STEP = of("block.snowy_leaves.step");
    public static final SoundEvent BLOCK_SNOWY_LEAVES_PLACE = of("block.snowy_leaves.place");
    public static final SoundEvent BLOCK_SNOWY_LEAVES_HIT = of("block.snowy_leaves.hit");
    public static final SoundEvent BLOCK_SNOWY_LEAVES_FALL = of("block.snowy_leaves.fall");

    public static final SoundEvent CAPYBARA_AMBIENT = of("entity.capybara.ambient");
    public static final SoundEvent CAPYBARA_AMBIENT_BABY = of("entity.capybara.ambient.baby");
    public static final SoundEvent CAPYBARA_FART = of("entity.capybara.fart");

    public static final SoundEvent DUCK_AMBIENT = of("entity.duck.ambient");
    public static final SoundEvent DUCK_HURT = of("entity.duck.hurt");
    public static final SoundEvent DUCK_DEATH = of("entity.duck.death");
    public static final SoundEvent DUCK_STEP = of("entity.duck.step");

    public static final SoundEvent SUNKEN_SKELETON_AMBIENT = of("entity.sunken_skeleton.ambient");
    public static final SoundEvent SUNKEN_SKELETON_HURT = of("entity.sunken_skeleton.hurt");
    public static final SoundEvent SUNKEN_SKELETON_DEATH = of("entity.sunken_skeleton.death");
    public static final SoundEvent SUNKEN_SKELETON_STEP = of("entity.sunken_skeleton.step");
    public static final SoundEvent SUNKEN_SKELETON_SHOOT = of("entity.sunken_skeleton.shoot");

    private static RegistryEntry.Reference<SoundEvent> ofRef(String path) {
        var id = Promenade.id(path);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    private static SoundEvent of(String path) {
        var id = Promenade.id(path);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
