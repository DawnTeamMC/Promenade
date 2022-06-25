package com.hugman.promenade.init;

import com.hugman.promenade.object.block.WitherRosePileBlock;
import com.hugman.promenade.util.BlockBuilders;
import net.minecraft.block.Block;

public class VanillaPilesBundle extends PromenadeBundle {
	public static final Block OAK_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("oak_leaf_pile").build());
	public static final Block SPRUCE_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("spruce_leaf_pile").build());
	public static final Block BIRCH_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("birch_leaf_pile").build());
	public static final Block JUNGLE_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("jungle_leaf_pile").build());
	public static final Block ACACIA_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("acacia_leaf_pile").build());
	public static final Block DARK_OAK_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("dark_oak_leaf_pile").build());
	public static final Block MANGROVE_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("mangrove_leaf_pile").build());
	public static final Block AZALEA_LEAF_PILE = add(BlockBuilders.AZALEA_LEAF_PILE.copy("azalea_leaf_pile").build());
	public static final Block FLOWERING_AZALEA_LEAF_PILE = add(BlockBuilders.AZALEA_LEAF_PILE.copy("flowering_azalea_leaf_pile").build());

	public static final Block DANDELION_PILE = add(BlockBuilders.PLANT_PILE.copy("dandelion_pile").build());
	public static final Block POPPY_PILE = add(BlockBuilders.PLANT_PILE.copy("poppy_pile").build());
	public static final Block BLUE_ORCHID_PILE = add(BlockBuilders.PLANT_PILE.copy("blue_orchid_pile").build());
	public static final Block ALLIUM_PILE = add(BlockBuilders.PLANT_PILE.copy("allium_pile").build());
	public static final Block AZURE_BLUET_PILE = add(BlockBuilders.PLANT_PILE.copy("azure_bluet_pile").build());
	public static final Block RED_TULIP_PILE = add(BlockBuilders.PLANT_PILE.copy("red_tulip_pile").build());
	public static final Block ORANGE_TULIP_PILE = add(BlockBuilders.PLANT_PILE.copy("orange_tulip_pile").build());
	public static final Block WHITE_TULIP_PILE = add(BlockBuilders.PLANT_PILE.copy("white_tulip_pile").build());
	public static final Block PINK_TULIP_PILE = add(BlockBuilders.PLANT_PILE.copy("pink_tulip_pile").build());
	public static final Block OXEYE_DAISY_PILE = add(BlockBuilders.PLANT_PILE.copy("oxeye_daisy_pile").build());
	public static final Block CORNFLOWER_PILE = add(BlockBuilders.PLANT_PILE.copy("cornflower_pile").build());
	public static final Block LILY_OF_THE_VALLEY_PILE = add(BlockBuilders.PLANT_PILE.copy("lily_of_the_valley_pile").build());
	public static final Block WITHER_ROSE_PILE = add(BlockBuilders.PLANT_PILE.copy("wither_rose_pile").provider(WitherRosePileBlock::new).build());
}
