package com.hugman.wild_explorer.init.world;

import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.init.WEBlockPack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class WEConfiguredSurfaceBuilders {
	private static <SC extends SurfaceConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> feature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, WildExplorer.MOD_DATA.id(name), feature);
	}

	public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> AMARANTH_DYLIUM = register("amaranth_dylium", SurfaceBuilder.DEFAULT.withConfig(new TernarySurfaceConfig(States.AMARANTH_DYLIUM, States.END_STONE, States.END_STONE)));

	public static final class States {
		protected static final BlockState END_STONE = Blocks.END_STONE.getDefaultState();
		protected static final BlockState AMARANTH_DYLIUM = WEBlockPack.AMARANTH_DYLIUM.getDefaultState();
	}
}
