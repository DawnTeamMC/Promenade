package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BoulderFeature extends Feature<BoulderFeatureConfig> {
    public BoulderFeature(Codec<BoulderFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BoulderFeatureConfig> context) {
        RandomSource random = context.random();
        BoulderFeatureConfig config = context.config();
        WorldGenLevel world = context.level();
        int radius = config.radius().sample(random);
        BlockPos pos = context.origin();
        for (; pos.getY() > world.getMinY() + radius; pos = pos.below()) {
            if (!world.isEmptyBlock(pos)) {
                if (config.replaceableBlocks().test(world, pos)) {
                    break;
                }
            }
        }
        if (pos.getY() <= world.getMinY() + radius) {
            return false;
        } else {
            for (int i = 0; i < 3; ++i) {
                int j = random.nextInt(radius);
                int k = random.nextInt(radius);
                int l = random.nextInt(radius);
                float f = (float) (j + k + l) * 0.333F + 0.5F;
                for (BlockPos pos2 : BlockPos.betweenClosed(pos.offset(-j, -k, -l), pos.offset(j, k, l))) {
                    if (pos2.distSqr(pos) <= (double) (f * f)) {
                        this.setBlock(world, pos2, config.stateProvider().getState(random, pos));
                    }
                }
                pos = pos.offset(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
            }
            return true;
        }
    }
}
