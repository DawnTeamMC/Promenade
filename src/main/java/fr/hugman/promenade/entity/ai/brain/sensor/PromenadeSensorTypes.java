package fr.hugman.promenade.entity.ai.brain.sensor;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.CapybaraBrain;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class PromenadeSensorTypes {
	public static final SensorType<TemptationsSensor> CAPYBARA_TEMPTATIONS = register(Promenade.id("capybara_temptations"), () -> new TemptationsSensor(CapybaraBrain.getBreedingIngredient())); //TODO: tag-driven

	private static <U extends Sensor<?>> SensorType<U> register(Identifier id, Supplier<U> factory) {
		return Registry.register(Registries.SENSOR_TYPE, id, new SensorType<U>(factory));
	}

	public static void init() {
	}
}
