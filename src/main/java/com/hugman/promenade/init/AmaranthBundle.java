package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.NetherWoodBundle;
import com.hugman.dawn.api.object.block.RootsBlock;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.DyliumBlock;
import com.hugman.promenade.object.world.gen.feature.config.BoulderFeatureConfig;
import com.hugman.promenade.object.world.gen.feature.config.SpikeFeatureConfig;
import com.hugman.promenade.util.BiomeUtil;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.collection.DataPool;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountMultilayerPlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class AmaranthBundle extends PromenadeBundle {
	public static final Block BLACK_DYLIUM = add(new BlockCreator.Builder("black_dylium", DyliumBlock::new, FabricBlockSettings.of(Material.STONE, MapColor.DULL_RED).requiresTool().strength(3.0F, 9.0F).sounds(BlockSoundGroup.NYLIUM).ticksRandomly()).itemGroup(ItemGroup.BUILDING_BLOCKS).build());
	public static final Block DARK_AMARANTH_WART_BLOCK = add(new BlockCreator.Builder("dark_amaranth_wart_block", Block::new, FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK)).itemGroup(ItemGroup.BUILDING_BLOCKS).build());
	public static final Block DARK_AMARANTH_ROOTS = add(new BlockCreator.Builder("dark_amaranth_roots", RootsBlock::new, FabricBlockSettings.of(Material.NETHER_SHOOTS, MapColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F).build());
	public static final NetherWoodBundle DARK_AMARANTH_WOOD = creator(new NetherWoodBundle.Builder("dark_amaranth", () -> Features.Configured.AMARANTH_FUNGUS_PLANTED, MapColor.LIGHT_GRAY, MapColor.DARK_DULL_PINK).build());

	//TODO
	//public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> AMARANTH_DYLIUM = add(new ConfiguredSurfaceBuilderCreator<>("amaranth_dylium", SurfaceBuilder.DEFAULT.withConfig(new TernarySurfaceConfig(States.AMARANTH_DYLIUM, States.END_STONE, States.END_STONE))));

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.dark_amaranth_forests_weight > 0) {
			//TheEndBiomes.addHighlandsBiome(Biomes.TALL_DARK_AMARANTH_FOREST.getRegistryKey(), Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
			//TheEndBiomes.addMidlandsBiome(Biomes.TALL_DARK_AMARANTH_FOREST.getRegistryKey(), Biomes.DARK_AMARANTH_FOREST.getRegistryKey(), Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
		}
	}

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<?, ?> AMARANTH_FUNGUS = add(new ConfiguredFeatureCreator<>("amaranth_fungus/normal", Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(BLACK_DYLIUM.getDefaultState(), DARK_AMARANTH_WOOD.getStem().getDefaultState(), DARK_AMARANTH_WART_BLOCK.getDefaultState(), Blocks.COBWEB.getDefaultState(), false))));
			public static final ConfiguredFeature<?, ?> TALL_AMARANTH_FUNGUS = add(new ConfiguredFeatureCreator<>("amaranth_fungus/tall", CommonBundle.TALL_HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(BLACK_DYLIUM.getDefaultState(), DARK_AMARANTH_WOOD.getStem().getDefaultState(), DARK_AMARANTH_WART_BLOCK.getDefaultState(), Blocks.COBWEB.getDefaultState(), false))));
			public static final ConfiguredFeature<HugeFungusFeatureConfig, ?> AMARANTH_FUNGUS_PLANTED = add(new ConfiguredFeatureCreator<>("amaranth_fungus/planted", Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(BLACK_DYLIUM.getDefaultState(), DARK_AMARANTH_WOOD.getStem().getDefaultState(), DARK_AMARANTH_WART_BLOCK.getDefaultState(), Blocks.COBWEB.getDefaultState(), true))));

			private static final WeightedBlockStateProvider AMARANTH_FOREST_VEGETATION_PROVIDER = new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(AmaranthBundle.DARK_AMARANTH_ROOTS.getDefaultState(), 87).add(DARK_AMARANTH_WOOD.getFungus().getDefaultState(), 13));
			public static final ConfiguredFeature<?, ?> DARK_AMARANTH_FOREST_VEGETATION = add(new ConfiguredFeatureCreator<>("dark_amaranth_forest_vegetation/normal", Feature.NETHER_FOREST_VEGETATION.configure(new NetherForestVegetationFeatureConfig(AMARANTH_FOREST_VEGETATION_PROVIDER, 8, 4))));
			public static final ConfiguredFeature<?, ?> DARK_AMARANTH_FOREST_VEGETATION_BONEMEAL = add(new ConfiguredFeatureCreator<>("dark_amaranth_forest_vegetation/bonemeal", Feature.NETHER_FOREST_VEGETATION.configure(new NetherForestVegetationFeatureConfig(AMARANTH_FOREST_VEGETATION_PROVIDER, 3, 1))));
			public static final ConfiguredFeature<?, ?> OBSIDIAN_SPIKE = add(new ConfiguredFeatureCreator<>("obsidian_spike", CommonBundle.SPIKE.configure(new SpikeFeatureConfig(Blocks.OBSIDIAN.getDefaultState(), List.of(BLACK_DYLIUM, Blocks.END_STONE), 6, 22, 2, 3, 0, 360, 150, 30, -4).setDecoration(Blocks.CRYING_OBSIDIAN.getDefaultState(), 0.08f))));
			public static final ConfiguredFeature<?, ?> ENDER_BOULDER = add(new ConfiguredFeatureCreator<>("ender_boulder", CommonBundle.BOULDER.configure(new BoulderFeatureConfig(Blocks.OBSIDIAN.getDefaultState(), List.of(Blocks.END_STONE.getDefaultState())))));
		}

		public static class Placed {
			public static final PlacedFeature AMARANTH_FUNGI = add(new PlacedFeatureCreator("amaranth_fungi", Configured.AMARANTH_FUNGUS.withPlacement(CountMultilayerPlacementModifier.of(8), BiomePlacementModifier.of())));
			public static final PlacedFeature TALL_AMARANTH_FUNGI = add(new PlacedFeatureCreator("tall_amaranth_fungi", Configured.AMARANTH_FUNGUS.withPlacement(CountMultilayerPlacementModifier.of(8), BiomePlacementModifier.of())));
			public static final PlacedFeature DARK_AMARANTH_FOREST_VEGETATION = add(new PlacedFeatureCreator("dark_amaranth_forest_vegetation", Configured.DARK_AMARANTH_FOREST_VEGETATION.withPlacement(CountMultilayerPlacementModifier.of(6), BiomePlacementModifier.of())));
			public static final PlacedFeature OBSIDIAN_SPIKES = add(new PlacedFeatureCreator("obsidian_spikes", Configured.OBSIDIAN_SPIKE.withPlacement(CountPlacementModifier.of(12), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of())));
		}
	}

	public static class Biomes {
		public static final BiomeCreator DARK_AMARANTH_FOREST = creator(new BiomeCreator("dark_amaranth_forest", createDarkAmaranthForest(false)));
		public static final BiomeCreator TALL_DARK_AMARANTH_FOREST = creator(new BiomeCreator("tall_dark_amaranth_forest", createDarkAmaranthForest(true)));

		public static Biome createDarkAmaranthForest(boolean isTall) {
			GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
			if(isTall) generationBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Features.Placed.OBSIDIAN_SPIKES);
			generationBuilder
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, isTall ? Features.Placed.TALL_AMARANTH_FUNGI : Features.Placed.AMARANTH_FUNGI)
					/* TODO
					.surfaceBuilder(PromenadeConfiguredSurfaceBuilders.AMARANTH_DYLIUM)
					.structureFeature(ConfiguredStructureFeatures.END_CITY)
					 */
					.feature(GenerationStep.Feature.SURFACE_STRUCTURES, EndPlacedFeatures.END_GATEWAY_RETURN)
					.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.DARK_AMARANTH_FOREST_VEGETATION);
			SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
			DefaultBiomeFeatures.addEndMobs(spawnSettings);
			spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 10, 3, 4));
			spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CAVE_SPIDER, 6, 1, 3));
			return BiomeUtil.createEndBiome(generationBuilder, spawnSettings);
		}
	}
}
