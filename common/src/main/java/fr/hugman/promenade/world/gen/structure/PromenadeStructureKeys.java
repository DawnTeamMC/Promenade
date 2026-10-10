package fr.hugman.promenade.world.gen.structure;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class PromenadeStructureKeys {
    public static final ResourceKey<Structure> DARK_FOREST_WITCH_HUT = of("dark_forest_witch_hut");

    private static ResourceKey<Structure> of(String id) {
        return ResourceKey.create(Registries.STRUCTURE, Promenade.id(id));
    }
}
