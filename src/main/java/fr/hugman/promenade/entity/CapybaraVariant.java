package fr.hugman.promenade.entity;

import fr.hugman.promenade.registry.PromenadeRegistries;
import net.minecraft.util.Identifier;

public record CapybaraVariant(int spawnWeight) {
	public Identifier texture(boolean isBaby) {
		Identifier variantId = PromenadeRegistries.CAPYBARA_VARIANT.getId(this);
		String nameSpace = variantId.getNamespace();
		String path = variantId.getPath();
		String type = isBaby ? "baby" : "adult";
		return Identifier.of(nameSpace, "textures/entity/capybara/" + path + "/" + type + ".png");
	}
}
