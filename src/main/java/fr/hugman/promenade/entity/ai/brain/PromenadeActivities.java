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
package fr.hugman.promenade.entity.ai.brain;

import fr.hugman.promenade.Promenade;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PromenadeActivities {
	public static final Activity FART = register("fart");

	private static Activity register(String id) {
		var key = Promenade.id(id);
		return Registry.register(Registries.ACTIVITY, key, new Activity(key.toString()));
	}
}
