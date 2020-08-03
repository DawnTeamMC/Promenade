package com.hugman.wild_explorer;

import com.hugman.dawn.api.creator.ModData;
import com.hugman.wild_explorer.init.WEBiomePack;
import com.hugman.wild_explorer.init.WEBlockPack;
import com.hugman.wild_explorer.init.WEItemPack;
import com.hugman.wild_explorer.init.client.WEColorMaps;
import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import com.hugman.wild_explorer.init.world.WEFeatures;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WildExplorer implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("wild_explorer");
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		new WEBlockPack();
		new WEItemPack();
		new WEFeatures();
		new WEConfiguredFeatures();
		new WEBiomePack();
	}
}
