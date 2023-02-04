package fr.hugman.promenade.entity.ai.brain.sensor;

import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.tag.PromenadeItemTags;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;


public class PromenadeSensorTypes {
	public static final SensorType<TemptationsSensor> CAPYBARA_TEMPTATIONS = new SensorType<>(() -> new TemptationsSensor(Ingredient.fromTag(PromenadeItemTags.BREEDING_CAPYBARA)));

	public static void register(Registrar r) {
		// TODO: add sensor types to registrar in Dawn API
		Registry.register(Registries.SENSOR_TYPE, r.id("capybara_temptations"), CAPYBARA_TEMPTATIONS);
	}
}
