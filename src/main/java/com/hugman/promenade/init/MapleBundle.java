package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.SimpleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import com.hugman.promenade.util.PromenadeBlockBuilders;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class MapleBundle extends PromenadeBundle {
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
		});
	}
}
