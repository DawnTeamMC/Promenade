package fr.hugman.promenade.entity;

import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeRegistrar;
import fr.hugman.promenade.registry.PromenadeRegistries;
import net.minecraft.util.math.random.Random;

public class CapybaraVariants {
	public static final CapybaraVariant BROWN = new CapybaraVariant(49);
	public static final CapybaraVariant ALBINO = new CapybaraVariant(1);

	public static void register(Registrar r) {
		PromenadeRegistrar.add(r.id("brown"), BROWN);
		PromenadeRegistrar.add(r.id("albino"), ALBINO);
	}

	public static CapybaraVariant getRandom(Random random) {
		// get the total weight of all variants
		int totalWeight = 0;
		for(CapybaraVariant variant : PromenadeRegistries.CAPYBARA_VARIANT) {
			totalWeight += variant.spawnWeight();
		}
		// choose a random variant
		int randomWeight = random.nextInt(totalWeight);
		for(CapybaraVariant variant : PromenadeRegistries.CAPYBARA_VARIANT) {
			randomWeight -= variant.spawnWeight();
			if(randomWeight <= 0) {
				return variant;
			}
		}
		// should never happen
		return PromenadeRegistries.CAPYBARA_VARIANT.get(0);
	}

	public static CapybaraVariant getDefault() {
		return BROWN;
	}
}
