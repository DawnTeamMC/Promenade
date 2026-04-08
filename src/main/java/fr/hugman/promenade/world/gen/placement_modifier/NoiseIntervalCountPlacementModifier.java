package fr.hugman.promenade.world.gen.placement_modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;

public class NoiseIntervalCountPlacementModifier extends RepeatingPlacement {
    public static final MapCodec<NoiseIntervalCountPlacementModifier> MODIFIER_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                    Codec.DOUBLE.fieldOf("noise_level_min").forGetter((pm) -> pm.noiseLevelMin),
                    Codec.DOUBLE.fieldOf("noise_level_max").forGetter((pm) -> pm.noiseLevelMax),
                    Codec.INT.fieldOf("inside_value").forGetter((pm) -> pm.insideValue),
                    Codec.INT.fieldOf("outside_value").forGetter((pm) -> pm.outsideValue))
            .apply(instance, NoiseIntervalCountPlacementModifier::new));
    private final double noiseLevelMin;
    private final double noiseLevelMax;
    private final int insideValue;
    private final int outsideValue;

    private NoiseIntervalCountPlacementModifier(double noiseLevelMin, double noiseLevelMax, int insideValue, int outsideValue) {
        this.noiseLevelMin = noiseLevelMin;
        this.noiseLevelMax = noiseLevelMax;
        this.insideValue = insideValue;
        this.outsideValue = outsideValue;
    }

    public static NoiseIntervalCountPlacementModifier of(double noiseLevelMin, double noiseLevelMax, int insideValue, int outsideValue) {
        return new NoiseIntervalCountPlacementModifier(noiseLevelMin, noiseLevelMax, insideValue, outsideValue);
    }


    @Override
    protected int count(RandomSource random, BlockPos pos) {
        double d = Biome.BIOME_INFO_NOISE.getValue((double) pos.getX() / 200.0, (double) pos.getZ() / 200.0, false);
        return (this.noiseLevelMin < d && d < this.noiseLevelMax) ? this.insideValue : this.outsideValue;
    }

    public PlacementModifierType<?> type() {
        return PromenadePlacementModifierTypes.NOISE_INTERVAL_COUNT;
    }
}
