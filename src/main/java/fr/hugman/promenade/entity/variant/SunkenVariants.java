/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
package fr.hugman.promenade.entity.variant;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class SunkenVariants {
	public static final RegistryKey<SunkenVariant> TUBE = of("tube");
	public static final RegistryKey<SunkenVariant> BRAIN = of("brain");
	public static final RegistryKey<SunkenVariant> BUBBLE = of("bubble");
	public static final RegistryKey<SunkenVariant> FIRE = of("fire");
	public static final RegistryKey<SunkenVariant> HORN = of("horn");

	public static final RegistryKey<SunkenVariant> DEFAULT = TUBE;

	private static RegistryKey<SunkenVariant> of(String path) {
		return of(Promenade.id(path));
	}

	public static RegistryKey<SunkenVariant> of(Identifier id) {
		return RegistryKey.of(PromenadeRegistryKeys.SUNKEN_VARIANT, id);
	}

	public static Optional<RegistryEntry.Reference<SunkenVariant>> select(Random random, DynamicRegistryManager registries, SpawnContext context) {
		return VariantSelectorProvider.select(registries.getOrThrow(PromenadeRegistryKeys.SUNKEN_VARIANT).streamEntries(), RegistryEntry::value, random, context);
	}
}
