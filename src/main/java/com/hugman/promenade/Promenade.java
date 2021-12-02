package com.hugman.promenade;

import com.hugman.dawn.api.object.ModData;
import com.hugman.promenade.compat.init.ColumnsBundle;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.*;
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
		initBundles();
		MOD_DATA.registerCreators();
		postInitBundles();
	}

	public static void initBundles() {
		AmaranthBundle.init();
		AnimalBundle.init();
		AutumnBundle.init();
		CherryBundle.init();
		CommonBundle.init();
		FoodBundle.init();
		GalleryBundle.init();
		MonsterBundle.init();
		MushroomBundle.init();
		OreBundle.init();
		PalmBundle.init();
		TallerNetherForestBundle.init();
		VanillaPilesBundle.init();
		WitchHutBundle.init();

		if(FabricLoader.getInstance().isModLoaded("columns")) {
			ColumnsBundle.init();
		}
	}

	public static void postInitBundles() {
		AmaranthBundle.addToGen();
		AnimalBundle.addToGen();
		AutumnBundle.addToGen();
		CherryBundle.addToGen();
		FoodBundle.addToGen();
		GalleryBundle.addToGen();
		MonsterBundle.addToGen();
		OreBundle.addToGen();
		PalmBundle.addToGen();
		TallerNetherForestBundle.addToGen();
		WitchHutBundle.addToGen();
	}
}