package com.hugman.promenade.content;

import com.hugman.newdawn.DawnFactory;
import com.hugman.promenade.PromenadeFactory;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.MapleLogBlock;
import com.hugman.promenade.object.block.StrippedMapleLogBlock;
import com.hugman.promenade.object.block.sapling_generator.SimpleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class MapleContent {
	private static final MapColor BARK_COLOR = MapColor.ORANGE;
	private static final MapColor WOOD_COLOR = MapColor.ORANGE;

	public static final Block MAPLE_LOG = DawnFactory.log(Promenade.id("maple_log"), new MapleLogBlock(DawnFactory.logSettings(WOOD_COLOR, BARK_COLOR, false)), false).get();
	public static final Block STRIPPED_MAPLE_LOG = DawnFactory.log(Promenade.id("stripped_maple_log"), new StrippedMapleLogBlock(DawnFactory.logSettings(WOOD_COLOR, false)), false).strippedFrom(MAPLE_LOG).get();
	public static final Block MAPLE_WOOD = DawnFactory.log(Promenade.id("maple_wood"), BARK_COLOR, false).get();
	public static final Block STRIPPED_MAPLE_WOOD = DawnFactory.log(Promenade.id("stripped_maple_wood"), WOOD_COLOR, false).strippedFrom(MAPLE_WOOD).get();

	public static final Block MAPLE_PLANKS = DawnFactory.planks(Promenade.id("maple_planks"), WOOD_COLOR, false).get();
	public static final Block MAPLE_STAIRS = DawnFactory.stairs(Promenade.id("maple_stairs"), MAPLE_PLANKS, false).get();
	public static final Block MAPLE_SLAB = DawnFactory.slab(Promenade.id("maple_slab"), MAPLE_PLANKS, false).get();
	public static final Block MAPLE_TRAPDOOR = DawnFactory.trapdoor(Promenade.id("maple_trapdoor"), MAPLE_PLANKS, false).get();
	public static final Block MAPLE_PRESSURE_PLATE = DawnFactory.pressurePlate(Promenade.id("maple_pressure_plate"), MAPLE_PLANKS, false).get();
	public static final Block MAPLE_BUTTON = DawnFactory.woodenButton(Promenade.id("maple_button"), false).get();
	public static final Block MAPLE_FENCE = DawnFactory.fence(Promenade.id("maple_fence"), MAPLE_PLANKS, false).get();
	public static final Block MAPLE_FENCE_GATE = DawnFactory.fenceGate(Promenade.id("maple_fence_gate"), MAPLE_PLANKS, false).get();
	public static final Block MAPLE_DOOR = DawnFactory.door(Promenade.id("maple_door"), MAPLE_PLANKS).get();

	public static final Item MAPLE_SYRUP_BOTTLE = DawnFactory.item(
			Promenade.id("maple_syrup_bottle"),
			new HoneyBottleItem(new FabricItemSettings()
					.group(ItemGroup.FOOD)
					.maxCount(16)
					.food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
					.recipeRemainder(Items.GLASS_BOTTLE)
			)).get();

	//TODO: maple syrup block

	public static final Block SAP_MAPLE_SAPLING = DawnFactory.sapling(Promenade.id("sap_maple_sapling"), new SimpleSaplingGenerator("maple/sap", "002")).get();
	public static final Block POTTED_SAP_MAPLE_SAPLING = DawnFactory.potted(SAP_MAPLE_SAPLING).get();
	public static final Block SAP_MAPLE_LEAVES = DawnFactory.leaves(Promenade.id("sap_maple_leaves")).get();
	public static final Block SAP_MAPLE_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("sap_maple_leaf_pile")).get();

	public static final Block VERMILION_MAPLE_SAPLING = DawnFactory.sapling(Promenade.id("vermilion_maple_sapling"), new SimpleSaplingGenerator("maple/vermilion", "002")).get();
	public static final Block POTTED_VERMILION_MAPLE_SAPLING = DawnFactory.potted(VERMILION_MAPLE_SAPLING).get();
	public static final Block VERMILION_MAPLE_LEAVES = DawnFactory.leaves(Promenade.id("vermilion_maple_leaves")).get();
	public static final Block VERMILION_MAPLE_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("vermilion_maple_leaf_pile")).get();
	public static final Block VERMILION_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock(Promenade.id("vermilion_carpeted_grass_block")).get();

	public static final Block FULVOUS_MAPLE_SAPLING = DawnFactory.sapling(Promenade.id("fulvous_maple_sapling"), new SimpleSaplingGenerator("maple/fulvous", "002")).get();
	public static final Block POTTED_FULVOUS_MAPLE_SAPLING = DawnFactory.potted(FULVOUS_MAPLE_SAPLING).get();
	public static final Block FULVOUS_MAPLE_LEAVES = DawnFactory.leaves(Promenade.id("fulvous_maple_leaves")).get();
	public static final Block FULVOUS_MAPLE_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("fulvous_maple_leaf_pile")).get();
	public static final Block FULVOUS_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock(Promenade.id("fulvous_carpeted_grass_block")).get();

	public static final Block MIKADO_MAPLE_SAPLING = DawnFactory.sapling(Promenade.id("mikado_maple_sapling"), new SimpleSaplingGenerator("maple/mikado", "002")).get();
	public static final Block POTTED_MIKADO_MAPLE_SAPLING = DawnFactory.potted(MIKADO_MAPLE_SAPLING).get();
	public static final Block MIKADO_MAPLE_LEAVES = DawnFactory.leaves(Promenade.id("mikado_maple_leaves")).get();
	public static final Block MIKADO_MAPLE_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("mikado_maple_leaf_pile")).get();
	public static final Block MIKADO_CARPETED_GRASS_BLOCK = PromenadeFactory.carpetedGrassBlock(Promenade.id("mikado_carpeted_grass_block")).get();

	public static final DefaultParticleType MAPLE_LEAF = DawnFactory.particle(Promenade.id("maple_leaf"));

	public static final RegistryKey<Biome> CARNELIAN_TREEWAY = DawnFactory.biomeKey(Promenade.id("carnelian_treeway"));

	public static void init() {
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
	}
}
