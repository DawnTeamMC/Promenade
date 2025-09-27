/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
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
