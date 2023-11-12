package fr.hugman.promenade.registry.content;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.block.sapling.SingleSaplingGenerator;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.block.MapleLogBlock;
import fr.hugman.promenade.block.StrippedMapleLogBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class DuskContent {
    public static final BlockSetType BLOCK_SET_TYPE = BlockSetTypeRegistry.registerWood(Promenade.id("dusk_cypress"));
    public static final WoodType WOOD_TYPE = WoodTypeRegistry.register(Promenade.id("dusk_cypress"), BLOCK_SET_TYPE);
    private static final BlockSoundGroup WOOD_SOUNDS = BlockSoundGroup.WOOD;
    //TODO change this
    private static final MapColor BARK_COLOR = MapColor.DEEPSLATE_GRAY;
    private static final MapColor WOOD_COLOR = MapColor.TERRACOTTA_WHITE;
    private static final MapColor LEAVES_COLOR = MapColor.BLUE;

    public static final Block STRIPPED_DUSK_CYPRESS_LOG = new StrippedMapleLogBlock(DawnFactory.logSettings(WOOD_COLOR, WOOD_SOUNDS, true));
    public static final Block DUSK_CYPRESS_LOG = new MapleLogBlock(DawnFactory.logSettings(WOOD_COLOR, BARK_COLOR, WOOD_SOUNDS, true).stripsInto(STRIPPED_DUSK_CYPRESS_LOG));
    public static final Block STRIPPED_DUSK_CYPRESS_WOOD = new PillarBlock(DawnFactory.logSettings(WOOD_COLOR, WOOD_SOUNDS, true));
    public static final Block DUSK_CYPRESS_WOOD = new PillarBlock(DawnFactory.logSettings(BARK_COLOR, WOOD_SOUNDS, true).stripsInto(STRIPPED_DUSK_CYPRESS_WOOD));

    public static final Block DUSK_CYPRESS_PLANKS = DawnFactory.planks(WOOD_COLOR, WOOD_SOUNDS, true);
    public static final Block DUSK_CYPRESS_STAIRS = DawnFactory.stairs(DUSK_CYPRESS_PLANKS);
    public static final Block DUSK_CYPRESS_SLAB = DawnFactory.slab(DUSK_CYPRESS_PLANKS);
    public static final Block DUSK_CYPRESS_FENCE = DawnFactory.fence(DUSK_CYPRESS_PLANKS);
    public static final Block DUSK_CYPRESS_FENCE_GATE = DawnFactory.fenceGate(DUSK_CYPRESS_PLANKS, WOOD_TYPE);
    public static final Block DUSK_CYPRESS_DOOR = DawnFactory.door(DUSK_CYPRESS_PLANKS, BLOCK_SET_TYPE);
    public static final Block DUSK_CYPRESS_TRAPDOOR = DawnFactory.trapdoor(DUSK_CYPRESS_PLANKS, BLOCK_SET_TYPE);
    public static final Block DUSK_CYPRESS_BUTTON = DawnFactory.woodenButton(DUSK_CYPRESS_PLANKS, BLOCK_SET_TYPE);
    public static final Block DUSK_CYPRESS_PRESSURE_PLATE = DawnFactory.pressurePlate(DUSK_CYPRESS_PLANKS, BLOCK_SET_TYPE);

    public static final SignBlocks DUSK_CYPRESS_SIGNS = DawnFactory.signs(Promenade.id("dusk_cypress"), DUSK_CYPRESS_PLANKS);
    public static final TerraformBoatType DUSK_CYPRESS_BOAT_TYPE = DawnFactory.boat(Promenade.id("dusk_cypress"), Items.OAK_PLANKS); //TODO change when possible (PR #72 on TerraformersMC/Terraform)

    public static final Block DUSK_CYPRESS_SAPLING = DawnFactory.sapling(LEAVES_COLOR, new SingleSaplingGenerator(Promenade.id("tree/dusk_cypress")));
    public static final Block POTTED_DUSK_CYPRESS_SAPLING = DawnFactory.potted(DUSK_CYPRESS_SAPLING);
    public static final Block DUSK_CYPRESS_LEAVES = DawnFactory.leaves(LEAVES_COLOR);
    public static final Block DUSK_CYPRESS_LEAF_PILE = PromenadeFactory.leafPile(LEAVES_COLOR);

    public static final RegistryKey<Biome> DUSK_CYPRESS_FOREST = DawnFactory.biome(Promenade.id("dusk_cypress_forest"));

    public static void register(Registrar r) {
        r.add("dusk_cypress_log", DUSK_CYPRESS_LOG);
        r.add("stripped_dusk_cypress_log", STRIPPED_DUSK_CYPRESS_LOG);
        r.add("dusk_cypress_wood", DUSK_CYPRESS_WOOD);
        r.add("stripped_dusk_cypress_wood", STRIPPED_DUSK_CYPRESS_WOOD);

        r.add("dusk_cypress_planks", DUSK_CYPRESS_PLANKS);
        r.add("dusk_cypress_stairs", DUSK_CYPRESS_STAIRS);
        r.add("dusk_cypress_slab", DUSK_CYPRESS_SLAB);
        r.add("dusk_cypress_fence", DUSK_CYPRESS_FENCE);
        r.add("dusk_cypress_fence_gate", DUSK_CYPRESS_FENCE_GATE);
        r.add("dusk_cypress_door", DUSK_CYPRESS_DOOR);
        r.add("dusk_cypress_trapdoor", DUSK_CYPRESS_TRAPDOOR);
        r.add("dusk_cypress_button", DUSK_CYPRESS_BUTTON);
        r.add("dusk_cypress_pressure_plate", DUSK_CYPRESS_PRESSURE_PLATE);

        r.add("dusk_cypress", DUSK_CYPRESS_SIGNS);
        r.add("dusk_cypress", DUSK_CYPRESS_BOAT_TYPE);

        r.add("dusk_cypress_sapling", DUSK_CYPRESS_SAPLING);
        r.add("potted_dusk_cypress_sapling", POTTED_DUSK_CYPRESS_SAPLING);
        r.add("dusk_cypress_leaves", DUSK_CYPRESS_LEAVES);
        r.add("dusk_cypress_leaf_pile", DUSK_CYPRESS_LEAF_PILE);

        appendItemGroups();
        //appendWorldGen();
    }

    private static void appendItemGroups() {
        ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e ->
                e.addAfter(Blocks.SPRUCE_BUTTON,
                        DUSK_CYPRESS_LOG,
                        DUSK_CYPRESS_WOOD,
                        STRIPPED_DUSK_CYPRESS_LOG,
                        STRIPPED_DUSK_CYPRESS_WOOD,
                        DUSK_CYPRESS_PLANKS,
                        DUSK_CYPRESS_STAIRS,
                        DUSK_CYPRESS_SLAB,
                        DUSK_CYPRESS_FENCE,
                        DUSK_CYPRESS_FENCE_GATE,
                        DUSK_CYPRESS_DOOR,
                        DUSK_CYPRESS_TRAPDOOR,
                        DUSK_CYPRESS_PRESSURE_PLATE,
                        DUSK_CYPRESS_BUTTON));

        ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
            e.addAfter(Blocks.SPRUCE_LOG, DUSK_CYPRESS_LOG);
            e.addAfter(Blocks.SPRUCE_LEAVES, DUSK_CYPRESS_LEAVES);
            e.addAfter(Blocks.SPRUCE_SAPLING, DUSK_CYPRESS_SAPLING);
            e.addAfter(VanillaPilesContent.SPRUCE_LEAF_PILE, DUSK_CYPRESS_LEAF_PILE);
        });
        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.SPRUCE_SIGN, DUSK_CYPRESS_SIGNS.sign(), DUSK_CYPRESS_SIGNS.hangingSign()));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.SPRUCE_BOAT, DUSK_CYPRESS_BOAT_TYPE.getItem(), DUSK_CYPRESS_BOAT_TYPE.getChestItem()));
    }

    private static void appendWorldGen() {
        //TODO
        if (Promenade.CONFIG.biomes.carnelian_treeway_weight <= 0) {
            return;
        }
        double weight = Promenade.CONFIG.biomes.carnelian_treeway_weight / 100.0D;
        BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, DUSK_CYPRESS_FOREST, weight);
    }
}
