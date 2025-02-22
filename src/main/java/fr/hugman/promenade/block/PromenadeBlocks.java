package fr.hugman.promenade.block;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.helper.BlockBuilder;
import fr.hugman.promenade.block.helper.BlockFactory;
import fr.hugman.promenade.block.map_color.PromenadeMapColors;
import fr.hugman.promenade.block.type.PromenadeBlockSetTypes;
import fr.hugman.promenade.block.type.PromenadeWoodTypes;
import fr.hugman.promenade.item.PromenadeItemKeys;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.particle.PromenadeParticleTypes;
import fr.hugman.promenade.sound.PromenadeBlockSounds;
import fr.hugman.promenade.tag.PromenadeBlockTags;
import fr.hugman.promenade.world.PromenadeSaplingGenerators;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;

public class PromenadeBlocks {
    /* ========= */
    /*   ROCKS   */
    /* ========= */
    public static final Block ASPHALT = register("asphalt", BlockFactory.of(BlockSettings.rock(MapColor.DEEPSLATE_GRAY, BlockSoundGroup.BASALT)));
    public static final Block ASPHALT_SLAB = register("asphalt_slab", BlockFactory.slab(ASPHALT));
    public static final Block ASPHALT_STAIRS = register("asphalt_stairs", BlockFactory.stairs(ASPHALT));
    public static final Block ASPHALT_WALL = register("asphalt_wall", BlockFactory.wall(ASPHALT));

    public static final Block POLISHED_ASPHALT = register("polished_asphalt", BlockFactory.copy(ASPHALT));
    public static final Block POLISHED_ASPHALT_SLAB = register("polished_asphalt_slab", BlockFactory.slab(POLISHED_ASPHALT));
    public static final Block POLISHED_ASPHALT_STAIRS = register("polished_asphalt_stairs", BlockFactory.stairs(POLISHED_ASPHALT));

    public static final Block BLUNITE = register("blunite", BlockFactory.of(BlockSettings.rock(MapColor.TERRACOTTA_CYAN, BlockSoundGroup.TUFF)));
    public static final Block BLUNITE_SLAB = register("blunite_slab", BlockFactory.slab(BLUNITE));
    public static final Block BLUNITE_STAIRS = register("blunite_stairs", BlockFactory.stairs(BLUNITE));
    public static final Block BLUNITE_WALL = register("blunite_wall", BlockFactory.wall(BLUNITE));

    public static final Block POLISHED_BLUNITE = register("polished_blunite", BlockFactory.copy(BLUNITE));
    public static final Block POLISHED_BLUNITE_SLAB = register("polished_blunite_slab", BlockFactory.slab(POLISHED_BLUNITE));
    public static final Block POLISHED_BLUNITE_STAIRS = register("polished_blunite_stairs", BlockFactory.stairs(POLISHED_BLUNITE));


    /* ================= */
    /*   VANILLA PILES   */
    /* ================= */
    public static final Block OAK_LEAF_PILE = register("oak_leaf_pile", BlockFactory.pile());
    public static final Block SPRUCE_LEAF_PILE = register("spruce_leaf_pile", BlockFactory.pile());
    public static final Block BIRCH_LEAF_PILE = register("birch_leaf_pile", BlockFactory.pile());
    public static final Block JUNGLE_LEAF_PILE = register("jungle_leaf_pile", BlockFactory.pile());
    public static final Block ACACIA_LEAF_PILE = register("acacia_leaf_pile", BlockFactory.pile());
    public static final Block CHERRY_LEAF_PILE = register("cherry_leaf_pile", BlockFactory.pile());
    public static final Block DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", BlockFactory.pile());
    public static final Block PALE_OAK_LEAF_PILE = register("pale_oak_leaf_pile", BlockFactory.pile());
    public static final Block MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", BlockFactory.pile());
    public static final Block AZALEA_LEAF_PILE = register("azalea_leaf_pile", BlockFactory.pile().settings(s -> s.sounds(BlockSoundGroup.AZALEA_LEAVES)));
    public static final Block FLOWERING_AZALEA_LEAF_PILE = register("flowering_azalea_leaf_pile", BlockFactory.pile().settings(s -> s.sounds(BlockSoundGroup.AZALEA_LEAVES)));

