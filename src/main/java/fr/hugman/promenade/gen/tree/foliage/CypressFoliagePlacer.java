package fr.hugman.promenade.gen.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.content.CommonContent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CypressFoliagePlacer extends FoliagePlacer {
    public static final Codec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            CypressFoliagePlacer.fillFoliagePlacerFields(instance).and(
                    IntProvider.createValidatingCodec(-16, 16).optionalFieldOf("bonus_height", ConstantIntProvider.create(0)).forGetter(placer -> placer.bonusHeight)
            ).apply(instance, CypressFoliagePlacer::new));

    protected final IntProvider bonusHeight;

    public CypressFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider bonusHeight) {
        super(radius, offset);
        this.bonusHeight = bonusHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return CommonContent.CYPRESS_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        var pos = treeNode.getCenter().down(trunkHeight - offset);

        var h = foliageHeight;
        var b = radius / 2;
        for (int y = 0; y < foliageHeight; y++) {
            var a = 10.0f * y / h;
            float r = (float) ((-0.003 * Math.pow(a, 4) +
                    0.0725 * Math.pow(a, 3) +
                    -0.64 * Math.pow(a, 2) +
                    2.2 * Math.pow(a, 1)
                    - 0.555) * b);
            this.generateCircle(world, placer, random, config, pos, r, y, treeNode.isGiantTrunk());
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return trunkHeight + this.bonusHeight.get(random);
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
                if (r > radiusInt) continue;
                if (r == radiusInt && 0.5 < radiusRemainder) continue;
                mutable.set(centerPos, x, y, z);
                FoliagePlacer.placeFoliageBlock(world, placer, random, config, mutable);
            }
        }
    }
}
