package fr.hugman.promenade.gen.placement_modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.placementmodifier.AbstractCountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class NoiseIntervalCountPlacementModifier extends AbstractCountPlacementModifier {
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

	@Override
	protected int getCount(Random random, BlockPos pos) {
		double d = Biome.FOLIAGE_NOISE.sample((double) pos.getX() / 200.0, (double) pos.getZ() / 200.0, false);
		return (this.noiseLevelMin < d && d < this.noiseLevelMax) ? this.insideValue : this.outsideValue;
	}

	public PlacementModifierType<?> getType() {
		return PlacementModifierType.NOISE_THRESHOLD_COUNT;
	}
}
