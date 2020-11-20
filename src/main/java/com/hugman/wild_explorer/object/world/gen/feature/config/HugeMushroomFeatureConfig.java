package com.hugman.wild_explorer.object.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.FeatureConfig;

public class HugeMushroomFeatureConfig implements FeatureConfig {
	public static final Codec<HugeMushroomFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockState.CODEC.fieldOf("stem_state").forGetter((config) -> {
			return config.stemState;
		}), Codec.INT.fieldOf("stem_base_height").forGetter((config) -> {
			return config.stemBaseHeight;
		}), Codec.INT.fieldOf("stem_random_height").forGetter((config) -> {
			return config.stemRandomHeight;
		}), BlockState.CODEC.fieldOf("hat_state").forGetter((config) -> {
			return config.hatState;
		}), Codec.INT.fieldOf("hat_base_size").forGetter((config) -> {
			return config.hatBaseSize;
		}), Codec.INT.fieldOf("hat_random_size").forGetter((config) -> {
			return config.hatRandomSize;
		}), Codec.BOOL.fieldOf("flat_hat").orElse(false).forGetter((config) -> {
			return config.flatHat;
		}), BlockState.CODEC.fieldOf("decor_state").forGetter((config) -> {
			return config.decorationState;
		}), Codec.DOUBLE.fieldOf("decor_chance").forGetter((config) -> {
			return config.decorationChance;
		}), Codec.DOUBLE.fieldOf("vine_chance").forGetter((config) -> {
			return config.vineChance;
		}), Codec.BOOL.fieldOf("upside_down").orElse(false).forGetter((config) -> {
			return config.upsideDown;
		})).apply(instance, HugeMushroomFeatureConfig::new);
	});
	public final BlockState stemState;
	public final int stemBaseHeight;
	public final int stemRandomHeight;
	public final BlockState hatState;
	public final int hatBaseSize;
	public final int hatRandomSize;
	public final boolean flatHat;
	public final BlockState decorationState;
	public final double decorationChance;
	public final double vineChance;
	public final boolean upsideDown;

	private HugeMushroomFeatureConfig(BlockState stemState, int stemBaseHeight, int stemRandomHeight, BlockState hatState, int hatBaseSize, int hatRandomSize, boolean flatHat, BlockState decorationState, double decorationChance, double vineChance, boolean upsideDown) {
		this.stemState = stemState;
		this.stemBaseHeight = stemBaseHeight;
		this.stemRandomHeight = stemRandomHeight;
		this.hatState = hatState;
		this.hatBaseSize = hatBaseSize;
		this.hatRandomSize = hatRandomSize;
		this.flatHat = flatHat;
		this.decorationState = decorationState;
		this.decorationChance = decorationChance;
		this.vineChance = vineChance;
		this.upsideDown = upsideDown;
	}

	public HugeMushroomFeatureConfig(int stemBaseHeight, int stemRandomHeight, BlockState hatState, int hatBaseSize, int hatRandomSize, BlockState decorationState, double decorationChance, double vineChance) {
		this(Blocks.MUSHROOM_STEM.getDefaultState(), stemBaseHeight, stemRandomHeight, hatState, hatBaseSize, hatRandomSize, false, decorationState, decorationChance, vineChance, false);
	}

	public HugeMushroomFeatureConfig setUpsideDown() {
		return new HugeMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stemRandomHeight, this.hatState, this.hatBaseSize, this.hatRandomSize, this.flatHat, this.decorationState, this.decorationChance, this.vineChance, true);
	}

	public HugeMushroomFeatureConfig setFlatHat() {
		return new HugeMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stemRandomHeight, this.hatState, this.hatBaseSize, this.hatRandomSize, true, this.decorationState, this.decorationChance, this.vineChance, this.upsideDown);
	}

	public HugeMushroomFeatureConfig setHatBaseSize(int hatBaseSize) {
		return new HugeMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stemRandomHeight, this.hatState, hatBaseSize, this.hatRandomSize, this.flatHat, this.decorationState, this.decorationChance, this.vineChance, this.upsideDown);
	}

	public HugeMushroomFeatureConfig setHatRandomSize(int hatRandomSize) {
		return new HugeMushroomFeatureConfig(this.stemState, this.stemBaseHeight, this.stemRandomHeight, this.hatState, this.hatBaseSize, hatRandomSize, this.flatHat, this.decorationState, this.decorationChance, this.vineChance, this.upsideDown);
	}
}