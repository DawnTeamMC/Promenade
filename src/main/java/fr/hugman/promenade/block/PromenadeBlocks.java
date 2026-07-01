package fr.hugman.promenade.block;

import fr.hugman.promenade.block.helper.BlockBuilder;
import fr.hugman.promenade.block.helper.BlockFactory;
import fr.hugman.promenade.block.map_color.PromenadeMapColors;
import fr.hugman.promenade.block.type.PromenadeBlockSetTypes;
import fr.hugman.promenade.block.type.PromenadeWoodTypes;
import fr.hugman.promenade.item.PromenadeItemKeys;
import fr.hugman.promenade.references.PromenadeBlockIds;
import fr.hugman.promenade.references.PromenadeBlockItemIds;
import fr.hugman.promenade.particle.PromenadeParticleTypes;
import fr.hugman.promenade.sound.PromenadeBlockSounds;
import fr.hugman.promenade.tag.PromenadeBlockTags;
import fr.hugman.promenade.world.PromenadeSaplingGenerators;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class PromenadeBlocks {
    /* ========= */
    /*   ROCKS   */
    /* ========= */
    public static final Block ASPHALT = register(PromenadeBlockItemIds.ASPHALT, BlockFactory.of(BlockSettings.rock(MapColor.DEEPSLATE, SoundType.BASALT)));
    public static final Block ASPHALT_SLAB = register(PromenadeBlockItemIds.ASPHALT_SLAB, BlockFactory.slab(ASPHALT));
    public static final Block ASPHALT_STAIRS = register(PromenadeBlockItemIds.ASPHALT_STAIRS, BlockFactory.stairs(ASPHALT));
    public static final Block ASPHALT_WALL = register(PromenadeBlockItemIds.ASPHALT_WALL, BlockFactory.wall(ASPHALT));

    public static final Block POLISHED_ASPHALT = register(PromenadeBlockItemIds.POLISHED_ASPHALT, BlockFactory.copy(ASPHALT));
    public static final Block POLISHED_ASPHALT_SLAB = register(PromenadeBlockItemIds.POLISHED_ASPHALT_SLAB, BlockFactory.slab(POLISHED_ASPHALT));
    public static final Block POLISHED_ASPHALT_STAIRS = register(PromenadeBlockItemIds.POLISHED_ASPHALT_STAIRS, BlockFactory.stairs(POLISHED_ASPHALT));

    public static final Block BLUNITE = register(PromenadeBlockItemIds.BLUNITE, BlockFactory.of(BlockSettings.rock(MapColor.TERRACOTTA_CYAN, SoundType.TUFF)));
    public static final Block BLUNITE_SLAB = register(PromenadeBlockItemIds.BLUNITE_SLAB, BlockFactory.slab(BLUNITE));
    public static final Block BLUNITE_STAIRS = register(PromenadeBlockItemIds.BLUNITE_STAIRS, BlockFactory.stairs(BLUNITE));
    public static final Block BLUNITE_WALL = register(PromenadeBlockItemIds.BLUNITE_WALL, BlockFactory.wall(BLUNITE));

    public static final Block POLISHED_BLUNITE = register(PromenadeBlockItemIds.POLISHED_BLUNITE, BlockFactory.copy(BLUNITE));
    public static final Block POLISHED_BLUNITE_SLAB = register(PromenadeBlockItemIds.POLISHED_BLUNITE_SLAB, BlockFactory.slab(POLISHED_BLUNITE));
    public static final Block POLISHED_BLUNITE_STAIRS = register(PromenadeBlockItemIds.POLISHED_BLUNITE_STAIRS, BlockFactory.stairs(POLISHED_BLUNITE));


    /* ================= */
    /*   VANILLA PILES   */
    /* ================= */
    public static final Block OAK_LEAF_PILE = register(PromenadeBlockItemIds.OAK_LEAF_PILE, BlockFactory.pile());
    public static final Block SPRUCE_LEAF_PILE = register(PromenadeBlockItemIds.SPRUCE_LEAF_PILE, BlockFactory.pile());
    public static final Block BIRCH_LEAF_PILE = register(PromenadeBlockItemIds.BIRCH_LEAF_PILE, BlockFactory.pile());
    public static final Block JUNGLE_LEAF_PILE = register(PromenadeBlockItemIds.JUNGLE_LEAF_PILE, BlockFactory.pile());
    public static final Block ACACIA_LEAF_PILE = register(PromenadeBlockItemIds.ACACIA_LEAF_PILE, BlockFactory.pile());
    public static final Block CHERRY_LEAF_PILE = register(PromenadeBlockItemIds.CHERRY_LEAF_PILE, BlockFactory.pile(MapColor.COLOR_PINK).settings(s -> s.sound(SoundType.CHERRY_LEAVES)));
    public static final Block DARK_OAK_LEAF_PILE = register(PromenadeBlockItemIds.DARK_OAK_LEAF_PILE, BlockFactory.pile());
    public static final Block PALE_OAK_LEAF_PILE = register(PromenadeBlockItemIds.PALE_OAK_LEAF_PILE, BlockFactory.pile());
    public static final Block MANGROVE_LEAF_PILE = register(PromenadeBlockItemIds.MANGROVE_LEAF_PILE, BlockFactory.pile());
    public static final Block AZALEA_LEAF_PILE = register(PromenadeBlockItemIds.AZALEA_LEAF_PILE, BlockFactory.pile().settings(s -> s.sound(SoundType.AZALEA_LEAVES)));
    public static final Block FLOWERING_AZALEA_LEAF_PILE = register(PromenadeBlockItemIds.FLOWERING_AZALEA_LEAF_PILE, BlockFactory.pile().settings(s -> s.sound(SoundType.AZALEA_LEAVES)));

    public static final Block DANDELION_PILE = register(PromenadeBlockItemIds.DANDELION_PILE, BlockFactory.pile(MapColor.GOLD));
    public static final Block POPPY_PILE = register(PromenadeBlockItemIds.POPPY_PILE, BlockFactory.pile(MapColor.CRIMSON_NYLIUM));
    public static final Block BLUE_ORCHID_PILE = register(PromenadeBlockItemIds.BLUE_ORCHID_PILE, BlockFactory.pile(MapColor.COLOR_LIGHT_BLUE));
    public static final Block ALLIUM_PILE = register(PromenadeBlockItemIds.ALLIUM_PILE, BlockFactory.pile(MapColor.COLOR_MAGENTA));
    public static final Block AZURE_BLUET_PILE = register(PromenadeBlockItemIds.AZURE_BLUET_PILE, BlockFactory.pile(MapColor.SAND));
    public static final Block RED_TULIP_PILE = register(PromenadeBlockItemIds.RED_TULIP_PILE, BlockFactory.pile(MapColor.CRIMSON_NYLIUM));
    public static final Block ORANGE_TULIP_PILE = register(PromenadeBlockItemIds.ORANGE_TULIP_PILE, BlockFactory.pile(MapColor.COLOR_ORANGE));
    public static final Block WHITE_TULIP_PILE = register(PromenadeBlockItemIds.WHITE_TULIP_PILE, BlockFactory.pile(MapColor.SNOW));
    public static final Block PINK_TULIP_PILE = register(PromenadeBlockItemIds.PINK_TULIP_PILE, BlockFactory.pile(MapColor.COLOR_PINK));
    public static final Block OXEYE_DAISY_PILE = register(PromenadeBlockItemIds.OXEYE_DAISY_PILE, BlockFactory.pile(MapColor.GOLD));
    public static final Block CORNFLOWER_PILE = register(PromenadeBlockItemIds.CORNFLOWER_PILE, BlockFactory.pile(MapColor.LAPIS));
    public static final Block LILY_OF_THE_VALLEY_PILE = register(PromenadeBlockItemIds.LILY_OF_THE_VALLEY_PILE, BlockFactory.pile(MapColor.SNOW));
    public static final Block WITHER_ROSE_PILE = register(PromenadeBlockItemIds.WITHER_ROSE_PILE, BlockFactory.pile(MapColor.COLOR_BLACK).factory(WitherRosePileBlock::new));


    /* ======================== */
    /*   VANILLA SNOWY LEAVES   */
    /* ======================== */
    public static final Block SNOWY_OAK_LEAVES = register(PromenadeBlockItemIds.SNOWY_OAK_LEAVES, BlockFactory.snowyLeaves());
    public static final Block SNOWY_SPRUCE_LEAVES = register(PromenadeBlockItemIds.SNOWY_SPRUCE_LEAVES, BlockFactory.snowyLeaves());
    public static final Block SNOWY_BIRCH_LEAVES = register(PromenadeBlockItemIds.SNOWY_BIRCH_LEAVES, BlockFactory.snowyLeaves());
    public static final Block SNOWY_JUNGLE_LEAVES = register(PromenadeBlockItemIds.SNOWY_JUNGLE_LEAVES, BlockFactory.snowyLeaves());
    public static final Block SNOWY_ACACIA_LEAVES = register(PromenadeBlockItemIds.SNOWY_ACACIA_LEAVES, BlockFactory.snowyLeaves());
    public static final Block SNOWY_CHERRY_LEAVES = register(PromenadeBlockItemIds.SNOWY_CHERRY_LEAVES, BlockFactory.snowyLeaves(0.1f, ParticleTypes.CHERRY_LEAVES, PromenadeBlockSounds.SNOWY_CHERRY_LEAVES));
    public static final Block SNOWY_DARK_OAK_LEAVES = register(PromenadeBlockItemIds.SNOWY_DARK_OAK_LEAVES, BlockFactory.snowyLeaves());
    public static final Block SNOWY_PALE_OAK_LEAVES = register(PromenadeBlockItemIds.SNOWY_PALE_OAK_LEAVES, BlockFactory.snowyLeaves(0.02F, ParticleTypes.PALE_OAK_LEAVES));
    public static final Block SNOWY_MANGROVE_LEAVES = register(PromenadeBlockItemIds.SNOWY_MANGROVE_LEAVES, BlockFactory.snowyLeaves());
    public static final Block SNOWY_AZALEA_LEAVES = register(PromenadeBlockItemIds.SNOWY_AZALEA_LEAVES, BlockFactory.snowyLeaves(PromenadeBlockSounds.SNOWY_AZALEA_LEAVES));
    public static final Block SNOWY_FLOWERING_AZALEA_LEAVES = register(PromenadeBlockItemIds.SNOWY_FLOWERING_AZALEA_LEAVES, BlockFactory.snowyLeaves(PromenadeBlockSounds.SNOWY_AZALEA_LEAVES));


    /* ========== */
    /*   SAKURA   */
    /* ========== */
    public static final Block SAKURA_LOG = register(PromenadeBlockItemIds.SAKURA_LOG, BlockFactory.log(PromenadeMapColors.SAKURA_WOOD, PromenadeMapColors.SAKURA_BARK, PromenadeBlockSounds.SAKURA_WOOD, true));
    public static final Block STRIPPED_SAKURA_LOG = register(PromenadeBlockItemIds.STRIPPED_SAKURA_LOG, BlockFactory.log(PromenadeMapColors.SAKURA_WOOD, PromenadeBlockSounds.SAKURA_WOOD, true));
    public static final Block SAKURA_WOOD = register(PromenadeBlockItemIds.SAKURA_WOOD, BlockFactory.log(PromenadeMapColors.SAKURA_BARK, PromenadeBlockSounds.SAKURA_WOOD, true));
    public static final Block STRIPPED_SAKURA_WOOD = register(PromenadeBlockItemIds.STRIPPED_SAKURA_WOOD, BlockFactory.log(PromenadeMapColors.SAKURA_WOOD, PromenadeBlockSounds.SAKURA_WOOD, true));

    public static final Block SAKURA_PLANKS = register(PromenadeBlockItemIds.SAKURA_PLANKS, BlockFactory.of(BlockSettings.planks(PromenadeMapColors.SAKURA_WOOD, PromenadeBlockSounds.SAKURA_WOOD, true)));
    public static final Block SAKURA_STAIRS = register(PromenadeBlockItemIds.SAKURA_STAIRS, BlockFactory.stairs(SAKURA_PLANKS));
    public static final Block SAKURA_SLAB = register(PromenadeBlockItemIds.SAKURA_SLAB, BlockFactory.slab(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE = register(PromenadeBlockItemIds.SAKURA_FENCE, BlockFactory.fence(SAKURA_PLANKS));
    public static final Block SAKURA_FENCE_GATE = register(PromenadeBlockItemIds.SAKURA_FENCE_GATE, BlockFactory.fenceGate(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_DOOR = register(PromenadeBlockItemIds.SAKURA_DOOR, BlockFactory.door(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_TRAPDOOR = register(PromenadeBlockItemIds.SAKURA_TRAPDOOR, BlockFactory.trapdoor(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_BUTTON = register(PromenadeBlockItemIds.SAKURA_BUTTON, BlockFactory.woodenButton(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_PRESSURE_PLATE = register(PromenadeBlockItemIds.SAKURA_PRESSURE_PLATE, BlockFactory.pressurePlate(SAKURA_PLANKS, PromenadeBlockSetTypes.SAKURA));
    public static final Block SAKURA_SIGN = register(PromenadeBlockItemIds.SAKURA_SIGN, BlockFactory.sign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_WALL_SIGN = register(PromenadeBlockIds.SAKURA_WALL_SIGN, BlockFactory.wallSign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA));
    public static final Block SAKURA_HANGING_SIGN = register(PromenadeBlockItemIds.SAKURA_HANGING_SIGN, BlockFactory.hangingSign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA, SoundType.CHERRY_WOOD_HANGING_SIGN));
    public static final Block SAKURA_WALL_HANGING_SIGN = register(PromenadeBlockIds.SAKURA_WALL_HANGING_SIGN, BlockFactory.wallHangingSign(SAKURA_PLANKS, PromenadeWoodTypes.SAKURA, SoundType.CHERRY_WOOD_HANGING_SIGN));
    public static final Block SAKURA_SHELF = register(PromenadeBlockItemIds.SAKURA_SHELF, BlockFactory.shelf(SAKURA_PLANKS));

    public static final Block BLUSH_SAKURA_SAPLING = register(PromenadeBlockItemIds.BLUSH_SAKURA_SAPLING, BlockFactory.sapling(PromenadeMapColors.BLUSH_BLOSSOMS, PromenadeSaplingGenerators.BLUSH_SAKURA));
    public static final Block POTTED_BLUSH_SAKURA_SAPLING = register(PromenadeBlockIds.POTTED_BLUSH_SAKURA_SAPLING, BlockFactory.pot(BLUSH_SAKURA_SAPLING));
    public static final Block BLUSH_SAKURA_BLOSSOMS = register(PromenadeBlockItemIds.BLUSH_SAKURA_BLOSSOMS, BlockFactory.leaves(PromenadeMapColors.BLUSH_BLOSSOMS, SoundType.CHERRY_LEAVES, 0.1f, PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM));
    public static final Block SNOWY_BLUSH_SAKURA_BLOSSOMS = register(PromenadeBlockItemIds.SNOWY_BLUSH_SAKURA_BLOSSOMS, BlockFactory.snowyLeaves(0.1f, PromenadeParticleTypes.BLUSH_SAKURA_BLOSSOM, PromenadeBlockSounds.SNOWY_CHERRY_LEAVES));
    public static final Block BLUSH_SAKURA_BLOSSOM_PILE = register(PromenadeBlockItemIds.BLUSH_SAKURA_BLOSSOM_PILE, BlockFactory.pile(PromenadeMapColors.BLUSH_BLOSSOMS, SoundType.CHERRY_LEAVES));

    public static final Block COTTON_SAKURA_SAPLING = register(PromenadeBlockItemIds.COTTON_SAKURA_SAPLING, BlockFactory.sapling(PromenadeMapColors.COTTON_BLOSSOMS, PromenadeSaplingGenerators.COTTON_SAKURA));
    public static final Block POTTED_COTTON_SAKURA_SAPLING = register(PromenadeBlockIds.POTTED_COTTON_SAKURA_SAPLING, BlockFactory.pot(COTTON_SAKURA_SAPLING));
    public static final Block COTTON_SAKURA_BLOSSOMS = register(PromenadeBlockItemIds.COTTON_SAKURA_BLOSSOMS, BlockFactory.leaves(PromenadeMapColors.COTTON_BLOSSOMS, SoundType.CHERRY_LEAVES, 0.1f, PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM));
    public static final Block SNOWY_COTTON_SAKURA_BLOSSOMS = register(PromenadeBlockItemIds.SNOWY_COTTON_SAKURA_BLOSSOMS, BlockFactory.snowyLeaves(0.1f, PromenadeParticleTypes.COTTON_SAKURA_BLOSSOM, PromenadeBlockSounds.SNOWY_CHERRY_LEAVES));
    public static final Block COTTON_SAKURA_BLOSSOM_PILE = register(PromenadeBlockItemIds.COTTON_SAKURA_BLOSSOM_PILE, BlockFactory.pile(PromenadeMapColors.COTTON_BLOSSOMS, SoundType.CHERRY_LEAVES));


    /* ========= */
    /*   MAPLE   */
    /* ========= */
    public static final Block MAPLE_LOG = register(PromenadeBlockItemIds.MAPLE_LOG, BlockFactory.log(PromenadeMapColors.MAPLE_WOOD, PromenadeMapColors.MAPLE_BARK, PromenadeBlockSounds.MAPLE_WOOD, true).factory(MapleLogBlock::new));
    public static final Block STRIPPED_MAPLE_LOG = register(PromenadeBlockItemIds.STRIPPED_MAPLE_LOG, BlockFactory.log(PromenadeMapColors.MAPLE_WOOD, PromenadeBlockSounds.MAPLE_WOOD, true).factory(StrippedMapleLogBlock::new));
    public static final Block MAPLE_WOOD = register(PromenadeBlockItemIds.MAPLE_WOOD, BlockFactory.log(PromenadeMapColors.MAPLE_BARK, PromenadeBlockSounds.MAPLE_WOOD, true));
    public static final Block STRIPPED_MAPLE_WOOD = register(PromenadeBlockItemIds.STRIPPED_MAPLE_WOOD, BlockFactory.log(PromenadeMapColors.MAPLE_WOOD, PromenadeBlockSounds.MAPLE_WOOD, true));

    public static final Block MAPLE_PLANKS = register(PromenadeBlockItemIds.MAPLE_PLANKS, BlockFactory.of(BlockSettings.planks(PromenadeMapColors.MAPLE_WOOD, PromenadeBlockSounds.MAPLE_WOOD, true)));
    public static final Block MAPLE_STAIRS = register(PromenadeBlockItemIds.MAPLE_STAIRS, BlockFactory.stairs(MAPLE_PLANKS));
    public static final Block MAPLE_SLAB = register(PromenadeBlockItemIds.MAPLE_SLAB, BlockFactory.slab(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE = register(PromenadeBlockItemIds.MAPLE_FENCE, BlockFactory.fence(MAPLE_PLANKS));
    public static final Block MAPLE_FENCE_GATE = register(PromenadeBlockItemIds.MAPLE_FENCE_GATE, BlockFactory.fenceGate(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_DOOR = register(PromenadeBlockItemIds.MAPLE_DOOR, BlockFactory.door(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_TRAPDOOR = register(PromenadeBlockItemIds.MAPLE_TRAPDOOR, BlockFactory.trapdoor(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_BUTTON = register(PromenadeBlockItemIds.MAPLE_BUTTON, BlockFactory.woodenButton(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_PRESSURE_PLATE = register(PromenadeBlockItemIds.MAPLE_PRESSURE_PLATE, BlockFactory.pressurePlate(MAPLE_PLANKS, PromenadeBlockSetTypes.MAPLE));
    public static final Block MAPLE_SIGN = register(PromenadeBlockItemIds.MAPLE_SIGN, BlockFactory.sign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_WALL_SIGN = register(PromenadeBlockIds.MAPLE_WALL_SIGN, BlockFactory.wallSign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE));
    public static final Block MAPLE_HANGING_SIGN = register(PromenadeBlockItemIds.MAPLE_HANGING_SIGN, BlockFactory.hangingSign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE, SoundType.CHERRY_WOOD_HANGING_SIGN));
    public static final Block MAPLE_WALL_HANGING_SIGN = register(PromenadeBlockIds.MAPLE_WALL_HANGING_SIGN, BlockFactory.wallHangingSign(MAPLE_PLANKS, PromenadeWoodTypes.MAPLE, SoundType.CHERRY_WOOD_HANGING_SIGN));
    public static final Block MAPLE_SHELF = register(PromenadeBlockItemIds.MAPLE_SHELF, BlockFactory.shelf(MAPLE_PLANKS));

    public static final Block SAP_MAPLE_SAPLING = register(PromenadeBlockItemIds.SAP_MAPLE_SAPLING, BlockFactory.sapling(PromenadeMapColors.SAP_MAPLE_LEAVES, PromenadeSaplingGenerators.SAP_MAPLE));
    public static final Block POTTED_SAP_MAPLE_SAPLING = register(PromenadeBlockIds.POTTED_SAP_MAPLE_SAPLING, BlockFactory.pot(SAP_MAPLE_SAPLING));
    public static final Block SAP_MAPLE_LEAVES = register(PromenadeBlockItemIds.SAP_MAPLE_LEAVES, BlockFactory.leaves(PromenadeMapColors.SAP_MAPLE_LEAVES));
    public static final Block SNOWY_SAP_MAPLE_LEAVES = register(PromenadeBlockItemIds.SNOWY_SAP_MAPLE_LEAVES, BlockFactory.snowyLeaves());
    public static final Block FALLEN_SAP_MAPLE_LEAVES = register(PromenadeBlockItemIds.FALLEN_SAP_MAPLE_LEAVES, BlockFactory.fallenLeaves(PromenadeMapColors.SAP_MAPLE_LEAVES));
    public static final Block SAP_MAPLE_LEAF_PILE = register(PromenadeBlockItemIds.SAP_MAPLE_LEAF_PILE, BlockFactory.pile(PromenadeMapColors.SAP_MAPLE_LEAVES, SoundType.GRASS));

    public static final Block VERMILION_MAPLE_SAPLING = register(PromenadeBlockItemIds.VERMILION_MAPLE_SAPLING, BlockFactory.sapling(PromenadeMapColors.VERMILION_MAPLE_LEAVES, PromenadeSaplingGenerators.VERMILION_MAPLE));
    public static final Block POTTED_VERMILION_MAPLE_SAPLING = register(PromenadeBlockIds.POTTED_VERMILION_MAPLE_SAPLING, BlockFactory.pot(VERMILION_MAPLE_SAPLING));
    public static final Block VERMILION_MAPLE_LEAVES = register(PromenadeBlockItemIds.VERMILION_MAPLE_LEAVES, BlockFactory.leaves(PromenadeMapColors.VERMILION_MAPLE_LEAVES, SoundType.GRASS, 0.01F, PromenadeParticleTypes.VERMILION_MAPLE_LEAF));
    public static final Block SNOWY_VERMILION_MAPLE_LEAVES = register(PromenadeBlockItemIds.SNOWY_VERMILION_MAPLE_LEAVES, BlockFactory.snowyLeaves(0.01F, PromenadeParticleTypes.VERMILION_MAPLE_LEAF));
    public static final Block FALLEN_VERMILION_MAPLE_LEAVES = register(PromenadeBlockItemIds.FALLEN_VERMILION_MAPLE_LEAVES, BlockFactory.fallenLeaves(PromenadeMapColors.VERMILION_MAPLE_LEAVES));
    public static final Block VERMILION_MAPLE_LEAF_PILE = register(PromenadeBlockItemIds.VERMILION_MAPLE_LEAF_PILE, BlockFactory.pile(PromenadeMapColors.VERMILION_MAPLE_LEAVES, SoundType.GRASS));

    public static final Block FULVOUS_MAPLE_SAPLING = register(PromenadeBlockItemIds.FULVOUS_MAPLE_SAPLING, BlockFactory.sapling(PromenadeMapColors.FULVOUS_MAPLE_LEAVES, PromenadeSaplingGenerators.FULVOUS_MAPLE));
    public static final Block POTTED_FULVOUS_MAPLE_SAPLING = register(PromenadeBlockIds.POTTED_FULVOUS_MAPLE_SAPLING, BlockFactory.pot(FULVOUS_MAPLE_SAPLING));
    public static final Block FULVOUS_MAPLE_LEAVES = register(PromenadeBlockItemIds.FULVOUS_MAPLE_LEAVES, BlockFactory.leaves(PromenadeMapColors.FULVOUS_MAPLE_LEAVES, SoundType.GRASS, 0.01F, PromenadeParticleTypes.FULVOUS_MAPLE_LEAF));
    public static final Block SNOWY_FULVOUS_MAPLE_LEAVES = register(PromenadeBlockItemIds.SNOWY_FULVOUS_MAPLE_LEAVES, BlockFactory.snowyLeaves(0.01F, PromenadeParticleTypes.FULVOUS_MAPLE_LEAF));
    public static final Block FALLEN_FULVOUS_MAPLE_LEAVES = register(PromenadeBlockItemIds.FALLEN_FULVOUS_MAPLE_LEAVES, BlockFactory.fallenLeaves(PromenadeMapColors.FULVOUS_MAPLE_LEAVES));
    public static final Block FULVOUS_MAPLE_LEAF_PILE = register(PromenadeBlockItemIds.FULVOUS_MAPLE_LEAF_PILE, BlockFactory.pile(PromenadeMapColors.FULVOUS_MAPLE_LEAVES, SoundType.GRASS));

    public static final Block MIKADO_MAPLE_SAPLING = register(PromenadeBlockItemIds.MIKADO_MAPLE_SAPLING, BlockFactory.sapling(PromenadeMapColors.MIKADO_MAPLE_LEAVES, PromenadeSaplingGenerators.MIKADO_MAPLE));
    public static final Block POTTED_MIKADO_MAPLE_SAPLING = register(PromenadeBlockIds.POTTED_MIKADO_MAPLE_SAPLING, BlockFactory.pot(MIKADO_MAPLE_SAPLING));
    public static final Block MIKADO_MAPLE_LEAVES = register(PromenadeBlockItemIds.MIKADO_MAPLE_LEAVES, BlockFactory.leaves(PromenadeMapColors.MIKADO_MAPLE_LEAVES, SoundType.GRASS, 0.01F, PromenadeParticleTypes.MIKADO_MAPLE_LEAF));
    public static final Block SNOWY_MIKADO_MAPLE_LEAVES = register(PromenadeBlockItemIds.SNOWY_MIKADO_MAPLE_LEAVES, BlockFactory.snowyLeaves(0.01F, PromenadeParticleTypes.MIKADO_MAPLE_LEAF));
    public static final Block FALLEN_MIKADO_MAPLE_LEAVES = register(PromenadeBlockItemIds.FALLEN_MIKADO_MAPLE_LEAVES, BlockFactory.fallenLeaves(PromenadeMapColors.MIKADO_MAPLE_LEAVES));
    public static final Block MIKADO_MAPLE_LEAF_PILE = register(PromenadeBlockItemIds.MIKADO_MAPLE_LEAF_PILE, BlockFactory.pile(PromenadeMapColors.MIKADO_MAPLE_LEAVES, SoundType.GRASS));


    /* ======== */
    /*   PALM   */
    /* ======== */
    public static final Block PALM_LOG = register(PromenadeBlockItemIds.PALM_LOG, BlockFactory.log(PromenadeMapColors.PALM_WOOD, PromenadeMapColors.PALM_BARK, PromenadeBlockSounds.PALM_WOOD, true));
    public static final Block STRIPPED_PALM_LOG = register(PromenadeBlockItemIds.STRIPPED_PALM_LOG, BlockFactory.log(PromenadeMapColors.PALM_WOOD, PromenadeBlockSounds.PALM_WOOD, true));
    public static final Block PALM_WOOD = register(PromenadeBlockItemIds.PALM_WOOD, BlockFactory.log(PromenadeMapColors.PALM_BARK, PromenadeBlockSounds.PALM_WOOD, true));
    public static final Block STRIPPED_PALM_WOOD = register(PromenadeBlockItemIds.STRIPPED_PALM_WOOD, BlockFactory.log(PromenadeMapColors.PALM_WOOD, PromenadeBlockSounds.PALM_WOOD, true));

    public static final Block PALM_PLANKS = register(PromenadeBlockItemIds.PALM_PLANKS, BlockFactory.of(BlockSettings.planks(PromenadeMapColors.PALM_WOOD, PromenadeBlockSounds.PALM_WOOD, true)));
    public static final Block PALM_STAIRS = register(PromenadeBlockItemIds.PALM_STAIRS, BlockFactory.stairs(PALM_PLANKS));
    public static final Block PALM_SLAB = register(PromenadeBlockItemIds.PALM_SLAB, BlockFactory.slab(PALM_PLANKS));
    public static final Block PALM_FENCE = register(PromenadeBlockItemIds.PALM_FENCE, BlockFactory.fence(PALM_PLANKS));
    public static final Block PALM_FENCE_GATE = register(PromenadeBlockItemIds.PALM_FENCE_GATE, BlockFactory.fenceGate(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_DOOR = register(PromenadeBlockItemIds.PALM_DOOR, BlockFactory.door(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_TRAPDOOR = register(PromenadeBlockItemIds.PALM_TRAPDOOR, BlockFactory.trapdoor(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_BUTTON = register(PromenadeBlockItemIds.PALM_BUTTON, BlockFactory.woodenButton(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_PRESSURE_PLATE = register(PromenadeBlockItemIds.PALM_PRESSURE_PLATE, BlockFactory.pressurePlate(PALM_PLANKS, PromenadeBlockSetTypes.PALM));
    public static final Block PALM_SIGN = register(PromenadeBlockItemIds.PALM_SIGN, BlockFactory.sign(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_WALL_SIGN = register(PromenadeBlockIds.PALM_WALL_SIGN, BlockFactory.wallSign(PALM_PLANKS, PromenadeWoodTypes.PALM));
    public static final Block PALM_HANGING_SIGN = register(PromenadeBlockItemIds.PALM_HANGING_SIGN, BlockFactory.hangingSign(PALM_PLANKS, PromenadeWoodTypes.PALM, SoundType.HANGING_SIGN));
    public static final Block PALM_WALL_HANGING_SIGN = register(PromenadeBlockIds.PALM_WALL_HANGING_SIGN, BlockFactory.wallHangingSign(PALM_PLANKS, PromenadeWoodTypes.PALM, SoundType.HANGING_SIGN));
    public static final Block PALM_SHELF = register(PromenadeBlockItemIds.PALM_SHELF, BlockFactory.shelf(PALM_PLANKS));

    public static final Block PALM_SAPLING = register(PromenadeBlockItemIds.PALM_SAPLING, BlockFactory.sapling(PromenadeMapColors.PALM_LEAVES, PromenadeSaplingGenerators.PALM, state -> state.is(BlockTags.SAND)));
    public static final Block POTTED_PALM_SAPLING = register(PromenadeBlockIds.POTTED_PALM_SAPLING, BlockFactory.pot(PALM_SAPLING));
    public static final Block PALM_LEAVES = register(PromenadeBlockItemIds.PALM_LEAVES, BlockFactory.leaves(PromenadeMapColors.PALM_LEAVES).factory(s -> new TintedParticleExtendedLeavesBlock(0.01F, s)));
    public static final Block SNOWY_PALM_LEAVES = register(PromenadeBlockItemIds.SNOWY_PALM_LEAVES, BlockFactory.snowyLeaves().factory(s -> new TintedParticleSnowyExtendedLeavesBlock(0.01F, s)));
    public static final Block PALM_HANGING_LEAVES = register(PromenadeBlockItemIds.PALM_HANGING_LEAVES, BlockFactory.hangingLeaves(PromenadeMapColors.PALM_LEAVES));
    public static final Block PALM_LEAF_PILE = register(PromenadeBlockItemIds.PALM_LEAF_PILE, BlockFactory.pile(PromenadeMapColors.PALM_LEAVES, SoundType.GRASS));


    /* ============ */
    /*   AMARANTH   */
    /* ============ */
    public static final Block DARK_AMARANTH_NYLIUM = register(PromenadeBlockItemIds.DARK_AMARANTH_NYLIUM, BlockFactory.of(NyliumBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.4F)
                    .sound(SoundType.NYLIUM)
                    .randomTicks()));
    public static final Block DARK_AMARANTH_WART_BLOCK = register(PromenadeBlockItemIds.DARK_AMARANTH_WART_BLOCK,
            BlockFactory.of(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .strength(1.0F)
                    .sound(SoundType.WART_BLOCK)));
    public static final Block DARK_AMARANTH_ROOTS = register(PromenadeBlockItemIds.DARK_AMARANTH_ROOTS, BlockFactory.of(s -> new RootsBlock(state -> state.is(PromenadeBlockTags.DARK_AMARANTH_ROOTS_PLACEABLE_ON), s),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .replaceable()
                    .instabreak()
                    .noCollision()
                    .sound(SoundType.ROOTS)));
    public static final Block POTTED_DARK_AMARANTH_ROOTS = register(PromenadeBlockIds.POTTED_DARK_AMARANTH_ROOTS, BlockFactory.pot(DARK_AMARANTH_ROOTS));

    public static final Block DARK_AMARANTH_STEM = register(PromenadeBlockItemIds.DARK_AMARANTH_STEM, BlockFactory.log(PromenadeMapColors.AMARANTH_BARK, PromenadeBlockSounds.AMARANTH_WOOD, false));
    public static final Block STRIPPED_DARK_AMARANTH_STEM = register(PromenadeBlockItemIds.STRIPPED_DARK_AMARANTH_STEM, BlockFactory.log(PromenadeMapColors.AMARANTH_WOOD, PromenadeBlockSounds.AMARANTH_WOOD, false));
    public static final Block DARK_AMARANTH_HYPHAE = register(PromenadeBlockItemIds.DARK_AMARANTH_HYPHAE, BlockFactory.log(PromenadeMapColors.AMARANTH_BARK, PromenadeBlockSounds.AMARANTH_WOOD, false));
    public static final Block STRIPPED_DARK_AMARANTH_HYPHAE = register(PromenadeBlockItemIds.STRIPPED_DARK_AMARANTH_HYPHAE, BlockFactory.log(PromenadeMapColors.AMARANTH_WOOD, PromenadeBlockSounds.AMARANTH_WOOD, false));

    public static final Block DARK_AMARANTH_PLANKS = register(PromenadeBlockItemIds.DARK_AMARANTH_PLANKS, BlockFactory.of(BlockSettings.planks(PromenadeMapColors.AMARANTH_WOOD, PromenadeBlockSounds.AMARANTH_WOOD, false)));
    public static final Block DARK_AMARANTH_STAIRS = register(PromenadeBlockItemIds.DARK_AMARANTH_STAIRS, BlockFactory.stairs(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_SLAB = register(PromenadeBlockItemIds.DARK_AMARANTH_SLAB, BlockFactory.slab(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_FENCE = register(PromenadeBlockItemIds.DARK_AMARANTH_FENCE, BlockFactory.fence(DARK_AMARANTH_PLANKS));
    public static final Block DARK_AMARANTH_FENCE_GATE = register(PromenadeBlockItemIds.DARK_AMARANTH_FENCE_GATE, BlockFactory.fenceGate(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.DARK_AMARANTH));
    public static final Block DARK_AMARANTH_DOOR = register(PromenadeBlockItemIds.DARK_AMARANTH_DOOR, BlockFactory.door(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.DARK_AMARANTH));
    public static final Block DARK_AMARANTH_TRAPDOOR = register(PromenadeBlockItemIds.DARK_AMARANTH_TRAPDOOR, BlockFactory.trapdoor(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.DARK_AMARANTH));
    public static final Block DARK_AMARANTH_BUTTON = register(PromenadeBlockItemIds.DARK_AMARANTH_BUTTON, BlockFactory.woodenButton(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.DARK_AMARANTH));
    public static final Block DARK_AMARANTH_PRESSURE_PLATE = register(PromenadeBlockItemIds.DARK_AMARANTH_PRESSURE_PLATE, BlockFactory.pressurePlate(DARK_AMARANTH_PLANKS, PromenadeBlockSetTypes.DARK_AMARANTH));
    public static final Block DARK_AMARANTH_SIGN = register(PromenadeBlockItemIds.DARK_AMARANTH_SIGN, BlockFactory.sign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.DARK_AMARANTH));
    public static final Block DARK_AMARANTH_WALL_SIGN = register(PromenadeBlockIds.DARK_AMARANTH_WALL_SIGN, BlockFactory.wallSign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.DARK_AMARANTH));
    public static final Block DARK_AMARANTH_HANGING_SIGN = register(PromenadeBlockItemIds.DARK_AMARANTH_HANGING_SIGN, BlockFactory.hangingSign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.DARK_AMARANTH, SoundType.NETHER_WOOD_HANGING_SIGN));
    public static final Block DARK_AMARANTH_WALL_HANGING_SIGN = register(PromenadeBlockIds.DARK_AMARANTH_WALL_HANGING_SIGN, BlockFactory.wallHangingSign(DARK_AMARANTH_PLANKS, PromenadeWoodTypes.DARK_AMARANTH, SoundType.NETHER_WOOD_HANGING_SIGN));
    public static final Block DARK_AMARANTH_SHELF = register(PromenadeBlockItemIds.DARK_AMARANTH_SHELF, BlockFactory.shelf(DARK_AMARANTH_PLANKS));

    public static final Block DARK_AMARANTH_FUNGUS = register(PromenadeBlockItemIds.DARK_AMARANTH_FUNGUS, BlockFactory.fungus(MapColor.COLOR_PURPLE, PromenadeConfiguredFeatures.PLANTED_DARK_AMARANTH_FUNGUS, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_GROWABLE_ON));
    public static final Block POTTED_DARK_AMARANTH_FUNGUS = register(PromenadeBlockIds.POTTED_DARK_AMARANTH_FUNGUS, BlockFactory.pot(DARK_AMARANTH_FUNGUS));

    public static final Block SOUL_SHROOMLIGHT = register(PromenadeBlockItemIds.SOUL_SHROOMLIGHT, BlockFactory.of(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.0F).sound(SoundType.SHROOMLIGHT).lightLevel(state -> 10)));

    public static final Block COILED_VINES = register(PromenadeBlockItemIds.COILED_VINES, BlockFactory.of(
            CoiledVinesBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .randomTicks()
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final Block COILED_VINES_PLANT = register(PromenadeBlockIds.COILED_VINES_PLANT, BlockFactory.of(
            CoiledVinesPlantBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WEEPING_VINES)
                    .pushReaction(PushReaction.DESTROY)
    ).noItem());

    public static final Block MOAI = register(PromenadeBlockItemIds.MOAI, BlockFactory.of(MoaiBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)).itemSettings(s -> s.equipmentSlot((entity, stack) -> EquipmentSlot.HEAD)));

    public static final Block BLUEBERRY_BUSH = register(PromenadeBlockIds.BLUEBERRY_BUSH, BlockFactory.of(s -> new BerryBushBlock(PromenadeItemKeys.BLUEBERRIES, false, s), BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .randomTicks()
            .noCollision()
            .sound(SoundType.SWEET_BERRY_BUSH)
            .pushReaction(PushReaction.DESTROY)).noItem());

    private static Block register(ResourceKey<Block> key, BlockBuilder builder) {
        return builder.register(key);
    }

    private static Block register(BlockItemId id, BlockBuilder builder) {
        return register(id.block(), builder);
    }
}
