package com.hugman.promenade.init.data;

import com.hugman.promenade.Promenade;
import net.minecraft.util.Identifier;

public class PromenadeLootTables {
	public static final Identifier WITCH_HUT_CHEST = chest("witch_hut");

	public static Identifier chest(String name) {
		return Promenade.MOD_DATA.id("chests/" + name);
	}
}
