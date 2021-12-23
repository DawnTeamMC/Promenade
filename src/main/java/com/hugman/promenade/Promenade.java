package com.hugman.promenade;

import com.google.common.reflect.Reflection;
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
		MOD_DATA.addOldName("wild_explorer");
		initBundles();
		MOD_DATA.registerCreators();
		postInitBundles();
	}

	public static void initBundle(Class<?> clazz) {
		Reflection.initialize(clazz);
		for(Class<?> clazz2 : clazz.getClasses()) {
			initBundle(clazz2);
		}
	}

	public static void initBundles() {
		initBundle(CommonBundle.class);

		initBundle(OreBundle.class);
		initBundle(VanillaPilesBundle.class);
		initBundle(MushroomBundle.class);

		initBundle(AnimalBundle.class);
		initBundle(MonsterBundle.class);

		initBundle(AutumnBundle.class);
		initBundle(CherryBundle.class);
		initBundle(FoodBundle.class);
		initBundle(PalmBundle.class);

		initBundle(WitchHutBundle.class);

		initBundle(TallerNetherForestBundle.class);
		initBundle(GalleryBundle.class);

		initBundle(AmaranthBundle.class);

		if(FabricLoader.getInstance().isModLoaded("columns")) {
			initBundle(ColumnsBundle.class);
			Promenade.LOGGER.info("Initialized Columns compatibility");
		}
	}

	public static void postInitBundles() {
		AutumnBundle.addWanderingSales();
		CherryBundle.addWanderingSales();

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