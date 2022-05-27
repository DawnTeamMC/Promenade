package com.hugman.promenade.object.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class DMushroomPlantBlock extends MushroomPlantBlock
{
	private final RegistryKey<ConfiguredFeature<?, ?>> featureKey;

	public DMushroomPlantBlock(Settings builder, RegistryKey<ConfiguredFeature<?, ?>> featureKey) {
		super(builder, null);
		this.featureKey = featureKey;
	}

	@Override
	public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		world.removeBlock(pos, false);
		ConfiguredFeature<?, ?> feature = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY).get(this.featureKey);
		if(feature != null && feature.generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
			return true;
		}
		world.setBlockState(pos, state, Block.NOTIFY_ALL);
		return false;
	}
}
