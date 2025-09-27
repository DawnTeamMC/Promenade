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
import fr.hugman.promenade.block.property.PromenadeBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public class MapleLogBlock extends PillarBlock {
	public static final BooleanProperty NATURAL = PromenadeBlockProperties.NATURAL;
	public static final MapCodec<MapleLogBlock> CODEC = createCodec(MapleLogBlock::new);

	public MapleLogBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(NATURAL, false));
	}

	@Override
	public MapCodec<MapleLogBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(NATURAL);
	}
}
