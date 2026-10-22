package fr.hugman.promenade.registry;

import net.minecraft.world.level.ItemLike;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The chance each of the mod's items has to add a layer to a composter.
 * Fabric registers them through its API, while Forge, which has no data maps in 1.20.1, adds them to vanilla's map.
 */
public final class PromenadeCompostables {
    // Same chances as vanilla's
    public static final float LOW = 0.3F;
    public static final float LOW_MEDIUM = 0.5F;
    public static final float MEDIUM = 0.65F;
    public static final float MEDIUM_HIGH = 0.85F;

    private static final Map<ItemLike, Float> CHANCES = new LinkedHashMap<>();

    public static void add(ItemLike item, float chance) {
        CHANCES.put(item, chance);
    }

    public static Map<ItemLike, Float> all() {
        return Collections.unmodifiableMap(CHANCES);
    }
}
