package com.hugman.wild_explorer.object.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.feature.WeepingVinesFeature;

import java.util.Random;

public class TallHugeFungusFeature extends Feature<HugeFungusFeatureConfig> {
	public TallHugeFungusFeature(Codec<HugeFungusFeatureConfig> codec) {
		super(codec);
	}

	private static boolean isReplaceable(WorldAccess worldAccess, BlockPos blockPos, boolean canReplaceAllPlants) {
		return worldAccess.testBlockState(blockPos, (blockState) -> {
			Material material = blockState.getMaterial();
			return blockState.isAir() || blockState.isOf(Blocks.WATER) || blockState.isOf(Blocks.LAVA) || material == Material.REPLACEABLE_PLANT || canReplaceAllPlants && material == Material.PLANT;
		});
	}

	private static BlockPos.Mutable getStartPos(WorldAccess world, BlockPos origin, Block block) {
		BlockPos.Mutable mutable = origin.mutableCopy();
		for(int i = origin.getY(); i >= 1; --i) {
			mutable.setY(i);
			Block block2 = world.getBlockState(mutable.down()).getBlock();
			if(block2 == block) {
				return mutable;
			}
		}
		return null;
	}

	private static void generateVines(BlockPos blockPos, WorldAccess worldAccess, Random random) {
		BlockPos.Mutable mutable = blockPos.mutableCopy().move(Direction.DOWN);
		if(worldAccess.isAir(mutable)) {
			int i = MathHelper.nextInt(random, 1, 5);
			if(random.nextInt(7) == 0) {
				i *= 2;
			}
			WeepingVinesFeature.generateVineColumn(worldAccess, random, mutable, i, 23, 25);
		}
	}

	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, HugeFungusFeatureConfig hugeFungusFeatureConfig) {
		Block block = hugeFungusFeatureConfig.validBaseBlock.getBlock();
		BlockPos pos2 = null;
		if(hugeFungusFeatureConfig.planted) {
			Block block2 = world.getBlockState(pos.down()).getBlock();
			if(block2 == block) {
				pos2 = pos;
			}
		}
		else {
			pos2 = getStartPos(world, pos, block);
		}
		if(pos2 == null) {
			return false;
		}
		else {
			int i = MathHelper.nextInt(random, 8, 16) * 2;
			if(!hugeFungusFeatureConfig.planted) {
				int j = world.getDimensionHeight();
				if(pos2.getY() + i + 1 >= j) {
					return false;
				}
			}
			boolean bl = !hugeFungusFeatureConfig.planted && random.nextFloat() < 0.16F;
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
			this.generateStem(world, random, hugeFungusFeatureConfig, pos2, i, bl);
			this.generateHat(world, random, hugeFungusFeatureConfig, pos2, i, bl);
			return true;
		}
	}

	private void generateStem(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos blockPos, int stemHeight, boolean thickStem) {
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		BlockState blockState = config.stemState;
		int i = thickStem ? 1 : 0;
		for(int j = -i; j <= i; ++j) {
			for(int k = -i; k <= i; ++k) {
				boolean bl = thickStem && MathHelper.abs(j) == i && MathHelper.abs(k) == i;
				for(int l = 0; l < stemHeight; ++l) {
					mutable.set(blockPos, j, l, k);
					if(isReplaceable(world, mutable, true)) {
						if(config.planted) {
							if(!world.getBlockState(mutable.down()).isAir()) {
								world.breakBlock(mutable, true);
							}
							world.setBlockState(mutable, blockState, 3);
						}
						else if(bl) {
							if(random.nextFloat() < 0.1F) {
								this.setBlockState(world, mutable, blockState);
							}
						}
						else {
							this.setBlockState(world, mutable, blockState);
						}
					}
				}
			}
		}

	}

	private void generateHat(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos blockPos, int hatHeight, boolean thickStem) {
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		boolean bl = config.hatState.isOf(Blocks.NETHER_WART_BLOCK);
		int i = Math.min(random.nextInt(1 + hatHeight / 3) + 5, hatHeight);
		int j = hatHeight - i;
		for(int k = j; k <= hatHeight; ++k) {
			int l = k < hatHeight - random.nextInt(3) ? 2 : 1;
			if(i > 8 && k < j + 4) {
				l = 3;
			}
			if(thickStem) {
				++l;
			}
			for(int m = -l; m <= l; ++m) {
				for(int n = -l; n <= l; ++n) {
					boolean bl2 = m == -l || m == l;
					boolean bl3 = n == -l || n == l;
					boolean bl4 = !bl2 && !bl3 && k != hatHeight;
					boolean bl5 = bl2 && bl3;
					boolean bl6 = k < j + 3;
					mutable.set(blockPos, m, k, n);
					if(isReplaceable(world, mutable, false)) {
						if(config.planted && !world.getBlockState(mutable.down()).isAir()) {
							world.breakBlock(mutable, true);
						}
						if(bl6) {
							if(!bl4) {
								this.tryGenerateVines(world, random, mutable, config.hatState, bl);
							}
						}
						else if(bl4) {
							this.generateHatBlock(world, random, config, mutable, 0.1F, 0.2F, bl ? 0.1F : 0.0F);
						}
						else if(bl5) {
							this.generateHatBlock(world, random, config, mutable, 0.01F, 0.7F, bl ? 0.083F : 0.0F);
						}
						else {
							this.generateHatBlock(world, random, config, mutable, 5.0E-4F, 0.98F, bl ? 0.07F : 0.0F);
						}
					}
				}
			}
		}

	}

	private void generateHatBlock(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos.Mutable pos, float decorationChance, float generationChance, float vineChance) {
		if(random.nextFloat() < decorationChance) {
			this.setBlockState(world, pos, config.decorationState);
		}
		else if(random.nextFloat() < generationChance) {
			this.setBlockState(world, pos, config.hatState);
			if(random.nextFloat() < vineChance) {
				generateVines(pos, world, random);
			}
		}

	}

	private void tryGenerateVines(WorldAccess world, Random random, BlockPos origin, BlockState state, boolean bl) {
		BlockPos.Mutable mutable = origin.mutableCopy();
		if(world.getBlockState(mutable.down()).isOf(state.getBlock())) {
			this.setBlockState(world, mutable, state);
		}
		else if((double) random.nextFloat() < 0.15D) {
			this.setBlockState(world, origin, state);
			if(bl && random.nextInt(11) == 0) {
				generateVines(mutable, world, random);
			}
		}

	}
}
