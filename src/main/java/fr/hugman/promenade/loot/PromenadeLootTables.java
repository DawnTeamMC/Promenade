package fr.hugman.promenade.loot;

import fr.hugman.promenade.Promenade;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeLootTables {
    public static final RegistryKey<LootTable> BUBBLE_SUNKEN = of("entities/sunken/bubble");
    public static final RegistryKey<LootTable> FIRE_SUNKEN = of("entities/sunken/fire");
    public static final RegistryKey<LootTable> HORN_SUNKEN = of("entities/sunken/horn");
    public static final RegistryKey<LootTable> WITCH_HUT_CHEST = of("chests/witch_hut");


    private static RegistryKey<LootTable> of(String path) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Promenade.id(path));
    }
}
