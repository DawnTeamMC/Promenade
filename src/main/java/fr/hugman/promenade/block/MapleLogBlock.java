package fr.hugman.promenade.block;

import fr.hugman.promenade.state.property.PromenadeBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public class MapleLogBlock extends PillarBlock {
	public static final BooleanProperty NATURAL = PromenadeBlockProperties.NATURAL;

	public MapleLogBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(NATURAL, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(NATURAL);
	}
}
