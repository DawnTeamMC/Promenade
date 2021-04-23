package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.block.MTBlockBundle;
import com.hugman.dawn.api.creator.bundle.block.NetherWoodBundle;
import com.hugman.dawn.api.creator.bundle.block.NormalWoodBundle;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.creator.bundle.block.WoodBundle;
import com.hugman.dawn.api.object.block.FertilizableMushroomPlantBlock;
import com.hugman.dawn.api.object.block.PlantPileBlock;
import com.hugman.dawn.api.object.block.RootsBlock;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.BlockTemplate;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import com.hugman.promenade.creator.bundle.block.OreContainerBundle;
import com.hugman.promenade.init.world.PromenadeConfiguredFeatures;
import com.hugman.promenade.object.block.BlueberryBushBlock;
import com.hugman.promenade.object.block.DyliumBlock;
import com.hugman.promenade.object.block.WitherRosePileBlock;
import com.hugman.promenade.object.block.sapling_generator.AutumnBirchSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.AutumnOakSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.PalmSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.PinkCherryOakSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.WhiteCherryOakSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.DyeColor;

public class PromenadeBlocks extends PromenadeBundle {
	public static final OreContainerBundle GRANITE_ORES = bundle(new OreContainerBundle("granite", FabricBlockSettings.of(Material.STONE, MapColor.DIRT_BROWN).requiresTool().strength(3.0F, 3.0F)));
	public static final OreContainerBundle DIORITE_ORES = bundle(new OreContainerBundle("diorite", FabricBlockSettings.of(Material.STONE, MapColor.OFF_WHITE).requiresTool().strength(3.0F, 3.0F)));
	public static final OreContainerBundle ANDESITE_ORES = bundle(new OreContainerBundle("andesite", FabricBlockSettings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(3.0F, 3.0F)));
	public static final OreContainerBundle TUFF_ORES = bundle(new OreContainerBundle("tuff", FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_GRAY).sounds(BlockSoundGroup.TUFF).requiresTool().strength(3.0F, 3.0F)));

	public static final Block OAK_LEAF_PILE = add(new BlockCreator.Builder("oak", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());
	public static final Block SPRUCE_LEAF_PILE = add(new BlockCreator.Builder("spruce", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());
	public static final Block BIRCH_LEAF_PILE = add(new BlockCreator.Builder("birch", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());
	public static final Block JUNGLE_LEAF_PILE = add(new BlockCreator.Builder("jungle", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());
	public static final Block ACACIA_LEAF_PILE = add(new BlockCreator.Builder("acacia", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());
	public static final Block DARK_OAK_LEAF_PILE = add(new BlockCreator.Builder("dark_oak", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());

	public static final Block DANDELION_PILE = add(new BlockCreator.Builder("dandelion", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block POPPY_PILE = add(new BlockCreator.Builder("poppy", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block BLUE_ORCHID_PILE = add(new BlockCreator.Builder("blue_orchid", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block ALLIUM_PILE = add(new BlockCreator.Builder("allium", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block AZURE_BLUET_PILE = add(new BlockCreator.Builder("azure_bluet", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block RED_TULIP_PILE = add(new BlockCreator.Builder("red_tulip", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block ORANGE_TULIP_PILE = add(new BlockCreator.Builder("orange_tulip", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block WHITE_TULIP_PILE = add(new BlockCreator.Builder("white_tulip", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block PINK_TULIP_PILE = add(new BlockCreator.Builder("pink_tulip", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block OXEYE_DAISY_PILE = add(new BlockCreator.Builder("oxeye_daisy", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block CORNFLOWER_PILE = add(new BlockCreator.Builder("cornflower", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block LILY_OF_THE_VALLEY_PILE = add(new BlockCreator.Builder("lily_of_the_valley", Templates.PLANT_PILE, Settings.FLOWER_PILE).flammability(60, 100).build());
	public static final Block WITHER_ROSE_PILE = add(new BlockCreator.Builder("wither_rose", Templates.PLANT_PILE, Settings.FLOWER_PILE).blockProvider(WitherRosePileBlock::new).flammability(60, 100).build());

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

	public static final BlockCreator.Builder MUSHROOM_BUILDER = new BlockCreator.Builder().settings(DefaultBlockSettings.MUSHROOM).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F);

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

	public static final Block BLUEBERRY_BUSH = add(new BlockCreator.Builder("blueberry_bush", BlueberryBushBlock::new, FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)).flammability(60, 100).render(BlockCreator.Render.CUTOUT).noItem().build());

	public static final MTBlockBundle BLUNITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("blunite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB, DefaultBlockTemplates.WALL));
	public static final MTBlockBundle CARBONITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("carbonite").settings(FabricBlockSettings.copyOf(Blocks.ANDESITE).mapColor(MapColor.BLACK)), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB, DefaultBlockTemplates.WALL));
	public static final MTBlockBundle POLISHED_BLUNITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("polished_blunite").settings(FabricBlockSettings.copyOf(BLUNITE.getBlock(DefaultBlockTemplates.CUBE))), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB));
	public static final MTBlockBundle POLISHED_CARBONITE = bundle(new MTBlockBundle(new BlockCreator.Builder().name("polished_carbonite").settings(FabricBlockSettings.copyOf(CARBONITE.getBlock(DefaultBlockTemplates.CUBE))), DefaultBlockTemplates.CUBE, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB));

	public static final PlantBundle AUTUMN_OAK_SAPLING = bundle(new PlantBundle(new BlockCreator.Builder("autumn_oak_sapling", settings -> new SaplingBlock(new AutumnOakSaplingGenerator(), settings), DefaultBlockSettings.SAPLING).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final Block AUTUMN_OAK_LEAVES = add(new BlockCreator.Builder("autumn_oak", DefaultBlockTemplates.LEAVES, DefaultBlockSettings.LEAVES).flammability(30, 60).build());
	public static final Block AUTUMN_OAK_LEAF_PILE = add(new BlockCreator.Builder("autumn_oak", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());
	public static final PlantBundle AUTUMN_BIRCH_SAPLING = bundle(new PlantBundle(new BlockCreator.Builder("autumn_birch_sapling", settings -> new SaplingBlock(new AutumnBirchSaplingGenerator(), settings), DefaultBlockSettings.SAPLING).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final Block AUTUMN_BIRCH_LEAVES = add(new BlockCreator.Builder("autumn_birch", DefaultBlockTemplates.LEAVES, DefaultBlockSettings.LEAVES).flammability(30, 60).build());
	public static final Block AUTUMN_BIRCH_LEAF_PILE = add(new BlockCreator.Builder("autumn_birch", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());

	public static final WoodBundle CHERRY_OAK_WOOD = bundle(new WoodBundle.Builder("cherry_oak", MapColor.DULL_RED, MapColor.DULL_PINK, MapColor.DARK_DULL_PINK, false).build());
	public static final PlantBundle PINK_CHERRY_OAK_SAPLING = bundle(new PlantBundle(new BlockCreator.Builder("pink_cherry_oak_sapling", settings -> new SaplingBlock(new PinkCherryOakSaplingGenerator(), settings), DefaultBlockSettings.SAPLING).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final PlantBundle WHITE_CHERRY_OAK_SAPLING = bundle(new PlantBundle(new BlockCreator.Builder("white_cherry_oak_sapling", settings -> new SaplingBlock(new WhiteCherryOakSaplingGenerator(), settings), DefaultBlockSettings.SAPLING).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final Block PINK_CHERRY_OAK_LEAVES = add(new BlockCreator.Builder("pink_cherry_oak", DefaultBlockTemplates.LEAVES, DefaultBlockSettings.LEAVES).flammability(30, 60).build());
	public static final Block PINK_CHERRY_OAK_LEAF_PILE = add(new BlockCreator.Builder("pink_cherry_oak", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());
	public static final Block WHITE_CHERRY_OAK_LEAVES = add(new BlockCreator.Builder("white_cherry_oak", DefaultBlockTemplates.LEAVES, DefaultBlockSettings.LEAVES).flammability(30, 60).build());
	public static final Block WHITE_CHERRY_OAK_LEAF_PILE = add(new BlockCreator.Builder("white_cherry_oak", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());

	public static final NormalWoodBundle PALM_WOOD = bundle(new NormalWoodBundle.Builder("palm", new PalmSaplingGenerator(), MapColor.ORANGE, MapColor.TERRACOTTA_CYAN).saplingSoil(blockState -> blockState.isIn(BlockTags.SAND)).build());
	public static final Block PALM_LEAF_PILE = add(new BlockCreator.Builder("palm", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());

	public static final Block BLACK_DYLIUM = add(new BlockCreator.Builder("black_dylium", DyliumBlock::new, FabricBlockSettings.of(Material.STONE, MapColor.DULL_RED).requiresTool().strength(3.0F, 9.0F).sounds(BlockSoundGroup.NYLIUM).ticksRandomly()).itemGroup(ItemGroup.BUILDING_BLOCKS).build());
	public static final Block DARK_AMARANTH_WART_BLOCK = add(new BlockCreator.Builder("dark_amaranth_wart_block", Block::new, FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL).breakByTool(FabricToolTags.HOES).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK)).build());
	public static final Block DARK_AMARANTH_ROOTS = add(new BlockCreator.Builder("dark_amaranth_roots", RootsBlock::new, FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).build());
	public static final NetherWoodBundle DARK_AMARANTH_WOOD = bundle(new NetherWoodBundle.Builder("dark_amaranth", () -> PromenadeConfiguredFeatures.AMARANTH_FUNGI_PLANTED, MapColor.LIGHT_GRAY, MapColor.DARK_DULL_PINK).build());

	public static void init() {
	}

	public static class Settings {
		public static final FabricBlockSettings LEAF_PILE = FabricBlockSettings.of(Material.LEAVES).strength(0.1F).ticksRandomly().sounds(BlockSoundGroup.GRASS).noCollision().nonOpaque();
		public static final FabricBlockSettings FLOWER_PILE = FabricBlockSettings.of(Material.PLANT).breakInstantly().sounds(BlockSoundGroup.GRASS).noCollision();
	}

	public static class Templates {
		public static final BlockTemplate LEAF_PILE = new BlockTemplate(PlantPileBlock::new, "leaf_pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED);
		public static final BlockTemplate PLANT_PILE = new BlockTemplate(PlantPileBlock::new, "pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED);
	}
}
