package fr.hugman.promenade;

import com.google.common.reflect.Reflection;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.boat.PromenadeBoatTypes;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.content.*;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import fr.hugman.promenade.world.gen.feature.PromenadeFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatureKeys;
import fr.hugman.promenade.world.gen.placement_modifier.PromenadePlacementModifierTypes;
import fr.hugman.promenade.world.gen.tree.foliage.PromenadeFoliagePlacerTypes;
import fr.hugman.promenade.world.gen.tree.trunk.PromenadeTrunkPlacerTypes;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Promenade implements ModInitializer {
    public static final Registrar REGISTRAR = new Registrar("promenade");
    public static final Logger LOGGER = LogManager.getLogger();
    public static final PromenadeConfig CONFIG = AutoConfig.register(PromenadeConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

    @Override
    public void onInitialize() {
        PromenadeRegistries.register();

        PromenadeBlocks.appendItemGroups();
        PromenadeBlocks.appendVillagerTrades();

        PromenadeItems.appendItemGroups();

        Reflection.initialize(PromenadeFeatures.class);
        Reflection.initialize(PromenadePlacementModifierTypes.class);
        Reflection.initialize(PromenadeFoliagePlacerTypes.class);
        Reflection.initialize(PromenadeTrunkPlacerTypes.class);
        Reflection.initialize(PromenadeSensorTypes.class);
        Reflection.initialize(PromenadeBoatTypes.class);
        Reflection.initialize(PromenadeSoundEvents.class);

        PromenadeBiomes.appendWorldGen();
        PromenadePlacedFeatureKeys.appendWorldGen();

        PromenadeTrackedData.init();

        AnimalContent.register(REGISTRAR);
        MonsterContent.register(REGISTRAR);

        VanillaPilesContent.register(REGISTRAR);
        FoodContent.register(REGISTRAR);

        GlaglaglaContent.register(REGISTRAR);
        AmaranthContent.register(REGISTRAR);
    }

    public static Identifier id(String path) {
        return REGISTRAR.id(path);
    }
}