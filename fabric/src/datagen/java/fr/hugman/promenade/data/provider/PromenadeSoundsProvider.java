package fr.hugman.promenade.data.provider;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.Holder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

/**
 * Generates the mod's {@code sounds.json}. Fabric API has no sounds provider on 1.21.1.
 */
public class PromenadeSoundsProvider implements DataProvider {
    private final FabricDataOutput output;

    public PromenadeSoundsProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public String getName() {
        return "Sounds";
    }

    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        Map<String, SoundTypeBuilder> sounds = new TreeMap<>();
        this.configure((event, builder) -> sounds.put(event.getLocation().getPath(), builder));

        JsonObject root = new JsonObject();
        sounds.forEach((path, builder) -> root.add(path, builder.toJson()));
        var path = this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(this.output.getModId()).resolve("sounds.json");
        return DataProvider.saveStable(writer, root, path);
    }

    protected void configure(SoundExporter soundExporter) {
        // Blocks
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_BREAK, variantSoundBuilder(4, Promenade.id("block/snowy_leaves/break")).subtitle("subtitles.block.generic.break"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_PLACE, variantSoundBuilder(4, Promenade.id("block/snowy_leaves/break")).subtitle("subtitles.block.generic.place"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_FALL, variantSoundBuilder(6, Promenade.id("block/snowy_leaves/step")));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_HIT, variantSoundBuilder(6, Promenade.id("block/snowy_leaves/step")).subtitle("subtitles.block.generic.hit"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_STEP, variantSoundBuilder(6, Promenade.id("block/snowy_leaves/step")).subtitle("subtitles.block.generic.footsteps"));

        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_BREAK, variantSoundBuilder(7, Promenade.id("block/snowy_azalea_leaves/break")).subtitle("subtitles.block.generic.break"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_PLACE, variantSoundBuilder(7, Promenade.id("block/snowy_azalea_leaves/break")).subtitle("subtitles.block.generic.place"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_FALL, variantSoundBuilder(5, Promenade.id("block/snowy_azalea_leaves/step")));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_HIT, variantSoundBuilder(5, Promenade.id("block/snowy_azalea_leaves/step")).subtitle("subtitles.block.generic.hit"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_STEP, variantSoundBuilder(5, Promenade.id("block/snowy_azalea_leaves/step")).subtitle("subtitles.block.generic.footsteps"));

        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_BREAK, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/break")).subtitle("subtitles.block.generic.break"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_PLACE, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/break")).subtitle("subtitles.block.generic.place"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_FALL, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/step")));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_HIT, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/step")).subtitle("subtitles.block.generic.hit"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_STEP, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/step")).subtitle("subtitles.block.generic.footsteps"));

        // Entities
        soundExporter.add(PromenadeSoundEvents.DUCK_AMBIENT, variantSoundBuilder(PromenadeSoundEvents.DUCK_AMBIENT, 4));
        soundExporter.add(PromenadeSoundEvents.DUCK_HURT, variantSoundBuilder(PromenadeSoundEvents.DUCK_HURT, 3));
        soundExporter.add(PromenadeSoundEvents.DUCK_DEATH, variantSoundBuilder(PromenadeSoundEvents.DUCK_DEATH, 1));
        soundExporter.add(PromenadeSoundEvents.DUCK_STEP, new SoundTypeBuilder().subtitle("subtitles.block.generic.footsteps")
                .sound(Sound.ofFile(ResourceLocation.parse("mob/chicken/step1")))
                .sound(Sound.ofFile(ResourceLocation.parse("mob/chicken/step2")))
        );

        soundExporter.add(PromenadeSoundEvents.CAPYBARA_AMBIENT, variantSoundBuilder(PromenadeSoundEvents.CAPYBARA_AMBIENT, 5));
        soundExporter.add(PromenadeSoundEvents.CAPYBARA_AMBIENT_BABY, variantSoundBuilder(PromenadeSoundEvents.CAPYBARA_AMBIENT_BABY, 6).subtitle("subtitles.promenade.entity.capybara.ambient"));
        soundExporter.add(PromenadeSoundEvents.CAPYBARA_FART, variantSoundBuilder(PromenadeSoundEvents.CAPYBARA_FART, 6));

        soundExporter.add(PromenadeSoundEvents.SUNKEN_AMBIENT, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_AMBIENT, 3));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_HURT, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_HURT, 4));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_DEATH, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_DEATH, 3));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_SHOOT, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_SHOOT, 3));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_STEP, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_STEP, 4).subtitle("subtitles.block.generic.footsteps"));

        // Music
        soundExporter.add(PromenadeSoundEvents.MUSIC_OVERWORLD_SAKURA_GROVES, new SoundTypeBuilder()
                .sound(Sound.ofFile(Promenade.id("music/brise_couleur_pastel")).stream(true).volume(0.4f).weight(6))
                .sound(Sound.ofFile(ResourceLocation.parse("minecraft:music/game/minecraft")).stream(true))
                .sound(Sound.ofFile(ResourceLocation.parse("minecraft:music/game/sweden")).stream(true))
                .sound(Sound.ofFile(ResourceLocation.parse("minecraft:music/game/clark")).stream(true))
                .sound(Sound.ofFile(ResourceLocation.parse("minecraft:music/game/left_to_bloom")).stream(true).volume(0.4f))
                .sound(Sound.ofFile(ResourceLocation.parse("minecraft:music/game/featherfall")).stream(true).volume(0.4f).weight(3))
                .sound(Sound.ofFile(ResourceLocation.parse("minecraft:music/game/echo_in_the_wind")).stream(true).volume(0.4f).weight(3))
                .sound(Sound.ofFile(ResourceLocation.parse("minecraft:music/game/bromeliad")).stream(true).volume(0.4f).weight(3))
        );
    }

    private SoundTypeBuilder variantSoundBuilder(SoundEvent soundEvent, int count) {
        return variantSoundBuilder(new SoundTypeBuilder().subtitle(subtitle(soundEvent)), count, soundEvent.getLocation().withPath(s -> s.replace(".", "/")));
    }

    private SoundTypeBuilder variantSoundBuilder(int count, ResourceLocation baseId) {
        return variantSoundBuilder(new SoundTypeBuilder(), count, baseId.withPath(s -> s.replace(".", "/")));
    }

    private SoundTypeBuilder variantSoundBuilder(SoundTypeBuilder builder, int count, ResourceLocation baseId) {
        if (count > 1) {
            for (int i = 1; i <= count; i++) {
                builder.sound(Sound.ofFile(baseId.withSuffix("/" + i)));
            }
        } else {
            builder.sound(Sound.ofFile(baseId));
        }
        return builder;
    }

    /**
     * The default subtitle of a sound event, as Fabric API's sounds provider uses it.
     */
    private static String subtitle(SoundEvent soundEvent) {
        return soundEvent.getLocation().toLanguageKey("subtitles");
    }

    @FunctionalInterface
    protected interface SoundExporter {
        void add(SoundEvent soundEvent, SoundTypeBuilder builder);

        default void add(Holder<SoundEvent> soundEvent, SoundTypeBuilder builder) {
            this.add(soundEvent.value(), builder);
        }
    }

    protected static class SoundTypeBuilder {
        private final JsonArray sounds = new JsonArray();
        private String subtitle = null;

        public SoundTypeBuilder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public SoundTypeBuilder sound(Sound sound) {
            this.sounds.add(sound.toJson());
            return this;
        }

        JsonObject toJson() {
            JsonObject json = new JsonObject();
            json.add("sounds", this.sounds);
            if (this.subtitle != null) {
                json.addProperty("subtitle", this.subtitle);
            }
            return json;
        }
    }

    protected static class Sound {
        private final ResourceLocation name;
        private boolean stream = false;
        private float volume = 1.0f;
        private int weight = 1;

        private Sound(ResourceLocation name) {
            this.name = name;
        }

        public static Sound ofFile(ResourceLocation name) {
            return new Sound(name);
        }

        public Sound stream(boolean stream) {
            this.stream = stream;
            return this;
        }

        public Sound volume(float volume) {
            this.volume = volume;
            return this;
        }

        public Sound weight(int weight) {
            this.weight = weight;
            return this;
        }

        JsonElement toJson() {
            if (!this.stream && this.volume == 1.0f && this.weight == 1) {
                return new JsonPrimitive(this.name.toString());
            }
            JsonObject json = new JsonObject();
            json.addProperty("name", this.name.toString());
            if (this.stream) {
                json.addProperty("stream", true);
            }
            if (this.volume != 1.0f) {
                json.addProperty("volume", this.volume);
            }
            if (this.weight != 1) {
                json.addProperty("weight", this.weight);
            }
            return json;
        }
    }
}
