package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.bundle.block.OverworldWoodBundle;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.PalmSaplingGenerator;
import com.hugman.promenade.object.trade_offers.SellSaplingFactory;
import com.hugman.promenade.util.BlockBuilders;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class PalmBundle extends PromenadeBundle
{
	public static final OverworldWoodBundle PALM_WOOD = creator(new OverworldWoodBundle.Builder("palm", new PalmSaplingGenerator(), MapColor.ORANGE, MapColor.TERRACOTTA_CYAN).saplingSoil(blockState -> blockState.isIn(BlockTags.SAND)).build());
	public static final Block PALM_LEAF_PILE = add(BlockBuilders.LEAF_PILE.copy("palm_leaf_pile").build());

	public static final RegistryKey<PlacedFeature> PALMS = WorldGenUtil.placedFeatureKey("trees/palms");

	public static void addWanderingSales() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(new SellSaplingFactory(PALM_WOOD.getSapling()));
		});
	}

	public static void addToGen() {
		if(Promenade.CONFIG.world_features.palms) {
			BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, PALMS);
		}
	}
}
