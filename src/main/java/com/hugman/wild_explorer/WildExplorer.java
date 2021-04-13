package com.hugman.wild_explorer;

import com.hugman.dawn.api.object.ModData;
import com.hugman.wild_explorer.config.WEConfig;
import com.hugman.wild_explorer.init.WEBiomes;
import com.hugman.wild_explorer.init.WEBlocks;
import com.hugman.wild_explorer.init.WEEntities;
import com.hugman.wild_explorer.init.WEItems;
import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import com.hugman.wild_explorer.init.world.WEConfiguredStructuresFeatures;
import com.hugman.wild_explorer.init.world.WEConfiguredSurfaceBuilders;
import com.hugman.wild_explorer.init.world.WEFeatures;
import com.hugman.wild_explorer.init.world.WEStructureFeatures;
import com.hugman.wild_explorer.init.world.WEStructurePieces;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WildExplorer implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("wild_explorer");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final WEConfig CONFIG = AutoConfig.register(WEConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

	@Override
	public void onInitialize() {
		WEBlocks.init();
		WEItems.init();
		WEEntities.init();
		WEFeatures.init();
		WEStructureFeatures.init();
		WEStructurePieces.init();
		WEConfiguredFeatures.init();
		WEConfiguredStructuresFeatures.init();
		WEConfiguredSurfaceBuilders.init();
		WEBiomes.init();
		MOD_DATA.registerCreators();
		WEBiomes.addToGen();
		WEConfiguredStructuresFeatures.addToGen();
	}
}