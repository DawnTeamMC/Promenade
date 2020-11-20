package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.pack.block.*;
import com.hugman.dawn.api.object.block.FertilizableMushroomPlantBlock;
import com.hugman.dawn.api.object.block.RootsBlock;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.BlockSettings;
import com.hugman.dawn.api.util.DefaultBlockGetter;
import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import com.hugman.wild_explorer.object.block.BlueberryBushBlock;
import com.hugman.wild_explorer.object.block.DyliumBlock;
import com.hugman.wild_explorer.object.block.WitherRosePileBlock;
import com.hugman.wild_explorer.object.block.sapling_generator.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.DyeColor;

public class WEBlocks extends WEPack {
	public static final Block OAK_LEAF_PILE = register(new BlockCreator.Builder("oak", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60));
	public static final Block SPRUCE_LEAF_PILE = register(new BlockCreator.Builder("spruce", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60));
	public static final Block BIRCH_LEAF_PILE = register(new BlockCreator.Builder("birch", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60));
	public static final Block JUNGLE_LEAF_PILE = register(new BlockCreator.Builder("jungle", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60));
	public static final Block ACACIA_LEAF_PILE = register(new BlockCreator.Builder("acacia", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60));
	public static final Block DARK_OAK_LEAF_PILE = register(new BlockCreator.Builder("dark_oak", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60));

