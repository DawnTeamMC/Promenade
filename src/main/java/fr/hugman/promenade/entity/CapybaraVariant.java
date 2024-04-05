package fr.hugman.promenade.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

//TODO : .withPath(oldPath -> "textures/" + oldPath + ".png")
public record CapybaraVariant(
        int spawnWeight,
        Identifier smallEyesTexture,
        Identifier largeEyesTexture,
        Identifier closedEyesTexture
) {
    public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("spawn_weight").forGetter(CapybaraVariant::spawnWeight),
            Identifier.CODEC.fieldOf("small_eyes_texture").forGetter(CapybaraVariant::smallEyesTexture),
            Identifier.CODEC.fieldOf("large_eyes_texture").forGetter(CapybaraVariant::largeEyesTexture),
            Identifier.CODEC.fieldOf("closed_eyes_texture").forGetter(CapybaraVariant::closedEyesTexture)
    ).apply(instance, CapybaraVariant::new));
    public static final Codec<RegistryEntry<CapybaraVariant>> ENTRY_CODEC = RegistryElementCodec.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, CODEC);
}
