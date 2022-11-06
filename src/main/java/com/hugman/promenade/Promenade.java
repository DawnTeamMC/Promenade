package com.hugman.promenade;

import com.google.common.reflect.Reflection;
import com.hugman.dawn.api.object.ModData;
import com.hugman.newdawn.DawnBuilders;
import com.hugman.newdawn.PromenadeBuilders;
import com.hugman.newdawn.builder.ItemBuilder;
import com.hugman.promenade.compat.init.ColumnsBundle;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.*;
import com.hugman.promenade.object.block.MapleLogBlock;
import com.hugman.promenade.object.block.StrippedMapleLogBlock;
import com.hugman.promenade.object.block.sapling_generator.SimpleSaplingGenerator;
import com.hugman.promenade.object.trade_offers.TradeOfferUtils;
import com.hugman.promenade.util.WorldGenUtil;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Promenade implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("promenade");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final PromenadeConfig CONFIG = AutoConfig.register(PromenadeConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

	@Override
	public void onInitialize() {
		MOD_DATA.addOldName("wild_explorer");

		MapleContent.init();

		initBundles();
		MOD_DATA.registerCreators();
		postInitBundles();
	}

	public static Identifier id(String path) {
		return MOD_DATA.id(path);
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
		CherryBundle.addWanderingSales();
		PalmBundle.addWanderingSales();

		AmaranthBundle.addToGen();
		AnimalBundle.addToGen();
		CherryBundle.addToGen();
		FoodBundle.addToGen();
		MonsterBundle.addToGen();
		OreBundle.addToGen();
		PalmBundle.addToGen();
	}
}