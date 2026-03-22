package fr.hugman.promenade.world;

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class PromenadeGameRules {
	public static final GameRule<Boolean> DO_BLOCKS_GET_SNOWY = GameRuleBuilder.forBoolean(true).category(GameRuleCategory.UPDATES).buildAndRegister(Promenade.id("do_blocks_get_snowy"));
}
