package com.hugman.wild_explorer.object.world.gen.decorator.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.decorator.DecoratorConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSafelistRangeDecoratorConfig implements DecoratorConfig {
	public final int count;
	public final int bottomOffset;
	public final int topOffset;
	public final int maximum;
	public final List<BlockState> safelist;

	public CountSafelistRangeDecoratorConfig(int count, int bottomOffset, int topOffset, int maximum, List<BlockState> safelist) {
		this.count = count;
		this.bottomOffset = bottomOffset;
		this.topOffset = topOffset;
		this.maximum = maximum;
		this.safelist = safelist;
	}

	public CountSafelistRangeDecoratorConfig(int count, int bottomOffset, int topOffset, int maximum, BlockState...safelist) {
		this(count, bottomOffset, topOffset, maximum, Arrays.asList(safelist));
	}

	public static final Codec<CountSafelistRangeDecoratorConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(Codec.INT.fieldOf("count").forGetter((config) -> {
			return config.count;
		}), Codec.INT.fieldOf("bottom_offset").orElse(0).forGetter((config) -> {
			return config.bottomOffset;
		}), Codec.INT.fieldOf("top_offset").orElse(0).forGetter((config) -> {
			return config.topOffset;
		}), Codec.INT.fieldOf("maximum").orElse(0).forGetter((config) -> {
			return config.maximum;
		}), BlockState.CODEC.listOf().fieldOf("safelist").orElse(new ArrayList<>()).forGetter((config) -> {
			return config.safelist;
		})).apply(instance, CountSafelistRangeDecoratorConfig::new);
	});
}
