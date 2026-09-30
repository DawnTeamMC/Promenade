package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class NoisePickedFeature extends Feature<NoisePickedFeatureConfig> {
    public NoisePickedFeature(Codec<NoisePickedFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoisePickedFeatureConfig> context) {
        var pos = context.origin();
        var random = context.random();
        var structureWorldAccess = context.level();
        var chunkGenerator = context.chunkGenerator();
        var config = context.config();

        double noiseValue = Biome.BIOME_INFO_NOISE.getValue((double) pos.getX() / config.noiseScale().x(), (double) pos.getZ() / config.noiseScale().z(), false);
        var entries = config.entries().stream()
                .filter(entry -> entry.min() < noiseValue && noiseValue < entry.max())
                .toList();
        if (entries.isEmpty()) {
            return false;
        }
        var entry = entries.get(context.random().nextInt(entries.size()));
        return entry.generate(structureWorldAccess, chunkGenerator, random, pos);
    }
}
