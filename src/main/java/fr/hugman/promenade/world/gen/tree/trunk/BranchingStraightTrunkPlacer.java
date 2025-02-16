package fr.hugman.promenade.world.gen.tree.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class BranchingStraightTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BranchingStraightTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> fillTrunkPlacerFields(instance).apply(instance, BranchingStraightTrunkPlacer::new)
    );

    public static final List<Direction> HORIZONTAL = List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);

    public BranchingStraightTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return PromenadeTrunkPlacerTypes.BRANCHING_STRAIGHT;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            int height,
            BlockPos startPos,
            TreeFeatureConfig config
    ) {
        setToDirt(world, replacer, random, startPos.down(), config);

        Direction branchDirection = null;
        int branches = 0;

        for (int i = 0; i < height; i++) {
            var pos = startPos.up(i);
            this.getAndSetState(world, replacer, random, pos, config);
            if (random.nextInt(6) == 0 && branches < 2 && i > 2) {
                if (branchDirection != null) {
                    branchDirection = branchDirection.getOpposite();
                } else {
                    branchDirection = Direction.Type.HORIZONTAL.random(random);
                }
                Direction finalBranchDirection = branchDirection;
                this.getAndSetState(world, replacer, random, pos.offset(branchDirection), config, state -> state.withIfExists(PillarBlock.AXIS, finalBranchDirection.getAxis()));
                branches++;
            }
        }

        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false));
    }
}
