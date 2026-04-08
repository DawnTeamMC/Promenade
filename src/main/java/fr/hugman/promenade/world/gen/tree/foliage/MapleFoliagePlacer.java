package fr.hugman.promenade.world.gen.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class MapleFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<MapleFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            MapleFoliagePlacer.foliagePlacerParts(instance).and(
                    IntProviders.codec(0, 32).optionalFieldOf("height", ConstantInt.of(0)).forGetter(placer -> placer.height)
            ).apply(instance, MapleFoliagePlacer::new));

    protected final IntProvider height;

    public MapleFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return PromenadeFoliagePlacerTypes.MAPLE;
    }

    @Override
    protected void createFoliage(WorldGenLevel world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
        var pos = treeNode.pos().below(foliageHeight - offset);

        var curvature = radius;

        radius = Math.max(
                (foliageHeight + 1) / 2,
                (foliageHeight + 1) / 2 * (1 + curvature)
        );

        //TODO: does this support giant trunks?
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {

                int d = Math.abs(dx) + Math.abs(dz);
                int k = Mth.ceil(Math.abs(Math.abs(dx) - Math.abs(dz)));

                // the further we are from the trunk, the lower the height
                int y1 = Math.max(0, d - 1);
                int y2 = Math.min(foliageHeight, foliageHeight - d * curvature - k);

                if (y2 <= y1) continue;

                this.generateColumn(world, placer, config, random, pos, dz, dx, y1, y2);
            }
        }
    }

    protected void generateColumn(WorldGenLevel world, FoliageSetter placer, TreeConfiguration config, RandomSource random, BlockPos centerPos, int dx, int dz, int y1, int y2) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        mutable.setWithOffset(centerPos, dz, y1, dx);
        for (int y = y1; y < y2; y++) {
            mutable.move(0, 1, 0);
            FoliagePlacer.tryPlaceLeaf(world, placer, random, config, mutable);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return this.height.sample(random);
    }


    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
