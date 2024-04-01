package fr.hugman.promenade;

import com.google.common.reflect.Reflection;
import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.content.*;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
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
        Reflection.initialize(PromenadeSensorTypes.class);

        PromenadeBlocks.appendItemGroups();
        PromenadeBlocks.appendVillagerTrades();
        PromenadeBiomes.appendWorldGen();

        PromenadeTrackedData.init();

        AnimalContent.register(REGISTRAR);
        MonsterContent.register(REGISTRAR);

        CommonContent.register(REGISTRAR);
        VanillaPilesContent.register(REGISTRAR);
        FoodContent.register(REGISTRAR);
        IgneousContent.register(REGISTRAR);

        SakuraContent.register(REGISTRAR);
        MapleContent.register(REGISTRAR);
        TropicalContent.register(REGISTRAR);
        GlaglaglaContent.register(REGISTRAR);
        AmaranthContent.register(REGISTRAR);
    }

    public static Identifier id(String path) {
        return REGISTRAR.id(path);
    }
}