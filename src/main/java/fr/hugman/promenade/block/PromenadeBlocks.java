package fr.hugman.promenade.block;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.particle.PromenadeParticleTypes;
import fr.hugman.promenade.registry.content.SakuraContent;
import fr.hugman.promenade.sign.PromenadeSigns;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
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

    public static final Block MAPLE_SIGN = register(PromenadeBlockKeys.MAPLE_SIGN, PromenadeSigns.MAPLE.sign());
    public static final Block MAPLE_WALL_SIGN = register(PromenadeBlockKeys.MAPLE_WALL_SIGN, PromenadeSigns.MAPLE.wallSign());
    public static final Block MAPLE_HANGING_SIGN = register(PromenadeBlockKeys.MAPLE_HANGING_SIGN, PromenadeSigns.MAPLE.hangingSign());
    public static final Block MAPLE_WALL_HANGING_SIGN = register(PromenadeBlockKeys.MAPLE_WALL_HANGING_SIGN, PromenadeSigns.MAPLE.wallHangingSign());

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

    @Deprecated
    public static <B extends Block> B register(String path, B block) {
        return Registry.register(Registries.BLOCK, Promenade.id(path), block);
    }

    public static <B extends Block> B register(RegistryKey<Block> key, B block) {
        return Registry.register(Registries.BLOCK, key, block);
    }

    public static void appendItemGroups() {
        ItemGroupHelper.append(ItemGroups.NATURAL, e -> e.addAfter(Blocks.ANDESITE, BLUNITE, ASPHALT));
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.POLISHED_ANDESITE_SLAB,
                BLUNITE, BLUNITE_SLAB, BLUNITE_STAIRS, BLUNITE_WALL,
                POLISHED_BLUNITE, POLISHED_BLUNITE_SLAB, POLISHED_BLUNITE_STAIRS,
                ASPHALT, ASPHALT_SLAB, ASPHALT_STAIRS, ASPHALT_WALL,
                POLISHED_ASPHALT, POLISHED_ASPHALT_SLAB, POLISHED_ASPHALT_STAIRS
        ));

        // MAPLE
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e ->
                e.addAfter(SakuraContent.SAKURA_BUTTON,
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
            e.addAfter(SakuraContent.SAKURA_LOG, MAPLE_LOG);
            e.addAfter(Blocks.GRASS_BLOCK, VERMILION_CARPETED_GRASS_BLOCK, FULVOUS_CARPETED_GRASS_BLOCK, MIKADO_CARPETED_GRASS_BLOCK);
            e.addAfter(SakuraContent.COTTON_SAKURA_BLOSSOMS, SAP_MAPLE_LEAVES, VERMILION_MAPLE_LEAVES, FULVOUS_MAPLE_LEAVES, MIKADO_MAPLE_LEAVES);
            e.addAfter(SakuraContent.COTTON_SAKURA_SAPLING, SAP_MAPLE_SAPLING, VERMILION_MAPLE_SAPLING, FULVOUS_MAPLE_SAPLING, MIKADO_MAPLE_SAPLING);
            e.addAfter(SakuraContent.COTTON_SAKURA_BLOSSOM_PILE, SAP_MAPLE_LEAF_PILE, VERMILION_MAPLE_LEAF_PILE, FULVOUS_MAPLE_LEAF_PILE, MIKADO_MAPLE_LEAF_PILE);
        });
    }

    public static void appendVillagerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(TradeOfferUtils.sapling(VERMILION_MAPLE_SAPLING));
            factories.add(TradeOfferUtils.sapling(FULVOUS_MAPLE_SAPLING));
            factories.add(TradeOfferUtils.sapling(MIKADO_MAPLE_SAPLING));
            factories.add(TradeOfferUtils.sapling(SAP_MAPLE_SAPLING));
        });
    }
}
