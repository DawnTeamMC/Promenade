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
package fr.hugman.promenade.component;

import net.minecraft.component.type.FoodComponent;

public class PromenadeFoodComponents {
	public static final FoodComponent MAPLE_SYRUP_BOTTLE = alwaysEdible(6, 0.1F);

	public static final FoodComponent BLUEBERRIES = simple(2, 0.1F);

	public static final FoodComponent BANANA = simple(4, 0.3F);
	public static final FoodComponent APRICOT = simple(4, 0.3F);
	public static final FoodComponent MANGO = simple(4, 0.3F);

	public static final FoodComponent RAW_DUCK = simple(2, 0.3F);
	public static final FoodComponent COOKED_DUCK = simple(6, 0.6F);

	public static FoodComponent simple(int nutrition, float saturation) {
		return new FoodComponent.Builder().nutrition(nutrition).saturationModifier(saturation).build();
	}

	public static FoodComponent alwaysEdible(int nutrition, float saturation) {
		return new FoodComponent.Builder().nutrition(nutrition).saturationModifier(saturation).build();
	}
}
