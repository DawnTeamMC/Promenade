package fr.hugman.promenade.entity;

import fr.hugman.promenade.registry.PromenadeRegistries;
import net.minecraft.util.Identifier;

public record CapybaraVariant(int spawnWeight) {
	public Identifier texture(boolean largeEyes) {
		Identifier variantId = PromenadeRegistries.CAPYBARA_VARIANT.getId(this);
		return Identifier.of(variantId.getNamespace(), "textures/entity/capybara/" + variantId.getPath() + "/" + (largeEyes ? "large_eyes" : "regular") + ".png");
	}
}
