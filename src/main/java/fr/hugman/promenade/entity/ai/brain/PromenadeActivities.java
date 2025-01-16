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
