package fr.hugman.promenade.entity.spawn;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.MinMaxBounds;
import fr.hugman.promenade.codec.PromenadeCodecs;

public record MoonBrightnessCheck(MinMaxBounds.Doubles range) implements SpawnCondition {
    public static final MapCodec<MoonBrightnessCheck> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            PromenadeCodecs.DOUBLE_BOUNDS.fieldOf("range").forGetter(MoonBrightnessCheck::range)
    ).apply(instance, MoonBrightnessCheck::new));

    @Override
    public boolean test(SpawnContext context) {
        return this.range.matches(context.level().getMoonBrightness());
    }

    @Override
    public MapCodec<MoonBrightnessCheck> codec() {
        return CODEC;
    }
}
