package fr.hugman.promenade.references;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PromenadeEntityTypeIds {
    public static final ResourceKey<EntityType<?>> SAKURA_BOAT = createKey("sakura_boat");
    public static final ResourceKey<EntityType<?>> SAKURA_CHEST_BOAT = createKey("sakura_chest_boat");

    public static final ResourceKey<EntityType<?>> PALM_BOAT = createKey("palm_boat");
    public static final ResourceKey<EntityType<?>> PALM_CHEST_BOAT = createKey("palm_chest_boat");

    public static final ResourceKey<EntityType<?>> MAPLE_BOAT = createKey("maple_boat");
    public static final ResourceKey<EntityType<?>> MAPLE_CHEST_BOAT = createKey("maple_chest_boat");

    public static final ResourceKey<EntityType<?>> CAPYBARA = createKey("capybara");
    public static final ResourceKey<EntityType<?>> DUCK = createKey("duck");
    public static final ResourceKey<EntityType<?>> LUSH_CREEPER = createKey("lush_creeper");
    public static final ResourceKey<EntityType<?>> SUNKEN = createKey("sunken");

    private static ResourceKey<EntityType<?>> createKey(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Promenade.id(path));
    }
}
