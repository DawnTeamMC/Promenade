package fr.hugman.promenade.registry.content;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.block.sapling.OakLikeSaplingGenerator;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.block.MapleLogBlock;
import fr.hugman.promenade.block.StrippedMapleLogBlock;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class MapleContent {
	public static final BlockSetType BLOCK_SET_TYPE = BlockSetTypeRegistry.registerWood(Promenade.id("maple"));
	public static final WoodType WOOD_TYPE = WoodTypeRegistry.register(Promenade.id("maple"), BLOCK_SET_TYPE);
	private static final BlockSoundGroup WOOD_SOUNDS = BlockSoundGroup.WOOD;
	private static final MapColor BARK_COLOR = MapColor.DEEPSLATE_GRAY;
	private static final MapColor WOOD_COLOR = MapColor.TERRACOTTA_WHITE;

	public static final MapColor SAP_LEAVES_COLOR = MapColor.DARK_GREEN;
	public static final MapColor VERMILION_LEAVES_COLOR = MapColor.DULL_RED;
	public static final MapColor FULVOUS_LEAVES_COLOR = MapColor.ORANGE;
	public static final MapColor MIKADO_LEAVES_COLOR = MapColor.GOLD;

	public static final Block STRIPPED_MAPLE_LOG = new StrippedMapleLogBlock(DawnFactory.logSettings(WOOD_COLOR, WOOD_SOUNDS, true));
	public static final Block MAPLE_LOG = new MapleLogBlock(DawnFactory.logSettings(WOOD_COLOR, BARK_COLOR, WOOD_SOUNDS, true).stripsInto(STRIPPED_MAPLE_LOG));
	public static final Block STRIPPED_MAPLE_WOOD = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, WOOD_SOUNDS, true));
	public static final Block MAPLE_WOOD = new PillarBlock(DawnFactory.logSettings(BARK_COLOR, WOOD_SOUNDS, true).stripsInto(STRIPPED_MAPLE_WOOD));

	public static final Block MAPLE_PLANKS = DawnFactory.planks(WOOD_COLOR, WOOD_SOUNDS, true);
	public static final Block MAPLE_STAIRS = DawnFactory.stairs(MAPLE_PLANKS);
	public static final Block MAPLE_SLAB = DawnFactory.slab(MAPLE_PLANKS);
	public static final Block MAPLE_FENCE = DawnFactory.fence(MAPLE_PLANKS);
	public static final Block MAPLE_FENCE_GATE = DawnFactory.fenceGate(MAPLE_PLANKS, WOOD_TYPE);
	public static final Block MAPLE_DOOR = DawnFactory.door(MAPLE_PLANKS, BLOCK_SET_TYPE);
	public static final Block MAPLE_TRAPDOOR = DawnFactory.trapdoor(MAPLE_PLANKS, BLOCK_SET_TYPE);
	public static final Block MAPLE_BUTTON = DawnFactory.woodenButton(MAPLE_PLANKS, BLOCK_SET_TYPE);
	public static final Block MAPLE_PRESSURE_PLATE = DawnFactory.pressurePlate(MAPLE_PLANKS, BLOCK_SET_TYPE);

	public static final SignBlocks MAPLE_SIGNS = DawnFactory.signs(Promenade.id("maple"), MAPLE_PLANKS);
	public static final TerraformBoatType MAPLE_BOAT_TYPE = DawnFactory.boat(Promenade.id("maple"), Items.OAK_PLANKS); //TODO change when possible (PR #72 on TerraformersMC/Terraform)

	public static final Item MAPLE_SYRUP_BOTTLE = new HoneyBottleItem(new DawnItemSettings()
			.maxCount(16)
			.food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
			.recipeRemainder(Items.GLASS_BOTTLE));

	public static final Block SAP_MAPLE_SAPLING = DawnFactory.sapling(SAP_LEAVES_COLOR, OakLikeSaplingGenerator.of(Promenade.id("maple/sap")));
	public static final Block POTTED_SAP_MAPLE_SAPLING = DawnFactory.potted(SAP_MAPLE_SAPLING);
	public static final Block SAP_MAPLE_LEAVES = DawnFactory.leaves(SAP_LEAVES_COLOR);
	public static final Block SAP_MAPLE_LEAF_PILE = PromenadeFactory.leafPile();

	public static final DefaultParticleType VERMILION_MAPLE_LEAF = FabricParticleTypes.simple();
	public static final Block VERMILION_MAPLE_SAPLING = DawnFactory.sapling(VERMILION_LEAVES_COLOR, OakLikeSaplingGenerator.of(Promenade.id("maple/vermilion")));
	public static final Block POTTED_VERMILION_MAPLE_SAPLING = DawnFactory.potted(VERMILION_MAPLE_SAPLING);
	public static final Block VERMILION_MAPLE_LEAVES = PromenadeFactory.decoratedLeaves(VERMILION_LEAVES_COLOR, VERMILION_MAPLE_LEAF);
	public static final Block VERMILION_MAPLE_LEAF_PILE = PromenadeFactory.leafPile(VERMILION_LEAVES_COLOR);
	public static final Block VERMILION_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock(VERMILION_LEAVES_COLOR);

	public static final DefaultParticleType FULVOUS_MAPLE_LEAF = FabricParticleTypes.simple();
	public static final Block FULVOUS_MAPLE_SAPLING = DawnFactory.sapling(FULVOUS_LEAVES_COLOR, OakLikeSaplingGenerator.of(Promenade.id("maple/fulvous")));
	public static final Block POTTED_FULVOUS_MAPLE_SAPLING = DawnFactory.potted(FULVOUS_MAPLE_SAPLING);
	public static final Block FULVOUS_MAPLE_LEAVES = PromenadeFactory.decoratedLeaves(FULVOUS_LEAVES_COLOR, FULVOUS_MAPLE_LEAF);
	public static final Block FULVOUS_MAPLE_LEAF_PILE = PromenadeFactory.leafPile(FULVOUS_LEAVES_COLOR);
	public static final Block FULVOUS_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock(FULVOUS_LEAVES_COLOR);

	public static final DefaultParticleType MIKADO_MAPLE_LEAF = FabricParticleTypes.simple();
	public static final Block MIKADO_MAPLE_SAPLING = DawnFactory.sapling(MIKADO_LEAVES_COLOR, OakLikeSaplingGenerator.of(Promenade.id("maple/mikado")));
	public static final Block POTTED_MIKADO_MAPLE_SAPLING = DawnFactory.potted(MIKADO_MAPLE_SAPLING);
	public static final Block MIKADO_MAPLE_LEAVES = PromenadeFactory.decoratedLeaves(MIKADO_LEAVES_COLOR, MIKADO_MAPLE_LEAF);
	public static final Block MIKADO_MAPLE_LEAF_PILE = PromenadeFactory.leafPile(MIKADO_LEAVES_COLOR);
	public static final Block MIKADO_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock(MIKADO_LEAVES_COLOR);

	public static final RegistryKey<Biome> CARNELIAN_TREEWAY = DawnFactory.biome(Promenade.id("carnelian_treeway"));

	public static void register(Registrar r) {
		r.add(("maple_log"), MAPLE_LOG);
		r.add(("stripped_maple_log"), STRIPPED_MAPLE_LOG);
		r.add(("maple_wood"), MAPLE_WOOD);
		r.add(("stripped_maple_wood"), STRIPPED_MAPLE_WOOD);

		r.add(("maple_planks"), MAPLE_PLANKS);
		r.add(("maple_stairs"), MAPLE_STAIRS);
		r.add(("maple_slab"), MAPLE_SLAB);
		r.add(("maple_fence"), MAPLE_FENCE);
		r.add(("maple_fence_gate"), MAPLE_FENCE_GATE);
		r.add(("maple_door"), MAPLE_DOOR);
		r.add(("maple_trapdoor"), MAPLE_TRAPDOOR);
		r.add(("maple_button"), MAPLE_BUTTON);
		r.add(("maple_pressure_plate"), MAPLE_PRESSURE_PLATE);

		r.add(("maple"), MAPLE_SIGNS);
		r.add(("maple"), MAPLE_BOAT_TYPE);

		r.add(("maple_syrup_bottle"), MAPLE_SYRUP_BOTTLE);

		r.add(("sap_maple_sapling"), SAP_MAPLE_SAPLING);
		r.add(("potted_sap_maple_sapling"), POTTED_SAP_MAPLE_SAPLING);
		r.add(("sap_maple_leaves"), SAP_MAPLE_LEAVES);
		r.add(("sap_maple_leaf_pile"), SAP_MAPLE_LEAF_PILE);

		r.add(("vermilion_maple_leaf"), VERMILION_MAPLE_LEAF);
		r.add(("vermilion_maple_sapling"), VERMILION_MAPLE_SAPLING);
		r.add(("potted_vermilion_maple_sapling"), POTTED_VERMILION_MAPLE_SAPLING);
		r.add(("vermilion_maple_leaves"), VERMILION_MAPLE_LEAVES);
		r.add(("vermilion_maple_leaf_pile"), VERMILION_MAPLE_LEAF_PILE);
		r.add(("vermilion_carpeted_grass_block"), VERMILION_CARPETED_GRASS_BLOCK);

		r.add(("fulvous_maple_leaf"), FULVOUS_MAPLE_LEAF);
		r.add(("fulvous_maple_sapling"), FULVOUS_MAPLE_SAPLING);
		r.add(("potted_fulvous_maple_sapling"), POTTED_FULVOUS_MAPLE_SAPLING);
		r.add(("fulvous_maple_leaves"), FULVOUS_MAPLE_LEAVES);
		r.add(("fulvous_maple_leaf_pile"), FULVOUS_MAPLE_LEAF_PILE);
		r.add(("fulvous_carpeted_grass_block"), FULVOUS_CARPETED_GRASS_BLOCK);

		r.add(("mikado_maple_leaf"), MIKADO_MAPLE_LEAF);
		r.add(("mikado_maple_sapling"), MIKADO_MAPLE_SAPLING);
		r.add(("potted_mikado_maple_sapling"), POTTED_MIKADO_MAPLE_SAPLING);
		r.add(("mikado_maple_leaves"), MIKADO_MAPLE_LEAVES);
		r.add(("mikado_maple_leaf_pile"), MIKADO_MAPLE_LEAF_PILE);
		r.add(("mikado_carpeted_grass_block"), MIKADO_CARPETED_GRASS_BLOCK);

		appendItemGroups();
		appendVillagerTrades();
		appendWorldGen();
	}

	private static void appendItemGroups() {
		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e ->
				e.addAfter(SakuraContent.SAKURA_BUTTON,
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
						MAPLE_BUTTON));

		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.GRASS_BLOCK, VERMILION_CARPETED_GRASS_BLOCK, FULVOUS_CARPETED_GRASS_BLOCK, MIKADO_CARPETED_GRASS_BLOCK);
			e.addAfter(SakuraContent.SAKURA_LOG, MAPLE_LOG);
			e.addAfter(SakuraContent.COTTON_SAKURA_BLOSSOMS, SAP_MAPLE_LEAVES, VERMILION_MAPLE_LEAVES, FULVOUS_MAPLE_LEAVES, MIKADO_MAPLE_LEAVES);
			e.addAfter(SakuraContent.COTTON_SAKURA_SAPLING, SAP_MAPLE_SAPLING, VERMILION_MAPLE_SAPLING, FULVOUS_MAPLE_SAPLING, MIKADO_MAPLE_SAPLING);
			e.addAfter(SakuraContent.COTTON_SAKURA_BLOSSOM_PILE, SAP_MAPLE_LEAF_PILE, VERMILION_MAPLE_LEAF_PILE, FULVOUS_MAPLE_LEAF_PILE, MIKADO_MAPLE_LEAF_PILE);
		});
		ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(SakuraContent.SAKURA_SIGNS.hangingSign(), MAPLE_SIGNS.sign(), MAPLE_SIGNS.hangingSign()));
		ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(SakuraContent.SAKURA_BOAT_TYPE.getChestItem(), MAPLE_BOAT_TYPE.getItem(), MAPLE_BOAT_TYPE.getChestItem()));
		ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.HONEY_BOTTLE, MAPLE_SYRUP_BOTTLE));
	}

	private static void appendVillagerTrades() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(MapleContent.VERMILION_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.FULVOUS_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.MIKADO_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.SAP_MAPLE_SAPLING));
		});
	}

	private static void appendWorldGen() {
		if (Promenade.CONFIG.biomes.carnelian_treeway_weight <= 0) {
			return;
		}
		double weight = Promenade.CONFIG.biomes.carnelian_treeway_weight / 100.0D;
		BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, CARNELIAN_TREEWAY, weight);
	}
}
