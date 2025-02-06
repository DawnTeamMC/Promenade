package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CoiledVinesFeature extends Feature<CoiledVinesFeatureConfig> {
    public CoiledVinesFeature(Codec<CoiledVinesFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<CoiledVinesFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        if (isNotSuitable(structureWorldAccess, blockPos)) {
            return false;
        } else {
            Random random = context.getRandom();
            CoiledVinesFeatureConfig config = context.getConfig();
            int i = config.spreadWidth();
            int j = config.spreadHeight();
            int k = config.maxHeight();
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int l = 0; l < i * i; l++) {
                mutable.set(blockPos).move(MathHelper.nextInt(random, -i, i), MathHelper.nextInt(random, -j, j), MathHelper.nextInt(random, -i, i));
                if (canGenerate(structureWorldAccess, mutable) && !isNotSuitable(structureWorldAccess, mutable)) {
                    int m = MathHelper.nextInt(random, 1, k);
                    if (random.nextInt(6) == 0) {
                        m *= 2;
                    }

                    if (random.nextInt(5) == 0) {
                        m = 1;
                    }
                    generateVineColumn(structureWorldAccess, random, mutable, m, 17, 25);
                }
            }

            return true;
        }
    }

    private static boolean canGenerate(WorldAccess world, BlockPos.Mutable pos) {
        do {
            pos.move(0, -1, 0);
            if (world.isOutOfHeightLimit(pos)) {
                return false;
            }
        } while (world.getBlockState(pos).isAir());

        pos.move(0, 1, 0);
        return true;
    }

    public static void generateVineColumn(WorldAccess world, Random random, BlockPos.Mutable pos, int maxLength, int minAge, int maxAge) {
        var direction = Direction.UP;
        for (int i = 1; i <= maxLength; i++) {
            if (world.isAir(pos)) {
                if (i == maxLength || !world.isAir(pos.up())) {
                    world.setBlockState(
                            pos,
                            PromenadeBlocks.COILED_VINES.getDefaultState()
                                    .with(FacingBlock.FACING, direction)
                                    .with(AbstractPlantStemBlock.AGE, Integer.valueOf(MathHelper.nextInt(random, minAge, maxAge))),
                            Block.NOTIFY_LISTENERS
                    );
                    break;
                }

                world.setBlockState(pos, PromenadeBlocks.COILED_VINES_PLANT.getDefaultState().with(FacingBlock.FACING, direction), Block.NOTIFY_LISTENERS);
            }

            pos.move(direction);
        }
    }

    private static boolean isNotSuitable(WorldAccess world, BlockPos pos) {
        if (!world.isAir(pos)) {
            return true;
        } else {
            BlockState blockState = world.getBlockState(pos.down());
            return !blockState.isOf(Blocks.NETHERRACK) &&
                    !blockState.isOf(PromenadeBlocks.DARK_AMARANTH_NYLIUM) &&
                    !blockState.isOf(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        }
    }
}
