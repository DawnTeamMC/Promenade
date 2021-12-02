package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.object.block.FertilizableMushroomPlantBlock;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import com.hugman.promenade.object.world.gen.feature.config.HugeMushroomFeatureConfig;
import com.hugman.promenade.util.GenUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class MushroomBundle extends PromenadeBundle {
	public static final Block WHITE_MUSHROOM_BLOCK = add(new BlockCreator.Builder("white", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.WHITE)).build());
	public static final Block LIGHT_GRAY_MUSHROOM_BLOCK = add(new BlockCreator.Builder("light_gray", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.LIGHT_GRAY)).build());
	public static final Block GRAY_MUSHROOM_BLOCK = add(new BlockCreator.Builder("gray", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.GRAY)).build());
	public static final Block BLACK_MUSHROOM_BLOCK = add(new BlockCreator.Builder("black", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.BLACK)).build());
	public static final Block ORANGE_MUSHROOM_BLOCK = add(new BlockCreator.Builder("orange", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.ORANGE)).build());
	public static final Block YELLOW_MUSHROOM_BLOCK = add(new BlockCreator.Builder("yellow", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.YELLOW)).build());
	public static final Block LIME_MUSHROOM_BLOCK = add(new BlockCreator.Builder("lime", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.LIME)).build());
	public static final Block GREEN_MUSHROOM_BLOCK = add(new BlockCreator.Builder("green", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.GREEN)).build());
	public static final Block CYAN_MUSHROOM_BLOCK = add(new BlockCreator.Builder("cyan", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.CYAN)).build());
	public static final Block LIGHT_BLUE_MUSHROOM_BLOCK = add(new BlockCreator.Builder("light_blue", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.LIGHT_BLUE)).build());
	public static final Block BLUE_MUSHROOM_BLOCK = add(new BlockCreator.Builder("blue", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.BLUE)).build());
	public static final Block PURPLE_MUSHROOM_BLOCK = add(new BlockCreator.Builder("purple", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.PURPLE)).build());
	public static final Block MAGENTA_MUSHROOM_BLOCK = add(new BlockCreator.Builder("magenta", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.MAGENTA)).build());
	public static final Block PINK_MUSHROOM_BLOCK = add(new BlockCreator.Builder("pink", DefaultBlockTemplates.MUSHROOM_BLOCK, DefaultBlockSettings.MUSHROOM_BLOCK.mapColor(DyeColor.PINK)).build());

	private static final BlockCreator.Builder MUSHROOM_BUILDER = new BlockCreator.Builder().settings(DefaultBlockSettings.MUSHROOM).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F);

	public static final PlantBundle WHITE_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("white_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, WHITE_MUSHROOM_BLOCK))));
	public static final PlantBundle LIGHT_GRAY_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("light_gray_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, LIGHT_GRAY_MUSHROOM_BLOCK))));
	public static final PlantBundle GRAY_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("gray_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, GRAY_MUSHROOM_BLOCK))));
	public static final PlantBundle BLACK_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("black_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, BLACK_MUSHROOM_BLOCK))));
	public static final PlantBundle ORANGE_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("orange_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, ORANGE_MUSHROOM_BLOCK))));
	public static final PlantBundle YELLOW_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("yellow_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, YELLOW_MUSHROOM_BLOCK))));
	public static final PlantBundle LIME_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("lime_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, LIME_MUSHROOM_BLOCK))));
	public static final PlantBundle GREEN_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("green_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, GREEN_MUSHROOM_BLOCK))));
	public static final PlantBundle CYAN_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("cyan_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, CYAN_MUSHROOM_BLOCK))));
	public static final PlantBundle LIGHT_BLUE_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("light_blue_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, LIGHT_BLUE_MUSHROOM_BLOCK))));
	public static final PlantBundle BLUE_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("blue_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, BLUE_MUSHROOM_BLOCK))));
	public static final PlantBundle PURPLE_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("purple_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, PURPLE_MUSHROOM_BLOCK))));
	public static final PlantBundle MAGENTA_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("magenta_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, MAGENTA_MUSHROOM_BLOCK))));
	public static final PlantBundle PINK_MUSHROOM = bundle(new PlantBundle(MUSHROOM_BUILDER.name("pink_mushroom").blockProvider(settings -> new FertilizableMushroomPlantBlock(settings, PINK_MUSHROOM_BLOCK))));

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<?, ?> WHITE_MUSHROOM_PATCH = patch("white", WHITE_MUSHROOM);
			public static final ConfiguredFeature<?, ?> LIGHT_GRAY_MUSHROOM_PATCH = patch("light_gray", LIGHT_GRAY_MUSHROOM);
			public static final ConfiguredFeature<?, ?> GRAY_MUSHROOM_PATCH = patch("gray", GRAY_MUSHROOM);
			public static final ConfiguredFeature<?, ?> BLACK_MUSHROOM_PATCH = patch("black", BLACK_MUSHROOM);
			public static final ConfiguredFeature<?, ?> ORANGE_MUSHROOM_PATCH = patch("orange", ORANGE_MUSHROOM);
			public static final ConfiguredFeature<?, ?> YELLOW_MUSHROOM_PATCH = patch("yellow", YELLOW_MUSHROOM);
			public static final ConfiguredFeature<?, ?> LIME_MUSHROOM_PATCH = patch("lime", LIME_MUSHROOM);
			public static final ConfiguredFeature<?, ?> GREEN_MUSHROOM_PATCH = patch("green", GREEN_MUSHROOM);
			public static final ConfiguredFeature<?, ?> CYAN_MUSHROOM_PATCH = patch("cyan", CYAN_MUSHROOM);
			public static final ConfiguredFeature<?, ?> LIGHT_BLUE_MUSHROOM_PATCH = patch("light_blue", LIGHT_BLUE_MUSHROOM);
			public static final ConfiguredFeature<?, ?> BLUE_MUSHROOM_PATCH = patch("blue", BLUE_MUSHROOM);
			public static final ConfiguredFeature<?, ?> PURPLE_MUSHROOM_PATCH = patch("purple", PURPLE_MUSHROOM);
			public static final ConfiguredFeature<?, ?> MAGENTA_MUSHROOM_PATCH = patch("magenta", MAGENTA_MUSHROOM);
			public static final ConfiguredFeature<?, ?> PINK_MUSHROOM_PATCH = patch("pink", PINK_MUSHROOM);

			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_blue", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_cyan", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_pink", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_yellow", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_brown", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_white", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_light_gray", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_gray", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY)));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_black", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK)));

			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_blue_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_cyan_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_pink_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_yellow_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_brown_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_white_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_light_gray_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_gray_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY.setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK_UPSIDE = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_black_upside", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK.setUpsideDown())));

			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_blue_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE.setFlatHat())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_cyan_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN.setFlatHat().setHatBaseSize(3))));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_pink_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK.setFlatHat())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_yellow_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW.setFlatHat())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_brown_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN.setFlatHat())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_white_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE.setFlatHat())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_light_gray_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setFlatHat())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_gray_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY.setFlatHat())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_black_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK.setFlatHat())));

			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLUE_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_blue_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLUE.setFlatHat().setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_cyan_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_CYAN.setFlatHat().setUpsideDown().setHatBaseSize(3))));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_PINK_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_pink_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_PINK.setFlatHat().setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_yellow_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_YELLOW.setFlatHat().setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BROWN_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_brown_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BROWN.setFlatHat().setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_WHITE_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_white_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_WHITE.setFlatHat().setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_light_gray_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setFlatHat().setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_GRAY_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_gray_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_GRAY.setFlatHat().setUpsideDown())));
			public static final ConfiguredFeature<?, ?> HUGE_NETHER_MUSHROOM_BLACK_UPSIDE_FLAT = add(new ConfiguredFeatureCreator<>("huge_nether_mushroom_black_upside_flat", CommonBundle.HUGE_MUSHROOM.configure(Configs.HUGE_NETHER_MUSHROOM_BLACK.setFlatHat().setUpsideDown())));

			public static ConfiguredFeature<?, ?> patch(String color, PlantBundle mushroom) {
				return add(new ConfiguredFeatureCreator<>("patch/mushroom/" + color, Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(mushroom.getPlant())))))));
			}

			public static final class Configs {
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BLUE = new HugeMushroomFeatureConfig(13, 8, BLUE_MUSHROOM_BLOCK, 3, 3, Blocks.SHROOMLIGHT, 0.1F, 0.05F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_CYAN = new HugeMushroomFeatureConfig(4, 3, CYAN_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.05F, 0.1F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_PINK = new HugeMushroomFeatureConfig(5, 4, PINK_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.05F, 0.1F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_YELLOW = new HugeMushroomFeatureConfig(4, 3, YELLOW_MUSHROOM_BLOCK, 3, 0, Blocks.SHROOMLIGHT, 0.1F, 0.0F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BROWN = new HugeMushroomFeatureConfig(4, 3, Blocks.BROWN_MUSHROOM_BLOCK, 3, 0, Blocks.SHROOMLIGHT, 0.1F, 0.0F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_WHITE = new HugeMushroomFeatureConfig(6, 2, WHITE_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.05F, 0.0F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_LIGHT_GRAY = new HugeMushroomFeatureConfig(4, 7, LIGHT_GRAY_MUSHROOM_BLOCK, 3, 1, Blocks.SHROOMLIGHT, 0.05F, 0.0F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_GRAY = new HugeMushroomFeatureConfig(4, 7, GRAY_MUSHROOM_BLOCK, 3, 1, Blocks.SHROOMLIGHT, 0.01F, 0.0F);
				private static final HugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BLACK = new HugeMushroomFeatureConfig(6, 2, BLACK_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.01F, 0.0F);
			}
		}

		public static class Placed {
			public static final PlacedFeature WHITE_MUSHROOM_NORMAL_PATCH = patch("white", Configured.WHITE_MUSHROOM_PATCH);
			public static final PlacedFeature LIGHT_GRAY_MUSHROOM_NORMAL_PATCH = patch("light_gray", Configured.LIGHT_GRAY_MUSHROOM_PATCH);
			public static final PlacedFeature GRAY_MUSHROOM_NORMAL_PATCH = patch("gray", Configured.GRAY_MUSHROOM_PATCH);
			public static final PlacedFeature BLACK_MUSHROOM_NORMAL_PATCH = patch("black", Configured.BLACK_MUSHROOM_PATCH);
			public static final PlacedFeature ORANGE_MUSHROOM_NORMAL_PATCH = patch("orange", Configured.ORANGE_MUSHROOM_PATCH);
			public static final PlacedFeature YELLOW_MUSHROOM_NORMAL_PATCH = patch("yellow", Configured.YELLOW_MUSHROOM_PATCH);
			public static final PlacedFeature LIME_MUSHROOM_NORMAL_PATCH = patch("lime", Configured.LIME_MUSHROOM_PATCH);
			public static final PlacedFeature GREEN_MUSHROOM_NORMAL_PATCH = patch("green", Configured.GREEN_MUSHROOM_PATCH);
			public static final PlacedFeature CYAN_MUSHROOM_NORMAL_PATCH = patch("cyan", Configured.CYAN_MUSHROOM_PATCH);
			public static final PlacedFeature LIGHT_BLUE_MUSHROOM_NORMAL_PATCH = patch("light_blue", Configured.LIGHT_BLUE_MUSHROOM_PATCH);
			public static final PlacedFeature BLUE_MUSHROOM_NORMAL_PATCH = patch("blue", Configured.BLUE_MUSHROOM_PATCH);
			public static final PlacedFeature PURPLE_MUSHROOM_NORMAL_PATCH = patch("purple", Configured.PURPLE_MUSHROOM_PATCH);
			public static final PlacedFeature MAGENTA_MUSHROOM_NORMAL_PATCH = patch("magenta", Configured.MAGENTA_MUSHROOM_PATCH);
			public static final PlacedFeature PINK_MUSHROOM_NORMAL_PATCH = patch("pink", Configured.PINK_MUSHROOM_PATCH);

			public static final PlacedFeature WHITE_MUSHROOM_NETHER_PATCH = netherPatch("white", Configured.WHITE_MUSHROOM_PATCH);
			public static final PlacedFeature LIGHT_GRAY_MUSHROOM_NETHER_PATCH = netherPatch("light_gray", Configured.LIGHT_GRAY_MUSHROOM_PATCH);
			public static final PlacedFeature GRAY_MUSHROOM_NETHER_PATCH = netherPatch("gray", Configured.GRAY_MUSHROOM_PATCH);
			public static final PlacedFeature BLACK_MUSHROOM_NETHER_PATCH = netherPatch("black", Configured.BLACK_MUSHROOM_PATCH);
			public static final PlacedFeature ORANGE_MUSHROOM_NETHER_PATCH = netherPatch("orange", Configured.ORANGE_MUSHROOM_PATCH);
			public static final PlacedFeature YELLOW_MUSHROOM_NETHER_PATCH = netherPatch("yellow", Configured.YELLOW_MUSHROOM_PATCH);
			public static final PlacedFeature LIME_MUSHROOM_NETHER_PATCH = netherPatch("lime", Configured.LIME_MUSHROOM_PATCH);
			public static final PlacedFeature GREEN_MUSHROOM_NETHER_PATCH = netherPatch("green", Configured.GREEN_MUSHROOM_PATCH);
			public static final PlacedFeature CYAN_MUSHROOM_NETHER_PATCH = netherPatch("cyan", Configured.CYAN_MUSHROOM_PATCH);
			public static final PlacedFeature LIGHT_BLUE_MUSHROOM_NETHER_PATCH = netherPatch("light_blue", Configured.LIGHT_BLUE_MUSHROOM_PATCH);
			public static final PlacedFeature BLUE_MUSHROOM_NETHER_PATCH = netherPatch("blue", Configured.BLUE_MUSHROOM_PATCH);
			public static final PlacedFeature PURPLE_MUSHROOM_NETHER_PATCH = netherPatch("purple", Configured.PURPLE_MUSHROOM_PATCH);
			public static final PlacedFeature MAGENTA_MUSHROOM_NETHER_PATCH = netherPatch("magenta", Configured.MAGENTA_MUSHROOM_PATCH);
			public static final PlacedFeature PINK_MUSHROOM_NETHER_PATCH = netherPatch("pink", Configured.PINK_MUSHROOM_PATCH);

			public static PlacedFeature patch(String color, ConfiguredFeature<?, ?> feature) {
				return add(new PlacedFeatureCreator("patch/mushroom/normal/" + color, feature.withPlacement(GenUtil.vegetationModifiersWithChance(512, null))));
			}

			public static PlacedFeature netherPatch(String color, ConfiguredFeature<?, ?> feature) {
				return add(new PlacedFeatureCreator("patch/mushroom/nether/" + color, feature.withPlacement(RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of())));
			}
		}
	}
}
