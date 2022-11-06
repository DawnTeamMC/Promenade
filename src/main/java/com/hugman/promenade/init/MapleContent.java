package com.hugman.promenade.init;

import com.hugman.newdawn.DawnBuilders;
import com.hugman.newdawn.PromenadeBuilders;
import com.hugman.newdawn.builder.ItemBuilder;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.MapleLogBlock;
import com.hugman.promenade.object.block.StrippedMapleLogBlock;
import com.hugman.promenade.object.block.sapling_generator.SimpleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class MapleContent {
	private static final MapColor MAPLE_BARK_COLOR = MapColor.ORANGE;
	private static final MapColor MAPLE_WOOD_COLOR = MapColor.ORANGE;

	public static final Block MAPLE_LOG = DawnBuilders.log(Promenade.id("maple_log"), new MapleLogBlock(DawnBuilders.logSettings(MAPLE_WOOD_COLOR, MAPLE_BARK_COLOR))).get();
	public static final Block STRIPPED_MAPLE_LOG = DawnBuilders.log(Promenade.id("stripped_maple_log"), new StrippedMapleLogBlock(DawnBuilders.logSettings(MAPLE_WOOD_COLOR))).strippedFrom(MAPLE_LOG).get();
	public static final Block MAPLE_WOOD = DawnBuilders.log(Promenade.id("maple_wood"), MAPLE_BARK_COLOR).get();
	public static final Block STRIPPED_MAPLE_WOOD = DawnBuilders.log(Promenade.id("stripped_maple_wood"), MAPLE_WOOD_COLOR).strippedFrom(MAPLE_WOOD).get();

	public static final Block MAPLE_PLANKS = DawnBuilders.planks(Promenade.id("maple_planks"), MAPLE_WOOD_COLOR).get();
	public static final Block MAPLE_STAIRS = DawnBuilders.stairs(Promenade.id("maple_stairs"), MAPLE_PLANKS).get();
	public static final Block MAPLE_SLAB = DawnBuilders.slab(Promenade.id("maple_slab"), MAPLE_PLANKS).get();
	public static final Block MAPLE_TRAPDOOR = DawnBuilders.trapdoor(Promenade.id("maple_trapdoor"), MAPLE_PLANKS).get();
	public static final Block MAPLE_PRESSURE_PLATE = DawnBuilders.pressurePlate(Promenade.id("maple_pressure_plate"), MAPLE_PLANKS).get();
	public static final Block MAPLE_BUTTON = DawnBuilders.button(Promenade.id("maple_button")).get();
	public static final Block MAPLE_FENCE = DawnBuilders.fence(Promenade.id("maple_fence"), MAPLE_PLANKS).get();
	public static final Block MAPLE_FENCE_GATE = DawnBuilders.fenceGate(Promenade.id("maple_fence_gate"), MAPLE_PLANKS).get();
	public static final Block MAPLE_DOOR = DawnBuilders.door(Promenade.id("maple_door"), MAPLE_PLANKS).get();

	public static final Item MAPLE_SYRUP_BOTTLE = ItemBuilder.of(
			Promenade.id("maple_syrup_bottle"),
			new HoneyBottleItem(new FabricItemSettings()
					.group(ItemGroup.FOOD)
					.maxCount(16)
					.food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
					.recipeRemainder(Items.GLASS_BOTTLE)
			)).get();

	public static final Block SAP_MAPLE_SAPLING = DawnBuilders.sapling(Promenade.id("sap_maple_sapling"), new SimpleSaplingGenerator("maple/sap", "002")).get();
	public static final Block POTTED_SAP_MAPLE_SAPLING = DawnBuilders.potted(SAP_MAPLE_SAPLING).get();
	public static final Block SAP_MAPLE_LEAVES = DawnBuilders.leaves(Promenade.id("sap_maple_leaves")).get();
	public static final Block SAP_MAPLE_LEAF_PILE = DawnBuilders.leafPile(Promenade.id("sap_maple_leaf_pile")).get();

	public static final Block VERMILION_MAPLE_SAPLING = DawnBuilders.sapling(Promenade.id("vermilion_maple_sapling"), new SimpleSaplingGenerator("maple/vermilion", "002")).get();
	public static final Block POTTED_VERMILION_MAPLE_SAPLING = DawnBuilders.potted(VERMILION_MAPLE_SAPLING).get();
	public static final Block VERMILION_MAPLE_LEAVES = DawnBuilders.leaves(Promenade.id("vermilion_maple_leaves")).get();
	public static final Block VERMILION_MAPLE_LEAF_PILE = DawnBuilders.leafPile(Promenade.id("vermilion_maple_leaf_pile")).get();
	public static final Block VERMILION_CARPETED_GRASS_BLOCK = PromenadeBuilders.carpetedGrassBlock(Promenade.id("vermilion_carpeted_grass_block")).get();

	public static final Block FULVOUS_MAPLE_SAPLING = DawnBuilders.sapling(Promenade.id("fulvous_maple_sapling"), new SimpleSaplingGenerator("maple/fulvous", "002")).get();
	public static final Block POTTED_FULVOUS_MAPLE_SAPLING = DawnBuilders.potted(FULVOUS_MAPLE_SAPLING).get();
	public static final Block FULVOUS_MAPLE_LEAVES = DawnBuilders.leaves(Promenade.id("fulvous_maple_leaves")).get();
	public static final Block FULVOUS_MAPLE_LEAF_PILE = DawnBuilders.leafPile(Promenade.id("fulvous_maple_leaf_pile")).get();
	public static final Block FULVOUS_CARPETED_GRASS_BLOCK = PromenadeBuilders.carpetedGrassBlock(Promenade.id("fulvous_carpeted_grass_block")).get();

	public static final Block MIKADO_MAPLE_SAPLING = DawnBuilders.sapling(Promenade.id("mikado_maple_sapling"), new SimpleSaplingGenerator("maple/mikado", "002")).get();
	public static final Block POTTED_MIKADO_MAPLE_SAPLING = DawnBuilders.potted(MIKADO_MAPLE_SAPLING).get();
	public static final Block MIKADO_MAPLE_LEAVES = DawnBuilders.leaves(Promenade.id("mikado_maple_leaves")).get();
	public static final Block MIKADO_MAPLE_LEAF_PILE = DawnBuilders.leafPile(Promenade.id("mikado_maple_leaf_pile")).get();
	public static final Block MIKADO_CARPETED_GRASS_BLOCK = PromenadeBuilders.carpetedGrassBlock(Promenade.id("mikado_carpeted_grass_block")).get();

	//TODO: rare purple japanese maple

	public static final DefaultParticleType MAPLE_LEAF = Registry.register(Registry.PARTICLE_TYPE, Promenade.id("maple_leaf"), FabricParticleTypes.simple());

	public static final RegistryKey<Biome> CARNELIAN_TREEWAY = WorldGenUtil.biomeKey("carnelian_treeway");

	public static void init() {
		registerTradeOffers();
		addToGen();
	}
	public static void addToGen() {
		//TODO : add back to overworld generation when the Biome API supports that
		if(Promenade.CONFIG.biomes.carnelian_treeway_weight > 0) {
			//OverworldBiomes.addContinentalBiome(MapleContent.CARNELIAN_TREEWAY, OverworldClimate.COOL, Promenade.CONFIG.biomes.carnelian_treeways_weight / 10.0D);
		}
	}

	public static void registerTradeOffers() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(MapleContent.VERMILION_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.FULVOUS_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.MIKADO_MAPLE_SAPLING));
			factories.add(TradeOfferUtils.sapling(MapleContent.SAP_MAPLE_SAPLING));
		});
	}
}
