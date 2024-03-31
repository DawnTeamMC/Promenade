package fr.hugman.promenade.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public record CapybaraVariant(
        int spawnWeight,
        Identifier baseTexture,
        Identifier largeOpenEyesTexture,
        Identifier largeClosedEyesTexture,
        Identifier regularOpenEyesTexture,
        Identifier regularClosedEyesTexture
) {
    public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("spawn_weight").forGetter(CapybaraVariant::spawnWeight),
            Identifier.CODEC.fieldOf("base_texture").forGetter(CapybaraVariant::baseTexture),
            Identifier.CODEC.fieldOf("large_open_eyes_texture").forGetter(CapybaraVariant::largeOpenEyesTexture),
            Identifier.CODEC.fieldOf("large_closed_eyes_texture").forGetter(CapybaraVariant::largeClosedEyesTexture),
            Identifier.CODEC.fieldOf("regular_open_eyes_texture").forGetter(CapybaraVariant::regularOpenEyesTexture),
            Identifier.CODEC.fieldOf("regular_closed_eyes_texture").forGetter(CapybaraVariant::regularClosedEyesTexture)
    ).apply(instance, CapybaraVariant::new));
    public static final Codec<RegistryEntry<CapybaraVariant>> ENTRY_CODEC = RegistryElementCodec.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, CODEC);
}
