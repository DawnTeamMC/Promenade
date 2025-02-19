package fr.hugman.promenade.util;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record NoiseScale(float x, float z) {
    private static final Codec<NoiseScale> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.POSITIVE_FLOAT.fieldOf("x").forGetter(NoiseScale::x),
            Codecs.POSITIVE_FLOAT.fieldOf("z").forGetter(NoiseScale::z)
    ).apply(instance, NoiseScale::new));

    public static final Codec<NoiseScale> CODEC = Codec.either(Codecs.POSITIVE_FLOAT, DIRECT_CODEC).xmap(
            either -> either.map(value -> new NoiseScale(value, value), scale -> scale),
            scale -> scale.x == scale.z ? Either.left(scale.x) : Either.right(scale)
    );

    public static NoiseScale of(float value) {
        return new NoiseScale(value, value);
    }
}