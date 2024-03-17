package fr.hugman.promenade.registry.content;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnRootsBlock;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.DyliumBlock;
import fr.hugman.promenade.registry.tag.PromenadeBlockTags;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;

public class AmaranthContent {
    public static final BlockSetType BLOCK_SET_TYPE = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(Promenade.id("dark_amaranth"));
    public static final WoodType WOOD_TYPE = WoodTypeBuilder.copyOf(WoodType.CRIMSON).register(Promenade.id("dark_amaranth"), BLOCK_SET_TYPE);
    private static final BlockSoundGroup WOOD_SOUNDS = BlockSoundGroup.NETHER_WOOD;
    private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
    private static final MapColor WOOD_COLOR = MapColor.GRAY;

    public static final RegistryKey<ConfiguredFeature<?, ?>> PLANTED_AMARANTH_FUNGUS = DawnFactory.configuredFeature(Promenade.id("amaranth_fungus/planted"));

    public static final Block BLACK_DYLIUM = new DyliumBlock(
            AbstractBlock.Settings.create()
                    .item()
                    .mapColor(MapColor.BLACK)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.0F, 9.0F)
                    .sounds(BlockSoundGroup.NYLIUM)
                    .ticksRandomly());
    public static final Block DARK_AMARANTH_WART_BLOCK = new Block(
            AbstractBlock.Settings.create()
                    .item()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WART_BLOCK));
    public static final Block DARK_AMARANTH_ROOTS = new DawnRootsBlock(state -> state.isIn(PromenadeBlockTags.DARK_AMARANTH_ROOTS_PLACEABLE_ON),
            AbstractBlock.Settings.create()
                    .item(new Item.Settings().compostingChance(0.65F))
                    .mapColor(MapColor.PURPLE)
                    .replaceable()
                    .breakInstantly()
                    .noCollision()
                    .sounds(BlockSoundGroup.ROOTS));

    public static final Block STRIPPED_DARK_AMARANTH_STEM = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, BlockSoundGroup.NETHER_STEM, false));
    public static final Block DARK_AMARANTH_STEM = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, BARK_COLOR, BlockSoundGroup.NETHER_STEM, false).stripsInto(STRIPPED_DARK_AMARANTH_STEM));
    public static final Block STRIPPED_DARK_AMARANTH_HYPHAE = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, BlockSoundGroup.NETHER_STEM, false));
    public static final Block DARK_AMARANTH_HYPHAE = new PillarBlock(DawnFactory.logSettings(BARK_COLOR, BlockSoundGroup.NETHER_STEM, false).stripsInto(STRIPPED_DARK_AMARANTH_HYPHAE));

    public static final Block DARK_AMARANTH_PLANKS = DawnFactory.planks(WOOD_COLOR, WOOD_SOUNDS, false);
    public static final Block DARK_AMARANTH_STAIRS = DawnFactory.stairs(DARK_AMARANTH_PLANKS);
    public static final Block DARK_AMARANTH_SLAB = DawnFactory.slab(DARK_AMARANTH_PLANKS);
    public static final Block DARK_AMARANTH_FENCE = DawnFactory.fence(DARK_AMARANTH_PLANKS);
    public static final Block DARK_AMARANTH_FENCE_GATE = DawnFactory.fenceGate(DARK_AMARANTH_PLANKS, WOOD_TYPE);
    public static final Block DARK_AMARANTH_DOOR = DawnFactory.door(DARK_AMARANTH_PLANKS, BLOCK_SET_TYPE);
    public static final Block DARK_AMARANTH_TRAPDOOR = DawnFactory.trapdoor(DARK_AMARANTH_PLANKS, BLOCK_SET_TYPE);
    public static final Block DARK_AMARANTH_BUTTON = DawnFactory.woodenButton(DARK_AMARANTH_PLANKS, BLOCK_SET_TYPE);
    public static final Block DARK_AMARANTH_PRESSURE_PLATE = DawnFactory.pressurePlate(DARK_AMARANTH_PLANKS, BLOCK_SET_TYPE);

    public static final SignBlocks DARK_AMARANTH_SIGNS = DawnFactory.signs(Promenade.id("dark_amaranth"), DARK_AMARANTH_PLANKS);

    public static final Block DARK_AMARANTH_FUNGUS = DawnFactory.fungus(MapColor.PURPLE, PLANTED_AMARANTH_FUNGUS, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeBlockTags.DARK_AMARANTH_FUNGUS_GROWABLE_ON);
    public static final Block POTTED_DARK_AMARANTH_FUNGUS = DawnFactory.potted(DARK_AMARANTH_FUNGUS);

    public static final RegistryKey<Biome> DARK_AMARANTH_FOREST = DawnFactory.biome(Promenade.id("dark_amaranth_forest"));
    public static final RegistryKey<Biome> TALL_DARK_AMARANTH_FOREST = DawnFactory.biome(Promenade.id("tall_dark_amaranth_forest"));

    public static void register(Registrar r) {
        r.add(("black_dylium"), BLACK_DYLIUM);
        r.add(("dark_amaranth_wart_block"), DARK_AMARANTH_WART_BLOCK);
        r.add(("dark_amaranth_roots"), DARK_AMARANTH_ROOTS);

        r.add(("dark_amaranth_stem"), DARK_AMARANTH_STEM);
        r.add(("stripped_dark_amaranth_stem"), STRIPPED_DARK_AMARANTH_STEM);
        r.add(("dark_amaranth_hyphae"), DARK_AMARANTH_HYPHAE);
        r.add(("stripped_dark_amaranth_hyphae"), STRIPPED_DARK_AMARANTH_HYPHAE);

        r.add(("dark_amaranth_planks"), DARK_AMARANTH_PLANKS);
        r.add(("dark_amaranth_stairs"), DARK_AMARANTH_STAIRS);
        r.add(("dark_amaranth_slab"), DARK_AMARANTH_SLAB);
        r.add(("dark_amaranth_fence"), DARK_AMARANTH_FENCE);
        r.add(("dark_amaranth_fence_gate"), DARK_AMARANTH_FENCE_GATE);
        r.add(("dark_amaranth_door"), DARK_AMARANTH_DOOR);
        r.add(("dark_amaranth_trapdoor"), DARK_AMARANTH_TRAPDOOR);
        r.add(("dark_amaranth_button"), DARK_AMARANTH_BUTTON);
        r.add(("dark_amaranth_pressure_plate"), DARK_AMARANTH_PRESSURE_PLATE);

        r.add(("dark_amaranth"), DARK_AMARANTH_SIGNS);

        r.add(("dark_amaranth_fungus"), DARK_AMARANTH_FUNGUS);
        r.add(("potted_dark_amaranth_fungus"), POTTED_DARK_AMARANTH_FUNGUS);

        appendItemGroups();
        appendWorldGen();
    }

    private static void appendItemGroups() {
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
        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.WARPED_HANGING_SIGN, DARK_AMARANTH_SIGNS.sign(), DARK_AMARANTH_SIGNS.hangingSign()));
    }

    private static void appendWorldGen() {
        if (Promenade.CONFIG.biomes.dark_amaranth_forests_weight <= 0) {
            return;
        }
        double weight = Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 100.0D;
        BiomePlacement.replaceEnd(BiomeKeys.END_HIGHLANDS, TALL_DARK_AMARANTH_FOREST, weight);
        BiomePlacement.replaceEnd(BiomeKeys.END_MIDLANDS, DARK_AMARANTH_FOREST, weight);

        MaterialRules.MaterialCondition isDarkAmaranthForest = MaterialRules.biome(DARK_AMARANTH_FOREST, TALL_DARK_AMARANTH_FOREST);
        SurfaceGeneration.addEndSurfaceRules(Promenade.id("end_surface"), MaterialRules.sequence(
                MaterialRules.condition(isDarkAmaranthForest,
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_FLOOR,
                                VanillaSurfaceRules.block(AmaranthContent.BLACK_DYLIUM)
                        )
                ))
        );
    }
}
