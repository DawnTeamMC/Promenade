package fr.hugman.promenade;

import fr.hugman.dawn.Registrar;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.gen.surface.PromenadeSurfaceBuilders;
import fr.hugman.promenade.registry.content.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Promenade implements ModInitializer {
	public static final Registrar REGISTRAR = new Registrar("promenade");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final PromenadeConfig CONFIG = AutoConfig.register(PromenadeConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

	@Override
	public void onInitialize() {
		PromenadeSurfaceBuilders.init();

		PromenadeTrackedData.init();
		PromenadeSensorTypes.register(REGISTRAR);

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
		DuskContent.register(REGISTRAR);
		AmaranthContent.register(REGISTRAR);

		if(FabricLoader.getInstance().isModLoaded("columns")) {
			//TODO: haykam
			//ColumnsContent.init();
			//Promenade.LOGGER.info("Initialized Columns compatibility");
			Promenade.LOGGER.warn("Columns compatibility has not yet been implemented");
		}
	}

	public static Identifier id(String path) {
		return REGISTRAR.id(path);
	}
}