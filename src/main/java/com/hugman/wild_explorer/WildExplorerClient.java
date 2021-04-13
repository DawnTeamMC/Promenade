package com.hugman.wild_explorer;

import com.hugman.wild_explorer.init.client.WEColorMaps;
import com.hugman.wild_explorer.init.client.WEEntityModels;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class WildExplorerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		WEColorMaps.registerColors();
		WEEntityModels.init();
	}
}
