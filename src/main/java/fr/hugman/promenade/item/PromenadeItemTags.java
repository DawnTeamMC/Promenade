package fr.hugman.promenade.item;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.promenade.Promenade;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class PromenadeItemTags {
	public static final TagKey<Item> BREEDING_CAPYBARA = DawnFactory.itemTag(Promenade.id("breeding/capybara"));
	public static final TagKey<Item> BREEDING_DUCK = DawnFactory.itemTag(Promenade.id("breeding/duck"));
}
