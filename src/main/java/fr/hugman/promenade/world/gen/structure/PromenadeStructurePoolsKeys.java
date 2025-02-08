package fr.hugman.promenade.world.gen.structure;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.pool.StructurePool;

public class PromenadeStructurePoolsKeys {
    public static final RegistryKey<StructurePool> DARK_FOREST_WITCH_HUTS = of("dark_forest_witch_huts");
    public static final RegistryKey<StructurePool> WITCH_HUT_INTERIORS = of("witch_huts_interiors");

    private static RegistryKey<StructurePool> of(String id) {
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, Promenade.id(id));
    }
}
