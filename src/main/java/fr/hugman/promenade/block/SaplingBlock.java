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
package fr.hugman.promenade.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;

//TODO: codec
public class SaplingBlock extends net.minecraft.block.SaplingBlock {
	private final Predicate<BlockState> predicate;

	public SaplingBlock(SaplingGenerator saplingGenerator, Predicate<BlockState> predicate, Settings settings) {
		super(saplingGenerator, settings);
		this.predicate = predicate;
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		if (predicate != null)
			return predicate.test(floor);
		return super.canPlantOnTop(floor, world, pos);
	}
}