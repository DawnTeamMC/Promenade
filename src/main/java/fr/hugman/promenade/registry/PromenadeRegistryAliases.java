package fr.hugman.promenade.registry;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashMap;

/**
 * Registry aliases for Promenade. Prevents players from losing stuff when content is removed along new versions.
 */
public class PromenadeRegistryAliases {
    public static void registerAliases() {
        HashMap<Identifier, Identifier> blockAliases = new HashMap<>();

        // I forgot what version that was in
        Registries.ENTITY_TYPE.addAlias(Promenade.id("sunken_skeleton"), Promenade.id("sunken"));

        // v3.0.0

        blockAliases.put(Promenade.id("autumn_oak_leaves"), Promenade.id("fulvous_maple_leaves"));
        blockAliases.put(Promenade.id("autumn_oak_sapling"), Promenade.id("fulvous_maple_sapling"));
        blockAliases.put(Promenade.id("autumn_oak_leaf_pile"), Promenade.id("fulvous_maple_leaf_pile"));
        blockAliases.put(Promenade.id("potted_autumn_oak_sapling"), Promenade.id("potted_fulvous_maple_sapling"));

        blockAliases.put(Promenade.id("autumn_birch_leaves"), Promenade.id("mikado_maple_leaves"));
        blockAliases.put(Promenade.id("autumn_birch_sapling"), Promenade.id("mikado_maple_sapling"));
        blockAliases.put(Promenade.id("autumn_birch_leaf_pile"), Promenade.id("mikado_maple_leaf_pile"));
        blockAliases.put(Promenade.id("potted_autumn_birch_sapling"), Promenade.id("potted_mikado_maple_sapling"));

        //TODO: add alias for pumpkin pastures

        // v3.1.0
        blockAliases.put(Promenade.id("cherry_oak_log"), Promenade.id("sakura_log"));
        blockAliases.put(Promenade.id("cherry_oak_wood"), Promenade.id("sakura_wood"));
        blockAliases.put(Promenade.id("stripped_cherry_oak_log"), Promenade.id("stripped_sakura_log"));
        blockAliases.put(Promenade.id("stripped_cherry_oak_wood"), Promenade.id("stripped_sakura_wood"));

        blockAliases.put(Promenade.id("cherry_oak_planks"), Promenade.id("sakura_planks"));
        blockAliases.put(Promenade.id("cherry_oak_slab"), Promenade.id("sakura_slab"));
        blockAliases.put(Promenade.id("cherry_oak_stairs"), Promenade.id("sakura_stairs"));
        blockAliases.put(Promenade.id("cherry_oak_fence"), Promenade.id("sakura_fence"));
        blockAliases.put(Promenade.id("cherry_oak_fence_gate"), Promenade.id("sakura_fence_gate"));
        blockAliases.put(Promenade.id("cherry_oak_door"), Promenade.id("sakura_door"));
        blockAliases.put(Promenade.id("cherry_oak_trapdoor"), Promenade.id("sakura_trapdoor"));
        blockAliases.put(Promenade.id("cherry_oak_pressure_plate"), Promenade.id("sakura_pressure_plate"));
        blockAliases.put(Promenade.id("cherry_oak_button"), Promenade.id("sakura_button"));
        blockAliases.put(Promenade.id("cherry_oak_sign"), Promenade.id("sakura_sign"));
        Registries.BLOCK.addAlias(Promenade.id("cherry_oak_wall_sign"), Promenade.id("sakura_wall_sign"));

        blockAliases.put(Promenade.id("pink_cherry_oak_sapling"), Promenade.id("blush_sakura_sapling"));
        blockAliases.put(Promenade.id("potted_pink_cherry_oak_sapling"), Promenade.id("potted_blush_sakura_sapling"));
        blockAliases.put(Promenade.id("pink_cherry_oak_leaves"), Promenade.id("blush_sakura_blossoms"));
        blockAliases.put(Promenade.id("pink_cherry_oak_leaf_pile"), Promenade.id("blush_sakura_blossom_pile"));

        blockAliases.put(Promenade.id("white_cherry_oak_sapling"), Promenade.id("cotton_sakura_sapling"));
        blockAliases.put(Promenade.id("potted_white_cherry_oak_sapling"), Promenade.id("potted_cotton_sakura_sapling"));
        blockAliases.put(Promenade.id("white_cherry_oak_leaves"), Promenade.id("cotton_sakura_blossoms"));
        blockAliases.put(Promenade.id("white_cherry_oak_leaf_pile"), Promenade.id("cotton_sakura_blossom_pile"));

        Registries.ITEM.addAlias(Promenade.id("cherry_oak_boat"), Promenade.id("sakura_boat"));

        // v4.0.0
        blockAliases.put(Promenade.id("carbonite"), Promenade.id("asphalt"));
        blockAliases.put(Promenade.id("carbonite_slab"), Promenade.id("asphalt_slab"));
        blockAliases.put(Promenade.id("carbonite_stairs"), Promenade.id("asphalt_stairs"));
        blockAliases.put(Promenade.id("carbonite_wall"), Promenade.id("asphalt_wall"));

        blockAliases.put(Promenade.id("polished_carbonite"), Promenade.id("polished_asphalt"));
        blockAliases.put(Promenade.id("polished_carbonite_slab"), Promenade.id("polished_asphalt_slab"));
        blockAliases.put(Promenade.id("polished_carbonite_stairs"), Promenade.id("polished_asphalt_stairs"));

        // v5.0.0
        var grassId = Identifier.ofVanilla("grass_block");
        blockAliases.put(Promenade.id("vermilion_carpeted_grass_block"), grassId);
        blockAliases.put(Promenade.id("fulvous_carpeted_grass"), grassId);
        blockAliases.put(Promenade.id("mikado_carpeted_grass"), grassId);

        blockAliases.put(Promenade.id("black_dylium"), Promenade.id("dark_amaranth_nylium"));

        for (var entry : blockAliases.entrySet()) {
            Registries.BLOCK.addAlias(entry.getKey(), entry.getValue());
            Registries.ITEM.addAlias(entry.getKey(), entry.getValue());
        }
    }
}
