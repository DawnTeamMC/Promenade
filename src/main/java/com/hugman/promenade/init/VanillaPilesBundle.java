package com.hugman.promenade.init;

import com.hugman.promenade.object.block.WitherRosePileBlock;
import com.hugman.promenade.util.PromenadeBlockBuilders;
import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;

public class VanillaPilesBundle extends PromenadeBundle {
	public static final Block OAK_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("oak_leaf_pile").build());
	public static final Block SPRUCE_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("spruce_leaf_pile").build());
	public static final Block BIRCH_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("birch_leaf_pile").build());
	public static final Block JUNGLE_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("jungle_leaf_pile").build());
	public static final Block ACACIA_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("acacia_leaf_pile").build());
	public static final Block DARK_OAK_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("dark_oak_leaf_pile").build());
	public static final Block MANGROVE_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("mangrove_leaf_pile").build());
	public static final Block AZALEA_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("azalea_leaf_pile", BlockSoundGroup.AZALEA_LEAVES).build());
	public static final Block FLOWERING_AZALEA_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("flowering_azalea_leaf_pile", BlockSoundGroup.AZALEA_LEAVES).build());

	public static final Block DANDELION_PILE = add(PromenadeBlockBuilders.plantPile("dandelion_pile").build());
	public static final Block POPPY_PILE = add(PromenadeBlockBuilders.plantPile("poppy_pile").build());
	public static final Block BLUE_ORCHID_PILE = add(PromenadeBlockBuilders.plantPile("blue_orchid_pile").build());
	public static final Block ALLIUM_PILE = add(PromenadeBlockBuilders.plantPile("allium_pile").build());
	public static final Block AZURE_BLUET_PILE = add(PromenadeBlockBuilders.plantPile("azure_bluet_pile").build());
	public static final Block RED_TULIP_PILE = add(PromenadeBlockBuilders.plantPile("red_tulip_pile").build());
	public static final Block ORANGE_TULIP_PILE = add(PromenadeBlockBuilders.plantPile("orange_tulip_pile").build());
	public static final Block WHITE_TULIP_PILE = add(PromenadeBlockBuilders.plantPile("white_tulip_pile").build());
	public static final Block PINK_TULIP_PILE = add(PromenadeBlockBuilders.plantPile("pink_tulip_pile").build());
	public static final Block OXEYE_DAISY_PILE = add(PromenadeBlockBuilders.plantPile("oxeye_daisy_pile").build());
	public static final Block CORNFLOWER_PILE = add(PromenadeBlockBuilders.plantPile("cornflower_pile").build());
	public static final Block LILY_OF_THE_VALLEY_PILE = add(PromenadeBlockBuilders.plantPile("lily_of_the_valley_pile").build());
	public static final Block WITHER_ROSE_PILE = add(PromenadeBlockBuilders.plantPile("wither_rose_pile").provider(WitherRosePileBlock::new).build());
}
