package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.codec.PromenadeCodecs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record NoisePickedFeatureEntry(
        Holder<PlacedFeature> feature,
        float min,
        float max
) {
    public static final Codec<NoisePickedFeatureEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PlacedFeature.CODEC.fieldOf("feature").forGetter(NoisePickedFeatureEntry::feature),
            PromenadeCodecs.SAMPLED_NOISE_VALUE.fieldOf("min").forGetter(NoisePickedFeatureEntry::min),
            PromenadeCodecs.SAMPLED_NOISE_VALUE.fieldOf("max").forGetter(NoisePickedFeatureEntry::max)
    ).apply(instance, NoisePickedFeatureEntry::new));

    public boolean generate(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource random, BlockPos pos) {
        return this.feature.value().place(world, chunkGenerator, random, pos);
    }
}
