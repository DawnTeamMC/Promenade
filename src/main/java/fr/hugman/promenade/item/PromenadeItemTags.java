package fr.hugman.promenade.item;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.promenade.Promenade;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class PromenadeItemTags {
	public static final TagKey<Item> CAPYBARA_FOOD = DawnFactory.itemTag(Promenade.id("entity_food/capybara"));
	public static final TagKey<Item> DUCK_FOOD = DawnFactory.itemTag(Promenade.id("entity_food/duck"));
}
