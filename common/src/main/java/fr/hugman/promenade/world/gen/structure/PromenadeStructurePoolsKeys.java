package fr.hugman.promenade.world.gen.structure;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class PromenadeStructurePoolsKeys {
    public static final ResourceKey<StructureTemplatePool> DARK_FOREST_WITCH_HUTS = of("dark_forest_witch_huts");
    public static final ResourceKey<StructureTemplatePool> WITCH_HUT_INTERIORS = of("witch_huts_interiors");

    private static ResourceKey<StructureTemplatePool> of(String id) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, Promenade.id(id));
    }
}
