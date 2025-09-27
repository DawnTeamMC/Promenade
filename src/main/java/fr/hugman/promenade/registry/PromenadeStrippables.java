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
package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class PromenadeStrippables {
	public static void register() {
		StrippableBlockRegistry.register(PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.STRIPPED_SAKURA_LOG);
		StrippableBlockRegistry.register(PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_WOOD);
		StrippableBlockRegistry.register(PromenadeBlocks.MAPLE_LOG, PromenadeBlocks.STRIPPED_MAPLE_LOG);
		StrippableBlockRegistry.register(PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_WOOD);
		StrippableBlockRegistry.register(PromenadeBlocks.PALM_LOG, PromenadeBlocks.STRIPPED_PALM_LOG);
		StrippableBlockRegistry.register(PromenadeBlocks.PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_WOOD);
		StrippableBlockRegistry.register(PromenadeBlocks.DARK_AMARANTH_STEM, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
		StrippableBlockRegistry.register(PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
	}
}
