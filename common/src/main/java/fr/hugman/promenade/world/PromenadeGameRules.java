package fr.hugman.promenade.world;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

public class PromenadeGameRules {
	public static final GameRule<Boolean> DO_BLOCKS_GET_SNOWY = registerBoolean("do_blocks_get_snowy", GameRuleCategory.UPDATES, true);

	// mirrors GameRules#registerBoolean
	private static GameRule<Boolean> registerBoolean(String path, GameRuleCategory category, boolean defaultValue) {
		var rule = new GameRule<>(category, GameRuleType.BOOL, BoolArgumentType.bool(), GameRuleTypeVisitor::visitBoolean, Codec.BOOL, value -> value ? 1 : 0, defaultValue, FeatureFlagSet.of());
		return Registry.register(BuiltInRegistries.GAME_RULE, Promenade.id(path), rule);
	}
}
