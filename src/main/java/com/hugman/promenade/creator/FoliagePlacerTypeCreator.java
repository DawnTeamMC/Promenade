package com.hugman.promenade.creator;

import com.hugman.dawn.api.creator.SimpleCreator;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

/**
 * A class allowing a foliage placer type to be created.
 *
 * @param <P> the foliage placer class, inheriting {@link FoliagePlacer}
 */
public class FoliagePlacerTypeCreator<P extends FoliagePlacer> extends SimpleCreator<FoliagePlacerType<P>> {

	/**
	 * Creates a foliage placer type.
	 *
	 * @param name  the name of the foliage placer type
	 * @param codec the trunk placer's CODEC
	 */
	public FoliagePlacerTypeCreator(String name, Codec<P> codec) {
		super(Registry.FOLIAGE_PLACER_TYPE, name, new FoliagePlacerType<>(codec));
	}
}
