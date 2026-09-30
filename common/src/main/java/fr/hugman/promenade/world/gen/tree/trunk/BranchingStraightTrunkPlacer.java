package fr.hugman.promenade.world.gen.tree.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class BranchingStraightTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BranchingStraightTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance).apply(instance, BranchingStraightTrunkPlacer::new)
    );

    public static final List<Direction> HORIZONTAL = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);

    public BranchingStraightTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return PromenadeTrunkPlacerTypes.BRANCHING_STRAIGHT;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader world,
            BiConsumer<BlockPos, BlockState> replacer,
            RandomSource random,
            int height,
            BlockPos startPos,
            TreeConfiguration config
    ) {
        setDirtAt(world, replacer, random, startPos.below(), config);

        Direction branchDirection = null;
        int branches = 0;

        //TODO: configurability
        for (int i = 0; i < height; i++) {
            var pos = startPos.above(i);
            this.placeLog(world, replacer, random, pos, config);
            if (random.nextInt(6) == 0 && branches < 2 && i > 2) {
                if (branchDirection != null) {
                    branchDirection = branchDirection.getOpposite();
                } else {
                    branchDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                }
                Direction finalBranchDirection = branchDirection;
                this.placeLog(world, replacer, random, pos.relative(branchDirection), config, state -> state.trySetValue(RotatedPillarBlock.AXIS, finalBranchDirection.getAxis()));
                branches++;
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));
    }
}
