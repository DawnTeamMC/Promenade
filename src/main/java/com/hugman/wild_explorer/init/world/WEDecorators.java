package com.hugman.wild_explorer.init.world;

import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.object.world.gen.decorator.CountSafelistRangeDecorator;
import com.hugman.wild_explorer.object.world.gen.decorator.config.CountSafelistRangeDecoratorConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;

public class WEDecorators {
	public static Decorator<CountSafelistRangeDecoratorConfig> COUNT_CEILING = Registry.register(Registry.DECORATOR, WildExplorer.MOD_DATA.id("count_safelist_range_ceiling"), new CountSafelistRangeDecorator(true));
	public static Decorator<CountSafelistRangeDecoratorConfig> COUNT_FLOOR = Registry.register(Registry.DECORATOR, WildExplorer.MOD_DATA.id("count_safelist_range_floor"), new CountSafelistRangeDecorator(false));

	public static void init() {
	}
}
