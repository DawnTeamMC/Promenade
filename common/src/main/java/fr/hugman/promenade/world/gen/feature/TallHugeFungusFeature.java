package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.WeepingVinesFeature;

public class TallHugeFungusFeature extends Feature<HugeFungusConfiguration> {
    public TallHugeFungusFeature(Codec<HugeFungusConfiguration> codec) {
        super(codec);
    }

    private static boolean isReplaceable(WorldGenLevel world, BlockPos pos, HugeFungusConfiguration config, boolean checkConfig) {
        if (world.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::canBeReplaced)) {
            return true;
        } else {
            return checkConfig && config.replaceableBlocks.test(world, pos);
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
            WeepingVinesFeature.placeWeepingVinesColumn(worldAccess, random, mutable, i, 23, 25);
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<HugeFungusConfiguration> context) {
        RandomSource random = context.random();
        HugeFungusConfiguration config = context.config();
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        Block block = config.validBaseState.getBlock();
        BlockPos pos2 = null;
        if (config.planted) {
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
            if (!config.planted) {
                int j = world.getHeight();
                if (pos2.getY() + i + 1 >= j) {
                    return false;
                }
            }
            boolean bl = !config.planted && random.nextFloat() < 0.16F;
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
            this.generateStem(world, random, config, pos2, i, bl);
            this.generateHat(world, random, config, pos2, i, bl);
            return true;
        }
    }

    private void generateStem(WorldGenLevel world, RandomSource random, HugeFungusConfiguration config, BlockPos blockPos, int stemHeight, boolean thickStem) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState blockState = config.stemState;
        int i = thickStem ? 1 : 0;
        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                boolean bl = thickStem && Mth.abs(j) == i && Mth.abs(k) == i;
                for (int l = 0; l < stemHeight; ++l) {
                    mutable.setWithOffset(blockPos, j, l, k);
                    if (isReplaceable(world, mutable, config, true)) {
                        if (config.planted) {
                            if (!world.getBlockState(mutable.below()).isAir()) {
                                world.destroyBlock(mutable, true);
                            }
                            world.setBlock(mutable, blockState, 3);
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

    private void generateHat(WorldGenLevel world, RandomSource random, HugeFungusConfiguration config, BlockPos blockPos, int hatHeight, boolean thickStem) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        boolean bl = config.hatState.is(Blocks.NETHER_WART_BLOCK);
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
                    if (isReplaceable(world, mutable, config, false)) {
                        if (config.planted && !world.getBlockState(mutable.below()).isAir()) {
                            world.destroyBlock(mutable, true);
                        }
                        if (bl6) {
                            if (!bl4) {
                                this.tryGenerateVines(world, random, mutable, config.hatState, bl);
                            }
                        } else if (bl4) {
                            this.generateHatBlock(world, random, config, mutable, 0.1F, 0.2F, bl ? 0.1F : 0.0F);
                        } else if (bl5) {
                            this.generateHatBlock(world, random, config, mutable, 0.01F, 0.7F, bl ? 0.083F : 0.0F);
                        } else {
                            this.generateHatBlock(world, random, config, mutable, 5.0E-4F, 0.98F, bl ? 0.07F : 0.0F);
                        }
                    }
                }
            }
        }

    }

    private void generateHatBlock(LevelAccessor world, RandomSource random, HugeFungusConfiguration config, BlockPos.MutableBlockPos pos, float decorationChance, float generationChance, float vineChance) {
        if (random.nextFloat() < decorationChance) {
            this.setBlock(world, pos, config.decorState);
        } else if (random.nextFloat() < generationChance) {
            this.setBlock(world, pos, config.hatState);
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