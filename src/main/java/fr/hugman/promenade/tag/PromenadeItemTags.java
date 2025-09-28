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
package fr.hugman.promenade.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import fr.hugman.promenade.Promenade;

public class PromenadeItemTags {
	public static final TagKey<Item> SAKURA_LOGS = of("sakura_logs");
	public static final TagKey<Item> MAPLE_LOGS = of("maple_logs");
	public static final TagKey<Item> PALM_LOGS = of("palm_logs");
	public static final TagKey<Item> DARK_AMARANTH_STEMS = of("dark_amaranth_stems");

	public static final TagKey<Item> SNOWY_LEAVES = of("snowy_leaves");

	public static final TagKey<Item> CAPYBARA_FOOD = of("capybara_food");
	public static final TagKey<Item> DUCK_FOOD = of("duck_food");

	private static TagKey<Item> of(String path) {
		return TagKey.of(RegistryKeys.ITEM, Promenade.id(path));
	}
}
