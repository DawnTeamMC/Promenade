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

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import fr.hugman.promenade.Promenade;

public class PromenadeBlockTags {
	public static final TagKey<Block> SAKURA_LOGS = of("sakura_logs");
	public static final TagKey<Block> MAPLE_LOGS = of("maple_logs");
	public static final TagKey<Block> PALM_LOGS = of("palm_logs");
	public static final TagKey<Block> DARK_AMARANTH_STEMS = of("dark_amaranth_stems");

	public static final TagKey<Block> SNOWY_LEAVES = of("snowy_leaves");

	public static final TagKey<Block> FALLEN_LEAVES = of("fallen_leaves");
	public static final TagKey<Block> LEAF_PILES = of("leaf_piles");
	public static final TagKey<Block> FLOWER_PILES = of("flower_piles");

	public static final TagKey<Block> DARK_AMARANTH_FUNGUS_PLACEABLE_ON = of("dark_amaranth_fungus_placeable_on");
	public static final TagKey<Block> DARK_AMARANTH_FUNGUS_GROWABLE_ON = of("dark_amaranth_fungus_growable_on");
	public static final TagKey<Block> DARK_AMARANTH_ROOTS_PLACEABLE_ON = of("dark_amaranth_roots_placeable_on");

	public static TagKey<Block> of(String path) {
		return TagKey.of(RegistryKeys.BLOCK, Promenade.id(path));
	}
}
