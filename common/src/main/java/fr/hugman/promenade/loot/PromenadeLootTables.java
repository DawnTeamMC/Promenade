package fr.hugman.promenade.loot;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class PromenadeLootTables {
    public static final ResourceKey<LootTable> TUBE_SUNKEN = of("entities/sunken/tube");
    public static final ResourceKey<LootTable> BRAIN_SUNKEN = of("entities/sunken/brain");
    public static final ResourceKey<LootTable> BUBBLE_SUNKEN = of("entities/sunken/bubble");
    public static final ResourceKey<LootTable> FIRE_SUNKEN = of("entities/sunken/fire");
    public static final ResourceKey<LootTable> HORN_SUNKEN = of("entities/sunken/horn");

    public static final ResourceKey<LootTable> WITCH_HUT_CHEST = of("chests/witch_hut");


    private static ResourceKey<LootTable> of(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, Promenade.id(path));
    }
}
