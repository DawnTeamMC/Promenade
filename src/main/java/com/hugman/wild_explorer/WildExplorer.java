package com.hugman.wild_explorer;

import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.mod.init.config.DawnConfig;
import com.hugman.wild_explorer.init.*;
import com.hugman.wild_explorer.init.config.WEConfig;
import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import com.hugman.wild_explorer.init.world.WEConfiguredSurfaceBuilders;
import com.hugman.wild_explorer.init.world.WEFeatures;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigHolder;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WildExplorer implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("wild_explorer");
	public static final Logger LOGGER = LogManager.getLogger();
	public static WEConfig CONFIG;

	@Override
	public void onInitialize() {
		ConfigHolder<WEConfig> configHolder = AutoConfig.register(WEConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));
		CONFIG = configHolder.getConfig();

		WEBlocks.init();
		WEItems.init();
		WEEntities.init();
		WESounds.init();
		WEFeatures.init();
		WEConfiguredFeatures.init();
		WEConfiguredSurfaceBuilders.init();
		WEBiomes.init();
	}
}