	public static final Block DANDELION_PILE = register(new BlockCreator.Builder("dandelion", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block POPPY_PILE = register(new BlockCreator.Builder("poppy", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block BLUE_ORCHID_PILE = register(new BlockCreator.Builder("blue_orchid", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block ALLIUM_PILE = register(new BlockCreator.Builder("allium", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block AZURE_BLUET_PILE = register(new BlockCreator.Builder("azure_bluet", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block RED_TULIP_PILE = register(new BlockCreator.Builder("red_tulip", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block ORANGE_TULIP_PILE = register(new BlockCreator.Builder("orange_tulip", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block WHITE_TULIP_PILE = register(new BlockCreator.Builder("white_tulip", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block PINK_TULIP_PILE = register(new BlockCreator.Builder("pink_tulip", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block OXEYE_DAISY_PILE = register(new BlockCreator.Builder("oxeye_daisy", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block CORNFLOWER_PILE = register(new BlockCreator.Builder("cornflower", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block LILY_OF_THE_VALLEY_PILE = register(new BlockCreator.Builder("lily_of_the_valley", DefaultBlockGetter.PLANT_PILE, BlockSettings.FLOWER_PILE).flammability(60, 100));
	public static final Block WITHER_ROSE_PILE = register(new BlockCreator.Builder("wither_rose_pile", new WitherRosePileBlock(BlockSettings.FLOWER_PILE)).itemGroup(ItemGroup.DECORATIONS).flammability(60, 100).render(BlockCreator.Render.CUTOUT_MIPPED));

	public static final Block WHITE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("white", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.WHITE)));
	public static final Block LIGHT_GRAY_MUSHROOM_BLOCK = register(new BlockCreator.Builder("light_gray", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.LIGHT_GRAY)));
	public static final Block GRAY_MUSHROOM_BLOCK = register(new BlockCreator.Builder("gray", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.GRAY)));
	public static final Block BLACK_MUSHROOM_BLOCK = register(new BlockCreator.Builder("black", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.BLACK)));
	public static final Block ORANGE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("orange", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.ORANGE)));
	public static final Block YELLOW_MUSHROOM_BLOCK = register(new BlockCreator.Builder("yellow", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.YELLOW)));
	public static final Block LIME_MUSHROOM_BLOCK = register(new BlockCreator.Builder("lime", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.LIME)));
	public static final Block GREEN_MUSHROOM_BLOCK = register(new BlockCreator.Builder("green", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.GREEN)));
	public static final Block CYAN_MUSHROOM_BLOCK = register(new BlockCreator.Builder("cyan", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.CYAN)));
	public static final Block LIGHT_BLUE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("light_blue", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.LIGHT_BLUE)));
	public static final Block BLUE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("blue", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.BLUE)));
	public static final Block PURPLE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("purple", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.PURPLE)));
	public static final Block MAGENTA_MUSHROOM_BLOCK = register(new BlockCreator.Builder("magenta", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.MAGENTA)));
	public static final Block PINK_MUSHROOM_BLOCK = register(new BlockCreator.Builder("pink", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.PINK)));

	public static final PottedPlantPack WHITE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("white_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, WHITE_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack LIGHT_GRAY_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("light_gray_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, LIGHT_GRAY_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack GRAY_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("gray_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, GRAY_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack BLACK_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("black_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, BLACK_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack ORANGE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("orange_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, ORANGE_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack YELLOW_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("yellow_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, YELLOW_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack LIME_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("lime_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, LIME_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack GREEN_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("green_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, GREEN_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack CYAN_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("cyan_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, CYAN_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack LIGHT_BLUE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("light_blue_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, LIGHT_BLUE_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack BLUE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("blue_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, BLUE_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack PURPLE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("purple_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, PURPLE_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack MAGENTA_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("magenta_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, MAGENTA_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));
	public static final PottedPlantPack PINK_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("pink_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, PINK_MUSHROOM_BLOCK)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F)));

	public static final Block BLUEBERRY_BUSH = register(new BlockCreator.Builder("blueberry_bush", new BlueberryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH))).flammability(60, 100).render(BlockCreator.Render.CUTOUT).noItem());

	public static final MSBlockPack BLUNITE_BLOCKS = register(new MSBlockPack.Builder("blunite", FabricBlockSettings.copyOf(Blocks.ANDESITE).materialColor(MaterialColor.LIGHT_BLUE_TERRACOTTA), DefaultBlockGetter.CUBE, DefaultBlockGetter.STAIRS, DefaultBlockGetter.SLAB, DefaultBlockGetter.WALL));
	public static final MSBlockPack CARBONITE_BLOCKS = register(new MSBlockPack.Builder("carbonite", FabricBlockSettings.copyOf(Blocks.ANDESITE).materialColor(MaterialColor.BLACK), DefaultBlockGetter.CUBE, DefaultBlockGetter.STAIRS, DefaultBlockGetter.SLAB, DefaultBlockGetter.WALL));
	public static final MSBlockPack POLISHED_BLUNITE = register(new MSBlockPack.Builder("polished_blunite", FabricBlockSettings.copyOf(BLUNITE_BLOCKS.getBlock(DefaultBlockGetter.CUBE)), DefaultBlockGetter.CUBE, DefaultBlockGetter.STAIRS, DefaultBlockGetter.SLAB));
	public static final MSBlockPack POLISHED_CARBONITE = register(new MSBlockPack.Builder("polished_carbonite", FabricBlockSettings.copyOf(CARBONITE_BLOCKS.getBlock(DefaultBlockGetter.CUBE)), DefaultBlockGetter.CUBE, DefaultBlockGetter.STAIRS, DefaultBlockGetter.SLAB));

	public static final PottedPlantPack AUTUMN_OAK_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("autumn_oak_sapling", new SaplingBlock(new AutumnOakSaplingGenerator(), BlockSettings.SAPLING)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final LeavesPack AUTUMN_OAK_LEAVES = register(new LeavesPack.Builder("autumn_oak"));
	public static final PottedPlantPack AUTUMN_BIRCH_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("autumn_birch_sapling", new SaplingBlock(new AutumnBirchSaplingGenerator(), BlockSettings.SAPLING)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final LeavesPack AUTUMN_BIRCH_LEAVES = register(new LeavesPack.Builder("autumn_birch"));

	public static final WoodPack CHERRY_OAK_WOOD = register(new WoodPack.Builder("cherry_oak", MaterialColor.field_25702, MaterialColor.field_25703, MaterialColor.field_25707, false));
	public static final PottedPlantPack PINK_CHERRY_OAK_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("pink_cherry_oak_sapling", new SaplingBlock(new PinkCherryOakSaplingGenerator(), BlockSettings.SAPLING)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack WHITE_CHERRY_OAK_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("white_cherry_oak_sapling", new SaplingBlock(new WhiteCherryOakSaplingGenerator(), BlockSettings.SAPLING)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT)));
	public static final LeavesPack PINK_CHERRY_OAK_LEAVES = register(new LeavesPack.Builder("pink_cherry_oak"));
	public static final LeavesPack WHITE_CHERRY_OAK_LEAVES = register(new LeavesPack.Builder("white_cherry_oak"));

	public static final NormalWoodPack PALM_WOOD = register(new NormalWoodPack.Builder("palm", new PalmSaplingGenerator(), MaterialColor.ORANGE, MaterialColor.CYAN_TERRACOTTA).saplingSoil(blockState -> blockState.isIn(BlockTags.SAND)));

	public static final Block BLACK_DYLIUM = register(new BlockCreator.Builder("black_dylium", new DyliumBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.field_25702).requiresTool().strength(3.0F, 9.0F).sounds(BlockSoundGroup.NYLIUM).ticksRandomly())).itemGroup(ItemGroup.BUILDING_BLOCKS));
	public static final Block DARK_AMARANTH_WART_BLOCK = register(new BlockCreator.Builder("dark_amaranth_wart_block", new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC, MaterialColor.field_25708).breakByTool(FabricToolTags.HOES).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK))));
	public static final Block DARK_AMARANTH_ROOTS = register(new BlockCreator.Builder("dark_amaranth_roots", new RootsBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MaterialColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS))).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT));
	public static final NetherWoodPack DARK_AMARANTH_WOOD = register(new NetherWoodPack.Builder("dark_amaranth", () -> WEConfiguredFeatures.AMARANTH_FUNGI_PLANTED, MaterialColor.LIGHT_GRAY, MaterialColor.field_25707));

	public static void init() {
	}
}
