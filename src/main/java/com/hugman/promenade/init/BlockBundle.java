package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.block.MTBlockBundle;
import com.hugman.dawn.api.creator.bundle.block.NetherWoodBundle;
import com.hugman.dawn.api.creator.bundle.block.OverworldWoodBundle;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.creator.bundle.block.WoodBundle;
import com.hugman.dawn.api.object.block.FertilizableMushroomPlantBlock;
import com.hugman.dawn.api.object.block.PlantPileBlock;
import com.hugman.dawn.api.object.block.RootsBlock;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.BlockTemplate;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import com.hugman.promenade.init.world.feature.PromenadeConfiguredFeatures;
import com.hugman.promenade.object.block.BlueberryBushBlock;
import com.hugman.promenade.object.block.DyliumBlock;
import com.hugman.promenade.object.block.WitherRosePileBlock;
import com.hugman.promenade.object.block.sapling_generator.AutumnBirchSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.AutumnOakSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.PalmSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.PinkCherryOakSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.WhiteCherryOakSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.DyeColor;

public class BlockBundle extends PromenadeBundle {
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

	public static final Block BLUEBERRY_BUSH = add(new BlockCreator.Builder("blueberry_bush", BlueberryBushBlock::new, FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)).flammability(60, 100).render(BlockCreator.Render.CUTOUT).noItem().build());

	public static final OverworldWoodBundle PALM_WOOD = bundle(new OverworldWoodBundle.Builder("palm", new PalmSaplingGenerator(), MapColor.ORANGE, MapColor.TERRACOTTA_CYAN).saplingSoil(blockState -> blockState.isIn(BlockTags.SAND)).build());
	public static final Block PALM_LEAF_PILE = add(new BlockCreator.Builder("palm", Templates.LEAF_PILE, Settings.LEAF_PILE).flammability(30, 60).build());

	public static final Block BLACK_DYLIUM = add(new BlockCreator.Builder("black_dylium", DyliumBlock::new, FabricBlockSettings.of(Material.STONE, MapColor.DULL_RED).requiresTool().strength(3.0F, 9.0F).sounds(BlockSoundGroup.NYLIUM).ticksRandomly()).itemGroup(ItemGroup.BUILDING_BLOCKS).build());
	public static final Block DARK_AMARANTH_WART_BLOCK = add(new BlockCreator.Builder("dark_amaranth_wart_block", Block::new, FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK)).build());
	public static final Block DARK_AMARANTH_ROOTS = add(new BlockCreator.Builder("dark_amaranth_roots", RootsBlock::new, FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F).build());
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
