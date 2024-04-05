package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
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
