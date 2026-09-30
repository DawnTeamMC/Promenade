package fr.hugman.promenade.world.gen.tree.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class LeapingTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<LeapingTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> trunkPlacerParts(instance).and(instance.group(
            IntProvider.codec(0, 80).fieldOf("straight_max").forGetter(placer -> placer.straightMax),
            IntProvider.CODEC.fieldOf("straight_difference").forGetter(placer -> placer.straightDifference),
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
    protected TrunkPlacerType<?> type() {
        return PromenadeTrunkPlacerTypes.LEAPING;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos mutable = startPos.mutable().move(Direction.DOWN);

        int j = this.straightMax.sample(random);
        int k = j;

        setDirtAt(world, replacer, random, mutable, config);
        for (int i = 0; i < height; ++i) {
            mutable.move(Direction.UP);
            if (k <= 0) {
                j += this.straightDifference.sample(random);
                k = j;
                if (random.nextFloat() < this.declineChance && i < height - 1) {
                    placeLog(world, replacer, random, mutable, config);
                    if (random.nextBoolean()) {
                        mutable.move(direction.getClockWise());
                    } else {
                        mutable.move(direction.getCounterClockWise());
                    }
                }
                mutable.move(direction);
            }
            placeLog(world, replacer, random, mutable, config);
            k--;
        }

        mutable.move(Direction.UP);

        double heightRatio = Mth.clamp((height - this.baseHeight) / (double) (this.heightRandA + this.heightRandB), 0.0D, 0.999D);
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(mutable, Mth.floor((this.maxFoliageRadiusBonus + 1) * heightRatio), false));
    }
}
