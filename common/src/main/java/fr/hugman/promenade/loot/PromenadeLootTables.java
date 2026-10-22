package fr.hugman.promenade.loot;

import fr.hugman.promenade.Promenade;
import net.minecraft.resources.ResourceLocation;

public class PromenadeLootTables {
    public static final ResourceLocation TUBE_SUNKEN = of("entities/sunken/tube");
    public static final ResourceLocation BRAIN_SUNKEN = of("entities/sunken/brain");
    public static final ResourceLocation BUBBLE_SUNKEN = of("entities/sunken/bubble");
    public static final ResourceLocation FIRE_SUNKEN = of("entities/sunken/fire");
    public static final ResourceLocation HORN_SUNKEN = of("entities/sunken/horn");

    public static final ResourceLocation WITCH_HUT_CHEST = of("chests/witch_hut");


    private static ResourceLocation of(String path) {
        return Promenade.id(path);
    }
}
