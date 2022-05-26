package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.AutumnBirchSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.AutumnOakSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import com.hugman.promenade.util.BlockBuilders;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class AutumnBundle extends PromenadeBundle
{
	public static final PlantBundle AUTUMN_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("autumn_oak_sapling").provider(settings -> new SaplingBlock(new AutumnOakSaplingGenerator(), settings))));
	public static final Block AUTUMN_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("autumn_oak_leaves").build());
	public static final Block AUTUMN_OAK_LEAF_PILE = add(BlockBuilders.leafPile("autumn_oak_leaf_pile").build());
	public static final PlantBundle AUTUMN_BIRCH_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("autumn_birch_sapling").provider(settings -> new SaplingBlock(new AutumnBirchSaplingGenerator(), settings))));
	public static final Block AUTUMN_BIRCH_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("autumn_birch_leaves").build());
	public static final Block AUTUMN_BIRCH_LEAF_PILE = add(BlockBuilders.leafPile("autumn_birch_leaf_pile").build());

	public static final RegistryKey<Biome> PUMPKIN_PASTURES = WorldGenUtil.biomeKey("pumpkin_pastures");

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.pumpkin_pastures_weight > 0) {
			//TODO
			//OverworldBiomes.addContinentalBiome(PUMPKIN_PASTURES, OverworldClimate.COOL, Promenade.CONFIG.biomes.pumpkin_pastures_weight / 10.0D);
		}
	}

	public static void addWanderingSales() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(AUTUMN_OAK_SAPLING.getPlant()));
			factories.add(TradeOfferUtils.sapling(AUTUMN_BIRCH_SAPLING.getPlant()));
		});
	}
}
