package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RootsBlock extends net.minecraft.world.level.block.RootsBlock {

    private final TagKey<Block> canPlantOn;

    public RootsBlock(TagKey<Block> canPlantOn, Properties settings) {
        super(settings);
        this.canPlantOn = canPlantOn;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(this.canPlantOn);
    }
}
