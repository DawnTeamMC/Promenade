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
package fr.hugman.promenade.entity.ai.brain.sensor;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.CapybaraBrain;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Supplier;


public class PromenadeSensorTypes {
	public static final SensorType<TemptationsSensor> CAPYBARA_TEMPTATIONS = register("capybara_temptations", () -> new TemptationsSensor(CapybaraBrain.getTemptItemPredicate()));

	private static <U extends Sensor<?>> SensorType<U> register(String path, Supplier<U> factory) {
		return Registry.register(Registries.SENSOR_TYPE, Promenade.id(path), new SensorType<>(factory));
	}
}
