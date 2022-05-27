package com.hugman.promenade.object.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TwistingVinesFeature;
import net.minecraft.world.gen.feature.WeepingVinesFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class EHugeMushroomFeature extends Feature<EHugeMushroomFeatureConfig> {
	public EHugeMushroomFeature(Codec<EHugeMushroomFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(FeatureContext<EHugeMushroomFeatureConfig> context) {
		Random random = context.getRandom();
		EHugeMushroomFeatureConfig config = context.getConfig();
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		int stemHeight = getStemHeight(random, config);
		int hatSize = getHatSize(random, config);
		BlockPos origin = getOrigin(world, pos, stemHeight, config);
		if(origin == null) {
			return false;
		}
		else {
			if(origin.getY() + stemHeight + 1 >= world.getHeight()) {
				return false;
			}
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
			this.generateStem(world, random, origin, stemHeight, config);
			if(config.flatHat) {
				this.generateFlatHat(world, random, origin, stemHeight, hatSize, config);
			}
			else {
				this.generateBubbleHat(world, random, origin, stemHeight, hatSize, config);
			}
			return true;
		}
	}

	private static void generateVines(BlockPos pos, WorldAccess worldAccess, Random random, EHugeMushroomFeatureConfig config) {
		BlockPos.Mutable mutable = pos.mutableCopy().move(config.upsideDown ? Direction.UP : Direction.DOWN);
		if(worldAccess.isAir(mutable)) {
			int i = MathHelper.nextInt(random, 1, 5);
			if(random.nextInt(7) == 0) {
				i *= 2;
			}
			if(config.upsideDown) {
				TwistingVinesFeature.generateVineColumn(worldAccess, random, mutable, i, 23, 25);
			}
			else {
				WeepingVinesFeature.generateVineColumn(worldAccess, random, mutable, i, 23, 25);
			}
		}
	}

	private static BlockPos.Mutable getOrigin(WorldAccess world, BlockPos pos, int height, EHugeMushroomFeatureConfig config) {
		BlockPos.Mutable mutable = pos.mutableCopy();
		for(int i = pos.getY(); i >= 1; --i) {
			mutable.setY(i);
			if(isValidGround(world, mutable.offset(config.upsideDown ? Direction.UP : Direction.DOWN))) {
				BlockPos.Mutable mutable2 = mutable.mutableCopy();
				boolean allReplaceable = true;
				for(int j = 0; j <= height + 1; ++j) {
					mutable2.move(config.upsideDown ? Direction.DOWN : Direction.UP, j);
					if(!isReplaceable(world, mutable2, false, true)) {
						allReplaceable = false;
						break;
					}
				}
				if(allReplaceable) {
					return mutable;
				}
			}
		}
		return null;
	}

	private static boolean isReplaceable(WorldAccess worldAccess, BlockPos blockPos, boolean canReplaceFluid, boolean canReplaceAllPlants) {
		return worldAccess.testBlockState(blockPos, (blockState) -> {
			Material material = blockState.getMaterial();
			return blockState.isAir() || canReplaceFluid && blockState.isOf(Blocks.WATER) || canReplaceFluid && blockState.isOf(Blocks.LAVA) || material == Material.REPLACEABLE_PLANT || canReplaceAllPlants && material == Material.PLANT;
		});
	}

	public static boolean isValidGround(WorldAccess worldAccess, BlockPos blockPos) {
		return worldAccess.testBlockState(blockPos, (blockState) -> blockState.isOf(Blocks.NETHERRACK) || blockState.isOf(Blocks.NETHER_QUARTZ_ORE) || blockState.isOf(Blocks.NETHER_GOLD_ORE));
	}

	private void generateStem(WorldAccess world, Random random, BlockPos origin, int stemHeight, EHugeMushroomFeatureConfig config) {
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		BlockState blockState = config.stemState;
		if(blockState.contains(MushroomBlock.UP) && blockState.contains(MushroomBlock.DOWN)) blockState = blockState.with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);
		for(int i = 0; i < stemHeight; ++i) {
			mutable.set(origin).move(config.upsideDown ? Direction.DOWN : Direction.UP, i);
			if(isReplaceable(world, mutable, true, true)) {
				this.setBlockState(world, mutable, blockState);
			}
		}
	}

	protected void generateBubbleHat(WorldAccess world, Random random, BlockPos origin, int stemHeight, int hatSize, EHugeMushroomFeatureConfig config) {
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for(int i = stemHeight - hatSize; i <= stemHeight; ++i) {
			int j = i < stemHeight ? hatSize : hatSize - 1;
			int k = hatSize - 2;
			for(int l = -j; l <= j; ++l) {
				for(int m = -j; m <= j; ++m) {
					boolean bl = l == -j;
					boolean bl2 = l == j;
					boolean bl3 = m == -j;
					boolean bl4 = m == j;
					boolean bl5 = bl || bl2;
					boolean bl6 = bl3 || bl4;
					if(i >= stemHeight || bl5 != bl6) {
						mutable.set(origin, l, config.upsideDown ? -i : i, m);
						if(!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
							boolean bl7 = i >= stemHeight - 1;
							boolean bl8 = i <= stemHeight - hatSize;
							boolean up = config.upsideDown ? bl8 : bl7;
							boolean down = config.upsideDown ? bl7 : bl8;
							BlockState state = config.hatState.with(MushroomBlock.UP, up).with(MushroomBlock.DOWN, down).with(MushroomBlock.WEST, l < -k).with(MushroomBlock.EAST, l > k).with(MushroomBlock.NORTH, m < -k).with(MushroomBlock.SOUTH, m > k);
							this.generateHatBlock(world, random, mutable, state, config);
						}
					}
				}
			}
		}
	}

	protected void generateFlatHat(WorldAccess world, Random random, BlockPos origin, int stemHeight, int hatSize, EHugeMushroomFeatureConfig config) {
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for(int j = -hatSize; j <= hatSize; ++j) {
			for(int k = -hatSize; k <= hatSize; ++k) {
				boolean bl = j == -hatSize;
				boolean bl2 = j == hatSize;
				boolean bl3 = k == -hatSize;
				boolean bl4 = k == hatSize;
				boolean bl5 = bl || bl2;
				boolean bl6 = bl3 || bl4;
				if(!bl5 || !bl6) {
					mutable.set(origin, j, config.upsideDown ? -stemHeight : stemHeight, k);
					if(!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
						boolean bl7 = bl || bl6 && j == 1 - hatSize;
						boolean bl8 = bl2 || bl6 && j == hatSize - 1;
						boolean bl9 = bl3 || bl5 && k == 1 - hatSize;
						boolean bl10 = bl4 || bl5 && k == hatSize - 1;
						BlockState state = config.hatState.with(MushroomBlock.UP, !config.upsideDown).with(MushroomBlock.DOWN, config.upsideDown).with(MushroomBlock.WEST, bl7).with(MushroomBlock.EAST, bl8).with(MushroomBlock.NORTH, bl9).with(MushroomBlock.SOUTH, bl10);
						this.generateHatBlock(world, random, mutable, state, config);
					}
				}
			}
		}
	}

	private void generateHatBlock(WorldAccess world, Random random, BlockPos pos, BlockState hatState, EHugeMushroomFeatureConfig config) {
		if(random.nextFloat() < config.decorationChance) {
			this.setBlockState(world, pos, config.decorationState);
		}
		else {
			this.setBlockState(world, pos, hatState);
			if(random.nextFloat() < config.vineChance) {
				generateVines(pos, world, random, config);
			}
		}
	}

	protected int getStemHeight(Random random, EHugeMushroomFeatureConfig config) {
		int i = config.stemBaseHeight + random.nextInt(config.stemRandomHeight);
		if(random.nextInt(12) == 0) {
			i *= 2;
		}
		return i;
	}

	protected int getHatSize(Random random, EHugeMushroomFeatureConfig config) {
		if(config.hatRandomSize == 0) {
			return config.hatBaseSize;
		}
		else {
			return config.hatBaseSize + random.nextInt(config.hatRandomSize);
		}
	}
}
