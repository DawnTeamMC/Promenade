package fr.hugman.promenade.world;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class PromenadeGameRules {
    public static final GameRules.Key<GameRules.BooleanRule> DO_BLOCKS_GET_SNOWY = of("doBlocksGetSnowy", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(true));

    public static <T extends GameRules.Rule<T>> GameRules.Key<T> of(String name, GameRules.Category category, GameRules.Type<T> type) {
        return GameRuleRegistry.register(name, category, type);
    }
}
