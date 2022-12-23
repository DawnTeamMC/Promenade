package fr.hugman.promenade.registry.tag;

import fr.hugman.promenade.PromenadeFactory;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class PromenadeItemTags {
	public static final TagKey<Item> BREEDING_CAPYBARA = PromenadeFactory.itemTag("breeding/capybara");
	public static final TagKey<Item> BREEDING_DUCK = PromenadeFactory.itemTag("breeding/duck");
}
