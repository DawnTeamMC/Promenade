package fr.hugman.promenade.gen.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.content.CommonContent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CypressFoliagePlacer extends FoliagePlacer {
    public static final Codec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            CypressFoliagePlacer.fillFoliagePlacerFields(instance)
                    .apply(instance, CypressFoliagePlacer::new));

    public CypressFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return CommonContent.CYPRESS_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        var pos = treeNode.getCenter().down(trunkHeight - offset);

        for (int y = 0; y < foliageHeight; y++) {
            //TODO: sin is not the best way to do this
            var r = MathHelper.sin(MathHelper.PI * y / foliageHeight) * radius;
            this.generateCircle(world, placer, random, config, pos, r, y, treeNode.isGiantTrunk());
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        //TODO: add to config
        return trunkHeight + 3;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }

    protected void generateCircle(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos centerPos, float radius, int y, boolean giantTrunk) {
        int i = giantTrunk ? 1 : 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        float radiusRemainder = radius % 1.0F;
        int radiusInt = (int) (radius) + 1;
        for (int x = -radiusInt; x <= radiusInt + i; ++x) {
            for (int z = -radiusInt; z <= radiusInt + i; ++z) {
                var r = MathHelper.abs(x) + MathHelper.abs(z);
                if(r > radiusInt) continue;
                if(r == radiusInt && random.nextFloat() > radiusRemainder) continue;
                mutable.set(centerPos, x, y, z);
                FoliagePlacer.placeFoliageBlock(world, placer, random, config, mutable);
            }
        }
    }
}
