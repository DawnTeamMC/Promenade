package com.hugman.promenade.content;

import com.hugman.newdawn.DawnBlockSettings;
import com.hugman.newdawn.DawnItemSettings;
import com.hugman.newdawn.RegistryHelper;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.PromenadeFactory;
import com.hugman.promenade.object.block.WitherRosePileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;

public class VanillaPilesContent {
	public static final Block OAK_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block SPRUCE_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block BIRCH_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block JUNGLE_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block ACACIA_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block DARK_OAK_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block MANGROVE_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block AZALEA_LEAF_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);
	public static final Block FLOWERING_AZALEA_LEAF_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);

	public static final Block DANDELION_PILE = PromenadeFactory.leafPile();
	public static final Block POPPY_PILE = PromenadeFactory.leafPile();
	public static final Block BLUE_ORCHID_PILE = PromenadeFactory.leafPile();
	public static final Block ALLIUM_PILE = PromenadeFactory.leafPile();
	public static final Block AZURE_BLUET_PILE = PromenadeFactory.leafPile();
	public static final Block RED_TULIP_PILE = PromenadeFactory.leafPile();
	public static final Block ORANGE_TULIP_PILE = PromenadeFactory.leafPile();
	public static final Block WHITE_TULIP_PILE = PromenadeFactory.leafPile();
	public static final Block PINK_TULIP_PILE = PromenadeFactory.leafPile();
	public static final Block OXEYE_DAISY_PILE = PromenadeFactory.leafPile();
	public static final Block CORNFLOWER_PILE = PromenadeFactory.leafPile();
	public static final Block LILY_OF_THE_VALLEY_PILE = PromenadeFactory.leafPile();
	public static final Block WITHER_ROSE_PILE = new WitherRosePileBlock(DawnBlockSettings.of(Material.PLANT)
			.item(DawnItemSettings.of(ItemGroup.DECORATIONS).compostingChance(0.3f)).flammability(30, 60)
			.strength(0.1f)
			.ticksRandomly()
			.sounds(BlockSoundGroup.GRASS)
			.noCollision()
			.nonOpaque());

	public static void init() {
		RegistryHelper.block(Promenade.id("oak_leaf_pile"), OAK_LEAF_PILE);
		RegistryHelper.block(Promenade.id("spruce_leaf_pile"), SPRUCE_LEAF_PILE);
		RegistryHelper.block(Promenade.id("birch_leaf_pile"), BIRCH_LEAF_PILE);
		RegistryHelper.block(Promenade.id("jungle_leaf_pile"), JUNGLE_LEAF_PILE);
		RegistryHelper.block(Promenade.id("acacia_leaf_pile"), ACACIA_LEAF_PILE);
		RegistryHelper.block(Promenade.id("dark_oak_leaf_pile"), DARK_OAK_LEAF_PILE);
		RegistryHelper.block(Promenade.id("mangrove_leaf_pile"), MANGROVE_LEAF_PILE);
		RegistryHelper.block(Promenade.id("azalea_leaf_pile"), AZALEA_LEAF_PILE);
		RegistryHelper.block(Promenade.id("flowering_azalea_leaf_pile"), FLOWERING_AZALEA_LEAF_PILE);

		RegistryHelper.block(Promenade.id("dandelion_pile"), DANDELION_PILE);
		RegistryHelper.block(Promenade.id("poppy_pile"), POPPY_PILE);
		RegistryHelper.block(Promenade.id("blue_orchid_pile"), BLUE_ORCHID_PILE);
		RegistryHelper.block(Promenade.id("allium_pile"), ALLIUM_PILE);
		RegistryHelper.block(Promenade.id("azure_bluet_pile"), AZURE_BLUET_PILE);
		RegistryHelper.block(Promenade.id("red_tulip_pile"), RED_TULIP_PILE);
		RegistryHelper.block(Promenade.id("orange_tulip_pile"), ORANGE_TULIP_PILE);
		RegistryHelper.block(Promenade.id("white_tulip_pile"), WHITE_TULIP_PILE);
		RegistryHelper.block(Promenade.id("pink_tulip_pile"), PINK_TULIP_PILE);
		RegistryHelper.block(Promenade.id("oxeye_daisy_pile"), OXEYE_DAISY_PILE);
		RegistryHelper.block(Promenade.id("cornflower_pile"), CORNFLOWER_PILE);
		RegistryHelper.block(Promenade.id("lily_of_the_valley_pile"), LILY_OF_THE_VALLEY_PILE);
		RegistryHelper.block(Promenade.id("wither_rose_pile"), WITHER_ROSE_PILE);
	}
}
