package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.block.WitherRosePileBlock;
import fr.hugman.promenade.item.ItemGroupHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroups;
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
			.item(new DawnItemSettings().compostingChance(0.3f)).flammability(30, 60)
			.strength(0.1f)
			.ticksRandomly()
			.sounds(BlockSoundGroup.GRASS)
			.noCollision()
			.nonOpaque());

	public static void init() {
		Registrar.add(Promenade.id("oak_leaf_pile"), OAK_LEAF_PILE);
		Registrar.add(Promenade.id("spruce_leaf_pile"), SPRUCE_LEAF_PILE);
		Registrar.add(Promenade.id("birch_leaf_pile"), BIRCH_LEAF_PILE);
		Registrar.add(Promenade.id("jungle_leaf_pile"), JUNGLE_LEAF_PILE);
		Registrar.add(Promenade.id("acacia_leaf_pile"), ACACIA_LEAF_PILE);
		Registrar.add(Promenade.id("dark_oak_leaf_pile"), DARK_OAK_LEAF_PILE);
		Registrar.add(Promenade.id("mangrove_leaf_pile"), MANGROVE_LEAF_PILE);
		Registrar.add(Promenade.id("azalea_leaf_pile"), AZALEA_LEAF_PILE);
		Registrar.add(Promenade.id("flowering_azalea_leaf_pile"), FLOWERING_AZALEA_LEAF_PILE);

		Registrar.add(Promenade.id("dandelion_pile"), DANDELION_PILE);
		Registrar.add(Promenade.id("poppy_pile"), POPPY_PILE);
		Registrar.add(Promenade.id("blue_orchid_pile"), BLUE_ORCHID_PILE);
		Registrar.add(Promenade.id("allium_pile"), ALLIUM_PILE);
		Registrar.add(Promenade.id("azure_bluet_pile"), AZURE_BLUET_PILE);
		Registrar.add(Promenade.id("red_tulip_pile"), RED_TULIP_PILE);
		Registrar.add(Promenade.id("orange_tulip_pile"), ORANGE_TULIP_PILE);
		Registrar.add(Promenade.id("white_tulip_pile"), WHITE_TULIP_PILE);
		Registrar.add(Promenade.id("pink_tulip_pile"), PINK_TULIP_PILE);
		Registrar.add(Promenade.id("oxeye_daisy_pile"), OXEYE_DAISY_PILE);
		Registrar.add(Promenade.id("cornflower_pile"), CORNFLOWER_PILE);
		Registrar.add(Promenade.id("lily_of_the_valley_pile"), LILY_OF_THE_VALLEY_PILE);
		Registrar.add(Promenade.id("wither_rose_pile"), WITHER_ROSE_PILE);

		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.FLOWERING_AZALEA_LEAVES,
					OAK_LEAF_PILE,
					SPRUCE_LEAF_PILE,
					BIRCH_LEAF_PILE,
					JUNGLE_LEAF_PILE,
					ACACIA_LEAF_PILE,
					DARK_OAK_LEAF_PILE,
					MANGROVE_LEAF_PILE,
					AZALEA_LEAF_PILE,
					FLOWERING_AZALEA_LEAF_PILE);

			e.addAfter(Blocks.LILY_OF_THE_VALLEY,
					DANDELION_PILE,
					POPPY_PILE,
					BLUE_ORCHID_PILE,
					ALLIUM_PILE,
					AZURE_BLUET_PILE,
					RED_TULIP_PILE,
					ORANGE_TULIP_PILE,
					WHITE_TULIP_PILE,
					PINK_TULIP_PILE,
					OXEYE_DAISY_PILE,
					CORNFLOWER_PILE,
					LILY_OF_THE_VALLEY_PILE);
			e.addAfter(Blocks.WITHER_ROSE, WITHER_ROSE_PILE);
		});
	}
}
