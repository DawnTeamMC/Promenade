package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;

public record TallHugeFungusFeature(
        BlockState validBaseState,
        BlockState stemState,
        BlockState hatState,
        BlockState decorState,
        BlockPredicate replaceableBlocks,
        boolean planted
) implements Feature {
    public static final MapCodec<TallHugeFungusFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockState.CODEC.fieldOf("valid_base_block").forGetter(TallHugeFungusFeature::validBaseState),
            BlockState.CODEC.fieldOf("stem_state").forGetter(TallHugeFungusFeature::stemState),
            BlockState.CODEC.fieldOf("hat_state").forGetter(TallHugeFungusFeature::hatState),
            BlockState.CODEC.fieldOf("decor_state").forGetter(TallHugeFungusFeature::decorState),
            BlockPredicate.CODEC.fieldOf("replaceable_blocks").forGetter(TallHugeFungusFeature::replaceableBlocks),
            Codec.BOOL.optionalFieldOf("planted", false).forGetter(TallHugeFungusFeature::planted)
    ).apply(instance, TallHugeFungusFeature::new));

    @Override
    public MapCodec<TallHugeFungusFeature> codec() {
        return CODEC;
    }

    private boolean isReplaceable(WorldGenLevel world, BlockPos pos, boolean checkConfig) {
        if (world.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::canBeReplaced)) {
            return true;
        } else {
            return checkConfig && this.replaceableBlocks.test(world, pos);
        }
    }

    private static BlockPos.MutableBlockPos getStartPos(LevelAccessor world, BlockPos origin, Block block) {
        BlockPos.MutableBlockPos mutable = origin.mutable();
        for (int i = origin.getY(); i >= 1; --i) {
            mutable.setY(i);
            Block block2 = world.getBlockState(mutable.below()).getBlock();
            if (block2 == block) {
                return mutable;
            }
        }
        return null;
    }

    private static void generateVines(BlockPos blockPos, LevelAccessor worldAccess, RandomSource random) {
        BlockPos.MutableBlockPos mutable = blockPos.mutable().move(Direction.DOWN);
        if (worldAccess.isEmptyBlock(mutable)) {
            int i = Mth.nextInt(random, 1, 5);
            if (random.nextInt(7) == 0) {
                i *= 2;
            }
            placeWeepingVinesColumn(worldAccess, random, mutable, i, 23, 25);
        }
    }

    private static void placeWeepingVinesColumn(LevelAccessor world, RandomSource random, BlockPos origin, int totalHeight, int minAge, int maxAge) {
        BlockPos.MutableBlockPos placePos = origin.mutable();

        for (int height = 0; height <= totalHeight; height++) {
            if (world.isEmptyBlock(placePos)) {
                if (height == totalHeight || !world.isEmptyBlock(placePos.below())) {
                    world.setBlock(placePos, Blocks.WEEPING_VINES.defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, minAge, maxAge)), Block.UPDATE_CLIENTS);
                    break;
                }

                world.setBlock(placePos, Blocks.WEEPING_VINES_PLANT.defaultBlockState(), Block.UPDATE_CLIENTS);
            }

            placePos.move(Direction.DOWN);
        }
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource random, BlockPos pos) {
        Block block = this.validBaseState.getBlock();
        BlockPos pos2 = null;
        if (this.planted) {
            Block block2 = world.getBlockState(pos.below()).getBlock();
            if (block2 == block) {
                pos2 = pos;
            }
        } else {
            pos2 = getStartPos(world, pos, block);
        }
        if (pos2 == null) {
            return false;
        } else {
            int i = Mth.nextInt(random, 8, 16) * 2;
            if (!this.planted) {
                int j = world.getHeight();
                if (pos2.getY() + i + 1 >= j) {
                    return false;
                }
            }
            boolean bl = !this.planted && random.nextFloat() < 0.16F;
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_INVISIBLE);
            this.generateStem(world, random, pos2, i, bl);
            this.generateHat(world, random, pos2, i, bl);
            return true;
        }
    }

    private void generateStem(WorldGenLevel world, RandomSource random, BlockPos blockPos, int stemHeight, boolean thickStem) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState blockState = this.stemState;
        int i = thickStem ? 1 : 0;
        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                boolean bl = thickStem && Mth.abs(j) == i && Mth.abs(k) == i;
                for (int l = 0; l < stemHeight; ++l) {
                    mutable.setWithOffset(blockPos, j, l, k);
                    if (this.isReplaceable(world, mutable, true)) {
                        if (this.planted) {
                            if (!world.getBlockState(mutable.below()).isAir()) {
                                world.destroyBlock(mutable, true);
                            }
                            world.setBlockAndUpdate(mutable, blockState);
                        } else if (bl) {
                            if (random.nextFloat() < 0.1F) {
                                this.setBlock(world, mutable, blockState);
                            }
                        } else {
                            this.setBlock(world, mutable, blockState);
                        }
                    }
                }
            }
        }

    }

    private void generateHat(WorldGenLevel world, RandomSource random, BlockPos blockPos, int hatHeight, boolean thickStem) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        boolean bl = this.hatState.is(Blocks.NETHER_WART_BLOCK);
        int i = Math.min(random.nextInt(1 + hatHeight / 3) + 5, hatHeight);
        int j = hatHeight - i;
        for (int k = j; k <= hatHeight; ++k) {
            int l = k < hatHeight - random.nextInt(3) ? 2 : 1;
            if (i > 8 && k < j + 4) {
                l = 3;
            }
            if (thickStem) {
                ++l;
            }
            for (int m = -l; m <= l; ++m) {
                for (int n = -l; n <= l; ++n) {
                    boolean bl2 = m == -l || m == l;
                    boolean bl3 = n == -l || n == l;
                    boolean bl4 = !bl2 && !bl3 && k != hatHeight;
                    boolean bl5 = bl2 && bl3;
                    boolean bl6 = k < j + 3;
                    mutable.setWithOffset(blockPos, m, k, n);
                    if (this.isReplaceable(world, mutable, false)) {
                        if (this.planted && !world.getBlockState(mutable.below()).isAir()) {
                            world.destroyBlock(mutable, true);
                        }
                        if (bl6) {
                            if (!bl4) {
                                this.tryGenerateVines(world, random, mutable, this.hatState, bl);
                            }
                        } else if (bl4) {
                            this.generateHatBlock(world, random, mutable, 0.1F, 0.2F, bl ? 0.1F : 0.0F);
                        } else if (bl5) {
                            this.generateHatBlock(world, random, mutable, 0.01F, 0.7F, bl ? 0.083F : 0.0F);
                        } else {
                            this.generateHatBlock(world, random, mutable, 5.0E-4F, 0.98F, bl ? 0.07F : 0.0F);
                        }
                    }
                }
            }
        }

    }

    private void generateHatBlock(LevelAccessor world, RandomSource random, BlockPos.MutableBlockPos pos, float decorationChance, float generationChance, float vineChance) {
        if (random.nextFloat() < decorationChance) {
            this.setBlock(world, pos, this.decorState);
        } else if (random.nextFloat() < generationChance) {
            this.setBlock(world, pos, this.hatState);
            if (random.nextFloat() < vineChance) {
                generateVines(pos, world, random);
            }
        }

    }

    private void tryGenerateVines(LevelAccessor world, RandomSource random, BlockPos origin, BlockState state, boolean bl) {
        BlockPos.MutableBlockPos mutable = origin.mutable();
        if (world.getBlockState(mutable.below()).is(state.getBlock())) {
            this.setBlock(world, mutable, state);
        } else if ((double) random.nextFloat() < 0.15D) {
            this.setBlock(world, origin, state);
            if (bl && random.nextInt(11) == 0) {
                generateVines(mutable, world, random);
            }
        }

    }
}
