package fr.hugman.promenade.entity.ai.brain.sensor;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.item.PromenadeItemTags;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Supplier;


public class PromenadeSensorTypes {
	public static final SensorType<TemptationsSensor> CAPYBARA_TEMPTATIONS = register("capybara_temptations", () -> new TemptationsSensor(Ingredient.fromTag(PromenadeItemTags.BREEDING_CAPYBARA)));

	private static <U extends Sensor<?>> SensorType<U> register(String path, Supplier<U> factory) {
		return Registry.register(Registries.SENSOR_TYPE, Promenade.id(path), new SensorType<>(factory));
	}
}
