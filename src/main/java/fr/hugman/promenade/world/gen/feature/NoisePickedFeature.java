package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.util.NoiseScale;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public record NoisePickedFeature(
        NoiseScale noiseScale,
        List<NoisePickedFeatureEntry> entries
) implements Feature {
    public static final MapCodec<NoisePickedFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            NoiseScale.CODEC.fieldOf("noise").forGetter(NoisePickedFeature::noiseScale),
            NoisePickedFeatureEntry.CODEC.listOf().fieldOf("entries").forGetter(NoisePickedFeature::entries)
    ).apply(instance, NoisePickedFeature::new));

    @Override
    public MapCodec<NoisePickedFeature> codec() {
        return CODEC;
    }

    @Override
    public Stream<Holder<Feature>> getSubFeatures() {
        return this.entries.stream().flatMap(entry -> entry.feature().value().getFeatures());
    }

    @Override
    public boolean place(WorldGenLevel structureWorldAccess, ChunkGenerator chunkGenerator, RandomSource random, BlockPos pos) {
        double noiseValue = Biome.BIOME_INFO_NOISE.get((double) pos.getX() / this.noiseScale.x(), (double) pos.getZ() / this.noiseScale.z());
        var entries = this.entries.stream()
                .filter(entry -> entry.min() < noiseValue && noiseValue < entry.max())
                .toList();
        if (entries.isEmpty()) {
            return false;
        }
        var entry = entries.get(random.nextInt(entries.size()));
        return entry.generate(structureWorldAccess, chunkGenerator, random, pos);
    }
}
