package fr.hugman.promenade.tag;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class PromenadeItemTags {
    public static final TagKey<Item> SAKURA_LOGS = of("sakura_logs");
    public static final TagKey<Item> MAPLE_LOGS = of("maple_logs");
    public static final TagKey<Item> PALM_LOGS = of("palm_logs");
    public static final TagKey<Item> DARK_AMARANTH_STEMS = of("dark_amaranth_stems");

    public static final TagKey<Item> SNOWY_LEAVES = of("snowy_leaves");

    public static final TagKey<Item> CAPYBARA_FOOD = of("animal_food/capybara");
    public static final TagKey<Item> DUCK_FOOD = of("animal_food/duck");

    public static final TagKey<Item> APRICOTS_FOODS = ofConventional("food/apricot");
    public static final TagKey<Item> BANANA_FOODS = ofConventional("food/banana");
	public static final TagKey<Item> MANGOES_FOODS = ofConventional("food/mango");
	public static final TagKey<Item> BLUEBERRIES_FOODS = ofConventional("food/blueberry");

	public static final TagKey<Item> MAPLE_SYRUP_DRINKS = ofConventional("drinks/maple_syrup");

	public static final TagKey<Item> IGNEOUS_ROCKS = ofConventional("igneous_rocks");

    private static TagKey<Item> of(String path) {
        return TagKey.of(RegistryKeys.ITEM, Promenade.id(path));
    }

    private static TagKey<Item> ofConventional(String path) {
		return TagKey.of(RegistryKeys.ITEM, Identifier.of(TagUtil.C_TAG_NAMESPACE, path));
    }
}
