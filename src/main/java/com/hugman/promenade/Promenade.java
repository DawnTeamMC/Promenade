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

	public static void initClass(Class<?> klass) {
		Reflection.initialize(klass);
		for(Class<?> subKlass : klass.getClasses()) {
			initClass(subKlass);
		}
	}

	public static void initBundles() {
		initClass(CommonBundle.class);

		initClass(OreBundle.class);
		initClass(VanillaPilesBundle.class);

		initClass(AnimalBundle.class);
		initClass(MonsterBundle.class);

		MapleBundle.init();
		initClass(CherryBundle.class);
		initClass(FoodBundle.class);
		initClass(PalmBundle.class);

		initClass(AmaranthBundle.class);

		if(FabricLoader.getInstance().isModLoaded("columns")) {
			initClass(ColumnsBundle.class);
			Promenade.LOGGER.info("Initialized Columns compatibility");
		}
	}

	public static void postInitBundles() {
		MapleBundle.addWanderingSales();
		CherryBundle.addWanderingSales();
		PalmBundle.addWanderingSales();

		AmaranthBundle.addToGen();
		AnimalBundle.addToGen();
		MapleBundle.addToGen();
		CherryBundle.addToGen();
		FoodBundle.addToGen();
		MonsterBundle.addToGen();
		OreBundle.addToGen();
		PalmBundle.addToGen();
	}
}