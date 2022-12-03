package fr.hugman.promenade;

import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.registry.PromenadeRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PromenadeRegistrar {
	public static CapybaraVariant add(Identifier id, CapybaraVariant variant) {
		return Registry.register(PromenadeRegistries.CAPYBARA_VARIANT, id, variant);
	}
}
