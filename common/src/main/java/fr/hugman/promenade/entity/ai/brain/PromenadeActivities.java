package fr.hugman.promenade.entity.ai.brain;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.schedule.Activity;

public class PromenadeActivities {
    public static final Activity FART = register("fart");

    private static Activity register(String id) {
        var key = Promenade.id(id);
        return Registry.register(BuiltInRegistries.ACTIVITY, key, new Activity(key.toString()));
    }
}
