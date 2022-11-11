package com.hugman.promenade.content;

import com.hugman.newdawn.DawnFactory;
import com.hugman.promenade.PromenadeFactory;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.sapling_generator.SimpleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class CherryContent {
	private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
	private static final MapColor WOOD_COLOR = MapColor.DULL_PINK;
	private static final MapColor PLANKS_COLOR = MapColor.DULL_RED;

	public static final Block CHERRY_OAK_LOG = DawnFactory.log(Promenade.id("cherry_oak_log"), WOOD_COLOR, BARK_COLOR, false).get();
	public static final Block STRIPPED_CHERRY_OAK_LOG = DawnFactory.log(Promenade.id("stripped_cherry_oak_log"), WOOD_COLOR, false).strippedFrom(CHERRY_OAK_LOG).get();
	public static final Block CHERRY_OAK_WOOD = DawnFactory.log(Promenade.id("cherry_oak_wood"), BARK_COLOR, false).get();
	public static final Block STRIPPED_CHERRY_OAK_WOOD = DawnFactory.log(Promenade.id("stripped_cherry_oak_wood"), WOOD_COLOR, false).strippedFrom(CHERRY_OAK_WOOD).get();

	public static final Block CHERRY_OAK_PLANKS = DawnFactory.planks(Promenade.id("cherry_oak_planks"), PLANKS_COLOR, false).get();
	public static final Block CHERRY_OAK_STAIRS = DawnFactory.stairs(Promenade.id("cherry_oak_stairs"), CHERRY_OAK_PLANKS, false).get();
	public static final Block CHERRY_OAK_SLAB = DawnFactory.slab(Promenade.id("cherry_oak_slab"), CHERRY_OAK_PLANKS, false).get();
	public static final Block CHERRY_OAK_TRAPDOOR = DawnFactory.trapdoor(Promenade.id("cherry_oak_trapdoor"), CHERRY_OAK_PLANKS, false).get();
	public static final Block CHERRY_OAK_PRESSURE_PLATE = DawnFactory.pressurePlate(Promenade.id("cherry_oak_pressure_plate"), CHERRY_OAK_PLANKS, false).get();
	public static final Block CHERRY_OAK_BUTTON = DawnFactory.woodenButton(Promenade.id("cherry_oak_button"), false).get();
	public static final Block CHERRY_OAK_FENCE = DawnFactory.fence(Promenade.id("cherry_oak_fence"), CHERRY_OAK_PLANKS, false).get();
	public static final Block CHERRY_OAK_FENCE_GATE = DawnFactory.fenceGate(Promenade.id("cherry_oak_fence_gate"), CHERRY_OAK_PLANKS, false).get();
	public static final Block CHERRY_OAK_DOOR = DawnFactory.door(Promenade.id("cherry_oak_door"), CHERRY_OAK_PLANKS).get();

	public static final Block PINK_CHERRY_OAK_SAPLING = DawnFactory.sapling(Promenade.id("pink_cherry_oak_sapling"), new SimpleSaplingGenerator("cherry_oak/pink", "005")).get();
	public static final Block POTTED_PINK_CHERRY_OAK_SAPLING = DawnFactory.potted(PINK_CHERRY_OAK_SAPLING).get();
	public static final Block PINK_CHERRY_OAK_LEAVES = DawnFactory.leaves(Promenade.id("pink_cherry_oak_leaves"), BlockSoundGroup.AZALEA_LEAVES).get();
	public static final Block PINK_CHERRY_OAK_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("pink_cherry_oak_leaf_pile"), BlockSoundGroup.AZALEA_LEAVES).get();
	public static final DefaultParticleType PINK_CHERRY_BLOSSOM = DawnFactory.particle(Promenade.id("pink_cherry_blossom"));

	public static final Block WHITE_CHERRY_OAK_SAPLING = DawnFactory.sapling(Promenade.id("white_cherry_oak_sapling"), new SimpleSaplingGenerator("cherry_oak/white", "005")).get();
	public static final Block POTTED_WHITE_CHERRY_OAK_SAPLING = DawnFactory.potted(WHITE_CHERRY_OAK_SAPLING).get();
	public static final Block WHITE_CHERRY_OAK_LEAVES = DawnFactory.leaves(Promenade.id("white_cherry_oak_leaves"), BlockSoundGroup.AZALEA_LEAVES).get();
	public static final Block WHITE_CHERRY_OAK_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("white_cherry_oak_leaf_pile"), BlockSoundGroup.AZALEA_LEAVES).get();
	public static final DefaultParticleType WHITE_CHERRY_BLOSSOM = DawnFactory.particle(Promenade.id("white_cherry_blossom"));

	public static final RegistryKey<Biome> PINK_CHERRY_OAK_FOREST = DawnFactory.biomeKey(Promenade.id("pink_cherry_oak_forest"));
	public static final RegistryKey<Biome> WHITE_CHERRY_OAK_FOREST = DawnFactory.biomeKey(Promenade.id("white_cherry_oak_forest"));

	public static void init() {
		if(Promenade.CONFIG.biomes.cherry_oak_forests_weight > 0) {
			//TODO : add back to overworld generation when the Biome API supports that
			//OverworldBiomes.addContinentalBiome(PINK_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D)
			//OverworldBiomes.addContinentalBiome(WHITE_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D);
		}

		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(PINK_CHERRY_OAK_SAPLING));
			factories.add(TradeOfferUtils.sapling(WHITE_CHERRY_OAK_SAPLING));
		});
	}
}
