package fr.hugman.promenade;

import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.content.*;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Promenade implements ModInitializer {
	public static final String MOD_ID = "promenade";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final PromenadeConfig CONFIG = AutoConfig.register(PromenadeConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

	@Override
	public void onInitialize() {
		PromenadeTrackedData.init();

		CommonContent.init();
		VanillaPilesContent.init();
		FoodContent.init();
		IgneousContent.init();

		CherryContent.init();
		MapleContent.init();
		PalmContent.init();
		AmaranthContent.init();

		AnimalContent.init();
		MonsterContent.init();

		if(FabricLoader.getInstance().isModLoaded("columns")) {
			//ColumnsContent.init();
			//Promenade.LOGGER.info("Initialized Columns compatibility");
			Promenade.LOGGER.warn("Columns compatibility has not yet been implemented");
		}
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}