package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.block.PromenadeBlocks;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public record CoiledVinesFeature(
        int spreadWidth,
        int spreadHeight,
        int maxLength,
        List<Direction> directions
) implements Feature {
    public static final MapCodec<CoiledVinesFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(CoiledVinesFeature::spreadWidth),
            ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(CoiledVinesFeature::spreadHeight),
            ExtraCodecs.POSITIVE_INT.fieldOf("max_length").forGetter(CoiledVinesFeature::maxLength),
            ExtraCodecs.nonEmptyList(Direction.CODEC.listOf()).optionalFieldOf("directions", List.of(Direction.values())).forGetter(CoiledVinesFeature::directions)
    ).apply(instance, CoiledVinesFeature::new));

    @Override
    public MapCodec<CoiledVinesFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel structureWorldAccess, ChunkGenerator chunkGenerator, RandomSource random, BlockPos blockPos) {
        if (isNotSuitable(structureWorldAccess, blockPos, Direction.UP)) {
            return false;
        } else {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

            for (int l = 0; l < this.spreadWidth * this.spreadWidth; l++) {
                // Pick a random direction
                var direction = this.directions.get(random.nextInt(this.directions.size()));
                // Pick a random position
                mutable.set(blockPos).move(
                        Mth.nextInt(random, -this.spreadWidth, this.spreadWidth),
                        Mth.nextInt(random, -this.spreadHeight, this.spreadHeight),
                        Mth.nextInt(random, -this.spreadWidth, this.spreadWidth)
                );

                if (findNonAirBlock(structureWorldAccess, mutable, direction) && !isNotSuitable(structureWorldAccess, mutable, direction)) {
                    int lenght = Mth.nextInt(random, 1, this.maxLength);
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
