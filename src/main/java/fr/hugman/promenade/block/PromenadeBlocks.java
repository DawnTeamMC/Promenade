package fr.hugman.promenade.block;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.particle.PromenadeParticleTypes;
import fr.hugman.promenade.registry.content.VanillaPilesContent;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.*;
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
    public static final Block ASPHALT = register(PromenadeBlockKeys.ASPHALT, new Block(AbstractBlock.Settings.copy(Blocks.ANDESITE).item().mapColor(MapColor.DEEPSLATE_GRAY).sounds(BlockSoundGroup.BASALT)));
    public static final Block ASPHALT_SLAB = register(PromenadeBlockKeys.ASPHALT_SLAB, DawnFactory.slab(ASPHALT));
    public static final Block ASPHALT_STAIRS = register(PromenadeBlockKeys.ASPHALT_STAIRS, DawnFactory.stairs(ASPHALT));
    public static final Block ASPHALT_WALL = register(PromenadeBlockKeys.ASPHALT_WALL, DawnFactory.wall(ASPHALT));

    public static final Block POLISHED_ASPHALT = register(PromenadeBlockKeys.POLISHED_ASPHALT, new Block(AbstractBlock.Settings.copy(ASPHALT)));
    public static final Block POLISHED_ASPHALT_SLAB = register(PromenadeBlockKeys.POLISHED_ASPHALT_SLAB, DawnFactory.slab(POLISHED_ASPHALT));
    public static final Block POLISHED_ASPHALT_STAIRS = register(PromenadeBlockKeys.POLISHED_ASPHALT_STAIRS, DawnFactory.stairs(POLISHED_ASPHALT));

    public static final Block BLUNITE = register(PromenadeBlockKeys.BLUNITE, new Block(AbstractBlock.Settings.copy(Blocks.ANDESITE).item().mapColor(MapColor.TERRACOTTA_CYAN).sounds(BlockSoundGroup.TUFF)));
    public static final Block BLUNITE_SLAB = register(PromenadeBlockKeys.BLUNITE_SLAB, DawnFactory.slab(BLUNITE));
    public static final Block BLUNITE_STAIRS = register(PromenadeBlockKeys.BLUNITE_STAIRS, DawnFactory.stairs(BLUNITE));
    public static final Block BLUNITE_WALL = register(PromenadeBlockKeys.BLUNITE_WALL, DawnFactory.wall(BLUNITE));

    public static final Block POLISHED_BLUNITE = register(PromenadeBlockKeys.POLISHED_BLUNITE, new Block(AbstractBlock.Settings.copy(BLUNITE)));
    public static final Block POLISHED_BLUNITE_SLAB = register(PromenadeBlockKeys.POLISHED_BLUNITE_SLAB, DawnFactory.slab(POLISHED_BLUNITE));
    public static final Block POLISHED_BLUNITE_STAIRS = register(PromenadeBlockKeys.POLISHED_BLUNITE_STAIRS, DawnFactory.stairs(POLISHED_BLUNITE));


    /* ========== */
    /*   SAKURA   */
    /* ========== */
    private static final BlockSoundGroup SAKURA_WOOD_SOUNDS = BlockSoundGroup.CHERRY_WOOD;
    private static final MapColor SAKURA_BARK_COLOR = MapColor.TERRACOTTA_BROWN;
    private static final MapColor SAKURA_WOOD_COLOR = MapColor.TERRACOTTA_BROWN;

    private static final MapColor BLUSH_BLOSSOMS_COLOR = MapColor.PINK;
    private static final MapColor COTTON_BLOSSOMS_COLOR = MapColor.OFF_WHITE;


    public static final Block STRIPPED_SAKURA_LOG = register(PromenadeBlockKeys.STRIPPED_SAKURA_LOG, new PillarBlock(DawnFactory.logSettings(SAKURA_WOOD_COLOR, SAKURA_WOOD_SOUNDS, true)));
    public static final Block SAKURA_LOG = register(PromenadeBlockKeys.SAKURA_LOG, new PillarBlock(DawnFactory.logSettings(SAKURA_WOOD_COLOR, SAKURA_BARK_COLOR, SAKURA_WOOD_SOUNDS, true).stripsInto(STRIPPED_SAKURA_LOG)));
    public static final Block STRIPPED_SAKURA_WOOD = register(PromenadeBlockKeys.STRIPPED_SAKURA_WOOD, new PillarBlock(DawnFactory.logSettings(SAKURA_WOOD_COLOR, SAKURA_WOOD_SOUNDS, true)));
    public static final Block SAKURA_WOOD = register(PromenadeBlockKeys.SAKURA_WOOD, new PillarBlock(DawnFactory.logSettings(SAKURA_BARK_COLOR, SAKURA_WOOD_SOUNDS, true).stripsInto(STRIPPED_SAKURA_WOOD)));

    public static final Block SAKURA_PLANKS = register(PromenadeBlockKeys.SAKURA_PLANKS, DawnFactory.planks(SAKURA_WOOD_COLOR, SAKURA_WOOD_SOUNDS, true));
    public static final Block SAKURA_STAIRS = register(PromenadeBlockKeys.SAKURA_STAIRS, DawnFactory.stairs(SAKURA_PLANKS));
    public static final Block SAKURA_SLAB = register(PromenadeBlockKeys.SAKURA_SLAB, DawnFactory.slab(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE = register(PromenadeBlockKeys.SAKURA_FENCE, DawnFactory.fence(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE_GATE = register(PromenadeBlockKeys.SAKURA_FENCE_GATE, DawnFactory.fenceGate(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_DOOR = register(PromenadeBlockKeys.SAKURA_DOOR, DawnFactory.door(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_TRAPDOOR = register(PromenadeBlockKeys.SAKURA_TRAPDOOR, DawnFactory.trapdoor(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_BUTTON = register(PromenadeBlockKeys.SAKURA_BUTTON, DawnFactory.woodenButton(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_PRESSURE_PLATE = register(PromenadeBlockKeys.SAKURA_PRESSURE_PLATE, DawnFactory.pressurePlate(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_SIGN = register(PromenadeBlockKeys.SAKURA_SIGN, PromenadeFactory.sign(false, false, Promenade.id("sakura"), SAKURA_PLANKS, SAKURA_WOOD_SOUNDS));
    public static final Block SAKURA_WALL_SIGN = register(PromenadeBlockKeys.SAKURA_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("sakura"), SAKURA_PLANKS, SAKURA_WOOD_SOUNDS));
    public static final Block SAKURA_HANGING_SIGN = register(PromenadeBlockKeys.SAKURA_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("sakura"), SAKURA_PLANKS, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));
    public static final Block SAKURA_WALL_HANGING_SIGN = register(PromenadeBlockKeys.SAKURA_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("sakura"), SAKURA_PLANKS, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));

    public static final Block BLUSH_SAKURA_SAPLING = register(PromenadeBlockKeys.BLUSH_SAKURA_SAPLING, DawnFactory.sapling(BLUSH_BLOSSOMS_COLOR, PromenadeSaplingGenerators.BLUSH_SAKURA_SAPLING_GENERATOR));
    public static final Block POTTED_BLUSH_SAKURA_SAPLING = register(PromenadeBlockKeys.POTTED_BLUSH_SAKURA_SAPLING, DawnFactory.potted(BLUSH_SAKURA_SAPLING));
    public static final Block BLUSH_SAKURA_BLOSSOMS = register(PromenadeBlockKeys.BLUSH_SAKURA_BLOSSOMS, PromenadeFactory.decoratedLeaves(BLUSH_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES, PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM));
    public static final Block BLUSH_SAKURA_BLOSSOM_PILE = register(PromenadeBlockKeys.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeFactory.leafPile(BLUSH_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES));

    public static final Block COTTON_SAKURA_SAPLING = register(PromenadeBlockKeys.COTTON_SAKURA_SAPLING, DawnFactory.sapling(COTTON_BLOSSOMS_COLOR, PromenadeSaplingGenerators.COTTON_SAKURA_SAPLING_GENERATOR));
    public static final Block POTTED_COTTON_SAKURA_SAPLING = register(PromenadeBlockKeys.POTTED_COTTON_SAKURA_SAPLING, DawnFactory.potted(COTTON_SAKURA_SAPLING));
    public static final Block COTTON_SAKURA_BLOSSOMS = register(PromenadeBlockKeys.COTTON_SAKURA_BLOSSOMS, PromenadeFactory.decoratedLeaves(COTTON_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES, PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM));
    public static final Block COTTON_SAKURA_BLOSSOM_PILE = register(PromenadeBlockKeys.COTTON_SAKURA_BLOSSOM_PILE, PromenadeFactory.leafPile(COTTON_BLOSSOMS_COLOR, BlockSoundGroup.CHERRY_LEAVES));


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


    public static final Block STRIPPED_MAPLE_LOG = register(PromenadeBlockKeys.STRIPPED_MAPLE_LOG, new StrippedMapleLogBlock(DawnFactory.logSettings(MAPLE_WOOD_COLOR, MAPLE_WOOD_SOUNDS, true)));
    public static final Block MAPLE_LOG = register(PromenadeBlockKeys.MAPLE_LOG, new MapleLogBlock(DawnFactory.logSettings(MAPLE_WOOD_COLOR, MAPLE_BARK_COLOR, MAPLE_WOOD_SOUNDS, true).stripsInto(STRIPPED_MAPLE_LOG)));
    public static final Block STRIPPED_MAPLE_WOOD = register(PromenadeBlockKeys.STRIPPED_MAPLE_WOOD, new PillarBlock(DawnFactory.logSettings(MAPLE_WOOD_COLOR, MAPLE_WOOD_SOUNDS, true)));
    public static final Block MAPLE_WOOD = register(PromenadeBlockKeys.MAPLE_WOOD, new PillarBlock(DawnFactory.logSettings(MAPLE_BARK_COLOR, MAPLE_WOOD_SOUNDS, true).stripsInto(STRIPPED_MAPLE_WOOD)));

    public static final Block MAPLE_PLANKS = register(PromenadeBlockKeys.MAPLE_PLANKS, DawnFactory.planks(MAPLE_BARK_COLOR, MAPLE_WOOD_SOUNDS, true));
    public static final Block MAPLE_STAIRS = register(PromenadeBlockKeys.MAPLE_STAIRS, DawnFactory.stairs(MAPLE_PLANKS));
    public static final Block MAPLE_SLAB = register(PromenadeBlockKeys.MAPLE_SLAB, DawnFactory.slab(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE = register(PromenadeBlockKeys.MAPLE_FENCE, DawnFactory.fence(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE_GATE = register(PromenadeBlockKeys.MAPLE_FENCE_GATE, DawnFactory.fenceGate(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_DOOR = register(PromenadeBlockKeys.MAPLE_DOOR, DawnFactory.door(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_TRAPDOOR = register(PromenadeBlockKeys.MAPLE_TRAPDOOR, DawnFactory.trapdoor(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_BUTTON = register(PromenadeBlockKeys.MAPLE_BUTTON, DawnFactory.woodenButton(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_PRESSURE_PLATE = register(PromenadeBlockKeys.MAPLE_PRESSURE_PLATE, DawnFactory.pressurePlate(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_SIGN = register(PromenadeBlockKeys.MAPLE_SIGN, PromenadeFactory.sign(false, false, Promenade.id("maple"), MAPLE_PLANKS, MAPLE_WOOD_SOUNDS));
    public static final Block MAPLE_WALL_SIGN = register(PromenadeBlockKeys.MAPLE_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("maple"), MAPLE_PLANKS, MAPLE_WOOD_SOUNDS));
    public static final Block MAPLE_HANGING_SIGN = register(PromenadeBlockKeys.MAPLE_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("maple"), MAPLE_PLANKS, BlockSoundGroup.HANGING_SIGN));
    public static final Block MAPLE_WALL_HANGING_SIGN = register(PromenadeBlockKeys.MAPLE_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("maple"), MAPLE_PLANKS, BlockSoundGroup.HANGING_SIGN));

    public static final Block SAP_MAPLE_SAPLING = register(PromenadeBlockKeys.SAP_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_SAP_LEAVES_COLOR, PromenadeSaplingGenerators.SAP_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_SAP_MAPLE_SAPLING = register(PromenadeBlockKeys.POTTED_SAP_MAPLE_SAPLING, DawnFactory.potted(SAP_MAPLE_SAPLING));
    public static final Block SAP_MAPLE_LEAVES = register(PromenadeBlockKeys.SAP_MAPLE_LEAVES, DawnFactory.leaves(MAPLE_SAP_LEAVES_COLOR));
    public static final Block SAP_MAPLE_LEAF_PILE = register(PromenadeBlockKeys.SAP_MAPLE_LEAF_PILE, PromenadeFactory.leafPile());

    public static final Block VERMILION_MAPLE_SAPLING = register(PromenadeBlockKeys.VERMILION_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_VERMILION_LEAVES_COLOR, PromenadeSaplingGenerators.VERMILION_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_VERMILION_MAPLE_SAPLING = register(PromenadeBlockKeys.POTTED_VERMILION_MAPLE_SAPLING, DawnFactory.potted(VERMILION_MAPLE_SAPLING));
    public static final Block VERMILION_MAPLE_LEAVES = register(PromenadeBlockKeys.VERMILION_MAPLE_LEAVES, PromenadeFactory.decoratedLeaves(MAPLE_VERMILION_LEAVES_COLOR, PromenadeParticleTypes.VERMILION_MAPLE_LEAF));
    public static final Block VERMILION_MAPLE_LEAF_PILE = register(PromenadeBlockKeys.VERMILION_MAPLE_LEAF_PILE, PromenadeFactory.leafPile(MAPLE_VERMILION_LEAVES_COLOR));
    public static final Block VERMILION_CARPETED_GRASS_BLOCK = register(PromenadeBlockKeys.VERMILION_CARPETED_GRASS_BLOCK, PromenadeFactory.carpetedGrassBlock(MAPLE_VERMILION_LEAVES_COLOR));

    public static final Block FULVOUS_MAPLE_SAPLING = register(PromenadeBlockKeys.FULVOUS_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_FULVOUS_LEAVES_COLOR, PromenadeSaplingGenerators.FULVOUS_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_FULVOUS_MAPLE_SAPLING = register(PromenadeBlockKeys.POTTED_FULVOUS_MAPLE_SAPLING, DawnFactory.potted(FULVOUS_MAPLE_SAPLING));
    public static final Block FULVOUS_MAPLE_LEAVES = register(PromenadeBlockKeys.FULVOUS_MAPLE_LEAVES, PromenadeFactory.decoratedLeaves(MAPLE_FULVOUS_LEAVES_COLOR, PromenadeParticleTypes.FULVOUS_MAPLE_LEAF));
    public static final Block FULVOUS_MAPLE_LEAF_PILE = register(PromenadeBlockKeys.FULVOUS_MAPLE_LEAF_PILE, PromenadeFactory.leafPile(MAPLE_FULVOUS_LEAVES_COLOR));
    public static final Block FULVOUS_CARPETED_GRASS_BLOCK = register(PromenadeBlockKeys.FULVOUS_CARPETED_GRASS_BLOCK, PromenadeFactory.carpetedGrassBlock(MAPLE_FULVOUS_LEAVES_COLOR));

    public static final Block MIKADO_MAPLE_SAPLING = register(PromenadeBlockKeys.MIKADO_MAPLE_SAPLING, DawnFactory.sapling(MAPLE_MIKADO_LEAVES_COLOR, PromenadeSaplingGenerators.MIKADO_MAPLE_SAPLING_GENERATOR));
    public static final Block POTTED_MIKADO_MAPLE_SAPLING = register(PromenadeBlockKeys.POTTED_MIKADO_MAPLE_SAPLING, DawnFactory.potted(MIKADO_MAPLE_SAPLING));
    public static final Block MIKADO_MAPLE_LEAVES = register(PromenadeBlockKeys.MIKADO_MAPLE_LEAVES, PromenadeFactory.decoratedLeaves(MAPLE_MIKADO_LEAVES_COLOR, PromenadeParticleTypes.MIKADO_MAPLE_LEAF));
    public static final Block MIKADO_MAPLE_LEAF_PILE = register(PromenadeBlockKeys.MIKADO_MAPLE_LEAF_PILE, PromenadeFactory.leafPile(MAPLE_MIKADO_LEAVES_COLOR));
    public static final Block MIKADO_CARPETED_GRASS_BLOCK = register(PromenadeBlockKeys.MIKADO_CARPETED_GRASS_BLOCK, PromenadeFactory.carpetedGrassBlock(MAPLE_MIKADO_LEAVES_COLOR));


    /* ======== */
    /*   PALM   */
    /* ======== */
    private static final BlockSoundGroup PALM_WOOD_SOUNDS = BlockSoundGroup.WOOD;
    private static final MapColor PALM_BARK_COLOR = MapColor.SPRUCE_BROWN;
    private static final MapColor PALM_WOOD_COLOR = MapColor.ORANGE;
    private static final MapColor PALM_LEAVES_COLOR = MapColor.DARK_GREEN;


    public static final Block STRIPPED_PALM_LOG = register(PromenadeBlockKeys.STRIPPED_PALM_LOG, new PillarBlock(DawnFactory.logSettings(PALM_WOOD_COLOR, PALM_WOOD_SOUNDS, true)));
    public static final Block PALM_LOG = register(PromenadeBlockKeys.PALM_LOG, new PillarBlock(DawnFactory.logSettings(PALM_WOOD_COLOR, PALM_BARK_COLOR, PALM_WOOD_SOUNDS, true).stripsInto(STRIPPED_PALM_LOG)));
    public static final Block STRIPPED_PALM_WOOD = register(PromenadeBlockKeys.STRIPPED_PALM_WOOD, new PillarBlock(DawnFactory.logSettings(PALM_WOOD_COLOR, PALM_WOOD_SOUNDS, true)));
    public static final Block PALM_WOOD = register(PromenadeBlockKeys.PALM_WOOD, new PillarBlock(DawnFactory.logSettings(PALM_BARK_COLOR, PALM_WOOD_SOUNDS, true).stripsInto(STRIPPED_PALM_WOOD)));

    public static final Block PALM_PLANKS = register(PromenadeBlockKeys.PALM_PLANKS, DawnFactory.planks(PALM_WOOD_COLOR, PALM_WOOD_SOUNDS, true));
    public static final Block PALM_STAIRS = register(PromenadeBlockKeys.PALM_STAIRS, DawnFactory.stairs(PALM_PLANKS));
    public static final Block PALM_SLAB = register(PromenadeBlockKeys.PALM_SLAB, DawnFactory.slab(PALM_PLANKS));
    public static final Block PALM_FENCE = register(PromenadeBlockKeys.PALM_FENCE, DawnFactory.fence(PALM_PLANKS));
    public static final Block PALM_FENCE_GATE = register(PromenadeBlockKeys.PALM_FENCE_GATE, DawnFactory.fenceGate(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_DOOR = register(PromenadeBlockKeys.PALM_DOOR, DawnFactory.door(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_TRAPDOOR = register(PromenadeBlockKeys.PALM_TRAPDOOR, DawnFactory.trapdoor(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_BUTTON = register(PromenadeBlockKeys.PALM_BUTTON, DawnFactory.woodenButton(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_PRESSURE_PLATE = register(PromenadeBlockKeys.PALM_PRESSURE_PLATE, DawnFactory.pressurePlate(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_SIGN = register(PromenadeBlockKeys.PALM_SIGN, PromenadeFactory.sign(false, false, Promenade.id("palm"), PALM_PLANKS, PALM_WOOD_SOUNDS));
    public static final Block PALM_WALL_SIGN = register(PromenadeBlockKeys.PALM_WALL_SIGN, PromenadeFactory.sign(false, true, Promenade.id("palm"), PALM_PLANKS, PALM_WOOD_SOUNDS));
    public static final Block PALM_HANGING_SIGN = register(PromenadeBlockKeys.PALM_HANGING_SIGN, PromenadeFactory.sign(true, false, Promenade.id("palm"), PALM_PLANKS, BlockSoundGroup.HANGING_SIGN));
    public static final Block PALM_WALL_HANGING_SIGN = register(PromenadeBlockKeys.PALM_WALL_HANGING_SIGN, PromenadeFactory.sign(true, true, Promenade.id("palm"), PALM_PLANKS, BlockSoundGroup.HANGING_SIGN));

    public static final Block PALM_SAPLING = register(PromenadeBlockKeys.PALM_SAPLING, DawnFactory.sapling(PALM_LEAVES_COLOR, PromenadeSaplingGenerators.PALM_SAPLING_GENERATOR, state -> state.isIn(BlockTags.SAND)));
    public static final Block POTTED_PALM_SAPLING = register(PromenadeBlockKeys.POTTED_PALM_SAPLING, DawnFactory.potted(PALM_SAPLING));
    //TODO: put the next two blocks in PromenadeFactory
    public static final Block PALM_LEAVES = register(PromenadeBlockKeys.PALM_LEAVES, new ExtendedLeavesBlock(AbstractBlock.Settings.create()
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
    public static final Block PALM_HANGING_LEAVES = register(PromenadeBlockKeys.PALM_HANGING_LEAVES, new HangingLeavesBlock(AbstractBlock.Settings.create()
            .item(new Item.Settings().compostingChance(0.3F))
            .mapColor(PALM_LEAVES_COLOR)
            .sounds(BlockSoundGroup.GRASS)
            .replaceable().noCollision().breakInstantly()
            .burnable(30, 60)
            .pistonBehavior(PistonBehavior.DESTROY)
    ));
    public static final Block PALM_LEAF_PILE = register(PromenadeBlockKeys.PALM_LEAF_PILE, PromenadeFactory.leafPile());

    public static final Block MOAI = register(PromenadeBlockKeys.MOAI, new MoaiBlock(AbstractBlock.Settings.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).item(new Item.Settings().equipmentSlot(stack -> EquipmentSlot.HEAD))));

    public static <B extends Block> B register(RegistryKey<Block> key, B block) {
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
            e.addAfter(VanillaPilesContent.BIRCH_LEAF_PILE, BLUSH_SAKURA_BLOSSOM_PILE, COTTON_SAKURA_BLOSSOM_PILE);
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
            e.addAfter(VanillaPilesContent.ACACIA_LEAF_PILE, PALM_LEAF_PILE);
        });

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
