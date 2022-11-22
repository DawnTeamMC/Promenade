package fr.hugman.promenade.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.block.MapleLogBlock;
import fr.hugman.promenade.block.StrippedMapleLogBlock;
import fr.hugman.promenade.block.sapling.SimpleSaplingGenerator;
import fr.hugman.promenade.item.ItemGroupHelper;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class MapleContent {
	private static final MapColor BARK_COLOR = MapColor.ORANGE;
	private static final MapColor WOOD_COLOR = MapColor.ORANGE;

	public static final Block STRIPPED_MAPLE_LOG = new StrippedMapleLogBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block MAPLE_LOG = new MapleLogBlock(DawnFactory.logSettings(false, WOOD_COLOR, BARK_COLOR).stripInto(STRIPPED_MAPLE_LOG));
	public static final Block STRIPPED_MAPLE_WOOD = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block MAPLE_WOOD = new PillarBlock(DawnFactory.logSettings(false, BARK_COLOR).stripInto(STRIPPED_MAPLE_WOOD));

	public static final Block MAPLE_PLANKS = DawnFactory.planks(false, WOOD_COLOR);
	public static final Block MAPLE_STAIRS = DawnFactory.stairs(MAPLE_PLANKS);
	public static final Block MAPLE_SLAB = DawnFactory.slab(MAPLE_PLANKS);
	public static final Block MAPLE_FENCE = DawnFactory.fence(false, MAPLE_PLANKS);
	public static final Block MAPLE_FENCE_GATE = DawnFactory.fenceGate(false, MAPLE_PLANKS);
	public static final Block MAPLE_DOOR = DawnFactory.woodenDoor(MAPLE_PLANKS);
	public static final Block MAPLE_TRAPDOOR = DawnFactory.woodenTrapdoor(MAPLE_PLANKS);
	public static final Block MAPLE_BUTTON = DawnFactory.woodenButton(false);
	public static final Block MAPLE_PRESSURE_PLATE = DawnFactory.pressurePlate(MAPLE_PLANKS);

	public static final Item MAPLE_SYRUP_BOTTLE = new HoneyBottleItem(new DawnItemSettings()
			.maxCount(16)
			.food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
			.recipeRemainder(Items.GLASS_BOTTLE));

	public static final Block SAP_MAPLE_SAPLING = DawnFactory.sapling(new SimpleSaplingGenerator("maple/sap", "002"));
	public static final Block POTTED_SAP_MAPLE_SAPLING = DawnFactory.potted(SAP_MAPLE_SAPLING);
	public static final Block SAP_MAPLE_LEAVES = DawnFactory.leaves();
	public static final Block SAP_MAPLE_LEAF_PILE = PromenadeFactory.leafPile();

	public static final Block VERMILION_MAPLE_SAPLING = DawnFactory.sapling(new SimpleSaplingGenerator("maple/vermilion", "002"));
	public static final Block POTTED_VERMILION_MAPLE_SAPLING = DawnFactory.potted(VERMILION_MAPLE_SAPLING);
	public static final Block VERMILION_MAPLE_LEAVES = DawnFactory.leaves();
	public static final Block VERMILION_MAPLE_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block VERMILION_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock();

	public static final Block FULVOUS_MAPLE_SAPLING = DawnFactory.sapling(new SimpleSaplingGenerator("maple/fulvous", "002"));
	public static final Block POTTED_FULVOUS_MAPLE_SAPLING = DawnFactory.potted(FULVOUS_MAPLE_SAPLING);
	public static final Block FULVOUS_MAPLE_LEAVES = DawnFactory.leaves();
	public static final Block FULVOUS_MAPLE_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block FULVOUS_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock();

	public static final Block MIKADO_MAPLE_SAPLING = DawnFactory.sapling(new SimpleSaplingGenerator("maple/mikado", "002"));
	public static final Block POTTED_MIKADO_MAPLE_SAPLING = DawnFactory.potted(MIKADO_MAPLE_SAPLING);
	public static final Block MIKADO_MAPLE_LEAVES = DawnFactory.leaves();
	public static final Block MIKADO_MAPLE_LEAF_PILE = PromenadeFactory.leafPile();
	public static final Block MIKADO_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock();

	public static final DefaultParticleType MAPLE_LEAF = FabricParticleTypes.simple();

	public static final RegistryKey<Biome> CARNELIAN_TREEWAY = DawnFactory.biome(Promenade.id("carnelian_treeway"));

	public static void init() {
		Registrar.add(Promenade.id("maple_log"), MAPLE_LOG);
		Registrar.add(Promenade.id("stripped_maple_log"), STRIPPED_MAPLE_LOG);
		Registrar.add(Promenade.id("maple_wood"), MAPLE_WOOD);
		Registrar.add(Promenade.id("stripped_maple_wood"), STRIPPED_MAPLE_WOOD);

		Registrar.add(Promenade.id("maple_planks"), MAPLE_PLANKS);
		Registrar.add(Promenade.id("maple_stairs"), MAPLE_STAIRS);
		Registrar.add(Promenade.id("maple_slab"), MAPLE_SLAB);
		Registrar.add(Promenade.id("maple_fence"), MAPLE_FENCE);
		Registrar.add(Promenade.id("maple_fence_gate"), MAPLE_FENCE_GATE);
		Registrar.add(Promenade.id("maple_door"), MAPLE_DOOR);
		Registrar.add(Promenade.id("maple_trapdoor"), MAPLE_TRAPDOOR);
		Registrar.add(Promenade.id("maple_button"), MAPLE_BUTTON);
		Registrar.add(Promenade.id("maple_pressure_plate"), MAPLE_PRESSURE_PLATE);

		Registrar.add(Promenade.id("maple_syrup_bottle"), MAPLE_SYRUP_BOTTLE);

		Registrar.add(Promenade.id("sap_maple_sapling"), SAP_MAPLE_SAPLING);
		Registrar.add(Promenade.id("potted_sap_maple_sapling"), POTTED_SAP_MAPLE_SAPLING);
		Registrar.add(Promenade.id("sap_maple_leaves"), SAP_MAPLE_LEAVES);
		Registrar.add(Promenade.id("sap_maple_leaf_pile"), SAP_MAPLE_LEAF_PILE);

		Registrar.add(Promenade.id("vermilion_maple_sapling"), VERMILION_MAPLE_SAPLING);
		Registrar.add(Promenade.id("potted_vermilion_maple_sapling"), POTTED_VERMILION_MAPLE_SAPLING);
		Registrar.add(Promenade.id("vermilion_maple_leaves"), VERMILION_MAPLE_LEAVES);
		Registrar.add(Promenade.id("vermilion_maple_leaf_pile"), VERMILION_MAPLE_LEAF_PILE);
		Registrar.add(Promenade.id("vermilion_carpeted_grass_block"), VERMILION_CARPETED_GRASS_BLOCK);

		Registrar.add(Promenade.id("fulvous_maple_sapling"), FULVOUS_MAPLE_SAPLING);
		Registrar.add(Promenade.id("potted_fulvous_maple_sapling"), POTTED_FULVOUS_MAPLE_SAPLING);
		Registrar.add(Promenade.id("fulvous_maple_leaves"), FULVOUS_MAPLE_LEAVES);
		Registrar.add(Promenade.id("fulvous_maple_leaf_pile"), FULVOUS_MAPLE_LEAF_PILE);
		Registrar.add(Promenade.id("fulvous_carpeted_grass_block"), FULVOUS_CARPETED_GRASS_BLOCK);

		Registrar.add(Promenade.id("mikado_maple_sapling"), MIKADO_MAPLE_SAPLING);
		Registrar.add(Promenade.id("potted_mikado_maple_sapling"), POTTED_MIKADO_MAPLE_SAPLING);
		Registrar.add(Promenade.id("mikado_maple_leaves"), MIKADO_MAPLE_LEAVES);
		Registrar.add(Promenade.id("mikado_maple_leaf_pile"), MIKADO_MAPLE_LEAF_PILE);
		Registrar.add(Promenade.id("mikado_carpeted_grass_block"), MIKADO_CARPETED_GRASS_BLOCK);

		Registrar.add(Promenade.id("maple_leaf"), MAPLE_LEAF);

		if(Promenade.CONFIG.biomes.carnelian_treeway_weight > 0) {
			//TODO : add back to overworld generation when the Biome API supports that
			//OverworldBiomes.addContinentalBiome(MapleContent.CARNELIAN_TREEWAY, OverworldClimate.COOL, Promenade.CONFIG.biomes.carnelian_treeways_weight / 10.0D);
		}

		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(MapleContent.VERMILION_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.FULVOUS_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.MIKADO_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.SAP_MAPLE_SAPLING));
		});

		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> {
			e.addAfter(CherryContent.CHERRY_OAK_BUTTON,
					MAPLE_LOG,
					MAPLE_WOOD,
					STRIPPED_MAPLE_LOG,
					STRIPPED_MAPLE_WOOD,
					MAPLE_PLANKS,
					MAPLE_STAIRS,
					MAPLE_SLAB,
					MAPLE_FENCE,
					MAPLE_FENCE_GATE,
					MAPLE_DOOR,
					MAPLE_TRAPDOOR,
					MAPLE_PRESSURE_PLATE,
					MAPLE_BUTTON);
		});
		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.GRASS_BLOCK, VERMILION_CARPETED_GRASS_BLOCK, FULVOUS_CARPETED_GRASS_BLOCK, MIKADO_CARPETED_GRASS_BLOCK);
			e.addAfter(CherryContent.CHERRY_OAK_LOG, MAPLE_LOG);
			e.addAfter(CherryContent.WHITE_CHERRY_OAK_LEAVES, SAP_MAPLE_LEAVES, VERMILION_MAPLE_LEAVES, FULVOUS_MAPLE_LEAVES, MIKADO_MAPLE_LEAVES);
			e.addAfter(CherryContent.WHITE_CHERRY_OAK_SAPLING, SAP_MAPLE_SAPLING, VERMILION_MAPLE_SAPLING, FULVOUS_MAPLE_SAPLING, MIKADO_MAPLE_SAPLING);
			e.addAfter(CherryContent.WHITE_CHERRY_OAK_LEAF_PILE, SAP_MAPLE_LEAF_PILE, VERMILION_MAPLE_LEAF_PILE, FULVOUS_MAPLE_LEAF_PILE, MIKADO_MAPLE_LEAF_PILE);
		});
		ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.HONEY_BOTTLE, MAPLE_SYRUP_BOTTLE));
		// TODO
		// ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(CherryContent.CHERRY_OAK_HANGING_SIGN, MAPLE_SIGN, MAPLE_HANGING_SIGN));
		// ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(CherryContent.CHERRY_OAK_CHEST_BOAT, MAPLE_BOAT, MAPLE_CHEST_BOAT));
	}
}
