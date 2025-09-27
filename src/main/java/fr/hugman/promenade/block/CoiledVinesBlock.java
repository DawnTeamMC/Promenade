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

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineLogic;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

public class CoiledVinesBlock extends AbstractFacingPlantStemBlock {
	public static final MapCodec<CoiledVinesBlock> CODEC = createCodec(CoiledVinesBlock::new);
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.createCuboidShape(2.0, 2.0, 2.0, 14.0, 16.0, 14.0), // DOWN
			Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 14.0, 14.0), // UP
			Block.createCuboidShape(2.0, 2.0, 2.0, 14.0, 14.0, 16.0), // NORTH
			Block.createCuboidShape(2.0, 2.0, 0.0, 14.0, 14.0, 14.0), // SOUTH
			Block.createCuboidShape(2.0, 2.0, 2.0, 16.0, 14.0, 14.0), // WEST
			Block.createCuboidShape(0.0, 2.0, 2.0, 14.0, 14.0, 14.0) // EAST
	};

	public CoiledVinesBlock(Settings settings) {
		super(settings, SHAPES, false, 0.1);
	}

	@Override
	protected MapCodec<CoiledVinesBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected Block getPlant() {
		return PromenadeBlocks.COILED_VINES_PLANT;
	}

	@Override
	protected int getGrowthLength(Random random) {
		return VineLogic.getGrowthLength(random);
	}

	@Override
	protected boolean canGrowAt(BlockState state) {
		return VineLogic.isValidForWeepingStem(state);
	}
}
