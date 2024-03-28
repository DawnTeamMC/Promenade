package fr.hugman.promenade.content;

import fr.hugman.promenade.Promenade;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeBlockKeys {
    public static final RegistryKey<Block> ASPHALT = of("asphalt");
    public static final RegistryKey<Block> ASPHALT_SLAB = of("asphalt_slab");
    public static final RegistryKey<Block> ASPHALT_STAIRS = of("asphalt_stairs");
    public static final RegistryKey<Block> ASPHALT_WALL = of("asphalt_wall");

    public static final RegistryKey<Block> POLISHED_ASPHALT = of("polished_asphalt");
    public static final RegistryKey<Block> POLISHED_ASPHALT_SLAB = of("polished_asphalt_slab");
    public static final RegistryKey<Block> POLISHED_ASPHALT_STAIRS = of("polished_asphalt_stairs");

    public static final RegistryKey<Block> BLUNITE = of("blunite");
    public static final RegistryKey<Block> BLUNITE_SLAB = of("blunite_slab");
    public static final RegistryKey<Block> BLUNITE_STAIRS = of("blunite_stairs");
    public static final RegistryKey<Block> BLUNITE_WALL = of("blunite_wall");

    public static final RegistryKey<Block> POLISHED_BLUNITE = of("polished_blunite");
    public static final RegistryKey<Block> POLISHED_BLUNITE_SLAB = of("polished_blunite_slab");
    public static final RegistryKey<Block> POLISHED_BLUNITE_STAIRS = of("polished_blunite_stairs");

    /* ========= */
    /*   MAPLE   */
    /* ========= */
    public static final RegistryKey<Block> STRIPPED_MAPLE_LOG = of("stripped_maple_log");
    public static final RegistryKey<Block> MAPLE_LOG = of("maple_log");
    public static final RegistryKey<Block> STRIPPED_MAPLE_WOOD = of("stripped_maple_wood");
    public static final RegistryKey<Block> MAPLE_WOOD = of("maple_wood");
    public static final RegistryKey<Block> MAPLE_PLANKS = of("maple_planks");
    public static final RegistryKey<Block> MAPLE_STAIRS = of("maple_stairs");
    public static final RegistryKey<Block> MAPLE_SLAB = of("maple_slab");
    public static final RegistryKey<Block> MAPLE_FENCE = of("maple_fence");
    public static final RegistryKey<Block> MAPLE_FENCE_GATE = of("maple_fence_gate");
    public static final RegistryKey<Block> MAPLE_DOOR = of("maple_door");
    public static final RegistryKey<Block> MAPLE_TRAPDOOR = of("maple_trapdoor");
    public static final RegistryKey<Block> MAPLE_BUTTON = of("maple_button");
    public static final RegistryKey<Block> MAPLE_PRESSURE_PLATE = of("maple_pressure_plate");

    public static final RegistryKey<Block> SAP_MAPLE_SAPLING = of("sap_maple_sapling");
    public static final RegistryKey<Block> POTTED_SAP_MAPLE_SAPLING = of("potted_sap_maple_sapling");
    public static final RegistryKey<Block> SAP_MAPLE_LEAVES = of("sap_maple_leaves");
    public static final RegistryKey<Block> SAP_MAPLE_LEAF_PILE = of("sap_maple_leaf_pile");

    public static final RegistryKey<Block> VERMILION_MAPLE_SAPLING = of("vermilion_maple_sapling");
    public static final RegistryKey<Block> POTTED_VERMILION_MAPLE_SAPLING = of("potted_vermilion_maple_sapling");
    public static final RegistryKey<Block> VERMILION_MAPLE_LEAVES = of("vermilion_maple_leaves");
    public static final RegistryKey<Block> VERMILION_MAPLE_LEAF_PILE = of("vermilion_maple_leaf_pile");
    public static final RegistryKey<Block> VERMILION_CARPETED_GRASS_BLOCK = of("vermilion_carpeted_grass_block");

    public static final RegistryKey<Block> FULVOUS_MAPLE_SAPLING = of("fulvous_maple_sapling");
    public static final RegistryKey<Block> POTTED_FULVOUS_MAPLE_SAPLING = of("potted_fulvous_maple_sapling");
    public static final RegistryKey<Block> FULVOUS_MAPLE_LEAVES = of("fulvous_maple_leaves");
    public static final RegistryKey<Block> FULVOUS_MAPLE_LEAF_PILE = of("fulvous_maple_leaf_pile");
    public static final RegistryKey<Block> FULVOUS_CARPETED_GRASS_BLOCK = of("fulvous_carpeted_grass_block");

    public static final RegistryKey<Block> MIKADO_MAPLE_SAPLING = of("mikado_maple_sapling");
    public static final RegistryKey<Block> POTTED_MIKADO_MAPLE_SAPLING = of("potted_mikado_maple_sapling");
    public static final RegistryKey<Block> MIKADO_MAPLE_LEAVES = of("mikado_maple_leaves");
    public static final RegistryKey<Block> MIKADO_MAPLE_LEAF_PILE = of("mikado_maple_leaf_pile");
    public static final RegistryKey<Block> MIKADO_CARPETED_GRASS_BLOCK = of("mikado_carpeted_grass_block");

    private static RegistryKey<Block> of(String path) {
        return RegistryKey.of(RegistryKeys.BLOCK, Promenade.id(path));
    }
}
