package com.hugman.promenade;

import com.hugman.dawn.api.object.ModData;
import com.hugman.promenade.compat.init.ColumnsBundle;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.BlockBundle;
import com.hugman.promenade.init.EntityBundle;
import com.hugman.promenade.init.ItemBundle;
import com.hugman.promenade.init.TallerNetherForestBundle;
import com.hugman.promenade.init.world.*;
import com.hugman.promenade.init.AutumnBundle;
import com.hugman.promenade.init.world.feature.OreBundle;
import com.hugman.promenade.init.world.feature.PromenadeConfiguredFeatures;
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
		BlockBundle.init();
		ItemBundle.init();
		EntityBundle.init();
		PromenadeFeatures.init();
		PromenadeStructureFeatures.init();
		PromenadeStructurePieces.init();
		PromenadeConfiguredFeatures.init();
		PromenadeConfiguredStructuresFeatures.init();
		PromenadeConfiguredSurfaceBuilders.init();
		TallerNetherForestBundle.init();

		AutumnBundle.init();

		if(FabricLoader.getInstance().isModLoaded("columns")) {
			ColumnsBundle.init();
		}
		MOD_DATA.registerCreators();

		AutumnBundle.addToGen();
		OreBundle.addToGen();
		TallerNetherForestBundle.addToGen();

		PromenadeConfiguredFeatures.addToGen();
		PromenadeConfiguredStructuresFeatures.addToGen();
		EntityBundle.addToGen();
	}
}