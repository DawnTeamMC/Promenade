package fr.hugman.promenade.entity.ai.brain.sensor;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.CapybaraBrain;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;


public class PromenadeSensorTypes {
    public static final SensorType<TemptingSensor> CAPYBARA_TEMPTATIONS = register("capybara_temptations", () -> new TemptingSensor(CapybaraBrain.getTemptItemPredicate()));

    private static <U extends Sensor<?>> SensorType<U> register(String path, Supplier<U> factory) {
        return Registry.register(BuiltInRegistries.SENSOR_TYPE, Promenade.id(path), new SensorType<>(factory));
    }
}
