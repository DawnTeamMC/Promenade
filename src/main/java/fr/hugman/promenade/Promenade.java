package fr.hugman.promenade;

import com.google.common.reflect.Reflection;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.block.dispenser.PromenadeDispenserBehaviors;
import fr.hugman.promenade.block.entity.PromenadeBlockEntities;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.itemgroup.PromenadeItemGroupAdditions;
import fr.hugman.promenade.itemgroup.PromenadeItemGroups;
import fr.hugman.promenade.registry.*;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.trade.PromenadeTrades;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import fr.hugman.promenade.world.gen.feature.PromenadeFeatures;
import fr.hugman.promenade.world.gen.feature.PromenadePlacedFeatures;
import fr.hugman.promenade.world.gen.placement_modifier.PromenadePlacementModifierTypes;
import fr.hugman.promenade.world.gen.tree.foliage.PromenadeFoliagePlacerTypes;
import fr.hugman.promenade.world.gen.tree.trunk.PromenadeTrunkPlacerTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Promenade implements ModInitializer {
    public static final String MOD_ID = "promenade";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        PromenadeRegistries.register();

        Reflection.initialize(PromenadeSoundEvents.class);

        Reflection.initialize(PromenadeBlocks.class);

        PromenadeStrippables.register();
        PromenadeFlammables.register();
        PromenadeBlockEntities.addBlocksToVanillaBlockEntityTypes();

        Reflection.initialize(PromenadeItems.class);

        Reflection.initialize(PromenadeItemGroups.class);
        PromenadeItemGroupAdditions.appendItemGroups();
        PromenadeCompostingChances.register();
        PromenadeTrades.appendVillagerTrades();
        PromenadeDispenserBehaviors.register();

        Reflection.initialize(PromenadeSensorTypes.class);
        Reflection.initialize(PromenadeMemoryModuleTypes.class);
        Reflection.initialize(PromenadeTrackedData.class);
        Reflection.initialize(PromenadeEntityTypes.class);

        Reflection.initialize(PromenadeTrunkPlacerTypes.class);
        Reflection.initialize(PromenadeFoliagePlacerTypes.class);
        Reflection.initialize(PromenadeFeatures.class);
        Reflection.initialize(PromenadePlacementModifierTypes.class);

        PromenadeEntityTypes.appendWorldGen();
        PromenadeBiomes.appendWorldGen();
        PromenadePlacedFeatures.appendWorldGen();

        PromenadeRegistryAliases.registerAliases();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}