    public static final Block DANDELION_PILE = register("dandelion_pile", BlockFactory.pile(MapColor.GOLD));
    public static final Block POPPY_PILE = register("poppy_pile", BlockFactory.pile(MapColor.DULL_RED));
    public static final Block BLUE_ORCHID_PILE = register("blue_orchid_pile", BlockFactory.pile(MapColor.LIGHT_BLUE));
    public static final Block ALLIUM_PILE = register("allium_pile", BlockFactory.pile(MapColor.MAGENTA));
    public static final Block AZURE_BLUET_PILE = register("azure_bluet_pile", BlockFactory.pile(MapColor.PALE_YELLOW));
    public static final Block RED_TULIP_PILE = register("red_tulip_pile", BlockFactory.pile(MapColor.DULL_RED));
    public static final Block ORANGE_TULIP_PILE = register("orange_tulip_pile", BlockFactory.pile(MapColor.ORANGE));
    public static final Block WHITE_TULIP_PILE = register("white_tulip_pile", BlockFactory.pile(MapColor.WHITE));
    public static final Block PINK_TULIP_PILE = register("pink_tulip_pile", BlockFactory.pile(MapColor.PINK));
    public static final Block OXEYE_DAISY_PILE = register("oxeye_daisy_pile", BlockFactory.pile(MapColor.GOLD));
    public static final Block CORNFLOWER_PILE = register("cornflower_pile", BlockFactory.pile(MapColor.LAPIS_BLUE));
    public static final Block LILY_OF_THE_VALLEY_PILE = register("lily_of_the_valley_pile", BlockFactory.pile(MapColor.WHITE));
    public static final Block WITHER_ROSE_PILE = register("wither_rose_pile", BlockFactory.pile(MapColor.BLACK).factory(WitherRosePileBlock::new));


    /* ======================== */
    /*   VANILLA SNOWY LEAVES   */
    /* ======================== */
    public static final Block SNOWY_OAK_LEAVES = register("snowy_oak_leaves", BlockFactory.snowyLeaves());
    public static final Block SNOWY_SPRUCE_LEAVES = register("snowy_spruce_leaves", BlockFactory.snowyLeaves());
    public static final Block SNOWY_JUNGLE_LEAVES = register("snowy_jungle_leaves", BlockFactory.snowyLeaves());


    /* ========== */
    /*   SAKURA   */
    /* ========== */
    public static final Block SAKURA_LOG = register("sakura_log", BlockFactory.log(PromenadeMapColors.SAKURA_WOOD, PromenadeMapColors.SAKURA_BARK, PromenadeBlockSounds.SAKURA_WOOD, true));
    public static final Block STRIPPED_SAKURA_LOG = register("stripped_sakura_log", BlockFactory.log(PromenadeMapColors.SAKURA_WOOD, PromenadeBlockSounds.SAKURA_WOOD, true));
    public static final Block SAKURA_WOOD = register("sakura_wood", BlockFactory.log(PromenadeMapColors.SAKURA_BARK, PromenadeBlockSounds.SAKURA_WOOD, true));
    public static final Block STRIPPED_SAKURA_WOOD = register("stripped_sakura_wood", BlockFactory.log(PromenadeMapColors.SAKURA_WOOD, PromenadeBlockSounds.SAKURA_WOOD, true));

