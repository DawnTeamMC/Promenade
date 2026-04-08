package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record BoulderFeatureConfig(
        BlockStateProvider stateProvider,
        BlockPredicate replaceableBlocks,
        IntProvider radius
) implements FeatureConfiguration {
    public static final Codec<BoulderFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("state").forGetter((config) -> config.stateProvider),
            BlockPredicate.CODEC.fieldOf("replaceable").forGetter((config) -> config.replaceableBlocks),
            IntProviders.codec(1, 64).fieldOf("count").forGetter((config) -> config.radius)
    ).apply(instance, BoulderFeatureConfig::new));
}