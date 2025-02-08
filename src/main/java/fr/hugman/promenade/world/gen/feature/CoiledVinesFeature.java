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
        if (isNotSuitable(structureWorldAccess, blockPos, Direction.UP)) {
            return false;
        } else {
            Random random = context.getRandom();
            CoiledVinesFeatureConfig config = context.getConfig();
            int spreadWidth = config.spreadWidth();
            int spreadHeight = config.spreadHeight();
            int maxLength = config.maxLength();
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int l = 0; l < spreadWidth * spreadWidth; l++) {
                // Pick a random direction
                var direction = config.directions().get(random.nextInt(config.directions().size()));
                // Pick a random position
                mutable.set(blockPos).move(
                        MathHelper.nextInt(random, -spreadWidth, spreadWidth),
                        MathHelper.nextInt(random, -spreadHeight, spreadHeight),
                        MathHelper.nextInt(random, -spreadWidth, spreadWidth)
                );

                if (findNonAirBlock(structureWorldAccess, mutable, direction) && !isNotSuitable(structureWorldAccess, mutable, direction)) {
                    int lenght = MathHelper.nextInt(random, 1, maxLength);
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

    private static boolean findNonAirBlock(WorldAccess world, BlockPos.Mutable pos, Direction direction) {
        int max = 16;
        do {
            pos.move(direction.getOpposite());
            if (world.isOutOfHeightLimit(pos) || max-- <= 0) {
                return false;
            }
        } while (world.getBlockState(pos).isAir());

        pos.move(direction);
        return true;
    }

    public static void generateVineColumn(
            WorldAccess world,
            Random random,
            BlockPos.Mutable pos,
            int maxLength,
            int minAge,
            int maxAge,
            Direction direction
    ) {
        for (int i = 1; i <= maxLength; i++) {
            if (world.isAir(pos)) {
                if (i == maxLength || !world.isAir(pos.offset(direction))) {
                    world.setBlockState(
                            pos,
                            PromenadeBlocks.COILED_VINES.getDefaultState()
                                    .with(FacingBlock.FACING, direction)
                                    .with(AbstractPlantStemBlock.AGE, MathHelper.nextInt(random, minAge, maxAge)),
                            Block.NOTIFY_LISTENERS
                    );
                    break;
                }

                world.setBlockState(pos, PromenadeBlocks.COILED_VINES_PLANT.getDefaultState().with(FacingBlock.FACING, direction), Block.NOTIFY_LISTENERS);
            }

            pos.move(direction);
        }
    }

    private static boolean isNotSuitable(WorldAccess world, BlockPos pos, Direction direction) {
        if (!world.isAir(pos)) {
            return true;
        } else {
            BlockState blockState = world.getBlockState(pos.offset(direction.getOpposite()));
            return !blockState.isOf(Blocks.NETHERRACK) &&
                    !blockState.isOf(PromenadeBlocks.DARK_AMARANTH_NYLIUM) &&
                    !blockState.isOf(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        }
    }
}
