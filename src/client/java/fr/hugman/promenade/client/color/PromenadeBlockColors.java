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
package fr.hugman.promenade.client.color;

import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.world.biome.FoliageColors;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeFoliageColors;

@Environment(EnvType.CLIENT)
public final class PromenadeBlockColors {
	public static void register() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> PromenadeFoliageColors.PALM, PromenadeBlocks.PALM_LEAVES, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeBlocks.PALM_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.DEFAULT,
				PromenadeBlocks.SNOWY_OAK_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES, PromenadeBlocks.SNOWY_ACACIA_LEAVES, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, PromenadeBlocks.SNOWY_MANGROVE_LEAVES,
				PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeBlocks.MANGROVE_LEAF_PILE,
				PromenadeBlocks.SAP_MAPLE_LEAVES, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES,
				PromenadeBlocks.SNOWY_OAK_LEAVES, PromenadeBlocks.SNOWY_JUNGLE_LEAVES
		);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.SPRUCE, PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.SNOWY_SPRUCE_LEAVES);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColors.BIRCH, PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.SNOWY_BIRCH_LEAVES);
	}
}
