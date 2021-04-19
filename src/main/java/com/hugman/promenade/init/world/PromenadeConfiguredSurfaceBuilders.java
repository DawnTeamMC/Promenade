package com.hugman.promenade.init.world;

import com.hugman.dawn.api.creator.ConfiguredSurfaceBuilderCreator;
import com.hugman.promenade.init.PromenadeBlocks;
import com.hugman.promenade.init.PromenadePack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class PromenadeConfiguredSurfaceBuilders extends PromenadePack {
	public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> AMARANTH_DYLIUM = register(new ConfiguredSurfaceBuilderCreator.Builder<>("amaranth_dylium", SurfaceBuilder.DEFAULT.withConfig(new TernarySurfaceConfig(States.AMARANTH_DYLIUM, States.END_STONE, States.END_STONE))));

	public static void init() {
	}

	public static final class States {
		protected static final BlockState END_STONE = Blocks.END_STONE.getDefaultState();
		protected static final BlockState AMARANTH_DYLIUM = PromenadeBlocks.BLACK_DYLIUM.getDefaultState();
	}
}
