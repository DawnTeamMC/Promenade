package com.hugman.promenade.object.world.gen.tree;

import com.google.common.collect.ImmutableList;
import com.hugman.promenade.init.PalmBundle;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class PalmTrunkPlacer extends TrunkPlacer {
	public static final Codec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> fillTrunkPlacerFields(instance).and(
			IntProvider.createValidatingCodec(0, 80).fieldOf("max_straight_length").forGetter(placer -> placer.maxStraightLength)
	).apply(instance, PalmTrunkPlacer::new));

	private final IntProvider maxStraightLength;

	public PalmTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider maxStraightLength) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
		this.maxStraightLength = maxStraightLength;
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return PalmBundle.PALM_TRUNK_PLACER;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
		Direction direction = Direction.Type.HORIZONTAL.random(random);
		BlockPos.Mutable mutable = startPos.mutableCopy().move(Direction.DOWN);

		int j = this.maxStraightLength.get(random);
		int k = j;

		setToDirt(world, replacer, random, mutable, config);
		for (int i = 0; i < height; ++i) {
			if (k <= 0) {
				mutable.move(direction);
				if (random.nextBoolean()) j--;
				k = j;
			}
			mutable.move(Direction.UP);
			getAndSetState(world, replacer, random, mutable, config);
			k--;
		}

		mutable.move(Direction.UP);
		return ImmutableList.of(new FoliagePlacer.TreeNode(mutable, 0, false));
	}
}
