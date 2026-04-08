package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.block.property.PromenadeBlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class MapleLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty NATURAL = PromenadeBlockProperties.NATURAL;
    public static final MapCodec<MapleLogBlock> CODEC = simpleCodec(MapleLogBlock::new);

    public MapleLogBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(NATURAL, false));
    }

    @Override
    public MapCodec<MapleLogBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NATURAL);
    }
}
