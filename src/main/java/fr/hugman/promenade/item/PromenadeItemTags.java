package fr.hugman.promenade.item;

import fr.hugman.promenade.Promenade;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class PromenadeItemTags {
    public static final TagKey<Item> CAPYBARA_FOOD = of("entity_food/capybara");
    public static final TagKey<Item> DUCK_FOOD = of("entity_food/duck");

    private static TagKey<Item> of(String path) {
        return TagKey.of(RegistryKeys.ITEM, Promenade.id(path));
    }
}
