package fr.hugman.promenade.world.gen.structure;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class PromenadeStructureSetKeys {
    public static final ResourceKey<StructureSet> WITCH_HUTS = of("witch_huts");

    private static ResourceKey<StructureSet> of(String id) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Promenade.id(id));
    }
}
