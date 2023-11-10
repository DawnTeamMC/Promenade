package fr.hugman.promenade.gen.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.content.CommonContent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class OvalFoliagePlacer extends FoliagePlacer {
    public static final Codec<OvalFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            OvalFoliagePlacer.fillFoliagePlacerFields(instance)
                    .apply(instance, OvalFoliagePlacer::new));

    public OvalFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return CommonContent.OVAL_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        var pos = treeNode.getCenter().mutableCopy();
        pos.move(0, -trunkHeight + offset, 0);
        for (int y = 0; y < foliageHeight; y++) {
            var r = MathHelper.ceil(MathHelper.sin(MathHelper.PI * y / foliageHeight) * radius);
            this.generateSquare(world, placer, random, config, pos, r, y, treeNode.isGiantTrunk());
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return trunkHeight + 3;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
