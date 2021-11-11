package com.hugman.promenade;

import com.hugman.promenade.init.client.PromenadeColorMaps;
import com.hugman.promenade.init.client.PromenadeEntityModelLayers;
import com.hugman.promenade.init.client.PromenadeEntityRenders;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PromenadeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		PromenadeColorMaps.registerColors();
		PromenadeEntityModelLayers.init();
		PromenadeEntityRenders.init();
	}
}
