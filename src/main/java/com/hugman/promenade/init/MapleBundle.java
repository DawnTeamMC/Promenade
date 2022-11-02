package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeFoods;
import com.hugman.promenade.object.block.MapleLogBlock;
import com.hugman.promenade.object.block.StrippedMapleLogBlock;
import com.hugman.promenade.object.block.sapling_generator.SimpleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import com.hugman.promenade.util.PromenadeBlockBuilders;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class MapleBundle extends PromenadeBundle {
	private static final MapColor MAPLE_LOG_INSIDE_COLOR = MapColor.ORANGE;
	private static final MapColor MAPLE_LOG_BARK_COLOR = MapColor.ORANGE;
	private static final MapColor MAPLE_WOOD_COLOR = MapColor.ORANGE;

	private static final BlockCreator.Builder MAPLE_BLOCK_BUILDER = new BlockCreator.Builder().settings(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).mapColor(MAPLE_WOOD_COLOR));

	public static final Block MAPLE_PLANKS = add(MAPLE_BLOCK_BUILDER.copy("maple_planks").from(DefaultBlockBuilders.PLANKS).build());

	public static final Block MAPLE_LOG = add(new BlockCreator.Builder(
			"maple_log",
			MapleLogBlock::new,
			FabricBlockSettings.of(Material.WOOD, (blockState) -> blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? MAPLE_LOG_INSIDE_COLOR : MAPLE_LOG_BARK_COLOR)
					.strength(2.0F)
					.sounds(BlockSoundGroup.WOOD))
			.itemGroup(ItemGroup.BUILDING_BLOCKS)
			.flammability(5)
			.build());

	public static final Block STRIPPED_MAPLE_LOG = add(new BlockCreator.Builder(
			"stripped_maple_log",
			StrippedMapleLogBlock::new,
			FabricBlockSettings.of(Material.WOOD, MAPLE_WOOD_COLOR)
					.strength(2.0F)
					.sounds(BlockSoundGroup.WOOD))
			.itemGroup(ItemGroup.BUILDING_BLOCKS)
			.flammability(5)
			.build());

	public static final Block MAPLE_WOOD = add(new BlockCreator.Builder(
			"maple_wood",
			PillarBlock::new,
			FabricBlockSettings.of(Material.WOOD, MAPLE_LOG_BARK_COLOR)
					.strength(2.0F)
					.sounds(BlockSoundGroup.WOOD))
			.itemGroup(ItemGroup.BUILDING_BLOCKS)
			.flammability(5)
			.build());

	public static final Block STRIPPED_MAPLE_WOOD = add(new BlockCreator.Builder(
			"stripped_maple_wood",
			PillarBlock::new,
			FabricBlockSettings.of(Material.WOOD, MAPLE_WOOD_COLOR)
					.strength(2.0F)
					.sounds(BlockSoundGroup.WOOD))
			.itemGroup(ItemGroup.BUILDING_BLOCKS)
			.flammability(5)
			.build());

	public static final Block MAPLE_STAIRS = add(MAPLE_BLOCK_BUILDER.copy("maple_stairs").from(DefaultBlockBuilders.STAIRS).build());
	public static final Block MAPLE_SLAB = add(MAPLE_BLOCK_BUILDER.copy("maple_slab").from(DefaultBlockBuilders.SLAB).build());
	public static final Block MAPLE_TRAPDOOR = add(MAPLE_BLOCK_BUILDER.copy("maple_trapdoor").from(DefaultBlockBuilders.TRAPDOOR).build());
	public static final Block MAPLE_PRESSURE_PLATE = add(MAPLE_BLOCK_BUILDER.copy("maple_pressure_plate").from(DefaultBlockBuilders.WOOD_PRESSURE_PLATE).build());
	public static final Block MAPLE_BUTTON = add(MAPLE_BLOCK_BUILDER.copy("maple_button").from(DefaultBlockBuilders.WOOD_BUTTON).build());
	public static final Block MAPLE_FENCE = add(MAPLE_BLOCK_BUILDER.copy("maple_fence").from(DefaultBlockBuilders.FENCE).build());
	public static final Block MAPLE_FENCE_GATE = add(MAPLE_BLOCK_BUILDER.copy("maple_fence_gate").from(DefaultBlockBuilders.FENCE_GATE).build());
	public static final Block MAPLE_DOOR = add(MAPLE_BLOCK_BUILDER.copy("maple_door").from(DefaultBlockBuilders.DOOR).build());

	public static final Item MAPLE_SYRUP_BOTTLE = add(new ItemCreator.Builder(
			"maple_syrup_bottle",
			HoneyBottleItem::new,
			new FabricItemSettings()
					.group(ItemGroup.FOOD)
					.maxCount(16)
					.food(PromenadeFoods.MAPLE_SYRUP)
					.recipeRemainder(Items.GLASS_BOTTLE))
			.build());

	//Red
	public static final PlantBundle VERMILION_MAPLE_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("vermilion_maple_sapling").provider(settings -> new SaplingBlock(new SimpleSaplingGenerator("maple/vermilion", "002"), settings))));
	public static final Block VERMILION_MAPLE_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("vermilion_maple_leaves").build());
	public static final Block VERMILION_MAPLE_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("vermilion_maple_leaf_pile").build());
	public static final Block VERMILION_CARPETED_GRASS_BLOCK = add(PromenadeBlockBuilders.carpetedGrassBlock("vermilion_carpeted_grass_block").build());

	//Orange
	public static final PlantBundle FULVOUS_MAPLE_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("fulvous_maple_sapling").provider(settings -> new SaplingBlock(new SimpleSaplingGenerator("maple/fulvous", "002"), settings))));
	public static final Block FULVOUS_MAPLE_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("fulvous_maple_leaves").build());
	public static final Block FULVOUS_MAPLE_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("fulvous_maple_leaf_pile").build());
	public static final Block FULVOUS_CARPETED_GRASS_BLOCK = add(PromenadeBlockBuilders.carpetedGrassBlock("fulvous_carpeted_grass_block").build());

	//Yellow
	public static final PlantBundle MIKADO_MAPLE_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("mikado_maple_sapling").provider(settings -> new SaplingBlock(new SimpleSaplingGenerator("maple/mikado", "002"), settings))));
	public static final Block MIKADO_MAPLE_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("mikado_maple_leaves").build());
	public static final Block MIKADO_MAPLE_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("mikado_maple_leaf_pile").build());
	public static final Block MIKADO_CARPETED_GRASS_BLOCK = add(PromenadeBlockBuilders.carpetedGrassBlock("mikado_carpeted_grass_block").build());

	//Green
	public static final PlantBundle SAP_MAPLE_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("sap_maple_sapling").provider(settings -> new SaplingBlock(new SimpleSaplingGenerator("maple/sap", "002"), settings))));
	public static final Block SAP_MAPLE_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("sap_maple_leaves").build());
	public static final Block SAP_MAPLE_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("sap_maple_leaf_pile").build());

	//TODO: rare purple japanese maple

	public static final RegistryKey<Biome> CARNELIAN_TREEWAY = WorldGenUtil.biomeKey("carnelian_treeway");

	public static void init() {
		StrippableBlockRegistry.register(MAPLE_LOG, STRIPPED_MAPLE_LOG);
		StrippableBlockRegistry.register(MAPLE_WOOD, STRIPPED_MAPLE_WOOD);
	}

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.carnelian_treeway_weight > 0) {
			//TODO : add back to overworld generation when the Biome API supports that
			//OverworldBiomes.addContinentalBiome(CARNELIAN_TREEWAY, OverworldClimate.COOL, Promenade.CONFIG.biomes.carnelian_treeways_weight / 10.0D);
		}
	}

	public static void addWanderingSales() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(VERMILION_MAPLE_SAPLING.getPlant()));
			factories.add(TradeOfferUtils.sapling(FULVOUS_MAPLE_SAPLING.getPlant()));
			factories.add(TradeOfferUtils.sapling(MIKADO_MAPLE_SAPLING.getPlant()));
			factories.add(TradeOfferUtils.sapling(SAP_MAPLE_SAPLING.getPlant()));
		});
	}
}
