package com.hugman.promenade.object.block.sapling_generator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public abstract class DynamicSaplingGenerator extends SaplingGenerator
{
	@Nullable
	@Override
	protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return null;
	}

	@Nullable
	protected abstract RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(ServerWorld world, Random var1, boolean var2);

	public boolean generate(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
		RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry = this.getTreeFeature(world, random, this.areFlowersNearby(world, pos));
		if(registryEntry == null) {
			return false;
		}
		ConfiguredFeature<?, ?> configuredFeature = registryEntry.value();
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NO_REDRAW);
		if(configuredFeature.generate(world, chunkGenerator, random, pos)) {
			return true;
		}
		world.setBlockState(pos, state, Block.NO_REDRAW);
		return false;
	}

	private boolean areFlowersNearby(WorldAccess world, BlockPos pos) {
		for(BlockPos blockPos : BlockPos.Mutable.iterate(pos.down().north(2).west(2), pos.up().south(2).east(2))) {
			if(!world.getBlockState(blockPos).isIn(BlockTags.FLOWERS)) continue;
			return true;
		}
		return false;
	}
}
