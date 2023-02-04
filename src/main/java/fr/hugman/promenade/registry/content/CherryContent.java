package fr.hugman.promenade.registry.content;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.block.sapling.OakLikeSaplingGenerator;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.item.ItemGroupHelper;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;

public class CherryContent {
	private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
	private static final MapColor WOOD_COLOR = MapColor.DULL_PINK;
	private static final MapColor PLANKS_COLOR = MapColor.DULL_RED;

	public static final Block STRIPPED_CHERRY_OAK_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block CHERRY_OAK_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR, BARK_COLOR).stripInto(STRIPPED_CHERRY_OAK_LOG));
	public static final Block STRIPPED_CHERRY_OAK_WOOD = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block CHERRY_OAK_WOOD = new PillarBlock(DawnFactory.logSettings(false, BARK_COLOR).stripInto(STRIPPED_CHERRY_OAK_WOOD));

	public static final Block CHERRY_OAK_PLANKS = DawnFactory.planks(false, PLANKS_COLOR);
	public static final Block CHERRY_OAK_STAIRS = DawnFactory.stairs(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_SLAB = DawnFactory.slab(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_FENCE = DawnFactory.fence(false, CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_FENCE_GATE = DawnFactory.fenceGate(false, CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_DOOR = DawnFactory.woodenDoor(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_TRAPDOOR = DawnFactory.woodenTrapdoor(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_BUTTON = DawnFactory.woodenButton(false);
	public static final Block CHERRY_OAK_PRESSURE_PLATE = DawnFactory.pressurePlate(CHERRY_OAK_PLANKS);

	public static final SignBlocks CHERRY_OAK_SIGNS = DawnFactory.signs(Promenade.id("cherry_oak"), CHERRY_OAK_PLANKS);
	public static final TerraformBoatType CHERRY_OAK_BOAT_TYPE = DawnFactory.boat(Promenade.id("cherry_oak"), Items.OAK_PLANKS); //TODO change when possible (PR #72 on TerraformersMC/Terraform)

	public static final Block PINK_CHERRY_OAK_SAPLING = DawnFactory.sapling(OakLikeSaplingGenerator.of(Promenade.id("cherry_oak/pink")));
	public static final Block POTTED_PINK_CHERRY_OAK_SAPLING = DawnFactory.potted(PINK_CHERRY_OAK_SAPLING);
	public static final Block PINK_CHERRY_OAK_LEAVES = DawnFactory.leaves(BlockSoundGroup.AZALEA_LEAVES);
	public static final Block PINK_CHERRY_OAK_LEAF_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);
	public static final DefaultParticleType PINK_CHERRY_BLOSSOM = FabricParticleTypes.simple();

	public static final Block WHITE_CHERRY_OAK_SAPLING = DawnFactory.sapling(OakLikeSaplingGenerator.of(Promenade.id("cherry_oak/white")));
	public static final Block POTTED_WHITE_CHERRY_OAK_SAPLING = DawnFactory.potted(WHITE_CHERRY_OAK_SAPLING);
	public static final Block WHITE_CHERRY_OAK_LEAVES = DawnFactory.leaves(BlockSoundGroup.AZALEA_LEAVES);
	public static final Block WHITE_CHERRY_OAK_LEAF_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);
	public static final DefaultParticleType WHITE_CHERRY_BLOSSOM = FabricParticleTypes.simple();

	public static final RegistryKey<Biome> PINK_CHERRY_OAK_FOREST = DawnFactory.biome(Promenade.id("pink_cherry_oak_forest"));
	public static final RegistryKey<Biome> WHITE_CHERRY_OAK_FOREST = DawnFactory.biome(Promenade.id("white_cherry_oak_forest"));

	public static void register(Registrar r) {
		r.add(("cherry_oak_log"), CHERRY_OAK_LOG);
		r.add(("stripped_cherry_oak_log"), STRIPPED_CHERRY_OAK_LOG);
		r.add(("cherry_oak_wood"), CHERRY_OAK_WOOD);
		r.add(("stripped_cherry_oak_wood"), STRIPPED_CHERRY_OAK_WOOD);

		r.add(("cherry_oak_planks"), CHERRY_OAK_PLANKS);
		r.add(("cherry_oak_stairs"), CHERRY_OAK_STAIRS);
		r.add(("cherry_oak_slab"), CHERRY_OAK_SLAB);
		r.add(("cherry_oak_fence"), CHERRY_OAK_FENCE);
		r.add(("cherry_oak_fence_gate"), CHERRY_OAK_FENCE_GATE);
		r.add(("cherry_oak_door"), CHERRY_OAK_DOOR);
		r.add(("cherry_oak_trapdoor"), CHERRY_OAK_TRAPDOOR);
		r.add(("cherry_oak_button"), CHERRY_OAK_BUTTON);
		r.add(("cherry_oak_pressure_plate"), CHERRY_OAK_PRESSURE_PLATE);

		r.add(("cherry_oak"), CHERRY_OAK_SIGNS);
		r.add(("cherry_oak"), CHERRY_OAK_BOAT_TYPE);

		r.add(("pink_cherry_oak_sapling"), PINK_CHERRY_OAK_SAPLING);
		r.add(("potted_pink_cherry_oak_sapling"), POTTED_PINK_CHERRY_OAK_SAPLING);
		r.add(("pink_cherry_oak_leaves"), PINK_CHERRY_OAK_LEAVES);
		r.add(("pink_cherry_oak_leaf_pile"), PINK_CHERRY_OAK_LEAF_PILE);
		r.add(("pink_cherry_blossom"), PINK_CHERRY_BLOSSOM);

		r.add(("white_cherry_oak_sapling"), WHITE_CHERRY_OAK_SAPLING);
		r.add(("potted_white_cherry_oak_sapling"), POTTED_WHITE_CHERRY_OAK_SAPLING);
		r.add(("white_cherry_oak_leaves"), WHITE_CHERRY_OAK_LEAVES);
		r.add(("white_cherry_oak_leaf_pile"), WHITE_CHERRY_OAK_LEAF_PILE);
		r.add(("white_cherry_blossom"), WHITE_CHERRY_BLOSSOM);

		if(Promenade.CONFIG.biomes.cherry_oak_forests_weight > 0) {
			//TODO : add back to overworld generation when the Biome API supports that
			//OverworldBiomes.addContinentalBiome(PINK_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D)
			//OverworldBiomes.addContinentalBiome(WHITE_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D);
		}

		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(PINK_CHERRY_OAK_SAPLING));
			factories.add(TradeOfferUtils.sapling(WHITE_CHERRY_OAK_SAPLING));
		});


		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.BIRCH_BUTTON,
				CHERRY_OAK_LOG,
				CHERRY_OAK_WOOD,
				STRIPPED_CHERRY_OAK_LOG,
				STRIPPED_CHERRY_OAK_WOOD,
				CHERRY_OAK_PLANKS,
				CHERRY_OAK_STAIRS,
				CHERRY_OAK_SLAB,
				CHERRY_OAK_FENCE,
				CHERRY_OAK_FENCE_GATE,
				CHERRY_OAK_DOOR,
				CHERRY_OAK_TRAPDOOR,
				CHERRY_OAK_PRESSURE_PLATE,
				CHERRY_OAK_BUTTON));

		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.BIRCH_LOG, CHERRY_OAK_LOG);
			e.addAfter(Blocks.BIRCH_LEAVES, PINK_CHERRY_OAK_LEAVES, WHITE_CHERRY_OAK_LEAVES);
			e.addAfter(Blocks.BIRCH_SAPLING, PINK_CHERRY_OAK_SAPLING, WHITE_CHERRY_OAK_SAPLING);
			e.addAfter(VanillaPilesContent.BIRCH_LEAF_PILE, PINK_CHERRY_OAK_LEAF_PILE, WHITE_CHERRY_OAK_LEAF_PILE);
		});
		ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.BIRCH_HANGING_SIGN, CHERRY_OAK_SIGNS.sign(), CHERRY_OAK_SIGNS.hangingSign()));
		ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.BIRCH_CHEST_BOAT, CHERRY_OAK_BOAT_TYPE.getItem(), CHERRY_OAK_BOAT_TYPE.getChestItem()));
	}
}
