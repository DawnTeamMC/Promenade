package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.promenade.object.world.gen.feature.EHugeMushroomFeatureConfig;
import com.hugman.promenade.util.GenUtil;
import com.hugman.promenade.util.PFeatureRegistrer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class MushroomBundle extends PromenadeBundle {
	public static final FabricBlockSettings MUSHROOM_BLOCk_SETTINGS = FabricBlockSettings.of(Material.WOOD).hardness(0.2F).sounds(BlockSoundGroup.WOOD);
	public static final Block WHITE_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("white_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.WHITE)).build());
	public static final Block LIGHT_GRAY_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("light_gray_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.LIGHT_GRAY)).build());
	public static final Block GRAY_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("gray_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.GRAY)).build());
	public static final Block BLACK_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("black_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.BLACK)).build());
	public static final Block ORANGE_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("orange_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.ORANGE)).build());
	public static final Block YELLOW_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("yellow_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.YELLOW)).build());
	public static final Block LIME_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("lime_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.LIME)).build());
	public static final Block GREEN_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("green_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.GREEN)).build());
	public static final Block CYAN_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("cyan_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.CYAN)).build());
	public static final Block LIGHT_BLUE_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("light_blue_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.LIGHT_BLUE)).build());
	public static final Block BLUE_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("blue_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.BLUE)).build());
	public static final Block PURPLE_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("purple_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.PURPLE)).build());
	public static final Block MAGENTA_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("magenta_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.MAGENTA)).build());
	public static final Block PINK_MUSHROOM_BLOCK = add(DefaultBlockBuilders.MUSHROOM_BLOCK.copy("pink_mushroom_block").settings(MUSHROOM_BLOCk_SETTINGS.mapColor(DyeColor.PINK)).build());

	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> WHITE_HUGE_MUSHROOM = PFeatureRegistrer.config("white_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(WHITE_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> LIGHT_GRAY_HUGE_MUSHROOM = PFeatureRegistrer.config("light_gray_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(LIGHT_GRAY_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> GRAY_HUGE_MUSHROOM = PFeatureRegistrer.config("gray_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(GRAY_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> BLACK_HUGE_MUSHROOM = PFeatureRegistrer.config("black_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(BLACK_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> ORANGE_HUGE_MUSHROOM = PFeatureRegistrer.config("orange_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(ORANGE_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> YELLOW_HUGE_MUSHROOM = PFeatureRegistrer.config("yellow_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(YELLOW_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> LIME_HUGE_MUSHROOM = PFeatureRegistrer.config("lime_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(LIME_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> GREEN_HUGE_MUSHROOM = PFeatureRegistrer.config("green_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(GREEN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> CYAN_HUGE_MUSHROOM = PFeatureRegistrer.config("cyan_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(CYAN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> LIGHT_BLUE_HUGE_MUSHROOM = PFeatureRegistrer.config("light_blue_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(LIGHT_BLUE_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> BLUE_HUGE_MUSHROOM = PFeatureRegistrer.config("blue_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(BLUE_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> PURPLE_HUGE_MUSHROOM = PFeatureRegistrer.config("purple_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(PURPLE_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> MAGENTA_HUGE_MUSHROOM = PFeatureRegistrer.config("magenta_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(MAGENTA_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	public static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> PINK_HUGE_MUSHROOM = PFeatureRegistrer.config("pink_huge_mushroom", Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of(PINK_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));

	public static final PlantBundle WHITE_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("white_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> WHITE_HUGE_MUSHROOM		))));
	public static final PlantBundle LIGHT_GRAY_MUSHROOM	= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("light_gray_mushroom")	.provider(s -> new MushroomPlantBlock(s, () -> LIGHT_GRAY_HUGE_MUSHROOM	))));
	public static final PlantBundle GRAY_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("gray_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> GRAY_HUGE_MUSHROOM		))));
	public static final PlantBundle BLACK_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("black_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> BLACK_HUGE_MUSHROOM		))));
	public static final PlantBundle ORANGE_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("orange_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> ORANGE_HUGE_MUSHROOM		))));
	public static final PlantBundle YELLOW_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("yellow_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> YELLOW_HUGE_MUSHROOM		))));
	public static final PlantBundle LIME_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("lime_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> LIME_HUGE_MUSHROOM		))));
	public static final PlantBundle GREEN_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("green_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> GREEN_HUGE_MUSHROOM		))));
	public static final PlantBundle CYAN_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("cyan_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> CYAN_HUGE_MUSHROOM		))));
	public static final PlantBundle LIGHT_BLUE_MUSHROOM	= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("light_blue_mushroom")	.provider(s -> new MushroomPlantBlock(s, () -> LIGHT_BLUE_HUGE_MUSHROOM	))));
	public static final PlantBundle BLUE_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("blue_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> BLUE_HUGE_MUSHROOM		))));
	public static final PlantBundle PURPLE_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("purple_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> PURPLE_HUGE_MUSHROOM		))));
	public static final PlantBundle MAGENTA_MUSHROOM	= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("magenta_mushroom")	.provider(s -> new MushroomPlantBlock(s, () -> MAGENTA_HUGE_MUSHROOM	))));
	public static final PlantBundle PINK_MUSHROOM		= creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("pink_mushroom")		.provider(s -> new MushroomPlantBlock(s, () -> PINK_HUGE_MUSHROOM		))));

	public static class Features {
		public static class Configured {
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WHITE_MUSHROOM_PATCH = patch("white", WHITE_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LIGHT_GRAY_MUSHROOM_PATCH = patch("light_gray", LIGHT_GRAY_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> GRAY_MUSHROOM_PATCH = patch("gray", GRAY_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> BLACK_MUSHROOM_PATCH = patch("black", BLACK_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ORANGE_MUSHROOM_PATCH = patch("orange", ORANGE_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> YELLOW_MUSHROOM_PATCH = patch("yellow", YELLOW_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LIME_MUSHROOM_PATCH = patch("lime", LIME_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> GREEN_MUSHROOM_PATCH = patch("green", GREEN_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> CYAN_MUSHROOM_PATCH = patch("cyan", CYAN_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LIGHT_BLUE_MUSHROOM_PATCH = patch("light_blue", LIGHT_BLUE_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> BLUE_MUSHROOM_PATCH = patch("blue", BLUE_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PURPLE_MUSHROOM_PATCH = patch("purple", PURPLE_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MAGENTA_MUSHROOM_PATCH = patch("magenta", MAGENTA_MUSHROOM);
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PINK_MUSHROOM_PATCH = patch("pink", PINK_MUSHROOM);

			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLUE = PFeatureRegistrer.config("huge_nether_mushroom/blue", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLUE);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_CYAN = PFeatureRegistrer.config("huge_nether_mushroom/cyan", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_CYAN);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_PINK = PFeatureRegistrer.config("huge_nether_mushroom/pink", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_PINK);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_YELLOW = PFeatureRegistrer.config("huge_nether_mushroom/yellow", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_YELLOW);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BROWN = PFeatureRegistrer.config("huge_nether_mushroom/brown", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BROWN);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_WHITE = PFeatureRegistrer.config("huge_nether_mushroom/white", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_WHITE);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_LIGHT_GRAY = PFeatureRegistrer.config("huge_nether_mushroom/light_gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_GRAY = PFeatureRegistrer.config("huge_nether_mushroom/gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_GRAY);
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLACK = PFeatureRegistrer.config("huge_nether_mushroom/black", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLACK);

			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLUE_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/blue", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLUE.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_CYAN_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/cyan", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_CYAN.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_PINK_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/pink", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_PINK.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/yellow", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_YELLOW.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BROWN_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/brown", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BROWN.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_WHITE_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/white", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_WHITE.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/light_gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_GRAY_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_GRAY.setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLACK_UPSIDE = PFeatureRegistrer.config("huge_nether_mushroom/upside/black", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLACK.setUpsideDown());

			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLUE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/blue", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLUE.setFlatHat());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_CYAN_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/cyan", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_CYAN.setFlatHat().setHatBaseSize(3));
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_PINK_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/pink", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_PINK.setFlatHat());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_YELLOW_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/yellow", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_YELLOW.setFlatHat());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BROWN_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/brown", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BROWN.setFlatHat());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_WHITE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/white", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_WHITE.setFlatHat());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/light_gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setFlatHat());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_GRAY_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_GRAY.setFlatHat());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLACK_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/flat/black", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLACK.setFlatHat());

			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLUE_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/blue", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLUE.setFlatHat().setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_CYAN_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/cyan", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_CYAN.setFlatHat().setUpsideDown().setHatBaseSize(3));
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_PINK_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/pink", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_PINK.setFlatHat().setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_YELLOW_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/yellow", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_YELLOW.setFlatHat().setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BROWN_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/brown", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BROWN.setFlatHat().setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_WHITE_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/white", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_WHITE.setFlatHat().setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_LIGHT_GRAY_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/light_gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_LIGHT_GRAY.setFlatHat().setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_GRAY_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/gray", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_GRAY.setFlatHat().setUpsideDown());
			public static final RegistryEntry<ConfiguredFeature<EHugeMushroomFeatureConfig, ?>> HUGE_NETHER_MUSHROOM_BLACK_UPSIDE_FLAT = PFeatureRegistrer.config("huge_nether_mushroom/upside_flat/black", CommonBundle.HUGE_MUSHROOM, Configs.HUGE_NETHER_MUSHROOM_BLACK.setFlatHat().setUpsideDown());

			public static RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> patch(String color, PlantBundle mushroom) {
				return PFeatureRegistrer.config("patch/mushroom/" + color, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(mushroom.getPlant()))));
			}

			public static final class Configs {
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BLUE = new EHugeMushroomFeatureConfig(13, 8, BLUE_MUSHROOM_BLOCK, 3, 3, Blocks.SHROOMLIGHT, 0.1F, 0.05F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_CYAN = new EHugeMushroomFeatureConfig(4, 3, CYAN_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.05F, 0.1F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_PINK = new EHugeMushroomFeatureConfig(5, 4, PINK_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.05F, 0.1F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_YELLOW = new EHugeMushroomFeatureConfig(4, 3, YELLOW_MUSHROOM_BLOCK, 3, 0, Blocks.SHROOMLIGHT, 0.1F, 0.0F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BROWN = new EHugeMushroomFeatureConfig(4, 3, Blocks.BROWN_MUSHROOM_BLOCK, 3, 0, Blocks.SHROOMLIGHT, 0.1F, 0.0F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_WHITE = new EHugeMushroomFeatureConfig(6, 2, WHITE_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.05F, 0.0F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_LIGHT_GRAY = new EHugeMushroomFeatureConfig(4, 7, LIGHT_GRAY_MUSHROOM_BLOCK, 3, 1, Blocks.SHROOMLIGHT, 0.05F, 0.0F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_GRAY = new EHugeMushroomFeatureConfig(4, 7, GRAY_MUSHROOM_BLOCK, 3, 1, Blocks.SHROOMLIGHT, 0.01F, 0.0F);
				private static final EHugeMushroomFeatureConfig HUGE_NETHER_MUSHROOM_BLACK = new EHugeMushroomFeatureConfig(6, 2, BLACK_MUSHROOM_BLOCK, 2, 1, Blocks.SHROOMLIGHT, 0.01F, 0.0F);
			}
		}

		public static class Placed {
			public static final RegistryEntry<PlacedFeature> WHITE_MUSHROOM_NORMAL_PATCH = patch("white", Configured.WHITE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> LIGHT_GRAY_MUSHROOM_NORMAL_PATCH = patch("light_gray", Configured.LIGHT_GRAY_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> GRAY_MUSHROOM_NORMAL_PATCH = patch("gray", Configured.GRAY_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> BLACK_MUSHROOM_NORMAL_PATCH = patch("black", Configured.BLACK_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> ORANGE_MUSHROOM_NORMAL_PATCH = patch("orange", Configured.ORANGE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> YELLOW_MUSHROOM_NORMAL_PATCH = patch("yellow", Configured.YELLOW_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> LIME_MUSHROOM_NORMAL_PATCH = patch("lime", Configured.LIME_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> GREEN_MUSHROOM_NORMAL_PATCH = patch("green", Configured.GREEN_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> CYAN_MUSHROOM_NORMAL_PATCH = patch("cyan", Configured.CYAN_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> LIGHT_BLUE_MUSHROOM_NORMAL_PATCH = patch("light_blue", Configured.LIGHT_BLUE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> BLUE_MUSHROOM_NORMAL_PATCH = patch("blue", Configured.BLUE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> PURPLE_MUSHROOM_NORMAL_PATCH = patch("purple", Configured.PURPLE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> MAGENTA_MUSHROOM_NORMAL_PATCH = patch("magenta", Configured.MAGENTA_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> PINK_MUSHROOM_NORMAL_PATCH = patch("pink", Configured.PINK_MUSHROOM_PATCH);

			public static final RegistryEntry<PlacedFeature> WHITE_MUSHROOM_NETHER_PATCH = netherPatch("white", Configured.WHITE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> LIGHT_GRAY_MUSHROOM_NETHER_PATCH = netherPatch("light_gray", Configured.LIGHT_GRAY_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> GRAY_MUSHROOM_NETHER_PATCH = netherPatch("gray", Configured.GRAY_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> BLACK_MUSHROOM_NETHER_PATCH = netherPatch("black", Configured.BLACK_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> ORANGE_MUSHROOM_NETHER_PATCH = netherPatch("orange", Configured.ORANGE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> YELLOW_MUSHROOM_NETHER_PATCH = netherPatch("yellow", Configured.YELLOW_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> LIME_MUSHROOM_NETHER_PATCH = netherPatch("lime", Configured.LIME_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> GREEN_MUSHROOM_NETHER_PATCH = netherPatch("green", Configured.GREEN_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> CYAN_MUSHROOM_NETHER_PATCH = netherPatch("cyan", Configured.CYAN_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> LIGHT_BLUE_MUSHROOM_NETHER_PATCH = netherPatch("light_blue", Configured.LIGHT_BLUE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> BLUE_MUSHROOM_NETHER_PATCH = netherPatch("blue", Configured.BLUE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> PURPLE_MUSHROOM_NETHER_PATCH = netherPatch("purple", Configured.PURPLE_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> MAGENTA_MUSHROOM_NETHER_PATCH = netherPatch("magenta", Configured.MAGENTA_MUSHROOM_PATCH);
			public static final RegistryEntry<PlacedFeature> PINK_MUSHROOM_NETHER_PATCH = netherPatch("pink", Configured.PINK_MUSHROOM_PATCH);

			public static RegistryEntry<PlacedFeature> patch(String color, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature) {
				return PFeatureRegistrer.place("patch/mushroom/normal/" + color, feature, GenUtil.vegetationModifiersWithChance(512, null));
			}

			public static RegistryEntry<PlacedFeature> netherPatch(String color, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature) {
				return PFeatureRegistrer.place("patch/mushroom/nether/" + color, feature, RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());
			}
		}
	}
}
