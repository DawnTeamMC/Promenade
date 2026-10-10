package fr.hugman.promenade.world.gen.placement_modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;

public record NoiseIntervalCountPlacementModifier(
        double noiseLevelMin,
        double noiseLevelMax,
        int insideValue,
        int outsideValue
) implements RepeatingPlacement {
    public static final MapCodec<NoiseIntervalCountPlacementModifier> MODIFIER_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                    Codec.DOUBLE.fieldOf("noise_level_min").forGetter(NoiseIntervalCountPlacementModifier::noiseLevelMin),
                    Codec.DOUBLE.fieldOf("noise_level_max").forGetter(NoiseIntervalCountPlacementModifier::noiseLevelMax),
                    Codec.INT.fieldOf("inside_value").forGetter(NoiseIntervalCountPlacementModifier::insideValue),
                    Codec.INT.fieldOf("outside_value").forGetter(NoiseIntervalCountPlacementModifier::outsideValue))
            .apply(instance, NoiseIntervalCountPlacementModifier::new));

    public static NoiseIntervalCountPlacementModifier of(double noiseLevelMin, double noiseLevelMax, int insideValue, int outsideValue) {
        return new NoiseIntervalCountPlacementModifier(noiseLevelMin, noiseLevelMax, insideValue, outsideValue);
    }

    @Override
    public int count(RandomSource random, BlockPos pos) {
        double d = Biome.BIOME_INFO_NOISE.get((double) pos.getX() / 200.0, (double) pos.getZ() / 200.0);
        return (this.noiseLevelMin < d && d < this.noiseLevelMax) ? this.insideValue : this.outsideValue;
    }

    @Override
    public MapCodec<NoiseIntervalCountPlacementModifier> codec() {
        return MODIFIER_CODEC;
    }
}
