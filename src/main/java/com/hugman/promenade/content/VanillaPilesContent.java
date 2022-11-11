package com.hugman.promenade.content;

import com.hugman.newdawn.DawnFactory;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.PromenadeFactory;
import com.hugman.promenade.object.block.WitherRosePileBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;

public class VanillaPilesContent {
	public static final Block OAK_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("oak_leaf_pile")).get();
	public static final Block SPRUCE_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("spruce_leaf_pile")).get();
	public static final Block BIRCH_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("birch_leaf_pile")).get();
	public static final Block JUNGLE_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("jungle_leaf_pile")).get();
	public static final Block ACACIA_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("acacia_leaf_pile")).get();
	public static final Block DARK_OAK_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("dark_oak_leaf_pile")).get();
	public static final Block MANGROVE_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("mangrove_leaf_pile")).get();
	public static final Block AZALEA_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("azalea_leaf_pile"), BlockSoundGroup.AZALEA_LEAVES).get();
	public static final Block FLOWERING_AZALEA_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("flowering_azalea_leaf_pile"), BlockSoundGroup.AZALEA_LEAVES).get();

	public static final Block DANDELION_PILE = PromenadeFactory.leafPile(Promenade.id("dandelion_pile")).get();
	public static final Block POPPY_PILE = PromenadeFactory.leafPile(Promenade.id("poppy_pile")).get();
	public static final Block BLUE_ORCHID_PILE = PromenadeFactory.leafPile(Promenade.id("blue_orchid_pile")).get();
	public static final Block ALLIUM_PILE = PromenadeFactory.leafPile(Promenade.id("allium_pile")).get();
	public static final Block AZURE_BLUET_PILE = PromenadeFactory.leafPile(Promenade.id("azure_bluet_pile")).get();
	public static final Block RED_TULIP_PILE = PromenadeFactory.leafPile(Promenade.id("red_tulip_pile")).get();
	public static final Block ORANGE_TULIP_PILE = PromenadeFactory.leafPile(Promenade.id("orange_tulip_pile")).get();
	public static final Block WHITE_TULIP_PILE = PromenadeFactory.leafPile(Promenade.id("white_tulip_pile")).get();
	public static final Block PINK_TULIP_PILE = PromenadeFactory.leafPile(Promenade.id("pink_tulip_pile")).get();
	public static final Block OXEYE_DAISY_PILE = PromenadeFactory.leafPile(Promenade.id("oxeye_daisy_pile")).get();
	public static final Block CORNFLOWER_PILE = PromenadeFactory.leafPile(Promenade.id("cornflower_pile")).get();
	public static final Block LILY_OF_THE_VALLEY_PILE = PromenadeFactory.leafPile(Promenade.id("lily_of_the_valley_pile")).get();
	public static final Block WITHER_ROSE_PILE = DawnFactory.block(Promenade.id("wither_rose_pile"), new WitherRosePileBlock(FabricBlockSettings.of(Material.PLANT)
					.strength(0.1f)
					.ticksRandomly()
					.sounds(BlockSoundGroup.GRASS)
					.noCollision()
					.nonOpaque()))
			.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.3f))
			.flammability(30, 60)
			.get();

	public static void init() {};
}
