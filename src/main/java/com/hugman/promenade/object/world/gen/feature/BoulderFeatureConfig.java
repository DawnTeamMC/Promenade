package com.hugman.promenade.object.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class BoulderFeatureConfig implements FeatureConfig {
	public static final Codec<BoulderFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> config.stateProvider),
			BlockState.CODEC.listOf().fieldOf("replaceable").forGetter((config) -> config.replaceableBlocks),
			IntProvider.createValidatingCodec(1, 64).fieldOf("count").forGetter((config) -> config.radius)
	).apply(instance, BoulderFeatureConfig::new));
	public final BlockStateProvider stateProvider;
	public final List<BlockState> replaceableBlocks;
	public final IntProvider radius;

	public BoulderFeatureConfig(BlockStateProvider stateProvider, List<BlockState> replaceableBlocks, IntProvider radius) {
		this.stateProvider = stateProvider;
		this.replaceableBlocks = replaceableBlocks;
		this.radius = radius;

	}
}