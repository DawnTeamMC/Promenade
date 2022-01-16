package com.hugman.promenade.creator;

import com.hugman.dawn.api.creator.SimpleCreator;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

/**
 * A class allowing a trunk placer type to be created.
 *
 * @param <P> the trunk placer class, inheriting {@link TrunkPlacer}
 */
public class TrunkPlacerTypeCreator<P extends TrunkPlacer> extends SimpleCreator<TrunkPlacerType<P>> {

	/**
	 * Creates a trunk placer type.
	 *
	 * @param name  the name of the trunk placer type
	 * @param codec the trunk placer's CODEC
	 */
	public TrunkPlacerTypeCreator(String name, Codec<P> codec) {
		super(Registry.TRUNK_PLACER_TYPE, name, new TrunkPlacerType<>(codec));
	}
}
