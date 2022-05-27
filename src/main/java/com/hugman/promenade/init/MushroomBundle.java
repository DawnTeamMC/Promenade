package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.promenade.object.block.DMushroomPlantBlock;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

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

	public static final RegistryKey<ConfiguredFeature<?, ?>> WHITE_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("white_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> LIGHT_GRAY_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("light_gray_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> GRAY_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("gray_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> BLACK_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("black_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ORANGE_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("orange_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("yellow_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> LIME_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("lime_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> GREEN_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("green_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> CYAN_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("cyan_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> LIGHT_BLUE_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("light_blue_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> BLUE_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("blue_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PURPLE_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("purple_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> MAGENTA_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("magenta_huge_mushroom");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_HUGE_MUSHROOM = WorldGenUtil.configuredFeatureKey("pink_huge_mushroom");

	public static final PlantBundle WHITE_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("white_mushroom").provider(s -> new DMushroomPlantBlock(s, WHITE_HUGE_MUSHROOM))));
	public static final PlantBundle LIGHT_GRAY_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("light_gray_mushroom").provider(s -> new DMushroomPlantBlock(s, LIGHT_GRAY_HUGE_MUSHROOM))));
	public static final PlantBundle GRAY_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("gray_mushroom").provider(s -> new DMushroomPlantBlock(s, GRAY_HUGE_MUSHROOM))));
	public static final PlantBundle BLACK_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("black_mushroom").provider(s -> new DMushroomPlantBlock(s, BLACK_HUGE_MUSHROOM))));
	public static final PlantBundle ORANGE_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("orange_mushroom").provider(s -> new DMushroomPlantBlock(s, ORANGE_HUGE_MUSHROOM))));
	public static final PlantBundle YELLOW_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("yellow_mushroom").provider(s -> new DMushroomPlantBlock(s, YELLOW_HUGE_MUSHROOM))));
	public static final PlantBundle LIME_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("lime_mushroom").provider(s -> new DMushroomPlantBlock(s, LIME_HUGE_MUSHROOM))));
	public static final PlantBundle GREEN_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("green_mushroom").provider(s -> new DMushroomPlantBlock(s, GREEN_HUGE_MUSHROOM))));
	public static final PlantBundle CYAN_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("cyan_mushroom").provider(s -> new DMushroomPlantBlock(s, CYAN_HUGE_MUSHROOM))));
	public static final PlantBundle LIGHT_BLUE_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("light_blue_mushroom").provider(s -> new DMushroomPlantBlock(s, LIGHT_BLUE_HUGE_MUSHROOM))));
	public static final PlantBundle BLUE_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("blue_mushroom").provider(s -> new DMushroomPlantBlock(s, BLUE_HUGE_MUSHROOM))));
	public static final PlantBundle PURPLE_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("purple_mushroom").provider(s -> new DMushroomPlantBlock(s, PURPLE_HUGE_MUSHROOM))));
	public static final PlantBundle MAGENTA_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("magenta_mushroom").provider(s -> new DMushroomPlantBlock(s, MAGENTA_HUGE_MUSHROOM))));
	public static final PlantBundle PINK_MUSHROOM = creator(new PlantBundle(DefaultBlockBuilders.MUSHROOM.copy("pink_mushroom").provider(s -> new DMushroomPlantBlock(s, PINK_HUGE_MUSHROOM))));
}
