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
package fr.hugman.promenade.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public final class ItemSettings {
	public static Item.Settings max1() {
		return new Item.Settings().maxCount(1);
	}

	public static Item.Settings max16() {
		return new Item.Settings().maxCount(16);
	}

	public static Item.Settings stackableDrink(FoodComponent foodComponent, ConsumableComponent consumableComponent) {
		return new Item.Settings()
				.recipeRemainder(Items.GLASS_BOTTLE)
				.food(foodComponent, consumableComponent)
				.useRemainder(Items.GLASS_BOTTLE)
				.maxCount(16);
	}
}
