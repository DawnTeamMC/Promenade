package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class PromenadeSoundsProvider extends FabricSoundsProvider {
    public PromenadeSoundsProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "Sounds";
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, SoundExporter soundExporter) {
        // Blocks
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_BREAK, variantSoundBuilder(4, Promenade.id("block/snowy_leaves/break")).subtitle("subtitles.block.generic.break"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_PLACE, variantSoundBuilder(4, Promenade.id("block/snowy_leaves/break")).subtitle("subtitles.block.generic.place"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_FALL, variantSoundBuilder(6, Promenade.id("block/snowy_leaves/step")));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_HIT, variantSoundBuilder(6, Promenade.id("block/snowy_leaves/step")).subtitle("subtitles.block.generic.hit"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_STEP, variantSoundBuilder(6, Promenade.id("block/snowy_leaves/step")).subtitle("subtitles.block.generic.step"));

        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_BREAK, variantSoundBuilder(7, Promenade.id("block/snowy_azalea_leaves/break")).subtitle("subtitles.block.generic.break"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_PLACE, variantSoundBuilder(7, Promenade.id("block/snowy_azalea_leaves/break")).subtitle("subtitles.block.generic.place"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_FALL, variantSoundBuilder(5, Promenade.id("block/snowy_azalea_leaves/step")));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_HIT, variantSoundBuilder(5, Promenade.id("block/snowy_azalea_leaves/step")).subtitle("subtitles.block.generic.hit"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_STEP, variantSoundBuilder(5, Promenade.id("block/snowy_azalea_leaves/step")).subtitle("subtitles.block.generic.step"));

        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_BREAK, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/break")).subtitle("subtitles.block.generic.break"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_PLACE, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/break")).subtitle("subtitles.block.generic.place"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_FALL, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/step")));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_HIT, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/step")).subtitle("subtitles.block.generic.hit"));
        soundExporter.add(PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_STEP, variantSoundBuilder(5, Promenade.id("block/snowy_cherry_leaves/step")).subtitle("subtitles.block.generic.step"));

        // Entities
        soundExporter.add(PromenadeSoundEvents.DUCK_AMBIENT, variantSoundBuilder(PromenadeSoundEvents.DUCK_AMBIENT, 4));
        soundExporter.add(PromenadeSoundEvents.DUCK_HURT, variantSoundBuilder(PromenadeSoundEvents.DUCK_HURT, 3));
        soundExporter.add(PromenadeSoundEvents.DUCK_DEATH, variantSoundBuilder(PromenadeSoundEvents.DUCK_DEATH, 1));
        soundExporter.add(PromenadeSoundEvents.DUCK_STEP, SoundTypeBuilder.of().subtitle("subtitles.block.generic.footsteps")
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("mob/chicken/step1")))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("mob/chicken/step2")))
        );

        soundExporter.add(PromenadeSoundEvents.CAPYBARA_AMBIENT, variantSoundBuilder(PromenadeSoundEvents.CAPYBARA_AMBIENT, 5));
        soundExporter.add(PromenadeSoundEvents.CAPYBARA_AMBIENT_BABY, variantSoundBuilder(PromenadeSoundEvents.CAPYBARA_AMBIENT_BABY, 6));
        soundExporter.add(PromenadeSoundEvents.CAPYBARA_FART, variantSoundBuilder(PromenadeSoundEvents.CAPYBARA_FART, 6));

        soundExporter.add(PromenadeSoundEvents.SUNKEN_AMBIENT, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_AMBIENT, 3));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_HURT, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_HURT, 4));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_DEATH, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_DEATH, 3));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_SHOOT, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_SHOOT, 3));
        soundExporter.add(PromenadeSoundEvents.SUNKEN_STEP, variantSoundBuilder(PromenadeSoundEvents.SUNKEN_STEP, 4).subtitle("subtitles.block.generic.footsteps"));

        // Music
        soundExporter.add(PromenadeSoundEvents.MUSIC_OVERWORLD_SAKURA_GROVES, SoundTypeBuilder.of()
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Promenade.id("music/brise_couleur_pastel")).stream(true).volume(0.4f).weight(6))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("minecraft:music/game/minecraft")).stream(true))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("minecraft:music/game/sweden")).stream(true))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("minecraft:music/game/clark")).stream(true))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("minecraft:music/game/left_to_bloom")).stream(true).volume(0.4f))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("minecraft:music/game/featherfall")).stream(true).volume(0.4f).weight(3))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("minecraft:music/game/echo_in_the_wind")).stream(true).volume(0.4f).weight(3))
                .sound(SoundTypeBuilder.EntryBuilder.ofFile(Identifier.of("minecraft:music/game/bromeliad")).stream(true).volume(0.4f).weight(3))
        );
    }

    private SoundTypeBuilder variantSoundBuilder(SoundEvent soundEvent, int count) {
        return variantSoundBuilder(SoundTypeBuilder.of(soundEvent), count, soundEvent.id().withPath(s -> s.replace(".", "/")));
    }

    private SoundTypeBuilder variantSoundBuilder(SoundEvent soundEvent, int count, Identifier baseId) {
        return variantSoundBuilder(SoundTypeBuilder.of(soundEvent), count, baseId.withPath(s -> s.replace(".", "/")));
    }

    private SoundTypeBuilder variantSoundBuilder(int count, Identifier baseId) {
        return variantSoundBuilder(SoundTypeBuilder.of(), count, baseId.withPath(s -> s.replace(".", "/")));
    }

    private SoundTypeBuilder variantSoundBuilder(SoundTypeBuilder builder, int count, Identifier baseId) {
        if (count > 1) {
            for (int i = 1; i <= count; i++) {
                Identifier soundId = baseId.withSuffixedPath("/" + i);
                builder.sound(SoundTypeBuilder.EntryBuilder.ofFile(soundId));
            }
        } else {
            builder.sound(SoundTypeBuilder.EntryBuilder.ofFile(baseId));
        }
        return builder;
    }
}