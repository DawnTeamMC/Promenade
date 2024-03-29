package fr.hugman.promenade.registry.content;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.block.sapling.SingleSaplingGenerator;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.block.ExtendedLeavesBlock;
import fr.hugman.promenade.block.HangingLeavesBlock;
import fr.hugman.promenade.block.MoaiBlock;
import fr.hugman.promenade.registry.tag.PromenadeBiomeTags;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class TropicalContent {
	public static final BlockSetType BLOCK_SET_TYPE = BlockSetTypeRegistry.registerWood(Promenade.id("palm"));
	public static final WoodType WOOD_TYPE = WoodTypeRegistry.register(Promenade.id("palm"), BLOCK_SET_TYPE);
	private static final BlockSoundGroup WOOD_SOUNDS = BlockSoundGroup.WOOD;
	private static final MapColor BARK_COLOR = MapColor.SPRUCE_BROWN;
	private static final MapColor WOOD_COLOR = MapColor.ORANGE;
	private static final MapColor LEAVES_COLOR = MapColor.DARK_GREEN;

	public static final Block STRIPPED_PALM_LOG = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, WOOD_SOUNDS, true));
	public static final Block PALM_LOG = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, BARK_COLOR, WOOD_SOUNDS, true).stripsInto(STRIPPED_PALM_LOG));
	public static final Block STRIPPED_PALM_WOOD = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, WOOD_SOUNDS, true));
	public static final Block PALM_WOOD = new PillarBlock(DawnFactory.logSettings(BARK_COLOR, WOOD_SOUNDS, true).stripsInto(STRIPPED_PALM_WOOD));

	public static final Block PALM_PLANKS = DawnFactory.planks(WOOD_COLOR, WOOD_SOUNDS, true);
	public static final Block PALM_STAIRS = DawnFactory.stairs(PALM_PLANKS);
	public static final Block PALM_SLAB = DawnFactory.slab(PALM_PLANKS);
	public static final Block PALM_FENCE = DawnFactory.fence(PALM_PLANKS);
	public static final Block PALM_FENCE_GATE = DawnFactory.fenceGate(PALM_PLANKS, WOOD_TYPE);
	public static final Block PALM_DOOR = DawnFactory.door(PALM_PLANKS, BLOCK_SET_TYPE);
	public static final Block PALM_TRAPDOOR = DawnFactory.trapdoor(PALM_PLANKS, BLOCK_SET_TYPE);
	public static final Block PALM_BUTTON = DawnFactory.woodenButton(PALM_PLANKS, BLOCK_SET_TYPE);
	public static final Block PALM_PRESSURE_PLATE = DawnFactory.pressurePlate(PALM_PLANKS, BLOCK_SET_TYPE);

	public static final SignBlocks PALM_SIGNS = DawnFactory.signs(Promenade.id("palm"), PALM_PLANKS);
	public static final TerraformBoatType PALM_BOAT_TYPE = DawnFactory.boat(Promenade.id("palm"), Items.OAK_PLANKS); //TODO change when possible (PR #72 on TerraformersMC/Terraform)

	public static final Block PALM_SAPLING = DawnFactory.sapling(LEAVES_COLOR, new SingleSaplingGenerator(Promenade.id("tree/palm")), state -> state.isIn(BlockTags.SAND));
	public static final Block POTTED_PALM_SAPLING = DawnFactory.potted(PALM_SAPLING);
	public static final Block PALM_LEAVES = new ExtendedLeavesBlock(DawnBlockSettings.create()
			.item(new DawnItemSettings().compostingChance(0.3f))
			.mapColor(LEAVES_COLOR)
			.strength(0.2f)
			.ticksRandomly()
			.sounds(BlockSoundGroup.GRASS)
			.nonOpaque()
			.allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
			.suffocates((state, world, pos) -> false)
			.blockVision((state, world, pos) -> false)
			.burnable(30, 60)
			.pistonBehavior(PistonBehavior.DESTROY)
			.solidBlock((state, world, pos) -> false));
	public static final Block PALM_HANGING_LEAVES = new HangingLeavesBlock(DawnBlockSettings.create()
			.item(new DawnItemSettings().compostingChance(0.3F))
			.mapColor(LEAVES_COLOR)
			.sounds(BlockSoundGroup.GRASS)
			.replaceable().noCollision().breakInstantly()
			.burnable(30, 60)
			.pistonBehavior(PistonBehavior.DESTROY)
	);
	public static final Block PALM_LEAF_PILE = PromenadeFactory.leafPile();

	public static final Block MOAI = new MoaiBlock(DawnBlockSettings.copyOf(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).item(new DawnItemSettings().equipmentSlot(stack -> EquipmentSlot.HEAD)));

	public static final RegistryKey<PlacedFeature> PALMS = DawnFactory.placedFeature(Promenade.id("trees/palms"));

	public static void register(Registrar r) {
		r.add(("palm_log"), PALM_LOG);
		r.add(("stripped_palm_log"), STRIPPED_PALM_LOG);
		r.add(("palm_wood"), PALM_WOOD);
		r.add(("stripped_palm_wood"), STRIPPED_PALM_WOOD);

		r.add(("palm_planks"), PALM_PLANKS);
		r.add(("palm_stairs"), PALM_STAIRS);
		r.add(("palm_slab"), PALM_SLAB);
		r.add(("palm_fence"), PALM_FENCE);
		r.add(("palm_fence_gate"), PALM_FENCE_GATE);
		r.add(("palm_door"), PALM_DOOR);
		r.add(("palm_trapdoor"), PALM_TRAPDOOR);
		r.add(("palm_button"), PALM_BUTTON);
		r.add(("palm_pressure_plate"), PALM_PRESSURE_PLATE);

		r.add(("palm"), PALM_SIGNS);
		r.add(("palm"), PALM_BOAT_TYPE);

		r.add(("palm_sapling"), PALM_SAPLING);
		r.add(("potted_palm_sapling"), POTTED_PALM_SAPLING);
		r.add(("palm_leaves"), PALM_LEAVES);
		r.add(("palm_hanging_leaves"), PALM_HANGING_LEAVES);
		r.add(("palm_leaf_pile"), PALM_LEAF_PILE);

		r.add(("moai"), MOAI);

		appendItemGroups();
		appendVillagerTrades();
		appendWorldGen();
	}

	private static void appendItemGroups() {
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
			e.addAfter(Blocks.ACACIA_LEAVES, PALM_LEAVES, PALM_HANGING_LEAVES);
			e.addAfter(Blocks.ACACIA_SAPLING, PALM_SAPLING);
			e.addAfter(VanillaPilesContent.ACACIA_LEAF_PILE, PALM_LEAF_PILE);
		});
		ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.ACACIA_HANGING_SIGN, PALM_SIGNS.sign(), PALM_SIGNS.hangingSign()));
		ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.ACACIA_CHEST_BOAT, PALM_BOAT_TYPE.getItem(), PALM_BOAT_TYPE.getChestItem()));

		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.CUT_RED_SANDSTONE_SLAB, MOAI));
	}

	private static void appendVillagerTrades() {
		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(TradeOfferUtils.sapling(PALM_SAPLING)));
	}

	private static void appendWorldGen() {
		if(Promenade.CONFIG.world_features.palms) {
			BiomeModifications.addFeature(BiomeSelectors.tag(PromenadeBiomeTags.HAS_PALMS), GenerationStep.Feature.VEGETAL_DECORATION, PALMS);
		}
	}
}
