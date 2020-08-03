package com.hugman.wild_explorer.object.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.FeatureConfig;

public class HugeNetherMushroomFeatureConfig implements FeatureConfig {
	public static final Codec<HugeNetherMushroomFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
	{
		return instance.group(BlockState.CODEC.fieldOf("stem_state").forGetter((config) ->
		{
			return config.stemState;
		}), Codec.INT.fieldOf("stem_base_height").forGetter((config) ->
		{
			return config.stemBaseHeight;
		}), Codec.INT.fieldOf("stem_random_height").forGetter((config) ->
		{
			return config.stepRandomHeight;
		}), BlockState.CODEC.fieldOf("hat_state").forGetter((config) ->
		{
			return config.hatState;
		}), Codec.INT.fieldOf("hat_base_size").forGetter((config) ->
		{
			return config.hatBaseSize;
		}), Codec.INT.fieldOf("hat_random_size").forGetter((config) ->
		{
			return config.hatRandomSize;
		}), Codec.BOOL.fieldOf("flat_hat").orElse(false).forGetter((config) ->
		{
			return config.flatHat;
		}), BlockState.CODEC.fieldOf("decor_state").forGetter((config) ->
		{
			return config.decorationState;
		}), Codec.DOUBLE.fieldOf("decor_chance").forGetter((config) ->
		{
			return config.decorationChance;
		}), Codec.DOUBLE.fieldOf("vine_chance").forGetter((config) ->
		{
			return config.vineChance;
		}), Codec.BOOL.fieldOf("upside_down").orElse(false).forGetter((config) ->
		{
			return config.upsideDown;
		})).apply(instance, HugeNetherMushroomFeatureConfig::new);
	});
	public final BlockState stemState;
	public final int stemBaseHeight;
	public final int stepRandomHeight;
	public final BlockState hatState;
	public final int hatBaseSize;
	public final int hatRandomSize;
	public final boolean flatHat;
	public final BlockState decorationState;
	public final double decorationChance;
	public final double vineChance;
	public final boolean upsideDown;

	private HugeNetherMushroomFeatureConfig(BlockState stemState, int stemBaseHeight, int stepRandomHeight, BlockState hatState, int hatBaseSize, int hatRandomSize, boolean flatHat, BlockState decorationState, double decorationChance, double vineChance, boolean upsideDown) {
		this.stemState = stemState;
		this.stemBaseHeight = stemBaseHeight;
		this.stepRandomHeight = stepRandomHeight;
		this.hatState = hatState;
		this.hatBaseSize = hatBaseSize;
		this.hatRandomSize = hatRandomSize;
		this.flatHat = flatHat;
		this.decorationState = decorationState;
		this.decorationChance = decorationChance;
		this.vineChance = vineChance;
		this.upsideDown = upsideDown;
	}

	public HugeNetherMushroomFeatureConfig(int stemBaseHeight, int stepRandomHeight, BlockState hatState, int hatBaseSize, int hatRandomSize, BlockState decorationState, double decorationChance, double vineChance) {
		this.stemState = Blocks.MUSHROOM_STEM.getDefaultState();
		this.stemBaseHeight = stemBaseHeight;
		this.stepRandomHeight = stepRandomHeight;
		this.hatState = hatState;
		this.hatBaseSize = hatBaseSize;
		this.hatRandomSize = hatRandomSize;
		this.flatHat = false;
		this.decorationState = decorationState;
		this.decorationChance = decorationChance;
		this.vineChance = vineChance;
		this.upsideDown = false;
	}

	public HugeNetherMushroomFeatureConfig setUpsideDown() {
		return new HugeNetherMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stepRandomHeight, this.hatState, this.hatBaseSize, this.hatRandomSize, this.flatHat, this.decorationState, this.decorationChance, this.vineChance, true);
	}

	public HugeNetherMushroomFeatureConfig setFlatHat() {
		return new HugeNetherMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stepRandomHeight, this.hatState, this.hatBaseSize, this.hatRandomSize, true, this.decorationState, this.decorationChance, this.vineChance, this.upsideDown);
	}

	public HugeNetherMushroomFeatureConfig setHatBaseSize(int hatBaseSize) {
		return new HugeNetherMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stepRandomHeight, this.hatState, hatBaseSize, this.hatRandomSize, this.flatHat, this.decorationState, this.decorationChance, this.vineChance, this.upsideDown);
	}

	public HugeNetherMushroomFeatureConfig setHatRandomSize(int hatRandomSize) {
		return new HugeNetherMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stepRandomHeight, this.hatState, this.hatBaseSize, hatRandomSize, this.flatHat, this.decorationState, this.decorationChance, this.vineChance, this.upsideDown);
	}
}