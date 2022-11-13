package com.hugman.promenade.content;

import com.hugman.newdawn.DawnFactory;
import com.hugman.newdawn.RegistryHelper;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.PromenadeFactory;
import com.hugman.promenade.object.block.sapling_generator.SimpleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class CherryContent {
	private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
	private static final MapColor WOOD_COLOR = MapColor.DULL_PINK;
	private static final MapColor PLANKS_COLOR = MapColor.DULL_RED;

	public static final Block STRIPPED_CHERRY_OAK_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block CHERRY_OAK_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR, BARK_COLOR).stripInto(STRIPPED_CHERRY_OAK_LOG));
	public static final Block STRIPPED_CHERRY_OAK_WOOD = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block CHERRY_OAK_WOOD = new PillarBlock(DawnFactory.logSettings(false, BARK_COLOR));

	public static final Block CHERRY_OAK_PLANKS = DawnFactory.planks(false, PLANKS_COLOR);
	public static final Block CHERRY_OAK_STAIRS = DawnFactory.stairs(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_SLAB = DawnFactory.slab(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_FENCE = DawnFactory.fence(false, CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_FENCE_GATE = DawnFactory.fenceGate(false, CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_DOOR = DawnFactory.door(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_TRAPDOOR = DawnFactory.trapdoor(CHERRY_OAK_PLANKS);
	public static final Block CHERRY_OAK_BUTTON = DawnFactory.woodenButton(false);
	public static final Block CHERRY_OAK_PRESSURE_PLATE = DawnFactory.pressurePlate(CHERRY_OAK_PLANKS);

	public static final Block PINK_CHERRY_OAK_SAPLING = DawnFactory.sapling(new SimpleSaplingGenerator("cherry_oak/pink", "005"));
	public static final Block POTTED_PINK_CHERRY_OAK_SAPLING = DawnFactory.potted(PINK_CHERRY_OAK_SAPLING);
	public static final Block PINK_CHERRY_OAK_LEAVES = DawnFactory.leaves(BlockSoundGroup.AZALEA_LEAVES);
	public static final Block PINK_CHERRY_OAK_LEAF_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);
	public static final DefaultParticleType PINK_CHERRY_BLOSSOM = FabricParticleTypes.simple();

	public static final Block WHITE_CHERRY_OAK_SAPLING = DawnFactory.sapling(new SimpleSaplingGenerator("cherry_oak/white", "005"));
	public static final Block POTTED_WHITE_CHERRY_OAK_SAPLING = DawnFactory.potted(WHITE_CHERRY_OAK_SAPLING);
	public static final Block WHITE_CHERRY_OAK_LEAVES = DawnFactory.leaves(BlockSoundGroup.AZALEA_LEAVES);
	public static final Block WHITE_CHERRY_OAK_LEAF_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);
	public static final DefaultParticleType WHITE_CHERRY_BLOSSOM = FabricParticleTypes.simple();

	public static final RegistryKey<Biome> PINK_CHERRY_OAK_FOREST = DawnFactory.biomeKey(Promenade.id("pink_cherry_oak_forest"));
	public static final RegistryKey<Biome> WHITE_CHERRY_OAK_FOREST = DawnFactory.biomeKey(Promenade.id("white_cherry_oak_forest"));

	public static void init() {
		RegistryHelper.block(Promenade.id("cherry_oak_log"), CHERRY_OAK_LOG);
		RegistryHelper.block(Promenade.id("stripped_cherry_oak_log"), STRIPPED_CHERRY_OAK_LOG);
		RegistryHelper.block(Promenade.id("cherry_oak_wood"), CHERRY_OAK_WOOD);
		RegistryHelper.block(Promenade.id("stripped_cherry_oak_wood"), STRIPPED_CHERRY_OAK_WOOD);

		RegistryHelper.block(Promenade.id("cherry_oak_planks"), CHERRY_OAK_PLANKS);
		RegistryHelper.block(Promenade.id("cherry_oak_stairs"), CHERRY_OAK_STAIRS);
		RegistryHelper.block(Promenade.id("cherry_oak_slab"), CHERRY_OAK_SLAB);
		RegistryHelper.block(Promenade.id("cherry_oak_fence"), CHERRY_OAK_FENCE);
		RegistryHelper.block(Promenade.id("cherry_oak_fence_gate"), CHERRY_OAK_FENCE_GATE);
		RegistryHelper.block(Promenade.id("cherry_oak_door"), CHERRY_OAK_DOOR);
		RegistryHelper.block(Promenade.id("cherry_oak_trapdoor"), CHERRY_OAK_TRAPDOOR);
		RegistryHelper.block(Promenade.id("cherry_oak_button"), CHERRY_OAK_BUTTON);
		RegistryHelper.block(Promenade.id("cherry_oak_pressure_plate"), CHERRY_OAK_PRESSURE_PLATE);

		RegistryHelper.block(Promenade.id("pink_cherry_oak_sapling"), PINK_CHERRY_OAK_SAPLING);
		RegistryHelper.block(Promenade.id("potted_pink_cherry_oak_sapling"), POTTED_PINK_CHERRY_OAK_SAPLING);
		RegistryHelper.block(Promenade.id("pink_cherry_oak_leaves"), PINK_CHERRY_OAK_LEAVES);
		RegistryHelper.block(Promenade.id("pink_cherry_oak_leaf_pile"), PINK_CHERRY_OAK_LEAF_PILE);
		RegistryHelper.particle(Promenade.id("pink_cherry_blossom"), PINK_CHERRY_BLOSSOM);

		RegistryHelper.block(Promenade.id("white_cherry_oak_sapling"), WHITE_CHERRY_OAK_SAPLING);
		RegistryHelper.block(Promenade.id("potted_white_cherry_oak_sapling"), POTTED_WHITE_CHERRY_OAK_SAPLING);
		RegistryHelper.block(Promenade.id("white_cherry_oak_leaves"), WHITE_CHERRY_OAK_LEAVES);
		RegistryHelper.block(Promenade.id("white_cherry_oak_leaf_pile"), WHITE_CHERRY_OAK_LEAF_PILE);
		RegistryHelper.particle(Promenade.id("white_cherry_blossom"), WHITE_CHERRY_BLOSSOM);

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
