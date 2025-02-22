package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class PromenadeItemTags {
    public static final TagKey<Item> SAKURA_LOGS = of("sakura_logs");
    public static final TagKey<Item> MAPLE_LOGS = of("maple_logs");
    public static final TagKey<Item> PALM_LOGS = of("palm_logs");
    public static final TagKey<Item> AURORAL_CYPRESS_LOGS = of("auroral_cypress_logs");
    public static final TagKey<Item> DARK_AMARANTH_STEMS = of("dark_amaranth_stems");

    public static final TagKey<Item> CAPYBARA_FOOD = of("capybara_food");
    public static final TagKey<Item> DUCK_FOOD = of("duck_food");

    private static TagKey<Item> of(String path) {
        return TagKey.of(RegistryKeys.ITEM, Promenade.id(path));
    }
}
