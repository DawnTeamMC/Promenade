package fr.hugman.promenade.gen.tree.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.content.CommonContent;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class LeapingTrunkPlacer extends TrunkPlacer {
	public static final Codec<LeapingTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> fillTrunkPlacerFields(instance).and(instance.group(
			IntProvider.createValidatingCodec(0, 80).fieldOf("straight_max").forGetter(placer -> placer.straightMax),
			IntProvider.VALUE_CODEC.fieldOf("straight_difference").forGetter(placer -> placer.straightDifference),
			Codec.FLOAT.fieldOf("decline_chance").forGetter(placer -> placer.declineChance),
			Codec.INT.fieldOf("max_foliage_radius_bonus").forGetter(placer -> placer.maxFoliageRadiusBonus))
	).apply(instance, LeapingTrunkPlacer::new));

	private final IntProvider straightMax;
	private final IntProvider straightDifference;
	private final float declineChance;
	private final int maxFoliageRadiusBonus;

	public LeapingTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider straightMax, IntProvider straightDifference, float declineChance, int maxFoliageRadiusBonus) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
		this.straightMax = straightMax;
		this.straightDifference = straightDifference;
		this.declineChance = declineChance;
		this.maxFoliageRadiusBonus = maxFoliageRadiusBonus;
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return CommonContent.LEAPING_TRUNK_PLACER;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
		Direction direction = Direction.Type.HORIZONTAL.random(random);
		BlockPos.Mutable mutable = startPos.mutableCopy().move(Direction.DOWN);

		int j = this.straightMax.get(random);
		int k = j;

		setToDirt(world, replacer, random, mutable, config);
		for(int i = 0; i < height; ++i) {
			mutable.move(Direction.UP);
			if(k <= 0) {
				j += this.straightDifference.get(random);
				k = j;
				if(random.nextFloat() < this.declineChance && i < height - 1) {
					getAndSetState(world, replacer, random, mutable, config);
					if(random.nextBoolean()) {
						mutable.move(direction.rotateYClockwise());
					}
					else {
						mutable.move(direction.rotateYCounterclockwise());
					}
				}
				mutable.move(direction);
			}
			getAndSetState(world, replacer, random, mutable, config);
			k--;
		}

		mutable.move(Direction.UP);

		double heightRatio = MathHelper.clamp((height - this.baseHeight) / (double) (this.firstRandomHeight + this.secondRandomHeight), 0.0D, 0.999D);
		return ImmutableList.of(new FoliagePlacer.TreeNode(mutable, MathHelper.floor((this.maxFoliageRadiusBonus + 1) * heightRatio), false));
	}
}
