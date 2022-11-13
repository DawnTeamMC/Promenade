package com.hugman.promenade.content;

import com.hugman.newdawn.DawnFactory;
import com.hugman.newdawn.RegistryHelper;
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
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class PalmContent {
	private static final MapColor BARK_COLOR = MapColor.TERRACOTTA_CYAN;
	private static final MapColor WOOD_COLOR = MapColor.ORANGE;

	public static final Block STRIPPED_PALM_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block PALM_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR, BARK_COLOR).stripInto(STRIPPED_PALM_LOG));
	public static final Block STRIPPED_PALM_WOOD = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block PALM_WOOD = new PillarBlock(DawnFactory.logSettings(false, BARK_COLOR).stripInto(STRIPPED_PALM_WOOD));

	public static final Block PALM_PLANKS = DawnFactory.planks(false, WOOD_COLOR);
	public static final Block PALM_STAIRS = DawnFactory.stairs(PALM_PLANKS);
	public static final Block PALM_SLAB = DawnFactory.slab(PALM_PLANKS);
	public static final Block PALM_FENCE = DawnFactory.fence(false, PALM_PLANKS);
	public static final Block PALM_FENCE_GATE = DawnFactory.fenceGate(false, PALM_PLANKS);
	public static final Block PALM_DOOR = DawnFactory.door(PALM_PLANKS);
	public static final Block PALM_TRAPDOOR = DawnFactory.trapdoor(PALM_PLANKS);
	public static final Block PALM_BUTTON = DawnFactory.woodenButton(false);
	public static final Block PALM_PRESSURE_PLATE = DawnFactory.pressurePlate(PALM_PLANKS);

	public static final Block PALM_SAPLING = DawnFactory.sapling(new SingleSaplingGenerator(Promenade.id("tree/palm")), state -> state.isIn(BlockTags.SAND));
	public static final Block POTTED_PALM_SAPLING = DawnFactory.potted(PALM_SAPLING);
	public static final Block PALM_LEAVES = DawnFactory.leaves();
	public static final Block PALM_LEAF_PILE = PromenadeFactory.leafPile();

	public static final RegistryKey<PlacedFeature> PALMS = DawnFactory.placedFeatureKey(Promenade.id("trees/palms"));

	public static void init() {
		RegistryHelper.block(Promenade.id("palm_log"), PALM_LOG);
		RegistryHelper.block(Promenade.id("stripped_palm_log"), STRIPPED_PALM_LOG);
		RegistryHelper.block(Promenade.id("palm_wood"), PALM_WOOD);
		RegistryHelper.block(Promenade.id("stripped_palm_wood"), STRIPPED_PALM_WOOD);

		RegistryHelper.block(Promenade.id("palm_planks"), PALM_PLANKS);
		RegistryHelper.block(Promenade.id("palm_stairs"), PALM_STAIRS);
		RegistryHelper.block(Promenade.id("palm_slab"), PALM_SLAB);
		RegistryHelper.block(Promenade.id("palm_fence"), PALM_FENCE);
		RegistryHelper.block(Promenade.id("palm_fence_gate"), PALM_FENCE_GATE);
		RegistryHelper.block(Promenade.id("palm_door"), PALM_DOOR);
		RegistryHelper.block(Promenade.id("palm_trapdoor"), PALM_TRAPDOOR);
		RegistryHelper.block(Promenade.id("palm_button"), PALM_BUTTON);
		RegistryHelper.block(Promenade.id("palm_pressure_plate"), PALM_PRESSURE_PLATE);

		RegistryHelper.block(Promenade.id("palm_sapling"), PALM_SAPLING);
		RegistryHelper.block(Promenade.id("potted_palm_sapling"), POTTED_PALM_SAPLING);
		RegistryHelper.block(Promenade.id("palm_leaves"), PALM_LEAVES);
		RegistryHelper.block(Promenade.id("palm_leaf_pile"), PALM_LEAF_PILE);

		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(TradeOfferUtils.sapling(PALM_SAPLING)));

		if(Promenade.CONFIG.world_features.palms) {
			BiomeModifications.addFeature(BiomeSelectors.tag(PromenadeTags.Biomes.HAS_PALMS), GenerationStep.Feature.VEGETAL_DECORATION, PALMS);
		}
	}
}
