package com.hugman.promenade.content;

import com.hugman.newdawn.DawnFactory;
import com.hugman.promenade.PromenadeFactory;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.object.block.sapling_generator.SingleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class PalmContent {
	private static final MapColor BARK_COLOR = MapColor.TERRACOTTA_CYAN;
	private static final MapColor WOOD_COLOR = MapColor.ORANGE;

	public static final Block PALM_LOG = DawnFactory.log(Promenade.id("palm_log"), WOOD_COLOR, BARK_COLOR, false).get();
	public static final Block STRIPPED_PALM_LOG = DawnFactory.log(Promenade.id("stripped_palm_log"), WOOD_COLOR, false).strippedFrom(PALM_LOG).get();
	public static final Block PALM_WOOD = DawnFactory.log(Promenade.id("palm_wood"), BARK_COLOR, false).get();
	public static final Block STRIPPED_PALM_WOOD = DawnFactory.log(Promenade.id("stripped_palm_wood"), WOOD_COLOR, false).strippedFrom(PALM_WOOD).get();

	public static final Block PALM_PLANKS = DawnFactory.planks(Promenade.id("palm_planks"), WOOD_COLOR, false).get();
	public static final Block PALM_STAIRS = DawnFactory.stairs(Promenade.id("palm_stairs"), PALM_PLANKS, false).get();
	public static final Block PALM_SLAB = DawnFactory.slab(Promenade.id("palm_slab"), PALM_PLANKS, false).get();
	public static final Block PALM_TRAPDOOR = DawnFactory.trapdoor(Promenade.id("palm_trapdoor"), PALM_PLANKS, false).get();
	public static final Block PALM_PRESSURE_PLATE = DawnFactory.pressurePlate(Promenade.id("palm_pressure_plate"), PALM_PLANKS, false).get();
	public static final Block PALM_BUTTON = DawnFactory.woodenButton(Promenade.id("palm_button"), false).get();
	public static final Block PALM_FENCE = DawnFactory.fence(Promenade.id("palm_fence"), PALM_PLANKS, false).get();
	public static final Block PALM_FENCE_GATE = DawnFactory.fenceGate(Promenade.id("palm_fence_gate"), PALM_PLANKS, false).get();
	public static final Block PALM_DOOR = DawnFactory.door(Promenade.id("palm_door"), PALM_PLANKS).get();

	public static final Block PALM_SAPLING = DawnFactory.sapling(Promenade.id("palm_sapling"), new SingleSaplingGenerator(Promenade.id("tree/palm")), state -> state.isIn(BlockTags.SAND)).get();
	public static final Block POTTED_PALM_SAPLING = DawnFactory.potted(PALM_SAPLING).get();
	public static final Block PALM_LEAVES = DawnFactory.leaves(Promenade.id("palm_leaves")).get();
	public static final Block PALM_LEAF_PILE = PromenadeFactory.leafPile(Promenade.id("palm_leaf_pile")).get();

	public static final RegistryKey<PlacedFeature> PALMS = DawnFactory.placedFeatureKey(Promenade.id("trees/palms"));

	public static void init() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(TradeOfferUtils.sapling(PALM_SAPLING)));

		if(Promenade.CONFIG.world_features.palms) {
			BiomeModifications.addFeature(BiomeSelectors.tag(PromenadeTags.Biomes.HAS_PALMS), GenerationStep.Feature.VEGETAL_DECORATION, PALMS);
		}
	}
}
