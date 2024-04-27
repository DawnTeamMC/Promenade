package fr.hugman.promenade.block;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.block.DawnRootsBlock;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.item.PromenadeItemKeys;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.particle.PromenadeParticleTypes;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.village.TradeOfferUtils;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatureKeys;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;

public class PromenadeBlocks {
    /* ========= */
    /*   ROCKS   */
    /* ========= */
    public static final Block ASPHALT = of(PromenadeBlockKeys.ASPHALT, new Block(AbstractBlock.Settings.copy(Blocks.ANDESITE).item().mapColor(MapColor.DEEPSLATE_GRAY).sounds(BlockSoundGroup.BASALT)));
    public static final Block ASPHALT_SLAB = of(PromenadeBlockKeys.ASPHALT_SLAB, DawnFactory.slab(ASPHALT));
    public static final Block ASPHALT_STAIRS = of(PromenadeBlockKeys.ASPHALT_STAIRS, DawnFactory.stairs(ASPHALT));
    public static final Block ASPHALT_WALL = of(PromenadeBlockKeys.ASPHALT_WALL, DawnFactory.wall(ASPHALT));

    public static final Block POLISHED_ASPHALT = of(PromenadeBlockKeys.POLISHED_ASPHALT, new Block(AbstractBlock.Settings.copy(ASPHALT)));
    public static final Block POLISHED_ASPHALT_SLAB = of(PromenadeBlockKeys.POLISHED_ASPHALT_SLAB, DawnFactory.slab(POLISHED_ASPHALT));
    public static final Block POLISHED_ASPHALT_STAIRS = of(PromenadeBlockKeys.POLISHED_ASPHALT_STAIRS, DawnFactory.stairs(POLISHED_ASPHALT));

    public static final Block BLUNITE = of(PromenadeBlockKeys.BLUNITE, new Block(AbstractBlock.Settings.copy(Blocks.ANDESITE).item().mapColor(MapColor.TERRACOTTA_CYAN).sounds(BlockSoundGroup.TUFF)));
    public static final Block BLUNITE_SLAB = of(PromenadeBlockKeys.BLUNITE_SLAB, DawnFactory.slab(BLUNITE));
    public static final Block BLUNITE_STAIRS = of(PromenadeBlockKeys.BLUNITE_STAIRS, DawnFactory.stairs(BLUNITE));
    public static final Block BLUNITE_WALL = of(PromenadeBlockKeys.BLUNITE_WALL, DawnFactory.wall(BLUNITE));

    public static final Block POLISHED_BLUNITE = of(PromenadeBlockKeys.POLISHED_BLUNITE, new Block(AbstractBlock.Settings.copy(BLUNITE)));
    public static final Block POLISHED_BLUNITE_SLAB = of(PromenadeBlockKeys.POLISHED_BLUNITE_SLAB, DawnFactory.slab(POLISHED_BLUNITE));
    public static final Block POLISHED_BLUNITE_STAIRS = of(PromenadeBlockKeys.POLISHED_BLUNITE_STAIRS, DawnFactory.stairs(POLISHED_BLUNITE));


