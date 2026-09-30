package fr.hugman.promenade.world;

import net.minecraft.world.level.GameRules;

public class PromenadeGameRules {
	// 1.21.1 game rules are named in camel case, without a namespace
	public static final GameRules.Key<GameRules.BooleanValue> DO_BLOCKS_GET_SNOWY = GameRules.register("doBlocksGetSnowy", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));
}
