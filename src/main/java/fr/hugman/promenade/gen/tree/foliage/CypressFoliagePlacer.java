package fr.hugman.promenade.gen.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.content.CommonContent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.IntProviderType;
import net.minecraft.util.math.intprovider.UniformIntProvider;
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
        var pos = treeNode.getCenter().down(trunkHeight - offset + 1);

        //TODO: support giant trunks
        //TODO: more configurability?
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {

                int d = Math.abs(dx) + Math.abs(dz);

                // the further we are from the trunk, the lower the height
                int y1 = Math.max(0, d - 1);
                int y2 = Math.min(foliageHeight, foliageHeight - d * 3);

                if(y2 <= y1) continue;

                this.generateColumn(world, placer, config, random, pos, dz, dx, y1, y2);
            }
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return trunkHeight + this.bonusHeight.get(random);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (dx == 0 && dz == 0) return true;
        var d = dx + dz;
        if (d > radius) return true;
        return false;
    }

    protected void generateColumn(TestableWorld world, BlockPlacer placer, TreeFeatureConfig config, Random random, BlockPos centerPos, int dx, int dz, int y1, int y2) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        mutable.set(centerPos, dz, y1, dx);
        for (int y = y1; y < y2; y++) {
            mutable.move(0, 1, 0);
            FoliagePlacer.placeFoliageBlock(world, placer, random, config, mutable);
        }
    }
}
