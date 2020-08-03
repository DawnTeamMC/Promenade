package com.hugman.wild_explorer.init.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hugman.dawn.init.DawnBlockPack;
import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.init.WEBlockPack;
import com.hugman.wild_explorer.object.world.gen.feature.HugeNetherMushroomFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.OptionalInt;
import java.util.Set;

public class WEConfiguredSurfaceBuilders {
	private static <SC extends SurfaceConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> feature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, WildExplorer.MOD_DATA.id(name), feature);
	}

	public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> AMARANTH_DYLIUM = register("amaranth_dylium", SurfaceBuilder.DEFAULT.method_30478(new TernarySurfaceConfig(States.AMARANTH_DYLIUM, States.END_STONE, States.END_STONE)));

	public static final class States {
		protected static final BlockState END_STONE = Blocks.END_STONE.getDefaultState();
		protected static final BlockState AMARANTH_DYLIUM = WEBlockPack.AMARANTH_DYLIUM.getDefaultState();
	}
}
