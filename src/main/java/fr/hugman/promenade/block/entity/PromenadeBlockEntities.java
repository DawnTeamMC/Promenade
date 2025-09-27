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
package fr.hugman.promenade.block.entity;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.block.entity.BlockEntityType;

public class PromenadeBlockEntities {
	public static void addBlocksToVanillaBlockEntityTypes() {
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_WALL_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_HANGING_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_WALL_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_HANGING_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.PALM_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.PALM_WALL_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.PALM_HANGING_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.PALM_WALL_HANGING_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.DARK_AMARANTH_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(PromenadeBlocks.DARK_AMARANTH_WALL_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);
	}
}
