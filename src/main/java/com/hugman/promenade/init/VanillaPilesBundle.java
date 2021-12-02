package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.promenade.object.block.WitherRosePileBlock;
import com.hugman.promenade.util.BlockSettingsUtil;
import com.hugman.promenade.util.BlockTemplateUtil;
import net.minecraft.block.Block;

public class VanillaPilesBundle extends PromenadeBundle {
	public static final Block OAK_LEAF_PILE = add(new BlockCreator.Builder("oak", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());
	public static final Block SPRUCE_LEAF_PILE = add(new BlockCreator.Builder("spruce", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());
	public static final Block BIRCH_LEAF_PILE = add(new BlockCreator.Builder("birch", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());
	public static final Block JUNGLE_LEAF_PILE = add(new BlockCreator.Builder("jungle", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());
	public static final Block ACACIA_LEAF_PILE = add(new BlockCreator.Builder("acacia", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());
	public static final Block DARK_OAK_LEAF_PILE = add(new BlockCreator.Builder("dark_oak", BlockTemplateUtil.LEAF_PILE, BlockSettingsUtil.LEAF_PILE).flammability(30, 60).build());

	public static final Block DANDELION_PILE = add(new BlockCreator.Builder("dandelion", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block POPPY_PILE = add(new BlockCreator.Builder("poppy", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block BLUE_ORCHID_PILE = add(new BlockCreator.Builder("blue_orchid", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block ALLIUM_PILE = add(new BlockCreator.Builder("allium", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block AZURE_BLUET_PILE = add(new BlockCreator.Builder("azure_bluet", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block RED_TULIP_PILE = add(new BlockCreator.Builder("red_tulip", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block ORANGE_TULIP_PILE = add(new BlockCreator.Builder("orange_tulip", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block WHITE_TULIP_PILE = add(new BlockCreator.Builder("white_tulip", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block PINK_TULIP_PILE = add(new BlockCreator.Builder("pink_tulip", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block OXEYE_DAISY_PILE = add(new BlockCreator.Builder("oxeye_daisy", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block CORNFLOWER_PILE = add(new BlockCreator.Builder("cornflower", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block LILY_OF_THE_VALLEY_PILE = add(new BlockCreator.Builder("lily_of_the_valley", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).flammability(60, 100).build());
	public static final Block WITHER_ROSE_PILE = add(new BlockCreator.Builder("wither_rose", BlockTemplateUtil.PLANT_PILE, BlockSettingsUtil.FLOWER_PILE).blockProvider(WitherRosePileBlock::new).flammability(60, 100).build());
}