    public static final Block SAKURA_PLANKS = register("sakura_planks", BlockFactory.of(BlockSettings.planks(PromenadeMapColors.SAKURA_WOOD, PromenadeBlockSounds.SAKURA_WOOD, true)));
    public static final Block SAKURA_STAIRS = register("sakura_stairs", BlockFactory.stairs(SAKURA_PLANKS));
    public static final Block SAKURA_SLAB = register("sakura_slab", BlockFactory.slab(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE = register("sakura_fence", BlockFactory.fence(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE_GATE = register("sakura_fence_gate", BlockFactory.fenceGate(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_DOOR = register("sakura_door", BlockFactory.door(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_TRAPDOOR = register("sakura_trapdoor", BlockFactory.trapdoor(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_BUTTON = register("sakura_button", BlockFactory.woodenButton(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_PRESSURE_PLATE = register("sakura_pressure_plate", BlockFactory.pressurePlate(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_SIGN = register("sakura_sign", BlockFactory.sign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_WALL_SIGN = register("sakura_wall_sign", BlockFactory.wallSign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_HANGING_SIGN = register("sakura_hanging_sign", BlockFactory.hangingSign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));
    public static final Block SAKURA_WALL_HANGING_SIGN = register("sakura_wall_hanging_sign", BlockFactory.wallHangingSign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));

    public static final Block BLUSH_SAKURA_SAPLING = register("blush_sakura_sapling", BlockFactory.sapling(PromenadeMapColors.BLUSH_BLOSSOMS, PromenadeSaplingGenerators.BLUSH_SAKURA));
    public static final Block POTTED_BLUSH_SAKURA_SAPLING = register("potted_blush_sakura_sapling", BlockFactory.pot(BLUSH_SAKURA_SAPLING));
    public static final Block BLUSH_SAKURA_BLOSSOMS = register("blush_sakura_blossoms", BlockFactory.leaves(PromenadeMapColors.BLUSH_BLOSSOMS, BlockSoundGroup.CHERRY_LEAVES, 10, PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM));
    public static final Block BLUSH_SAKURA_BLOSSOM_PILE = register("blush_sakura_blossom_pile", BlockFactory.pile(PromenadeMapColors.BLUSH_BLOSSOMS, BlockSoundGroup.CHERRY_LEAVES));

    public static final Block COTTON_SAKURA_SAPLING = register("cotton_sakura_sapling", BlockFactory.sapling(PromenadeMapColors.COTTON_BLOSSOMS, PromenadeSaplingGenerators.COTTON_SAKURA));
    public static final Block POTTED_COTTON_SAKURA_SAPLING = register("potted_cotton_sakura_sapling", BlockFactory.pot(COTTON_SAKURA_SAPLING));
    public static final Block COTTON_SAKURA_BLOSSOMS = register("cotton_sakura_blossoms", BlockFactory.leaves(PromenadeMapColors.COTTON_BLOSSOMS, BlockSoundGroup.CHERRY_LEAVES, 10, PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM));
    public static final Block COTTON_SAKURA_BLOSSOM_PILE = register("cotton_sakura_blossom_pile", BlockFactory.pile(PromenadeMapColors.COTTON_BLOSSOMS, BlockSoundGroup.CHERRY_LEAVES));


    /* ========= */
    /*   MAPLE   */
    /* ========= */
    public static final Block MAPLE_LOG = register("maple_log", BlockFactory.log(PromenadeMapColors.MAPLE_WOOD, PromenadeMapColors.MAPLE_BARK, PromenadeBlockSounds.MAPLE_WOOD, true).factory(MapleLogBlock::new));
    public static final Block STRIPPED_MAPLE_LOG = register("stripped_maple_log", BlockFactory.log(PromenadeMapColors.MAPLE_WOOD, PromenadeBlockSounds.MAPLE_WOOD, true).factory(StrippedMapleLogBlock::new));
    public static final Block MAPLE_WOOD = register("maple_wood", BlockFactory.log(PromenadeMapColors.MAPLE_BARK, PromenadeBlockSounds.MAPLE_WOOD, true));
    public static final Block STRIPPED_MAPLE_WOOD = register("stripped_maple_wood", BlockFactory.log(PromenadeMapColors.MAPLE_WOOD, PromenadeBlockSounds.MAPLE_WOOD, true));

    public static final Block MAPLE_PLANKS = register("maple_planks", BlockFactory.of(BlockSettings.planks(PromenadeMapColors.MAPLE_WOOD, PromenadeBlockSounds.MAPLE_WOOD, true)));
    public static final Block MAPLE_STAIRS = register("maple_stairs", BlockFactory.stairs(MAPLE_PLANKS));
    public static final Block MAPLE_SLAB = register("maple_slab", BlockFactory.slab(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE = register("maple_fence", BlockFactory.fence(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE_GATE = register("maple_fence_gate", BlockFactory.fenceGate(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_DOOR = register("maple_door", BlockFactory.door(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_TRAPDOOR = register("maple_trapdoor", BlockFactory.trapdoor(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_BUTTON = register("maple_button", BlockFactory.woodenButton(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_PRESSURE_PLATE = register("maple_pressure_plate", BlockFactory.pressurePlate(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_SIGN = register("maple_sign", BlockFactory.sign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_WALL_SIGN = register("maple_wall_sign", BlockFactory.wallSign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_HANGING_SIGN = register("maple_hanging_sign", BlockFactory.hangingSign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));
    public static final Block MAPLE_WALL_HANGING_SIGN = register("maple_wall_hanging_sign", BlockFactory.wallHangingSign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN));

    public static final Block SAP_MAPLE_SAPLING = register("sap_maple_sapling", BlockFactory.sapling(PromenadeMapColors.SAP_MAPLE_LEAVES, PromenadeSaplingGenerators.SAP_MAPLE));
    public static final Block POTTED_SAP_MAPLE_SAPLING = register("potted_sap_maple_sapling", BlockFactory.pot(SAP_MAPLE_SAPLING));
    public static final Block SAP_MAPLE_LEAVES = register("sap_maple_leaves", BlockFactory.leaves(PromenadeMapColors.SAP_MAPLE_LEAVES));
    public static final Block FALLEN_SAP_MAPLE_LEAVES = register("fallen_sap_maple_leaves", BlockFactory.fallenLeaves(PromenadeMapColors.SAP_MAPLE_LEAVES, BlockSoundGroup.GRASS));
    public static final Block SAP_MAPLE_LEAF_PILE = register("sap_maple_leaf_pile", BlockFactory.pile(PromenadeMapColors.SAP_MAPLE_LEAVES, BlockSoundGroup.GRASS));

    public static final Block VERMILION_MAPLE_SAPLING = register("vermilion_maple_sapling", BlockFactory.sapling(PromenadeMapColors.VERMILION_MAPLE_LEAVES, PromenadeSaplingGenerators.VERMILION_MAPLE));
    public static final Block POTTED_VERMILION_MAPLE_SAPLING = register("potted_vermilion_maple_sapling", BlockFactory.pot(VERMILION_MAPLE_SAPLING));
    public static final Block VERMILION_MAPLE_LEAVES = register("vermilion_maple_leaves", BlockFactory.leaves(PromenadeMapColors.VERMILION_MAPLE_LEAVES, BlockSoundGroup.GRASS, 10, PromenadeParticleTypes.VERMILION_MAPLE_LEAF));
    public static final Block FALLEN_VERMILION_MAPLE_LEAVES = register("fallen_vermilion_maple_leaves", BlockFactory.fallenLeaves(PromenadeMapColors.VERMILION_MAPLE_LEAVES, BlockSoundGroup.GRASS));
    public static final Block VERMILION_MAPLE_LEAF_PILE = register("vermilion_maple_leaf_pile", BlockFactory.pile(PromenadeMapColors.VERMILION_MAPLE_LEAVES, BlockSoundGroup.GRASS));

    public static final Block FULVOUS_MAPLE_SAPLING = register("fulvous_maple_sapling", BlockFactory.sapling(PromenadeMapColors.FULVOUS_MAPLE_LEAVES, PromenadeSaplingGenerators.FULVOUS_MAPLE));
    public static final Block POTTED_FULVOUS_MAPLE_SAPLING = register("potted_fulvous_maple_sapling", BlockFactory.pot(FULVOUS_MAPLE_SAPLING));
    public static final Block FULVOUS_MAPLE_LEAVES = register("fulvous_maple_leaves", BlockFactory.leaves(PromenadeMapColors.FULVOUS_MAPLE_LEAVES, BlockSoundGroup.GRASS, 10, PromenadeParticleTypes.FULVOUS_MAPLE_LEAF));
    public static final Block FALLEN_FULVOUS_MAPLE_LEAVES = register("fallen_fulvous_maple_leaves", BlockFactory.fallenLeaves(PromenadeMapColors.FULVOUS_MAPLE_LEAVES, BlockSoundGroup.GRASS));
    public static final Block FULVOUS_MAPLE_LEAF_PILE = register("fulvous_maple_leaf_pile", BlockFactory.pile(PromenadeMapColors.FULVOUS_MAPLE_LEAVES, BlockSoundGroup.GRASS));

    public static final Block MIKADO_MAPLE_SAPLING = register("mikado_maple_sapling", BlockFactory.sapling(PromenadeMapColors.MIKADO_MAPLE_LEAVES, PromenadeSaplingGenerators.MIKADO_MAPLE));
    public static final Block POTTED_MIKADO_MAPLE_SAPLING = register("potted_mikado_maple_sapling", BlockFactory.pot(MIKADO_MAPLE_SAPLING));
    public static final Block MIKADO_MAPLE_LEAVES = register("mikado_maple_leaves", BlockFactory.leaves(PromenadeMapColors.MIKADO_MAPLE_LEAVES, BlockSoundGroup.GRASS, 10, PromenadeParticleTypes.MIKADO_MAPLE_LEAF));
    public static final Block FALLEN_MIKADO_MAPLE_LEAVES = register("fallen_mikado_maple_leaves", BlockFactory.fallenLeaves(PromenadeMapColors.MIKADO_MAPLE_LEAVES, BlockSoundGroup.GRASS));
    public static final Block MIKADO_MAPLE_LEAF_PILE = register("mikado_maple_leaf_pile", BlockFactory.pile(PromenadeMapColors.MIKADO_MAPLE_LEAVES, BlockSoundGroup.GRASS));


    /* ======== */
    /*   PALM   */
    /* ======== */
    public static final Block PALM_LOG = register("palm_log", BlockFactory.log(PromenadeMapColors.PALM_WOOD, PromenadeMapColors.PALM_BARK, PromenadeBlockSounds.PALM_WOOD, true));
    public static final Block STRIPPED_PALM_LOG = register("stripped_palm_log", BlockFactory.log(PromenadeMapColors.PALM_WOOD, PromenadeBlockSounds.PALM_WOOD, true));
    public static final Block PALM_WOOD = register("palm_wood", BlockFactory.log(PromenadeMapColors.PALM_BARK, PromenadeBlockSounds.PALM_WOOD, true));
    public static final Block STRIPPED_PALM_WOOD = register("stripped_palm_wood", BlockFactory.log(PromenadeMapColors.PALM_WOOD, PromenadeBlockSounds.PALM_WOOD, true));

    public static final Block PALM_PLANKS = register("palm_planks", BlockFactory.of(BlockSettings.planks(PromenadeMapColors.PALM_WOOD, PromenadeBlockSounds.PALM_WOOD, true)));
    public static final Block PALM_STAIRS = register("palm_stairs", BlockFactory.stairs(PALM_PLANKS));
    public static final Block PALM_SLAB = register("palm_slab", BlockFactory.slab(PALM_PLANKS));
    public static final Block PALM_FENCE = register("palm_fence", BlockFactory.fence(PALM_PLANKS));
    public static final Block PALM_FENCE_GATE = register("palm_fence_gate", BlockFactory.fenceGate(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_DOOR = register("palm_door", BlockFactory.door(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_TRAPDOOR = register("palm_trapdoor", BlockFactory.trapdoor(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_BUTTON = register("palm_button", BlockFactory.woodenButton(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_PRESSURE_PLATE = register("palm_pressure_plate", BlockFactory.pressurePlate(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_SIGN = register("palm_sign", BlockFactory.sign(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_WALL_SIGN = register("palm_wall_sign", BlockFactory.wallSign(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_HANGING_SIGN = register("palm_hanging_sign", BlockFactory.hangingSign(PALM_PLANKS, PromenadeWoodTypes.PALM, BlockSoundGroup.HANGING_SIGN));
    public static final Block PALM_WALL_HANGING_SIGN = register("palm_wall_hanging_sign", BlockFactory.wallHangingSign(PALM_PLANKS, PromenadeWoodTypes.PALM, BlockSoundGroup.HANGING_SIGN));

    public static final Block PALM_SAPLING = register("palm_sapling", BlockFactory.sapling(PromenadeMapColors.PALM_LEAVES, PromenadeSaplingGenerators.PALM, state -> state.isIn(BlockTags.SAND)));
    public static final Block POTTED_PALM_SAPLING = register("potted_palm_sapling", BlockFactory.pot(PALM_SAPLING));
    public static final Block PALM_LEAVES = register("palm_leaves", BlockFactory.leaves(PromenadeMapColors.PALM_LEAVES).factory(ExtendedLeavesBlock::new));
    public static final Block PALM_HANGING_LEAVES = register("palm_hanging_leaves", BlockFactory.hangingLeaves(PromenadeMapColors.PALM_LEAVES));
    public static final Block PALM_LEAF_PILE = register("palm_leaf_pile", BlockFactory.pile(PromenadeMapColors.PALM_LEAVES, BlockSoundGroup.GRASS));


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
    public static final Block DARK_AMARANTH_NYLIUM = register("dark_amaranth_nylium", BlockFactory.of(NyliumBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(0.4F)
                    .sounds(BlockSoundGroup.NYLIUM)
                    .ticksRandomly()));
    public static final Block DARK_AMARANTH_WART_BLOCK = register("dark_amaranth_wart_block",
            BlockFactory.of(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WART_BLOCK)));
    public static final Block DARK_AMARANTH_ROOTS = register("dark_amaranth_roots", BlockFactory.of(s -> new RootsBlock(state -> state.isIn(PromenadeBlockTags.DARK_AMARANTH_ROOTS_PLACEABLE_ON), s),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .replaceable()
                    .breakInstantly()
                    .noCollision()
                    .sounds(BlockSoundGroup.ROOTS)));
    public static final Block POTTED_DARK_AMARANTH_ROOTS = register("potted_dark_amaranth_roots", BlockFactory.pot(DARK_AMARANTH_ROOTS));

    public static final Block DARK_AMARANTH_STEM = register("dark_amaranth_stem", BlockFactory.log(PromenadeMapColors.AMARANTH_BARK, PromenadeBlockSounds.AMARANTH_WOOD, false));
    public static final Block STRIPPED_DARK_AMARANTH_STEM = register("stripped_dark_amaranth_stem", BlockFactory.log(PromenadeMapColors.AMARANTH_WOOD, PromenadeBlockSounds.AMARANTH_WOOD, false));
    public static final Block DARK_AMARANTH_HYPHAE = register("dark_amaranth_hyphae", BlockFactory.log(PromenadeMapColors.AMARANTH_BARK, PromenadeBlockSounds.AMARANTH_WOOD, false));
    public static final Block STRIPPED_DARK_AMARANTH_HYPHAE = register("stripped_dark_amaranth_hyphae", BlockFactory.log(PromenadeMapColors.AMARANTH_WOOD, PromenadeBlockSounds.AMARANTH_WOOD, false));

    public static final Block DARK_AMARANTH_PLANKS = register("dark_amaranth_planks", BlockFactory.of(BlockSettings.planks(PromenadeMapColors.AMARANTH_WOOD, PromenadeBlockSounds.AMARANTH_WOOD, false)));
    public static final Block DARK_AMARANTH_STAIRS = register("dark_amaranth_stairs", BlockFactory.stairs(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_SLAB = register("dark_amaranth_slab", BlockFactory.slab(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_FENCE = register("dark_amaranth_fence", BlockFactory.fence(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_FENCE_GATE = register("dark_amaranth_fence_gate", BlockFactory.fenceGate(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.AMARANTH));
    public static final Block DARK_AMARANTH_DOOR = register("dark_amaranth_door", BlockFactory.door(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_TRAPDOOR = register("dark_amaranth_trapdoor", BlockFactory.trapdoor(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_BUTTON = register("dark_amaranth_button", BlockFactory.woodenButton(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_PRESSURE_PLATE = register("dark_amaranth_pressure_plate", BlockFactory.pressurePlate(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.AMARANTH));
    public static final Block DARK_AMARANTH_SIGN = register("dark_amaranth_sign", BlockFactory.sign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.AMARANTH));
    public static final Block DARK_AMARANTH_WALL_SIGN = register("dark_amaranth_wall_sign", BlockFactory.wallSign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.AMARANTH));
    public static final Block DARK_AMARANTH_HANGING_SIGN = register("dark_amaranth_hanging_sign", BlockFactory.hangingSign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.AMARANTH, BlockSoundGroup.NETHER_WOOD_HANGING_SIGN));
    public static final Block DARK_AMARANTH_WALL_HANGING_SIGN = register("dark_amaranth_wall_hanging_sign", BlockFactory.wallHangingSign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.AMARANTH, BlockSoundGroup.NETHER_WOOD_HANGING_SIGN));

    public static final Block DARK_AMARANTH_FUNGUS = register("dark_amaranth_fungus", BlockFactory.fungus(MapColor.PURPLE, PromenadeConfiguredFeatures.PLANTED_DARK_AMARANTH_FUNGUS, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_GROWABLE_ON));
    public static final Block POTTED_DARK_AMARANTH_FUNGUS = register("potted_dark_amaranth_fungus", BlockFactory.pot(DARK_AMARANTH_FUNGUS));

    public static final Block SOUL_SHROOMLIGHT = register("soul_shroomlight", BlockFactory.of(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).strength(1.0F).sounds(BlockSoundGroup.SHROOMLIGHT).luminance(state -> 10)));

    public static final Block COILED_VINES = register("coiled_vines", BlockFactory.of(
            CoiledVinesBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .ticksRandomly()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WEEPING_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)
    ));
    public static final Block COILED_VINES_PLANT = register("coiled_vines_plant", BlockFactory.of(
            CoiledVinesPlantBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WEEPING_VINES)
                    .pistonBehavior(PistonBehavior.DESTROY)
    ).noItem());

    public static final Block MOAI = register("moai", BlockFactory.of(MoaiBlock::new, AbstractBlock.Settings.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)).itemSettings(s -> s.equipmentSlot((entity, stack) -> EquipmentSlot.HEAD)));

    public static final Block BLUEBERRY_BUSH = register("blueberry_bush", BlockFactory.of(s -> new BerryBushBlock(PromenadeItemKeys.BLUEBERRIES, false, s), AbstractBlock.Settings.create()
            .mapColor(MapColor.DARK_GREEN)
            .ticksRandomly()
            .noCollision()
            .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)
            .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block STAR_BITS = of(PromenadeBlockKeys.STAR_BITS, StarBitsBlock.of(MapColor.TERRACOTTA_WHITE));
    public static final Block STAR_FRAGMENT = of(PromenadeBlockKeys.STAR_FRAGMENT, StarFragmentBlock.of(MapColor.TERRACOTTA_WHITE));

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Promenade.id(id));
    }

    private static Block register(RegistryKey<Block> key, BlockBuilder builder) {
        return builder.register(key);
    }

    private static Block register(String id, BlockBuilder builder) {
        return register(keyOf(id), builder);
    }
}
