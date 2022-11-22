package fr.hugman.promenade.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.block.sapling.SingleSaplingGenerator;
import fr.hugman.promenade.item.ItemGroupHelper;
import fr.hugman.promenade.registry.tag.PromenadeTags;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
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
	public static final Block PALM_DOOR = DawnFactory.woodenDoor(PALM_PLANKS);
	public static final Block PALM_TRAPDOOR = DawnFactory.woodenTrapdoor(PALM_PLANKS);
	public static final Block PALM_BUTTON = DawnFactory.woodenButton(false);
	public static final Block PALM_PRESSURE_PLATE = DawnFactory.pressurePlate(PALM_PLANKS);

	public static final Block PALM_SAPLING = DawnFactory.sapling(new SingleSaplingGenerator(Promenade.id("tree/palm")), state -> state.isIn(BlockTags.SAND));
	public static final Block POTTED_PALM_SAPLING = DawnFactory.potted(PALM_SAPLING);
	public static final Block PALM_LEAVES = DawnFactory.leaves();
	public static final Block PALM_LEAF_PILE = PromenadeFactory.leafPile();

	public static final RegistryKey<PlacedFeature> PALMS = DawnFactory.placedFeature(Promenade.id("trees/palms"));

	public static void init() {
		Registrar.add(Promenade.id("palm_log"), PALM_LOG);
		Registrar.add(Promenade.id("stripped_palm_log"), STRIPPED_PALM_LOG);
		Registrar.add(Promenade.id("palm_wood"), PALM_WOOD);
		Registrar.add(Promenade.id("stripped_palm_wood"), STRIPPED_PALM_WOOD);

		Registrar.add(Promenade.id("palm_planks"), PALM_PLANKS);
		Registrar.add(Promenade.id("palm_stairs"), PALM_STAIRS);
		Registrar.add(Promenade.id("palm_slab"), PALM_SLAB);
		Registrar.add(Promenade.id("palm_fence"), PALM_FENCE);
		Registrar.add(Promenade.id("palm_fence_gate"), PALM_FENCE_GATE);
		Registrar.add(Promenade.id("palm_door"), PALM_DOOR);
		Registrar.add(Promenade.id("palm_trapdoor"), PALM_TRAPDOOR);
		Registrar.add(Promenade.id("palm_button"), PALM_BUTTON);
		Registrar.add(Promenade.id("palm_pressure_plate"), PALM_PRESSURE_PLATE);

		Registrar.add(Promenade.id("palm_sapling"), PALM_SAPLING);
		Registrar.add(Promenade.id("potted_palm_sapling"), POTTED_PALM_SAPLING);
		Registrar.add(Promenade.id("palm_leaves"), PALM_LEAVES);
		Registrar.add(Promenade.id("palm_leaf_pile"), PALM_LEAF_PILE);

		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(TradeOfferUtils.sapling(PALM_SAPLING)));

		if(Promenade.CONFIG.world_features.palms) {
			BiomeModifications.addFeature(BiomeSelectors.tag(PromenadeTags.Biomes.HAS_PALMS), GenerationStep.Feature.VEGETAL_DECORATION, PALMS);
		}

		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> {
			e.addAfter(Blocks.ACACIA_BUTTON,
					PALM_LOG,
					PALM_WOOD,
					STRIPPED_PALM_LOG,
					STRIPPED_PALM_WOOD,
					PALM_PLANKS,
					PALM_STAIRS,
					PALM_SLAB,
					PALM_FENCE,
					PALM_FENCE_GATE,
					PALM_DOOR,
					PALM_TRAPDOOR,
					PALM_PRESSURE_PLATE,
					PALM_BUTTON);
		});
		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.ACACIA_LOG, PALM_LOG);
			e.addAfter(Blocks.ACACIA_LEAVES, PALM_LEAVES);
			e.addAfter(Blocks.ACACIA_SAPLING, PALM_SAPLING);
			e.addAfter(VanillaPilesContent.ACACIA_LEAF_PILE, PALM_LEAF_PILE);
		});
		// TODO
		// ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.ACACIA_HANGING_SIGN, PALM_SIGN, PALM_HANGING_SIGN));
		// ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Item.ACACIA_CHEST_BOAT, PALM_BOAT, PALM_CHEST_BOAT));
	}
}