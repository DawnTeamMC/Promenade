package com.hugman.wild_explorer.init.data;

import com.hugman.wild_explorer.WildExplorer;
import net.minecraft.util.Identifier;

public class WELootTables {
	public static final Identifier WITCH_HUT_CHEST = chest("witch_hut");

	public static Identifier chest(String name) {
		return WildExplorer.MOD_DATA.id("chests/" + name);
	}
}
