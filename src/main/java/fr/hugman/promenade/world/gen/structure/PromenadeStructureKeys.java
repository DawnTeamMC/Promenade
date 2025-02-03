package fr.hugman.promenade.world.gen.structure;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.structure.Structure;

public class PromenadeStructureKeys {
    public static final RegistryKey<Structure> DARK_FOREST_WITCH_HUT = of("dark_forest_witch_hut");

    private static RegistryKey<Structure> of(String id) {
        return RegistryKey.of(RegistryKeys.STRUCTURE, Promenade.id(id));
    }
}
