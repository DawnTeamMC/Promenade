package fr.hugman.promenade.references;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class PromenadeBlockIds {
    public static final ResourceKey<Block> SAKURA_WALL_SIGN = createKey("sakura_wall_sign");
    public static final ResourceKey<Block> SAKURA_WALL_HANGING_SIGN = createKey("sakura_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_BLUSH_SAKURA_SAPLING = createKey("potted_blush_sakura_sapling");
    public static final ResourceKey<Block> POTTED_COTTON_SAKURA_SAPLING = createKey("potted_cotton_sakura_sapling");

    public static final ResourceKey<Block> MAPLE_WALL_SIGN = createKey("maple_wall_sign");
    public static final ResourceKey<Block> MAPLE_WALL_HANGING_SIGN = createKey("maple_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_SAP_MAPLE_SAPLING = createKey("potted_sap_maple_sapling");
    public static final ResourceKey<Block> POTTED_VERMILION_MAPLE_SAPLING = createKey("potted_vermilion_maple_sapling");
    public static final ResourceKey<Block> POTTED_FULVOUS_MAPLE_SAPLING = createKey("potted_fulvous_maple_sapling");
    public static final ResourceKey<Block> POTTED_MIKADO_MAPLE_SAPLING = createKey("potted_mikado_maple_sapling");

    public static final ResourceKey<Block> PALM_WALL_SIGN = createKey("palm_wall_sign");
    public static final ResourceKey<Block> PALM_WALL_HANGING_SIGN = createKey("palm_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_PALM_SAPLING = createKey("potted_palm_sapling");

    public static final ResourceKey<Block> POTTED_DARK_AMARANTH_ROOTS = createKey("potted_dark_amaranth_roots");
    public static final ResourceKey<Block> POTTED_DARK_AMARANTH_FUNGUS = createKey("potted_dark_amaranth_fungus");
    public static final ResourceKey<Block> DARK_AMARANTH_WALL_SIGN = createKey("dark_amaranth_wall_sign");
    public static final ResourceKey<Block> DARK_AMARANTH_WALL_HANGING_SIGN = createKey("dark_amaranth_wall_hanging_sign");
    public static final ResourceKey<Block> COILED_VINES_PLANT = createKey("coiled_vines_plant");
    public static final ResourceKey<Block> BLUEBERRY_BUSH = createKey("blueberry_bush");

    private static ResourceKey<Block> createKey(String path) {
        return ResourceKey.create(Registries.BLOCK, Promenade.id(path));
    }
}
