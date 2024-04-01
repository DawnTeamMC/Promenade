package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record BoulderFeatureConfig(BlockStateProvider stateProvider, RegistryEntryList<Block> replaceableBlocks,
                                   IntProvider radius) implements FeatureConfig {
    public static final Codec<BoulderFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> config.stateProvider),
            RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("replaceable").forGetter((config) -> config.replaceableBlocks),
            IntProvider.createValidatingCodec(1, 64).fieldOf("count").forGetter((config) -> config.radius)
    ).apply(instance, BoulderFeatureConfig::new));
}