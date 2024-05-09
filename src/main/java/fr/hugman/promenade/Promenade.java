package fr.hugman.promenade;

import com.google.common.reflect.Reflection;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.boat.PromenadeBoatTypes;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.decoration.painting.PromenadePaintingVariants;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import fr.hugman.promenade.world.gen.feature.PromenadeFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatures;
import fr.hugman.promenade.world.gen.placement_modifier.PromenadePlacementModifierTypes;
import fr.hugman.promenade.world.gen.surface.PromenadeSurfaceBuilders;
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
    public static final String MOD_ID = "promenade";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final PromenadeConfig CONFIG = AutoConfig.register(PromenadeConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

    @Override
    public void onInitialize() {
        PromenadeRegistries.register();

        PromenadeBlocks.appendItemGroups();
        PromenadeItems.appendItemGroups();

        PromenadeBlocks.appendVillagerTrades();

        Reflection.initialize(PromenadeFeatures.class);
        Reflection.initialize(PromenadePlacementModifierTypes.class);
        Reflection.initialize(PromenadeFoliagePlacerTypes.class);
        Reflection.initialize(PromenadeTrunkPlacerTypes.class);
        Reflection.initialize(PromenadeSensorTypes.class);
        Reflection.initialize(PromenadeBoatTypes.class);
        Reflection.initialize(PromenadeSoundEvents.class);
        Reflection.initialize(PromenadeTrackedData.class);

        PromenadeBiomes.appendWorldGen();
        PromenadePlacedFeatures.appendWorldGen();
        PromenadeEntityTypes.appendWorldGen();
        PromenadeSurfaceBuilders.init();

        PromenadePaintingVariants.register();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}