    /* ================= */
    /*   VANILLA PILES   */
    /* ================= */
    public static final Block OAK_LEAF_PILE = of(PromenadeBlockKeys.OAK_LEAF_PILE, PromenadeFactory.leafPile());
    public static final Block SPRUCE_LEAF_PILE = of(PromenadeBlockKeys.SPRUCE_LEAF_PILE, PromenadeFactory.leafPile());
    public static final Block BIRCH_LEAF_PILE = of(PromenadeBlockKeys.BIRCH_LEAF_PILE, PromenadeFactory.leafPile());
    public static final Block JUNGLE_LEAF_PILE = of(PromenadeBlockKeys.JUNGLE_LEAF_PILE, PromenadeFactory.leafPile());
    public static final Block ACACIA_LEAF_PILE = of(PromenadeBlockKeys.ACACIA_LEAF_PILE, PromenadeFactory.leafPile());
    public static final Block DARK_OAK_LEAF_PILE = of(PromenadeBlockKeys.DARK_OAK_LEAF_PILE, PromenadeFactory.leafPile());
    public static final Block MANGROVE_LEAF_PILE = of(PromenadeBlockKeys.MANGROVE_LEAF_PILE, PromenadeFactory.leafPile());
    public static final Block AZALEA_LEAF_PILE = of(PromenadeBlockKeys.AZALEA_LEAF_PILE, PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES));
    public static final Block FLOWERING_AZALEA_LEAF_PILE = of(PromenadeBlockKeys.FLOWERING_AZALEA_LEAF_PILE, PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES));

    public static final Block DANDELION_PILE = of(PromenadeBlockKeys.DANDELION_PILE, PromenadeFactory.leafPile(MapColor.GOLD));
    public static final Block POPPY_PILE = of(PromenadeBlockKeys.POPPY_PILE, PromenadeFactory.leafPile(MapColor.DULL_RED));
    public static final Block BLUE_ORCHID_PILE = of(PromenadeBlockKeys.BLUE_ORCHID_PILE, PromenadeFactory.leafPile(MapColor.LIGHT_BLUE));
    public static final Block ALLIUM_PILE = of(PromenadeBlockKeys.ALLIUM_PILE, PromenadeFactory.leafPile(MapColor.MAGENTA));
    public static final Block AZURE_BLUET_PILE = of(PromenadeBlockKeys.AZURE_BLUET_PILE, PromenadeFactory.leafPile(MapColor.PALE_YELLOW));
    public static final Block RED_TULIP_PILE = of(PromenadeBlockKeys.RED_TULIP_PILE, PromenadeFactory.leafPile(MapColor.DULL_RED));
    public static final Block ORANGE_TULIP_PILE = of(PromenadeBlockKeys.ORANGE_TULIP_PILE, PromenadeFactory.leafPile(MapColor.ORANGE));
    public static final Block WHITE_TULIP_PILE = of(PromenadeBlockKeys.WHITE_TULIP_PILE, PromenadeFactory.leafPile(MapColor.WHITE));
    public static final Block PINK_TULIP_PILE = of(PromenadeBlockKeys.PINK_TULIP_PILE, PromenadeFactory.leafPile(MapColor.PINK));
    public static final Block OXEYE_DAISY_PILE = of(PromenadeBlockKeys.OXEYE_DAISY_PILE, PromenadeFactory.leafPile(MapColor.GOLD));
    public static final Block CORNFLOWER_PILE = of(PromenadeBlockKeys.CORNFLOWER_PILE, PromenadeFactory.leafPile(MapColor.LAPIS_BLUE));
    public static final Block LILY_OF_THE_VALLEY_PILE = of(PromenadeBlockKeys.LILY_OF_THE_VALLEY_PILE, PromenadeFactory.leafPile(MapColor.WHITE));
    //TODO: put the next block in PromenadeFactory
    public static final Block WITHER_ROSE_PILE = of(PromenadeBlockKeys.WITHER_ROSE_PILE, new WitherRosePileBlock(AbstractBlock.Settings.create()
            .item(new Item.Settings().compostingChance(0.3f))
            .mapColor(MapColor.BLACK)
            .burnable(30, 60)
            .strength(0.1f)
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .nonOpaque()));


    /* ======================== */
    /*   VANILLA SNOWY LEAVES   */
    /* ======================== */
    public static final BlockSoundGroup SNOWY_LEAVES_SOUNDS = new BlockSoundGroup(1.0f, 1.0f, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_BREAK, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_STEP, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_PLACE, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_HIT, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_FALL);

    public static final SnowyLeavesBlock SNOWY_SPRUCE_LEAVES = of(PromenadeBlockKeys.SNOWY_SPRUCE_LEAVES, PromenadeFactory.snowyLeaves());


    /* ========== */
    /*   SAKURA   */
    /* ========== */
    private static final BlockSoundGroup SAKURA_WOOD_SOUNDS = BlockSoundGroup.CHERRY_WOOD;
    private static final MapColor SAKURA_BARK_COLOR = MapColor.TERRACOTTA_BROWN;
    private static final MapColor SAKURA_WOOD_COLOR = MapColor.TERRACOTTA_BROWN;

    private static final MapColor BLUSH_BLOSSOMS_COLOR = MapColor.PINK;
    private static final MapColor COTTON_BLOSSOMS_COLOR = MapColor.OFF_WHITE;


    public static final Block STRIPPED_SAKURA_LOG = of(PromenadeBlockKeys.STRIPPED_SAKURA_LOG, new PillarBlock(DawnFactory.logSettings(SAKURA_WOOD_COLOR, SAKURA_WOOD_SOUNDS, true)));
    public static final Block SAKURA_LOG = of(PromenadeBlockKeys.SAKURA_LOG, new PillarBlock(DawnFactory.logSettings(SAKURA_WOOD_COLOR, SAKURA_BARK_COLOR, SAKURA_WOOD_SOUNDS, true).stripsInto(STRIPPED_SAKURA_LOG)));
    public static final Block STRIPPED_SAKURA_WOOD = of(PromenadeBlockKeys.STRIPPED_SAKURA_WOOD, new PillarBlock(DawnFactory.logSettings(SAKURA_WOOD_COLOR, SAKURA_WOOD_SOUNDS, true)));
    public static final Block SAKURA_WOOD = of(PromenadeBlockKeys.SAKURA_WOOD, new PillarBlock(DawnFactory.logSettings(SAKURA_BARK_COLOR, SAKURA_WOOD_SOUNDS, true).stripsInto(STRIPPED_SAKURA_WOOD)));

    public static final Block SAKURA_PLANKS = of(PromenadeBlockKeys.SAKURA_PLANKS, DawnFactory.planks(SAKURA_WOOD_COLOR, SAKURA_WOOD_SOUNDS, true));
    public static final Block SAKURA_STAIRS = of(PromenadeBlockKeys.SAKURA_STAIRS, DawnFactory.stairs(SAKURA_PLANKS));
    public static final Block SAKURA_SLAB = of(PromenadeBlockKeys.SAKURA_SLAB, DawnFactory.slab(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE = of(PromenadeBlockKeys.SAKURA_FENCE, DawnFactory.fence(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE_GATE = of(PromenadeBlockKeys.SAKURA_FENCE_GATE, DawnFactory.fenceGate(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_DOOR = of(PromenadeBlockKeys.SAKURA_DOOR, DawnFactory.door(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_TRAPDOOR = of(PromenadeBlockKeys.SAKURA_TRAPDOOR, DawnFactory.trapdoor(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_BUTTON = of(PromenadeBlockKeys.SAKURA_BUTTON, DawnFactory.woodenButton(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_PRESSURE_PLATE = of(PromenadeBlockKeys.SAKURA_PRESSURE_PLATE, DawnFactory.pressurePlate(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_SIGN = of(PromenadeBlockKeys.SAKURA_SIGN, PromenadeFactory.sign(false, false, Promenade.id("sakura"), SAKURA_PLANKS, SAKURA_WOOD_SOUNDS));
    public static final Block SAKURA_WALL_SIGN = of(PromenadeBlockKeys.SAKURA_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("sakura"), SAKURA_PLANKS, SAKURA_WOOD_SOUNDS));
    public static final Block SAKURA_HANGING_SIGN = of(PromenadeBlockKeys.SAKURA_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("sakura"), SAKURA_PLANKS, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));
    public static final Block SAKURA_WALL_HANGING_SIGN = of(PromenadeBlockKeys.SAKURA_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("sakura"), SAKURA_PLANKS, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));

    public static final Block BLUSH_SAKURA_SAPLING = of(PromenadeBlockKeys.BLUSH_SAKURA_SAPLING, DawnFactory.sapling(BLUSH_BLOSSOMS_COLOR, PromenadeSaplingGenerators.BLUSH_SAKURA_SAPLING_GENERATOR));
    public static final Block POTTED_BLUSH_SAKURA_SAPLING = of(PromenadeBlockKeys.POTTED_BLUSH_SAKURA_SAPLING, DawnFactory.potted(BLUSH_SAKURA_SAPLING));
    public static final Block BLUSH_SAKURA_BLOSSOMS = of(PromenadeBlockKeys.BLUSH_SAKURA_BLOSSOMS, PromenadeFactory.decoratedLeaves(BLUSH_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES, PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM));
    public static final Block BLUSH_SAKURA_BLOSSOM_PILE = of(PromenadeBlockKeys.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeFactory.leafPile(BLUSH_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES));

    public static final Block COTTON_SAKURA_SAPLING = of(PromenadeBlockKeys.COTTON_SAKURA_SAPLING, DawnFactory.sapling(COTTON_BLOSSOMS_COLOR, PromenadeSaplingGenerators.COTTON_SAKURA_SAPLING_GENERATOR));
    public static final Block POTTED_COTTON_SAKURA_SAPLING = of(PromenadeBlockKeys.POTTED_COTTON_SAKURA_SAPLING, DawnFactory.potted(COTTON_SAKURA_SAPLING));
    public static final Block COTTON_SAKURA_BLOSSOMS = of(PromenadeBlockKeys.COTTON_SAKURA_BLOSSOMS, PromenadeFactory.decoratedLeaves(COTTON_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES, PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM));
    public static final Block COTTON_SAKURA_BLOSSOM_PILE = of(PromenadeBlockKeys.COTTON_SAKURA_BLOSSOM_PILE, PromenadeFactory.leafPile(COTTON_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES));


    /* ========= */
    /*   MAPLE   */
    /* ========= */
    private static final BlockSoundGroup MAPLE_WOOD_SOUNDS = BlockSoundGroup.WOOD;
    private static final MapColor MAPLE_BARK_COLOR = MapColor.DEEPSLATE_GRAY;
    private static final MapColor MAPLE_WOOD_COLOR = MapColor.TERRACOTTA_WHITE;

    public static final MapColor MAPLE_SAP_LEAVES_COLOR = MapColor.DARK_GREEN;
    public static final MapColor MAPLE_VERMILION_LEAVES_COLOR = MapColor.DULL_RED;
    public static final MapColor MAPLE_FULVOUS_LEAVES_COLOR = MapColor.ORANGE;
    public static final MapColor MAPLE_MIKADO_LEAVES_COLOR = MapColor.GOLD;


    public static final Block STRIPPED_MAPLE_LOG = of(PromenadeBlockKeys.STRIPPED_MAPLE_LOG, new StrippedMapleLogBlock(DawnFactory.logSettings(MAPLE_WOOD_COLOR, MAPLE_WOOD_SOUNDS, true)));
    public static final Block MAPLE_LOG = of(PromenadeBlockKeys.MAPLE_LOG, new MapleLogBlock(DawnFactory.logSettings(MAPLE_WOOD_COLOR, MAPLE_BARK_COLOR, MAPLE_WOOD_SOUNDS, true).stripsInto(STRIPPED_MAPLE_LOG)));
    public static final Block STRIPPED_MAPLE_WOOD = of(PromenadeBlockKeys.STRIPPED_MAPLE_WOOD, new PillarBlock(DawnFactory.logSettings(MAPLE_WOOD_COLOR, MAPLE_WOOD_SOUNDS, true)));
    public static final Block MAPLE_WOOD = of(PromenadeBlockKeys.MAPLE_WOOD, new PillarBlock(DawnFactory.logSettings(MAPLE_BARK_COLOR, MAPLE_WOOD_SOUNDS, true).stripsInto(STRIPPED_MAPLE_WOOD)));

    public static final Block MAPLE_PLANKS = of(PromenadeBlockKeys.MAPLE_PLANKS, DawnFactory.planks(MAPLE_BARK_COLOR, MAPLE_WOOD_SOUNDS, true));
    public static final Block MAPLE_STAIRS = of(PromenadeBlockKeys.MAPLE_STAIRS, DawnFactory.stairs(MAPLE_PLANKS));
    public static final Block MAPLE_SLAB = of(PromenadeBlockKeys.MAPLE_SLAB, DawnFactory.slab(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE = of(PromenadeBlockKeys.MAPLE_FENCE, DawnFactory.fence(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE_GATE = of(PromenadeBlockKeys.MAPLE_FENCE_GATE, DawnFactory.fenceGate(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_DOOR = of(PromenadeBlockKeys.MAPLE_DOOR, DawnFactory.door(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_TRAPDOOR = of(PromenadeBlockKeys.MAPLE_TRAPDOOR, DawnFactory.trapdoor(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_BUTTON = of(PromenadeBlockKeys.MAPLE_BUTTON, DawnFactory.woodenButton(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_PRESSURE_PLATE = of(PromenadeBlockKeys.MAPLE_PRESSURE_PLATE, DawnFactory.pressurePlate(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_SIGN = of(PromenadeBlockKeys.MAPLE_SIGN, PromenadeFactory.sign(false, false, Promenade.id("maple"), MAPLE_PLANKS, MAPLE_WOOD_SOUNDS));
    public static final Block MAPLE_WALL_SIGN = of(PromenadeBlockKeys.MAPLE_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("maple"), MAPLE_PLANKS, MAPLE_WOOD_SOUNDS));
    public static final Block MAPLE_HANGING_SIGN = of(PromenadeBlockKeys.MAPLE_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("maple"), MAPLE_PLANKS, BlockSoundGroup.HANGING_SIGN));
    public static final Block MAPLE_WALL_HANGING_SIGN = of(PromenadeBlockKeys.MAPLE_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("maple"), MAPLE_PLANKS, BlockSoundGroup.HANGING_SIGN));

    public static final Block SAP_MAPLE_SAPLING = of(PromenadeBlockKeys.SAP_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_SAP_LEAVES_COLOR, PromenadeSaplingGenerators.SAP_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_SAP_MAPLE_SAPLING = of(PromenadeBlockKeys.POTTED_SAP_MAPLE_SAPLING, DawnFactory.potted(SAP_MAPLE_SAPLING));
    public static final Block SAP_MAPLE_LEAVES = of(PromenadeBlockKeys.SAP_MAPLE_LEAVES, DawnFactory.leaves(MAPLE_SAP_LEAVES_COLOR));
    public static final Block SAP_MAPLE_LEAF_PILE = of(PromenadeBlockKeys.SAP_MAPLE_LEAF_PILE, PromenadeFactory.leafPile());

    public static final Block VERMILION_MAPLE_SAPLING = of(PromenadeBlockKeys.VERMILION_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_VERMILION_LEAVES_COLOR, PromenadeSaplingGenerators.VERMILION_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_VERMILION_MAPLE_SAPLING = of(PromenadeBlockKeys.POTTED_VERMILION_MAPLE_SAPLING, DawnFactory.potted(VERMILION_MAPLE_SAPLING));
    public static final Block VERMILION_MAPLE_LEAVES = of(PromenadeBlockKeys.VERMILION_MAPLE_LEAVES, PromenadeFactory.decoratedLeaves(MAPLE_VERMILION_LEAVES_COLOR, PromenadeParticleTypes.VERMILION_MAPLE_LEAF));
    public static final Block VERMILION_MAPLE_LEAF_PILE = of(PromenadeBlockKeys.VERMILION_MAPLE_LEAF_PILE, PromenadeFactory.leafPile(MAPLE_VERMILION_LEAVES_COLOR));
    public static final Block VERMILION_CARPETED_GRASS_BLOCK = of(PromenadeBlockKeys.VERMILION_CARPETED_GRASS_BLOCK, PromenadeFactory.carpetedGrassBlock(MAPLE_VERMILION_LEAVES_COLOR));

    public static final Block FULVOUS_MAPLE_SAPLING = of(PromenadeBlockKeys.FULVOUS_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_FULVOUS_LEAVES_COLOR, PromenadeSaplingGenerators.FULVOUS_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_FULVOUS_MAPLE_SAPLING = of(PromenadeBlockKeys.POTTED_FULVOUS_MAPLE_SAPLING, DawnFactory.potted(FULVOUS_MAPLE_SAPLING));
    public static final Block FULVOUS_MAPLE_LEAVES = of(PromenadeBlockKeys.FULVOUS_MAPLE_LEAVES, PromenadeFactory.decoratedLeaves(MAPLE_FULVOUS_LEAVES_COLOR, PromenadeParticleTypes.FULVOUS_MAPLE_LEAF));
    public static final Block FULVOUS_MAPLE_LEAF_PILE = of(PromenadeBlockKeys.FULVOUS_MAPLE_LEAF_PILE, PromenadeFactory.leafPile(MAPLE_FULVOUS_LEAVES_COLOR));
    public static final Block FULVOUS_CARPETED_GRASS_BLOCK = of(PromenadeBlockKeys.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeFactory.carpetedGrassBlock(MAPLE_FULVOUS_LEAVES_COLOR));

    public static final Block MIKADO_MAPLE_SAPLING = of(PromenadeBlockKeys.MIKADO_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_MIKADO_LEAVES_COLOR, PromenadeSaplingGenerators.MIKADO_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_MIKADO_MAPLE_SAPLING = of(PromenadeBlockKeys.POTTED_MIKADO_MAPLE_SAPLING, DawnFactory.potted(MIKADO_MAPLE_SAPLING));
    public static final Block MIKADO_MAPLE_LEAVES = of(PromenadeBlockKeys.MIKADO_MAPLE_LEAVES, PromenadeFactory.decoratedLeaves(MAPLE_MIKADO_LEAVES_COLOR, PromenadeParticleTypes.MIKADO_MAPLE_LEAF));
    public static final Block MIKADO_MAPLE_LEAF_PILE = of(PromenadeBlockKeys.MIKADO_MAPLE_LEAF_PILE, PromenadeFactory.leafPile(MAPLE_MIKADO_LEAVES_COLOR));
    public static final Block MIKADO_CARPETED_GRASS_BLOCK = of(PromenadeBlockKeys.MIKADO_CARPETED_GRASS_BLOCK, PromenadeFactory.carpetedGrassBlock(MAPLE_MIKADO_LEAVES_COLOR));


    /* ======== */
    /*   PALM   */
    /* ======== */
    private static final BlockSoundGroup PALM_WOOD_SOUNDS = BlockSoundGroup.WOOD;
    private static final MapColor PALM_BARK_COLOR = MapColor.SPRUCE_BROWN;
    private static final MapColor PALM_WOOD_COLOR = MapColor.ORANGE;
    private static final MapColor PALM_LEAVES_COLOR = MapColor.DARK_GREEN;


    public static final Block STRIPPED_PALM_LOG = of(PromenadeBlockKeys.STRIPPED_PALM_LOG, new PillarBlock(DawnFactory.logSettings(PALM_WOOD_COLOR, PALM_WOOD_SOUNDS, true)));
    public static final Block PALM_LOG = of(PromenadeBlockKeys.PALM_LOG, new PillarBlock(DawnFactory.logSettings(PALM_WOOD_COLOR, PALM_BARK_COLOR, PALM_WOOD_SOUNDS, true).stripsInto(STRIPPED_PALM_LOG)));
    public static final Block STRIPPED_PALM_WOOD = of(PromenadeBlockKeys.STRIPPED_PALM_WOOD, new PillarBlock(DawnFactory.logSettings(PALM_WOOD_COLOR, PALM_WOOD_SOUNDS, true)));
    public static final Block PALM_WOOD = of(PromenadeBlockKeys.PALM_WOOD, new PillarBlock(DawnFactory.logSettings(PALM_BARK_COLOR, PALM_WOOD_SOUNDS, true).stripsInto(STRIPPED_PALM_WOOD)));

    public static final Block PALM_PLANKS = of(PromenadeBlockKeys.PALM_PLANKS, DawnFactory.planks(PALM_WOOD_COLOR, PALM_WOOD_SOUNDS, true));
    public static final Block PALM_STAIRS = of(PromenadeBlockKeys.PALM_STAIRS, DawnFactory.stairs(PALM_PLANKS));
    public static final Block PALM_SLAB = of(PromenadeBlockKeys.PALM_SLAB, DawnFactory.slab(PALM_PLANKS));
    public static final Block PALM_FENCE = of(PromenadeBlockKeys.PALM_FENCE, DawnFactory.fence(PALM_PLANKS));
    public static final Block PALM_FENCE_GATE = of(PromenadeBlockKeys.PALM_FENCE_GATE, DawnFactory.fenceGate(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_DOOR = of(PromenadeBlockKeys.PALM_DOOR, DawnFactory.door(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_TRAPDOOR = of(PromenadeBlockKeys.PALM_TRAPDOOR, DawnFactory.trapdoor(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_BUTTON = of(PromenadeBlockKeys.PALM_BUTTON, DawnFactory.woodenButton(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_PRESSURE_PLATE = of(PromenadeBlockKeys.PALM_PRESSURE_PLATE, DawnFactory.pressurePlate(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_SIGN = of(PromenadeBlockKeys.PALM_SIGN, PromenadeFactory.sign(false, false, Promenade.id("palm"), PALM_PLANKS, PALM_WOOD_SOUNDS));
    public static final Block PALM_WALL_SIGN = of(PromenadeBlockKeys.PALM_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("palm"), PALM_PLANKS, PALM_WOOD_SOUNDS));
    public static final Block PALM_HANGING_SIGN = of(PromenadeBlockKeys.PALM_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("palm"), PALM_PLANKS, BlockSoundGroup.HANGING_SIGN));
    public static final Block PALM_WALL_HANGING_SIGN = of(PromenadeBlockKeys.PALM_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("palm"), PALM_PLANKS, BlockSoundGroup.HANGING_SIGN));

    public static final Block PALM_SAPLING = of(PromenadeBlockKeys.PALM_SAPLING, DawnFactory.sapling(PALM_LEAVES_COLOR, PromenadeSaplingGenerators.PALM_SAPLING_GENERATOR, state -> state.isIn(BlockTags.SAND)));
    public static final Block POTTED_PALM_SAPLING = of(PromenadeBlockKeys.POTTED_PALM_SAPLING, DawnFactory.potted(PALM_SAPLING));
    //TODO: put the next two blocks in PromenadeFactory
    public static final Block PALM_LEAVES = of(PromenadeBlockKeys.PALM_LEAVES, new ExtendedLeavesBlock(AbstractBlock.Settings.create()
            .item(new Item.Settings().compostingChance(0.3f))
            .mapColor(PALM_LEAVES_COLOR)
            .strength(0.2f)
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .nonOpaque()
            .allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
            .suffocates((state, world, pos) -> false)
            .blockVision((state, world, pos) -> false)
            .burnable(30, 60)
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock((state, world, pos) -> false)));
    public static final Block PALM_HANGING_LEAVES = of(PromenadeBlockKeys.PALM_HANGING_LEAVES, new HangingLeavesBlock(AbstractBlock.Settings.create()
            .item(new Item.Settings().compostingChance(0.3F))
            .mapColor(PALM_LEAVES_COLOR)
            .sounds(BlockSoundGroup.GRASS)
            .replaceable().noCollision().breakInstantly()
            .burnable(30, 60)
            .pistonBehavior(PistonBehavior.DESTROY)
    ));
    public static final Block PALM_LEAF_PILE = of(PromenadeBlockKeys.PALM_LEAF_PILE, PromenadeFactory.leafPile());


    /* =========== */
    /*   AURORAL   */
    /* =========== */
    private static final BlockSoundGroup AURORAL_CYPRESS_WOOD_SOUNDS = BlockSoundGroup.WOOD;
    private static final MapColor AURORAL_CYPRESS_BARK_COLOR = MapColor.DEEPSLATE_GRAY;
    private static final MapColor AURORAL_CYPRESS_WOOD_COLOR = MapColor.TERRACOTTA_WHITE;
    private static final MapColor AURORAL_CYPRESS_LEAVES_COLOR = MapColor.BLUE;


    public static final Block STRIPPED_AURORAL_CYPRESS_LOG = of(PromenadeBlockKeys.STRIPPED_AURORAL_CYPRESS_LOG, new StrippedMapleLogBlock(DawnFactory.logSettings(AURORAL_CYPRESS_WOOD_COLOR, AURORAL_CYPRESS_WOOD_SOUNDS, true)));
    public static final Block AURORAL_CYPRESS_LOG = of(PromenadeBlockKeys.AURORAL_CYPRESS_LOG, new MapleLogBlock(DawnFactory.logSettings(AURORAL_CYPRESS_WOOD_COLOR, AURORAL_CYPRESS_BARK_COLOR, AURORAL_CYPRESS_WOOD_SOUNDS, true).stripsInto(STRIPPED_AURORAL_CYPRESS_LOG)));
    public static final Block STRIPPED_AURORAL_CYPRESS_WOOD = of(PromenadeBlockKeys.STRIPPED_AURORAL_CYPRESS_WOOD, new PillarBlock(DawnFactory.logSettings(AURORAL_CYPRESS_WOOD_COLOR, AURORAL_CYPRESS_WOOD_SOUNDS, true)));
    public static final Block AURORAL_CYPRESS_WOOD = of(PromenadeBlockKeys.AURORAL_CYPRESS_WOOD, new PillarBlock(DawnFactory.logSettings(AURORAL_CYPRESS_BARK_COLOR, AURORAL_CYPRESS_WOOD_SOUNDS, true).stripsInto(STRIPPED_AURORAL_CYPRESS_WOOD)));

    public static final Block AURORAL_CYPRESS_PLANKS = of(PromenadeBlockKeys.AURORAL_CYPRESS_PLANKS, DawnFactory.planks(AURORAL_CYPRESS_BARK_COLOR, AURORAL_CYPRESS_WOOD_SOUNDS, true));
    public static final Block AURORAL_CYPRESS_STAIRS = of(PromenadeBlockKeys.AURORAL_CYPRESS_STAIRS, DawnFactory.stairs(AURORAL_CYPRESS_PLANKS));
    public static final Block AURORAL_CYPRESS_SLAB = of(PromenadeBlockKeys.AURORAL_CYPRESS_SLAB, DawnFactory.slab(AURORAL_CYPRESS_PLANKS));
    public static final Block AURORAL_CYPRESS_FENCE = of(PromenadeBlockKeys.AURORAL_CYPRESS_FENCE, DawnFactory.fence(AURORAL_CYPRESS_PLANKS));
    public static final Block AURORAL_CYPRESS_FENCE_GATE = of(PromenadeBlockKeys.AURORAL_CYPRESS_FENCE_GATE, DawnFactory.fenceGate(AURORAL_CYPRESS_PLANKS, PromenadeWoodTypes.AURORAL_CYPRESS));
    public static final Block AURORAL_CYPRESS_DOOR = of(PromenadeBlockKeys.AURORAL_CYPRESS_DOOR, DawnFactory.door(AURORAL_CYPRESS_PLANKS, PromenadeBlockSetTypes.AURORAL_CYPRESS));
    public static final Block AURORAL_CYPRESS_TRAPDOOR = of(PromenadeBlockKeys.AURORAL_CYPRESS_TRAPDOOR, DawnFactory.trapdoor(AURORAL_CYPRESS_PLANKS, PromenadeBlockSetTypes.AURORAL_CYPRESS));
    public static final Block AURORAL_CYPRESS_BUTTON = of(PromenadeBlockKeys.AURORAL_CYPRESS_BUTTON, DawnFactory.woodenButton(AURORAL_CYPRESS_PLANKS, PromenadeBlockSetTypes.AURORAL_CYPRESS));
    public static final Block AURORAL_CYPRESS_PRESSURE_PLATE = of(PromenadeBlockKeys.AURORAL_CYPRESS_PRESSURE_PLATE, DawnFactory.pressurePlate(AURORAL_CYPRESS_PLANKS, PromenadeBlockSetTypes.AURORAL_CYPRESS));
    public static final Block AURORAL_CYPRESS_SIGN = of(PromenadeBlockKeys.AURORAL_CYPRESS_SIGN, PromenadeFactory.sign(false, false, Promenade.id("auroral_cypress"), AURORAL_CYPRESS_PLANKS, AURORAL_CYPRESS_WOOD_SOUNDS));
    public static final Block AURORAL_CYPRESS_WALL_SIGN = of(PromenadeBlockKeys.AURORAL_CYPRESS_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("auroral_cypress"), AURORAL_CYPRESS_PLANKS, AURORAL_CYPRESS_WOOD_SOUNDS));
    public static final Block AURORAL_CYPRESS_HANGING_SIGN = of(PromenadeBlockKeys.AURORAL_CYPRESS_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("auroral_cypress"), AURORAL_CYPRESS_PLANKS, BlockSoundGroup.HANGING_SIGN));
    public static final Block AURORAL_CYPRESS_WALL_HANGING_SIGN = of(PromenadeBlockKeys.AURORAL_CYPRESS_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("auroral_cypress"), AURORAL_CYPRESS_PLANKS, BlockSoundGroup.HANGING_SIGN));

    public static final Block AURORAL_CYPRESS_SAPLING = of(PromenadeBlockKeys.AURORAL_CYPRESS_SAPLING, DawnFactory.sapling(AURORAL_CYPRESS_LEAVES_COLOR, PromenadeSaplingGenerators.AURORAL_CYPRESS_SAPLING_GENERATOR, state -> state.isIn(PromenadeBlockTags.AURORAL_CYPRESS_SAPLING_PLACEABLE_ON)));
    public static final Block POTTED_AURORAL_CYPRESS_SAPLING = of(PromenadeBlockKeys.POTTED_AURORAL_CYPRESS_SAPLING, DawnFactory.potted(AURORAL_CYPRESS_SAPLING));
    public static final Block AURORAL_CYPRESS_LEAVES = of(PromenadeBlockKeys.AURORAL_CYPRESS_LEAVES, StarryLeavesBlock.of(AURORAL_CYPRESS_LEAVES_COLOR));
    public static final Block AURORAL_CYPRESS_LEAF_PILE = of(PromenadeBlockKeys.AURORAL_CYPRESS_LEAF_PILE, PromenadeFactory.leafPile());


    /* ============ */
    /*   AMARANTH   */
    /* ============ */
    private static final BlockSoundGroup AMARANTH_WOOD_SOUNDS = BlockSoundGroup.NETHER_WOOD;
    private static final MapColor AMARANTH_BARK_COLOR = MapColor.DARK_DULL_PINK;
    private static final MapColor AMARANTH_WOOD_COLOR = MapColor.GRAY;

    public static final Block BLACK_DYLIUM = of(PromenadeBlockKeys.BLACK_DYLIUM, new DyliumBlock(
            AbstractBlock.Settings.create()
                    .item()
                    .mapColor(MapColor.BLACK)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.0F, 9.0F)
                    .sounds(BlockSoundGroup.NYLIUM)
                    .ticksRandomly()));
    public static final Block DARK_AMARANTH_WART_BLOCK = of(PromenadeBlockKeys.DARK_AMARANTH_WART_BLOCK, new Block(
            AbstractBlock.Settings.create()
                    .item()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WART_BLOCK)));
    public static final Block DARK_AMARANTH_ROOTS = of(PromenadeBlockKeys.DARK_AMARANTH_ROOTS, new DawnRootsBlock(state -> state.isIn(PromenadeBlockTags.DARK_AMARANTH_ROOTS_PLACEABLE_ON),
            AbstractBlock.Settings.create()
                    .item(new Item.Settings().compostingChance(0.65F))
                    .mapColor(MapColor.PURPLE)
                    .replaceable()
                    .breakInstantly()
                    .noCollision()
                    .sounds(BlockSoundGroup.ROOTS)));

    public static final Block STRIPPED_DARK_AMARANTH_STEM = of(PromenadeBlockKeys.STRIPPED_DARK_AMARANTH_STEM, new PillarBlock(DawnFactory.logSettings(AMARANTH_WOOD_COLOR, BlockSoundGroup.NETHER_STEM, false)));
    public static final Block DARK_AMARANTH_STEM = of(PromenadeBlockKeys.DARK_AMARANTH_STEM, new PillarBlock(DawnFactory.logSettings(AMARANTH_WOOD_COLOR, AMARANTH_BARK_COLOR, BlockSoundGroup.NETHER_STEM, false).stripsInto(STRIPPED_DARK_AMARANTH_STEM)));
    public static final Block STRIPPED_DARK_AMARANTH_HYPHAE = of(PromenadeBlockKeys.STRIPPED_DARK_AMARANTH_HYPHAE, new PillarBlock(DawnFactory.logSettings(AMARANTH_WOOD_COLOR, BlockSoundGroup.NETHER_STEM, false)));
    public static final Block DARK_AMARANTH_HYPHAE = of(PromenadeBlockKeys.DARK_AMARANTH_HYPHAE, new PillarBlock(DawnFactory.logSettings(AMARANTH_BARK_COLOR, BlockSoundGroup.NETHER_STEM, false).stripsInto(STRIPPED_DARK_AMARANTH_HYPHAE)));

    public static final Block DARK_AMARANTH_PLANKS = of(PromenadeBlockKeys.DARK_AMARANTH_PLANKS, DawnFactory.planks(AMARANTH_WOOD_COLOR, AMARANTH_WOOD_SOUNDS, false));
    public static final Block DARK_AMARANTH_STAIRS = of(PromenadeBlockKeys.DARK_AMARANTH_STAIRS, DawnFactory.stairs(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_SLAB = of(PromenadeBlockKeys.DARK_AMARANTH_SLAB, DawnFactory.slab(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_FENCE = of(PromenadeBlockKeys.DARK_AMARANTH_FENCE, DawnFactory.fence(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_FENCE_GATE = of(PromenadeBlockKeys.DARK_AMARANTH_FENCE_GATE, DawnFactory.fenceGate(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.AMARANTH));
    public static final Block DARK_AMARANTH_DOOR = of(PromenadeBlockKeys.DARK_AMARANTH_DOOR, DawnFactory.door(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_TRAPDOOR = of(PromenadeBlockKeys.DARK_AMARANTH_TRAPDOOR, DawnFactory.trapdoor(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_BUTTON = of(PromenadeBlockKeys.DARK_AMARANTH_BUTTON, DawnFactory.woodenButton(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_PRESSURE_PLATE = of(PromenadeBlockKeys.DARK_AMARANTH_PRESSURE_PLATE, DawnFactory.pressurePlate(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_SIGN = of(PromenadeBlockKeys.DARK_AMARANTH_SIGN, PromenadeFactory.sign(false, false, Promenade.id("dark_amaranth"), DARK_AMARANTH_PLANKS, AMARANTH_WOOD_SOUNDS));
    public static final Block DARK_AMARANTH_WALL_SIGN = of(PromenadeBlockKeys.DARK_AMARANTH_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("dark_amaranth"), DARK_AMARANTH_PLANKS, AMARANTH_WOOD_SOUNDS));
    public static final Block DARK_AMARANTH_HANGING_SIGN = of(PromenadeBlockKeys.DARK_AMARANTH_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("dark_amaranth"), DARK_AMARANTH_PLANKS, BlockSoundGroup.HANGING_SIGN));
    public static final Block DARK_AMARANTH_WALL_HANGING_SIGN = of(PromenadeBlockKeys.DARK_AMARANTH_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("dark_amaranth"), DARK_AMARANTH_PLANKS, BlockSoundGroup.HANGING_SIGN));

    public static final Block DARK_AMARANTH_FUNGUS = of(PromenadeBlockKeys.DARK_AMARANTH_FUNGUS, DawnFactory.fungus(MapColor.PURPLE, PromenadeConfiguredFeatureKeys.PLANTED_AMARANTH_FUNGUS, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_GROWABLE_ON));
    public static final Block POTTED_DARK_AMARANTH_FUNGUS = of(PromenadeBlockKeys.POTTED_DARK_AMARANTH_FUNGUS, DawnFactory.potted(DARK_AMARANTH_FUNGUS));


    public static final Block MOAI = of(PromenadeBlockKeys.MOAI, new MoaiBlock(AbstractBlock.Settings.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).item(new Item.Settings().equipmentSlot(stack -> EquipmentSlot.HEAD))));

    public static final Block BLUEBERRY_BUSH = of(PromenadeBlockKeys.BLUEBERRY_BUSH, BerryBushBlock.of(PromenadeItemKeys.BLUEBERRIES, false));

    public static final Block STAR_BITS = of(PromenadeBlockKeys.STAR_BITS, StarBitsBlock.of(MapColor.TERRACOTTA_WHITE));

    public static <B extends Block> B of(RegistryKey<Block> key, B block) {
        return Registry.register(Registries.BLOCK, key, block);
    }

    public static void appendItemGroups() {
        //TODO: optimize item groups
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> e.addAfter(Blocks.ANDESITE, BLUNITE, ASPHALT));
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.POLISHED_ANDESITE_SLAB,
                BLUNITE, BLUNITE_SLAB, BLUNITE_STAIRS, BLUNITE_WALL,
                POLISHED_BLUNITE, POLISHED_BLUNITE_SLAB, POLISHED_BLUNITE_STAIRS,
                ASPHALT, ASPHALT_SLAB, ASPHALT_STAIRS, ASPHALT_WALL,
                POLISHED_ASPHALT, POLISHED_ASPHALT_SLAB, POLISHED_ASPHALT_STAIRS
        ));


        // VANILLA PILES
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
            e.addAfter(Blocks.FLOWERING_AZALEA_LEAVES,
                    OAK_LEAF_PILE,
                    SPRUCE_LEAF_PILE,
                    BIRCH_LEAF_PILE,
                    JUNGLE_LEAF_PILE,
                    ACACIA_LEAF_PILE,
                    DARK_OAK_LEAF_PILE,
                    MANGROVE_LEAF_PILE,
                    AZALEA_LEAF_PILE,
                    FLOWERING_AZALEA_LEAF_PILE);

            e.addAfter(Blocks.LILY_OF_THE_VALLEY,
                    DANDELION_PILE,
                    POPPY_PILE,
                    BLUE_ORCHID_PILE,
                    ALLIUM_PILE,
                    AZURE_BLUET_PILE,
                    RED_TULIP_PILE,
                    ORANGE_TULIP_PILE,
                    WHITE_TULIP_PILE,
                    PINK_TULIP_PILE,
                    OXEYE_DAISY_PILE,
                    CORNFLOWER_PILE,
                    LILY_OF_THE_VALLEY_PILE);
            e.addAfter(Blocks.WITHER_ROSE, WITHER_ROSE_PILE);
        });

        // VANILLA SNOWY LEAVES
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
            e.addAfter(Blocks.SPRUCE_LEAVES, SNOWY_SPRUCE_LEAVES);
        });

        // SAKURA
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.BIRCH_BUTTON,
                SAKURA_LOG,
                SAKURA_WOOD,
                STRIPPED_SAKURA_LOG,
                STRIPPED_SAKURA_WOOD,
                SAKURA_PLANKS,
                SAKURA_STAIRS,
                SAKURA_SLAB,
                SAKURA_FENCE,
                SAKURA_FENCE_GATE,
                SAKURA_DOOR,
                SAKURA_TRAPDOOR,
                SAKURA_PRESSURE_PLATE,
                SAKURA_BUTTON));
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
            e.addAfter(Blocks.BIRCH_LOG, SAKURA_LOG);
            e.addAfter(Blocks.BIRCH_LEAVES, BLUSH_SAKURA_BLOSSOMS, COTTON_SAKURA_BLOSSOMS);
            e.addAfter(Blocks.BIRCH_SAPLING, BLUSH_SAKURA_SAPLING, COTTON_SAKURA_SAPLING);
            e.addAfter(BIRCH_LEAF_PILE, BLUSH_SAKURA_BLOSSOM_PILE, COTTON_SAKURA_BLOSSOM_PILE);
        });

        // MAPLE
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e ->
                e.addAfter(SAKURA_BUTTON,
                        MAPLE_LOG,
                        MAPLE_WOOD,
                        STRIPPED_MAPLE_LOG,
                        STRIPPED_MAPLE_WOOD,
                        MAPLE_PLANKS,
                        MAPLE_STAIRS,
                        MAPLE_SLAB,
                        MAPLE_FENCE,
                        MAPLE_FENCE_GATE,
                        MAPLE_DOOR,
                        MAPLE_TRAPDOOR,
                        MAPLE_PRESSURE_PLATE,
                        MAPLE_BUTTON));
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
            e.addAfter(SAKURA_LOG, MAPLE_LOG);
            e.addAfter(Blocks.GRASS_BLOCK, VERMILION_CARPETED_GRASS_BLOCK, FULVOUS_CARPETED_GRASS_BLOCK, MIKADO_CARPETED_GRASS_BLOCK);
            e.addAfter(COTTON_SAKURA_BLOSSOMS, SAP_MAPLE_LEAVES, VERMILION_MAPLE_LEAVES, FULVOUS_MAPLE_LEAVES, MIKADO_MAPLE_LEAVES);
            e.addAfter(COTTON_SAKURA_SAPLING, SAP_MAPLE_SAPLING, VERMILION_MAPLE_SAPLING, FULVOUS_MAPLE_SAPLING, MIKADO_MAPLE_SAPLING);
            e.addAfter(COTTON_SAKURA_BLOSSOM_PILE, SAP_MAPLE_LEAF_PILE, VERMILION_MAPLE_LEAF_PILE, FULVOUS_MAPLE_LEAF_PILE, MIKADO_MAPLE_LEAF_PILE);
        });

        // PALM
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
            e.addAfter(ACACIA_LEAF_PILE, PALM_LEAF_PILE);
        });

        // AURORAL CYPRESS
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> {
            e.addAfter(Blocks.CHERRY_BUTTON,
                    AURORAL_CYPRESS_LOG,
                    AURORAL_CYPRESS_WOOD,
                    STRIPPED_AURORAL_CYPRESS_LOG,
                    STRIPPED_AURORAL_CYPRESS_WOOD,
                    AURORAL_CYPRESS_PLANKS,
                    AURORAL_CYPRESS_STAIRS,
                    AURORAL_CYPRESS_SLAB,
                    AURORAL_CYPRESS_FENCE,
                    AURORAL_CYPRESS_FENCE_GATE,
                    AURORAL_CYPRESS_DOOR,
                    AURORAL_CYPRESS_TRAPDOOR,
                    AURORAL_CYPRESS_PRESSURE_PLATE,
                    AURORAL_CYPRESS_BUTTON);
        });
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
            e.addAfter(Blocks.CHERRY_LOG, AURORAL_CYPRESS_LOG);
            e.addAfter(Blocks.AZALEA_LEAVES, AURORAL_CYPRESS_LEAVES);
            e.addAfter(Blocks.FLOWERING_AZALEA, AURORAL_CYPRESS_SAPLING);
            e.addAfter(FLOWERING_AZALEA_LEAF_PILE, AURORAL_CYPRESS_LEAF_PILE);
        });

        // AMARANTH
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.WARPED_BUTTON,
                DARK_AMARANTH_STEM,
                DARK_AMARANTH_HYPHAE,
                STRIPPED_DARK_AMARANTH_STEM,
                STRIPPED_DARK_AMARANTH_HYPHAE,
                DARK_AMARANTH_PLANKS,
                DARK_AMARANTH_STAIRS,
                DARK_AMARANTH_SLAB,
                DARK_AMARANTH_FENCE,
                DARK_AMARANTH_FENCE_GATE,
                DARK_AMARANTH_DOOR,
                DARK_AMARANTH_TRAPDOOR,
                DARK_AMARANTH_PRESSURE_PLATE,
                DARK_AMARANTH_BUTTON));

        ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
            e.addAfter(Blocks.WARPED_NYLIUM, BLACK_DYLIUM);
            e.addAfter(Blocks.WARPED_STEM, DARK_AMARANTH_STEM);
            e.addAfter(Blocks.WARPED_WART_BLOCK, DARK_AMARANTH_WART_BLOCK);
            e.addAfter(Blocks.WARPED_FUNGUS, DARK_AMARANTH_FUNGUS);
            e.addAfter(Blocks.WARPED_ROOTS, DARK_AMARANTH_ROOTS);
        });

        // STARS
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> e.addAfter(Blocks.HONEY_BLOCK, STAR_BITS));
        ItemGroupHelper.append(ItemGroups.INGREDIENTS, e -> e.addAfter(PromenadeItems.STAR_DUST, STAR_BITS));

        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.CUT_RED_SANDSTONE_SLAB, MOAI));
    }

    public static void appendVillagerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(TradeOfferUtils.sapling(VERMILION_MAPLE_SAPLING));
            factories.add(TradeOfferUtils.sapling(FULVOUS_MAPLE_SAPLING));
            factories.add(TradeOfferUtils.sapling(MIKADO_MAPLE_SAPLING));
            factories.add(TradeOfferUtils.sapling(SAP_MAPLE_SAPLING));
        });
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(TradeOfferUtils.sapling(BLUSH_SAKURA_SAPLING));
            factories.add(TradeOfferUtils.sapling(COTTON_SAKURA_SAPLING));
        });
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(TradeOfferUtils.sapling(PALM_SAPLING)));
    }
}
