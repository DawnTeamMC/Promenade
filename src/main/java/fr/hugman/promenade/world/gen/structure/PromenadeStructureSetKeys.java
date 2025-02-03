package fr.hugman.promenade.world.gen.structure;

import fr.hugman.promenade.Promenade;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;

public class PromenadeStructureSetKeys {
    public static final RegistryKey<StructureSet> WITCH_HUTS = of("witch_huts");

    private static RegistryKey<StructureSet> of(String id) {
        return RegistryKey.of(RegistryKeys.STRUCTURE_SET, Promenade.id(id));
    }
}
