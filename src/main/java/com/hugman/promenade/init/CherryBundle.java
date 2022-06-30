package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.ParticleCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.creator.bundle.block.WoodBundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.PinkCherryOakSaplingGenerator;
import com.hugman.promenade.object.block.sapling_generator.WhiteCherryOakSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import com.hugman.promenade.util.PromenadeBlockBuilders;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class CherryBundle extends PromenadeBundle {
	public static final WoodBundle CHERRY_OAK_WOOD = creator(new WoodBundle.Builder("cherry_oak", MapColor.DULL_RED, MapColor.DULL_PINK, MapColor.DARK_DULL_PINK, false).build());
	public static final PlantBundle PINK_CHERRY_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("pink_cherry_oak_sapling").provider(s -> new SaplingBlock(new PinkCherryOakSaplingGenerator(), s))));
	public static final Block PINK_CHERRY_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("pink_cherry_oak_leaves").settings(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.AZALEA_LEAVES).nonOpaque().allowsSpawning(DefaultBlockSettings::canSpawnOnLeaves).suffocates(DefaultBlockSettings::never).blockVision(DefaultBlockSettings::never)).build());
	public static final Block PINK_CHERRY_OAK_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("pink_cherry_oak_leaf_pile", BlockSoundGroup.AZALEA_LEAVES).build());
	public static final PlantBundle WHITE_CHERRY_OAK_SAPLING = creator(new PlantBundle(DefaultBlockBuilders.SAPLING.copy("white_cherry_oak_sapling").provider(s -> new SaplingBlock(new WhiteCherryOakSaplingGenerator(), s))));
	public static final Block WHITE_CHERRY_OAK_LEAVES = add(DefaultBlockBuilders.LEAVES.copy("white_cherry_oak_leaves").settings(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.AZALEA_LEAVES).nonOpaque().allowsSpawning(DefaultBlockSettings::canSpawnOnLeaves).suffocates(DefaultBlockSettings::never).blockVision(DefaultBlockSettings::never)).build());
	public static final Block WHITE_CHERRY_OAK_LEAF_PILE = add(PromenadeBlockBuilders.leafPile("white_cherry_oak_leaf_pile", BlockSoundGroup.AZALEA_LEAVES).build());
	public static final DefaultParticleType PINK_CHERRY_BLOSSOM = add(new ParticleCreator<>("pink_cherry_blossom", FabricParticleTypes.simple()));
	public static final DefaultParticleType WHITE_CHERRY_BLOSSOM = add(new ParticleCreator<>("white_cherry_blossom", FabricParticleTypes.simple()));

	public static final RegistryKey<Biome> PINK_CHERRY_OAK_FOREST = WorldGenUtil.biomeKey("pink_cherry_oak_forest");
	public static final RegistryKey<Biome> WHITE_CHERRY_OAK_FOREST = WorldGenUtil.biomeKey("white_cherry_oak_forest");

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.cherry_oak_forests_weight > 0) {
			//TODO : add back to overworld generation when the Biome API supports that
			//OverworldBiomes.addContinentalBiome(PINK_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D)
			//OverworldBiomes.addContinentalBiome(WHITE_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D);
		}
	}

	public static void addWanderingSales() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(PINK_CHERRY_OAK_SAPLING.getPlant()));
			factories.add(TradeOfferUtils.sapling(WHITE_CHERRY_OAK_SAPLING.getPlant()));
		});
	}
}
