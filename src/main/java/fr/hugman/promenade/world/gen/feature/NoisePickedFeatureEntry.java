package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

public record NoisePickedFeatureEntry(
        RegistryEntry<PlacedFeature> feature,
        float min,
        float max
) implements FeatureConfig {
    public static final Codec<NoisePickedFeatureEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PlacedFeature.REGISTRY_CODEC.fieldOf("feature").forGetter(NoisePickedFeatureEntry::feature),
            Codec.FLOAT.fieldOf("min").forGetter(NoisePickedFeatureEntry::min),
            Codec.FLOAT.fieldOf("max").forGetter(NoisePickedFeatureEntry::max)
    ).apply(instance, NoisePickedFeatureEntry::new));

    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos) {
        return this.feature.value().generateUnregistered(world, chunkGenerator, random, pos);
    }
}
