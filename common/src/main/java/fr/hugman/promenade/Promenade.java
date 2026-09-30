package fr.hugman.promenade;

import com.google.common.reflect.Reflection;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.block.dispenser.PromenadeDispenserBehaviors;
import fr.hugman.promenade.block.entity.PromenadeBlockEntities;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.spawn.PromenadeSpawnConditions;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.itemgroup.PromenadeItemGroupAdditions;
import fr.hugman.promenade.itemgroup.PromenadeItemGroups;
import fr.hugman.promenade.platform.PromenadePlatform;
import fr.hugman.promenade.registry.*;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.world.PromenadeGameRules;
import fr.hugman.promenade.world.gen.feature.PromenadeFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatures;
import fr.hugman.promenade.world.gen.placement_modifier.PromenadePlacementModifierTypes;
import fr.hugman.promenade.world.gen.tree.foliage.PromenadeFoliagePlacerTypes;
import fr.hugman.promenade.world.gen.tree.trunk.PromenadeTrunkPlacerTypes;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Promenade {
    public static final String MOD_ID = "promenade";
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Registers all the mod's content. Called once by each loader, while vanilla registries are open.
     */
    public static void init() {
        PromenadeConfig.load(PromenadePlatform.INSTANCE.configDir());

        Reflection.initialize(PromenadeSoundEvents.class);

        Reflection.initialize(PromenadeBlocks.class);

        PromenadeFlammables.register();
        PromenadeBlockEntities.addBlocksToVanillaBlockEntityTypes();

        Reflection.initialize(PromenadeItems.class);

        Reflection.initialize(PromenadeItemGroups.class);
        PromenadeItemGroupAdditions.appendItemGroups();
        PromenadeDispenserBehaviors.register();

        Reflection.initialize(PromenadeSensorTypes.class);
        Reflection.initialize(PromenadeMemoryModuleTypes.class);
        Reflection.initialize(PromenadeTrackedData.class);
        Reflection.initialize(PromenadeEntityTypes.class);

        Reflection.initialize(PromenadeTrunkPlacerTypes.class);
        Reflection.initialize(PromenadeFoliagePlacerTypes.class);
        Reflection.initialize(PromenadeFeatures.class);
        Reflection.initialize(PromenadePlacementModifierTypes.class);

        PromenadeSpawnConditions.register();
        PromenadeEntityTypes.registerAttributes();
        PromenadeEntityTypes.registerSpawnPlacements();

        PromenadeEntityTypes.appendWorldGen();
        PromenadePlacedFeatures.appendWorldGen();

        PromenadeRegistryAliases.registerAliases();

        Reflection.initialize(PromenadeGameRules.class);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}