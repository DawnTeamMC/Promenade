package fr.hugman.promenade.world.gen.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class MapleFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<MapleFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            MapleFoliagePlacer.fillFoliagePlacerFields(instance).and(
                    IntProvider.createValidatingCodec(0, 16).optionalFieldOf("height", ConstantIntProvider.create(0)).forGetter(placer -> placer.height)
            ).apply(instance, MapleFoliagePlacer::new));

    protected final IntProvider height;

    public MapleFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return PromenadeFoliagePlacerTypes.MAPLE;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        var pos = treeNode.getCenter().down(offset);

        var curvature = radius;

        radius = Math.max(
                (foliageHeight + 1) / 2,
                (foliageHeight + 1) / 2 * (1 + curvature)
        );

        //TODO: support giant trunks
        //TODO: more configurability?
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {

                int d = Math.abs(dx) + Math.abs(dz);
                int k = MathHelper.ceil(Math.abs(Math.abs(dx) - Math.abs(dz)));

                // the further we are from the trunk, the lower the height
                int y1 = Math.max(0, d - 1);
                int y2 = Math.min(foliageHeight, foliageHeight - d * curvature - k);

                if (y2 <= y1) continue;

                this.generateColumn(world, placer, config, random, pos, dz, dx, y1, y2);
            }
        }
    }

    protected void generateColumn(TestableWorld world, BlockPlacer placer, TreeFeatureConfig config, Random random, BlockPos centerPos, int dx, int dz, int y1, int y2) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        mutable.set(centerPos, dz, y1, dx);
        for (int y = y1; y < y2; y++) {
            mutable.move(0, 1, 0);
            FoliagePlacer.placeFoliageBlock(world, placer, random, config, mutable);
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return trunkHeight + this.height.get(random);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
