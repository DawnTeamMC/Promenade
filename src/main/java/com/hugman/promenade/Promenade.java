package com.hugman.promenade;

import com.hugman.dawn.api.object.ModData;
import com.hugman.promenade.compat.init.PromenadeColumns;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.PromenadeBlocks;
import com.hugman.promenade.init.PromenadeEntities;
import com.hugman.promenade.init.PromenadeItems;
import com.hugman.promenade.init.world.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Promenade implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("promenade");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final PromenadeConfig CONFIG = AutoConfig.register(PromenadeConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

	@Override
	public void onInitialize() {
		PromenadeBlocks.init();
		PromenadeItems.init();
		PromenadeEntities.init();
		PromenadeFeatures.init();
		PromenadeStructureFeatures.init();
		PromenadeStructurePieces.init();
		PromenadeConfiguredFeatures.init();
		PromenadeConfiguredStructuresFeatures.init();
		PromenadeConfiguredSurfaceBuilders.init();
		PromenadeBiomes.init();
		if(FabricLoader.getInstance().isModLoaded("columns")) {
			PromenadeColumns.init();
		}
		MOD_DATA.registerCreators();
		PromenadeBiomes.addToGen();
		PromenadeConfiguredFeatures.addToGen();
		PromenadeConfiguredStructuresFeatures.addToGen();
		PromenadeEntities.addToGen();
	}
}