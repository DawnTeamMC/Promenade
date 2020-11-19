package com.hugman.wild_explorer.object.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.ArrayList;
import java.util.List;

public class SpikeFeatureConfig implements FeatureConfig {
	public static final Codec<SpikeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockState.CODEC.fieldOf("state").forGetter((config) -> {
			return config.state;
		}), BlockState.CODEC.listOf().fieldOf("base_states").orElse(new ArrayList<>()).forGetter((config) -> {
			return config.baseStates;
		}), Codec.INT.fieldOf("base_height").orElse(4).forGetter((config) -> {
			return config.baseHeight;
		}), Codec.INT.fieldOf("random_height").orElse(0).forGetter((config) -> {
			return config.randomHeight;
		}), Codec.FLOAT.fieldOf("base_radius").orElse(2.0f).forGetter((config) -> {
			return config.baseRadius;
		}), Codec.FLOAT.fieldOf("random_radius").orElse(0.0f).forGetter((config) -> {
			return config.randomRadius;
		}), Codec.FLOAT.fieldOf("base_ytheta").orElse(360.0F).forGetter((config) -> {
			return config.baseYtheta;
		}), Codec.FLOAT.fieldOf("random_ytheta").orElse(0.0f).forGetter((config) -> {
			return config.randomYtheta;
		}), Codec.FLOAT.fieldOf("base_ztheta").orElse(180.0f).forGetter((config) -> {
			return config.baseZtheta;
		}), Codec.FLOAT.fieldOf("random_ztheta").orElse(0.0f).forGetter((config) -> {
			return config.randomZtheta;
		})).apply(instance, SpikeFeatureConfig::new);
	});
	public final BlockState state;
	public final List<BlockState> baseStates;
	public final int baseHeight;
	public final int randomHeight;
	public final float baseRadius;
	public final float randomRadius;
	public final float baseYtheta;
	public final float randomYtheta;
	public final float baseZtheta;
	public final float randomZtheta;

	public SpikeFeatureConfig(BlockState state, List<BlockState> baseStates, int baseHeight, int randomHeight, float baseRadius, float randomRadius, float baseYtheta, float randomYtheta, float baseZtheta, float randomZtheta) {
		this.state = state;
		this.baseStates = baseStates;
		this.baseHeight = baseHeight;
		this.randomHeight = randomHeight;
		this.baseRadius = baseRadius;
		this.randomRadius = randomRadius;
		this.baseYtheta = baseYtheta;
		this.randomYtheta = randomYtheta;
		this.baseZtheta = baseZtheta;
		this.randomZtheta = randomZtheta;
	}
}