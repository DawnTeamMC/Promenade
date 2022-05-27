package com.hugman.promenade.object.block;

import com.hugman.promenade.init.AmaranthBundle;
import com.hugman.promenade.util.WorldGenUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NyliumBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class DyliumBlock extends NyliumBlock
{
	public static final RegistryKey<ConfiguredFeature<?, ?>> BONEMEAL_VEGETATION = WorldGenUtil.configuredFeatureKey("dark_amaranth_forest_vegetation/bonemeal");

	public DyliumBlock(Settings settings) {
		super(settings);
	}

	private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
		BlockPos blockPos = pos.up();
		BlockState blockState = world.getBlockState(blockPos);
		int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
		return i < world.getMaxLightLevel();
	}

	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if(!stayAlive(state, world, pos)) {
			world.setBlockState(pos, Blocks.END_STONE.getDefaultState());
		}
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		ConfiguredFeature<?, ?> configuredFeature = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY).get(BONEMEAL_VEGETATION);
		return configuredFeature != null && super.canGrow(world, random, pos, state);
	}

	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		BlockState blockState = world.getBlockState(pos);
		BlockPos blockPos = pos.up();
		ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
		if(blockState.isOf(AmaranthBundle.BLACK_DYLIUM)) {
			ConfiguredFeature<?, ?> configuredFeature = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY).get(BONEMEAL_VEGETATION);
			if(configuredFeature != null) configuredFeature.generate(world, chunkGenerator, random, blockPos);
		}
	}
}
