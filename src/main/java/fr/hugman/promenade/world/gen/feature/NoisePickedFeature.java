package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class NoisePickedFeature extends Feature<NoisePickedFeatureConfig> {
    public NoisePickedFeature(Codec<NoisePickedFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<NoisePickedFeatureConfig> context) {
        var pos = context.getOrigin();
        var random = context.getRandom();
        var structureWorldAccess = context.getWorld();
        var chunkGenerator = context.getGenerator();

        double noiseValue = Biome.FOLIAGE_NOISE.sample((double) pos.getX() / 200.0, (double) pos.getZ() / 200.0, false);
        var entries = context.getConfig().entries().stream()
                .filter(entry -> entry.min() < noiseValue && noiseValue < entry.max())
                .toList();
        if (entries.isEmpty()) {
            return false;
        }
        var entry = entries.get(context.getRandom().nextInt(entries.size()));
        return entry.generate(structureWorldAccess, chunkGenerator, random, pos);
    }
}
