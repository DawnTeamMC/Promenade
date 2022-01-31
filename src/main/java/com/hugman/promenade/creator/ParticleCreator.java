package com.hugman.promenade.creator;

import com.hugman.dawn.api.creator.SimpleCreator;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a particle type to be created.
 *
 * @param <E> the particle effect class, inheriting {@link ParticleEffect}
 */
public class ParticleCreator<E extends ParticleEffect, T extends ParticleType<E>> extends SimpleCreator<T> {

	/**
	 * Creates a particle type.
	 *
	 * @param name         the name of the particle type
	 * @param particleType the particle type itself
	 */
	public ParticleCreator(String name, T particleType) {
		super(Registry.PARTICLE_TYPE, name, particleType);
	}
}
