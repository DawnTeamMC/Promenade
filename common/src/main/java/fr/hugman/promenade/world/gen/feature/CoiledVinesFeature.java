package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class CoiledVinesFeature extends Feature<CoiledVinesFeatureConfig> {
    public CoiledVinesFeature(Codec<CoiledVinesFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CoiledVinesFeatureConfig> context) {
        WorldGenLevel structureWorldAccess = context.level();
        BlockPos blockPos = context.origin();
        if (isNotSuitable(structureWorldAccess, blockPos, Direction.UP)) {
            return false;
        } else {
            RandomSource random = context.random();
            CoiledVinesFeatureConfig config = context.config();
            int spreadWidth = config.spreadWidth();
            int spreadHeight = config.spreadHeight();
            int maxLength = config.maxLength();
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

            for (int l = 0; l < spreadWidth * spreadWidth; l++) {
                // Pick a random direction
                var direction = config.directions().get(random.nextInt(config.directions().size()));
                // Pick a random position
                mutable.set(blockPos).move(
                        Mth.nextInt(random, -spreadWidth, spreadWidth),
                        Mth.nextInt(random, -spreadHeight, spreadHeight),
                        Mth.nextInt(random, -spreadWidth, spreadWidth)
                );

                if (findNonAirBlock(structureWorldAccess, mutable, direction) && !isNotSuitable(structureWorldAccess, mutable, direction)) {
                    int lenght = Mth.nextInt(random, 1, maxLength);
                    if (random.nextInt(6) == 0) {
                        lenght *= 2;
                    }
                    if (random.nextInt(5) == 0) {
                        lenght = 1;
                    }
                    generateVineColumn(structureWorldAccess, random, mutable, lenght, 17, 25, direction);
                }
            }

            return true;
        }
    }

    private static boolean findNonAirBlock(LevelAccessor world, BlockPos.MutableBlockPos pos, Direction direction) {
        int max = 16;
        do {
            pos.move(direction.getOpposite());
            if (world.isOutsideBuildHeight(pos) || max-- <= 0) {
                return false;
            }
        } while (world.getBlockState(pos).isAir());

        pos.move(direction);
        return true;
    }

    public static void generateVineColumn(
            LevelAccessor world,
            RandomSource random,
            BlockPos.MutableBlockPos pos,
            int maxLength,
            int minAge,
            int maxAge,
            Direction direction
    ) {
        for (int i = 1; i <= maxLength; i++) {
            if (world.isEmptyBlock(pos)) {
                if (i == maxLength || !world.isEmptyBlock(pos.relative(direction))) {
                    world.setBlock(
                            pos,
                            PromenadeBlocks.COILED_VINES.defaultBlockState()
                                    .setValue(DirectionalBlock.FACING, direction)
                                    .setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, minAge, maxAge)),
                            Block.UPDATE_CLIENTS
                    );
                    break;
                }

                world.setBlock(pos, PromenadeBlocks.COILED_VINES_PLANT.defaultBlockState().setValue(DirectionalBlock.FACING, direction), Block.UPDATE_CLIENTS);
            }

            pos.move(direction);
        }
    }

    private static boolean isNotSuitable(LevelAccessor world, BlockPos pos, Direction direction) {
        if (!world.isEmptyBlock(pos)) {
            return true;
        } else {
            BlockState blockState = world.getBlockState(pos.relative(direction.getOpposite()));
            return !blockState.is(Blocks.NETHERRACK) &&
                    !blockState.is(PromenadeBlocks.DARK_AMARANTH_NYLIUM) &&
                    !blockState.is(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        }
    }
}
