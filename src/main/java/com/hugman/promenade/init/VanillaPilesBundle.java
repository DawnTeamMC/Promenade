package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import com.hugman.promenade.object.block.WitherRosePileBlock;
import com.hugman.promenade.util.BlockSettingsUtil;
import com.hugman.promenade.util.BlockTemplateUtil;
import net.minecraft.block.Block;

public class VanillaPilesBundle extends PromenadeBundle {
	private static final BlockCreator.Builder LEAF_PILE_BUILDER = new BlockCreator.Builder().applyTemplate(BlockTemplateUtil.LEAF_PILE).settings(BlockSettingsUtil.LEAF_PILE).compostingChance(0.3f).flammability(30, 60);

	public static final Block OAK_LEAF_PILE = add(LEAF_PILE_BUILDER.name("oak").build());
	public static final Block SPRUCE_LEAF_PILE = add(LEAF_PILE_BUILDER.name("spruce").build());
	public static final Block BIRCH_LEAF_PILE = add(LEAF_PILE_BUILDER.name("birch").build());
	public static final Block JUNGLE_LEAF_PILE = add(LEAF_PILE_BUILDER.name("jungle").build());
	public static final Block ACACIA_LEAF_PILE = add(LEAF_PILE_BUILDER.name("acacia").build());
	public static final Block DARK_OAK_LEAF_PILE = add(LEAF_PILE_BUILDER.name("dark_oak").build());

	private static final BlockCreator.Builder PLANT_PILE_BUILDER = new BlockCreator.Builder().applyTemplate(BlockTemplateUtil.PLANT_PILE).settings(BlockSettingsUtil.FLOWER_PILE).compostingChance(0.65f).flammability(60, 100);

	public static final Block DANDELION_PILE = add(PLANT_PILE_BUILDER.name("dandelion").build());
	public static final Block POPPY_PILE = add(PLANT_PILE_BUILDER.name("poppy").build());
	public static final Block BLUE_ORCHID_PILE = add(PLANT_PILE_BUILDER.name("blue_orchid").build());
	public static final Block ALLIUM_PILE = add(PLANT_PILE_BUILDER.name("allium").build());
	public static final Block AZURE_BLUET_PILE = add(PLANT_PILE_BUILDER.name("azure_bluet").build());
	public static final Block RED_TULIP_PILE = add(PLANT_PILE_BUILDER.name("red_tulip").build());
	public static final Block ORANGE_TULIP_PILE = add(PLANT_PILE_BUILDER.name("orange_tulip").build());
	public static final Block WHITE_TULIP_PILE = add(PLANT_PILE_BUILDER.name("white_tulip").build());
	public static final Block PINK_TULIP_PILE = add(PLANT_PILE_BUILDER.name("pink_tulip").build());
	public static final Block OXEYE_DAISY_PILE = add(PLANT_PILE_BUILDER.name("oxeye_daisy").build());
	public static final Block CORNFLOWER_PILE = add(PLANT_PILE_BUILDER.name("cornflower").build());
	public static final Block LILY_OF_THE_VALLEY_PILE = add(PLANT_PILE_BUILDER.name("lily_of_the_valley").build());
	public static final Block WITHER_ROSE_PILE = add(PLANT_PILE_BUILDER.name("wither_rose").blockProvider(WitherRosePileBlock::new).build());
}
